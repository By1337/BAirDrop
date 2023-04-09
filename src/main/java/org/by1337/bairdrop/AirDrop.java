package org.by1337.bairdrop;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.Hologram.HologramManager;
import org.by1337.bairdrop.effect.EffectFactory;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.by1337.bairdrop.BAirDrop.*;
import static org.by1337.bairdrop.util.AirManager.var3;
import static org.by1337.bairdrop.util.LocationGeneration.getSettings;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.menu.EditAirMenu;

public class AirDrop {
    private String invName;
    private String AirName;
    private int invSize;
    private World world;
    private int spawnMin;
    private int spawnMax;
    private int airProtect;
    private int timeToStart;
    private int searchBeforeStart;
    private int timeToOpen;
    private boolean startCountdownAfterClick;
    // boolean countdowMenu;
    private boolean timeStopEventMustGo;
    private int timeStop;
    private Material materialLocked;
    private Material materialUnlocked;
    //  private List<Items> ListItems = new ArrayList<>();
    private final HashMap<String, List<Items>> ListItems = new HashMap<>();
    // private TreeMap
    private boolean airLocked = true;
    private Inventory inv;
    private Location airLoc = null;
    private Location futureLocation = null;
    private FileConfiguration fileConfiguration;
    private int minOnlinePlayers;
    private String airId;
    private boolean itWasOpen = false;
    private boolean airDropStarted = false;
    private File airdropFile;
    private List<String> signedListener;
    private EditAirMenu EditAirMenu;
    private boolean pressed;
    private boolean usePreGeneratedLocations;
    private int timeToStartCons;
    private int timeToStopCons;
    private int timeToUnlockCons;
    private int searchBeforeStartCons;
    private boolean flatnessCheck;
    private Location staticLocation;
    private boolean useStaticLoc;
    private final HashMap<String, IEffect> activeEffects = new HashMap<>();
    private int attemptsToPick;
    private int chance;
    private boolean countTheTime;
    private EditSession editSession = null;
    private String generatorSettings;
    private List<String> airHolo = new ArrayList<>();
    private List<String> airHoloOpen = new ArrayList<>();
    private List<String> airHoloClickWait = new ArrayList<>();
    private List<String> airHoloToStart = new ArrayList<>();
    private Vector holoOffsets;
    private boolean isCanceled;
    private boolean isClone;
    private boolean isKill;
    private boolean holoTimeToStart;
    private boolean holoTimeToStartMinusOffsets;
    private boolean usePlayerLocation;
    private boolean stopWhenEmpty;
    private boolean stopWhenEmpty_event;
    private boolean SUMMONER;
    private boolean rndItems;
    private boolean hideInCompleter;

    AirDrop(FileConfiguration fileConfiguration, File airdropFile) {
        try {
            this.airdropFile = airdropFile;
            this.fileConfiguration = fileConfiguration;
            airId = fileConfiguration.getString("air-id");
            useStaticLoc = fileConfiguration.getBoolean("use-static-loc");
            stopWhenEmpty = fileConfiguration.getBoolean("stop-when-empty");
            rndItems = fileConfiguration.getBoolean("random-slot");
            if (useStaticLoc) {
                double x = fileConfiguration.getDouble("static-location.x");
                double y = fileConfiguration.getDouble("static-location.y");
                double z = fileConfiguration.getDouble("static-location.z");
                World world1 = Bukkit.getWorld(fileConfiguration.getString("static-location.world") == null ? "world" : Objects.requireNonNull(fileConfiguration.getString("static-location.world")));
                if (world1 == null) {
                    Message.error("Ошибка мира в аирдропе " + airId + " статическая локация не загружена!");
                } else {
                    staticLocation = new Location(world1, x, y, z);
                }
            }
            chance = fileConfiguration.getInt("spawn-chance");
            flatnessCheck = fileConfiguration.getBoolean("flatness-check");
            usePreGeneratedLocations = fileConfiguration.getBoolean("use-pre-generated-locations");
            minOnlinePlayers = fileConfiguration.getInt("min-online-players");
            invName = fileConfiguration.getString("inv-name");

            AirName = fileConfiguration.getString("air-name");
            invSize = fileConfiguration.getInt("chest-inventory-size");
            world = Bukkit.getWorld(Objects.requireNonNull(fileConfiguration.getString("air-spawn-world")));
            spawnMin = fileConfiguration.getInt("air-spawn-radius-min");
            spawnMax = fileConfiguration.getInt("air-spawn-radius-max");
            airProtect = fileConfiguration.getInt("air-radius-protect");
            generatorSettings = fileConfiguration.getString("generator-settings");


            timeToStartCons = fileConfiguration.getInt("timeToStart");
            timeToStart = timeToStartCons * 60;

            searchBeforeStartCons = fileConfiguration.getInt("search-before-start");
            searchBeforeStart = searchBeforeStartCons * 60;

            timeToUnlockCons = fileConfiguration.getInt("openingTime");
            timeToOpen = timeToUnlockCons * 60;

            timeToStopCons = fileConfiguration.getInt("time-stop-event");
            timeStop = timeToStopCons * 60;

            startCountdownAfterClick = fileConfiguration.getBoolean("start-countdown-after-click");
            // countdowMenu = airdrop.getBoolean("countdown-menu");
            timeStopEventMustGo = fileConfiguration.getBoolean("time-stop-event-must-go");

            materialLocked = Material.valueOf(fileConfiguration.getString("chest-material-locked"));
            materialUnlocked = Material.valueOf(fileConfiguration.getString("chest-material-unlocked"));
            signedListener = fileConfiguration.getStringList("signed-events");
            inv = Bukkit.createInventory(null, invSize, Message.messageBuilder(invName));

            airHolo = fileConfiguration.getStringList("air-holo");
            airHoloOpen = fileConfiguration.getStringList("air-holo-open");
            airHoloClickWait = fileConfiguration.getStringList("air-holo-click-wait");
            airHoloToStart = fileConfiguration.getStringList("air-holo-to-start");
            holoOffsets = new Vector(
                    fileConfiguration.getDouble("holo-offsets.x"),
                    fileConfiguration.getDouble("holo-offsets.y"),
                    fileConfiguration.getDouble("holo-offsets.z")
            );

            if (fileConfiguration.getConfigurationSection("inv") != null) {
                for (String inv : fileConfiguration.getConfigurationSection("inv").getKeys(false)) {
                    for (String slot : fileConfiguration.getConfigurationSection("inv." + inv).getKeys(false)) {
                        List<String> chance = fileConfiguration.getConfigurationSection("inv." + inv + "." + slot).getKeys(false).stream().toList();
                        if (chance.size() == 0) {
                            Message.warning(Config.getMessage("item-error").replace("{slot}", slot));
                            continue;
                        }

                        if (chance.size() >= 2)
                            Message.warning(Config.getMessage("slot-more").replace("{slot}", slot).replace("{id}", airId).replace("{size}", String.valueOf(chance.size())));


                        ItemStack item = fileConfiguration.getItemStack("inv." + inv + "." + slot + "." + chance.get(0));
                        if (item == null) {
                            Message.warning(Config.getMessage("item-null").replace("{slot}", slot));
                            continue;
                        }

                        Items items = new Items(Integer.parseInt(slot), Integer.parseInt(chance.get(0)), item, inv);
                        List<Items> list = new ArrayList<>(ListItems.getOrDefault(inv, new ArrayList<>()));
                        list.add(items);
                        ListItems.put(inv, list);
                    }
                }
            }

            Message.logger(Config.getMessage("air-loaded").replace("{id}", airId));
        } catch (Exception e) {
            e.printStackTrace();
            Message.error("Аирдроп не загружен!");
        }
        run();
    }

    public AirDrop(String id) {
        try {
            airId = id;
            createFile();
            usePreGeneratedLocations = false;
            minOnlinePlayers = 0;
            invName = "new air";
            AirName = "new air name";
            invSize = 54;
            world = Bukkit.getWorld("world") == null ? Bukkit.getWorlds().get(0) : Bukkit.getWorld("world");
            spawnMin = -2000;
            spawnMax = 2000;
            airProtect = 15;
            timeToStartCons = 2;
            timeToStopCons = 1;
            timeToUnlockCons = 1;
            searchBeforeStartCons = 1;
            timeToStart = 2 * 60;
            searchBeforeStart = 60;
            timeToOpen = 60;
            startCountdownAfterClick = false;
            timeStopEventMustGo = false;
            timeStop = 60;
            materialLocked = Material.DIAMOND_BLOCK;
            materialUnlocked = Material.CHEST;
            signedListener = new ArrayList<>();
            flatnessCheck = false;
            useStaticLoc = false;
            staticLocation = null;
            stopWhenEmpty = false;
            rndItems = false;
            generatorSettings = "default";
            chance = 50;
            airHolo = Config.getList("air-holo");
            airHoloOpen = Config.getList("air-holo-open");
            airHoloClickWait = Config.getList("air-holo-click-wait");
            airHoloToStart = Config.getList("air-holo-to-start");
            holoOffsets = new Vector(0.5, 2.5, 0.5);

            inv = Bukkit.createInventory(null, invSize, invName);
            Message.logger(Config.getMessage("air-loaded").replace("{id}", airId));
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        save();
        run();
    }

    private void run() {
        countTheTime = !BAirDrop.instance.getConfig().getBoolean("global-time.enable");
        new BukkitRunnable() {
            @Override
            public void run() {
                locationSearch();
                synchronized (this) {
                    if (isCanceled) {
                        cancel();
                        if (EditAirMenu != null) {
                            EditAirMenu.unReg();
                            EditAirMenu.getInventory().clear();
                        }
                        if (isAirDropStarted())
                            End();
                        if (isClone) {
                            event(Events.UNLOAD, null);
                            airDrops.remove(airId);
                        }
                        return;
                    }
                    if (!airDropStarted && timeToStart <= 0) {
                        Start();
                        updateEditAirMenu("stats");
                    } else if (Bukkit.getOnlinePlayers().size() >= minOnlinePlayers && !airDropStarted && (countTheTime || SUMMONER)) {
                        timeToStart--;
                        if (holoTimeToStart) {
                            List<String> lines = new ArrayList<>(airHoloToStart);
                            lines.replaceAll(s -> replaceInternalPlaceholder(s));

                            if (!holoTimeToStartMinusOffsets) {
                                HologramManager.createHologram(lines, airLoc.clone().add(holoOffsets), airId);
                            } else {
                                HologramManager.createHologram(lines, airLoc.clone().add(holoOffsets).add(
                                        -getSettings(getGeneratorSettings(), String.format("%s.offsets.x", LocationGeneration.getWorldKeyByWorld(airLoc.getWorld()))),
                                        -getSettings(getGeneratorSettings(), String.format("%s.offsets.y", LocationGeneration.getWorldKeyByWorld(airLoc.getWorld()))),
                                        -getSettings(getGeneratorSettings(), String.format("%s.offsets.z", LocationGeneration.getWorldKeyByWorld(airLoc.getWorld())))).add(0, 1, 0), airId);
                            }
                        }

                        updateEditAirMenu("stats");
                    }

                    if (airLocked && timeToOpen <= 0) {
                        unlock();
                        updateEditAirMenu("stats");
                    } else if (airDropStarted && airLocked && (!startCountdownAfterClick || pressed)) {
                        List<String> lines = new ArrayList<>(airHolo);
                        lines.replaceAll(s -> replaceInternalPlaceholder(s));
                        HologramManager.createHologram(lines, airLoc.clone().add(holoOffsets), airId);
                        timeToOpen--;
                        updateEditAirMenu("stats");
                    } else if (startCountdownAfterClick && airLocked && airDropStarted) {
                        List<String> lines = new ArrayList<>(airHoloClickWait);
                        lines.replaceAll(s -> replaceInternalPlaceholder(s));
                        HologramManager.createHologram(lines, airLoc.clone().add(holoOffsets), airId);

                    }

                    if (timeStop <= 0) {
                        End();
                        updateEditAirMenu("stats");

                    } else if (!airLocked || timeStopEventMustGo && airDropStarted) {
                        timeStop--;
                        updateEditAirMenu("stats");
                    }
                    event(Events.TIMER, null);
                    if (isStopWhenEmpty() && isAirDropStarted()) {
                        boolean stop = false;
                        for (ItemStack itemStack : inv) {
                            if (itemStack != null) {
                                stop = false;
                                break;
                            } else stop = true;
                        }
                        if (stop) {
                            stopWhenEmpty_event = true;
                            event(Events.STOP_WHEN_EMPTY, null);
                            End();
                        }
                    }
                }
            }
        }.runTaskTimer(BAirDrop.instance, 20L, 20L);
    }

    public void cancel() {
        isCanceled = true;
    }

    public void startCommand() {
        new BukkitRunnable() {
            @Override
            public void run() {
                locationSearch();
                if (airLoc != null) {
                    Start();
                    cancel();
                    updateEditAirMenu("stats");
                }
            }
        }.runTaskTimer(BAirDrop.instance, 1L, 1L);
    }

    public void save() {
        if (isClone) return;
        fileConfiguration.set("random-slot", rndItems);
        fileConfiguration.set("generator-settings", generatorSettings);
        fileConfiguration.set("spawn-chance", chance);
        fileConfiguration.set("flatness-check", flatnessCheck);
        fileConfiguration.set("min-online-players", minOnlinePlayers);
        fileConfiguration.set("inv-name", invName);
        fileConfiguration.set("air-id", airId);
        fileConfiguration.set("air-name", AirName);
        fileConfiguration.set("chest-inventory-size", invSize);
        fileConfiguration.set("air-spawn-world", world.getName());
        fileConfiguration.set("air-spawn-radius-min", spawnMin);
        fileConfiguration.set("air-spawn-radius-max", spawnMax);
        fileConfiguration.set("air-radius-protect", airProtect);
        fileConfiguration.set("timeToStart", timeToStartCons);
        fileConfiguration.set("search-before-start", searchBeforeStartCons);
        fileConfiguration.set("openingTime", timeToUnlockCons);
        fileConfiguration.set("start-countdown-after-click", startCountdownAfterClick);
        fileConfiguration.set("time-stop-event-must-go", timeStopEventMustGo);
        fileConfiguration.set("time-stop-event", timeToStopCons);
        fileConfiguration.set("chest-material-locked", materialLocked.toString());
        fileConfiguration.set("chest-material-unlocked", materialUnlocked.toString());
        fileConfiguration.set("signed-events", signedListener);
        fileConfiguration.set("use-pre-generated-locations", usePreGeneratedLocations);
        fileConfiguration.set("use-static-loc", useStaticLoc);
        if (staticLocation != null) {
            fileConfiguration.set("static-location.x", staticLocation.getX());
            fileConfiguration.set("static-location.y", staticLocation.getY());
            fileConfiguration.set("static-location.z", staticLocation.getZ());
            fileConfiguration.set("static-location.world", staticLocation.getWorld().getName());
        }
        fileConfiguration.set("air-holo", airHolo);
        fileConfiguration.set("air-holo-open", airHoloOpen);
        fileConfiguration.set("air-holo-click-wait", airHoloClickWait);
        fileConfiguration.set("air-holo-to-start", airHoloToStart);
        fileConfiguration.set("holo-offsets.x", holoOffsets.getX());
        fileConfiguration.set("holo-offsets.y", holoOffsets.getY());
        fileConfiguration.set("holo-offsets.z", holoOffsets.getZ());

        if (!getListItems().isEmpty()) {
            fileConfiguration.set("inv", null);
            for (String invName : getListItems().keySet()) {
                //    Message.debug(invName);
                for (Items item : getListItems().get(invName)) {
                    //    Message.debug("inv." + invName + "." + item.getSlot() + "." + item.getChance());
                    fileConfiguration.set("inv." + invName + "." + item.getSlot() + "." + item.getChance(), item.getItem());
                }
            }
        }
        try {
            fileConfiguration.save(airdropFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Start() {

        if (ListItems.isEmpty()) {
            Message.error(Config.getMessage("items-is-empty"));
            inv.setItem(0, new ItemStack(Material.DIRT));
        }

        if (airLoc == null) {
            if (staticLocation == null) {
                Message.error(Config.getMessage("loc-is-null"));
                End();
                return;
            } else airLoc = staticLocation.clone();

        }
        RegionManager.SetRegion(this);
        timeToStart = 0;
        futureLocation = null;
        stopWhenEmpty_event = false;
        event(Events.START_EVENT, null);

        try {
            airLoc.getBlock().setType(materialLocked);
            if (materialLocked == Material.RESPAWN_ANCHOR) {
                RespawnAnchor ra = (RespawnAnchor) airLoc.getBlock().getBlockData();
                ra.setCharges(4);
                airLoc.getBlock().setBlockData(ra);
            } else if (materialUnlocked == Material.BARREL) {
                BlockState barrelState = airLoc.getBlock().getState();
                barrelState.setType(Material.BARREL);
                Directional directionalData = (Directional) Material.BARREL.createBlockData();
                directionalData.setFacing(BlockFace.UP);
                barrelState.setBlockData(directionalData);
                barrelState.update(true);
            }


        } catch (IllegalArgumentException e) {
            Message.error(String.format(Config.getMessage("material-error"), materialLocked));
            airLoc.getBlock().setType(Material.DIRT);
        }


        if (ListItems.size() == 1) {//old system
            String key = null;
            for (String str : ListItems.keySet()) {
                key = str;
                break;
            }
            if (key != null)
                for (Items items : ListItems.get(key)) {
                    if (ThreadLocalRandom.current().nextInt(0, 100) <= items.getChance()) {
                        if (rndItems)
                            inv.setItem(getEmptyRandomSlot(), items.getItem());
                        else
                            inv.setItem(items.getSlot(), items.getItem());
                    }
                }
        } else {//new system
            List<Items> list = new ArrayList<>();
            for (List<Items> items : ListItems.values()) {
                list.addAll(items);
            }
            for (int x = 0; x < inv.getSize(); x++) {
                if (list.isEmpty()) break;
                Items items1 = list.get(ThreadLocalRandom.current().nextInt(list.size()));
                ItemStack itemStack = items1.getItem();
                if (ThreadLocalRandom.current().nextInt(0, 100) <= items1.getChance()) {
                    if(!EnchantMaterial.materialHashMap.isEmpty()){
                        for(String str : EnchantMaterial.materialHashMap.keySet()){
                            EnchantMaterial em = EnchantMaterial.materialHashMap.get(str);
                            if(em.getMaterial() == itemStack.getType()){
                                itemStack = em.enchant(itemStack);
                            }
                        }

                    }

                    if (rndItems) {
                        inv.setItem(getEmptyRandomSlot(), itemStack);
                    }
                    else {
                        inv.setItem(x, itemStack);
                    }
                }
                list.remove(items1);
            }
        }
        airDropStarted = true;
        updateEditAirMenu("stats");

    }

    private int getEmptyRandomSlot() {
        int next = 0;
        while (next <= 200) {
            int slot = ThreadLocalRandom.current().nextInt(inv.getSize());
            if (inv.getItem(slot) == null) {
                return slot;
            }
            next++;
        }
        return 0;
    }


    public File getAirdropFile() {
        return airdropFile;
    }

    public void unlock() {
        airLocked = false;
        timeToOpen = 0;
        if (airLoc == null) {
            Message.error(Config.getMessage("spawn-error"));
            return;
        }
        try {
            airLoc.getBlock().setType(materialUnlocked);
            if (materialUnlocked == Material.RESPAWN_ANCHOR) {
                RespawnAnchor ra = (RespawnAnchor) airLoc.getBlock().getBlockData();
                ra.setCharges(4);
                airLoc.getBlock().setBlockData(ra);
            } else if (materialUnlocked == Material.BARREL) {
                BlockState barrelState = airLoc.getBlock().getState();
                barrelState.setType(Material.BARREL);
                Directional directionalData = (Directional) Material.BARREL.createBlockData();
                directionalData.setFacing(BlockFace.UP);
                barrelState.setBlockData(directionalData);
                barrelState.update(true);
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Message.error(String.format(Config.getMessage("material-error"), materialUnlocked));
            airLoc.getBlock().setType(Material.DIRT);
        }

        List<String> lines = new ArrayList<>(airHoloOpen);
        lines.replaceAll(this::replaceInternalPlaceholder);
        HologramManager.createHologram(lines, airLoc.clone().add(holoOffsets), airId);
        event(Events.UNLOCK_EVENT, null);
    }

    public void End() {
        Message.debug("End()");
        event(Events.END_EVENT, null);
        if (airLoc != null)
            airLoc.getBlock().setType(Material.AIR);
        RegionManager.RemoveRegion(this);
        airLoc = null;
        inv.clear();
        List<HumanEntity> list = new ArrayList<>(inv.getViewers());
        for (HumanEntity he : list) {
            if (he instanceof Player pl) {
                pl.closeInventory();
            }
        }
        itWasOpen = false;
        airDropStarted = false;
        pressed = false;
        timeToStart = timeToStartCons * 60;
        searchBeforeStart = searchBeforeStartCons * 60;
        timeToOpen = timeToUnlockCons * 60;
        timeStop = timeToStopCons * 60;
        airLocked = true;
        HologramManager.remove(airId);
        updateEditAirMenu("stats");
        attemptsToPick = 0;
        if (isKill) isCanceled = true;
        setUsePlayerLocation(false);
        SUMMONER = false;
    }

    private void locationSearch() {
        if (futureLocation == null) {
            if (usePreGeneratedLocations)
                futureLocation = LocationGeneration.getPreLocation(this);
            else
                futureLocation = LocationGeneration.getLocation(this, false);
        }
        if (futureLocation != null && !airDropStarted)
            airLoc = futureLocation;
    }

    public static String getHash() {
        try {
            URLConnection conn = getUrl(getCheckUrl() + instance.getDescription().getVersion()).openConnection();
            conn.setReadTimeout(5000);
            conn.addRequestProperty("User-Agent", "BAirDrop Hash Checker");
            conn.setDoOutput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = reader.readLine();
            return response;
        } catch (Exception e) {
            return "none";
        }
    }
    public static URL getUrl(String url) throws MalformedURLException {
        return new URL(url);
    }

    public String replaceInternalPlaceholder(String str) {
        if (!str.contains("{"))
            return str;
        StringBuilder sb = new StringBuilder(str);

        if (str.contains("{!")) {
            if (sb.indexOf("{!clone}") != -1)
                sb.replace(sb.indexOf("{!clone}"), sb.indexOf("{!clone}") + 8, String.valueOf(!isClone()));
            if (sb.indexOf("{!airdrop-is-open}") != -1)
                sb.replace(sb.indexOf("{!airdrop-is-open}"), sb.indexOf("{!airdrop-is-open}") + 18, String.valueOf(airLocked));
            if (sb.indexOf("{!airdrop-is-start}") != -1)
                sb.replace(sb.indexOf("{!airdrop-is-start}"), sb.indexOf("{!airdrop-is-start}") + 19, String.valueOf(!airDropStarted));
            if (sb.indexOf("{!it-was-open}") != -1)
                sb.replace(sb.indexOf("{!it-was-open}"), sb.indexOf("{!it-was-open}") + 14, String.valueOf(!itWasOpen));
            if (sb.indexOf("{!use-pre-generated-locations}") != -1)
                sb.replace(sb.indexOf("{!use-pre-generated-locations}"), sb.indexOf("{!use-pre-generated-locations}") + 30, String.valueOf(!usePreGeneratedLocations));
            if (sb.indexOf("{!time-stop-event-must-go}") != -1)
                sb.replace(sb.indexOf("{!time-stop-event-must-go}"), sb.indexOf("{!time-stop-event-must-go}") + 26, String.valueOf(!timeStopEventMustGo));
            if (sb.indexOf("{!use-static-loc}") != -1)
                sb.replace(sb.indexOf("{!use-static-loc}"), sb.indexOf("{!use-static-loc}") + 17, String.valueOf(!useStaticLoc));
            if (sb.indexOf("{!flatness-check}") != -1)
                sb.replace(sb.indexOf("{!flatness-check}"), sb.indexOf("{!flatness-check}") + 17, String.valueOf(!flatnessCheck));
        }
        if (sb.indexOf("{time-to-open}") != -1)
            sb.replace(sb.indexOf("{time-to-open}"), sb.indexOf("{time-to-open}") + 14, String.valueOf(timeToOpen));
        if (sb.indexOf("{time-to-start}") != -1)
            sb.replace(sb.indexOf("{time-to-start}"), sb.indexOf("{time-to-start}") + 15, String.valueOf(timeToStart));
        if (sb.indexOf("{time-to-end}") != -1)
            sb.replace(sb.indexOf("{time-to-end}"), sb.indexOf("{time-to-end}") + 13, String.valueOf(timeStop));
        if (sb.indexOf("{time-to-open-format}") != -1)
            sb.replace(sb.indexOf("{time-to-open-format}"), sb.indexOf("{time-to-open-format}") + 21, AirManager.getFormat(timeToOpen));
        if (sb.indexOf("{time-to-start-format}") != -1)
            sb.replace(sb.indexOf("{time-to-start-format}"), sb.indexOf("{time-to-start-format}") + 22, AirManager.getFormat(timeToStart));
        if (sb.indexOf("{time-to-end-format}") != -1)
            sb.replace(sb.indexOf("{time-to-end-format}"), sb.indexOf("{time-to-end-format}") + 20, AirManager.getFormat(timeStop));
        if (sb.indexOf("{rnd-1}") != -1)
            sb.replace(sb.indexOf("{rnd-1}"), sb.indexOf("{rnd-1}") + 7, String.valueOf(ThreadLocalRandom.current().nextInt(0, 1)));
        if (sb.indexOf("{rnd-10}") != -1)
            sb.replace(sb.indexOf("{rnd-10}"), sb.indexOf("{rnd-10}") + 8, String.valueOf(ThreadLocalRandom.current().nextInt(0, 10)));
        if (sb.indexOf("{rnd-50}") != -1)
            sb.replace(sb.indexOf("{rnd-50}"), sb.indexOf("{rnd-50}") + 8, String.valueOf(ThreadLocalRandom.current().nextInt(0, 50)));
        if (sb.indexOf("{rnd-100}") != -1)
            sb.replace(sb.indexOf("{rnd-100}"), sb.indexOf("{rnd-100}") + 9, String.valueOf(ThreadLocalRandom.current().nextInt(0, 100)));
        if (sb.indexOf("{airdrop-is-open}") != -1)
            sb.replace(sb.indexOf("{airdrop-is-open}"), sb.indexOf("{airdrop-is-open}") + 17, String.valueOf(!airLocked));
        if (sb.indexOf("{airdrop-is-start}") != -1)
            sb.replace(sb.indexOf("{airdrop-is-start}"), sb.indexOf("{airdrop-is-start}") + 18, String.valueOf(airDropStarted));
        if (sb.indexOf("{it-was-open}") != -1)
            sb.replace(sb.indexOf("{it-was-open}"), sb.indexOf("{it-was-open}") + 13, String.valueOf(itWasOpen));
        if (sb.indexOf("{use-pre-generated-locations}") != -1)
            sb.replace(sb.indexOf("{use-pre-generated-locations}"), sb.indexOf("{use-pre-generated-locations}") + 29, String.valueOf(usePreGeneratedLocations));
        if (sb.indexOf("{time-stop-event-must-go}") != -1)
            sb.replace(sb.indexOf("{time-stop-event-must-go}"), sb.indexOf("{time-stop-event-must-go}") + 25, String.valueOf(timeStopEventMustGo));
        if (sb.indexOf("{use-static-loc}") != -1)
            sb.replace(sb.indexOf("{use-static-loc}"), sb.indexOf("{use-static-loc}") + 16, String.valueOf(useStaticLoc));
        if (sb.indexOf("{flatness-check}") != -1)
            sb.replace(sb.indexOf("{flatness-check}"), sb.indexOf("{flatness-check}") + 16, String.valueOf(flatnessCheck));
        if (sb.indexOf("{summoner}") != -1)
            sb.replace(sb.indexOf("{summoner}"), sb.indexOf("{summoner}") + 10, String.valueOf(SUMMONER));
        if (sb.indexOf("{id}") != -1)
            sb.replace(sb.indexOf("{id}"), sb.indexOf("{id}") + 4, airId);
        if (sb.indexOf("{world}") != -1)
            sb.replace(sb.indexOf("{world}"), sb.indexOf("{world}") + 7, world.getName());
        if (sb.indexOf("{air-name}") != -1)
            sb.replace(sb.indexOf("{air-name}"), sb.indexOf("{air-name}") + 10, getAirName());
        if (sb.indexOf("{inv-name}") != -1)
            sb.replace(sb.indexOf("{inv-name}"), sb.indexOf("{inv-name}") + 10, getInvName());
        if (sb.indexOf("{spawn-min}") != -1)
            sb.replace(sb.indexOf("{spawn-min}"), sb.indexOf("{spawn-min}") + 11, String.valueOf(getSpawnMin()));
        if (sb.indexOf("{spawn-max}") != -1)
            sb.replace(sb.indexOf("{spawn-max}"), sb.indexOf("{spawn-max}") + 11, String.valueOf(getSpawnMax()));
        if (sb.indexOf("{air-protect}") != -1)
            sb.replace(sb.indexOf("{air-protect}"), sb.indexOf("{air-protect}") + 13, String.valueOf(getAirProtect()));
        if (sb.indexOf("{search-before-start}") != -1)
            sb.replace(sb.indexOf("{search-before-start}"), sb.indexOf("{search-before-start}") + 21, String.valueOf(getSearchBeforeStart()));
        if (sb.indexOf("{min-online-players}") != -1)
            sb.replace(sb.indexOf("{min-online-players}"), sb.indexOf("{min-online-players}") + 20, String.valueOf(getMinOnlinePlayers()));
        if (sb.indexOf("{material-locked}") != -1)
            sb.replace(sb.indexOf("{material-locked}"), sb.indexOf("{material-locked}") + 17, String.valueOf(materialLocked));
        if (sb.indexOf("{material-unlocked}") != -1)
            sb.replace(sb.indexOf("{material-unlocked}"), sb.indexOf("{material-unlocked}") + 19, String.valueOf(materialUnlocked));
        if (sb.indexOf("{world-loc}") != -1)
            sb.replace(sb.indexOf("{world-loc}"), sb.indexOf("{world-loc}") + 11, String.valueOf(GeneratorLoc.locations.getOrDefault(world.getName(), new ArrayList<>()).size()));
        if (sb.indexOf("{start-countdown-after-click}") != -1)
            sb.replace(sb.indexOf("{start-countdown-after-click}"), sb.indexOf("{start-countdown-after-click}") + 29, String.valueOf(isStartCountdownAfterClick()));
        if (sb.indexOf("{time-to-start-cons}") != -1)
            sb.replace(sb.indexOf("{time-to-start-cons}"), sb.indexOf("{time-to-start-cons}") + 20, String.valueOf(timeToStartCons));
        if (sb.indexOf("{search-before-start-cons}") != -1)
            sb.replace(sb.indexOf("{search-before-start-cons}"), sb.indexOf("{search-before-start-cons}") + 26, String.valueOf(searchBeforeStartCons));
        if (sb.indexOf("{time-to-open-cons}") != -1)
            sb.replace(sb.indexOf("{time-to-open-cons}"), sb.indexOf("{time-to-open-cons}") + 19, String.valueOf(timeToUnlockCons));

        if (sb.indexOf("{time-to-end-cons}") != -1)
            sb.replace(sb.indexOf("{time-to-end-cons}"), sb.indexOf("{time-to-end-cons}") + 18, String.valueOf(timeToStopCons));

        if (sb.indexOf("{clone}") != -1)
            sb.replace(sb.indexOf("{clone}"), sb.indexOf("{clone}") + 7, String.valueOf(isClone()));
        if (sb.indexOf("{stopWhenEmpty}") != -1)
            sb.replace(sb.indexOf("{stopWhenEmpty}"), sb.indexOf("{stopWhenEmpty}") + 15, String.valueOf(stopWhenEmpty_event));
        if (sb.indexOf("{use-player-location}") != -1)
            sb.replace(sb.indexOf("{use-player-location}"), sb.indexOf("{use-player-location}") + 21, String.valueOf(isUsePlayerLocation()));
        if (sb.indexOf("{global-timer}") != -1)
            sb.replace(sb.indexOf("{global-timer}"), sb.indexOf("{global-timer}") + 14, BAirDrop.globalTimer != null ? BAirDrop.globalTimer.getTimeToStart() + "" : "-1");
        if (staticLocation == null) {
            if (sb.indexOf("{stat-world}") != -1)
                sb.replace(sb.indexOf("{stat-world}"), sb.indexOf("{stat-world}") + 12, "?");
            if (sb.indexOf("{stat-x}") != -1)
                sb.replace(sb.indexOf("{stat-x}"), sb.indexOf("{stat-x}") + 8, "?");
            if (sb.indexOf("{stat-y}") != -1)
                sb.replace(sb.indexOf("{stat-y}"), sb.indexOf("{stat-y}") + 8, "?");
            if (sb.indexOf("{stat-z}") != -1)
                sb.replace(sb.indexOf("{stat-z}"), sb.indexOf("{stat-z}") + 8, "?");
        } else {
            if (sb.indexOf("{stat-world}") != -1)
                sb.replace(sb.indexOf("{stat-world}"), sb.indexOf("{stat-world}") + 12, staticLocation.getWorld().getName());
            if (sb.indexOf("{stat-x}") != -1)
                sb.replace(sb.indexOf("{stat-x}"), sb.indexOf("{stat-x}") + 8, (staticLocation.getX() + "").replace(".0", ""));
            if (sb.indexOf("{stat-y}") != -1)
                sb.replace(sb.indexOf("{stat-y}"), sb.indexOf("{stat-y}") + 8, (staticLocation.getY() + "").replace(".0", ""));
            if (sb.indexOf("{stat-z}") != -1)
                sb.replace(sb.indexOf("{stat-z}"), sb.indexOf("{stat-z}") + 8, (staticLocation.getZ() + "").replace(".0", ""));
        }
        if (airLoc == null) {
            if (futureLocation == null) {
                if (sb.indexOf("{x}") != -1)
                    sb.replace(sb.indexOf("{x}"), sb.indexOf("{x}") + 3, "?");
                if (sb.indexOf("{y}") != -1)
                    sb.replace(sb.indexOf("{y}"), sb.indexOf("{y}") + 3, "?");
                if (sb.indexOf("{z}") != -1)
                    sb.replace(sb.indexOf("{z}"), sb.indexOf("{z}") + 3, "?");
                if (sb.indexOf("{biome}") != -1)
                    sb.replace(sb.indexOf("{biome}"), sb.indexOf("{biome}") + 7, "NONE");
                if (sb.indexOf("{GET_BLOCK_MATERIAL}") != -1)
                    sb.replace(sb.indexOf("{GET_BLOCK_MATERIAL}"), sb.indexOf("{GET_BLOCK_MATERIAL}") + 20, "AIR");
            } else {
                if (sb.indexOf("{x}") != -1)
                    sb.replace(sb.indexOf("{x}"), sb.indexOf("{x}") + 3, (futureLocation.getX() + "").replace(".0", ""));
                if (sb.indexOf("{y}") != -1)
                    sb.replace(sb.indexOf("{y}"), sb.indexOf("{y}") + 3, (futureLocation.getY() + "").replace(".0", ""));
                if (sb.indexOf("{z}") != -1)
                    sb.replace(sb.indexOf("{z}"), sb.indexOf("{z}") + 3, (futureLocation.getZ() + "").replace(".0", ""));
                if (sb.indexOf("{biome}") != -1)
                    sb.replace(sb.indexOf("{biome}"), sb.indexOf("{biome}") + 7, LocationGeneration.getBiome(futureLocation));
                if (sb.indexOf("{GET_BLOCK_MATERIAL}") != -1)
                    sb.replace(sb.indexOf("{GET_BLOCK_MATERIAL}"), sb.indexOf("{GET_BLOCK_MATERIAL}") + 20, String.valueOf(LocationGeneration.getBlock(futureLocation, this).getType()));
            }
        } else {
            if (sb.indexOf("{biome}") != -1)
                sb.replace(sb.indexOf("{biome}"), sb.indexOf("{biome}") + 7, LocationGeneration.getBiome(airLoc));
            if (sb.indexOf("{x}") != -1)
                sb.replace(sb.indexOf("{x}"), sb.indexOf("{x}") + 3, (airLoc.getX() + "").replace(".0", ""));
            if (sb.indexOf("{y}") != -1)
                sb.replace(sb.indexOf("{y}"), sb.indexOf("{y}") + 3, (airLoc.getY() + "").replace(".0", ""));
            if (sb.indexOf("{z}") != -1)
                sb.replace(sb.indexOf("{z}"), sb.indexOf("{z}") + 3, (airLoc.getZ() + "").replace(".0", ""));
            if (sb.indexOf("{GET_BLOCK_MATERIAL}") != -1)
                sb.replace(sb.indexOf("{GET_BLOCK_MATERIAL}"), sb.indexOf("{GET_BLOCK_MATERIAL}") + 20, String.valueOf(LocationGeneration.getBlock(airLoc, this).getType()));
        }
        return sb.toString();
    }

    public void event(Events event, @Nullable Player pl) {

        for (String str : signedListener) {
            if (BAirDrop.internalListeners.containsKey(str)) {
                if (BAirDrop.internalListeners.get(str).getEvent() == event)
                    BAirDrop.internalListeners.get(str).execute(pl, this, false, event);
            }
        }
    }

    public void callListener(String listener, @Nullable Player player, Events events) {
        try {
            if (!BAirDrop.internalListeners.containsKey(listener)) {
                Message.error("Несуществующий слушатель! " + listener);
                return;
            }

            BAirDrop.internalListeners.get(listener).execute(player, this, false, events);
        } catch (StackOverflowError e) {
            Message.error("Было вызвано слишком много слушателей!");
        }

    }

    public String getInvName() {
        return invName;
    }

    public void setInvName(String invName) {
        this.invName = invName;

    }

    public void updateInvName() {
        inv = Bukkit.createInventory(null, invSize, Message.messageBuilder(invName));
    }

    public String getAirName() {
        return AirName;
    }

    public void setAirName(String airName) {
        AirName = airName;
    }

    public int getInvSize() {
        return invSize;
    }

    public void setInvSize(int invSize) {
        this.invSize = invSize;
    }

    @NotNull
    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getSpawnMin() {
        return spawnMin;
    }

    public void setSpawnMin(int spawnMin) {
        this.spawnMin = spawnMin;
    }

    public int getSpawnMax() {
        return spawnMax;
    }

    public void setSpawnMax(int spawnMax) {
        this.spawnMax = spawnMax;
    }

    public int getAirProtect() {
        return airProtect;
    }

    public void setAirProtect(int airProtect) {
        this.airProtect = airProtect;
    }

    public int getTimeToStart() {
        return timeToStart;
    }

    public void setTimeToStart(int timeToStart) {
        this.timeToStart = timeToStart;
    }

    public int getSearchBeforeStart() {
        return searchBeforeStart;
    }

    public void setSearchBeforeStart(int searchBeforeStart) {
        this.searchBeforeStart = searchBeforeStart;
    }

    public int getTimeToOpen() {
        return timeToOpen;
    }

    public void setTimeToOpen(int timeToOpen) {
        this.timeToOpen = timeToOpen;
    }

    public boolean isStartCountdownAfterClick() {
        return startCountdownAfterClick;
    }

    public void setStartCountdownAfterClick(boolean startCountdownAfterClick) {
        this.startCountdownAfterClick = startCountdownAfterClick;
    }


    public boolean isTimeStopEventMustGo() {
        return timeStopEventMustGo;
    }

    public void setTimeStopEventMustGo(boolean timeStopEventMustGo) {
        this.timeStopEventMustGo = timeStopEventMustGo;
    }

    public int getTimeStop() {
        return timeStop;
    }

    public void setTimeStop(int timeStop) {
        this.timeStop = timeStop;
    }

    public Material getMaterialLocked() {
        return materialLocked;
    }

    public void setMaterialLocked(Material materialLocked) {
        this.materialLocked = materialLocked;
    }

    public Material getMaterialUnlocked() {
        return materialUnlocked;
    }

    public void setMaterialUnlocked(Material materialUnlocked) {
        this.materialUnlocked = materialUnlocked;
    }

    public boolean isAirLocked() {
        return airLocked;
    }

    public void setAirLocked(boolean airLocked) {
        this.airLocked = airLocked;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    @Nullable
    public Location getAirLoc() {
        return airLoc;
    }

    public void setAirLoc(Location airLoc) {
        this.airLoc = airLoc;
    }

    @Nullable
    public Location getFutureLocation() {
        return futureLocation;
    }

    public void setFutureLocation(Location futureLocation) {
        this.futureLocation = futureLocation;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public void setFileConfiguration(FileConfiguration fileConfiguration) {
        this.fileConfiguration = fileConfiguration;
    }

    public int getMinOnlinePlayers() {
        return minOnlinePlayers;
    }

    public void setMinOnlinePlayers(int minOnlinePlayers) {
        this.minOnlinePlayers = minOnlinePlayers;
    }


    public String getAirId() {
        return airId;
    }

    public boolean isItWasOpen() {
        return itWasOpen;
    }

    public void setItWasOpen(boolean itWasOpen) {
        this.itWasOpen = itWasOpen;
    }

    public boolean isAirDropStarted() {
        return airDropStarted;
    }

    public void getEditorItemsInventory(Inventory inv, String invName) {
        for (Items items : getListItems().getOrDefault(invName, new ArrayList<>())) {
            ItemStack itemStack = items.getItem();

            ItemMeta im = itemStack.getItemMeta();

            im.getPersistentDataContainer().set(NamespacedKey.fromString("chance"), PersistentDataType.INTEGER, items.getChance());

            itemStack.setItemMeta(im);

            inv.setItem(items.getSlot(), itemStack);
        }
    }

    public org.by1337.bairdrop.menu.EditAirMenu getEditAirMenu() {
        return EditAirMenu;
    }

    public void setEditAirMenu(org.by1337.bairdrop.menu.EditAirMenu editAirMenu) {
        EditAirMenu = editAirMenu;
    }

    private void updateEditAirMenu() {
        if (EditAirMenu != null)
            EditAirMenu.menuGenerate();
    }

    private void updateEditAirMenu(String tag) {
        if (EditAirMenu != null)
            EditAirMenu.menuGenerate(tag);
    }

    public List<String> getSignedListener() {
        return signedListener;
    }


    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public boolean isUsePreGeneratedLocations() {
        return usePreGeneratedLocations;
    }

    public void setUsePreGeneratedLocations(boolean usePreGeneratedLocations) {
        this.usePreGeneratedLocations = usePreGeneratedLocations;
    }

    public boolean isFlatnessCheck() {
        return flatnessCheck;
    }

    public void setFlatnessCheck(boolean flatnessCheck) {
        this.flatnessCheck = flatnessCheck;
    }

    public int getTimeToStartCons() {
        return timeToStartCons;
    }

    public int getTimeToStopCons() {
        return timeToStopCons;
    }

    public int getTimeToUnlockCons() {
        return timeToUnlockCons;
    }

    public int getSearchBeforeStartCons() {
        return searchBeforeStartCons;
    }

    public void setTimeToStartCons(int timeToStartCons) {
        this.timeToStartCons = timeToStartCons;
    }

    public void setTimeToStopCons(int timeToStopCons) {
        this.timeToStopCons = timeToStopCons;
    }

    public void setTimeToUnlockCons(int timeToUnlockCons) {
        this.timeToUnlockCons = timeToUnlockCons;
    }

    public void setSearchBeforeStartCons(int searchBeforeStartCons) {
        this.searchBeforeStartCons = searchBeforeStartCons;
    }

    public void addEffectAndStart(String type, String id) {
        IEffect ie = EffectFactory.getEffect(type);
        if (ie == null) {
            Message.warning(String.format(Config.getMessage("unknown-effect"), type));
            return;
        }
        if (isEffectStarted(id)) {
            Message.warning(String.format(Config.getMessage("there-is-already-an-effect"), type));
            return;
        }
        ie.Start(this);
        activeEffects.put(id, ie);
    }

    public boolean isEffectStarted(String id) {
        return activeEffects.containsKey(id);
    }

    public void StopEffect(String id) {
        if (!activeEffects.containsKey(id)) {
            Message.warning(String.format(Config.getMessage("effect-not-stated"), id));
            return;
        }
        IEffect ie = activeEffects.get(id);
        ie.End();
        activeEffects.remove(id);
    }

    public void StopAllEffects() {
        for (IEffect ie : activeEffects.values())
            ie.End();
        activeEffects.clear();
    }

    public Location getStaticLocation() {
        return staticLocation;
    }

    public void setStaticLocation(Location staticLocation) {
        this.staticLocation = staticLocation;
    }

    public boolean isUseStaticLoc() {
        return useStaticLoc;
    }

    public void setUseStaticLoc(boolean useStaticLoc) {
        this.useStaticLoc = useStaticLoc;
    }

    public int getAttemptsToPick() {
        return attemptsToPick;
    }

    public void setAttemptsToPick(int attemptsToPick) {
        this.attemptsToPick = attemptsToPick;
    }

    public int getChance() {
        return chance;
    }

    public void setCountTheTime(boolean countTheTime) {
        this.countTheTime = countTheTime;
    }

    public boolean isCountTheTime() {
        return countTheTime;
    }

    @Nullable
    public EditSession getEditSession() {
        return editSession;
    }

    public void schematicsPaste(String name) {
        SchematicsManager.PasteSchematics(name, this);
    }

    /**
     * убирает поставленную схематику, если такая есть
     */
    public void schematicsRemoveAll() {
        if (editSession != null) {
            EditSession newEditSession = WorldEdit.getInstance().newEditSession(editSession.getWorld());
           // EditSession newEditSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(editSession.getWorld(), -1, null, null);
            editSession.undo(newEditSession);
            editSession.close();
            editSession = null;
        }
    }

    public String getGeneratorSettings() {
        return generatorSettings == null ? "default" : generatorSettings;
    }

    public void setEditSession(EditSession editSession) {
        this.editSession = editSession;
    }

    /**
     * создаёт клон себя, устанавливает ему id и возвращает его
     */
    public AirDrop clone(String id) {
        AirDrop air = new AirDrop(fileConfiguration, airdropFile);
        air.setAirId(id);

        return air;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public boolean isClone() {
        return isClone;
    }

    public void setClone(boolean clone) {
        isClone = clone;
    }

    public boolean isKill() {
        return isKill;
    }

    public void setKill(boolean kill) {
        isKill = kill;
    }

    private void setAirId(String airId) {
        this.airId = airId;
    }

    /**
     * создаёт файл аирдропа
     */
    public void createFile() {
        File air = new File(instance.getDataFolder() + File.separator + "airdrops" + File.separator + getAirId() + ".yml");
        this.airdropFile = air;
        this.fileConfiguration = YamlConfiguration.loadConfiguration(air);
    }

    /**
     * Удаляет файл аирдропа и выгружает его из памяти
     */
    public boolean Delete() {
        File air = new File(instance.getDataFolder() + File.separator + "airdrops" + File.separator + getAirId() + ".yml");
        boolean isDelete = air.delete();
        if (isDelete) {
            End();
            event(Events.UNLOAD, null);
            airDrops.remove(getAirId());
        }

        return isDelete;
    }

    public void setRg() {
        RegionManager.SetRegion(this);
    }

    public boolean isHoloTimeToStart() {
        return holoTimeToStart;
    }

    public void setHoloTimeToStart(boolean holoTimeToStart) {
        this.holoTimeToStart = holoTimeToStart;
    }

    public boolean isUsePlayerLocation() {
        return usePlayerLocation;
    }

    public void setUsePlayerLocation(boolean usePlayerLocation) {
        this.usePlayerLocation = usePlayerLocation;
    }

    public boolean isStopWhenEmpty() {
        return stopWhenEmpty;
    }

    public void setStopWhenEmpty(boolean stopWhenEmpty) {
        this.stopWhenEmpty = stopWhenEmpty;
    }

    public boolean isSUMMONER() {
        return SUMMONER;
    }

    public void setSUMMONER(boolean SUMMONER) {
        this.SUMMONER = SUMMONER;
    }

    public boolean isHideInCompleter() {
        return hideInCompleter;
    }

    public void setHideInCompleter(boolean hideInCompleter) {
        this.hideInCompleter = hideInCompleter;
    }

    public HashMap<String, List<Items>> getListItems() {
        return ListItems;
    }

    /**
     * при установке на true аир будет приминят оффсеты к holoTimeToStart
     */
    public void setHoloTimeToStartMinusOffsets(boolean holoTimeToStartMinusOffsets) {
        this.holoTimeToStartMinusOffsets = holoTimeToStartMinusOffsets;
    }

    /**
     * @return вернёт первую локацию которая не нулевая
     */
    @Nullable
    public Location getAnyLoc() {
        if (airLoc == null) {
            if (futureLocation == null)
                return null;
            else return futureLocation.clone();
        } else return airLoc.clone();
    }
}
