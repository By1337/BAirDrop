package org.by1337.bairdrop.effect.util;

import org.bukkit.Color;

import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.util.Message;

public class RGBHelper {
    public static Color getColorWithRgb(String rgb) {
        String[] args = rgb.split(":");
        if (args.length != 3) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("color-error"), rgb));
            return Color.BLACK;
        }
        try {
            return Color.fromRGB(
                    Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2])

            );
        } catch (NumberFormatException e) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("color-error"), rgb));
            e.printStackTrace();
            return Color.BLACK;
        }
    }
}
