package org.by1337.bairdrop.menu.property;

import lombok.Getter;
import org.by1337.bairdrop.menu.property.property.Property;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This abstract class represents a collection of editable properties using a Map structure.
 * Each property is associated with a unique name and can be retrieved by its name.
 * New properties can be registered in the collection.
 */
@Getter
public abstract class StorageProperties {

    private final Map<String, Property<?>> properties = new HashMap<>();

    /**
     * Retrieves a property by its name.
     *
     * @param name The name of the property to retrieve.
     * @return The property with the specified name.
     * @throws NullPointerException   if the provided name is null.
     * @throws IllegalArgumentException if no property is found with the given name.
     */
    public Property<?> getProperty(String name){
        if (name == null) throw new NullPointerException("Name cannot be null!");
        Property<?> property = properties.getOrDefault(name, null);
        if (property == null) {
            throw new IllegalArgumentException(String.format("Unknown property '%s'", name));
        }
        return property;
    }

    /**
     * Registers a new property in the collection.
     *
     * @param property The property to register.
     * @return The registered property.
     * @throws IllegalArgumentException if a property with the same name already exists.
     */
    protected <T> Property<T> putProperty(Property<T> property) {
        if (properties.containsKey(property.getName())) {
            throw new IllegalArgumentException("A property with the same name already exists!");
        }
        properties.put(property.getName(), property);
        return property;
    }

    protected <T> Property<T> putProperty(Property<T> property, boolean replace) {
        if (!replace && properties.containsKey(property.getName())) {
            throw new IllegalArgumentException("A property with the same name already exists!");
        }
        properties.put(property.getName(), property);
        return property;
    }


    public Set<String> getPropertiesNames(){
        return properties.keySet();
    }

}
