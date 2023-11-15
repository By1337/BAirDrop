package org.by1337.bairdrop.observer;

import org.bukkit.configuration.InvalidConfigurationException;
import org.by1337.api.configuration.YamlConfig;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.api.util.NameKey;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * The CustomListenerLoader class is responsible for loading and managing custom event listeners.
 * It scans a directory for listener configuration files, loads them, and adds them to a HashMap for easy access.
 */
public class CustomListenerLoader {

    private List<ListenersFile> listeners = new ArrayList<>();


    /**
     * Constructs a new CustomListenerLoader. It initializes the directory for listener configurations and loads listeners.
     */
    public CustomListenerLoader() {
        File dir = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "listeners");
        if (!dir.exists()) {
            dir.mkdir();
        }
        listeners = loadListeners(dir);
    }

    /**
     * Recursively loads listener configuration files from the given directory and returns a list of ListenersFile objects.
     *
     * @param dir The directory to scan for listener configuration files.
     * @return A list of ListenersFile objects representing loaded listener configurations.
     */
    private List<ListenersFile> loadListeners(File dir) {
        List<ListenersFile> list = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                list.addAll(loadListeners(file));
            } else if (file.getName().endsWith(".yml")) {
                try {
                    list.add(new ListenersFile(new YamlConfig(file)));
                } catch (IOException | InvalidConfigurationException e) {
                    BAirDrop.MESSAGE.error("failed to load listeners! ", e);
                }
            }
        }
        return list;
    }


    /**
     * Updates a custom event listener with the given key using the provided event.
     *
     * @param key   The key associated with the custom event listener to update.
     * @param event The event to use for the update.
     * @throws IllegalArgumentException if no listener is found for the given key.
     */
    public void update(@NotNull NameKey key, Event event) {
        for (ListenersFile listener : listeners) {
            listener.update(key, event);
        }
    }

    public List<ListenersFile> getListeners() {
        return listeners;
    }
}
