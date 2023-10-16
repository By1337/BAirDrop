package org.by1337.bairdrop.menu.property.property;

import org.bukkit.Material;

public class PropertyMaterial extends Property<Material> {

    public PropertyMaterial(Material value, String name) {
        super(value, name);
    }

    @Override
    public Material parse(String str) {
        return Material.valueOf(str);
    }

    @Override
    public PropertyType getType() {
        return PropertyType.MATERIAL;
    }

    public PropertyMaterial(Material value, String name, Material defValue) {
        super(value, name, defValue);
    }
}
