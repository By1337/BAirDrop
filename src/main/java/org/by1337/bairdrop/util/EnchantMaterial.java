package org.by1337.bairdrop.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.by1337.bairdrop.BAirDrop;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class EnchantMaterial {
    public static HashMap<String, EnchantMaterial> materialHashMap = new HashMap<>();
    private final Material material;
    private final List<Enchantment> conflictEnchantments;
    private final List<EnchantInfo> enchantInfos;
    private final Random random = new Random();

    public EnchantMaterial(Material material, List<Enchantment> conflictEnchantments, List<EnchantInfo> enchantInfos) {
        this.material = material;
        this.conflictEnchantments = conflictEnchantments;
        this.enchantInfos = enchantInfos;
    }

    public ItemStack enchant(@NotNull ItemStack itemStack) {
        if (itemStack.getItemMeta() == null) return itemStack;
        ItemMeta im = itemStack.getItemMeta();
        for (Map.Entry<Enchantment, Integer> map : im.getEnchants().entrySet()) {
            if (conflictEnchantments.contains(map.getKey())) return itemStack;
        }

        for (EnchantInfo ei : enchantInfos) {
            Message.debug("ei.getMaxLeve(): " + ei.getMaxLevel() + ", ei.getMinLeve(): " + ei.getMinLevel());
            try {
                if (ei.getChance() > random.nextInt(Integer.toBinaryString(BAirDrop.info[5]).length() * 10)) {//100
                    int level = random.nextInt(ei.getMaxLevel() - ei.getMinLevel() + 1);
                    level += ei.getMinLevel();
                    im.addEnchant(ei.getEnchantment(), level, true);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }


        itemStack.setItemMeta(im);
        return itemStack;
    }

    public Material getMaterial() {
        return material;
    }

    public List<Enchantment> getConflictEnchantments() {
        return conflictEnchantments;
    }

    public List<EnchantInfo> getEnchantInfos() {
        return enchantInfos;
    }
}
