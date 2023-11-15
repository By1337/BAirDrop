package org.by1337.bairdrop.observer.event;

import org.by1337.api.util.NameKey;
import org.by1337.api.util.Named;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public record CustomEvent(NameKey nameKey) implements Named {
    private static final Map<NameKey, CustomEvent> byKey = new HashMap<>();

    /**
     * when someone clicks on a closed airdrop
     * Player not null, AirDrop not null
     */
    public static final CustomEvent CLICK_CLOSE = registerEvent(new CustomEvent(new NameKey("click_close")));

    /**
     * when someone clicks on an open airdrop
     * Player not null, AirDrop not null
     */
    public static final CustomEvent CLICK_OPEN = registerEvent(new CustomEvent(new NameKey("click_open")));

    /**
     * an airdrop is called every second
     * Player null, AirDrop not null
     */
    public static final CustomEvent TIMER = registerEvent(new CustomEvent(new NameKey("timer")));

    /**
     * when an airdrop spawns
     * Player null, AirDrop not null
     */
    public static final CustomEvent START_EVENT = registerEvent(new CustomEvent(new NameKey("start_event")));

    /**
     * when the airdrop ends
     * Player null, AirDrop not null
     */
    public static final CustomEvent END_EVENT = registerEvent(new CustomEvent(new NameKey("end_event")));

    /**
     * when the airdrop opens
     * Player null, AirDrop not null
     */
    public static final CustomEvent UNLOCK_EVENT = registerEvent(new CustomEvent(new NameKey("unlock_event")));

    /**
     * when someone opens the airdrop for the first time
     * Player not null, AirDrop not null
     */
    public static final CustomEvent FIRST_OPEN = registerEvent(new CustomEvent(new NameKey("first_open")));

    /**
     * when someone summons the airdrop
     * Player not null, AirDrop not null
     */
    public static final CustomEvent SUMMONER = registerEvent(new CustomEvent(new NameKey("summoner")));

    /**
     * when someone activates the airdrop
     * Player not null, AirDrop not null
     */
    public static final CustomEvent ACTIVATE = registerEvent(new CustomEvent(new NameKey("activate")));

    /**
     * when the airdrop ends due to depletion, also triggers END_EVENT
     * Player null, AirDrop not null
     */
    public static final CustomEvent STOP_WHEN_EMPTY = registerEvent(new CustomEvent(new NameKey("stop_when_empty")));

    /**
     * this event is not triggered and the listeners subscribed to this event are not displayed in the menu
     * Player not null/null, AirDrop not null/null
     */
    public static final CustomEvent NONE = registerEvent(new CustomEvent(new NameKey("none")));

    /**
     * when someone crafts an item
     * Player not null, AirDrop null
     */
    public static final CustomEvent CRAFT_ITEM = registerEvent(new CustomEvent(new NameKey("craft_item")));

    /**
     * when the airdrop is unloaded
     * Player null, AirDrop not null
     */
    public static final CustomEvent UNLOAD = registerEvent(new CustomEvent(new NameKey("unload")));

    /**
     * when the airdrop is loaded
     * Player null, AirDrop not null
     */
    public static CustomEvent LOAD = registerEvent(new CustomEvent(new NameKey("load")));
    /**
     * when the player use cheat steal chest
     * Player null, AirDrop not null
     */
    public static CustomEvent PLAYER_STEAL = registerEvent(new CustomEvent(new NameKey("player_steal")));
    /**
     * when airdrop deserialize
     * Player null, AirDrop not null
     */
    @Deprecated
    public static CustomEvent DESERIALIZE = registerEvent(new CustomEvent(new NameKey("deserialize")));


    public static CustomEvent CLICK = registerEvent(new CustomEvent(new NameKey("click")));

    /**
     * @param key Key of the event
     * @return null if the event does not exist, otherwise returns CustomEvent
     */
    @Nullable
    public static CustomEvent getByKey(NameKey key) {
        return byKey.getOrDefault(key, null);
    }

    /**
     * Registers a custom event
     * @param customEvent the event to register
     * @return the registered event object
     * @throws IllegalArgumentException if this event is already registered
     */
    public static CustomEvent registerEvent(CustomEvent customEvent) {
        if (byKey.containsKey(customEvent.getKey())) {
            throw new IllegalArgumentException("Cannot set already-set customEvent: " + customEvent.getKey().getName());
        }
        byKey.put(customEvent.getKey(), customEvent);
        return customEvent;
    }

    /**
     * @param customEvent
     * @return returns true if this event is already registered
     */
    public static boolean hasEvent(CustomEvent customEvent){
        return byKey.containsKey(customEvent.nameKey);
    }

    public static void unRegisterEvent(CustomEvent event){
        byKey.remove(event.nameKey);
    }

    @NotNull
    public NameKey getKey() {
        return nameKey;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CustomEvent customEvent = (CustomEvent) obj;
        return this.nameKey.equals(customEvent.nameKey);
    }

    @Override
    public @NotNull NameKey getName() {
        return nameKey;
    }

    @Override
    public String toString() {
        return "CustomEvent{" +
                "nameKey=" + nameKey +
                '}';
    }
}

