package org.by1337.v1_17.network.protocol.game;

import net.minecraft.network.protocol.game.ClientboundRemoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.ints.IntArrayList;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.ints.IntList;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.by1337.api.network.protocol.game.DestroyEntityPacket;
import org.by1337.api.world.BLocation;
import org.by1337.api.world.entity.PacketEntity;

public class DestroyEntityPacketV1_17 implements DestroyEntityPacket {

    private final IntList integers;

    public DestroyEntityPacketV1_17(int... ids) {
        integers = new IntArrayList(ids);
    }

    @Override
    public void send(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        for (int x : integers)
            craftPlayer.getHandle().connection.send(new ClientboundRemoveEntityPacket(x));
    }

    @Override
    public void sendAll(BLocation location, int radius) {
        for (Entity entity : location.getLocation().getWorld().getNearbyEntities(location.getLocation(), radius, radius, radius)){
            if (entity instanceof CraftPlayer craftPlayer){
                for (int x : integers)
                    craftPlayer.getHandle().connection.send(new ClientboundRemoveEntityPacket(x));
            }
        }
    }
}
