package org.by1337.bairdrop.menu.property.property;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public record PropertyType(NamespacedKey key) implements Keyed {
    private static final HashMap<NamespacedKey, PropertyType> byKey = new HashMap<>();

    public static final PropertyType VECTOR = register(new PropertyType(NamespacedKey.fromString("vector")));
    public static final PropertyType STRING = register(new PropertyType(NamespacedKey.fromString("string")));
    public static final PropertyType PARTICLE = register(new PropertyType(NamespacedKey.fromString("particle")));
    public static final PropertyType LONG = register(new PropertyType(NamespacedKey.fromString("long")));
    public static final PropertyType INTEGER = register(new PropertyType(NamespacedKey.fromString("integer")));
    public static final PropertyType DOUBLE = register(new PropertyType(NamespacedKey.fromString("double")));
    public static final PropertyType COLOR = register(new PropertyType(NamespacedKey.fromString("color")));
    public static final PropertyType BOOLEAN = register(new PropertyType(NamespacedKey.fromString("boolean")));
    public static final PropertyType LIST_STRING = register(new PropertyType(NamespacedKey.fromString("list_string")));
    public static final PropertyType LOCATION = register(new PropertyType(NamespacedKey.fromString("location")));
    public static final PropertyType MATERIAL = register(new PropertyType(NamespacedKey.fromString("material")));


    @Nullable
    public static PropertyType getByKey(NamespacedKey key) {
        return byKey.getOrDefault(key, null);
    }

    public static PropertyType register(PropertyType type) {
        if (byKey.containsKey(type.getKey())) {
            throw new IllegalArgumentException("Cannot set already-set type: " + type.getKey().getKey());
        }
        byKey.put(type.getKey(), type);
        return type;
    }

    public static boolean has(PropertyType propertyType) {
        return byKey.containsKey(propertyType.key);
    }

    public static void unregister(PropertyType propertyType) {
        byKey.remove(propertyType.key);
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }
}