package org.by1337.lib.chat;

import java.awt.*;
/**
 * Represents a chat color with the option to convert to/from hexadecimal values.
 */
public class ChatColor {
    private Color color;

    /**
     * Constructs a ChatColor from a given Color object.
     *
     * @param color The Color object representing the chat color.
     */
    public ChatColor(Color color) {
        this.color = color;
    }

    /**
     * Constructs a ChatColor from a hexadecimal color code.
     *
     * @param hex The hexadecimal color code (e.g., "#RRGGBB").
     */
    public ChatColor(String hex) {
        if (hex.startsWith("#")) {
            hex = hex.substring(1);
        }
        int red = Integer.parseInt(hex.substring(0, 2), 16);
        int green = Integer.parseInt(hex.substring(2, 4), 16);
        int blue = Integer.parseInt(hex.substring(4, 6), 16);
         color = new Color(red, green, blue);
    }

    /**
     * Converts the ChatColor to its hexadecimal representation.
     *
     * @return The hexadecimal color code.
     */
    public String toHex(){
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        return String.format("#%02X%02X%02X", red, green, blue).toLowerCase();
    }
    public static ChatColor fromHex(String hex){
        if (hex.equals("black")){
            return new ChatColor("#000000");
        }else if (hex.equals("red")){
            return new ChatColor("#FF5555");
        }else if (hex.equals("dark_blue")){
            return new ChatColor("#0000AA");
        }else if (hex.equals("light_purple")){
            return new ChatColor("#FF55FF");
        }else if (hex.equals("dark_green")){
            return new ChatColor("#00AA00");
        }else if (hex.equals("yellow")){
            return new ChatColor("#FFFF55");
        }else if (hex.equals("dark_aqua")){
            return new ChatColor("#00AAAA");
        }else if (hex.equals("white")){
            return new ChatColor("#FFFFFF");
        }else if (hex.equals("dark_red")){
            return new ChatColor("#AA0000");
        }else if (hex.equals("dark_purple")){
            return new ChatColor("#AA00AA");
        }else if (hex.equals("gold")){
            return new ChatColor("#FFAA00");
        }else if (hex.equals("gray")){
            return new ChatColor("#AAAAAA");
        }else if (hex.equals("dark_gray")){
            return new ChatColor("#555555");
        }else if (hex.equals("blue")){
            return new ChatColor("#5555FF");
        }else if (hex.equals("green")){
            return new ChatColor("#55FF55");
        }else if (hex.equals("aqua")){
            return new ChatColor("#55FFFF");
        }
        return new ChatColor(hex);
    }
}
