package org.by1337.bairdrop.menu.property.property;

public class PropertyString extends Property<String>{
    /**
     * Creates a new property with the specified initial value, name
     *
     * @param value The initial value of the property.
     * @param name  The unique name of the property.
     */
    public PropertyString(String value, String name) {
        super(value, name);
    }

    @Override
    public String parse(String str) {
        return str;
    }
//    public PropertyString(String value, String name, Resource lore, Resource material, Resource displayName, StorageProperties owner) {
//        super(value, name, lore, material, displayName, owner);
//    }

    public PropertyString(String value, String name, String defValue) {
        super(value, name, defValue);
    }


/*
    @Override
    public void editValue(Player player) {
        new ListenChat((str) -> {
            str.ifPresent(this::setValue);
            PropertyEditor propertyEditor = new PropertyEditor(getOwner());
            propertyEditor.generate();
            player.openInventory(propertyEditor.getInventory());

        }, player);
    }
*/

    @Override
    public PropertyType getType() {
        return PropertyType.STRING;
    }

}
