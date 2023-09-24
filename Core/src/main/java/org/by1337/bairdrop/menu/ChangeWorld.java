package org.by1337.bairdrop.menu;

import lombok.Getter;
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
import java.util.Optional;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.menu.property.SetValueCallBack;
import org.by1337.bairdrop.util.Message;

public class ChangeWorld implements Listener {
    @Getter
    private final Inventory inventory;
    private final SetValueCallBack<World> callBack;

    public ChangeWorld(SetValueCallBack<World> callBack) {
        this.callBack = callBack;
        inventory = Bukkit.createInventory(null, 54, Message.messageBuilder("&7Смена мира"));
        Bukkit.getServer().getPluginManager().registerEvents(this, BAirDrop.getInstance());
        generate();
    }

    private void generate() {
        int slot = 0;
        for (World world : Bukkit.getWorlds()) {
            ItemStack itemStack = switch (world.getEnvironment()) {
                case NORMAL -> new ItemStack(Material.GRASS_BLOCK);
                case NETHER -> new ItemStack(Material.NETHERRACK);
                case THE_END -> new ItemStack(Material.END_STONE);
                case CUSTOM -> new ItemStack(Material.COMMAND_BLOCK);
            };

            ItemMeta im = itemStack.getItemMeta();
            im.setDisplayName(Message.messageBuilder("&7World №" + slot));
            List<String> lore = new ArrayList<>(BAirDrop.getConfigMessage().getList("world-lore"));
            lore.replaceAll(s -> s.replace("{type}", world.getEnvironment().name()).replace("{name}", world.getName()));
            lore.replaceAll(this::locate);
            lore.replaceAll(Message::messageBuilder);

            im.getPersistentDataContainer().set(NamespacedKey.fromString("world"), PersistentDataType.STRING, world.getName());

            im.setLore(lore);
            itemStack.setItemMeta(im);
            inventory.setItem(slot, itemStack);
            slot++;
        }
    }

    private String locate(String string) {
        string = string.replace("NORMAL", "&aОбычный");
        string = string.replace("NETHER", "&cНезер");
        string = string.replace("THE_END", "&dЭнд");
        string = string.replace("CUSTOM", "&6Кастомный");
        return string;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            if (e.getCurrentItem() == null) return;
            ItemMeta im = e.getCurrentItem().getItemMeta();
            String world = im.getPersistentDataContainer().get(NamespacedKey.fromString("world"), PersistentDataType.STRING);
            if (world == null) {
                inventory.clear();
                generate();
                return;
            }

            World world1 = Bukkit.getWorld(world);
            callBack.result(Optional.ofNullable(world1));
        }

    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inventory)) {
            HandlerList.unregisterAll(this);
        }
    }
}
