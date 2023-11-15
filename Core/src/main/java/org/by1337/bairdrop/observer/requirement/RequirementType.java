package org.by1337.bairdrop.observer.requirement;

import org.by1337.bairdrop.observer.requirement.impl.RequirementHasPermission;
import org.by1337.bairdrop.observer.requirement.impl.RequirementNumericalCheck;
import org.by1337.bairdrop.observer.requirement.impl.RequirementStringCheck;
import org.by1337.api.util.NameKey;
import org.by1337.api.util.Named;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public record RequirementType(@NotNull NameKey key,
                              @NotNull RequirementSuppler<Requirement> supplier) implements Named {
    private static final HashMap<NameKey, RequirementType> byKey = new HashMap<>();

    public static final RequirementType STRING_CHECK = register(new RequirementType(new NameKey("string_check"), RequirementStringCheck::new));
    public static final RequirementType NUMERICAL_CHECK = register(new RequirementType(new NameKey("numerical_check"), RequirementNumericalCheck::new));
    public static final RequirementType HAS_PERMISSION = register(new RequirementType(new NameKey("has_permission"), RequirementHasPermission::new));

    @Nullable
    public static RequirementType getByKey(@NotNull NameKey key) {
        return byKey.getOrDefault(key, null);
    }

    public static RequirementType register(@NotNull RequirementType type) {
        if (byKey.containsKey(type.getName())) {
            throw new IllegalArgumentException("Cannot set already-set type: " + type.getName());
        }
        byKey.put(type.getName(), type);
        return type;
    }

    public static boolean has(RequirementType type) {
        return byKey.containsKey(type.key);
    }

    public static void unregister(@NotNull RequirementType type) {
        byKey.remove(type.key);
    }


    @Override
    public @NotNull NameKey getName() {
        return key;
    }

    public interface RequirementSuppler<T> {
        T get(String string);
    }

}
