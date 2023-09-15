package org.by1337.api.factory;

import org.bukkit.Location;
import org.by1337.api.world.BLocation;
import org.by1337.api.world.entity.PacketArmorStand;

public interface EntityFactory {
    PacketArmorStand createPacketArmorStand(BLocation location);
    PacketArmorStand createPacketArmorStand(Location location);
}
