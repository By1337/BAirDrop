package org.by1337.lib;

import org.bukkit.Bukkit;

/**
 * The Version enum represents the different supported versions of the Bukkit server.
 * It is used to determine the server version at runtime.
 */
public enum Version {
    UNKNOWN,
    V1_16_R3, // Represents version 1.16.5
    V1_17_R1, // Represents versions 1.17 & 1.17.1
    V1_18_R1, // Represents versions 1.18 & 1.18.1
    V1_18_R2, // Represents version 1.18.2
    V1_19_R1, // Represents versions 1.19, 1.19.1, & 1.19.2
    V1_19_R2, // Represents version 1.19.3
    V1_19_R3, // Represents version 1.19.4
    V1_20_R1; // Represents versions 1.20 & 1.20.1

    /**
     * The current server version.
     */
    public static Version version;

    /**
     * Initializes the current server version. This method should be called
     * to determine the server version at runtime.
     *
     * @throws UnsupportedVersionException if the server version is not supported.
     */
    public static void init() throws UnsupportedVersionException {
        version = getVersion();
    }

    private static Version getVersion() throws UnsupportedVersionException {
        String ver = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        if (ver.equals("v1_16_R3")) return V1_16_R3;
        if (ver.equals("v1_17_R1")) return V1_17_R1;
        if (ver.equals("v1_18_R1")) return V1_18_R1;
        if (ver.equals("v1_18_R2")) return V1_18_R2;
        if (ver.equals("v1_19_R1")) return V1_19_R1;
        if (ver.equals("v1_19_R2")) return V1_19_R2;
        if (ver.equals("v1_19_R3")) return V1_19_R3;
        if (ver.equals("v1_20_R1")) return V1_20_R1;
        throw new UnsupportedVersionException(String.format("This server version is not supported: %s", ver));
    }

    /**
     * Exception thrown when an unsupported server version is encountered.
     */
    public static class UnsupportedVersionException extends Exception {
        public UnsupportedVersionException(String message) {
            super(message);
        }
    }
}
