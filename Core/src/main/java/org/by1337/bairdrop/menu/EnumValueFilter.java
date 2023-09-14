package org.by1337.bairdrop.menu;

/**
 * Represents a functional interface for filtering enum values.
 *
 * @param <T> The enum type.
 */
@FunctionalInterface
public interface EnumValueFilter<T extends Enum<?>> {
    /**
     * Checks whether an enum value should be skipped or not.
     *
     * @param value The enum value to check.
     * @return true if the value should be skipped, false otherwise.
     */
    boolean isSkip(T value);
}
