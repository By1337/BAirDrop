package command;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.NamespacedKey;
import org.by1337.api.world.BLocation;
import org.by1337.bairdrop.menu.property.ListenChat;
import org.by1337.bairdrop.menu.property.property.Property;
import org.by1337.bairdrop.menu.property.property.PropertyBoolean;
import org.by1337.bairdrop.menu.property.property.PropertyListString;
import org.by1337.bairdrop.menu.property.property.PropertyLocation;
import org.by1337.bairdrop.serializable.GsonFactory;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class Clazz {
    @Test
    public void run(){

//
//        HashMap<String, Property<?>> propertyHashMap = new HashMap<>();
//
//        propertyHashMap.put("test", new PropertyBoolean(false, "test"));
//        propertyHashMap.put("test1", new PropertyBoolean(false, "test1"));
//        propertyHashMap.put("loc", new PropertyLocation(new BLocation(123, 10, 10, "world"), "location"));
//        propertyHashMap.put("list", new PropertyListString(List.of("line 1", "line 2"), "list", List.of("123", "123")));
//
//
//        Gson gson = GsonFactory.create();
//        System.out.println(gson.toJson(propertyHashMap));
//
//        Type empMapType = new TypeToken<HashMap<String, Property<?>>>() {
//        }.getType();
//
//        HashMap<String, Property<?>> propertyHashMap2 = gson.fromJson(gson.toJson(propertyHashMap), empMapType);
    }
}
