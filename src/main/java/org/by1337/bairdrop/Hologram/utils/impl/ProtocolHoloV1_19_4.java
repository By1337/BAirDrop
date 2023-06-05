package org.by1337.bairdrop.Hologram.utils.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataValue;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.Hologram.utils.IProtocolHolo;
import org.by1337.bairdrop.util.Message;

import java.util.*;

public class ProtocolHoloV1_19_4 implements IProtocolHolo {
    private List<String> lines;
    private final Location location;
    private final List<Integer> ids = new ArrayList<>();
    private List<PacketContainer> packetSpawn = new ArrayList<>();
    private List<PacketContainer> packetMeta = new ArrayList<>();

    public ProtocolHoloV1_19_4(List<String> lines, Location location) {
        this.lines = lines;
        this.location = location.clone().add(0, -2, 0);
        Random random = new Random();
        double offsets = 0D;
        for (String line : lines) {
            int id = random.nextInt(Integer.MAX_VALUE);
            packetSpawn.add(create(id, this.location.clone().add(0, -offsets, 0)));
            packetMeta.add(createMeta(id, line));
            ids.add(id);
            offsets += 0.3;
        }
    }

    public void spawn() {
        for (PacketContainer spawn : packetSpawn) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, spawn);
            }
        }
        for (PacketContainer meta : packetMeta) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, meta);
            }
        }

    }

    private PacketContainer create(int id, Location location) {

        PacketContainer packet = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
        packet.getIntegers().write(0, id);
        packet.getUUIDs().write(0, UUID.randomUUID());
        packet.getIntegers().write(1, 1);
        packet.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);

        packet.getDoubles()
                .write(0, location.getX())
                .write(1, location.getY())
                .write(2, location.getZ());

        return packet;
    }

    private PacketContainer createMeta(int id, String name) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, id);
        WrappedDataWatcher watcher = new WrappedDataWatcher();

        try {
            Class.forName("com.comphenix.protocol.wrappers.WrappedDataValue");

            packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
            final List<WrappedDataValue> wrappedDataValueList = new ArrayList<>();

            wrappedDataValueList.add(new WrappedDataValue(0, WrappedDataWatcher.Registry.get(Byte.class), (byte) 0x20));

            Optional<?> opt = Optional.of(WrappedChatComponent.fromChatMessage(
                    Message.messageBuilder(name))[0].getHandle());

            wrappedDataValueList.add(new WrappedDataValue(2, WrappedDataWatcher.Registry.getChatComponentSerializer(true), opt));
            wrappedDataValueList.add(new WrappedDataValue(3, WrappedDataWatcher.Registry.get(Boolean.class), true));

            wrappedDataValueList.add(new WrappedDataValue(15, WrappedDataWatcher.Registry.get(Byte.class), (byte) 0x01));


            packet.getDataValueCollectionModifier().write(0, wrappedDataValueList);

        } catch (ClassNotFoundException e) {

            WrappedDataWatcher.WrappedDataWatcherObject visible = new WrappedDataWatcher.WrappedDataWatcherObject(
                    0, WrappedDataWatcher.Registry.get(Byte.class));
            watcher.setObject(visible, (byte) 0x20);

            Optional<?> opt = Optional.of(WrappedChatComponent.fromChatMessage(
                    Message.messageBuilder(name))[0].getHandle());
            watcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(2,
                    WrappedDataWatcher.Registry.getChatComponentSerializer(true)), opt);

            WrappedDataWatcher.WrappedDataWatcherObject nameVisible = new WrappedDataWatcher.WrappedDataWatcherObject(
                    3, WrappedDataWatcher.Registry.get(Boolean.class));
            watcher.setObject(nameVisible, true);


            WrappedDataWatcher.WrappedDataWatcherObject small = new WrappedDataWatcher.WrappedDataWatcherObject(
                    15, WrappedDataWatcher.Registry.get(Byte.class));
            watcher.setObject(small, (byte) 0x01);


            packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());
        }
        return packet;
    }

    public void remove() {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
        packet.getIntLists().write(0, ids);

        for (Player player : Bukkit.getOnlinePlayers()) {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        }
    }
}
