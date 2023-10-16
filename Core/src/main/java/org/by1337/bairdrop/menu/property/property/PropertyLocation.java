package org.by1337.bairdrop.menu.property.property;

import org.by1337.api.world.BLocation;

public class PropertyLocation extends Property<BLocation> {
    /**
     * Creates a new property with the specified initial value, name
     *
     * @param value The initial value of the property.
     * @param name  The unique name of the property.
     */
    public PropertyLocation(BLocation value, String name) {
        super(value, name);
    }

    @Override
    public BLocation parse(String str) {
        return null;
    }

    public PropertyLocation(BLocation value, String name, BLocation defValue) {
        super(value, name, defValue);
    }

    @Override
    public PropertyType getType() {
        return PropertyType.LOCATION;
    }
}
