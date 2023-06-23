package org.by1337.bairdrop.serializable;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.by1337.bairdrop.util.Message;

import java.util.Arrays;

public class DeserializeUtils {
    public static String objectSerialize(Object o){
        if (o == null) return "null";
        return o.toString();
    }

    public static Color deserializeColor(String serializedColor) {
       String[] args = serializedColor.split(",");
        return Color.fromRGB(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1]),
                Integer.parseInt(args[2])
        );
    }


    public static Location deserializeLocation(String serializedLocation) {
        if (serializedLocation.equals("null")) return null;
        try {
            String worldString = extractValue(serializedLocation, "{world=CraftWorld{name=").replace("}", "");
            double x = Double.parseDouble(extractValue(serializedLocation, "x="));
            double y = Double.parseDouble(extractValue(serializedLocation, "y="));
            double z = Double.parseDouble(extractValue(serializedLocation, "z="));
            float pitch = Float.parseFloat(extractValue(serializedLocation, "pitch="));
            float yaw = Float.parseFloat(extractValue(serializedLocation, "yaw="));

            World world = (worldString.equals("null")) ? null : Bukkit.getWorld(worldString);
            return new Location(world, x, y, z, yaw, pitch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String extractValue(String serializedLocation, String key) {
        int keyIndex = serializedLocation.indexOf(key);
        if (keyIndex == -1) {
            throw new IllegalArgumentException("Некорректный формат сериализованной строки");
        }
        int valueStartIndex = keyIndex + key.length();
        int valueEndIndex = serializedLocation.indexOf(",", valueStartIndex);
        if (valueEndIndex == -1) {
            valueEndIndex = serializedLocation.indexOf("}", valueStartIndex);
        }
        return serializedLocation.substring(valueStartIndex, valueEndIndex);
    }
}
