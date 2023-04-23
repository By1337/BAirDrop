package org.by1337.bairdrop.util;

import org.bukkit.enchantments.Enchantment;

public class EnchantInfo {
    private final int chance;
    private final int minLeve;
    private final int maxLeve;
    private final Enchantment enchantment;

    public EnchantInfo(int chance, int minLeve, int maxLeve, Enchantment enchantment) {
        this.chance = chance;
        this.minLeve = minLeve;
        this.maxLeve = maxLeve;
        this.enchantment = enchantment;
    }

    public int getChance() {
        return chance;
    }

    public int getMinLeve() {
        return minLeve;
    }

    public int getMaxLeve() {
        return maxLeve;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }
}
