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
import org.by1337.bairdrop.ItemUtil.EnchantMaterial;
import org.by1337.bairdrop.ItemUtil.Items;
import org.by1337.bairdrop.LocationGenerator.Generator;
import org.by1337.bairdrop.LocationGenerator.GeneratorLoc;
import org.by1337.bairdrop.LocationGenerator.CGenerator;
import org.by1337.bairdrop.LocationGenerator.GeneratorUtils;
import org.by1337.bairdrop.WorldGuardApi.RegionManager;
import org.by1337.bairdrop.WorldGuardApi.SchematicsManager;
import org.by1337.bairdrop.api.event.AirDropEndEvent;
import org.by1337.bairdrop.api.event.AirDropStartEvent;
import org.by1337.bairdrop.api.event.AirDropUnlockEvent;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.customListeners.observer.Observer;
import org.by1337.bairdrop.effect.EffectFactory;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.by1337.bairdrop.BAirDrop.*;

import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.menu.EditAirMenu;

public class CAirDrop implements AirDrop {
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
    private HashMap<String, IEffect> loadedEffect = new HashMap<>();
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
    private final CAirDrop CAirDropInstance;
    private boolean useOnlyStaticLoc;
    private final List<Observer> observers = new ArrayList<>();

    private Generator generator;

    CAirDrop(FileConfiguration fileConfiguration, File airDropFile) {
        CAirDropInstance = this;
        try {
            this.airDropFile = airDropFile;
            this.fileConfiguration = fileConfiguration;
            id = fileConfiguration.getString("air-id");

            InvalidCharactersChecker invalidCharactersChecker = new InvalidCharacters();
            String invalidChars = invalidCharactersChecker.getInvalidCharacters(id);

            if (!invalidChars.isEmpty()) {
                Message.error(String.format("Недопустимые символы: %s", invalidChars));
                Message.error("АирДроп не загружен!");
                airDrops.remove(id);
                return;
            }

            useStaticLoc = fileConfiguration.getBoolean("use-static-loc");
            stopWhenEmpty = fileConfiguration.getBoolean("stop-when-empty");
            randomizeSlot = fileConfiguration.getBoolean("random-slot");

            if (fileConfiguration.getString("static-location.world") != null) {
                double x = fileConfiguration.getDouble("static-location.x");
                double y = fileConfiguration.getDouble("static-location.y");
                double z = fileConfiguration.getDouble("static-location.z");
                World world1 = Bukkit.getWorld(fileConfiguration.getString("static-location.world"));
                if (world1 == null) {
                    Message.error(String.format(BAirDrop.getConfigMessage().getMessage("static-loc-error"), id));
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

            generator = new CGenerator();
            if (fileConfiguration.getConfigurationSection("inv") != null) {
                for (String inv : fileConfiguration.getConfigurationSection("inv").getKeys(false)) {
                    for (String slot : fileConfiguration.getConfigurationSection("inv." + inv).getKeys(false)) {
                        List<String> chance = fileConfiguration.getConfigurationSection("inv." + inv + "." + slot).getKeys(false).stream().toList();
                        if (chance.size() == 0) {
                            Message.warning(BAirDrop.getConfigMessage().getMessage("item-error").replace("{slot}", slot));
                            continue;
                        }

                        if (chance.size() >= 2)
                            Message.warning(BAirDrop.getConfigMessage().getMessage("slot-more").replace("{slot}", slot).replace("{id}", id).replace("{size}", String.valueOf(chance.size())));


                        ItemStack item = fileConfiguration.getItemStack("inv." + inv + "." + slot + "." + chance.get(0));
                        if (item == null) {
                            Message.warning(BAirDrop.getConfigMessage().getMessage("item-null").replace("{slot}", slot));
                            continue;
                        }

                        Items items = new Items(Integer.parseInt(slot), Integer.parseInt(chance.get(0)), item, inv);
                        List<Items> list = new ArrayList<>(listItems.getOrDefault(inv, new ArrayList<>()));
                        list.add(items);
                        listItems.put(inv, list);
                    }
                }
            }

            Message.logger(BAirDrop.getConfigMessage().getMessage("air-loaded").replace("{id}", id));
        } catch (Exception e) {
            e.printStackTrace();
            Message.error(BAirDrop.getConfigMessage().getMessage("not-load"));
        }
        notifyObservers(CustomEvent.LOAD, null);
        run();
    }

    public CAirDrop(String id) {
        CAirDropInstance = this;
        try {
            this.id = id;
            createFile();
            usePreGeneratedLocations = false;
            minPlayersToStart = 0;
            inventoryTitle = "new air";
            displayName = "new air name";
            inventorySize = 54;//54
            world = Bukkit.getWorld("world") == null ? Bukkit.getWorlds().get(0) : Bukkit.getWorld("world");
            spawnRadiusMin = -2000;
            spawnRadiusMax = 2000;
            regionRadius = 15;
            timeToStartCons = 2;
            timeToStopCons = 1;
            timeToUnlockCons = 1;
            searchBeforeStartCons = 1;
            timeToStart = 2 * 60; //2 * 60
            searchBeforeStart = 60;//60
            timeToOpen = 60;//60
            startCountdownAfterClick = false;
            timeStopEventMustGo = false;
            timeStop = 60;//60
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
            spawnChance = 50; //50
            airHolo = BAirDrop.getConfigMessage().getList("air-holo");
            airHoloOpen = BAirDrop.getConfigMessage().getList("air-holo-open");
            airHoloClickWait = BAirDrop.getConfigMessage().getList("air-holo-click-wait");
            airHoloToStart = BAirDrop.getConfigMessage().getList("air-holo-to-start");
            holoOffsets = new Vector(0.5, 2.5, 0.5);
            generator = new CGenerator();
            inventory = Bukkit.createInventory(null, inventorySize, inventoryTitle);
            Message.logger(BAirDrop.getConfigMessage().getMessage("air-loaded").replace("{id}", this.id));
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
                                        -GeneratorUtils.getSettings(getGeneratorSettings(), String.format("%s.offsets.x", GeneratorUtils.getWorldKeyByWorld(airDropLocation.getWorld()))),
                                        -GeneratorUtils.getSettings(getGeneratorSettings(), String.format("%s.offsets.y", GeneratorUtils.getWorldKeyByWorld(airDropLocation.getWorld()))),
                                        -GeneratorUtils.getSettings(getGeneratorSettings(), String.format("%s.offsets.z", GeneratorUtils.getWorldKeyByWorld(airDropLocation.getWorld())))).add(0, 1, 0), id);
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
        }.runTaskTimer(BAirDrop.getInstance(), 20, 20);//20 20
    }

    private BukkitTask bukkitTaskStart = null;
    @Override
    public void startCommand(@Nullable Player player) {
        if(bukkitTaskStart != null && !bukkitTaskStart.isCancelled()){
            bukkitTaskStart.cancel();
        }
        bukkitTaskStart = new BukkitRunnable() {
            int x = 0;
            @Override
            public void run() {
                locationSearch();
                if (airDropLocation != null) {
                    Message.sendMsg(player,"&aStarted");
                    Start();
                    cancel();
                }else
                    Message.sendMsg(player,"&cFail start: " + x);
                x++;
            }
        }.runTaskTimer(BAirDrop.getInstance(), 1L, 10L);
    }

    @Override
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
            Message.error(BAirDrop.getConfigMessage().getMessage("items-is-empty"));
            inventory.setItem(0, new ItemStack(Material.DIRT));
        }

        if (airDropLocation == null) {
            if (staticLocation == null) {
                Message.error(BAirDrop.getConfigMessage().getMessage("loc-is-null"));
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
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("material-error"), materialLocked));
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
        while (next <= 200) {//200
            int slot = ThreadLocalRandom.current().nextInt(inventory.getSize());
            if (inventory.getItem(slot) == null) {
                return slot;
            }
            next++;
        }
        return 0;
    }

    @Override
    public File getAirDropFile() {
        return airDropFile;
    }

    @Override
    public void unlock() {
        if(!airDropStarted){
            throw new IllegalArgumentException("airdrop is not started!");
        }
        AirDropUnlockEvent airDropUnlockEvent = new AirDropUnlockEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(airDropUnlockEvent);
        if (airDropUnlockEvent.isCancelled())
            return;

        airDropLocked = false;
        timeToOpen = 0;
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
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("material-error"), materialUnlocked));
            airDropLocation.getBlock().setType(Material.DIRT);
        }

        List<String> lines = new ArrayList<>(airHoloOpen);
        lines.replaceAll(this::replaceInternalPlaceholder);
        BAirDrop.hologram.createOrUpdateHologram(lines, airDropLocation.clone().add(holoOffsets), id);
        notifyObservers(CustomEvent.UNLOCK_EVENT, null);
    }

    @Override
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
                        if (futureLocation == null) {
                            if (usePreGeneratedLocations)
                                futureLocation = generator.getPreLocation(CAirDropInstance);
                            else
                                futureLocation = generator.getLocation(CAirDropInstance, false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    CAirDropInstance.setBukkitTask(null);
                }
            }.runTaskAsynchronously(getInstance());
        }
    }

    private synchronized void setBukkitTask(BukkitTask bukkitTask) {
        this.bukkitTask = bukkitTask;
    }

    @Override
    public String replaceInternalPlaceholder(String str) {
        if (!str.contains("{"))
            return str;
        StringBuilder sb = new StringBuilder(str);

        //int var = -1;
        if (str.contains("{!")) {
            if (sb.indexOf("{!clone}") != -1)
                sb.replace(sb.indexOf("{!clone}"), sb.indexOf("{!clone}") + 8, String.valueOf(!isClone()));
            if (sb.indexOf("{!airdrop-is-open}") != -1)
                sb.replace(sb.indexOf("{!airdrop-is-open}"), sb.indexOf("{!airdrop-is-open}") + 18, String.valueOf(airDropLocked));
            if (sb.indexOf("{!airdrop-is-start}") != -1)
                sb.replace(sb.indexOf("{!airdrop-is-start}"), sb.indexOf("{!airdrop-is-start}") + 19, String.valueOf(!airDropStarted));
            if (sb.indexOf("{!it-was-open}") != -1)
                sb.replace(sb.indexOf("{!it-was-open}"), sb.indexOf("{!it-was-open}") + 14, String.valueOf(!wasOpened));
            if (sb.indexOf("{!use-pre-generated-locations}") != -1)
                sb.replace(sb.indexOf("{!use-pre-generated-locations}"), sb.indexOf("{!use-pre-generated-locations}") + 30, String.valueOf(!usePreGeneratedLocations));
            if (sb.indexOf("{!time-stop-event-must-go}") != -1)
                sb.replace(sb.indexOf("{!time-stop-event-must-go}"), sb.indexOf("{!time-stop-event-must-go}") + 26, String.valueOf(!timeStopEventMustGo));
            if (sb.indexOf("{!use-static-loc}") != -1)
                sb.replace(sb.indexOf("{!use-static-loc}"), sb.indexOf("{!use-static-loc}") + 17, String.valueOf(!useStaticLoc));
            if (sb.indexOf("{!flatness-check}") != -1)
                sb.replace(sb.indexOf("{!flatness-check}"), sb.indexOf("{!flatness-check}") + 17, String.valueOf(!flatnessCheck));
        }

        if (sb.indexOf("{use-only-static-loc}") != -1)
            sb.replace(sb.indexOf("{use-only-static-loc}"), sb.indexOf("{use-only-static-loc}") + 21, String.valueOf(useOnlyStaticLoc));
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
            sb.replace(sb.indexOf("{airdrop-is-open}"), sb.indexOf("{airdrop-is-open}") + 17, String.valueOf(!airDropLocked));
        if (sb.indexOf("{airdrop-is-start}") != -1)
            sb.replace(sb.indexOf("{airdrop-is-start}"), sb.indexOf("{airdrop-is-start}") + 18, String.valueOf(airDropStarted));
        if (sb.indexOf("{it-was-open}") != -1)
            sb.replace(sb.indexOf("{it-was-open}"), sb.indexOf("{it-was-open}") + 13, String.valueOf(wasOpened));
        if (sb.indexOf("{use-pre-generated-locations}") != -1)
            sb.replace(sb.indexOf("{use-pre-generated-locations}"), sb.indexOf("{use-pre-generated-locations}") + 29, String.valueOf(usePreGeneratedLocations));
        if (sb.indexOf("{time-stop-event-must-go}") != -1)
            sb.replace(sb.indexOf("{time-stop-event-must-go}"), sb.indexOf("{time-stop-event-must-go}") + 25, String.valueOf(timeStopEventMustGo));
        if (sb.indexOf("{use-static-loc}") != -1)
            sb.replace(sb.indexOf("{use-static-loc}"), sb.indexOf("{use-static-loc}") + 16, String.valueOf(useStaticLoc));
        if (sb.indexOf("{flatness-check}") != -1)
            sb.replace(sb.indexOf("{flatness-check}"), sb.indexOf("{flatness-check}") + 16, String.valueOf(flatnessCheck));
        if (sb.indexOf("{summoner}") != -1)
            sb.replace(sb.indexOf("{summoner}"), sb.indexOf("{summoner}") + 10, String.valueOf(summoner));
        if (sb.indexOf("{id}") != -1)
            sb.replace(sb.indexOf("{id}"), sb.indexOf("{id}") + 4, id);
        if (sb.indexOf("{world}") != -1)
            sb.replace(sb.indexOf("{world}"), sb.indexOf("{world}") + 7, world.getName());
        if (sb.indexOf("{air-name}") != -1)
            sb.replace(sb.indexOf("{air-name}"), sb.indexOf("{air-name}") + 10, getDisplayName());
        if (sb.indexOf("{inv-name}") != -1)
            sb.replace(sb.indexOf("{inv-name}"), sb.indexOf("{inv-name}") + 10, getInventoryTitle());
        if (sb.indexOf("{spawn-min}") != -1)
            sb.replace(sb.indexOf("{spawn-min}"), sb.indexOf("{spawn-min}") + 11, String.valueOf(getSpawnRadiusMin()));
        if (sb.indexOf("{spawn-max}") != -1)
            sb.replace(sb.indexOf("{spawn-max}"), sb.indexOf("{spawn-max}") + 11, String.valueOf(getSpawnRadiusMax()));
        if (sb.indexOf("{air-protect}") != -1)
            sb.replace(sb.indexOf("{air-protect}"), sb.indexOf("{air-protect}") + 13, String.valueOf(getRegionRadius()));
        if (sb.indexOf("{search-before-start}") != -1)
            sb.replace(sb.indexOf("{search-before-start}"), sb.indexOf("{search-before-start}") + 21, String.valueOf(getSearchBeforeStart()));
        if (sb.indexOf("{min-online-players}") != -1)
            sb.replace(sb.indexOf("{min-online-players}"), sb.indexOf("{min-online-players}") + 20, String.valueOf(getMinPlayersToStart()));
        if (sb.indexOf("{material-locked}") != -1)
            sb.replace(sb.indexOf("{material-locked}"), sb.indexOf("{material-locked}") + 17, String.valueOf(materialLocked));
        if (sb.indexOf("{material-unlocked}") != -1)
            sb.replace(sb.indexOf("{material-unlocked}"), sb.indexOf("{material-unlocked}") + 19, String.valueOf(materialUnlocked));
        if (sb.indexOf("{world-loc}") != -1)
            sb.replace(sb.indexOf("{world-loc}"), sb.indexOf("{world-loc}") + 11, String.valueOf(GeneratorLoc.getSizeLocForAirDrop(this)));
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
            sb.replace(sb.indexOf("{global-timer}"), sb.indexOf("{global-timer}") + 14, BAirDrop.globalTimer != null ? String.valueOf(BAirDrop.globalTimer.getTimeToStart()) : "var");
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
                sb.replace(sb.indexOf("{stat-x}"), sb.indexOf("{stat-x}") + 8, (String.valueOf(staticLocation.getX())).replace(".0", ""));
            if (sb.indexOf("{stat-y}") != -1)
                sb.replace(sb.indexOf("{stat-y}"), sb.indexOf("{stat-y}") + 8, (String.valueOf(staticLocation.getY())).replace(".0", ""));
            if (sb.indexOf("{stat-z}") != -1)
                sb.replace(sb.indexOf("{stat-z}"), sb.indexOf("{stat-z}") + 8, (String.valueOf(staticLocation.getZ())).replace(".0", ""));
        }
        if (getAnyLoc() == null) {
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
                sb.replace(sb.indexOf("{x}"), sb.indexOf("{x}") + 3, (String.valueOf(getAnyLoc().getX())).replace(".0", ""));
            if (sb.indexOf("{y}") != -1)
                sb.replace(sb.indexOf("{y}"), sb.indexOf("{y}") + 3, (String.valueOf(getAnyLoc().getY())).replace(".0", ""));
            if (sb.indexOf("{z}") != -1)
                sb.replace(sb.indexOf("{z}"), sb.indexOf("{z}") + 3, (String.valueOf(getAnyLoc().getZ())).replace(".0", ""));
            if (sb.indexOf("{biome}") != -1)
                sb.replace(sb.indexOf("{biome}"), sb.indexOf("{biome}") + 7, GeneratorUtils.getBiome(getAnyLoc()));
            if (sb.indexOf("{GET_BLOCK_MATERIAL}") != -1)
                sb.replace(sb.indexOf("{GET_BLOCK_MATERIAL}"), sb.indexOf("{GET_BLOCK_MATERIAL}") + 20, String.valueOf(GeneratorUtils.getBlock(this).getType()));
        }
        return sb.toString();
    }


    @Override
    public void registerObserver(Observer observer) {
        if (observers.contains(observer)) {
            throw new IllegalArgumentException("this observer is already registered");
        }
        observers.add(observer);
    }

    @Override
    public void saveObserver(String observerKey) {
        if (!signedListener.contains(observerKey))
            signedListener.add(observerKey);
        else
            throw new IllegalArgumentException("this observer is already saved");
    }

    @Override
    public void removeSaveObserver(String observerKey) {
        if (!signedListener.remove(observerKey)) {
            throw new IllegalArgumentException("this observer is not saved yet");
        }
    }

    @Override
    public boolean hasSavedObserver(String observerKey) {
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

        List<Observer> tempObservers = new ArrayList<>(observers);

        tempObservers.forEach(o -> o.update(pl, this, customEvent, false));

        AirDropUtils.getStaticObservers().forEach(o -> o.update(pl, this, customEvent, false));

        if (System.currentTimeMillis() - x < 50)
            Message.debug(String.format(BAirDrop.getConfigMessage().getMessage("event-time"), customEvent.getKey().getKey(), (System.currentTimeMillis() - x)), LogLevel.HARD);
        else if (System.currentTimeMillis() - x > 50 && System.currentTimeMillis() - x < 75)
            Message.debug(String.format(BAirDrop.getConfigMessage().getMessage("event-time-50"), customEvent.getKey().getKey(), (System.currentTimeMillis() - x)), LogLevel.MEDIUM);
        else if (System.currentTimeMillis() - x > 75)
            Message.debug(String.format(BAirDrop.getConfigMessage().getMessage("event-time-75"), customEvent.getKey().getKey(), (System.currentTimeMillis() - x)), LogLevel.LOW);
    }

    @Override
    public boolean hasObserver(Observer observer) {
        return observers.contains(observer);
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public void InvokeListener(NamespacedKey listener, @Nullable Player player, CustomEvent customEvent) {
        try {
            if (!BAirDrop.customEventListeners.containsKey(listener)) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-listener"), listener));
                return;
            }

            BAirDrop.customEventListeners.get(listener).update(player, this, customEvent, true);
        } catch (StackOverflowError e) {
            Message.error(BAirDrop.getConfigMessage().getMessage("too-many-call"));
        }

    }

    @Override
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

    @Override
    public org.by1337.bairdrop.menu.EditAirMenu getEditAirMenu() {
        return EditAirMenu;
    }

    @Override
    public void setEditAirMenu(org.by1337.bairdrop.menu.EditAirMenu editAirMenu) {
        EditAirMenu = editAirMenu;
    }

    @Override
    public void updateEditAirMenu() {
        if (EditAirMenu != null)
            EditAirMenu.menuGenerate();
    }

    @Override
    public void updateEditAirMenu(String tag) {
        if (EditAirMenu != null)
            EditAirMenu.menuGenerate(tag);
    }

    @Override
    public void loadEffect(String name, String id) {
        IEffect ie = EffectFactory.getEffect(name);
        if (ie == null) {
            throw new IllegalArgumentException(String.format(BAirDrop.getConfigMessage().getMessage("unknown-effect"), name));
        }
        loadedEffect.put(id, ie);
    }

    @Override
    public void startEffect(String id) {
        IEffect ie = loadedEffect.getOrDefault(id, null);
        if (ie == null) {
            throw new IllegalArgumentException(String.format(BAirDrop.getConfigMessage().getMessage("unknown-effect"), id));
        }
        if (isEffectStarted(id)) {
            throw new IllegalArgumentException(String.format(BAirDrop.getConfigMessage().getMessage("there-is-already-an-effect"), id));
        }
        ie.Start(this);
    }

    @Override
    public boolean isEffectStarted(String id) {
        return loadedEffect.get(id).isUsed();
    }

    @Override
    public void StopEffect(String id) {
        if (!loadedEffect.containsKey(id)) {
            throw new IllegalArgumentException(String.format(BAirDrop.getConfigMessage().getMessage("effect-not-stated"), id));
        }
        IEffect ie = loadedEffect.get(id);
        ie.End();
        loadedEffect.remove(id);
    }

    @Override
    public void StopAllEffects() {
        for (IEffect ie : loadedEffect.values())
            ie.End();
    }
    @Override
    public HashMap<String, IEffect> getLoadedEffect() {
        return loadedEffect;
    }
    @Override
    public void setLoadedEffect(HashMap<String, IEffect> loadedEffect) {
        this.loadedEffect = loadedEffect;
    }

    @Override
    public void schematicsUndo() {
        if (editSession != null) {
            EditSession newEditSession = WorldEdit.getInstance().newEditSession(editSession.getWorld());
            editSession.undo(newEditSession);
            editSession.close();
            editSession = null;
        }
    }

    @Override
    public String getGeneratorSettings() {
        return generatorSettings == null ? "default" : generatorSettings;
    }

    @Override
    public void setEditSession(EditSession editSession) {
        this.editSession = editSession;
    }

    @Override
    public AirDrop clone(String id) {
        CAirDrop air = new CAirDrop(fileConfiguration, airDropFile);
        air.setId(id);
        for(Observer observer : observers)
            air.registerObserver(observer);
        air.setGenerator(generator);
        return air;
    }


    @Override
    public void createFile() {
        File air = new File(getInstance().getDataFolder() + File.separator + "airdrops" + File.separator + getId() + ".yml");
        this.airDropFile = air;
        this.fileConfiguration = YamlConfiguration.loadConfiguration(air);
    }

    @Override
    public boolean delete() {
        File air = new File(getInstance().getDataFolder() + File.separator + "airdrops" + File.separator + getId() + ".yml");
        return air.delete();
    }

    @Override
    public void unload() {
        if (airDropStarted) {
            End();
        }
        notifyObservers(CustomEvent.UNLOAD, null);
        airDrops.remove(getId());
    }

    @Override
    @Nullable
    public Location getAnyLoc() {
        if (airDropLocation == null) {
            if (futureLocation == null)
                return null;
            else
                return futureLocation.clone();
        } else {
            return airDropLocation.clone();
        }
    }

    @Override
    public boolean isUseOnlyStaticLoc() {
        return useOnlyStaticLoc;
    }

    @Override
    public void setUseOnlyStaticLoc(boolean useOnlyStaticLoc) {
        this.useOnlyStaticLoc = useOnlyStaticLoc;
    }

    @Override
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

    @Override
    public void setRegion() {
        RegionManager.SetRegion(this);
    }

    @Override
    public boolean isHoloTimeToStartEnabled() {
        return holoTimeToStartEnabled;
    }

    @Override
    public void setHoloTimeToStartEnabled(boolean holoTimeToStartEnabled) {
        this.holoTimeToStartEnabled = holoTimeToStartEnabled;
    }

    @Override
    public boolean isUsePlayerLocation() {
        return usePlayerLocation;
    }

    @Override
    public void setUsePlayerLocation(boolean usePlayerLocation) {
        this.usePlayerLocation = usePlayerLocation;
    }

    @Override
    public boolean isStopWhenEmpty() {
        return stopWhenEmpty;
    }

    @Override
    public void setStopWhenEmpty(boolean stopWhenEmpty) {
        this.stopWhenEmpty = stopWhenEmpty;
    }

    @Override
    public boolean isSummoner() {
        return summoner;
    }

    @Override
    public void setSummoner(boolean summoner) {
        this.summoner = summoner;
    }

    @Override
    public boolean isHideInCompleter() {
        return hideInCompleter;
    }

    @Override
    public void setHideInCompleter(boolean hideInCompleter) {
        this.hideInCompleter = hideInCompleter;
    }

    @Override
    public HashMap<String, List<Items>> getListItems() {
        return listItems;
    }

    @Override
    public void setHoloTimeToStartMinusOffsets(boolean holoTimeToStartMinusOffsets) {
        this.holoTimeToStartMinusOffsets = holoTimeToStartMinusOffsets;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public boolean isClone() {
        return clone;
    }

    @Override
    public void setClone(boolean clone) {
        this.clone = clone;
    }

    @Override
    public boolean isKill() {
        return kill;
    }

    @Override
    public void setKill(boolean kill) {
        this.kill = kill;
    }

    private void setId(String id) {
        this.id = id;
    }

    @Override
    public Location getStaticLocation() {
        return staticLocation;
    }

    @Override
    public void setStaticLocation(Location staticLocation) {
        this.staticLocation = staticLocation;
    }

    @Override
    public boolean isUseStaticLoc() {
        return useStaticLoc;
    }

    @Override
    public void setUseStaticLoc(boolean useStaticLoc) {
        this.useStaticLoc = useStaticLoc;
    }

    @Override
    public int getPickPreGenLocs() {
        return pickPreGenLocs;
    }

    @Override
    public void setPickPreGenLocs(int pickPreGenLocs) {
        this.pickPreGenLocs = pickPreGenLocs;
    }

    @Override
    public int getSpawnChance() {
        return spawnChance;
    }

    @Override
    public void setTimeCountingEnabled(boolean timeCountingEnabled) {
        this.timeCountingEnabled = timeCountingEnabled;
    }

    @Override
    public boolean isTimeCountingEnabled() {
        return timeCountingEnabled;
    }

    @Override
    @Nullable
    public EditSession getEditSession() {
        return editSession;
    }

    @Override
    public void schematicsPaste(SchematicsManager manager,String name) {
        manager.PasteSchematics(name, this);
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean isUsePreGeneratedLocations() {
        return usePreGeneratedLocations;
    }

    @Override
    public void setUsePreGeneratedLocations(boolean usePreGeneratedLocations) {
        this.usePreGeneratedLocations = usePreGeneratedLocations;
    }

    @Override
    public boolean isFlatnessCheck() {
        return flatnessCheck;
    }

    @Override
    public void setFlatnessCheck(boolean flatnessCheck) {
        this.flatnessCheck = flatnessCheck;
    }

    @Override
    public int getTimeToStartCons() {
        return timeToStartCons;
    }

    @Override
    public int getTimeToStopCons() {
        return timeToStopCons;
    }

    @Override
    public int getTimeToUnlockCons() {
        return timeToUnlockCons;
    }

    @Override
    public int getSearchBeforeStartCons() {
        return searchBeforeStartCons;
    }

    @Override
    public void setTimeToStartCons(int timeToStartCons) {
        this.timeToStartCons = timeToStartCons;
    }

    @Override
    public void setTimeToStopCons(int timeToStopCons) {
        this.timeToStopCons = timeToStopCons;
    }

    @Override
    public void setTimeToUnlockCons(int timeToUnlockCons) {
        this.timeToUnlockCons = timeToUnlockCons;
    }

    @Override
    public void setSearchBeforeStartCons(int searchBeforeStartCons) {
        this.searchBeforeStartCons = searchBeforeStartCons;
    }

    @Override
    public String getInventoryTitle() {
        return inventoryTitle;
    }

    @Override
    public void setInventoryTitle(String inventoryTitle) {
        this.inventoryTitle = inventoryTitle;
        inventory = Bukkit.createInventory(null, inventorySize, Message.messageBuilder(inventoryTitle));
    }


    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public int getInventorySize() {
        return inventorySize;
    }

    @Override
    @NotNull
    public World getWorld() {
        return world;
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public int getSpawnRadiusMin() {
        return spawnRadiusMin;
    }

    @Override
    public void setSpawnRadiusMin(int spawnRadiusMin) {
        this.spawnRadiusMin = spawnRadiusMin;
    }

    @Override
    public int getSpawnRadiusMax() {
        return spawnRadiusMax;
    }

    @Override
    public void setSpawnRadiusMax(int spawnRadiusMax) {
        this.spawnRadiusMax = spawnRadiusMax;
    }

    @Override
    public int getRegionRadius() {
        return regionRadius;
    }

    @Override
    public void setRegionRadius(int regionRadius) {
        this.regionRadius = regionRadius;
    }

    @Override
    public int getTimeToStart() {
        return timeToStart;
    }

    @Override
    public void setTimeToStart(int timeToStart) {
        this.timeToStart = timeToStart;
    }

    @Override
    public int getSearchBeforeStart() {
        return searchBeforeStart;
    }

    @Override
    public void setSearchBeforeStart(int searchBeforeStart) {
        this.searchBeforeStart = searchBeforeStart;
    }

    @Override
    public int getTimeToOpen() {
        return timeToOpen;
    }

    @Override
    public void setTimeToOpen(int timeToOpen) {
        this.timeToOpen = timeToOpen;
    }

    @Override
    public boolean isStartCountdownAfterClick() {
        return startCountdownAfterClick;
    }

    @Override
    public void setStartCountdownAfterClick(boolean startCountdownAfterClick) {
        this.startCountdownAfterClick = startCountdownAfterClick;
    }

    @Override
    public boolean isTimeStopEventMustGo() {
        return timeStopEventMustGo;
    }

    @Override
    public void setTimeStopEventMustGo(boolean timeStopEventMustGo) {
        this.timeStopEventMustGo = timeStopEventMustGo;
    }

    @Override
    public int getTimeStop() {
        return timeStop;
    }

    @Override
    public void setTimeStop(int timeStop) {
        this.timeStop = timeStop;
    }

    @Override
    public Material getMaterialLocked() {
        return materialLocked;
    }

    @Override
    public void setMaterialLocked(Material materialLocked) {
        this.materialLocked = materialLocked;
    }

    @Override
    public Material getMaterialUnlocked() {
        return materialUnlocked;
    }

    @Override
    public void setMaterialUnlocked(Material materialUnlocked) {
        this.materialUnlocked = materialUnlocked;
    }

    @Override
    public boolean isAirDropLocked() {
        return airDropLocked;
    }

    @Override
    public void setAirDropLocked(boolean airDropLocked) {
        this.airDropLocked = airDropLocked;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    @Nullable
    public Location getAirDropLocation() {
        return airDropLocation;
    }

    @Override
    public void setAirDropLocation(Location airDropLocation) {
        this.airDropLocation = airDropLocation;
    }

    @Override
    @Nullable
    public Location getFutureLocation() {
        return futureLocation;
    }

    @Override
    public void setFutureLocation(Location futureLocation) {
        this.futureLocation = futureLocation;
    }

    @Override
    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    @Override
    public int getMinPlayersToStart() {
        return minPlayersToStart;
    }

    @Override
    public void setMinPlayersToStart(int minPlayersToStart) {
        this.minPlayersToStart = minPlayersToStart;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isWasOpened() {
        return wasOpened;
    }

    @Override
    public void setWasOpened(boolean wasOpened) {
        this.wasOpened = wasOpened;
    }

    @Override
    public boolean isAirDropStarted() {
        return airDropStarted;
    }

    @Override
    public Generator getGenerator() {
        return generator;
    }
    @Override
    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(obj == null) return false;
//        if(obj instanceof CAirDrop airDrop){
//            return this.hashCode() == airDrop.hashCode();
//        }
//        return false;
//    }

}
