package org.by1337.bairdrop.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.ConfigManager.Config;

import java.util.UUID;

/**
 * Объект описывает сгенерированную локацию
 */
public class GenLoc {
    private final Location location;
    private final World world;
    private final Vector offsets;
    private final String airDropId;
    private final UUID uuid;

    public GenLoc(Location location, Vector offsets, String airDropId) {
        this.location = location;
        this.world = location.getWorld();
        this.offsets = offsets;
        this.airDropId = airDropId;
        uuid = UUID.randomUUID();
    }

    public GenLoc(Location location, Vector offsets, String airDropId, UUID uuid) {
        this.location = location;
        this.world = location.getWorld();
        this.offsets = offsets;
        this.airDropId = airDropId;
        this.uuid = uuid;
    }

    public Location getLocation() {
        return location;
    }

    public World getWorld() {
        return world;
    }

    public Vector getOffsets() {
        return offsets;
    }

    public String getAirDropId() {
        return airDropId;
    }
    public void Save(){//locations.air-id.world.uuid
        Config.locations.set(String.format("locations.%s.%s.%s.offsets-x", airDropId, world.getName(), uuid), offsets.getX());
        Config.locations.set(String.format("locations.%s.%s.%s.offsets-y", airDropId, world.getName(), uuid), offsets.getY());
        Config.locations.set(String.format("locations.%s.%s.%s.offsets-z", airDropId, world.getName(), uuid), offsets.getZ());
        Config.locations.set(String.format("locations.%s.%s.%s.x", airDropId, world.getName(), uuid), location.getX());
        Config.locations.set(String.format("locations.%s.%s.%s.y", airDropId, world.getName(), uuid), location.getY());
        Config.locations.set(String.format("locations.%s.%s.%s.z", airDropId, world.getName(), uuid), location.getZ());
    }
}
