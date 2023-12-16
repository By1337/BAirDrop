package org.by1337.bairdrop;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import org.by1337.bairdrop.configManager.CConfig;
import org.by1337.bairdrop.configManager.ConfigMessage;
import org.by1337.bairdrop.configManager.Config;
import org.by1337.bairdrop.hologram.*;
import org.by1337.bairdrop.listeners.Compass;
import org.by1337.bairdrop.listeners.InteractListener;
import org.by1337.bairdrop.locationGenerator.CGenLoc;
import org.by1337.bairdrop.locationGenerator.GeneratorLoc;
import org.by1337.bairdrop.summoner.Summoner;
import org.by1337.bairdrop.worldGuardHook.RegionManager;
import org.by1337.bairdrop.command.Commands;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.customListeners.observer.Observer;
import org.by1337.bairdrop.util.*;
import org.by1337.bairdrop.util.Message;

import java.io.*;

import java.util.*;


public final class BAirDrop extends JavaPlugin {

    public static HashMap<String, AirDrop> airDrops = new HashMap<>();
    public static HashMap<NamespacedKey, Observer> customEventListeners = new HashMap<>();

    public static Summoner summoner = new Summoner();
    public static GlobalTimer globalTimer;
    public static Compass compass;
    public static LogLevel logLevel;
    public static IHologram hologram;
    private static Config config;
    private static ConfigMessage configMessage;
    private static BAirDrop instance;

    private static org.by1337.api.chat.util.Message message;

    private static ProtocolManager protocolManager = null;

    @Override
    public void onLoad() {
        instance = this;
        message = new org.by1337.api.chat.util.Message(getLogger());
    }


    @Override
    public void onEnable() {
        ConfigurationSerialization.registerClass(CGenLoc.class);

        config = new CConfig();

        configMessage = (ConfigMessage) config;
        getInstance().saveDefaultConfig();
        getInstance().saveConfig();


        try {
            String lvl = getInstance().getConfig().getString("log-level", "LOW");
            logLevel = LogLevel.valueOf(lvl);
        } catch (IllegalArgumentException e) {
            message.error(e);
            logLevel = LogLevel.LOW;
        }

        long x = System.currentTimeMillis();

        getiConfig().loadConfiguration();

        if (this.getConfig().getBoolean("use-metrics"))
            new Metrics(getInstance(), 17870);

        Commands commands = new Commands();
        Objects.requireNonNull(getInstance().getCommand("bairdrop")).setExecutor(commands);
        Objects.requireNonNull(getInstance().getCommand("bairdrop")).setTabCompleter(commands);

        Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), getInstance());
        getServer().getPluginManager().registerEvents(summoner, getInstance());
        getServer().getPluginManager().registerEvents(compass, BAirDrop.getInstance());


        if (Bukkit.getPluginManager().getPlugin("DecentHolograms") != null) {
            hologram = new DecentHologram();
        } else if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
            protocolManager = ProtocolLibrary.getProtocolManager();
            hologram = new ProtocolHoloManager();
        } else {
            hologram = new EmptyHologram();
            message.error(getConfigMessage().getMessage("depend-not-found"));
        }

        for (File file : getiConfig().getAirDrops().keySet()) {
            AirDrop airDrop = new CAirDrop(getiConfig().getAirDrops().get(file), file);
            airDrops.put(getiConfig().getAirDrops().get(file).getString("air-id"), airDrop);
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

        ExecuteCommands.registerIgnoreCommand("[SCHEDULER]");
        ExecuteCommands.registerIgnoreCommand("[ASYNC]");
        ExecuteCommands.registerIgnoreCommand("[LATER-");

        message.logger(String.format(getConfigMessage().getMessage("start-time"), System.currentTimeMillis() - x));

    }

    public static org.by1337.api.chat.util.Message getMessage() {
        return message;
    }

    public static Config getiConfig() {
        return config;
    }

    public static ConfigMessage getConfigMessage() {
        return configMessage;
    }

    public static BAirDrop getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        if (!getiConfig().isLoaded())
            return;
        long x = System.currentTimeMillis();
        for (AirDrop airDrop : airDrops.values()) {
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

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            new PlaceholderHook().unregister();
        message.logger(String.format(getConfigMessage().getMessage("off-time"), System.currentTimeMillis() - x));

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
        getiConfig().getSchematics().clear();
        airDrops.clear();
        customEventListeners.clear();
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

