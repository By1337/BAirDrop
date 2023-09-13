package org.by1337.lib;

import org.bukkit.Bukkit;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An enumeration representing different server versions.
 */
public enum Version {
    UNKNOWN,
    V1_16_5,
    V1_17,
    V1_17_1,
    V1_18,
    V1_18_1,
    V1_18_2,
    V1_19,
    V1_19_1,
    V1_19_2,
    V1_19_3,
    V1_19_4,
    V1_20_1;

    /**
     * The current server version.
     */
    public static Version version;

    /**
     * Initializes the current server version.
     *
     * @throws UnsupportedVersionException If the server version is not supported.
     */
    public static void init() throws UnsupportedVersionException {
        version = getVersion(Bukkit.getVersion(), Bukkit.getBukkitVersion(), Bukkit.getServer().getClass().getPackage().getName());
    }

    /**
     * Gets the {@link Version} based on the server, Bukkit, and server package versions.
     *
     * @param version       The server version.
     * @param bukkitVersion The Bukkit version.
     * @param serverPackage The server package version.
     * @return The corresponding {@link Version}.
     * @throws UnsupportedVersionException If the server version is not supported.
     */
    public static Version getVersion(String version, String bukkitVersion, String serverPackage) throws UnsupportedVersionException {
        String ver = detectServerVersion(version, bukkitVersion, serverPackage);
        if (ver.equals("16.5")) return V1_16_5;
        if (ver.equals("17.0")) return V1_17;
        if (ver.equals("17.1")) return V1_17_1;
        if (ver.equals("18.0")) return V1_18;
        if (ver.equals("18.1")) return V1_18_1;
        if (ver.equals("18.2")) return V1_18_2;
        if (ver.equals("19.0")) return V1_19;
        if (ver.equals("19.1")) return V1_19_1;
        if (ver.equals("19.2")) return V1_19_2;
        if (ver.equals("19.3")) return V1_19_3;
        if (ver.equals("19.4")) return V1_19_4;
        if (ver.equals("20.1")) return V1_20_1;
        throw new UnsupportedVersionException(String.format("This server version is not supported: %s", version));
    }

    /**
     * Detects the server version based on the input parameters.
     *
     * @param version       The server version.
     * @param bukkitVersion The Bukkit version.
     * @param serverPackage The server package version.
     * @return The detected server version as a string.
     */
    private static String detectServerVersion(String version, String bukkitVersion, String serverPackage) {
        int majorVersion;
        int minorVersion;
        try {
            Pattern versionPattern = Pattern.compile("\\(MC: (\\d)\\.(\\d+)\\.?(\\d+?)?\\)");
            Matcher matcher = versionPattern.matcher(version);

            if (matcher.find()) {
                MatchResult matchResult = matcher.toMatchResult();
                majorVersion = Integer.parseInt(matchResult.group(2), 10);
                if (matchResult.groupCount() >= 3) {
                    minorVersion = Integer.parseInt(matchResult.group(3), 10);
                    return majorVersion + "." + minorVersion;
                }
            } else {
                throw new IllegalStateException();
            }

        } catch (Exception e) {
            try {
                String[] split = bukkitVersion.split("-")[0].split("\\.");
                majorVersion = Integer.parseInt(split[1]);
                if (split.length == 3) {
                    minorVersion = Integer.parseInt(split[2]);
                    return majorVersion + "." + minorVersion;
                }
            } catch (Exception e2) {
                try {
                    String[] split = serverPackage.split("\\.")[3].split("_");
                    majorVersion = Integer.parseInt(split[1]);
                    return majorVersion + ".0";
                } catch (Exception e3) {
                    return "UNKNOWN";
                }
            }
        }
        return majorVersion + ".0";
    }

    /**
     * An exception class to represent unsupported server versions.
     */
    public static class UnsupportedVersionException extends Exception {
        public UnsupportedVersionException(String message) {
            super(message);
        }
    }
}
