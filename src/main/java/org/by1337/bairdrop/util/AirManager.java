package org.by1337.bairdrop.util;


import org.bukkit.Location;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.CAirDrop;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class AirManager {
    @Nullable
    public static AirDrop getAirDropForLocation(@NotNull Location location) {
        for (AirDrop airDrop : BAirDrop.airDrops.values()) {
            if (Objects.equals(location, airDrop.getAirDropLocation())) return airDrop;
        }
        return null;
    }
    public static String getFormat(int Sec) {
        int hour = Sec / 3600;//3600
        int min = Sec % 3600 / 60;
        int sec = Sec % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
    public static int getTimeToNextAirdrop() {
        int time = -1;
        for (AirDrop airDrop : BAirDrop.airDrops.values()) {
            if (!airDrop.isAirDropStarted())
                if (time > airDrop.getTimeToStart() || time == -1) {
                    time = airDrop.getTimeToStart();
                }
        }
        return time;
    }
    public static String colored(String str) {
        str = str.replace("true", "&atrue");
        str = str.replace("false", "&cfalse");
        str = str.replace(" 0 ", " &c0 ");
        return str;
    }


}