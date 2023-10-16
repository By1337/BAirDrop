package org.by1337.v1_16_5.network.factory;

import org.by1337.api.factory.PacketFactory;
import org.by1337.api.network.clientbound.entity.PacketAddEntity;
import org.by1337.api.network.clientbound.entity.PacketRemoveEntity;
import org.by1337.api.network.clientbound.entity.PacketSetEntityData;
import org.by1337.api.network.clientbound.entity.TeleportEntityPacket;
import org.by1337.api.world.entity.PacketEntity;
import org.by1337.v1_16_5.network.clientbound.PacketAddEntityImpl165;
import org.by1337.v1_16_5.network.clientbound.PacketRemoveEntityImpl165;
import org.by1337.v1_16_5.network.clientbound.PacketSetEntityDataImpl165;
import org.by1337.v1_16_5.network.clientbound.TeleportEntityPacketImp165;

public class PacketFactoryImpl165 implements PacketFactory {
    @Override
    public PacketAddEntity createPacketAddEntity(PacketEntity packetEntity) {
        return new PacketAddEntityImpl165(packetEntity);
    }

    @Override
    public PacketRemoveEntity createPacketRemoveEntity(PacketEntity entity) {
        return new PacketRemoveEntityImpl165(entity);
    }

    @Override
    public PacketRemoveEntity createPacketRemoveEntity(int... ids) {
        return new PacketRemoveEntityImpl165(ids);
    }

    @Override
    public PacketSetEntityData createPacketSetEntityData(PacketEntity packetEntity) {
        return new PacketSetEntityDataImpl165(packetEntity);
    }

    @Override
    public TeleportEntityPacket createTeleportEntityPacket(PacketEntity entity) {
        return new TeleportEntityPacketImp165(entity);
    }
    @Override
    public TeleportEntityPacket createTeleportEntityPacket(int id, double x, double y, double z, float pitch, float yaw, boolean onGround) {
        return new TeleportEntityPacketImp165(id, x, y, z, pitch, yaw, onGround);
    }
}
