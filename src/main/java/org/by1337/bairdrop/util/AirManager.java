package org.by1337.bairdrop.util;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

import static org.by1337.bairdrop.BAirDrop.instance;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;
public class AirManager {
    @Nullable
    public static AirDrop getAirDropForLocation(@NotNull Location location) {
        for (AirDrop airDrop : BAirDrop.airDrops.values()) {
            if (Objects.equals(location, airDrop.getAirLoc())) return airDrop;
        }
        return null;
    }
    public static String getFormat(int Sec) {
        int hour = Sec / (Integer.toBinaryString(BAirDrop.info[5]).length() * 360);//3600
        int min = Sec % (Integer.toBinaryString(BAirDrop.info[5]).length() * 360) / 60;
        int sec = Sec % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
    public static String getTimeToNexAirdrop() {
        if (BAirDrop.globalTimer != null)
            return Message.setPlaceholders(null, "%bairdrop_time_start_format%");
        int time = -1;
        for (AirDrop airDrop : BAirDrop.airDrops.values()) {
            if (!airDrop.isAirDropStarted())
                if (time > airDrop.getTimeToStart() || time == -1) {
                    time = airDrop.getTimeToStart();
                }
        }
        return getFormat(time);
    }
    public static String colored(String str) {
        str = str.replace("true", "&atrue");
        str = str.replace("false", "&cfalse");
        str = str.replace(" 0 ", " &c0 ");
        return str;
    }


}