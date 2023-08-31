package org.by1337.bairdrop.hologram.utils.impl;

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
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.hologram.utils.HoloLine;
import org.by1337.bairdrop.util.Message;

import java.util.*;

public class HoloLineV1_19_4 implements HoloLine {
    private String name;
    private Location location;
    private final Integer id;
    private PacketContainer spawnPacket;
    private PacketContainer metaPacket;

    public HoloLineV1_19_4(String name, Location location) {
        this.name = name;
        this.location = location;
        id = new Random().nextInt(Integer.MAX_VALUE);
        spawnPacket = create();
        metaPacket = createMeta();
        Bukkit.getServer().getPluginManager().registerEvents(this, BAirDrop.getInstance());
    }

    @Override
    public void spawn() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, spawnPacket);
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, metaPacket);
        }
    }

    @Override
    public void spawn(Player player) {
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, spawnPacket);
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, metaPacket);
    }

    @Override
    public void remove() {
        HandlerList.unregisterAll(this);

        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
        packet.getIntLists().write(0, Collections.singletonList(id));

        for (Player player : Bukkit.getOnlinePlayers()) {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        }
    }

    @Override
    public void updateName(String name) {
        if(!name.equals(this.name)){
            this.name = name;
            metaPacket = createMeta();
            for (Player player : Bukkit.getOnlinePlayers()) {
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, metaPacket);
            }
        }
    }

    private PacketContainer create() {
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

    private PacketContainer createMeta() {
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
    @EventHandler
    public void join(PlayerJoinEvent e){
        if(Objects.equals(e.getPlayer().getLocation().getWorld(), location.getWorld()))
            spawn(e.getPlayer());
    }
    @EventHandler
    public void ChangedWorld(PlayerChangedWorldEvent e){
        if(Objects.equals(e.getPlayer().getLocation().getWorld(), location.getWorld()))
            spawn(e.getPlayer());
    }
}
