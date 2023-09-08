package org.by1337.api.world;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Getter
@Setter
@ToString
public class BLocation {
    private double x;
    private double y;
    private double z;
    private float yaw = 0;
    private float pitch = 0;
    private String worldName;

    public BLocation(double x, double y, double z, float yaw, float pitch, String worldName) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.worldName = worldName;
    }

    public BLocation(double x, double y, double z, String worldName) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldName = worldName;
    }

    public BLocation(Location location) {
        this(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), location.getWorld().getName());
    }

    public Location getLocation() {
        return new Location(
                Bukkit.getWorld(worldName),
                x,
                y,
                z,
                yaw,
                pitch
        );
    }
}
