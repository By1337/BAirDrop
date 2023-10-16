package org.by1337.bairdrop.menu.property.property;

public class PropertyBoolean extends Property<Boolean>{
    /**
     * Creates a new property with the specified initial value, name
     *
     * @param value The initial value of the property.
     * @param name  The unique name of the property.
     */
    public PropertyBoolean(Boolean value, String name) {
        super(value, name);
    }

    @Override
    public Boolean parse(String str) {
        return Boolean.parseBoolean(str);
    }

    public PropertyBoolean(Boolean value, String name, Boolean defValue) {
        super(value, name, defValue);
    }
    //    public PropertyBoolean(Boolean value, String name, Resource lore, Resource material, Resource displayName, StorageProperties owner) {
//        super(value, name, lore, material, displayName, owner);
//    }

//    @Override
//    public void editValue(Player player) {
//        new ListenChat((str) -> {
//            setValue(!getValue());
//            PropertyEditor propertyEditor = new PropertyEditor(getOwner());
//            propertyEditor.generate();
//            player.openInventory(propertyEditor.getInventory());
//        }, player);
//    }

    @Override
    public PropertyType getType() {
        return PropertyType.BOOLEAN;
    }
}
