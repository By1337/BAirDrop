package org.by1337.bairdrop.ItemUtil;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Items {
    private int slot;
    private int chance;
    private ItemStack item;
    private final String inv;

    public Items(int slot, int chance, @NotNull ItemStack item, String inv) {
        this.slot = slot;
        this.chance = chance;
        this.item = item;
        this.inv = inv;
    }

    public int getSlot() {
        return slot;
    }

    public int getChance() {
        return chance;
    }

    public ItemStack getItem() {
        return item.clone();
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public String getInv() {
        return inv;
    }
}
