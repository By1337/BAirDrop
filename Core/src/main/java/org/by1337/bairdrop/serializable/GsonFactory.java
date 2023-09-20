package org.by1337.bairdrop.serializable;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
    public static Gson create() {
        return new GsonBuilder()
              //  .registerTypeAdapter(Property.class, new Property.Serializer<>())
                .addSerializationExclusionStrategy(new ExclusionStrategy() {

                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getAnnotation(SkipSerialisation.class) != null;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
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
