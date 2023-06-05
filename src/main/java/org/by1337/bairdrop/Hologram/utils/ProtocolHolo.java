package org.by1337.bairdrop.Hologram.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.by1337.bairdrop.util.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ProtocolHolo implements Listener {
    private List<String> lines;
    private final Location location;
    private final List<Integer> ids = new ArrayList<>();
    private List<PacketContainer> packetSpawn = new ArrayList<>();
    private List<PacketContainer> packetMeta = new ArrayList<>();

    public ProtocolHolo(List<String> lines, Location location) {
        this.lines = lines;
        this.location = location.clone().add(0, -2.5, 0);
        Random random = new Random();
        double offsets = 0D;
        for(String line : lines){
            int id = random.nextInt(Integer.MAX_VALUE);
            packetSpawn.add(create(id, this.location.clone().add(0, -offsets, 0)));
            packetMeta.add(createMeta(id, line));
            ids.add(id);
            offsets += 0.3;
        }
    }

    public void spawn() {
        for (PacketContainer spawn : packetSpawn){
            for(Player player : Bukkit.getOnlinePlayers()) {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, spawn);
            }
        }
        for (PacketContainer meta : packetMeta){
            for(Player player : Bukkit.getOnlinePlayers()) {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, meta);
            }
        }

    }
    private PacketContainer create(int id, Location location) {

        PacketContainer packet = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY_LIVING);
        packet.getIntegers().write(0, id);
        packet.getUUIDs().write(0, UUID.randomUUID());
        packet.getIntegers().write(1,  1);

        packet.getDoubles()
                .write(0, location.getX())
                .write(1, location.getY())
                .write(2, location.getZ());
        return packet;
    }
    private PacketContainer createMeta(int id, String name) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        EasyMetadataPacket metadata = new EasyMetadataPacket(null);

        byte bitmask = 0x00;
        bitmask |= 0x20;

        metadata.write(0, bitmask);

        metadata.writeOptional(2, WrappedChatComponent.fromText(Message.messageBuilder(name)));
        metadata.write(3, true);
        metadata.write(4, false);
        metadata.write(5, false);
        metadata.write(7, 0);

        packet.getIntegers().write(0, id);
        packet.getWatchableCollectionModifier().write(0, metadata.export());

        return packet;
    }

//    public void remove() {
//        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
//        int[] entityIds = new int[ids.size()];
//        for(int x = 0; x < ids.size(); x++){
//            entityIds[x] = ids.get(x);
//        }
//        packet.getIntegerArrays().write(0, entityIds);
//
//        // Отправить пакет удаления всем игрокам
//        for (Player player : Bukkit.getOnlinePlayers()) {
//            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
//        }
//
//
//    }

    public void remove() {

        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
        int[] entityIds = new int[ids.size()];
        for(int x = 0; x < ids.size(); x++){
            entityIds[x] = ids.get(x);
        }
        packet.getIntegerArrays().write(0, entityIds);

        // Отправить пакет удаления всем игрокам
        for (Player player : Bukkit.getOnlinePlayers()) {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        }


    }
}
