package org.by1337.bairdrop.menu.util;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.ItemUtil.BaseHeadHook;

import org.jetbrains.annotations.NotNull;

import java.util.*;

import org.by1337.bairdrop.util.Message;

public class MenuItem {
    public static HashMap<String, MenuItem> menuItemHashMap = new HashMap<>();
    private final String tag;
    private final String name;
    private final List<String> lore;
    private final int slot;
    private final List<String> LEFT_COMMANDS;
    private final List<String> SHIFT_LEFT_COMMANDS;
    private final List<String> RIGHT_COMMANDS;
    private final List<String> SHIFT_RIGHT_COMMANDS;
    private final List<String> MIDDLE_COMMANDS;
    private final List<String> DROP_COMMANDS;
    private final String material;

    public MenuItem(@NotNull String tag, String name, List<String> lore, int slot, List<String> LEFT_COMMANDS, List<String> SHIFT_LEFT_COMMANDS, List<String> RIGHT_COMMANDS, List<String> SHIFT_RIGHT_COMMANDS, List<String> MIDDLE_COMMANDS, List<String> DROP_COMMANDS, String material) {
        this.tag = tag;
        this.name = name;
        this.lore = lore;
        this.slot = slot;
        this.LEFT_COMMANDS = LEFT_COMMANDS;
        this.SHIFT_LEFT_COMMANDS = SHIFT_LEFT_COMMANDS;
        this.RIGHT_COMMANDS = RIGHT_COMMANDS;
        this.SHIFT_RIGHT_COMMANDS = SHIFT_RIGHT_COMMANDS;
        this.MIDDLE_COMMANDS = MIDDLE_COMMANDS;
        this.DROP_COMMANDS = DROP_COMMANDS;
        this.material = material;
        menuItemHashMap.put(tag, this);
    }
    public ItemStack getItem(AirDrop airDrop){
        ItemStack itemStack;
        try {
            if (material.contains("basehead-"))
                itemStack = BaseHeadHook.getItem(material);
            else itemStack = new ItemStack(Material.valueOf(material));
        }catch (Exception e){
            itemStack = new ItemStack(Material.DIRT);
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-material"), material, tag));
        }
        ItemMeta im;

        im = ItemUtil.addLore(itemStack, airDrop, new ArrayList<>(lore));
        itemStack.setItemMeta(im);

        im.setDisplayName(Message.messageBuilder(name));
        itemStack.setItemMeta(im);

        im = itemStack.getItemMeta();
        im.getPersistentDataContainer().set(NamespacedKey.fromString("tag"), PersistentDataType.STRING, tag);
        itemStack.setItemMeta(im);
        return itemStack;
    }

    public String getTag() {
        return tag;
    }

    public int getSlot() {
        return slot;
    }

    public List<String> getLEFT_COMMANDS() {
        return new ArrayList<>(LEFT_COMMANDS);
    }

    public List<String> getSHIFT_LEFT_COMMANDS() {
        return new ArrayList<>(SHIFT_LEFT_COMMANDS);
    }

    public List<String> getRIGHT_COMMANDS() {
        return new ArrayList<>(RIGHT_COMMANDS);
    }

    public List<String> getSHIFT_RIGHT_COMMANDS() {
        return new ArrayList<>(SHIFT_RIGHT_COMMANDS);
    }

    public List<String> getMIDDLE_COMMANDS() {
        return new ArrayList<>(MIDDLE_COMMANDS);
    }

    public List<String> getDROP_COMMANDS() {
        return new ArrayList<>(DROP_COMMANDS);
    }
}
