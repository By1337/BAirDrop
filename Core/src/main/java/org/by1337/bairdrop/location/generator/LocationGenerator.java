package org.by1337.bairdrop.location.generator;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.by1337.api.world.BlockPosition;
import org.by1337.api.world.Vector2D;
import org.by1337.bairdrop.BAirDrop;

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
    protected abstract Location generate();

}
