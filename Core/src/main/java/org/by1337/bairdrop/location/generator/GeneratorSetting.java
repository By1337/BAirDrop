package org.by1337.bairdrop.location.generator;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.util.Vector;
import org.by1337.api.world.BlockPosition;
import org.by1337.api.world.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class GeneratorSetting {
   public List<BlockPosition> hasBlock = new ArrayList<>();
   public List<BlockPosition> hasNoBlock = new ArrayList<>();
   public List<Material> ignoreBlocks = new ArrayList<>();
   public List<Material> whiteListBlocks = new ArrayList<>(List.of(Material.GRASS_BLOCK, Material.SAND));
   public List<Biome> whiteListBiomes = new ArrayList<>(List.of(Biome.values()));
   public BlockPosition offsets = new BlockPosition(0, 1, 0);
   public Vector2D center = new Vector2D(0, 0);
   public int radius = 500;
   public int maxY = 100;
   public int minY = 30;
   public BlockPosition regionRadius = new BlockPosition(15, 15, 15);

}
