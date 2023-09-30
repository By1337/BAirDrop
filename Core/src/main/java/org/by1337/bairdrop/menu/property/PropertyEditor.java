package org.by1337.bairdrop.menu.property;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.menu.property.property.Property;
import org.by1337.bairdrop.util.Message;

import java.util.ArrayList;
import java.util.List;

public class PropertyEditor implements Listener {

    @Getter
    private final Inventory inventory;
    private final EditableProperties editableProperties;
    private int page = 0;

    @Setter
    private PropertyEditLang lang = new PropertyEditLang();

    public PropertyEditor(EditableProperties editableProperties) {
        this.editableProperties = editableProperties;
        this.inventory = Bukkit.createInventory(null, 54);
        Bukkit.getPluginManager().registerEvents(this, BAirDrop.getInstance());

    }

    public void generate() {
        inventory.clear();
        int slot = 0;
        for (Property<?> property : editableProperties.getProperties().values()) {
            if (page > 0) {
                if (slot < (52 * page)) {
                    slot++;
                    continue;
                }
            }
            if (slot - (52 * page) >= 52) break;
            inventory.setItem(slot - (52 * page), property.createItem());
            slot++;
        }
        if (slot >= 52) {
            ItemStack itemStack = new ItemStack(Material.ARROW);
            ItemMeta im = itemStack.getItemMeta();
            im.setDisplayName(Message.messageBuilder(lang.swapPageName));
            List<String> lore = new ArrayList<>(lang.swapPageLore);
            lore.replaceAll(Message::messageBuilder);
            im.setLore(lore);
            itemStack.setItemMeta(im);
            inventory.setItem(53, itemStack);
        }
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta im = itemStack.getItemMeta();
        im.setDisplayName(Message.messageBuilder(lang.back));
        itemStack.setItemMeta(im);
        inventory.setItem(52, itemStack);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        System.out.println(74);
        if (e.getInventory().equals(inventory)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (e.getSlot() == 52) {
                e.getWhoClicked().closeInventory();
                System.out.println(80);
                return;
            }
            if (e.getSlot() == 53) {
                if (e.getClick() == ClickType.LEFT && inventory.getItem(51) != null) {
                    page++;
                    generate();
                }
                if (e.getClick() == ClickType.RIGHT) {
                    page = page == 0 ? 0 : page - 1;
                    generate();
                }
                System.out.println(92);
                return;
            }
            ItemStack itemStack = e.getCurrentItem();
            ItemMeta im = itemStack.getItemMeta();
            String value = im.getPersistentDataContainer().get(Property.PROPERTY_KEY, PersistentDataType.STRING);

            if (value == null){
                generate();
                System.out.println(101);
                return;
            }

            Property<?> property = editableProperties.getPropertyByName(value);

            if (property == null){
                generate();
                System.out.println(109);
                return;
            }
            e.getWhoClicked().closeInventory();
            property.editValue((Player) e.getWhoClicked());
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inventory)) {
            HandlerList.unregisterAll(this);
        }
    }

    /**
     * Language settings for the PropertyEditor.
     */
    public static class PropertyEditLang {
        private final String swapPageName = "&aSwap page";
        private final List<String> swapPageLore = List.of(
                "&aLMB &7- Next page",
                "&cRMB &7- Previous page"
        );
        private final String back = "&cBack";
    }
}
