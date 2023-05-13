package org.by1337.bairdrop.menu;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.menu.util.MenuItem;
import org.by1337.bairdrop.util.ExecuteCommands;
import org.jetbrains.annotations.Nullable;

public class EditAirMenu implements Listener {
    private static EditAirMenu editAirMenu = null;
    private final Inventory inventory;
    private final AirDrop airDrop;

    public EditAirMenu(AirDrop airDrop) {
        if(editAirMenu != null)
            editAirMenu.unReg();
        editAirMenu = this;
        this.airDrop = airDrop;
        inventory = Bukkit.createInventory(null, 54, String.format(BAirDrop.getConfigMessage().getMessage("editor"), airDrop.getId()));
        menuGenerate();
        Bukkit.getServer().getPluginManager().registerEvents(this, BAirDrop.getInstance());
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inventory)) {
            HandlerList.unregisterAll(this);
            airDrop.setEditAirMenu(null);
            editAirMenu = null;
        }

    }

    @EventHandler
    public void ocClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;
        if (e.getInventory().equals(inventory)) {
            ItemStack itemStack = e.getCurrentItem();
            ItemMeta im = itemStack.getItemMeta();
            String tag = im.getPersistentDataContainer().get(NamespacedKey.fromString("tag"), PersistentDataType.STRING);
            if (tag == null) {
                e.setCancelled(true);
                return;
            }
            MenuItem menuItem = getMenuItem(tag);
            if (menuItem == null) {
                e.setCancelled(true);
                return;
            }
            ExecuteCommands.execute(airDrop, menuItem, e);
            menuGenerate(tag);
            e.setCancelled(true);

        }
    }

    public void menuGenerate() {
        for (MenuItem menuItem : MenuItem.menuItemHashMap.values()) {
            inventory.setItem(menuItem.getSlot(), menuItem.getItem(airDrop));
        }
    }

    public void menuGenerate(String tag) {
        if (MenuItem.menuItemHashMap.containsKey(tag)) {
            MenuItem menuItem = MenuItem.menuItemHashMap.get(tag);
            inventory.setItem(menuItem.getSlot(), menuItem.getItem(airDrop));
        }
    }

    @Nullable
    public MenuItem getMenuItem(String tag) {
        return MenuItem.menuItemHashMap.getOrDefault(tag, null);

    }

    public Inventory getInventory() {
        return inventory;
    }
    public void unReg(){
        inventory.clear();
        HandlerList.unregisterAll(this);
    }
}
