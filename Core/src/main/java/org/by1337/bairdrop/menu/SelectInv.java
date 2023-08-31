package org.by1337.bairdrop.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.ItemUtil.BaseHeadHook;
import org.by1337.bairdrop.ItemUtil.Items;
import org.by1337.bairdrop.util.Message;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.bukkit.Bukkit.getServer;

public class SelectInv implements Listener {
    private static SelectInv selectInv = null;
    private final Inventory inv;
    private final AirDrop airDrop;
    private final String menu;
    private boolean openEditAirMenu = true;

    public SelectInv(AirDrop airDrop, String menu) {
        if (selectInv != null) {
            selectInv.unReg();
        }
        selectInv = this;
        this.airDrop = airDrop;
        this.menu = menu;
        inv = Bukkit.createInventory(null, 54, Message.messageBuilder(BAirDrop.getConfigMessage().getMessage("inv-select")));
        generate();
    }

    private void generate() {
        int slot = 0;
        if (airDrop.getFileConfiguration().getConfigurationSection("inv") != null) {
            for (String inv : airDrop.getFileConfiguration().getConfigurationSection("inv").getKeys(false)) {
                if (slot >= 53) continue;
                ItemStack itemStack;
                if (airDrop.getListItems().get(inv).size() == 1)
                    itemStack = new ItemStack(Material.LIME_WOOL);
                else
                    itemStack = BaseHeadHook.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDNhOWEwNzFiNDI4M2M3NTYyNjg3NWM3YmFmZDBlZWYxM2IzZGZmNThhZDk2ODBhMTY1Mjg4YTcxNzFjNTYzNSJ9fX0=");
                ItemMeta im = itemStack.getItemMeta();
                im.setLore(List.of(Message.messageBuilder(BAirDrop.getConfigMessage().getMessage("edit-list")), Message.messageBuilder(String.format(BAirDrop.getConfigMessage().getMessage("list-size"), airDrop.getListItems().get(inv).size()))));
                im.getPersistentDataContainer().set(NamespacedKey.fromString("inv"), PersistentDataType.STRING, inv);
                im.setDisplayName(Message.messageBuilder("&7" + inv));
                itemStack.setItemMeta(im);
                this.inv.setItem(slot, itemStack);
                slot++;
            }
        }
        ItemStack itemStack = BaseHeadHook.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWEyZDg5MWM2YWU5ZjZiYWEwNDBkNzM2YWI4NGQ0ODM0NGJiNmI3MGQ3ZjFhMjgwZGQxMmNiYWM0ZDc3NyJ9fX0=");
        ItemMeta im = itemStack.getItemMeta();
        im.setDisplayName(Message.messageBuilder(BAirDrop.getConfigMessage().getMessage("create-new-list")));
        im.getPersistentDataContainer().set(NamespacedKey.fromString("inv"), PersistentDataType.STRING, "add");
        itemStack.setItemMeta(im);
        this.inv.setItem(53, itemStack);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory().equals(inv)) {
            if (e.getCurrentItem() == null) return;
            ItemMeta im = e.getCurrentItem().getItemMeta();
            String str = im.getPersistentDataContainer().get(NamespacedKey.fromString("inv"), PersistentDataType.STRING);
            if (str == null) {
                generate();
                e.setCancelled(true);
                return;
            }
            if (str.equals("add")) {
                String newInv = "inv" + ThreadLocalRandom.current().nextInt(0, 99999);
                while (airDrop.getListItems().containsKey(newInv))
                    newInv = "inv" + ThreadLocalRandom.current().nextInt(0, 99999);
                airDrop.getListItems().put(newInv, List.of(new Items(0, 100, new ItemStack(Material.DIRT), newInv)));
                airDrop.save();
                generate();
                e.setCancelled(true);
                e.setCancelled(true);
                return;
            }
            Player pl = (Player) e.getWhoClicked();
            if (menu.equals("[edit]")) {
                openEditAirMenu = false;
                pl.closeInventory();
                AddingItems ai = new AddingItems(airDrop, str);
                getServer().getPluginManager().registerEvents(ai, BAirDrop.getInstance());
                pl.openInventory(ai.getInv());
                HandlerList.unregisterAll(this);
            } else {
                openEditAirMenu = false;
                pl.closeInventory();
                EditChance ec = new EditChance(airDrop, str);
                getServer().getPluginManager().registerEvents(ec, BAirDrop.getInstance());
                pl.openInventory(ec.getInv());
            }

        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inv)) {
            selectInv = null;
            if (openEditAirMenu) {
                new BukkitRunnable() {
                    final AirDrop air = airDrop;

                    @Override
                    public void run() {
                        EditAirMenu em = new EditAirMenu(air);
                        air.setEditAirMenu(em);
                        e.getPlayer().openInventory(em.getInventory());
                        cancel();
                    }
                }.runTaskTimer(BAirDrop.getInstance(), 1, 1);
            }

            HandlerList.unregisterAll(this);
        }
    }

    public Inventory getInv() {
        return inv;
    }

    public void unReg() {
        HandlerList.unregisterAll(this);
    }
}
