package org.by1337.bairdrop.menu.property;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.by1337.bairdrop.ItemUtil.BaseHeadHook;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.util.Placeholder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class representing a property with a value.
 *
 * @param <T> The type of the property's value.
 */
public abstract class Property<T> implements EditValue<T> {
    /**
     * The key used for storing property information in the PersistentDataContainer.
     */
    @NotNull
    public static final NamespacedKey PROPERTY_KEY = NamespacedKey.fromString("bair_property");

    @Getter
    @Setter
    private T value;
    @Getter
    private final String displayName;
    @Getter
    private final String name;
    @Getter
    private final List<String> lore;
    @Getter
    private final String material;

    /**
     * The list of placeholders associated with the property.
     */
    private final List<Placeholder> placeholders = new ArrayList<>();


    /**
     * Creates a new property with the specified initial value, name, lore, material, and display name.
     *
     * @param value       The initial value of the property.
     * @param name        The unique name of the property.
     * @param lore        The lore (description) of the property.
     * @param material    The material used to represent the property visually.
     * @param displayName The display name of the property.
     */
    public Property(T value, String name, List<String> lore, Material material, String displayName) {
        this.displayName = displayName;
        this.value = value;
        this.name = name;
        this.lore = lore;
        this.material = material.name();
        placeholders.add(sb -> {
            while (true) {
                if (sb.indexOf("{value}") != -1) {
                    sb.replace(sb.indexOf("{value}"), sb.indexOf("{value}") + "{value}".length(), String.valueOf(this.value));
                    continue;
                }
                break;
            }
            return sb;
        });
        init();
    }

    public Property(T value, String name, List<String> lore, String material, String displayName) {
        this.displayName = displayName;
        this.value = value;
        this.name = name;
        this.lore = lore;
        this.material = material;
        placeholders.add(sb -> {
            while (true) {
                if (sb.indexOf("{value}") != -1) {
                    sb.replace(sb.indexOf("{value}"), sb.indexOf("{value}") + "{value}".length(), String.valueOf(this.value));
                    continue;
                }
                break;
            }
            return sb;
        });
        init();
    }

    /**
     * Registers a placeholder for replacing text within the lore and display name of the property.
     *
     * @param placeholder The placeholder to register.
     */
    protected void registerPlaceholder(Placeholder placeholder) {
        placeholders.add(placeholder);
    }

    /**
     * Creates an ItemStack representing the property with its current value and metadata.
     *
     * @return An ItemStack representing the property.
     */
    public ItemStack createItem() {
        ItemStack itemStack = new ItemStack(BaseHeadHook.getItem(material));
        ItemMeta im = itemStack.getItemMeta();

        im.setDisplayName(Message.messageBuilder(replace(displayName)));

        List<String> lore = new ArrayList<>(this.lore);
        lore.replaceAll(this::replace);
        lore.replaceAll(Message::messageBuilder);
        im.setLore(lore);

        im.getPersistentDataContainer().set(PROPERTY_KEY, PersistentDataType.STRING, name);

        itemStack.setItemMeta(im);
        return itemStack;
    }

    /**
     * Replaces placeholders within a given string.
     *
     * @param raw The string with placeholders to replace.
     * @return The string with placeholders replaced.
     */
    private String replace(String raw) {
        StringBuilder sb = new StringBuilder(raw);
        for (Placeholder placeholder : placeholders) {
            sb = placeholder.replace(sb);
        }
        return sb.toString();
    }

}
