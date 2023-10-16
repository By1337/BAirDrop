package org.by1337.v1_17.network.factory;

import org.by1337.api.factory.PacketFactory;
import org.by1337.api.network.clientbound.entity.PacketAddEntity;
import org.by1337.api.network.clientbound.entity.PacketRemoveEntity;
import org.by1337.api.network.clientbound.entity.PacketSetEntityData;
import org.by1337.api.network.clientbound.entity.TeleportEntityPacket;
import org.by1337.api.world.entity.PacketEntity;
import org.by1337.v1_17.network.clientbound.PacketAddEntityImpl17;
import org.by1337.v1_17.network.clientbound.PacketRemoveEntityImpl17;
import org.by1337.v1_17.network.clientbound.PacketSetEntityDataImpl17;
import org.by1337.v1_17.network.clientbound.TeleportEntityPacketImpl17;

public class PacketFactoryImpl17 implements PacketFactory {
    @Override
    public PacketAddEntity createPacketAddEntity(PacketEntity packetEntity) {
        return new PacketAddEntityImpl17(packetEntity);
    }

    @Override
    public PacketRemoveEntity createPacketRemoveEntity(PacketEntity entity) {
        return new PacketRemoveEntityImpl17(entity);
    }

    @Override
    public PacketRemoveEntity createPacketRemoveEntity(int... ids) {
        return new PacketRemoveEntityImpl17(ids);
    }

    @Override
    public PacketSetEntityData createPacketSetEntityData(PacketEntity packetEntity) {
        return new PacketSetEntityDataImpl17(packetEntity);
    }

    @Override
    public TeleportEntityPacket createTeleportEntityPacket(PacketEntity entity) {
        return new TeleportEntityPacketImpl17(entity);
    }
    @Override
    public TeleportEntityPacket createTeleportEntityPacket(int id, double x, double y, double z, float pitch, float yaw, boolean onGround) {
        return new TeleportEntityPacketImpl17(id, x, y, z, pitch, yaw, onGround);
    }
}
