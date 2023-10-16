package org.by1337.bairdrop.menu.property.property;

import org.bukkit.Color;

public class PropertyColor extends Property<Color> {
    /**
     * Creates a new property with the specified initial value, name
     *
     * @param value The initial value of the property.
     * @param name  The unique name of the property.
     */
    public PropertyColor(Color value, String name) {
        super(value, name);
    }

    @Override
    public Color parse(String str) {
        return null;
    }
//    public PropertyColor(Color value, String name, Resource lore, Resource material, Resource displayName, StorageProperties owner) {
//        super(value, name, lore, material, displayName, owner);
//    }

    public PropertyColor(Color value, String name, Color defValue) {
        super(value, name, defValue);
    }


/*
    @Override
    public void editValue(Player player) {
        new ListenChat((str) -> {
            str.ifPresent(s -> {
                String[] args = s.split(" ");
                setValue(Color.fromRGB(
                        Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2])
                ));
            });
            PropertyEditor propertyEditor = new PropertyEditor(getOwner());
            propertyEditor.generate();
            player.openInventory(propertyEditor.getInventory());

        }, player);
    }
*/

    @Override
    public PropertyType getType() {
        return PropertyType.COLOR;
    }
}
