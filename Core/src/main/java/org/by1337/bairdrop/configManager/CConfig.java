package org.by1337.bairdrop.configManager;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.ItemUtil.EnchantInfo;
import org.by1337.bairdrop.ItemUtil.EnchantMaterial;
import org.by1337.bairdrop.lang.Lang;
import org.by1337.bairdrop.listeners.Compass;
import org.by1337.bairdrop.locationGenerator.CGenLoc;
import org.by1337.bairdrop.locationGenerator.GenLoc;
import org.by1337.bairdrop.worldGuardHook.RegionManager;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.customListeners.CustomEventListener;
import org.by1337.bairdrop.customListeners.util.CustomEventListenerBuilder;
import org.by1337.bairdrop.menu.util.MenuItem;
import org.by1337.bairdrop.util.*;

import java.io.*;
import java.util.*;

import static org.by1337.bairdrop.BAirDrop.summoner;
import static org.by1337.bairdrop.locationGenerator.GeneratorLoc.locs;
import static org.by1337.bairdrop.effect.LoadEffects.LoadEffect;


public class CConfig implements Config, ConfigMessage {
    private FileConfiguration listeners;
    private File fileListeners;
    private FileConfiguration effects;
    private File fileEffects;
    private FileConfiguration locations;
    private File fileLocations;
    private FileConfiguration menu;
    private File fileMenu;
    private FileConfiguration schemConf;
    private File fileSchemConf;
    private FileConfiguration generatorSettings;
    private File fileGeneratorSettings;
    private HashMap<String, File> Schematics = new HashMap<>();
    private HashMap<File, FileConfiguration> airDrops = new HashMap<>();
    private Lang lang;
    private boolean loaded;
    public HashMap<String, File> scripts = new HashMap<>();

    public void loadConfiguration() {
        fileMenu = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "menu.yml");
        if (!fileMenu.exists()) {
            BAirDrop.getInstance().saveResource("menu.yml", true);
        }
        menu = YamlConfiguration.loadConfiguration(fileMenu);


        fileGeneratorSettings = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "generatorSettings.yml");
        if (!fileGeneratorSettings.exists()) {
            BAirDrop.getInstance().saveResource("generatorSettings.yml", true);
        }
        generatorSettings = YamlConfiguration.loadConfiguration(fileGeneratorSettings);

        File shemDir = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "Schematics");
        if (!shemDir.exists()) {
            shemDir.mkdir();
        }

        fileSchemConf = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "Schematics" + File.separator + "schemConf.yml");
        if (!fileSchemConf.exists()) {
            BAirDrop.getInstance().saveResource("Schematics" + File.separator + "schemConf.yml", true);
        }
        schemConf = YamlConfiguration.loadConfiguration(fileSchemConf);


        for (File shemFile : Arrays.stream(Objects.requireNonNull(shemDir.listFiles())).toList()) {
            if (shemFile.getAbsolutePath().equals(fileSchemConf.getAbsolutePath()))
                continue;
            Message.debug("load schematics" + shemFile.getAbsolutePath(), LogLevel.LOW);
            Schematics.put(shemFile.getName(), shemFile);
        }


        fileEffects = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "effects.yml");
        if (!fileEffects.exists()) {
            BAirDrop.getInstance().saveResource("effects.yml", true);
        }
        effects = YamlConfiguration.loadConfiguration(fileEffects);

        fileLocations = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "locations.yml");
        if (!fileLocations.exists()) {
            BAirDrop.getInstance().saveResource("locations.yml", true);
        }
        locations = YamlConfiguration.loadConfiguration(fileLocations);

        fileListeners = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "listeners.yml");
        if (!fileListeners.exists()) {
            BAirDrop.getInstance().saveResource("listeners.yml", true);
        }
        listeners = YamlConfiguration.loadConfiguration(fileListeners);

        loadLang();

        File dir = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "airdrops");
        if (!dir.exists()) {
            dir.mkdir();
            BAirDrop.getInstance().saveResource("airdrops" + File.separator + "default.yml", true);
        }
        File dir2 = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "scripts");//diamond.js
        if (!dir2.exists()) {
            dir2.mkdir();
            BAirDrop.getInstance().saveResource("scripts" + File.separator + "diamond.js", true);
        }
        scripts.clear();
        for (File script : Arrays.stream(Objects.requireNonNull(dir2.listFiles())).toList()) {
            scripts.put(script.getName(), script);
            Message.debug("load " + script.getName(), LogLevel.LOW);
        }

        for (File airFile : Arrays.stream(Objects.requireNonNull(dir.listFiles())).toList()) {
            Message.debug("load " + airFile.getAbsolutePath(), LogLevel.LOW);
            FileConfiguration fc = YamlConfiguration.loadConfiguration(airFile);
            airDrops.put(airFile, fc);
        }
        BAirDrop.customEventListeners.clear();
        loadListeners();
        loadMenu();
        loadLocations();
        LoadEffect(effects);
        RegionManager.loadFlags();
        loadEnchant();
        summoner.LoadSummoner();
        BAirDrop.compass = new Compass();
        BAirDrop.compass.loadItem();
        if (BAirDrop.getInstance().getConfig().getBoolean("custom-crafts.enable"))
            loadCustomCraft();
        loaded = true;
    }

    private void loadLang() {
//        String file = BAirDrop.BAirDrop.getInstance().getConfig().getString("lang", "en");
//        InputStream resourceStream = BAirDrop.getInstance().getResource("lang/" + file + ".json");
//        if (resourceStream == null) {
//            Message.error("file: " + "lang/" + file + ".json" + " not found!");
//            resourceStream = BAirDrop.getInstance().getResource("lang/en.json");
//            if (resourceStream == null) {
//                throw new IllegalStateException("Message file not found! Do you have the latest version of the plugin?");
//            }
//        }
//        try (InputStreamReader reader = new InputStreamReader(resourceStream, StandardCharsets.UTF_8)) {
//            Gson gson = new Gson();
//            lang = gson.fromJson(reader, Lang.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void loadMenu() {
        MenuItem.menuItemHashMap.clear();
        for (String tag : menu.getConfigurationSection("main").getKeys(false)) {
            try {
                String name = Objects.requireNonNull(menu.getString("main." + tag + ".name"));
                String material = Objects.requireNonNull(menu.getString("main." + tag + ".material"));
                int slot = menu.getInt("main." + tag + ".slot");
                List<String> lore = getListOrEmpty("main." + tag + ".lore", menu);
                List<String> LEFT = getListOrEmpty("main." + tag + ".commands." + "LEFT-CLICK", menu);
                List<String> SHIFT_LEFT = getListOrEmpty("main." + tag + ".commands." + "SHIFT_LEFT-CLICK", menu);
                List<String> RIGHT = getListOrEmpty("main." + tag + ".commands." + "RIGHT-CLICK", menu);
                List<String> SHIFT_RIGHT = getListOrEmpty("main." + tag + ".commands." + "SHIFT_RIGHT-CLICK", menu);
                List<String> MIDDLE = getListOrEmpty("main." + tag + ".commands." + "MIDDLE-CLICK", menu);
                List<String> DROP = getListOrEmpty("main." + tag + ".commands." + "DROP-CLICK", menu);
                new MenuItem(tag, name, lore, slot, LEFT, SHIFT_LEFT, RIGHT, SHIFT_RIGHT, MIDDLE, DROP, material);
            } catch (NullPointerException e) {
                Message.error(String.format(getMessage("menu-error"), tag));

            } catch (IllegalArgumentException e) {
                Message.error(String.format(getMessage("menu-mat-error"), tag));
            }
        }

    }

    public void loadCustomCraft() {
        if (BAirDrop.getInstance().getConfig().getConfigurationSection("custom-crafts.crafts") == null) {
            Message.error(getMessage("craft-list-is-empty"));
            return;
        }
        main:
        for (String key : BAirDrop.getInstance().getConfig().getConfigurationSection("custom-crafts.crafts").getKeys(false)) {
            try {
                String summoner = Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("custom-crafts.crafts.%s.summoner", key)));
                if (!BAirDrop.summoner.getItems().containsKey(summoner)) {
                    Message.error(String.format(getMessage("craft-unknown-item"), summoner));
                    Message.error(String.format(getMessage("craft-skip"), key));
                    continue;
                }
                String top = Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("custom-crafts.crafts.%s.slots.top", key)));
                String middle = Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("custom-crafts.crafts.%s.slots.middle", key)));
                String bottom = Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("custom-crafts.crafts.%s.slots.bottom", key)));
                List<String> call = BAirDrop.getInstance().getConfig().getStringList(String.format("custom-crafts.crafts.%s.call", key));

                if (BAirDrop.getInstance().getConfig().getConfigurationSection(String.format("custom-crafts.crafts.%s.ingredients", key)) == null) {
                    Message.error(getMessage("craft-ingredients-is-empty"));
                    continue;
                }
                HashMap<Character, Material> ingredients = new HashMap<>();
                for (String ingred : BAirDrop.getInstance().getConfig().getConfigurationSection(String.format("custom-crafts.crafts.%s.ingredients", key)).getKeys(false)) {
                    if (ingred.length() > 1) {
                        Message.error(ingred + " Может состоять только из одного символа!");
                        Message.error(String.format(getMessage("craft-skip"), key));
                        continue main;
                    }
                    try {
                        ingredients.put(ingred.charAt(0), Material.valueOf(Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("custom-crafts.crafts.%s.ingredients.%s", key, ingred)))));
                    } catch (IllegalArgumentException e) {
                        Message.error(BAirDrop.getInstance().getConfig().getString(String.format("custom-crafts.crafts.%s.ingredients.%s", key, ingred)) + " Неизвестный материал!");
                        Message.error(String.format(getMessage("craft-skip"), key));
                        continue main;
                    }
                }
                BAirDrop.crafts.put(key, new CustomCraft(key, summoner, call, ingredients, top, middle, bottom));
            } catch (NullPointerException e) {
                Message.error(String.format(getMessage("craft-load-error"), key));
                e.printStackTrace();
            } catch (Exception e) {
                Message.error(String.format(getMessage("craft-load-error"), key));
                e.printStackTrace();
            }
        }
    }

    public void loadListeners() {
        if (listeners.getConfigurationSection("listeners") == null) {
            Message.error(getMessage("list-listeners-is-empty"));
            return;
        }
        for (String key : listeners.getConfigurationSection("listeners").getKeys(false)) {
            try {
                CustomEvent customEvent = CustomEvent.getByKey(NamespacedKey.fromString(Objects.requireNonNull(listeners.getString("listeners." + key + ".event"), key + " event is null").toLowerCase()));
                if (customEvent == null) {
                    Message.error("Незарегистрированный ивент! " + listeners.getString("listeners." + key + ".event"));
                    continue;
                }
                List<String> commands = listeners.getStringList("listeners." + key + ".commands");
                List<String> denyCommands = listeners.getStringList("listeners." + key + ".deny-commands");
                String description = Objects.requireNonNull(listeners.getString("listeners." + key + ".description"), key + " description is null");
                HashMap<String, HashMap<String, String>> requirement = new HashMap<>();
                if (listeners.getConfigurationSection("listeners." + key + ".requirement") != null) {
                    for (String checkId : listeners.getConfigurationSection("listeners." + key + ".requirement").getKeys(false)) {
                        String checkType = Objects.requireNonNull(listeners.getString("listeners." + key + ".requirement." + checkId + ".type"), key + " requirement type is null");
                        String input = Objects.requireNonNull(listeners.getString("listeners." + key + ".requirement." + checkId + ".input"), key + " requirement input is null");
                        HashMap<String, String> check = new HashMap<>();
                        check.put(checkType, input);
                        requirement.put(checkId, check);
                    }
                }
                CustomEventListener customEventListener = new CustomEventListenerBuilder()
                        .customEvent(customEvent)
                        .commands(commands.toArray(new String[0]))
                        .denyCommands(denyCommands.toArray(new String[0]))
                        .description(description).requirement(requirement)
                        .key(NamespacedKey.fromString(key.toLowerCase()))
                        .build();


                BAirDrop.customEventListeners.put(NamespacedKey.fromString(key.toLowerCase()), customEventListener);
            } catch (NullPointerException e) {
                Message.error(getMessage("listeners-error"));
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                Message.error(String.format(getMessage("unknown-event"), key));
                e.printStackTrace();
            }
        }
    }

    public void loadEnchant() {
        EnchantMaterial.materialHashMap.clear();
        if (!BAirDrop.getInstance().getConfig().getBoolean("auto-enchant.enable")) return;
        for (String id : BAirDrop.getInstance().getConfig().getConfigurationSection("auto-enchant").getKeys(false)) {
            if (id.equals("enable")) continue;

            if (BAirDrop.getInstance().getConfig().getConfigurationSection("auto-enchant." + id) == null) continue;
            Material material1 = Material.DIRT;
            try {
                material1 = Material.valueOf(BAirDrop.getInstance().getConfig().getString(String.format("auto-enchant.%s.material", id)));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            List<EnchantInfo> enchantInfos = new ArrayList<>();
            List<Enchantment> conflictEnchantments = new ArrayList<>();
            for (String enchant : BAirDrop.getInstance().getConfig().getConfigurationSection("auto-enchant." + id).getKeys(false)) {
                if (enchant.equals("material")) continue;
                try {
                    if (enchant.equals("conflict-enchant")) {
                        for (String str : BAirDrop.getInstance().getConfig().getStringList(String.format("auto-enchant.%s.%s", id, enchant))) {
                            Enchantment enchantment = Objects.requireNonNull(Enchantment.getByKey(NamespacedKey.fromString("minecraft:" + str)));
                            conflictEnchantments.add(enchantment);
                        }
                        continue;
                    }
                    Enchantment enchantment = Enchantment.getByKey(NamespacedKey.fromString(enchant));
                    if (enchantment == null) {
                        Message.error("Неизвестный чар: " + enchant);
                        continue;
                    }
                    int chance = BAirDrop.getInstance().getConfig().getInt(String.format("auto-enchant.%s.%s.chance", id, enchant));
                    int minLevel = BAirDrop.getInstance().getConfig().getInt(String.format("auto-enchant.%s.%s.min-level", id, enchant));
                    int maxLevel = BAirDrop.getInstance().getConfig().getInt(String.format("auto-enchant.%s.%s.max-level", id, enchant));
                    //Message.debug(enchant + " = chance:" + chance + ", minLevel:" + minLevel + ", maxLevel:" + maxLevel);
                    enchantInfos.add(new EnchantInfo(chance, minLevel, maxLevel, enchantment));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            EnchantMaterial.materialHashMap.put(id, new EnchantMaterial(material1, conflictEnchantments, enchantInfos));
        }
    }

    @Deprecated
    public String getMessage(String path) {
        return "null";
    }

    @Deprecated
    public List<String> getList(String path) {
        return List.of("null");
    }


    public List<String> getListOrEmpty(String path, FileConfiguration file) {
        if (file.getStringList(path).isEmpty()) {
            return new ArrayList<String>();
        }
        return new ArrayList<>(file.getStringList(path));
    }

    public void loadLocations() {
        if (locations.getConfigurationSection("locations") == null)
            return;

        for (String airId : locations.getConfigurationSection("locations").getKeys(false)) {
            world:
            for (String world : locations.getConfigurationSection(String.format("locations.%s", airId)).getKeys(false)) {
                for (String uuid : locations.getConfigurationSection(String.format("locations.%s.%s", airId, world)).getKeys(false)) {
                    if (locations.getInt("version") == 0) {
                        if (!loadOldLoc(airId, world, uuid)) {
                            continue world;
                        }
                    } else {
                        GenLoc genLoc = locations.getSerializable(String.format("locations.%s.%s.%s", airId, world, uuid), CGenLoc.class);
                        List<GenLoc> list = locs.getOrDefault(airId, new ArrayList<>());
                        list.add(genLoc);
                        locs.put(airId, list);
                    }
                }
            }
        }
    }

    @Deprecated
    public boolean loadOldLoc(String airId, String world, String uuid) {
        int offsetsX = locations.getInt(String.format("locations.%s.%s.%s.offsets-x", airId, world, uuid));
        int offsetsY = locations.getInt(String.format("locations.%s.%s.%s.offsets-y", airId, world, uuid));
        int offsetsZ = locations.getInt(String.format("locations.%s.%s.%s.offsets-z", airId, world, uuid));
        int x = locations.getInt(String.format("locations.%s.%s.%s.x", airId, world, uuid));
        int y = locations.getInt(String.format("locations.%s.%s.%s.y", airId, world, uuid));
        int z = locations.getInt(String.format("locations.%s.%s.%s.z", airId, world, uuid));
        World world1 = Bukkit.getWorld(world);
        if (world1 == null) {
            Message.error(String.format(getMessage("gen-loc-world-is-null"), world));
            return false;
        }
        Location location = new Location(world1, x, y, z);

        if (!locs.containsKey(airId))
            locs.put(airId, new ArrayList<>());
        locs.getOrDefault(airId, new ArrayList<>()).add(new CGenLoc(location, new Vector(offsetsX, offsetsY, offsetsZ), airId, UUID.fromString(uuid)));
        return true;
    }

    public HashMap<File, FileConfiguration> getAirDrops() {
        return airDrops;
    }

    public FileConfiguration getSchemConf() {
        return schemConf;
    }

    public FileConfiguration getListeners() {
        return listeners;
    }

    public File getFileListeners() {
        return fileListeners;
    }

    public FileConfiguration getEffects() {
        return effects;
    }

    public File getFileEffects() {
        return fileEffects;
    }

    public FileConfiguration getLocations() {
        return locations;
    }

    public File getFileLocations() {
        return fileLocations;
    }

    public FileConfiguration getMenu() {
        return menu;
    }

    public File getFileMenu() {
        return fileMenu;
    }

    public File getFileSchemConf() {
        return fileSchemConf;
    }

    public File getFileGeneratorSettings() {
        return fileGeneratorSettings;
    }

    public HashMap<String, File> getSchematics() {
        return Schematics;
    }


    public boolean isLoaded() {
        return loaded;
    }

    public HashMap<String, File> getScripts() {
        return scripts;
    }

    public FileConfiguration getGeneratorSettings() {
        return generatorSettings;
    }
}
