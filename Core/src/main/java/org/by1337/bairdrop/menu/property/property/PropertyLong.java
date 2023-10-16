package org.by1337.bairdrop.menu.property.property;

public class PropertyLong extends Property<Long>{
    /**
     * Creates a new property with the specified initial value, name
     *
     * @param value The initial value of the property.
     * @param name  The unique name of the property.
     */
    public PropertyLong(Long value, String name) {
        super(value, name);
    }

    @Override
    public Long parse(String str) {
        return Long.parseLong(str);
    }
//    public PropertyLong(Long value, String name, Resource lore, Resource material, Resource displayName, StorageProperties owner) {
//        super(value, name, lore, material, displayName, owner);
//    }

    public PropertyLong(Long value, String name, Long defValue) {
        super(value, name, defValue);
    }



/*
    @Override
    public void editValue(Player player) {
        new ListenChat((str) -> {
            str.ifPresent(s -> setValue(Long.parseLong(s)));

            PropertyEditor propertyEditor = new PropertyEditor(getOwner());
            propertyEditor.generate();
            player.openInventory(propertyEditor.getInventory());

        }, player);
    }
*/

    @Override
    public PropertyType getType() {
        return PropertyType.LONG;
    }
}
