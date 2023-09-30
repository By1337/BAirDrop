package org.by1337.bairdrop.observer.requirement;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.by1337.bairdrop.observer.CustomEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public record RequirementType(NamespacedKey key) implements Keyed {
    private static final HashMap<NamespacedKey, RequirementType> byKey = new HashMap<>();

    public static final RequirementType STRING_CHECK  = register(new RequirementType(NamespacedKey.fromString("string_check")));
    public static final RequirementType NUMERICAL_CHECK  = register(new RequirementType(NamespacedKey.fromString("numerical_check")));
    public static final RequirementType HAS_PERMISSION  = register(new RequirementType(NamespacedKey.fromString("has_permission")));

    @Nullable
    public static RequirementType getByKey(NamespacedKey key) {
        return byKey.getOrDefault(key, null);
    }

    public static RequirementType register(RequirementType type) {
        if (byKey.containsKey(type.getKey())) {
            throw new IllegalArgumentException("Cannot set already-set type: " + type.getKey().getKey());
        }
        byKey.put(type.getKey(), type);
        return type;
    }

    public static boolean has(RequirementType type) {
        return byKey.containsKey(type.key);
    }

    public static void unregister(RequirementType type) {
        byKey.remove(type.key);
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
