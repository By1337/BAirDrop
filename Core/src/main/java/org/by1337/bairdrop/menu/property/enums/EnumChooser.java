package org.by1337.bairdrop.menu.property.enums;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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
import org.by1337.bairdrop.menu.property.SetValueCallBack;
import org.by1337.bairdrop.util.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a utility class for choosing enum values from a graphical interface.
 * @param <T> The enum type.
 */
public class EnumChooser<T extends Enum<T>> implements Listener {
    @Getter
    private final Inventory inventory;
    private int page = 0;
    private final Class<T> anEnum;
    private final List<EnumValueFilter<T>> filters = new ArrayList<>();
    private final EnumToItemStackConverter<T> converter;
    private final SetValueCallBack<T> result;
    @Setter
    private EnumChooserLang lang = new EnumChooserLang();

    /**
     * Creates an instance of the EnumChooser.
     *
     * @param anEnum    The enum class to choose values from.
     * @param converter A converter for mapping enum values to materials.
     * @param result    A callback to handle the selected enum value.
     */
    public EnumChooser(Class<T> anEnum, EnumToItemStackConverter<T> converter, SetValueCallBack<T> result) {
        this.result = result;
        this.converter = converter;
        this.anEnum = anEnum;
        this.inventory = Bukkit.createInventory(null, 54, Message.messageBuilder(lang.title));
        Bukkit.getPluginManager().registerEvents(this, BAirDrop.getInstance());
    }

    /**
     * Generates the graphical representation of enum values in the inventory.
     */
    public void generate() {
        inventory.clear();
        int slot = 0;
        for (T val : anEnum.getEnumConstants()) {
            if (filters.stream().findFirst().filter(f -> f.isSkip(val)).isPresent())
                continue;
            if (page > 0) {
                if (slot < (52 * page)) {
                    slot++;
                    continue;
                }
            }
            if (slot - (52 * page) >= 52) break;

            ItemStack itemStack =  converter.convertToMaterial(val);
            ItemMeta im = itemStack.getItemMeta();
            assert im != null;
            if (!im.hasDisplayName())
                im.setDisplayName(Message.messageBuilder(lang.clickToSelect));
            im.getPersistentDataContainer().set(NamespacedKey.fromString("value"), PersistentDataType.STRING, val.name());
            itemStack.setItemMeta(im);
            inventory.setItem(slot - (52 * page), itemStack);
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

    /**
     * Handles inventory click events.
     *
     * @param e The InventoryClickEvent.
     */
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (e.getSlot() == 52) {
                HandlerList.unregisterAll(this);
                result.result(Optional.empty());
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
                return;
            }
            ItemStack itemStack = e.getCurrentItem();
            ItemMeta im = itemStack.getItemMeta();
            String value = im.getPersistentDataContainer().get(NamespacedKey.fromString("value"), PersistentDataType.STRING);

            if (value == null){
                generate();
                return;
            }
            result.result(Optional.of(Enum.valueOf(anEnum, value)));
            HandlerList.unregisterAll(this);
        }
    }

    /**
     * Handles inventory close events.
     *
     * @param e The InventoryCloseEvent.
     */
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inventory)) {
            HandlerList.unregisterAll(this);
        }
    }

    /**
     * Registers a filter to skip specific enum values when generating the inventory.
     *
     * @param filter The EnumValueFilter to register.
     */
    public void registerFilter(EnumValueFilter<T> filter){
        filters.add(filter);
    }

    /**
     * Represents language settings for the EnumChooser.
     */
    public static class EnumChooserLang {
        private final String clickToSelect = "&aLeft-click to set";
        private final String swapPageName = "&aSwap page";
        private final List<String> swapPageLore = List.of(
                "&aLMB &7- Next page",
                "&cRMB &7- Previous page"
        );
        private final String back = "&cBack";
        private final String title = "&7Changing material";
    }
}
