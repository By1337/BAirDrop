package org.by1337.bairdrop.effect;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public record EffectType(NamespacedKey key) implements Keyed {
    private static final HashMap<NamespacedKey, EffectType> byKey = new HashMap<>();
    public static EffectType CIRCLE = registerEffect(new EffectType(NamespacedKey.fromString("circle")));
    public static EffectType EXPANDING_CIRCLE = registerEffect(new EffectType(NamespacedKey.fromString("expanding_circle")));
    public static EffectType FIREWORK = registerEffect(new EffectType(NamespacedKey.fromString("firework")));
    public static EffectType SPAWN_GUARD = registerEffect(new EffectType(NamespacedKey.fromString("spawn_guard")));
    public static EffectType RANDOM_PARTICLE = registerEffect(new EffectType(NamespacedKey.fromString("random_particle")));
    public static EffectType TORUS = registerEffect(new EffectType(NamespacedKey.fromString("torus")));
    public static EffectType WRITHING_HELIX = registerEffect(new EffectType(NamespacedKey.fromString("writhing_helix")));
    public static EffectType HELIX = registerEffect(new EffectType(NamespacedKey.fromString("helix")));
    public static EffectType PARTICLE_EXPLOSION = registerEffect(new EffectType(NamespacedKey.fromString("particle_explosion")));

    /**
     * @param key NamespacedKey of the event
     * @return null if the EffectType does not exist, otherwise returns EffectType
     */
    @Nullable
    public static EffectType getByKey(NamespacedKey key) {
        return byKey.getOrDefault(key, null);
    }

    /**
     * Registers a custom event
     * @param effectType the effectType to register
     * @return the registered effectType object
     * @throws IllegalArgumentException if this effectType is already registered
     */
    public static EffectType registerEffect(EffectType effectType) {
        if (byKey.containsKey(effectType.getKey())) {
            throw new IllegalArgumentException("Cannot set already-set effectType: " + effectType.getKey().getKey());
        }
        byKey.put(effectType.getKey(), effectType);
        return effectType;
    }

    public static boolean hasEffectType(EffectType effectType){
        return byKey.containsKey(effectType.getKey());
    }
    public static void unRegisterEffectType(EffectType effectType){
        byKey.remove(effectType.getKey());
    }
    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

}
