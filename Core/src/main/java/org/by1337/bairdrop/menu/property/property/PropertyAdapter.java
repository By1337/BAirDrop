package org.by1337.bairdrop.menu.property.property;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashMap;

public class PropertyAdapter implements JsonDeserializer<Property<?>>, JsonSerializer<Property<?>> {

    private static final HashMap<String, Class<?>> registeredProperty = new HashMap<>();

    public static void register(String name, Class<?> clazz) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        if (clazz == null) {
            throw new IllegalArgumentException("Class cannot be null.");
        }

        if (registeredProperty.containsKey(name)) {
            throw new IllegalArgumentException("A Property implementation with the same name '" + name + "' is already registered.");
        }

        registeredProperty.put(name, clazz);
    }
    @Override
    public Property<?> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String typeValue = jsonObject.get("type").getAsString();

        if (!registeredProperty.containsKey(typeValue)) {
            throw new JsonParseException("Unsupported Requirement type: " + typeValue);
        }

        return context.deserialize(jsonObject, registeredProperty.get(typeValue));
    }

    @Override
    public JsonElement serialize(Property<?> property, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        System.out.println(property.getType().getKey().getKey());
        jsonObject.addProperty("type", property.getType().getKey().getKey());

        JsonElement requirementJsonElement = context.serialize(property, property.getClass());
        jsonObject.add("data", requirementJsonElement);

        return jsonObject;
    }
    static {
        register("vector", PropertyVector.class);
        register("string", PropertyString.class);
        register("particle", PropertyParticle.class);
        register("long", PropertyLong.class);
        register("integer", PropertyInteger.class);
        register("double", PropertyDouble.class);
        register("color", PropertyColor.class);
        register("boolean", PropertyBoolean.class);
    }
}
