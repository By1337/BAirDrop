package org.by1337.bairdrop.hologram;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.api.hologaram.Hologram;
import org.by1337.api.chat.Placeholderable;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class HologramManager {
    private static final HashMap<String, Hologram.Setting> hologramSettings = new HashMap<>();
    private static final HashMap<String, Hologram> holograms = new HashMap<>();

    /**
     * Loads hologram settings from configuration files.
     */
    public static void loadHolograms() {
        hologramSettings.clear();
        File dir = new File(BAirDrop.getInstance().getDataFolder() + "/hologram");

        if (!dir.exists()) {
            dir.mkdir();
            BAirDrop.getInstance().saveResource("hologram/default.yml", false);
        }

        for (File file : dir.listFiles()) {
            if (file.getName().endsWith(".yml")) {
                Hologram.Setting setting = Hologram.Setting.load(YamlConfiguration.loadConfiguration(file));
                hologramSettings.put(file.getName().split(".yml")[0], setting);
            }
        }
    }

    /**
     * Creates a hologram at the specified location.
     *
     * @param location       The location where the hologram should be created.
     * @param settingName    The name of the hologram's settings.
     * @param holoName       The unique name for the hologram.
     * @param placeholderable An object that supports placeholders.
     * @throws IllegalStateException If a hologram with the same name already exists or if the specified settings don't exist.
     */
    public static void createHologram(Location location, String settingName, String holoName, Placeholderable placeholderable) {
        if (holograms.containsKey(holoName)) {
            throw new IllegalStateException(String.format("A hologram with the name '%s' already exists.", holoName));
        }
        if (!hologramSettings.containsKey(settingName)) {
            throw new IllegalStateException(String.format("No settings found with the name '%s'.", settingName));
        }
        Hologram hologram = new Hologram(
                hologramSettings.get(settingName),
                location,
                placeholderable,
                BAirDrop.getInstance());
        hologram.start();
        holograms.put(holoName, hologram);
    }

    /**
     * Stops and removes a hologram by its name.
     *
     * @param holoName The name of the hologram to stop and remove.
     * @throws IllegalStateException If no hologram with the specified name is found.
     */
    public static void stopAndRemoveHologram(String holoName) {
        if (!holograms.containsKey(holoName)) {
            throw new IllegalStateException(String.format("No hologram found with the name '%s'.", holoName));
        }
        Hologram hologram = holograms.get(holoName);
        hologram.stop();
        holograms.remove(holoName);
    }

    /**
     * Stops and removes all holograms.
     */
    public static void stopAndRemoveAllHolograms() {
        for (String s : holograms.keySet()) {
            holograms.get(s).stop();
        }
        holograms.clear();
    }

    /**
     * Gets the set of keys for available hologram settings.
     *
     * @return A set of strings representing the keys for hologram settings.
     */
    public static Set<String> settingKeySet(){
        return hologramSettings.keySet();
    }
}
