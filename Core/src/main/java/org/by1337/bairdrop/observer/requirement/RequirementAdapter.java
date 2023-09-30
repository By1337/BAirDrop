package org.by1337.bairdrop.observer.requirement;

import com.google.gson.*;
import org.by1337.bairdrop.observer.requirement.impl.RequirementNumericalCheck;
import org.by1337.bairdrop.observer.requirement.impl.RequirementStringCheck;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * A custom Gson adapter for serializing and deserializing objects implementing the Requirement interface.
 * This adapter allows dynamic registration of Requirement implementations based on a specified name.
 */
public class RequirementAdapter implements JsonDeserializer<Requirement>, JsonSerializer<Requirement> {

    /**
     * A map that stores the registered Requirement implementations with their associated names.
     */
    private static final HashMap<String, Class<?>> registeredRequirements = new HashMap<>();

    /**
     * Registers a Requirement implementation with a specified name.
     *
     * @param name  The name associated with the Requirement implementation.
     * @param clazz The class of the Requirement implementation to register.
     * @throws IllegalArgumentException if an object with the same name is already registered.
     */
    public static void register(String name, Class<?> clazz) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        if (clazz == null) {
            throw new IllegalArgumentException("Class cannot be null.");
        }

        if (registeredRequirements.containsKey(name)) {
            throw new IllegalArgumentException("A Requirement implementation with the same name '" + name + "' is already registered.");
        }

        registeredRequirements.put(name, clazz);
    }


    @Override
    public Requirement deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String typeValue = jsonObject.get("type").getAsString();

        if (!registeredRequirements.containsKey(typeValue)) {
            throw new JsonParseException("Unsupported Requirement type: " + typeValue);
        }

        return context.deserialize(jsonObject.getAsJsonObject("data"), registeredRequirements.get(typeValue));
    }

    @Override
    public JsonElement serialize(Requirement requirement, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", requirement.getType().getKey().getKey());

        JsonElement requirementJsonElement = context.serialize(requirement, requirement.getClass());
        jsonObject.add("data", requirementJsonElement);

        return jsonObject;
    }

    static {
        register("string_check", RequirementStringCheck.class);
        register("numerical_check", RequirementNumericalCheck.class);
    }
}
