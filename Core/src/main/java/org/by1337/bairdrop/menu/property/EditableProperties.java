package org.by1337.bairdrop.menu.property;

import lombok.Getter;
import org.by1337.bairdrop.menu.property.property.Property;

import java.util.ArrayList;
import java.util.List;


@Getter
public abstract class EditableProperties {

    private final List<Property<?>> properties = new ArrayList<>();

    public final Property<?> getPropertyByName(String name){
        if (name == null) throw new NullPointerException("name cannot be null!");
        for (Property<?> property : properties) {
            if (property.getName().equals(name)) return property;
        }
        throw new IllegalArgumentException(String.format("unknown property '%s'", name));

    }

    protected <T> Property<T> registerProperty(Property<T> property) {
        if (properties.stream().findFirst().filter(p -> p.getName().equals(property.getName())).isPresent()) {
            throw new IllegalArgumentException("there is already a property with the same name!");
        }
        properties.add(property);
        return property;
    }

}
