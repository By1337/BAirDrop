package org.by1337.bairdrop.location.generator;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.by1337.api.world.BlockPosition;
import org.by1337.api.world.Vector2D;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class LocationGenerator {
    protected final GeneratorSetting setting;
    protected final World world;
    protected LocationGenerator(GeneratorSetting setting, World world) {
        this.setting = setting;
        this.world = world;
    }

    protected Chunk getRandomChunk() {
        int randomX = (int) (setting.center.x + (int) ((Math.random() * 2 - 1) * setting.radius));
        int randomZ = (int) (setting.center.z + (int) ((Math.random() * 2 - 1) * setting.radius));
        return world.getChunkAt(randomX >> 4, randomZ >> 4);
    }
    protected int getHighestBlock(Chunk chunk, Vector2D vector2D){
        boolean upBlockIsAir = false;
        int x = (int) vector2D.x;
        int z = (int) vector2D.z;
        for(int y = setting.maxY; y > setting.minY; y--){
            if(!chunk.getBlock(x, y, z).getType().isAir()){
                if (!setting.whiteListBlocks.contains(chunk.getBlock(x, y, z).getType())) {
                    return -1;
                }
                if(upBlockIsAir)
                    return y;
                else
                    return -1;
            }else {
                upBlockIsAir = true;
            }
        }
        return -1;
    }

    protected boolean hasBlock(Chunk chunk, BlockPosition blockPosition){
        Material type = chunk.getBlock(blockPosition.x, blockPosition.y, blockPosition.z).getType();
        return !setting.ignoreBlocks.contains(type);
    }

    protected boolean isRegionEmpty(int radius, @NotNull Location location) {
        try {
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regions = container.get((BukkitAdapter.adapt(location.getWorld())));

            Location point1 = new Location(location.getWorld(), location.getX() + radius, location.getY() + radius, location.getZ() + radius);
            Location point2 = new Location(location.getWorld(), location.getX() - radius, location.getY() - radius, location.getZ() - radius);

            ProtectedCuboidRegion region = new ProtectedCuboidRegion(UUID.randomUUID() + "_region",
                    BlockVector3.at(point1.getX(), point1.getY(), point1.getZ()),
                    BlockVector3.at(point2.getX(), point2.getY(), point2.getZ()));

            Map<String, ProtectedRegion> rg = regions.getRegions();
            List<ProtectedRegion> candidates = new ArrayList<>(rg.values());

            List<ProtectedRegion> overlapping = region.getIntersectingRegions(candidates);

            return overlapping.isEmpty();
        } catch (Exception e) {
            Message.error(e.getLocalizedMessage());
            return true;
        }
    }

    protected abstract Location generate();

}
