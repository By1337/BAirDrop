package org.by1337.bairdrop.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
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

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Bukkit.selectEntities;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;
public class ChangeWorld implements Listener {
    private final Inventory inventory;
    private final AirDrop airDrop;

    public ChangeWorld(AirDrop airDrop) {
        this.airDrop = airDrop;
        inventory = Bukkit.createInventory(null, 54, Config.getMessage("change-world-inv-name"));
        generate();
    }
    private void generate(){
        int slot = 0;
        for(World world : Bukkit.getWorlds()){
            ItemStack itemStack;
            if(String.valueOf(world.getEnvironment()).equals("NORMAL"))
                itemStack = new ItemStack(Material.GRASS_BLOCK);
            else if(String.valueOf(world.getEnvironment()).equals("NETHER"))
                itemStack = new ItemStack(Material.NETHERRACK);
            else if(String.valueOf(world.getEnvironment()).equals("THE_END"))
                itemStack = new ItemStack(Material.END_STONE);
            else itemStack = new ItemStack(Material.COMMAND_BLOCK);
            ItemMeta im = itemStack.getItemMeta();
            im.setDisplayName(Message.messageBuilder("&7Мир №" + slot));
            List<String> lore = new ArrayList<>(Config.getList("world-lore"));
            lore.replaceAll(s -> s.replace("{type}", world.getEnvironment() + "").replace("{name}", world.getName()));
            lore.replaceAll(ChangeWorld::locate);
            lore.replaceAll(Message::messageBuilder);

            im.getPersistentDataContainer().set(NamespacedKey.fromString("world"), PersistentDataType.STRING, world.getName());

            im.setLore(lore);
            itemStack.setItemMeta(im);
            inventory.setItem(slot, itemStack);
            slot++;
        }
    }
    public static String locate(String string){
        string = string.replace("NORMAL", Config.getMessage("NORMAL"));
        string = string.replace("NETHER", Config.getMessage("NETHER"));
        string = string.replace("THE_END", Config.getMessage("THE_END"));
        string = string.replace("CUSTOM", Config.getMessage("CUSTOM"));
        return string;
    }
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            if(e.getCurrentItem() == null) return;
            ItemMeta im = e.getCurrentItem().getItemMeta();
            String world = im.getPersistentDataContainer().get(NamespacedKey.fromString("world"), PersistentDataType.STRING);
            if(world == null){
                Message.sendMsg((Player) e.getWhoClicked(), Config.getMessage("error"));
                inventory.clear();
                generate();
                return;
            }
            if(airDrop.isAirDropStarted())
                airDrop.End();
            airDrop.setFutureLocation(null);
            World world1 = Bukkit.getWorld(world);
            airDrop.setWorld(world1);
            Message.sendMsg((Player) e.getWhoClicked(), Config.getMessage("world-changed"));

            airDrop.save();
            EditAirMenu em = new EditAirMenu(airDrop);
         //   getServer().getPluginManager().registerEvents(em, BAirDrop.getInstance());
            airDrop.setEditAirMenu(em);
            e.getWhoClicked().openInventory(em.getInventory());
        }

    }
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inventory)) {
            HandlerList.unregisterAll(this);
            airDrop.save();
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
