package org.by1337.api.inventory;

import org.bukkit.inventory.ItemStack;

/**
 * An interface for serializing and deserializing Bukkit ItemStacks to/from string representations.
 */
public interface ItemStackSerialize {
    /**
     * Serializes the given ItemStack into a string representation.
     *
     * @param itemStack The ItemStack to be serialized.
     * @return A string representation of the serialized ItemStack.
     * @throws IllegalArgumentException If the serialization fails or the ItemStack is invalid.
     */
    String serialize(ItemStack itemStack) throws IllegalArgumentException;

    /**
     * Deserializes an ItemStack from the provided string representation.
     *
     * @param data The string representation of the ItemStack.
     * @return The deserialized ItemStack.
     * @throws IllegalArgumentException If the deserialization fails or the string data is invalid.
     */
    ItemStack deserialize(String data) throws IllegalArgumentException;
}
