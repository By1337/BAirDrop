package org.by1337.bairdrop.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.ConfigManager.Config;
import org.jetbrains.annotations.NotNull;

import javax.sql.rowset.spi.SyncResolver;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Объект описывает сгенерированную локацию
 */
public class GenLoc implements ConfigurationSerializable {
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

    public UUID getUuid() {
        return uuid;
    }
    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("x", location.getX());
        map.put("y", location.getY());
        map.put("z", location.getZ());
        map.put("offsets-x", offsets.getX());
        map.put("offsets-y", offsets.getY());
        map.put("offsets-z", offsets.getZ());
        map.put("world", location.getWorld().getName());
        map.put("v", 1);
        map.put("uuid", uuid.toString());
        map.put("air-id", airDropId);

        return map;
    }

    /**
     * Required method for configuration serialization
     *
     * @param map map to deserialize
     * @return deserialized item stack
     * @see ConfigurationSerializable
     */
    @NotNull
    public static GenLoc deserialize(@NotNull Map<String, Object> map) {
        double loc_x = (double) map.get("x");
        double loc_y = (double) map.get("y");
        double loc_z = (double) map.get("z");
        double offsets_x = (double) map.get("offsets-x");
        double offsets_y = (double) map.get("offsets-y");
        double offsets_z = (double) map.get("offsets-z");
        World world = Bukkit.getWorld((String) map.get("world"));
        String airId = (String) map.get("air-id");
        UUID uuid = UUID.fromString((String) map.get("uuid"));
        if(world == null){
            throw new IllegalArgumentException("world is null!");
        }
        Location loc = new Location(world, loc_x, loc_y, loc_z);
        Vector vector = new Vector(offsets_x, offsets_y, offsets_z);
        return new GenLoc(loc, vector, airId, uuid);
    }
}
