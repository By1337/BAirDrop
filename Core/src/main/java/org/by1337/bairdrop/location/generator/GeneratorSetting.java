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
   public List<Material> whiteListBlocks = new ArrayList<>();
   public List<Biome> whiteListBiomes = new ArrayList<>();
   public BlockPosition offsets = new BlockPosition();
   public Vector2D center = new Vector2D();
   public int radius;
   public int maxY;
   public int minY;
}
