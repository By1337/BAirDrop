package org.by1337.bairdrop.customListeners;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public record CustomEvent(NamespacedKey key) implements Keyed {
    private static final HashMap<NamespacedKey, CustomEvent> byKey = new HashMap<>();

    /**
     * when someone clicks on a closed airdrop
     * Player not null, AirDrop not null
     */
    public static final CustomEvent CLICK_CLOSE = registerEvent(new CustomEvent(NamespacedKey.fromString("click_close")));

    /**
     * when someone clicks on an open airdrop
     * Player not null, AirDrop not null
     */
    public static final CustomEvent CLICK_OPEN = registerEvent(new CustomEvent(NamespacedKey.fromString("click_open")));

    /**
     * an airdrop is called every second
     * Player null, AirDrop not null
     */
    public static final CustomEvent TIMER = registerEvent(new CustomEvent(NamespacedKey.fromString("timer")));

    /**
     * when an airdrop spawns
     * Player null, AirDrop not null
     */
    public static final CustomEvent START_EVENT = registerEvent(new CustomEvent(NamespacedKey.fromString("start_event")));

    /**
     * when the airdrop ends
     * Player null, AirDrop not null
     */
    public static final CustomEvent END_EVENT = registerEvent(new CustomEvent(NamespacedKey.fromString("end_event")));

    /**
     * when the airdrop opens
     * Player null, AirDrop not null
     */
    public static final CustomEvent UNLOCK_EVENT = registerEvent(new CustomEvent(NamespacedKey.fromString("unlock_event")));

    /**
     * when someone opens the airdrop for the first time
     * Player not null, AirDrop not null
     */
    public static final CustomEvent FIRST_OPEN = registerEvent(new CustomEvent(NamespacedKey.fromString("first_open")));

    /**
     * when someone summons the airdrop
     * Player not null, AirDrop not null
     */
    public static final CustomEvent SUMMONER = registerEvent(new CustomEvent(NamespacedKey.fromString("summoner")));

    /**
     * when someone activates the airdrop
     * Player not null, AirDrop not null
     */
    public static final CustomEvent ACTIVATE = registerEvent(new CustomEvent(NamespacedKey.fromString("activate")));

    /**
     * when the airdrop ends due to depletion, also triggers END_EVENT
     * Player null, AirDrop not null
     */
    public static final CustomEvent STOP_WHEN_EMPTY = registerEvent(new CustomEvent(NamespacedKey.fromString("stop_when_empty")));

    /**
     * this event is not triggered and the listeners subscribed to this event are not displayed in the menu
     * Player not null/null, AirDrop not null/null
     */
    public static final CustomEvent NONE = registerEvent(new CustomEvent(NamespacedKey.fromString("none")));

    /**
     * when someone crafts an item
     * Player not null, AirDrop null
     */
    public static final CustomEvent CRAFT_ITEM = registerEvent(new CustomEvent(NamespacedKey.fromString("craft_item")));

    /**
     * when the airdrop is unloaded
     * Player null, AirDrop not null
     */
    public static final CustomEvent UNLOAD = registerEvent(new CustomEvent(NamespacedKey.fromString("unload")));

    /**
     * when the airdrop is loaded
     * Player null, AirDrop not null
     */
    public static CustomEvent LOAD = registerEvent(new CustomEvent(NamespacedKey.fromString("load")));
    /**
     * when the player use cheat steal chest
     * Player null, AirDrop not null
     */
    public static CustomEvent PLAYER_STEAL = registerEvent(new CustomEvent(NamespacedKey.fromString("player_steal")));
    /**
     * when airdrop deserialize
     * Player null, AirDrop not null
     */
    public static CustomEvent DESERIALIZE = registerEvent(new CustomEvent(NamespacedKey.fromString("deserialize")));

    /**
     * @param key NamespacedKey of the event
     * @return null if the event does not exist, otherwise returns CustomEvent
     */
    @Nullable
    public static CustomEvent getByKey(NamespacedKey key) {
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
            throw new IllegalArgumentException("Cannot set already-set customEvent: " + customEvent.getKey().getKey());
        }
        byKey.put(customEvent.getKey(), customEvent);
        return customEvent;
    }

    /**
     * @param customEvent
     * @return returns true if this event is already registered
     */
    public static boolean hasEvent(CustomEvent customEvent){
        return byKey.containsKey(customEvent.key);
    }

    public static void unRegisterEvent(CustomEvent event){
        byKey.remove(event.key);
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof CustomEvent customEvent) {
            return this.key.getKey().equals(customEvent.key.getKey());
           // return this.hashCode() == customEvent.hashCode();
        } else
            return false;
    }

    @Override
    public int hashCode() {
        if(this.key == null)
            return 0;

        int result = 0;
        for (byte element : this.key.getKey().getBytes())
            result = 45 * result + element;
        return result;
    }
}

