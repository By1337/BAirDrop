package org.by1337.bairdrop.listeners;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.util.Message;

public class SetStaticLocation implements Listener {
    private final AirDrop airDrop;
    private ItemStack item;
    public static SetStaticLocation SSL;

    public SetStaticLocation(AirDrop airDrop) {
        this.airDrop = airDrop;
        SSL = this;
    }

    @EventHandler
    public void onClick(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().contains(item)) {
            airDrop.setStaticLocation(e.getBlock().getLocation());
            airDrop.save();
            Message.sendMsg(e.getPlayer(), BAirDrop.getConfigMessage().getMessage("set-static-loc"));
            e.getPlayer().getInventory().remove(item);
            e.setCancelled(true);
            HandlerList.unregisterAll(this);
        }
    }

    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.GOLDEN_AXE);
        List<String> lore = new ArrayList<>();
        ItemMeta im = itemStack.getItemMeta();
        im.setDisplayName(Message.messageBuilder(BAirDrop.getConfigMessage().getMessage("item-name-set-static-loc")));
        lore.add(Message.messageBuilder(BAirDrop.getConfigMessage().getMessage("item-lore-set-static-loc")));
        im.setLore(lore);
        im.getPersistentDataContainer().set(NamespacedKey.fromString("item"), PersistentDataType.STRING, "itemBairDrop");
        itemStack.setItemMeta(im);
        item = itemStack;
        return itemStack;
    }

    public void unReg() {
        HandlerList.unregisterAll(this);
    }
    public SetStaticLocation get(){
        return SSL;
    }

}
