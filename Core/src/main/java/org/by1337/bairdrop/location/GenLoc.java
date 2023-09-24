package org.by1337.bairdrop.location;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.Vector;

import java.util.UUID;

public interface GenLoc extends ConfigurationSerializable {
    Location getLocation();
    World getWorld();
    Vector getOffsets();
    String getAirDropId();
    UUID getUuid();
}
