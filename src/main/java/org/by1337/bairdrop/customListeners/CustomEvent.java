package org.by1337.bairdrop.customListeners;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.by1337.bairdrop.obfuscate.DontObfuscate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
@DontObfuscate
public record CustomEvent(NamespacedKey key) implements Keyed {
    private static final HashMap<NamespacedKey, CustomEvent> byKey = new HashMap<>();
    public static final CustomEvent CLICK_CLOSE = registerEvent(new CustomEvent(NamespacedKey.fromString("click_close")));
    public static final CustomEvent CLICK_OPEN = registerEvent(new CustomEvent(NamespacedKey.fromString("click_open")));
    public static final CustomEvent TIMER = registerEvent(new CustomEvent(NamespacedKey.fromString("timer")));
    public static final CustomEvent START_EVENT = registerEvent(new CustomEvent(NamespacedKey.fromString("start_event")));
    public static final CustomEvent END_EVENT = registerEvent(new CustomEvent(NamespacedKey.fromString("end_event")));
    public static final CustomEvent UNLOCK_EVENT = registerEvent(new CustomEvent(NamespacedKey.fromString("unlock_event")));
    public static final CustomEvent FIRST_OPEN = registerEvent(new CustomEvent(NamespacedKey.fromString("first_open")));
    public static final CustomEvent SUMMONER = registerEvent(new CustomEvent(NamespacedKey.fromString("summoner")));
    public static final CustomEvent ACTIVATE = registerEvent(new CustomEvent(NamespacedKey.fromString("activate")));
    public static final CustomEvent STOP_WHEN_EMPTY = registerEvent(new CustomEvent(NamespacedKey.fromString("stop_when_empty")));
    public static final CustomEvent NONE = registerEvent(new CustomEvent(NamespacedKey.fromString("none")));
    public static final CustomEvent CRAFT_ITEM = registerEvent(new CustomEvent(NamespacedKey.fromString("craft_item")));
    public static final CustomEvent UNLOAD = registerEvent(new CustomEvent(NamespacedKey.fromString("unload")));
   // public static CustomEvent CLONE_CREATE = registerEvent(new CustomEvent(NamespacedKey.fromString("clone_create")));
    public static CustomEvent LOAD = registerEvent(new CustomEvent(NamespacedKey.fromString("load")));

    @Nullable
    public static CustomEvent getByKey(NamespacedKey key) {
        return byKey.getOrDefault(key, null);
    }

    public static CustomEvent registerEvent(CustomEvent customEvent) {
        if (byKey.containsKey(customEvent.getKey())) {
            throw new IllegalArgumentException("Cannot set already-set customEvent: " + customEvent.getKey().getKey());
        }
        byKey.put(customEvent.getKey(), customEvent);
        return customEvent;
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
