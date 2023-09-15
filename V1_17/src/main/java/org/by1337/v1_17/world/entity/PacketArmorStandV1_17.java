package org.by1337.v1_17.world.entity;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.by1337.api.network.protocol.game.MobMetaDataPacket;
import org.by1337.api.network.protocol.game.SpawnMobPacket;
import org.by1337.api.world.BLocation;
import org.by1337.api.world.entity.PacketArmorStand;
import org.by1337.v1_17.network.protocol.game.MobMetaDataPacketV1_17;
import org.by1337.v1_17.network.protocol.game.SpawnMobPacketV1_17;

import java.util.UUID;


public class PacketArmorStandV1_17 implements PacketArmorStand {

    private final ArmorStand handle;
    private PacketArmorStandV1_17(Level world, double d0, double d1, double d2) {
        handle = new ArmorStand(world, d0, d1, d2);
    }

    public PacketArmorStandV1_17(Location location) {
        this(((CraftWorld)location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
    }
    public PacketArmorStandV1_17(BLocation location) {
        this(((CraftWorld) Bukkit.getWorld(location.getWorldName())).getHandle(), location.getX(), location.getY(), location.getZ());
    }

    @Override
    public void setGlowingTag_(boolean flag) {
        handle.setGlowingTag(flag);
    }

    @Override
    public void setInvisible_(boolean flag) {
        handle.setInvisible(flag);
    }

    @Override
    public void setSprinting_(boolean flag) {
        handle.setSprinting(flag);
    }

    @Override
    public void setCustomNameVisible_(boolean flag) {
        handle.setCustomNameVisible(flag);
    }

    @Override
    public void setSilent_(boolean flag) {
        handle.setSilent(flag);
    }

    @Override
    public void setNoGravity_(boolean flag) {
        handle.setNoGravity(flag);
    }

    @Override
    public void setMarker_(boolean flag) {
        handle.setMarker(flag);
    }

    @Override
    public void setSmall_(boolean flag) {
        handle.setSmall(flag);
    }

    @Override
    public void setCustomName_(String name) {
        handle.setCustomName(new TextComponent(name));
    }

    @Override
    public String getCustomName_() {
        return handle.getCustomName() == null ? null : handle.getCustomName().getString();
    }

    @Override
    public boolean isNoGravity_() {
        return handle.isNoGravity();
    }

    @Override
    public boolean isMarker_() {
        return handle.isMarker();
    }

    @Override
    public boolean isSmall_() {
        return handle.isSmall();
    }

    @Override
    public SpawnMobPacket createSpawnPacket() {
        return new SpawnMobPacketV1_17(this);
    }

    @Override
    public MobMetaDataPacket createMetaPacket() {
        return new MobMetaDataPacketV1_17(this);
    }

    @Override
    public int getId_() {
        return handle.getId();
    }

    @Override
    public UUID getUUID_() {
        return handle.getUUID();
    }

    @Override
    public double getX_() {
        return handle.getX();
    }

    @Override
    public double getY_() {
        return handle.getY();
    }

    @Override
    public double getZ_() {
        return handle.getZ();
    }

    @Override
    public Object getEntityType_() {
        return handle.getType();
    }

    @Override
    public Object getSynchedEntityData_() {
        return handle.getEntityData();
    }
}
