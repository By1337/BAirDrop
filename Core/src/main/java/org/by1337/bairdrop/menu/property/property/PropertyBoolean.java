package org.by1337.bairdrop.menu.property.property;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.menu.property.EditableProperties;
import org.by1337.bairdrop.menu.property.ListenChat;
import org.by1337.bairdrop.menu.property.PropertyEditor;

public class PropertyBoolean extends Property<Boolean>{
    public PropertyBoolean(Boolean value, String name, Resource lore, Resource material, Resource displayName, EditableProperties owner) {
        super(value, name, lore, material, displayName, owner);
    }

    @Override
    public void editValue(Player player) {
        new ListenChat((str) -> {
            setValue(!getValue());
            PropertyEditor propertyEditor = new PropertyEditor(getOwner());
            propertyEditor.generate();
            player.openInventory(propertyEditor.getInventory());
        }, player);
    }

    @Override
    public PropertyType getType() {
        return PropertyType.BOOLEAN;
    }
}
