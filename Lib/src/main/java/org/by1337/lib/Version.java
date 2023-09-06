package org.by1337.lib;

import org.bukkit.Bukkit;

public enum Version {
    UNKNOWN,
    V1_16_5;

    public static Version getVersion(){
        String ver = Bukkit.getBukkitVersion();
        if (ver.contains("1.16.5")) return V1_16_5;
        return UNKNOWN;
    }
}
