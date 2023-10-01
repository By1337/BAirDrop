package org.by1337.bairdrop;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import org.by1337.bairdrop.command.CommandHook;
import org.by1337.bairdrop.configManager.CConfig;
import org.by1337.bairdrop.configManager.ConfigMessage;
import org.by1337.bairdrop.configManager.Config;
import org.by1337.bairdrop.hologram.*;
import org.by1337.bairdrop.lang.Lang;
import org.by1337.bairdrop.listeners.Compass;
import org.by1337.bairdrop.listeners.CraftItem;
import org.by1337.bairdrop.listeners.InteractListener;
import org.by1337.bairdrop.location.CGenLoc;
import org.by1337.bairdrop.location.GeneratorLoc;
import org.by1337.bairdrop.observer.CustomListenerLoader;
import org.by1337.bairdrop.summoner.Summoner;
import org.by1337.bairdrop.worldGuardHook.RegionManager;
import org.by1337.bairdrop.command.CompleterHook;
import org.by1337.bairdrop.observer.CustomEvent;
import org.by1337.bairdrop.effect.effectImpl.*;
import org.by1337.bairdrop.serializable.EffectDeserialize;
import org.by1337.bairdrop.serializable.StateSerializable;
import org.by1337.bairdrop.util.*;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.Version;

import java.io.*;

import java.util.*;


public final class BAirDrop extends JavaPlugin {

    public static HashMap<String, AirDrop> airDrops = new HashMap<>();

    public static Summoner summoner = new Summoner();
    public static GlobalTimer globalTimer;
    public static HashMap<String, CustomCraft> crafts = new HashMap<>();
    public static Compass compass;
    public static LogLevel logLevel;
    public static IHologram hologram;
    private static Config config;
    private static ConfigMessage configMessage;

    @Getter
    private static Plugin instance;

    private static ProtocolManager protocolManager = null;

    public BAirDrop() {
        super();
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        Lang.loadFromCode();
//        try {
//            Version.init();
//            Message.logger("Version detected: " + Version.version);
//        } catch (Version.UnsupportedVersionException e) {
//            Message.error(e.getLocalizedMessage());
//            getServer().getPluginManager().disablePlugin(this);
//            return;
//        }
        ConfigurationSerialization.registerClass(CGenLoc.class);

        EffectDeserialize.register(Circle.class);
        EffectDeserialize.register(ExpandingCircle.class);
        EffectDeserialize.register(FireworkEffect.class);
        EffectDeserialize.register(Helix.class);
        EffectDeserialize.register(RandomParticle.class);
        EffectDeserialize.register(Torus.class);
        EffectDeserialize.register(WrithingHelix.class);

        getInstance().saveDefaultConfig();
        getInstance().saveConfig();
        config = new CConfig();
        configMessage = (ConfigMessage) config;


        try {
            String lvl = getInstance().getConfig().getString("log-level", "LOW");
            logLevel = LogLevel.valueOf(lvl);
        } catch (IllegalArgumentException e) {
            Message.error(e.getLocalizedMessage());
            logLevel = LogLevel.LOW;
        }

        long x = System.currentTimeMillis();

        getiConfig().loadConfiguration();

        if (this.getConfig().getBoolean("use-metrics"))
            new Metrics(this, 17870);


        getCommand("bairdrop").setExecutor(new CommandHook());
        getCommand("bairdrop").setTabCompleter(new CompleterHook());

        Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), getInstance());
        getServer().getPluginManager().registerEvents(summoner, getInstance());
        getServer().getPluginManager().registerEvents(new CraftItem(), BAirDrop.getInstance());
        getServer().getPluginManager().registerEvents(compass, BAirDrop.getInstance());


        if (Bukkit.getPluginManager().getPlugin("DecentHolograms") != null) {
            hologram = new DecentHologram();
        } else if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
            protocolManager = ProtocolLibrary.getProtocolManager();
            hologram = new ProtocolHoloManager();
        } else {
            hologram = new EmptyHologram();
            Message.error(getConfigMessage().getMessage("depend-not-found"));
        }


        for (File file : getiConfig().getAirDrops().keySet()) {
            AirDrop airDrop = new CAirDrop(getiConfig().getAirDrops().get(file), file);
            airDrops.put(getiConfig().getAirDrops().get(file).getString("air-id"), airDrop);
            if (instance.getConfig().getBoolean("state-serializable")) {
                StateSerializable stateSerializable = (StateSerializable) airDrop;
                stateSerializable.stateDeserialize();
            }

        }

        List<String> ids = new ArrayList<>(airDrops.keySet());
        for (String id : ids) {
            airDrops.get(id).registerAllSignedObservers();
        }


        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            new PlaceholderHook().register();

        if (BAirDrop.getInstance().getConfig().getBoolean("global-time.enable")) {
            globalTimer = new GlobalTimer((BAirDrop.getInstance().getConfig().getInt("global-time.time") * 60));
        }
        if (logLevel == LogLevel.HARD) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Message.debug(String.format(getConfigMessage().getMessage("thread-count"), getInstance().getServer().getScheduler().getPendingTasks().stream().filter(t -> t.getOwner().getName().equalsIgnoreCase("BairDrop"))
                            .count()), LogLevel.HARD);
                }
            }.runTaskTimerAsynchronously(getInstance(), 10, 10);
        }

        Message.logger(String.format(getConfigMessage().getMessage("start-time"), System.currentTimeMillis() - x));

    }


    public static Config getiConfig() {
        return config;
    }

    @Deprecated
    public static ConfigMessage getConfigMessage() {
        return configMessage;
    }

    @Override
    public void onDisable() {
        if (!getiConfig().isLoaded())
            return;
        long x = System.currentTimeMillis();
        for (AirDrop airDrop : airDrops.values()) {
            if (instance.getConfig().getBoolean("state-serializable") && airDrop instanceof StateSerializable stateSerializable)
                stateSerializable.stateSerialize();

            if (airDrop.isAirDropStarted())
                airDrop.end();
            if (airDrop.isClone())
                airDrop.end();
            airDrop.notifyObservers(CustomEvent.UNLOAD, null);
            BAirDrop.hologram.remove(airDrop.getId());
            airDrop.save();
            airDrop.schematicsUndo();
            RegionManager.removeRegion(airDrop);
        }
        GeneratorLoc.save();
        CustomCraft.unloadCrafts();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            new PlaceholderHook().unregister();
        Message.logger(String.format(getConfigMessage().getMessage("off-time"), System.currentTimeMillis() - x));

    }

    /**
     * Перезагрузка конфиг файлов
     */
    public static void reload() {
        for (AirDrop airDrop : airDrops.values()) {
            if (airDrop.isAirDropStarted())
                airDrop.end();
            if (airDrop.isClone())
                airDrop.end();
            airDrop.notifyObservers(CustomEvent.UNLOAD, null);
            BAirDrop.hologram.remove(airDrop.getId());
            airDrop.setCanceled(true);
            airDrop.schematicsUndo();
            RegionManager.removeRegion(airDrop);
        }
        CustomCraft.unloadCrafts();
        getiConfig().getSchematics().clear();
        airDrops.clear();
        CustomListenerLoader.getCustomEventListeners().clear();
        // EffectFactory.EffectList.clear();
        getiConfig().getAirDrops().clear();

        getInstance().reloadConfig();
        compass.loadItem();
        GeneratorLoc.locs.clear();
        summoner.LoadSummoner();
        if (globalTimer != null) {
            if (!BAirDrop.getInstance().getConfig().getBoolean("global-time.enable")) {
                globalTimer.setStop(true);
                globalTimer = null;
            } else {
                globalTimer.setTimeToStartCons(BAirDrop.getInstance().getConfig().getInt("global-time.time") * 60);
                globalTimer.setTimeToStart(globalTimer.getTimeToStartCons());
                if (globalTimer.getAir() != null)
                    globalTimer.setAir(null);
            }
        } else if (BAirDrop.getInstance().getConfig().getBoolean("global-time.enable")) {
            globalTimer = new GlobalTimer((BAirDrop.getInstance().getConfig().getInt("global-time.time") * 60));
        }
        getiConfig().loadConfiguration();
        for (File file : getiConfig().getAirDrops().keySet()) {
            airDrops.put(getiConfig().getAirDrops().get(file).getString("air-id"), new CAirDrop(getiConfig().getAirDrops().get(file), file));
        }
        List<String> ids = new ArrayList<>(airDrops.keySet());
        for (String id : ids) {
            airDrops.get(id).registerAllSignedObservers();
        }
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}

