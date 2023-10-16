package org.by1337.bairdrop.menu.property.property;

public class PropertyInteger extends Property<Integer>{
    /**
     * Creates a new property with the specified initial value, name
     *
     * @param value The initial value of the property.
     * @param name  The unique name of the property.
     */
    public PropertyInteger(Integer value, String name) {
        super(value, name);
    }

    @Override
    public Integer parse(String str) {
        return Integer.parseInt(str);
    }
//    public PropertyInteger(Integer value, String name, Resource lore, Resource material, Resource displayName, StorageProperties owner) {
//        super(value, name, lore, material, displayName, owner);
//    }

    public PropertyInteger(Integer value, String name, Integer defValue) {
        super(value, name, defValue);
    }


/*
    @Override
    public void editValue(Player player) {
        new ListenChat((str) -> {
            str.ifPresent(s -> setValue(Integer.parseInt(s)));

            PropertyEditor propertyEditor = new PropertyEditor(getOwner());
            propertyEditor.generate();
            player.openInventory(propertyEditor.getInventory());

        }, player);
    }
*/

    @Override
    public PropertyType getType() {
        return PropertyType.INTEGER;
    }
}
