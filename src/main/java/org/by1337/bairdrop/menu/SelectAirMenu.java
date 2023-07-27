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
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.CAirDrop;
import org.by1337.bairdrop.util.Message;

import java.util.ArrayList;
import java.util.List;


public class SelectAirMenu implements Listener {
    private final Inventory inventory;

    private final int taskId;

    public SelectAirMenu() {
       inventory = Bukkit.createInventory(null, 54, Message.messageBuilder(BAirDrop.getConfigMessage().getMessage("menu-air-select")));
        taskId = new BukkitRunnable() {
            @Override
            public void run() {
                generate();
            }
        }.runTaskTimer(BAirDrop.getInstance(), 0, 20).getTaskId();


    }
    private void generate(){
        inventory.clear();
        int x = 0;
        for(AirDrop airDrop : BAirDrop.airDrops.values()){
            ItemStack itemStack = new ItemStack(airDrop.getMaterialLocked());
            ItemMeta im = itemStack.getItemMeta();
            im.getPersistentDataContainer().set(NamespacedKey.fromString("air_id"), PersistentDataType.STRING, airDrop.getId());
            im.setDisplayName(Message.messageBuilder(airDrop.getDisplayName()));
            List<String> lore = new ArrayList<>(BAirDrop.getConfigMessage().getList("select-air-menu-lore"));
            lore.addAll(airDrop.getDec());
            lore.replaceAll(airDrop::replaceInternalPlaceholder);
            lore.replaceAll(Message::messageBuilder);
            im.setLore(lore);
            itemStack.setItemMeta(im);
            inventory.setItem(x, itemStack);
            x++;
        }
    }
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getClickedInventory() != null && e.getClickedInventory().equals(inventory)){
            if(e.getCurrentItem() == null){
                e.setCancelled(true);
                return;
            }
            ItemMeta im = e.getCurrentItem().getItemMeta();
            if(im.getPersistentDataContainer().has(NamespacedKey.fromString("air_id"), PersistentDataType.STRING)){
                String key = im.getPersistentDataContainer().get(NamespacedKey.fromString("air_id"), PersistentDataType.STRING);
                if(BAirDrop.airDrops.containsKey(key)){

                    if (BAirDrop.airDrops.get(key).getEditAirMenu() != null) {
                        BAirDrop.airDrops.get(key).getEditAirMenu().unReg();
                    }

                    EditAirMenu editAirMenu = new EditAirMenu(BAirDrop.airDrops.get(key));
                    BAirDrop.airDrops.get(key).setEditAirMenu(editAirMenu);
                    e.getWhoClicked().openInventory(editAirMenu.getInventory());
                    return;
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inventory)) {
           unReg();
        }

    }

    public void unReg() {
        HandlerList.unregisterAll(this);
        Bukkit.getServer().getScheduler().cancelTask(taskId);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
