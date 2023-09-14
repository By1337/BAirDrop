package org.by1337.bairdrop.menu.property.enums;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a functional interface for converting enum values to ItemStacks.
 *
 * @param <T> The enum type.
 */
@FunctionalInterface
public interface EnumToItemStackConverter<T extends Enum<?>> {
    /**
     * Converts an enum value to an ItemStack.
     *
     * @param enumValue The enum value to convert.
     * @return The resulting ItemStack.
     */
    @NotNull
    ItemStack convertToMaterial(T enumValue);
}
