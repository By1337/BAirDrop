package org.by1337.bairdrop.ItemUtil;

import org.bukkit.enchantments.Enchantment;
import org.by1337.bairdrop.util.Message;

public class EnchantInfo {
    private final int chance;
    private final int minLevel;
    private final int maxLevel;
    private final Enchantment enchantment;

    public EnchantInfo(int chance, int minLevel, int maxLevel, Enchantment enchantment) {
        this.chance = chance;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.enchantment = enchantment;
        if(maxLevel < minLevel){
            Message.error("max-level cannot be less than min-level." + enchantment);
            maxLevel = minLevel+1;
        }
    }

    public int getChance() {
        return chance;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }
}
