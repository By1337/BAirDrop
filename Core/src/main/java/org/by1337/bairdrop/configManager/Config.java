package org.by1337.bairdrop.configManager;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface Config {
    void loadConfiguration();
    void loadMenu();
    void loadCustomCraft();
    void loadListeners();
    void loadEnchant();
    String getMessage(String path);
    String getMessageFromPlugin(String path);
    List<String> getList(String path);
    List<String> getListFromPlugin(String path);
    List<String> getListOrEmpty(String path, FileConfiguration file);
    void loadLocations();
    @Deprecated
    boolean loadOldLoc(String airId, String world, String uuid);
    HashMap<File, FileConfiguration> getAirDrops();
    FileConfiguration getSchemConf();
    FileConfiguration getListeners();
    File getFileListeners();
    FileConfiguration getEffects();
    File getFileEffects();
    FileConfiguration getLocations();
    File getFileLocations();
    FileConfiguration getMenu();
    File getFileMenu();
    File getFileSchemConf();
    File getFileGeneratorSettings();
    HashMap<String, File> getSchematics();
    FileConfiguration getMessage();
    File getFileMessage();
    boolean isLoaded();
    HashMap<String, File> getScripts();
    FileConfiguration getGeneratorSettings();


}
