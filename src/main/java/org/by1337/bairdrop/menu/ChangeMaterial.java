package org.by1337.bairdrop.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;

import org.by1337.bairdrop.util.Message;

import java.util.ArrayList;
import java.util.List;

public class ChangeMaterial implements Listener {
    private final Inventory inventory;
    private final AirDrop airDrop;
    private int page = 0;
    private final boolean isMaterialLocked;

    public ChangeMaterial(AirDrop airDrop, boolean isMaterialLocked) {
        this.inventory = Bukkit.createInventory(null, 54, String.format(BAirDrop.getConfigMessage().getMessage("mat-change-inv"), airDrop.getId()));
        this.airDrop = airDrop;
        this.isMaterialLocked = isMaterialLocked;
        generate();
    }

    private void generate() {
        int slot = 0;
        for (Material mat : Material.values()) {
            if (!mat.isBlock() || mat.isAir())
                continue;
            if (page > 0) {
                if (slot < (52 * page)) {
                    slot++;
                    continue;
                }
            }
            if (slot - (52 * page) >= 52) break;

            ItemStack itemStack = new ItemStack(mat);
            ItemMeta im = Bukkit.getItemFactory().getItemMeta(mat);
            assert im != null;
            im.setDisplayName(BAirDrop.getConfigMessage().getMessage("mat-change-item-name"));
            itemStack.setItemMeta(im);
            inventory.setItem(slot - (52 * page), itemStack);
            slot++;
        }
        if (slot >= 52) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta im = itemStack.getItemMeta();
            im.setDisplayName(BAirDrop.getConfigMessage().getMessage("mat-change-arrow-name"));
            List<String> lore = new ArrayList<>(BAirDrop.getConfigMessage().getList("mat-change-arrow-lore"));
            lore.replaceAll(Message::messageBuilder);
            im.setLore(lore);
            itemStack.setItemMeta(im);
            inventory.setItem(53, itemStack);
        }
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta im = itemStack.getItemMeta();
        im.setDisplayName(Message.messageBuilder(BAirDrop.getConfigMessage().getMessage("mat-change-back-name")));
        itemStack.setItemMeta(im);
        inventory.setItem(52, itemStack);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            if (e.getCurrentItem() == null) return;
            if (e.getSlot() == 52) {
                HandlerList.unregisterAll(this);
                if (airDrop.getEditAirMenu() != null)
                    airDrop.getEditAirMenu().unReg();
                EditAirMenu em = new EditAirMenu(airDrop);
              //  getServer().getPluginManager().registerEvents(em, BAirDrop.getInstance());
                airDrop.setEditAirMenu(em);
                e.getWhoClicked().openInventory(em.getInventory());
                e.setCancelled(true);
                return;
            }
            if (e.getSlot() == 53) {
                if (e.getClick() == ClickType.LEFT && inventory.getItem(51) != null) {
                    page++;
                    inventory.clear();
                    generate();
                }
                if (e.getClick() == ClickType.RIGHT) {
                    page = page == 0 ? 0 : page - 1;
                    inventory.clear();
                    generate();
                }
                e.setCancelled(true);
                return;
            }
            if (isMaterialLocked) {
                airDrop.setMaterialLocked(e.getCurrentItem().getType());
                e.getWhoClicked().closeInventory();
            } else {
                airDrop.setMaterialUnlocked(e.getCurrentItem().getType());
                e.getWhoClicked().closeInventory();
            }
            airDrop.save();
            if (airDrop.getEditAirMenu() != null)
                airDrop.getEditAirMenu().unReg();
            EditAirMenu em = new EditAirMenu(airDrop);
           // getServer().getPluginManager().registerEvents(em, BAirDrop.getInstance());
            airDrop.setEditAirMenu(em);
            e.getWhoClicked().openInventory(em.getInventory());
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inventory)) {
            HandlerList.unregisterAll(this);

        }
    }


    public Inventory getInventory() {
        return inventory;
    }
}
