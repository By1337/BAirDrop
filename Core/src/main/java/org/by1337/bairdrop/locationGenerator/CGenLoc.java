package org.by1337.bairdrop.locationGenerator;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The object describes the generated location
 */
public class CGenLoc implements GenLoc{
    private final Location location;
    private final World world;
    private final Vector offsets;
    private final String airDropId;
    private final UUID uuid;


    public CGenLoc(Location location, Vector offsets, String airDropId) {
        this.location = location;
        this.world = location.getWorld();
        this.offsets = offsets;
        this.airDropId = airDropId;
        uuid = UUID.randomUUID();
    }

    public CGenLoc(Location location, Vector offsets, String airDropId, UUID uuid) {
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

    public UUID getUuid() {
        return uuid;
    }


    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("location", location.clone());
        map.put("offsets", offsets.clone());
        map.put("uuid", uuid.toString());
        map.put("air-id", airDropId);
        return map;
    }

    /**
     * Required method for configuration serialization
     *
     * @param map map to deserialize
     * @return deserialized CGenLoc
     * @see ConfigurationSerializable
     */
    @NotNull
    public static CGenLoc deserialize(@NotNull Map<String, Object> map) {
        String airId = (String) map.get("air-id");
        UUID uuid = UUID.fromString((String) map.get("uuid"));
        Location loc = (Location) map.get("location");
        Vector vector = (Vector) map.get("offsets");
        return new CGenLoc(loc, vector, airId, uuid);
    }
}
