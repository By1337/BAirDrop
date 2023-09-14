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
    private final List<String> leftCommands;
    private final List<String> shiftLeftCommands;
    private final List<String> rightCommands;
    private final List<String> shiftRightCommands;
    private final List<String> middleCommands;
    private final List<String> dropCommands;
    private final String material;

    public MenuItem(@NotNull String tag, String name, List<String> lore, int slot, List<String> leftCommands, List<String> shiftLeftCommands, List<String> rightCommands, List<String> shiftRightCommands, List<String> middleCommands, List<String> dropCommands, String material) {
        this.tag = tag;
        this.name = name;
        this.lore = lore;
        this.slot = slot;
        this.leftCommands = leftCommands;
        this.shiftLeftCommands = shiftLeftCommands;
        this.rightCommands = rightCommands;
        this.shiftRightCommands = shiftRightCommands;
        this.middleCommands = middleCommands;
        this.dropCommands = dropCommands;
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

    public List<String> getLeftCommands() {
        return new ArrayList<>(leftCommands);
    }

    public List<String> getShiftLeftCommands() {
        return new ArrayList<>(shiftLeftCommands);
    }

    public List<String> getRightCommands() {
        return new ArrayList<>(rightCommands);
    }

    public List<String> getShiftRightCommands() {
        return new ArrayList<>(shiftRightCommands);
    }

    public List<String> getMiddleCommands() {
        return new ArrayList<>(middleCommands);
    }

    public List<String> getDropCommands() {
        return new ArrayList<>(dropCommands);
    }
}
