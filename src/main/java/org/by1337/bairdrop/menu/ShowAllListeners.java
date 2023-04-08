package org.by1337.bairdrop.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.util.AirManager;
import org.by1337.bairdrop.util.Events;
import org.by1337.bairdrop.util.InternalListener;
import org.by1337.bairdrop.util.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.by1337.bairdrop.menu.util.ItemUtil.getErrorItem;

public class ShowAllListeners implements Listener {
    Inventory inventory;
    AirDrop airDrop;
    int page = 0;

    public ShowAllListeners(AirDrop airDrop) {
        this.airDrop = airDrop;
        page = 0;
        inventory = Bukkit.createInventory(null, 54, Config.getMessage("show-all-events-inv"));
        generate();
    }

    public ShowAllListeners(AirDrop airDrop, int page) {
        this.page = page;
        this.airDrop = airDrop;
        inventory = Bukkit.createInventory(null, 54, Config.getMessage("show-all-events-inv"));
        generate();
    }

    private void generate() {
        int slot = 0;
        for (String key : BAirDrop.internalListeners.keySet()) {
            if (BAirDrop.internalListeners.get(key).getEvent() == Events.NONE) continue;
            if (page > 0) {

                if (slot < (53 * page)) {
                    slot++;
                    continue;
                }
            }
            if (slot - (53 * page) >= 53) continue;
            inventory.setItem(slot - (53 * page), getItem(key));
            slot++;
        }
        if (slot >= 53) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta im = itemStack.getItemMeta();
            im.setDisplayName(Config.getMessage("mat-change-arrow-name"));
            List<String> lore = new ArrayList<>(Config.getList("mat-change-arrow-lore"));
            lore.replaceAll(Message::messageBuilder);
            im.setLore(lore);
            im.getPersistentDataContainer().set(NamespacedKey.fromString("event"), PersistentDataType.STRING, "uewjdskj3248jdskfzjke432kjdnfxjs");//page
            itemStack.setItemMeta(im);
            inventory.setItem(53, itemStack);
        }
    }

    public ItemStack getItem(String key) {
        InternalListener ei = BAirDrop.internalListeners.get(key);
        if (ei == null) {
            return getErrorItem();
        }
        ItemStack itemStack = new ItemStack(airDrop.getSignedListener().contains(key) ? Material.DISPENSER : Material.OBSERVER);
        ItemMeta im = itemStack.getItemMeta();

        im.setDisplayName(Message.messageBuilder("&f" + key));

        im.getPersistentDataContainer().set(NamespacedKey.fromString("event"), PersistentDataType.STRING, key);
        List<String> lore = new ArrayList<>(Config.getList("event-lore"));
        lore.replaceAll(s -> s
                .replace("{description}", ei.getDescription())
                .replace("{flag}", airDrop.getSignedListener().contains(key) + "")
                .replace("{event}", ei.getEvent().toString())
        );

        int max = 0;
        for (String cmd : ei.getCommands()) {
            lore.add("&7" + cmd);
            max++;
            if (max == 3) {
                if (ei.getCommands().length > 3)
                    lore.add(String.format(Config.getMessage("event-lore-max"), (ei.getCommands().length - 4)));
                break;
            }
        }
        if (ei.getDenyCommands().length != 0)
            lore.add("&adeny-commands:");
        max = 0;
        for (String cmd : ei.getDenyCommands()) {
            lore.add("&7" + cmd);
            max++;
            if (max == 3) {
                if (ei.getCommands().length > 3)
                    lore.add(String.format(Config.getMessage("event-lore-max"), (ei.getCommands().length - 4)));
                break;
            }
        }

        List<String> formattedLore = new ArrayList<>();
        for (String s : lore) {
            if (s.length() > 50) {
                StringBuilder sb = new StringBuilder();
                String[] args = s.split(" ");
                int len = 0;
                boolean close = false;
                for(String word : args){
                    len += word.length() + 1;
                    if(len >= 50 ){
                        sb.append(word);
                        formattedLore.add(sb.toString());
                        sb = new StringBuilder();
                        sb.append("&7");
                        len = 0;
                    }else {
                        sb.append(word).append(" ");
                    }
                }
                if(!sb.toString().equals("&7")){
                    formattedLore.add(sb.toString());
                }
            } else {
                formattedLore.add(s);
            }
        }
        lore = formattedLore;

//        lore.replaceAll(airDrop::replaceInternalPlaceholder);
//        lore.replaceAll(airDrop::colored);
//        lore.replaceAll(Message::messageBuilder);

        List<String> nweLore = new ArrayList<>();
        lore.replaceAll(s -> s.replace("\\n", "%split%"));
        for (String str : lore) {
            nweLore.addAll(Arrays.stream(str
                    .replace("[", "&d[")
                    .replace("]", "]&7")
                    .replace("-offsets", "&d-offsets")
                    .split("%split%")).toList());
        }
        nweLore.replaceAll(airDrop::replaceInternalPlaceholder);
        nweLore.replaceAll(AirManager::colored);
        nweLore.replaceAll(Message::messageBuilder);
        // nweLore.addAll(Arrays.stream(str.split("%split%")).toList());

        im.setLore(nweLore);
        itemStack.setItemMeta(im);
        return itemStack;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            if (e.getCurrentItem() == null) {
                e.setCancelled(true);
                return;
            }
            ItemMeta im = e.getCurrentItem().getItemMeta();
            String key = im.getPersistentDataContainer().get(NamespacedKey.fromString("event"), PersistentDataType.STRING);
            if (key == null) {
                e.setCancelled(true);
                Message.sendMsg((Player) e.getWhoClicked(), Config.getMessage("item-error2"));
                inventory.clear();
                generate();
                return;
            }
            if (key.equals("uewjdskj3248jdskfzjke432kjdnfxjs")) {//page
                if (e.getClick() == ClickType.LEFT) {
                    page++;
                    e.setCancelled(true);
                    inventory.clear();
                    generate();
                    return;
                }
                if (e.getClick() == ClickType.RIGHT) {
                    page = page == 0 ? 0 : page - 1;
                    e.setCancelled(true);
                    inventory.clear();
                    generate();
                    return;
                }
            }
            if (airDrop.getSignedListener().contains(key)) {
                airDrop.getSignedListener().remove(key);
                Message.sendMsg((Player) e.getWhoClicked(), Config.getMessage("unsubscribed"));
            } else {
                airDrop.getSignedListener().add(key);
                Message.sendMsg((Player) e.getWhoClicked(), Config.getMessage("signed"));
            }
            airDrop.save();
            e.setCancelled(true);
            inventory.setItem(e.getSlot(), getItem(key));
        }


    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inventory)) {
            HandlerList.unregisterAll(this);
        }
    }
}
