package org.by1337.bairdrop.menu;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.util.Items;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;


public class AddingItems implements Listener {
    public static AddingItems addingItems = null;
    Inventory inv;
    AirDrop airdrop;
    String invName;

    public AddingItems(AirDrop airdrop, String invName) {
        if(addingItems != null){
            addingItems.unReg();
        }
        addingItems = this;
        if (airdrop != null) {
            this.airdrop = airdrop;
            inv = Bukkit.createInventory(null, airdrop.getInvSize(), Config.getMessage("inv-edit").replace("{id}", airdrop.getAirId()));
            this.invName = invName;
            airdrop.getEditorItemsInventory(inv, invName);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inv)) {
            List<Items> list = new ArrayList<>();
            for (int x = 0; x < inv.getSize(); x++) {
                if (inv.getItem(x) == null) continue;
                ItemMeta im = inv.getItem(x).getItemMeta();
                Integer chance = im.getPersistentDataContainer().get(NamespacedKey.fromString("chance"), PersistentDataType.INTEGER);
                if (chance != null)
                    im.getPersistentDataContainer().remove(NamespacedKey.fromString("chance"));
                inv.getItem(x).setItemMeta(im);
                list.add(new Items(x, chance == null ? 100 : chance, inv.getItem(x), invName));
            }
            airdrop.getListItems().put(invName, list);
            airdrop.save();

            new BukkitRunnable() {
                final AirDrop air = airdrop;
                @Override
                public void run() {
                    SelectInv si = new SelectInv(air, "[edit]");
                    getServer().getPluginManager().registerEvents(si, BAirDrop.instance);
                    e.getPlayer().openInventory(si.getInv());
                    cancel();
                }
            }.runTaskTimer(BAirDrop.instance, 1, 1);
            addingItems = null;
            HandlerList.unregisterAll(this);
        }

    }
    public void unReg(){
        HandlerList.unregisterAll(this);
    }
    public Inventory getInv() {
        return inv;
    }
}
