package org.by1337.v1_17_R1.command;

import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.nbt.MojangsonParser;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.chat.ChatMessage;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EnumMobSpawn;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.by1337.api.command.SummonCommand;
import org.by1337.api.world.BLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SummonCommandV1_17_R1 implements SummonCommand {
    @Override
    public void spawn(@NotNull String entityType, @NotNull BLocation location, @Nullable String nbt) {
        try {
            NBTTagCompound entityData = new NBTTagCompound();
            if (nbt != null){
                entityData = MojangsonParser.parse(nbt);
            }
            MinecraftKey entityKey = new MinecraftKey(entityType);
            entityData.setString("id", entityKey.toString());
            Location location1 = location.getLocation();
            WorldServer world = ((CraftWorld) location1.getWorld()).getHandle();
            Entity entity = EntityTypes.a(entityData, world, (entity1) -> {
                entity1.setPositionRotation(location1.getX(), location1.getY(), location1.getZ(), location1.getYaw(), location1.getPitch());
                return entity1;
            });
            if (entity == null) {
                throw new SimpleCommandExceptionType(new ChatMessage("commands.summon.failed")).create();
            } else {
                if (entity instanceof EntityInsentient) {
                    ((EntityInsentient) entity).prepare(world, world.getDamageScaler(entity.getChunkCoordinates()), EnumMobSpawn.n,  null,  null);
                }

                if (!world.addAllEntitiesSafely(entity, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.COMMAND)) {
                    throw new SimpleCommandExceptionType(new ChatMessage("commands.summon.failed.uuid")).create();
                } else {
                    //System.out.println(new ChatMessage("commands.summon.success", new Object[]{entity.getScoreboardDisplayName()}));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
