package org.by1337.bairdrop.menu.property.property;

import java.util.List;

public class PropertyListString extends Property<List<String>> {
    /**
     * Creates a new property with the specified initial value, name
     *
     * @param value The initial value of the property.
     * @param name  The unique name of the property.
     */
    public PropertyListString(List<String> value, String name) {
        super(value, name);
    }

    @Override
    public List<String> parse(String str) {
        return List.of(str.split(", "));
    }

    public PropertyListString(List<String> value, String name, List<String> defValue) {
        super(value, name, defValue);
    }

    @Override
    public PropertyType getType() {
        return PropertyType.LIST_STRING;
    }
}
