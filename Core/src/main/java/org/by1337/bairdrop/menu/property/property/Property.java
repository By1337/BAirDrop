package org.by1337.bairdrop.menu.property.property;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Color;
import org.bukkit.util.Vector;
import org.by1337.api.chat.ChatColor;

/**
 * An abstract class representing a property with a value.
 *
 * @param <T> The type of the property's value.
 */
public abstract class Property<T>  { //implements EditValue<T>
    /**
     * The key used for storing property information in the PersistentDataContainer.
     */
  //  @NotNull
  //  public static final NamespacedKey PROPERTY_KEY = NamespacedKey.fromString("bair_property");

    @Getter
    @Setter
    private T value;
    @Getter
    private final String name;
    @Getter
    @Setter
    private T defValue;
    @Getter
    @Setter
    private boolean hasDefValue;

    private final String type = getType().getKey().getKey();

    /**
     * The list of placeholders associated with the property.
     */
  //  private final transient List<Placeholder> placeholders = new ArrayList<>();



    public Property(T value, String name, T defValue) {
        this.defValue = defValue;
        this.value = value;
        this.name = name;
        hasDefValue = true;
    }

    public Property(T value, String name) {
        this.value = value;
        this.name = name;
//        placeholders.add(sb -> {
//            while (true) {
//                if (sb.indexOf("{value}") != -1) {
//                    sb.replace(sb.indexOf("{value}"), sb.indexOf("{value}") + "{value}".length(), toString(this.value));
//                    continue;
//                }
//                break;
//            }
//            return sb;
//        });
       // init();
    }

//    /**
//     * Registers a placeholder for replacing text within the lore and display name of the property.
//     *
//     * @param placeholder The placeholder to register.
//     */
//    protected void registerPlaceholder(Placeholder placeholder) {
//        placeholders.add(placeholder);
//    }
/*
    */

    @Override
    public String toString() {
        return "Property{" +
                "value=" + value +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    /**
     * Creates an ItemStack representing the property with its current value and metadata.
     *
     * @return An ItemStack representing the property.
     *//*
    public ItemStack createItem() {
        ItemStack itemStack = new ItemStack(BaseHeadHook.getItem(material.getString()));
        ItemMeta im = itemStack.getItemMeta();

        im.setDisplayName(Message.messageBuilder(replace(displayName.getString())));

        List<String> lore = new ArrayList<>(this.lore.getList());
        lore.replaceAll(this::replace);
        lore.replaceAll(Message::messageBuilder);
        im.setLore(lore);

        im.getPersistentDataContainer().set(PROPERTY_KEY, PersistentDataType.STRING, name);

        itemStack.setItemMeta(im);
        return itemStack;
    }*/

//    /**
//     * Replaces placeholders within a given string.
//     *
//     * @param raw The string with placeholders to replace.
//     * @return The string with placeholders replaced.
//     */
//    private String replace(String raw) {
//        StringBuilder sb = new StringBuilder(raw);
//        for (Placeholder placeholder : placeholders) {
//            sb = placeholder.replace(sb);
//        }
//        return sb.toString();
//    }



    public abstract T parse(String str);
    public static <T> String toString(T val){
        if (val instanceof Color color){
            return "&r&n&" + ChatColor.toHex(color).toUpperCase().repeat(2);
        }else  if (val instanceof Vector vector){
            return vector.getX() + ", " + vector.getY() + ", " + vector.getZ();
        }else {
            return String.valueOf(val);
        }
    }
    abstract public PropertyType getType();
}

