package org.by1337.bairdrop.serializable;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.NamespacedKey;
import org.by1337.bairdrop.menu.property.property.Property;
import org.by1337.bairdrop.menu.property.property.PropertyAdapter;
import org.by1337.bairdrop.menu.property.property.PropertyInteger;
import org.by1337.bairdrop.observer.requirement.Requirement;
import org.by1337.bairdrop.observer.requirement.RequirementAdapter;
import org.by1337.bairdrop.serializable.adapter.NamespacedKeyAdapter;

public class GsonFactory {
    public static Gson create() {
     //   NamespacedKey
        return new GsonBuilder()
            //    .registerTypeAdapter(NamespacedKey.class, new NamespacedKeyAdapter())
                .registerTypeAdapter(Requirement.class, new RequirementAdapter())
                .registerTypeAdapter(Property.class, new PropertyAdapter())
                .setFieldNamingStrategy(field -> {
                    if (field.isAnnotationPresent(SerializableAs.class)) {
                        SerializableAs as = field.getAnnotation(SerializableAs.class);
                        return as.value();
                    }
                    return field.getName();
                })
                .setPrettyPrinting()
                .create();
    }
}
