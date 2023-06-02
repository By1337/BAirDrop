package org.by1337.bairdrop.menu;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;

import org.by1337.bairdrop.ItemUtil.Items;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;


import org.by1337.bairdrop.util.Message;

public class EditChance implements Listener {
    private static EditChance editChance = null;
    private final Inventory inv;
    private final AirDrop airDrop;
    private final String invName;

    public EditChance(@NotNull AirDrop airDrop, String invName) {
        if(editChance != null){
            editChance.unReg();
        }
        editChance = this;
        this.airDrop = airDrop;
        this.invName = invName;
        inv = Bukkit.createInventory(null, airDrop.getInventorySize(), airDrop.getId());

        for(Items items : airDrop.getListItems().getOrDefault(invName, new ArrayList<>())){
            ItemStack item = items.getItem();
            ItemMeta im = item.getItemMeta().clone();
            item.setItemMeta(im);

            item.setItemMeta(setLore(items.getChance(), item));
            inv.setItem(items.getSlot(), item);
        }
    }
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inv)) {
            airDrop.save();
            new BukkitRunnable() {
                final AirDrop air = airDrop;
                @Override
                public void run() {
                    SelectInv si = new SelectInv(air, "[chance]");
                    getServer().getPluginManager().registerEvents(si, BAirDrop.getInstance());
                    e.getPlayer().openInventory(si.getInv());
                    cancel();
                }
            }.runTaskTimer(BAirDrop.getInstance(), 1, 1);
            editChance = null;
            HandlerList.unregisterAll(this);
        }

    }
    @EventHandler
    public void ocClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inv)) {
            if(!e.getClickedInventory().equals(inv)){
                e.setCancelled(true);
                return;
            }
            if(e.getCurrentItem() == null) return;
            try {

                Items originalItem = null;
                for(Items items : airDrop.getListItems().getOrDefault(invName, new ArrayList<>())){
                    if(items.getSlot() == e.getSlot()){
                        originalItem = items;
                        break;
                    }
                }
                if(originalItem == null){
                    Message.error(BAirDrop.getConfigMessage().getMessage("item-error2"));
                    e.setCancelled(true);
                    return;
                }
                int chance = originalItem.getChance();

                if(e.getClick() == ClickType.LEFT)
                    chance--;
                if(e.getClick() == ClickType.SHIFT_LEFT)
                    chance -= 10;
                if(e.getClick() == ClickType.RIGHT)
                    chance++;
                if(e.getClick() == ClickType.SHIFT_RIGHT)
                    chance += 10;

                ItemMeta newIm = originalItem.getItem().getItemMeta().clone();

                originalItem.setChance(chance);
                ItemStack newItem = originalItem.getItem().clone();
                newItem.setItemMeta(newIm);

                newItem.setItemMeta(setLore(chance, newItem));

                e.getInventory().setItem(e.getSlot(), newItem);

                e.setCancelled(true);
            }catch (NullPointerException var3){
                var3.printStackTrace();
            }

        }

    }
    private ItemMeta setLore(Integer chance, ItemStack itemStack){
        ItemMeta im = itemStack.getItemMeta();
        List<String> lore = BAirDrop.getConfigMessage().getList("edit-lore");
        lore.replaceAll(s -> s.replace("{chance}", chance + ""));

        lore.replaceAll(Message::messageBuilder);
        if(im.getLore() != null) {
            lore.addAll(im.getLore());
            im.setLore(lore);
        }
        else
            im.setLore(lore);
        return im;
    }

    public Inventory getInv() {
        return inv;
    }
    public void unReg(){
        HandlerList.unregisterAll(this);
    }
}
