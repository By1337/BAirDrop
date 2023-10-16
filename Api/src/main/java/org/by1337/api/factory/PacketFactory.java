package org.by1337.api.factory;

import org.by1337.api.network.clientbound.entity.PacketAddEntity;
import org.by1337.api.network.clientbound.entity.PacketRemoveEntity;
import org.by1337.api.network.clientbound.entity.PacketSetEntityData;
import org.by1337.api.network.clientbound.entity.TeleportEntityPacket;
import org.by1337.api.world.entity.PacketEntity;

public interface PacketFactory {
    PacketAddEntity createPacketAddEntity(PacketEntity packetEntity);

    PacketRemoveEntity createPacketRemoveEntity(PacketEntity entity);

    PacketRemoveEntity createPacketRemoveEntity(int... ids);

    PacketSetEntityData createPacketSetEntityData(PacketEntity packetEntity);

    TeleportEntityPacket createTeleportEntityPacket(PacketEntity entity);
    TeleportEntityPacket createTeleportEntityPacket(int id, double x, double y, double z, float pitch, float yaw, boolean onGround);
}
