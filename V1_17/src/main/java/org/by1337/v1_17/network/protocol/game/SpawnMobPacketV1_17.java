package org.by1337.v1_17.network.protocol.game;

import io.netty.buffer.Unpooled;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundAddMobPacket;
import net.minecraft.world.entity.EntityType;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.by1337.api.network.protocol.game.SpawnMobPacket;
import org.by1337.api.world.BLocation;
import org.by1337.api.world.entity.PacketArmorStand;
import org.by1337.api.world.entity.PacketEntity;

import java.util.UUID;

public class SpawnMobPacketV1_17 implements SpawnMobPacket {
    private ClientboundAddMobPacket packet;

    public SpawnMobPacketV1_17(PacketEntity entity) {
        int id = entity.getId_();
        UUID uuid = entity.getUUID_();
        double x = entity.getX_();
        double y = entity.getY_();
        double z = entity.getZ_();
        int xd = 0;
        int yd = 0;
        int zd = 0;
        byte yRot = 0;
        byte xRot = 0;
        byte yHeadRot = 0;
        int type = Registry.ENTITY_TYPE.getId((EntityType<?>) entity.getEntityType_());

        FriendlyByteBuf param0 = new FriendlyByteBuf(Unpooled.buffer());
        param0.writeVarInt(id);
        param0.writeUUID(uuid);
        param0.writeVarInt(type);
        param0.writeDouble(x);
        param0.writeDouble(y);
        param0.writeDouble(z);
        param0.writeByte(yRot);
        param0.writeByte(xRot);
        param0.writeByte(yHeadRot);
        param0.writeShort(xd);
        param0.writeShort(yd);
        param0.writeShort(zd);
        packet = new ClientboundAddMobPacket(param0);
    }

    @Override
    public void send(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        craftPlayer.getHandle().connection.send(packet);
    }

    @Override
    public void sendAll(BLocation location, int radius) {
        for (Entity entity : location.getLocation().getWorld().getNearbyEntities(location.getLocation(), radius, radius, radius)){
            if (entity instanceof CraftPlayer craftPlayer){
                craftPlayer.getHandle().connection.send(packet);
            }
        }
    }
}
