package org.by1337.bairdrop.menu.property.property;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.menu.property.EditableProperties;
import org.by1337.bairdrop.menu.property.ListenChat;
import org.by1337.bairdrop.menu.property.PropertyEditor;

import java.util.List;

public class PropertyVector extends Property<Vector> {
    public PropertyVector(Vector value, String name, Resource lore, Resource material, Resource displayName, EditableProperties owner) {
        super(value, name, lore, material, displayName, owner);
    }

    @Override
    public PropertyType getType() {
        return PropertyType.VECTOR;
    }

    @Override
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
    }
}
