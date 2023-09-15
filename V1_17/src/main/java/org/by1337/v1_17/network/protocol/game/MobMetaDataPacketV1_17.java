package org.by1337.v1_17.network.protocol.game;

import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.by1337.api.network.protocol.game.MobMetaDataPacket;
import org.by1337.api.world.BLocation;
import org.by1337.api.world.entity.PacketEntity;

public class MobMetaDataPacketV1_17 implements MobMetaDataPacket {
    private final ClientboundSetEntityDataPacket packet;

    public MobMetaDataPacketV1_17(PacketEntity entity) {
        packet = new ClientboundSetEntityDataPacket(entity.getId_(), (SynchedEntityData) entity.getSynchedEntityData_(), true);
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
