package org.by1337.bairdrop.configManager;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.util.Vector;
import org.by1337.api.chat.util.LogLevel;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.ItemUtil.EnchantInfo;
import org.by1337.bairdrop.ItemUtil.EnchantMaterial;
import org.by1337.bairdrop.hologram.HologramManager;
import org.by1337.bairdrop.lang.Lang;
import org.by1337.bairdrop.location.CGenLoc;
import org.by1337.bairdrop.location.GenLoc;
import org.by1337.bairdrop.observer.CustomListenerLoader;
import org.by1337.bairdrop.worldGuardHook.RegionManager;

import org.by1337.bairdrop.menu.util.MenuItem;
import org.by1337.bairdrop.util.*;

import java.io.*;
import java.util.*;

import static org.by1337.bairdrop.BAirDrop.summoner;
import static org.by1337.bairdrop.location.GeneratorLoc.locs;
import static org.by1337.bairdrop.effect.LoadEffects.LoadEffect;


public class CConfig implements Config, ConfigMessage {
//    private FileConfiguration listeners;
//    private File fileListeners;
    private FileConfiguration effects;
    private File fileEffects;
    private FileConfiguration locations;
    private File fileLocations;
//    private FileConfiguration menu;
//    private File fileMenu;
    private FileConfiguration schemConf;
    private File fileSchemConf;
    //    private FileConfiguration generatorSettings;
//    private File fileGeneratorSettings;
    private HashMap<String, File> Schematics = new HashMap<>();
    private HashMap<File, FileConfiguration> airDrops = new HashMap<>();
    private Lang lang;
    private boolean loaded;
    public HashMap<String, File> scripts = new HashMap<>();

    private CustomListenerLoader listenerLoader;

    public CustomListenerLoader getListenerLoader() {
        return listenerLoader;
    }

    public void loadConfiguration() {

        HologramManager.loadHolograms();


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
            BAirDrop.MESSAGE.debug("load schematics" + shemFile.getAbsolutePath(), LogLevel.LEVEL_2);
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


        loadLang();

        File dir = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "airdrops");
//        if (!dir.exists()) {
//            dir.mkdir();
//            BAirDrop.getInstance().saveResource("airdrops" + File.separator + "default.yml", true);
//        }
        File dir2 = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "scripts");//diamond.js
        if (!dir2.exists()) {
            dir2.mkdir();
            BAirDrop.getInstance().saveResource("scripts" + File.separator + "diamond.js", true);
        }
        scripts.clear();
        for (File script : Arrays.stream(Objects.requireNonNull(dir2.listFiles())).toList()) {
            scripts.put(script.getName(), script);
            OLDMessage.debug("load " + script.getName(), LogLevel.LEVEL_0);
        }

        for (File airFile : Arrays.stream(Objects.requireNonNull(dir.listFiles())).toList()) {
            OLDMessage.debug("load " + airFile.getAbsolutePath(), LogLevel.LEVEL_0);
            FileConfiguration fc = YamlConfiguration.loadConfiguration(airFile);
            airDrops.put(airFile, fc);
        }
        //CustomListenerLoader.getCustomEventListeners().clear();
        listenerLoader = new CustomListenerLoader();
        //  listenerLoader.load();
        //  loadListeners();
        loadMenu();
        loadLocations();
        LoadEffect(effects);
        RegionManager.loadFlags();
        loadEnchant();
        summoner.LoadSummoner();
        // BAirDrop.compass = new Compass();
        //  BAirDrop.compass.loadItem();
        if (BAirDrop.getInstance().getConfig().getBoolean("custom-crafts.enable"))
            loadCustomCraft();
        loaded = true;
    }

    private void loadLang() {
//        String file = BAirDrop.BAirDrop.getInstance().getConfig().getString("lang", "en");
//        InputStream resourceStream = BAirDrop.getInstance().getResource("lang/" + file + ".json");
//        if (resourceStream == null) {
//            OLDMessage.error("file: " + "lang/" + file + ".json" + " not found!");
//            resourceStream = BAirDrop.getInstance().getResource("lang/en.json");
//            if (resourceStream == null) {
//                throw new IllegalStateException("OLDMessage file not found! Do you have the latest version of the plugin?");
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
//        MenuItem.menu ItemHashMap.clear();
//        for (String tag : menu.getConfigurationSection("main").getKeys(false)) {
//            try {
//                String name = Objects.requireNonNull(menu.getString("main." + tag + ".name"));
//                String material = Objects.requireNonNull(menu.getString("main." + tag + ".material"));
//                int slot = menu.getInt("main." + tag + ".slot");
//                List<String> lore = getListOrEmpty("main." + tag + ".lore", menu);
//                List<String> LEFT = getListOrEmpty("main." + tag + ".commands." + "LEFT-CLICK", menu);
//                List<String> SHIFT_LEFT = getListOrEmpty("main." + tag + ".commands." + "SHIFT_LEFT-CLICK", menu);
//                List<String> RIGHT = getListOrEmpty("main." + tag + ".commands." + "RIGHT-CLICK", menu);
//                List<String> SHIFT_RIGHT = getListOrEmpty("main." + tag + ".commands." + "SHIFT_RIGHT-CLICK", menu);
//                List<String> MIDDLE = getListOrEmpty("main." + tag + ".commands." + "MIDDLE-CLICK", menu);
//                List<String> DROP = getListOrEmpty("main." + tag + ".commands." + "DROP-CLICK", menu);
//                new MenuItem(tag, name, lore, slot, LEFT, SHIFT_LEFT, RIGHT, SHIFT_RIGHT, MIDDLE, DROP, material);
//            } catch (NullPointerException e) {
//                OLDMessage.error(String.format(getMessage("menu-error"), tag));
//
//            } catch (IllegalArgumentException e) {
//                OLDMessage.error(String.format(getMessage("menu-mat-error"), tag));
//            }
//        }
//
    }

    public void loadCustomCraft() {
        if (BAirDrop.getInstance().getConfig().getConfigurationSection("custom-crafts.crafts") == null) {
            OLDMessage.error(getMessage("craft-list-is-empty"));
            return;
        }
        main:
        for (String key : BAirDrop.getInstance().getConfig().getConfigurationSection("custom-crafts.crafts").getKeys(false)) {
            try {
                String summoner = Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("custom-crafts.crafts.%s.summoner", key)));
                if (!BAirDrop.summoner.getItems().containsKey(summoner)) {
                    OLDMessage.error(String.format(getMessage("craft-unknown-item"), summoner));
                    OLDMessage.error(String.format(getMessage("craft-skip"), key));
                    continue;
                }
                String top = Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("custom-crafts.crafts.%s.slots.top", key)));
                String middle = Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("custom-crafts.crafts.%s.slots.middle", key)));
                String bottom = Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("custom-crafts.crafts.%s.slots.bottom", key)));
                List<String> call = BAirDrop.getInstance().getConfig().getStringList(String.format("custom-crafts.crafts.%s.call", key));

                if (BAirDrop.getInstance().getConfig().getConfigurationSection(String.format("custom-crafts.crafts.%s.ingredients", key)) == null) {
                    OLDMessage.error(getMessage("craft-ingredients-is-empty"));
                    continue;
                }
                HashMap<Character, Material> ingredients = new HashMap<>();
                for (String ingred : BAirDrop.getInstance().getConfig().getConfigurationSection(String.format("custom-crafts.crafts.%s.ingredients", key)).getKeys(false)) {
                    if (ingred.length() > 1) {
                        OLDMessage.error(ingred + " Может состоять только из одного символа!");
                        OLDMessage.error(String.format(getMessage("craft-skip"), key));
                        continue main;
                    }
                    try {
                        ingredients.put(ingred.charAt(0), Material.valueOf(Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("custom-crafts.crafts.%s.ingredients.%s", key, ingred)))));
                    } catch (IllegalArgumentException e) {
                        OLDMessage.error(BAirDrop.getInstance().getConfig().getString(String.format("custom-crafts.crafts.%s.ingredients.%s", key, ingred)) + " Неизвестный материал!");
                        OLDMessage.error(String.format(getMessage("craft-skip"), key));
                        continue main;
                    }
                }
                BAirDrop.crafts.put(key, new CustomCraft(key, summoner, call, ingredients, top, middle, bottom));
            } catch (NullPointerException e) {
                OLDMessage.error(String.format(getMessage("craft-load-error"), key));
                e.printStackTrace();
            } catch (Exception e) {
                OLDMessage.error(String.format(getMessage("craft-load-error"), key));
                e.printStackTrace();
            }
        }
    }

    @Override
    public void loadListeners() {

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
                        OLDMessage.error("Неизвестный чар: " + enchant);
                        continue;
                    }
                    int chance = BAirDrop.getInstance().getConfig().getInt(String.format("auto-enchant.%s.%s.chance", id, enchant));
                    int minLevel = BAirDrop.getInstance().getConfig().getInt(String.format("auto-enchant.%s.%s.min-level", id, enchant));
                    int maxLevel = BAirDrop.getInstance().getConfig().getInt(String.format("auto-enchant.%s.%s.max-level", id, enchant));
                    //OLDMessage.debug(enchant + " = chance:" + chance + ", minLevel:" + minLevel + ", maxLevel:" + maxLevel);
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
        return path;
    }

    @Deprecated
    public List<String> getList(String path) {
        return List.of(path);
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
            OLDMessage.error(String.format(getMessage("gen-loc-world-is-null"), world));
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
        throw new IllegalStateException();
    }

    public File getFileListeners() {
        throw new IllegalStateException();
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
        throw new IllegalStateException();
    }

    public File getFileMenu() {
        throw new IllegalStateException();
    }

    public File getFileSchemConf() {
        return fileSchemConf;
    }

    public File getFileGeneratorSettings() {
        throw new IllegalStateException();
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
        throw new IllegalStateException();
    }
}
