package org.by1337.bairdrop.airdrop;


import org.by1337.api.BLib;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TimerRegistry {
    /**
     * Storage for timers indexed by name.
     */
    private static final HashMap<String, Timer> timers = new HashMap<>();

    /**
     * Registers a timer in the registry.
     *
     * @param timer The timer to register.
     * @param name  The name under which the timer will be registered.
     * @throws IllegalArgumentException If a timer with the same name already exists in the registry.
     */
    public static void register(Timer timer, String name) {
        BLib.catchOp("Asynchronous timer registration");
        if (timers.containsKey(name)) {
            throw new IllegalArgumentException("A timer with the same name already exists in the registry.");
        }
        timers.put(name, timer);
    }

    /**
     * Unregisters a timer from the registry by name.
     *
     * @param name The name of the timer to unregister.
     * @throws IllegalArgumentException If no timer with the specified name is found in the registry.
     */
    public static void unregister(String name) {
        if (!timers.containsKey(name)) {
            throw new IllegalArgumentException("No timer with the specified name found in the registry.");
        }
        timers.remove(name);
    }

    /**
     * Gets a timer from the registry by name.
     *
     * @param name The name of the timer to retrieve.
     * @return The timer with the specified name, or null if no timer is found with that name.
     * @throws IllegalArgumentException If no timer with the specified name is found in the registry.
     */
    public static Timer get(String name) {
        if (!timers.containsKey(name)) {
            throw new IllegalArgumentException("No timer with the specified name found in the registry.");
        }
        return timers.get(name);
    }

    /**
     * Returns a set view of the mappings contained in the registry.
     * The set is backed by the registry, so changes to the registry are reflected in the set, and vice versa.
     *
     * @return A set view of the mappings contained in the registry.
     */
    public static Set<Map.Entry<String, Timer>> entrySet() {
        return timers.entrySet();
    }

    /**
     * Checks if the registry contains a timer with the specified name.
     *
     * @param name The name of the timer to check for.
     * @return {@code true} if a timer with the specified name exists in the registry, {@code false} otherwise.
     */
    public static boolean containsTimer(String name) {
        return timers.containsKey(name);
    }

    /**
     * Stops all timers in the registry by canceling their associated tasks.
     */
    public static void stopAll() {
        for (Timer timer : timers.values()) {
            timer.getTask().cancel();
        }
    }

    /**
     * Clears all timers from the registry.
     */
    public static void clear() {
        timers.clear();
    }
}
