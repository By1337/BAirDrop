package org.by1337.bairdrop.menu.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.CAirDrop;
import org.by1337.bairdrop.util.AirManager;
import org.by1337.bairdrop.util.Message;

public class ItemUtil {
    public static ItemMeta addLore(ItemStack itemStack, AirDrop airDrop, String... lore){
        ItemMeta im = itemStack.getItemMeta();
        List<String> newLore = new ArrayList<>(Arrays.stream(lore).toList());
        newLore.replaceAll(airDrop::replaceInternalPlaceholder);
        if(im.getLore() != null) {
            newLore.addAll(im.getLore());
            im.setLore(newLore);
        }
        else
            im.setLore(newLore);
        return im;
    }
    public static ItemMeta addLore(ItemStack itemStack, AirDrop airDrop, List<String> newLore){
        ItemMeta im = itemStack.getItemMeta();
        newLore.replaceAll(airDrop::replaceInternalPlaceholder);
        newLore.replaceAll(AirManager::colored);
        newLore.replaceAll(Message::messageBuilder);
        if(im.getLore() != null) {
            newLore.addAll(im.getLore());
            im.setLore(newLore);
        }
        else
            im.setLore(newLore);
        return im;
    }
    public static ItemStack getErrorItem(){
        ItemStack itemStack = new ItemStack(Material.DIRT);
        ItemMeta im = itemStack.getItemMeta();
        im.setDisplayName(Message.messageBuilder("&cERROR"));
        itemStack.setItemMeta(im);
        return itemStack;
    }
}
