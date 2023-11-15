package org.by1337.bairdrop.lang;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.by1337.api.util.BGameVersion;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Resource {
    private static HashMap<String, String> map;
    private final String path;

    public Resource(String path) {
        this.path = path;
    }
    public String getString(){
        return Lang.getLang().getMessage(path);
    }
    public List<String> getList(){
        return Lang.getLang().getList(path);
    }

    public static void a(){
        test();
        getAsString("click.to.teleport");
    }
    public static void test(){
        try (InputStream stream = Resource.class.getResourceAsStream("lang/en.json")) {
            assert stream != null;
            try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                Gson gson = new Gson();
                JsonReader jsonReader = new JsonReader(reader);
                Type empMapType = new TypeToken<HashMap<String, String>>() {
                }.getType();

                map = gson.fromJson(jsonReader, empMapType);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getAsString(String patch){
        return map.get(patch);
    }
}
