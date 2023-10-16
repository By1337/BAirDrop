package org.by1337.bairdrop.serializable.adapter;

import com.google.gson.*;
import org.bukkit.NamespacedKey;

import java.lang.reflect.Type;


public class NamespacedKeyAdapter  implements JsonDeserializer<NamespacedKey>, JsonSerializer<NamespacedKey> {
    @Override
    public NamespacedKey deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return NamespacedKey.fromString(jsonObject.getAsString());
    }

    @Override
    public JsonElement serialize(NamespacedKey namespacedKey, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        //jsonObject.
        return null;

    }
}
