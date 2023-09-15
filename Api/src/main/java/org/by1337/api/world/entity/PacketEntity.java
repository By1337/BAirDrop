package org.by1337.api.world.entity;

import org.by1337.api.network.protocol.game.MobMetaDataPacket;
import org.by1337.api.network.protocol.game.SpawnMobPacket;

import java.util.Objects;
import java.util.UUID;

public interface PacketEntity {
    SpawnMobPacket createSpawnPacket();

    MobMetaDataPacket createMetaPacket();

    int getId_();

    UUID getUUID_();

    double getX_();

    double getY_();

    double getZ_();
    Object getEntityType_();
    Object getSynchedEntityData_();
}
