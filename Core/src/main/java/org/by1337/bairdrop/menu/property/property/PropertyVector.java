package org.by1337.bairdrop.menu.property.property;

import org.bukkit.util.Vector;

public class PropertyVector extends Property<Vector> {
    /**
     * Creates a new property with the specified initial value, name
     *
     * @param value The initial value of the property.
     * @param name  The unique name of the property.
     */
    public PropertyVector(Vector value, String name) {
        super(value, name);
    }

    @Override
    public Vector parse(String str) {
        return null;
    }
//    public PropertyVector(Vector value, String name, Resource lore, Resource material, Resource displayName, StorageProperties owner) {
//        super(value, name, lore, material, displayName, owner);
//    }


    public PropertyVector(Vector value, String name, Vector defValue) {
        super(value, name, defValue);
    }

    @Override
    public PropertyType getType() {
        return PropertyType.VECTOR;
    }

/*    @Override
    public void editValue(Player player) {
        new ListenChat((str) -> {
            str.ifPresent(s -> {
                String[] args = s.split(" ");
                setValue(new Vector(
                        Double.parseDouble(args[0]),
                        Double.parseDouble(args[1]),
                        Double.parseDouble(args[2])
                ));
            });

            PropertyEditor propertyEditor = new PropertyEditor(getOwner());
            propertyEditor.generate();
            player.openInventory(propertyEditor.getInventory());

        }, player);
    }*/
}
