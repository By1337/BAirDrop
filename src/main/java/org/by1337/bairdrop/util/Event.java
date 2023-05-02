package org.by1337.bairdrop.util;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public record Event(NamespacedKey key) implements Keyed {
    private static final HashMap<NamespacedKey, Event> byKey = new HashMap<>();
    public static Event CLICK_CLOSE = registerEvent(new Event(NamespacedKey.fromString("click_close")));
    public static Event CLICK_OPEN = registerEvent(new Event(NamespacedKey.fromString("click_open")));
    public static Event TIMER = registerEvent(new Event(NamespacedKey.fromString("timer")));
    public static Event START_EVENT = registerEvent(new Event(NamespacedKey.fromString("start_event")));
    public static Event END_EVENT = registerEvent(new Event(NamespacedKey.fromString("end_event")));
    public static Event UNLOCK_EVENT = registerEvent(new Event(NamespacedKey.fromString("unlock_event")));
    public static Event FIRST_OPEN = registerEvent(new Event(NamespacedKey.fromString("first_open")));
    public static Event SUMMONER = registerEvent(new Event(NamespacedKey.fromString("summoner")));
    public static Event ACTIVATE = registerEvent(new Event(NamespacedKey.fromString("activate")));
    public static Event STOP_WHEN_EMPTY = registerEvent(new Event(NamespacedKey.fromString("stop_when_empty")));
    public static Event NONE = registerEvent(new Event(NamespacedKey.fromString("none")));
    public static Event CRAFT_ITEM = registerEvent(new Event(NamespacedKey.fromString("craft_item")));
    public static Event UNLOAD = registerEvent(new Event(NamespacedKey.fromString("unload")));

    @Nullable
    public static Event getByKey(NamespacedKey key) {
        return byKey.getOrDefault(key, null);
    }

    public static Event registerEvent(Event event) {
        if (byKey.containsKey(event.getKey())) {
            throw new IllegalArgumentException("Cannot set already-set event: " + event.getKey().getKey());
        }
        byKey.put(event.getKey(), event);
        return event;
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
