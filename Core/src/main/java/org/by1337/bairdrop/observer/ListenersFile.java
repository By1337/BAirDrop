package org.by1337.bairdrop.observer;

import org.by1337.api.chat.util.LogLevel;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.api.configuration.YamlConfig;
import org.by1337.bairdrop.migrator.observer.ListenerMigrator;
import org.by1337.api.util.NameKey;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * The ListenersFile class represents a configuration file containing custom event listeners.
 * It loads and manages the custom event listeners defined in the configuration file.
 */
public class ListenersFile {

    private Map<NameKey, CustomEventListener> listeners;
    private final int version;

    /**
     * Constructs a new ListenersFile by loading data from the provided YamlConfig.
     *
     * @param yamlConfig The YamlConfig containing listener data.
     */
    public ListenersFile(@NotNull YamlConfig yamlConfig) {
        version = yamlConfig.getContext().getAsInteger("version", 0);

        if (ListenerMigrator.LASTED_VERSION != version) {
            new ListenerMigrator(yamlConfig, version).adapt();
        }

        listeners = yamlConfig.getContext().getMap("listeners", CustomEventListener.class, NameKey.class, new HashMap<>());
        BAirDrop.MESSAGE.debug("Loaded %s listeners! From '%s' file", LogLevel.LEVEL_2, listeners.size(), yamlConfig.getFile().getPath());
    }

    /**
     * Retrieves the map of custom event listeners defined in the configuration file.
     *
     * @return A map of NameKey (listener keys) to CustomEventListener objects.
     */
    public Map<NameKey, CustomEventListener> getListeners() {
        return listeners;
    }

    public void update(@NotNull NameKey key, Event event) {
        if (!listeners.containsKey(key)) {
            return;
        }
        listeners.get(key).update(event);
    }
}
