package org.by1337.lib;

import org.bukkit.Bukkit;

public enum Version {
    UNKNOWN,
    V1_16_5,
    V1_17_1,
    V1_18,
    V1_18_1,
    V1_17;

    public static Version getVersion(){
        String ver = Bukkit.getBukkitVersion();
        if (ver.contains("1.16.5")) return V1_16_5;
        if (ver.contains("1.17.1")) return V1_17_1;
        if (ver.contains("1.17")) return V1_17;
        if (ver.contains("1.18.1")) return V1_18_1;
        if (ver.contains("1.18")) return V1_18;
        return UNKNOWN;
    }
}
