package org.by1337.lib.factory;

import org.by1337.api.network.protocol.game.DestroyEntityPacket;
import org.by1337.api.world.entity.PacketEntity;
import org.by1337.lib.Version;
import org.by1337.v1_17.factory.EntityFactoryV1_17;
import org.by1337.v1_17.network.protocol.game.DestroyEntityPacketV1_17;

public class PacketFactory {
    public static final PacketFactory factory = new PacketFactory();
    public DestroyEntityPacket createDestroyEntityPacket(int... ids){
        if (Version.version == Version.V1_16_5) {

        } else if (Version.version == Version.V1_17) {
            return new DestroyEntityPacketV1_17(ids);
        } else if (Version.version == Version.V1_17_1) {

        } else if (Version.version == Version.V1_18) {

        } else if (Version.version == Version.V1_18_1) {

        } else if (Version.version == Version.V1_18_2) {

        } else if (Version.version == Version.V1_19) {

        } else if (Version.version == Version.V1_19_1) {

        } else if (Version.version == Version.V1_19_2) {

        } else if (Version.version == Version.V1_19_3) {

        } else if (Version.version == Version.V1_19_4) {

        } else if (Version.version == Version.V1_20_1) {

        } else{
            throw new IllegalStateException("Unsupported version");
        }
        return null;
    }
    public DestroyEntityPacket createDestroyEntityPacket(PacketEntity entity){
        return createDestroyEntityPacket(entity.getId_());
    }
}
