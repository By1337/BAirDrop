package org.by1337.bairdrop.menu.property.property;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.menu.property.EditableProperties;
import org.by1337.bairdrop.menu.property.ListenChat;
import org.by1337.bairdrop.menu.property.PropertyEditor;

import java.util.List;

public class PropertyDouble extends Property<Double>{
    public PropertyDouble(Double value, String name, Resource lore, Resource material, Resource displayName, EditableProperties owner) {
        super(value, name, lore, material, displayName, owner);
    }

    @Override
    public void editValue(Player player) {
        new ListenChat((str) -> {
            str.ifPresent(s -> setValue(Double.parseDouble(s)));

            PropertyEditor propertyEditor = new PropertyEditor(getOwner());
            propertyEditor.generate();
            player.openInventory(propertyEditor.getInventory());

        }, player);
    }
}
