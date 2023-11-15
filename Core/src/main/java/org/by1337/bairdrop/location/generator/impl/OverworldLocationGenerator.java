package org.by1337.bairdrop.location.generator.impl;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.by1337.api.world.BlockPosition;
import org.by1337.api.world.Vector2D;
import org.by1337.bairdrop.location.generator.GeneratorSetting;
import org.by1337.bairdrop.location.generator.LocationGenerator;

public class OverworldLocationGenerator extends LocationGenerator {
    public OverworldLocationGenerator(GeneratorSetting setting, World world) {
        super(setting, world);
    }

    @Override
    protected Location generate() {
        Chunk chunk = getRandomChunk();

        Vector2D vector2D = new Vector2D(8, 8);
        int y = getHighestBlock(chunk, (int) vector2D.x, (int) vector2D.z);

        if (y < setting.minY) return null;

        BlockPosition pos = new BlockPosition(8, y, 8);

        Block block = chunk.getBlock(pos.getX(), pos.getY(), pos.getZ());
        if (!setting.whiteListBiomes.contains(block.getBiome()))
            return null;

        pos = pos.add(setting.offsets);

//        if (!isRegionEmpty(setting.regionRadiusCheck, block.getLocation()))
//            return null; // todo

        for (BlockPosition blockPosition : setting.hasBlock) {
            if (!hasBlock(chunk, blockPosition.add(pos))) {
                return null;
            }
        }
        for (BlockPosition blockPosition : setting.hasNoBlock) {
            if (hasBlock(chunk, blockPosition.add(pos))) {
                return null;
            }
        }

        return chunk.getBlock(pos.getX(), pos.getY(), pos.getZ()).getLocation();
    }
}
