package org.by1337.v1_17.factory;

import org.bukkit.Location;
import org.by1337.api.factory.EntityFactory;
import org.by1337.api.world.BLocation;
import org.by1337.api.world.entity.PacketArmorStand;
import org.by1337.v1_17.world.entity.PacketArmorStandV1_17;

public class EntityFactoryV1_17 implements EntityFactory {
    @Override
    public PacketArmorStand createPacketArmorStand(BLocation location) {
        return new PacketArmorStandV1_17(location);
    }

    @Override
    public PacketArmorStand createPacketArmorStand(Location location) {
        return createPacketArmorStand(new BLocation(location));
    }
}
