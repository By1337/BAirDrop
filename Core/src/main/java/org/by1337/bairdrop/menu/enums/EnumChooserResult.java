package org.by1337.bairdrop.menu.enums;

import javax.annotation.Nullable;

/**
 * Represents a functional interface for handling the result of selecting an enum value.
 * This interface is intended to be used as a callback.
 *
 * @param <T> The enum type.
 */
@FunctionalInterface
public interface EnumChooserResult<T extends Enum<?>> {
    /**
     * Handles the result of selecting an enum value.
     *
     * @param result The selected enum value, or null if no value was selected.
     */
    void result(@Nullable T result);
}
