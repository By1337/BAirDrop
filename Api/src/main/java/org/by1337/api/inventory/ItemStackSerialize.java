package org.by1337.api.inventory;

import org.bukkit.inventory.ItemStack;

public interface ItemStackSerialize {
    String serialize(ItemStack itemStack) throws IllegalArgumentException;

    ItemStack deserialize(String data) throws IllegalArgumentException;
}
