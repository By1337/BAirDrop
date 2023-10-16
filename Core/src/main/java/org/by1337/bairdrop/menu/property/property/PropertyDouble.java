package org.by1337.bairdrop.menu.property.property;

public class PropertyDouble extends Property<Double>{
    /**
     * Creates a new property with the specified initial value, name
     *
     * @param value The initial value of the property.
     * @param name  The unique name of the property.
     */
    public PropertyDouble(Double value, String name) {
        super(value, name);
    }

    @Override
    public Double parse(String str) {
        return Double.parseDouble(str);
    }
//    public PropertyDouble(Double value, String name, Resource lore, Resource material, Resource displayName, StorageProperties owner) {
//        super(value, name, lore, material, displayName, owner);
//    }

    public PropertyDouble(Double value, String name, Double defValue) {
        super(value, name, defValue);
    }


/*    @Override
    public void editValue(Player player) {
        new ListenChat((str) -> {
            str.ifPresent(s -> setValue(Double.parseDouble(s)));

            PropertyEditor propertyEditor = new PropertyEditor(getOwner());
            propertyEditor.generate();
            player.openInventory(propertyEditor.getInventory());

        }, player);
    }*/

    @Override
    public PropertyType getType() {
        return PropertyType.DOUBLE;
    }
}
