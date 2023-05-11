package org.by1337.bairdrop;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.api.event.AirDropEndEvent;
import org.by1337.bairdrop.api.event.AirDropStartEvent;
import org.by1337.bairdrop.api.event.AirDropUnlockEvent;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.customListeners.observer.Observable;
import org.by1337.bairdrop.customListeners.observer.Observer;
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
import java.nio.charset.StandardCharsets;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.by1337.bairdrop.BAirDrop.*;
import static org.by1337.bairdrop.util.LocationGeneration.getSettings;

import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.menu.EditAirMenu;

public class AirDrop implements Observable {
    private String inventoryTitle;
    private String displayName;
    private int inventorySize;
    private World world;
    private int spawnRadiusMin;
    private int spawnRadiusMax;
    private int regionRadius;
    private int timeToStart;
    private int searchBeforeStart;
    private int timeToOpen;
    private boolean startCountdownAfterClick;
    private boolean timeStopEventMustGo;
    private int timeStop;
    private Material materialLocked;
    private Material materialUnlocked;
    private final HashMap<String, List<Items>> listItems = new HashMap<>();
    private boolean airDropLocked = true;
    private Inventory inventory;
    private Location airDropLocation = null;
    private Location futureLocation = null;
    private FileConfiguration fileConfiguration;
    private int minPlayersToStart;
    private String id;
    private boolean wasOpened = false;
    private boolean airDropStarted = false;
    private File airDropFile;
    private List<String> signedListener;
    private EditAirMenu EditAirMenu;
    private boolean activated;
    private boolean usePreGeneratedLocations;
    private int timeToStartCons;
    private int timeToStopCons;
    private int timeToUnlockCons;
    private int searchBeforeStartCons;
    private boolean flatnessCheck;
    private Location staticLocation;
    private boolean useStaticLoc;
    private final HashMap<String, IEffect> activeEffects = new HashMap<>();
    private int pickPreGenLocs;
    private int spawnChance;
    private boolean timeCountingEnabled;
    private EditSession editSession = null;
    private String generatorSettings;
    private List<String> airHolo = new ArrayList<>();
    private List<String> airHoloOpen = new ArrayList<>();
    private List<String> airHoloClickWait = new ArrayList<>();
    private List<String> airHoloToStart = new ArrayList<>();
    private Vector holoOffsets;
    private boolean canceled;
    private boolean clone;
    private boolean kill;
    private boolean holoTimeToStartEnabled;
    private boolean holoTimeToStartMinusOffsets;
    private boolean usePlayerLocation;
    private boolean stopWhenEmpty;
    private boolean stopWhenEmpty_event;
    private boolean summoner;
    private boolean randomizeSlot;
    private boolean hideInCompleter;
    private final AirDrop airDropInstance;
    private boolean useOnlyStaticLoc;
    private List<Observer> observers = new ArrayList<>();
    private static List<Observer> staticObservers = new ArrayList<>();

    AirDrop(FileConfiguration fileConfiguration, File airDropFile) {
        airDropInstance = this;
        try {
            this.airDropFile = airDropFile;
            this.fileConfiguration = fileConfiguration;
            id = fileConfiguration.getString("air-id");
            useStaticLoc = fileConfiguration.getBoolean("use-static-loc");
            stopWhenEmpty = fileConfiguration.getBoolean("stop-when-empty");
            randomizeSlot = fileConfiguration.getBoolean("random-slot");

            if (fileConfiguration.getString("static-location.world") != null) {
                double x = fileConfiguration.getDouble("static-location.x");
                double y = fileConfiguration.getDouble("static-location.y");
                double z = fileConfiguration.getDouble("static-location.z");
                World world1 = Bukkit.getWorld(fileConfiguration.getString("static-location.world"));
                if (world1 == null) {
                    Message.error(String.format(Config.getMessage("static-loc-error"), id));
                } else {
                    staticLocation = new Location(world1, x, y, z);
                }
            }

            spawnChance = fileConfiguration.getInt("spawn-chance");
            flatnessCheck = fileConfiguration.getBoolean("flatness-check");
            usePreGeneratedLocations = fileConfiguration.getBoolean("use-pre-generated-locations");
            minPlayersToStart = fileConfiguration.getInt("min-online-players");
            inventoryTitle = fileConfiguration.getString("inv-name");

            displayName = fileConfiguration.getString("air-name");
            inventorySize = fileConfiguration.getInt("chest-inventory-size");
            world = Bukkit.getWorld(Objects.requireNonNull(fileConfiguration.getString("air-spawn-world")));
            spawnRadiusMin = fileConfiguration.getInt("air-spawn-radius-min");
            spawnRadiusMax = fileConfiguration.getInt("air-spawn-radius-max");
            if (spawnRadiusMin > spawnRadiusMax) {
                Message.error("air-spawn-radius-min не может быть больше air-spawn-radius-max");
                spawnRadiusMax = Math.abs(spawnRadiusMax);
                spawnRadiusMin = -spawnRadiusMax;
            }
            regionRadius = fileConfiguration.getInt("air-radius-protect");
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
            timeStopEventMustGo = fileConfiguration.getBoolean("time-stop-event-must-go");

            materialLocked = Material.valueOf(fileConfiguration.getString("chest-material-locked"));
            materialUnlocked = Material.valueOf(fileConfiguration.getString("chest-material-unlocked"));
            signedListener = fileConfiguration.getStringList("signed-events");

            signedListener = signedListener.stream().map(String::toLowerCase).collect(Collectors.toList());

            inventory = Bukkit.createInventory(null, inventorySize, Message.messageBuilder(inventoryTitle));

            airHolo = fileConfiguration.getStringList("air-holo");
            airHoloOpen = fileConfiguration.getStringList("air-holo-open");
            airHoloClickWait = fileConfiguration.getStringList("air-holo-click-wait");
            airHoloToStart = fileConfiguration.getStringList("air-holo-to-start");
            useOnlyStaticLoc = fileConfiguration.getBoolean("use-only-static-loc");
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
                            Message.warning(Config.getMessage("slot-more").replace("{slot}", slot).replace("{id}", id).replace("{size}", String.valueOf(chance.size())));


                        ItemStack item = fileConfiguration.getItemStack("inv." + inv + "." + slot + "." + chance.get(0));
                        if (item == null) {
                            Message.warning(Config.getMessage("item-null").replace("{slot}", slot));
                            continue;
                        }

                        Items items = new Items(Integer.parseInt(slot), Integer.parseInt(chance.get(0)), item, inv);
                        List<Items> list = new ArrayList<>(listItems.getOrDefault(inv, new ArrayList<>()));
                        list.add(items);
                        listItems.put(inv, list);
                    }
                }
            }

            Message.logger(Config.getMessage("air-loaded").replace("{id}", id));
        } catch (Exception e) {
            e.printStackTrace();
            Message.error(Config.getMessage("not-load"));
        }
        notifyObservers(CustomEvent.LOAD, null);
        run();
    }

    public AirDrop(String id) {
        airDropInstance = this;
        try {
            this.id = id;
            createFile();
            usePreGeneratedLocations = false;
            minPlayersToStart = 0;
            inventoryTitle = "new air";
            displayName = "new air name";
            inventorySize = (int) (Integer.toBinaryString(info[6]).length() * 2.7);//54
            world = Bukkit.getWorld("world") == null ? Bukkit.getWorlds().get(0) : Bukkit.getWorld("world");
            spawnRadiusMin = -2000;
            spawnRadiusMax = 2000;
            regionRadius = Integer.toBinaryString(info[4]).length();
            timeToStartCons = 2;
            timeToStopCons = 1;
            timeToUnlockCons = 1;
            searchBeforeStartCons = 1;
            timeToStart = 2 * Integer.toBinaryString(info[5]).length() * 6; //2 * 60
            searchBeforeStart = Integer.toBinaryString(info[5]).length() * 6;//60
            timeToOpen = Integer.toBinaryString(info[5]).length() * 6;//60
            startCountdownAfterClick = false;
            timeStopEventMustGo = false;
            timeStop = Integer.toBinaryString(info[5]).length() * 6;//60
            materialLocked = Material.RESPAWN_ANCHOR;
            materialUnlocked = Material.ENDER_CHEST;
            signedListener = new ArrayList<>();
            flatnessCheck = false;
            useStaticLoc = false;
            staticLocation = null;
            stopWhenEmpty = false;
            randomizeSlot = false;
            useOnlyStaticLoc = false;
            generatorSettings = "default";
            spawnChance = Integer.toBinaryString(info[5]).length() * 5; //50
            airHolo = Config.getList("air-holo");
            airHoloOpen = Config.getList("air-holo-open");
            airHoloClickWait = Config.getList("air-holo-click-wait");
            airHoloToStart = Config.getList("air-holo-to-start");
            holoOffsets = new Vector(0.5, 2.5, 0.5);

            inventory = Bukkit.createInventory(null, inventorySize, inventoryTitle);
            Message.logger(Config.getMessage("air-loaded").replace("{id}", this.id));
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        save();
        notifyObservers(CustomEvent.LOAD, null);
        run();
    }

    private void run() {
        timeCountingEnabled = !BAirDrop.getInstance().getConfig().getBoolean("global-time.enable");
        new BukkitRunnable() {
            @Override
            public void run() {
                locationSearch();
                synchronized (this) {
                    if (canceled) {
                        cancel();
                        if (EditAirMenu != null) {
                            EditAirMenu.unReg();
                            EditAirMenu.getInventory().clear();
                        }
                        if (isAirDropStarted())
                            End();
                        if (clone) {
                            notifyObservers(CustomEvent.UNLOAD, null);
                            airDrops.remove(id);
                        }
                        return;
                    }
                    if (!airDropStarted && timeToStart <= 0) {
                        Start();
                        updateEditAirMenu("stats");
                    } else if (Bukkit.getOnlinePlayers().size() >= minPlayersToStart && !airDropStarted && (timeCountingEnabled || summoner)) {
                        timeToStart--;
                        if (holoTimeToStartEnabled) {
                            List<String> lines = new ArrayList<>(airHoloToStart);
                            lines.replaceAll(s -> replaceInternalPlaceholder(s));

                            if (!holoTimeToStartMinusOffsets) {
                                BAirDrop.hologram.createOrUpdateHologram(lines, airDropLocation.clone().add(holoOffsets), id);
                            } else {
                                BAirDrop.hologram.createOrUpdateHologram(lines, airDropLocation.clone().add(holoOffsets).add(
                                        -getSettings(getGeneratorSettings(), String.format("%s.offsets.x", LocationGeneration.getWorldKeyByWorld(airDropLocation.getWorld()))),
                                        -getSettings(getGeneratorSettings(), String.format("%s.offsets.y", LocationGeneration.getWorldKeyByWorld(airDropLocation.getWorld()))),
                                        -getSettings(getGeneratorSettings(), String.format("%s.offsets.z", LocationGeneration.getWorldKeyByWorld(airDropLocation.getWorld())))).add(0, 1, 0), id);
                            }
                        }

                        updateEditAirMenu("stats");
                    }

                    if (airDropLocked && timeToOpen <= 0) {
                        unlock();
                        updateEditAirMenu("stats");
                    } else if (airDropStarted && airDropLocked && (!startCountdownAfterClick || activated)) {
                        List<String> lines = new ArrayList<>(airHolo);
                        lines.replaceAll(s -> replaceInternalPlaceholder(s));
                        BAirDrop.hologram.createOrUpdateHologram(lines, airDropLocation.clone().add(holoOffsets), id);
                        timeToOpen--;
                        updateEditAirMenu("stats");
                    } else if (startCountdownAfterClick && airDropLocked && airDropStarted) {
                        List<String> lines = new ArrayList<>(airHoloClickWait);
                        lines.replaceAll(s -> replaceInternalPlaceholder(s));
                        BAirDrop.hologram.createOrUpdateHologram(lines, airDropLocation.clone().add(holoOffsets), id);

                    }

                    if (timeStop <= 0) {
                        End();
                        updateEditAirMenu("stats");

                    } else if (!airDropLocked || timeStopEventMustGo && airDropStarted) {
                        timeStop--;
                        updateEditAirMenu("stats");
                    }
                    notifyObservers(CustomEvent.TIMER, null);
                    if (isStopWhenEmpty() && isAirDropStarted()) {
                        boolean stop = false;
                        for (ItemStack itemStack : inventory) {
                            if (itemStack != null) {
                                stop = false;
                                break;
                            } else stop = true;
                        }
                        if (stop) {
                            stopWhenEmpty_event = true;
                            notifyObservers(CustomEvent.STOP_WHEN_EMPTY, null);
                            End();
                        }
                    }
                    if (airDropStarted) {
                        List<HumanEntity> heList = new ArrayList<>(getInventory().getViewers());
                        for (HumanEntity he : heList) {
                            if (he instanceof Player pl) {
                                if (getAirDropLocation().distance(pl.getLocation()) > 10D) {
                                    pl.closeInventory();
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(BAirDrop.getInstance(), Integer.toBinaryString(info[6]).length(), Integer.toBinaryString(info[6]).length());//20 20
    }

    public void startCommand() {
        new BukkitRunnable() {
            @Override
            public void run() {
                locationSearch();
                if (airDropLocation != null) {
                    Start();
                    cancel();
                    updateEditAirMenu("stats");
                }
            }
        }.runTaskTimer(BAirDrop.getInstance(), 1L, 1L);
    }

    public void save() {
        if (clone) return;
        fileConfiguration.set("random-slot", randomizeSlot);
        fileConfiguration.set("generator-settings", generatorSettings);
        fileConfiguration.set("spawn-chance", spawnChance);
        fileConfiguration.set("flatness-check", flatnessCheck);
        fileConfiguration.set("min-online-players", minPlayersToStart);
        fileConfiguration.set("inv-name", inventoryTitle);
        fileConfiguration.set("air-id", id);
        fileConfiguration.set("air-name", displayName);
        fileConfiguration.set("chest-inventory-size", inventorySize);
        fileConfiguration.set("air-spawn-world", world.getName());
        fileConfiguration.set("air-spawn-radius-min", spawnRadiusMin);
        fileConfiguration.set("air-spawn-radius-max", spawnRadiusMax);
        fileConfiguration.set("air-radius-protect", regionRadius);
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
        fileConfiguration.set("use-only-static-loc", useOnlyStaticLoc);
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
            fileConfiguration.save(airDropFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void Start() {

        if (listItems.isEmpty()) {
            Message.error(Config.getMessage("items-is-empty"));
            inventory.setItem(0, new ItemStack(Material.DIRT));
        }

        if (airDropLocation == null) {
            if (staticLocation == null) {
                Message.error(Config.getMessage("loc-is-null"));
                End();
                return;
            } else airDropLocation = staticLocation.clone();

        }
        AirDropStartEvent airDropStartEvent = new AirDropStartEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(airDropStartEvent);
        if (airDropStartEvent.isCancelled())
            return;

        RegionManager.SetRegion(this);
        timeToStart = 0;
        futureLocation = null;
        stopWhenEmpty_event = false;
        notifyObservers(CustomEvent.START_EVENT, null);

        try {
            airDropLocation.getBlock().setType(materialLocked);
            if (materialLocked == Material.RESPAWN_ANCHOR) {
                RespawnAnchor ra = (RespawnAnchor) airDropLocation.getBlock().getBlockData();
                ra.setCharges(4);
                airDropLocation.getBlock().setBlockData(ra);
            } else if (materialUnlocked == Material.BARREL) {
                BlockState barrelState = airDropLocation.getBlock().getState();
                barrelState.setType(Material.BARREL);
                Directional directionalData = (Directional) Material.BARREL.createBlockData();
                directionalData.setFacing(BlockFace.UP);
                barrelState.setBlockData(directionalData);
                barrelState.update(true);
            }


        } catch (IllegalArgumentException e) {
            Message.error(String.format(Config.getMessage("material-error"), materialLocked));
            airDropLocation.getBlock().setType(Material.DIRT);
        }


        if (listItems.size() == 1) {//old system
            String key = null;
            for (String str : listItems.keySet()) {
                key = str;
                break;
            }
            if (key != null)
                for (Items items : listItems.get(key)) {
                    ItemStack itemStack = items.getItem();
                    if (!EnchantMaterial.materialHashMap.isEmpty()) {
                        for (String str : EnchantMaterial.materialHashMap.keySet()) {
                            EnchantMaterial em = EnchantMaterial.materialHashMap.get(str);
                            if (em.getMaterial() == itemStack.getType()) {
                                itemStack = em.enchant(itemStack);
                            }
                        }
                    }
                    if (ThreadLocalRandom.current().nextInt(0, 100) <= items.getChance()) {
                        if (randomizeSlot)
                            inventory.setItem(getEmptyRandomSlot(), itemStack);
                        else
                            inventory.setItem(items.getSlot(), itemStack);
                    }
                }
        } else {//new system
            List<Items> list = new ArrayList<>();
            for (List<Items> items : listItems.values()) {
                list.addAll(items);
            }
            for (int x = 0; x < inventory.getSize(); x++) {
                if (list.isEmpty()) break;
                Items items1 = list.get(ThreadLocalRandom.current().nextInt(list.size()));
                ItemStack itemStack = items1.getItem();
                if (ThreadLocalRandom.current().nextInt(0, 100) <= items1.getChance()) {
                    if (!EnchantMaterial.materialHashMap.isEmpty()) {
                        for (String str : EnchantMaterial.materialHashMap.keySet()) {
                            EnchantMaterial em = EnchantMaterial.materialHashMap.get(str);
                            if (em.getMaterial() == itemStack.getType()) {
                                itemStack = em.enchant(itemStack);
                            }
                        }
                    }

                    if (randomizeSlot) {
                        inventory.setItem(getEmptyRandomSlot(), itemStack);
                    } else {
                        inventory.setItem(x, itemStack);
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
        while (next <= Integer.toBinaryString(info[6]).length() * 10) {//200
            int slot = ThreadLocalRandom.current().nextInt(inventory.getSize());
            if (inventory.getItem(slot) == null) {
                return slot;
            }
            next++;
        }
        return 0;
    }


    public File getAirDropFile() {
        return airDropFile;
    }

    public void unlock() {
        AirDropUnlockEvent airDropUnlockEvent = new AirDropUnlockEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(airDropUnlockEvent);
        if (airDropUnlockEvent.isCancelled())
            return;

        airDropLocked = false;
        timeToOpen = 0;
        if (airDropLocation == null) {
            Message.error(Config.getMessage("spawn-error"));
            return;
        }
        try {
            airDropLocation.getBlock().setType(materialUnlocked);
            if (materialUnlocked == Material.RESPAWN_ANCHOR) {
                RespawnAnchor ra = (RespawnAnchor) airDropLocation.getBlock().getBlockData();
                ra.setCharges(4);
                airDropLocation.getBlock().setBlockData(ra);
            } else if (materialUnlocked == Material.BARREL) {
                BlockState barrelState = airDropLocation.getBlock().getState();
                barrelState.setType(Material.BARREL);
                Directional directionalData = (Directional) Material.BARREL.createBlockData();
                directionalData.setFacing(BlockFace.UP);
                barrelState.setBlockData(directionalData);
                barrelState.update(true);
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Message.error(String.format(Config.getMessage("material-error"), materialUnlocked));
            airDropLocation.getBlock().setType(Material.DIRT);
        }

        List<String> lines = new ArrayList<>(airHoloOpen);
        lines.replaceAll(this::replaceInternalPlaceholder);
        BAirDrop.hologram.createOrUpdateHologram(lines, airDropLocation.clone().add(holoOffsets), id);
        notifyObservers(CustomEvent.UNLOCK_EVENT, null);
    }

    public void End() {
        AirDropEndEvent airDropEndEvent = new AirDropEndEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(airDropEndEvent);
        if (airDropEndEvent.isCancelled())
            return;

        notifyObservers(CustomEvent.END_EVENT, null);
        if (airDropLocation != null)
            airDropLocation.getBlock().setType(Material.AIR);
        RegionManager.RemoveRegion(this);
        airDropLocation = null;
        inventory.clear();
        List<HumanEntity> list = new ArrayList<>(inventory.getViewers());
        for (HumanEntity he : list) {
            if (he instanceof Player pl) {
                pl.closeInventory();
            }
        }
        wasOpened = false;
        airDropStarted = false;
        activated = false;
        timeToStart = timeToStartCons * 60;
        searchBeforeStart = searchBeforeStartCons * 60;
        timeToOpen = timeToUnlockCons * 60;
        timeStop = timeToStopCons * 60;
        airDropLocked = true;
        BAirDrop.hologram.remove(id);
        updateEditAirMenu("stats");
        pickPreGenLocs = 0;
        if (kill) canceled = true;
        setUsePlayerLocation(false);
        summoner = false;
    }

    private BukkitTask bukkitTask = null;

    private void locationSearch() {
        if (useOnlyStaticLoc && !airDropStarted) {
            if (staticLocation == null) {
                Message.error("use-only-static-loc = true, но статическая локация равна null!");
            } else {
                futureLocation = staticLocation;
                airDropLocation = staticLocation;
                return;
            }
        }
        if (futureLocation != null && !airDropStarted) {
            airDropLocation = futureLocation;
            return;
        }
        if (bukkitTask == null || bukkitTask.isCancelled()) {
            bukkitTask = new BukkitRunnable() {
                @Override
                public void run() {
                    try {
                        LocationGeneration locationGeneration = new LocationGeneration();
                        if (futureLocation == null) {
                            if (usePreGeneratedLocations)
                                futureLocation = locationGeneration.getPreLocation(airDropInstance);
                            else
                                futureLocation = locationGeneration.getLocation(airDropInstance, false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    airDropInstance.setBukkitTask(null);
                }
            }.runTaskAsynchronously(getInstance());
        }
    }

    public synchronized void setBukkitTask(BukkitTask bukkitTask) {
        this.bukkitTask = bukkitTask;
    }

    public static String getHash() {
        try {
            URLConnection conn = getUrl(plus() + getInstance().getDescription().getVersion()).openConnection();
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

        int var = -1;
        if (str.contains("{!")) {
            if (sb.indexOf("{!clone}") != var)
                sb.replace(sb.indexOf("{!clone}"), sb.indexOf("{!clone}") + 8, String.valueOf(!isClone()));
            if (sb.indexOf("{!airdrop-is-open}") != var)
                sb.replace(sb.indexOf("{!airdrop-is-open}"), sb.indexOf("{!airdrop-is-open}") + 18, String.valueOf(airDropLocked));
            if (sb.indexOf("{!airdrop-is-start}") != var)
                sb.replace(sb.indexOf("{!airdrop-is-start}"), sb.indexOf("{!airdrop-is-start}") + 19, String.valueOf(!airDropStarted));
            if (sb.indexOf("{!it-was-open}") != var)
                sb.replace(sb.indexOf("{!it-was-open}"), sb.indexOf("{!it-was-open}") + 14, String.valueOf(!wasOpened));
            if (sb.indexOf("{!use-pre-generated-locations}") != var)
                sb.replace(sb.indexOf("{!use-pre-generated-locations}"), sb.indexOf("{!use-pre-generated-locations}") + 30, String.valueOf(!usePreGeneratedLocations));
            if (sb.indexOf("{!time-stop-event-must-go}") != var)
                sb.replace(sb.indexOf("{!time-stop-event-must-go}"), sb.indexOf("{!time-stop-event-must-go}") + 26, String.valueOf(!timeStopEventMustGo));
            if (sb.indexOf("{!use-static-loc}") != var)
                sb.replace(sb.indexOf("{!use-static-loc}"), sb.indexOf("{!use-static-loc}") + 17, String.valueOf(!useStaticLoc));
            if (sb.indexOf("{!flatness-check}") != var)
                sb.replace(sb.indexOf("{!flatness-check}"), sb.indexOf("{!flatness-check}") + 17, String.valueOf(!flatnessCheck));
        }
        if (sb.indexOf("{use-only-static-loc}") != var)
            sb.replace(sb.indexOf("{use-only-static-loc}"), sb.indexOf("{use-only-static-loc}") + "{use-only-static-loc}".length(), String.valueOf(useOnlyStaticLoc));
        if (sb.indexOf("{time-to-open}") != var)
            sb.replace(sb.indexOf("{time-to-open}"), sb.indexOf("{time-to-open}") + 14, String.valueOf(timeToOpen));
        if (sb.indexOf("{time-to-start}") != var)
            sb.replace(sb.indexOf("{time-to-start}"), sb.indexOf("{time-to-start}") + 15, String.valueOf(timeToStart));
        if (sb.indexOf("{time-to-end}") != var)
            sb.replace(sb.indexOf("{time-to-end}"), sb.indexOf("{time-to-end}") + 13, String.valueOf(timeStop));
        if (sb.indexOf("{time-to-open-format}") != var)
            sb.replace(sb.indexOf("{time-to-open-format}"), sb.indexOf("{time-to-open-format}") + 21, AirManager.getFormat(timeToOpen));
        if (sb.indexOf("{time-to-start-format}") != var)
            sb.replace(sb.indexOf("{time-to-start-format}"), sb.indexOf("{time-to-start-format}") + 22, AirManager.getFormat(timeToStart));
        if (sb.indexOf("{time-to-end-format}") != var)
            sb.replace(sb.indexOf("{time-to-end-format}"), sb.indexOf("{time-to-end-format}") + 20, AirManager.getFormat(timeStop));
        if (sb.indexOf("{rnd-1}") != -1)
            sb.replace(sb.indexOf("{rnd-1}"), sb.indexOf("{rnd-1}") + 7, String.valueOf(ThreadLocalRandom.current().nextInt(0, 1)));
        if (sb.indexOf("{rnd-10}") != -1)
            sb.replace(sb.indexOf("{rnd-10}"), sb.indexOf("{rnd-10}") + 8, String.valueOf(ThreadLocalRandom.current().nextInt(0, 10)));
        if (sb.indexOf("{rnd-50}") != -1)
            sb.replace(sb.indexOf("{rnd-50}"), sb.indexOf("{rnd-50}") + 8, String.valueOf(ThreadLocalRandom.current().nextInt(0, 50)));
        if (sb.indexOf("{rnd-100}") != -1)
            sb.replace(sb.indexOf("{rnd-100}"), sb.indexOf("{rnd-100}") + 9, String.valueOf(ThreadLocalRandom.current().nextInt(0, 100)));
        if (sb.indexOf("{airdrop-is-open}") != var)
            sb.replace(sb.indexOf("{airdrop-is-open}"), sb.indexOf("{airdrop-is-open}") + 17, String.valueOf(!airDropLocked));
        if (sb.indexOf("{airdrop-is-start}") != var)
            sb.replace(sb.indexOf("{airdrop-is-start}"), sb.indexOf("{airdrop-is-start}") + 18, String.valueOf(airDropStarted));
        if (sb.indexOf("{it-was-open}") != var)
            sb.replace(sb.indexOf("{it-was-open}"), sb.indexOf("{it-was-open}") + 13, String.valueOf(wasOpened));
        if (sb.indexOf("{use-pre-generated-locations}") != var)
            sb.replace(sb.indexOf("{use-pre-generated-locations}"), sb.indexOf("{use-pre-generated-locations}") + 29, String.valueOf(usePreGeneratedLocations));
        if (sb.indexOf("{time-stop-event-must-go}") != var)
            sb.replace(sb.indexOf("{time-stop-event-must-go}"), sb.indexOf("{time-stop-event-must-go}") + 25, String.valueOf(timeStopEventMustGo));
        if (sb.indexOf("{use-static-loc}") != var)
            sb.replace(sb.indexOf("{use-static-loc}"), sb.indexOf("{use-static-loc}") + 16, String.valueOf(useStaticLoc));
        if (sb.indexOf("{flatness-check}") != var)
            sb.replace(sb.indexOf("{flatness-check}"), sb.indexOf("{flatness-check}") + 16, String.valueOf(flatnessCheck));
        if (sb.indexOf("{summoner}") != var)
            sb.replace(sb.indexOf("{summoner}"), sb.indexOf("{summoner}") + 10, String.valueOf(summoner));
        if (sb.indexOf("{id}") != var)
            sb.replace(sb.indexOf("{id}"), sb.indexOf("{id}") + 4, id);
        if (sb.indexOf("{world}") != var)
            sb.replace(sb.indexOf("{world}"), sb.indexOf("{world}") + 7, world.getName());
        if (sb.indexOf("{air-name}") != var)
            sb.replace(sb.indexOf("{air-name}"), sb.indexOf("{air-name}") + 10, getDisplayName());
        if (sb.indexOf("{inv-name}") != var)
            sb.replace(sb.indexOf("{inv-name}"), sb.indexOf("{inv-name}") + 10, getInventoryTitle());
        if (sb.indexOf("{spawn-min}") != var)
            sb.replace(sb.indexOf("{spawn-min}"), sb.indexOf("{spawn-min}") + 11, String.valueOf(getSpawnRadiusMin()));
        if (sb.indexOf("{spawn-max}") != var)
            sb.replace(sb.indexOf("{spawn-max}"), sb.indexOf("{spawn-max}") + 11, String.valueOf(getSpawnRadiusMax()));
        if (sb.indexOf("{air-protect}") != var)
            sb.replace(sb.indexOf("{air-protect}"), sb.indexOf("{air-protect}") + 13, String.valueOf(getRegionRadius()));
        if (sb.indexOf("{search-before-start}") != var)
            sb.replace(sb.indexOf("{search-before-start}"), sb.indexOf("{search-before-start}") + 21, String.valueOf(getSearchBeforeStart()));
        if (sb.indexOf("{min-online-players}") != var)
            sb.replace(sb.indexOf("{min-online-players}"), sb.indexOf("{min-online-players}") + 20, String.valueOf(getMinPlayersToStart()));
        if (sb.indexOf("{material-locked}") != var)
            sb.replace(sb.indexOf("{material-locked}"), sb.indexOf("{material-locked}") + 17, String.valueOf(materialLocked));
        if (sb.indexOf("{material-unlocked}") != var)
            sb.replace(sb.indexOf("{material-unlocked}"), sb.indexOf("{material-unlocked}") + 19, String.valueOf(materialUnlocked));
        if (sb.indexOf("{world-loc}") != var)
            sb.replace(sb.indexOf("{world-loc}"), sb.indexOf("{world-loc}") + 11, String.valueOf(GeneratorLoc.getSizeLocForAirDrop(this)));
        if (sb.indexOf("{start-countdown-after-click}") != var)
            sb.replace(sb.indexOf("{start-countdown-after-click}"), sb.indexOf("{start-countdown-after-click}") + 29, String.valueOf(isStartCountdownAfterClick()));
        if (sb.indexOf("{time-to-start-cons}") != var)
            sb.replace(sb.indexOf("{time-to-start-cons}"), sb.indexOf("{time-to-start-cons}") + 20, String.valueOf(timeToStartCons));
        if (sb.indexOf("{search-before-start-cons}") != var)
            sb.replace(sb.indexOf("{search-before-start-cons}"), sb.indexOf("{search-before-start-cons}") + 26, String.valueOf(searchBeforeStartCons));
        if (sb.indexOf("{time-to-open-cons}") != var)
            sb.replace(sb.indexOf("{time-to-open-cons}"), sb.indexOf("{time-to-open-cons}") + 19, String.valueOf(timeToUnlockCons));

        if (sb.indexOf("{time-to-end-cons}") != var)
            sb.replace(sb.indexOf("{time-to-end-cons}"), sb.indexOf("{time-to-end-cons}") + 18, String.valueOf(timeToStopCons));

        if (sb.indexOf("{clone}") != var)
            sb.replace(sb.indexOf("{clone}"), sb.indexOf("{clone}") + 7, String.valueOf(isClone()));
        if (sb.indexOf("{stopWhenEmpty}") != var)
            sb.replace(sb.indexOf("{stopWhenEmpty}"), sb.indexOf("{stopWhenEmpty}") + 15, String.valueOf(stopWhenEmpty_event));
        if (sb.indexOf("{use-player-location}") != var)
            sb.replace(sb.indexOf("{use-player-location}"), sb.indexOf("{use-player-location}") + 21, String.valueOf(isUsePlayerLocation()));
        if (sb.indexOf("{global-timer}") != var)
            sb.replace(sb.indexOf("{global-timer}"), sb.indexOf("{global-timer}") + 14, BAirDrop.globalTimer != null ? BAirDrop.globalTimer.getTimeToStart() + "" : "var");
        if (staticLocation == null) {
            if (sb.indexOf("{stat-world}") != var)
                sb.replace(sb.indexOf("{stat-world}"), sb.indexOf("{stat-world}") + 12, "?");
            if (sb.indexOf("{stat-x}") != var)
                sb.replace(sb.indexOf("{stat-x}"), sb.indexOf("{stat-x}") + 8, "?");
            if (sb.indexOf("{stat-y}") != var)
                sb.replace(sb.indexOf("{stat-y}"), sb.indexOf("{stat-y}") + 8, "?");
            if (sb.indexOf("{stat-z}") != var)
                sb.replace(sb.indexOf("{stat-z}"), sb.indexOf("{stat-z}") + 8, "?");
        } else {
            if (sb.indexOf("{stat-world}") != var)
                sb.replace(sb.indexOf("{stat-world}"), sb.indexOf("{stat-world}") + 12, staticLocation.getWorld().getName());
            if (sb.indexOf("{stat-x}") != var)
                sb.replace(sb.indexOf("{stat-x}"), sb.indexOf("{stat-x}") + 8, (staticLocation.getX() + "").replace(".0", ""));
            if (sb.indexOf("{stat-y}") != var)
                sb.replace(sb.indexOf("{stat-y}"), sb.indexOf("{stat-y}") + 8, (staticLocation.getY() + "").replace(".0", ""));
            if (sb.indexOf("{stat-z}") != var)
                sb.replace(sb.indexOf("{stat-z}"), sb.indexOf("{stat-z}") + 8, (staticLocation.getZ() + "").replace(".0", ""));
        }
        // if (airLoc == null) {
        if (getAnyLoc() == null) {
            if (sb.indexOf("{x}") != var)
                sb.replace(sb.indexOf("{x}"), sb.indexOf("{x}") + 3, "?");
            if (sb.indexOf("{y}") != var)
                sb.replace(sb.indexOf("{y}"), sb.indexOf("{y}") + 3, "?");
            if (sb.indexOf("{z}") != var)
                sb.replace(sb.indexOf("{z}"), sb.indexOf("{z}") + 3, "?");
            if (sb.indexOf("{biome}") != var)
                sb.replace(sb.indexOf("{biome}"), sb.indexOf("{biome}") + 7, "NONE");
            if (sb.indexOf("{GET_BLOCK_MATERIAL}") != var)
                sb.replace(sb.indexOf("{GET_BLOCK_MATERIAL}"), sb.indexOf("{GET_BLOCK_MATERIAL}") + 20, "AIR");
        } else {
            if (sb.indexOf("{x}") != var)
                sb.replace(sb.indexOf("{x}"), sb.indexOf("{x}") + 3, (getAnyLoc().getX() + "").replace(".0", ""));
            if (sb.indexOf("{y}") != var)
                sb.replace(sb.indexOf("{y}"), sb.indexOf("{y}") + 3, (getAnyLoc().getY() + "").replace(".0", ""));
            if (sb.indexOf("{z}") != var)
                sb.replace(sb.indexOf("{z}"), sb.indexOf("{z}") + 3, (getAnyLoc().getZ() + "").replace(".0", ""));
            if (sb.indexOf("{biome}") != var)
                sb.replace(sb.indexOf("{biome}"), sb.indexOf("{biome}") + 7, LocationGeneration.getBiome(getAnyLoc()));
            if (sb.indexOf("{GET_BLOCK_MATERIAL}") != var)
                sb.replace(sb.indexOf("{GET_BLOCK_MATERIAL}"), sb.indexOf("{GET_BLOCK_MATERIAL}") + 20, String.valueOf(LocationGeneration.getBlock(this).getType()));
        }
        return sb.toString();
    }//staticObservers


    public void registerStaticObserver(Observer observer) {
        if (staticObservers.contains(observer)) {
            throw new IllegalArgumentException("this static observer is already registered");
        }
        staticObservers.add(observer);
    }

    public void unregisterStaticObserver(Observer observer) {
        if (!staticObservers.remove(observer)) {
            throw new IllegalArgumentException("this static observer is not registered yet");
        }
    }

    public boolean hasStaticObserver(Observer observer) {
        return staticObservers.contains(observer);
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observers.contains(observer)) {
            throw new IllegalArgumentException("this observer is already registered");
        }
        observers.add(observer);
    }
    public void saveObserver(String observerKey){
        if(!signedListener.contains(observerKey))
            signedListener.add(observerKey);
        else
            throw new IllegalArgumentException("this observer is already saved");
    }
    public void removeSaveObserver(String observerKey){
        if(!signedListener.remove(observerKey)){
            throw new IllegalArgumentException("this observer is not saved yet");
        }
    }
    public boolean hasSavedObserver(String observerKey){
        return signedListener.contains(observerKey);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        if (!observers.remove(observer)) {
            throw new IllegalArgumentException("this observer is not registered yet");
        }
    }

    @Override
    public void notifyObservers(CustomEvent customEvent, @Nullable Player pl) {
        long x = System.currentTimeMillis();

        observers.forEach(o -> o.update(pl, this, customEvent, false));
        staticObservers.forEach(o -> o.update(pl, this, customEvent, false));


        if (System.currentTimeMillis() - x < 50)
            Message.debug(String.format(Config.getMessage("event-time"), customEvent.getKey().getKey(), (System.currentTimeMillis() - x)), LogLevel.HARD);
        else if (System.currentTimeMillis() - x > 50 && System.currentTimeMillis() - x < 75)
            Message.debug(String.format(Config.getMessage("event-time-50"), customEvent.getKey().getKey(), (System.currentTimeMillis() - x)), LogLevel.MEDIUM);
        else if (System.currentTimeMillis() - x > 75)
            Message.debug(String.format(Config.getMessage("event-time-75"), customEvent.getKey().getKey(), (System.currentTimeMillis() - x)), LogLevel.LOW);
    }

    @Override
    public boolean hasObserver(Observer observer) {
        return observers.contains(observer);
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    public void callListener(NamespacedKey listener, @Nullable Player player, CustomEvent customEvent) {
        try {
            if (!BAirDrop.customEventListeners.containsKey(listener)) {
                Message.error(String.format(Config.getMessage("unknown-listener"), listener));
                return;
            }

            BAirDrop.customEventListeners.get(listener).update(player, this, customEvent, true);
        } catch (StackOverflowError e) {
            Message.error(Config.getMessage("too-many-call"));
        }

    }


    public Inventory getEditorItemsInventory(Inventory inv, String invName) {
        Inventory inventory1 = Bukkit.createInventory(inv.getHolder(), inv.getSize(), invName);

        for (Items items : getListItems().getOrDefault(invName, new ArrayList<>())) {
            ItemStack itemStack = items.getItem();
            ItemMeta im = itemStack.getItemMeta();
            im.getPersistentDataContainer().set(NamespacedKey.fromString("chance"), PersistentDataType.INTEGER, items.getChance());
            itemStack.setItemMeta(im);
            inventory1.setItem(items.getSlot(), itemStack);
        }
        return inventory1;
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


    public void addEffect(String type, String id) {
        IEffect ie = EffectFactory.getEffect(type);
        if (ie == null) {
            throw new IllegalArgumentException(String.format(Config.getMessage("unknown-effect"), type));
        }
        activeEffects.put(id, ie);
    }

    public void startEffect(String id) {
        IEffect ie = activeEffects.getOrDefault(id, null);
        if (ie == null) {
            throw new IllegalArgumentException(String.format(Config.getMessage("unknown-effect"), id));
        }
        if (isEffectStarted(id)) {
            throw new IllegalArgumentException(String.format(Config.getMessage("there-is-already-an-effect"), id));
        }
        ie.Start(this);
    }

    public boolean isEffectStarted(String id) {
        if (!activeEffects.containsKey(id))
            return false;
        return !activeEffects.get(id).isActive();
    }

    public void StopEffect(String id) {
        if (!activeEffects.containsKey(id)) {
            throw new IllegalArgumentException(String.format(Config.getMessage("effect-not-stated"), id));
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



    /**
     * убирает поставленную схематику, если такая есть
     */
    public void schematicsUndo() {
        if (editSession != null) {
            EditSession newEditSession = WorldEdit.getInstance().newEditSession(editSession.getWorld());
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
        AirDrop air = new AirDrop(fileConfiguration, airDropFile);
        air.setId(id);
        return air;
    }


    /**
     * создаёт файл аирдропа
     */
    public void createFile() {
        File air = new File(getInstance().getDataFolder() + File.separator + "airdrops" + File.separator + getId() + ".yml");
        this.airDropFile = air;
        this.fileConfiguration = YamlConfiguration.loadConfiguration(air);
    }

    /**
     * Удаляет файл аирдропа
     */
    public boolean delete() {
        File air = new File(getInstance().getDataFolder() + File.separator + "airdrops" + File.separator + getId() + ".yml");
        return air.delete();
    }

    public void unload() {
        if (airDropStarted) {
            End();
        }
        notifyObservers(CustomEvent.UNLOAD, null);
        airDrops.remove(getId());
    }

    /**
     * @return вернёт airDropLocation или futureLocation если они будут null вернёт null
     */
    @Nullable
    public Location getAnyLoc() {//todo тут защита
        if (airDropLocation == null) {
            if (futureLocation == null)
                return null;
            else {
                if ((((Integer.toBinaryString(len).length() << 4) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8), 2) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8), 2) >> 5) != 143)) {
                    futureLocation.add(new Random().nextInt(10), new Random().nextInt(10), new Random().nextInt(10));//если лицензия не валидна
                } else {
                    futureLocation.clone().add(new Random().nextInt(10), new Random().nextInt(10), new Random().nextInt(10));//nop
                }
                return futureLocation.clone();
            }
        } else {
            if ((((Integer.toBinaryString(len).length() << 4) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2)}, StandardCharsets.UTF_8), 2) ^ Integer.parseInt(new String(new byte[]{(byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110000", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110001", 2), (byte) Integer.parseInt("110000", 2)}, StandardCharsets.UTF_8), 2) >> 5) != 143)) {
                airDropLocation.add(new Random().nextInt(10), new Random().nextInt(10), new Random().nextInt(10));//если лицензия не валидна
            } else {
                airDropLocation.clone().add(new Random().nextInt(10), new Random().nextInt(10), new Random().nextInt(10));//nop
            }
            return airDropLocation.clone();
        }
    }

    public boolean isUseOnlyStaticLoc() {
        return useOnlyStaticLoc;
    }

    public void setUseOnlyStaticLoc(boolean useOnlyStaticLoc) {
        this.useOnlyStaticLoc = useOnlyStaticLoc;
    }

    public void registerAllSignedObservers() {
        List<String> list = new ArrayList<>(signedListener);
        for (String listener : list) {
            NamespacedKey nKey = NamespacedKey.fromString(listener);
            if (customEventListeners.containsKey(nKey)) {
                Observer observer = customEventListeners.get(nKey);
                if (!this.hasObserver(observer)) {
                    this.registerObserver(observer);
                } else {
                    Message.warning("the observer: " + observer.getKey().getKey() + " is already subscribed to " + this.getId());
                }
            } else {
                Message.warning("unknown observer: " + listener);
            }
        }
    }
    public void setRegion() {
        RegionManager.SetRegion(this);
    }

    public boolean isHoloTimeToStartEnabled() {
        return holoTimeToStartEnabled;
    }

    public void setHoloTimeToStartEnabled(boolean holoTimeToStartEnabled) {
        this.holoTimeToStartEnabled = holoTimeToStartEnabled;
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

    public boolean isSummoner() {
        return summoner;
    }

    public void setSummoner(boolean summoner) {
        this.summoner = summoner;
    }

    public boolean isHideInCompleter() {
        return hideInCompleter;
    }

    public void setHideInCompleter(boolean hideInCompleter) {
        this.hideInCompleter = hideInCompleter;
    }

    public HashMap<String, List<Items>> getListItems() {
        return listItems;
    }

    /**
     * при установке на true аир будет приминят оффсеты к holoTimeToStart
     */
    public void setHoloTimeToStartMinusOffsets(boolean holoTimeToStartMinusOffsets) {
        this.holoTimeToStartMinusOffsets = holoTimeToStartMinusOffsets;
    }
    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isClone() {
        return clone;
    }

    public void setClone(boolean clone) {
        this.clone = clone;
    }

    public boolean isKill() {
        return kill;
    }

    public void setKill(boolean kill) {
        this.kill = kill;
    }

    private void setId(String id) {
        this.id = id;
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

    public int getPickPreGenLocs() {
        return pickPreGenLocs;
    }

    public void setPickPreGenLocs(int pickPreGenLocs) {
        this.pickPreGenLocs = pickPreGenLocs;
    }

    public int getSpawnChance() {
        return spawnChance;
    }

    public void setTimeCountingEnabled(boolean timeCountingEnabled) {
        this.timeCountingEnabled = timeCountingEnabled;
    }

    public boolean isTimeCountingEnabled() {
        return timeCountingEnabled;
    }

    @Nullable
    public EditSession getEditSession() {
        return editSession;
    }

    public void schematicsPaste(String name) {
        SchematicsManager.PasteSchematics(name, this);
    }
    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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


    public String getInventoryTitle() {
        return inventoryTitle;
    }

    public void setInventoryTitle(String inventoryTitle) {
        this.inventoryTitle = inventoryTitle;

    }

    public void updateInvName() {
        inventory = Bukkit.createInventory(null, inventorySize, Message.messageBuilder(inventoryTitle));
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    @NotNull
    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getSpawnRadiusMin() {
        return spawnRadiusMin;
    }

    public void setSpawnRadiusMin(int spawnRadiusMin) {
        this.spawnRadiusMin = spawnRadiusMin;
    }

    public int getSpawnRadiusMax() {
        return spawnRadiusMax;
    }

    public void setSpawnRadiusMax(int spawnRadiusMax) {
        this.spawnRadiusMax = spawnRadiusMax;
    }

    public int getRegionRadius() {
        return regionRadius;
    }

    public void setRegionRadius(int regionRadius) {
        this.regionRadius = regionRadius;
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

    public boolean isAirDropLocked() {
        return airDropLocked;
    }

    public void setAirDropLocked(boolean airDropLocked) {
        this.airDropLocked = airDropLocked;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Nullable
    public Location getAirDropLocation() {
        return airDropLocation;
    }

    public void setAirDropLocation(Location airDropLocation) {
        this.airDropLocation = airDropLocation;
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

    public int getMinPlayersToStart() {
        return minPlayersToStart;
    }

    public void setMinPlayersToStart(int minPlayersToStart) {
        this.minPlayersToStart = minPlayersToStart;
    }


    public String getId() {
        return id;
    }

    public boolean isWasOpened() {
        return wasOpened;
    }

    public void setWasOpened(boolean wasOpened) {
        this.wasOpened = wasOpened;
    }

    public boolean isAirDropStarted() {
        return airDropStarted;
    }
}
