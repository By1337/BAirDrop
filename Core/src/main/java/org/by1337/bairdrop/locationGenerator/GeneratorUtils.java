package org.by1337.bairdrop.locationGenerator;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneratorUtils {

    public static int getSettings(String genS, String patch) {
        return BAirDrop.getiConfig().getGeneratorSettings().getInt(String.format("settings.%s.%s", genS, patch));
    }

    @NotNull
    public static Vector getOffsets(AirDrop airDrop) {
        return new Vector(
                getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.x", getWorldKeyByWorld(airDrop.getWorld()))),
                getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.y", getWorldKeyByWorld(airDrop.getWorld()))),
                getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.z", getWorldKeyByWorld(airDrop.getWorld()))));
    }

    public static String getWorldKeyByWorld(World world) {
        String worldType = String.valueOf(world.getEnvironment());
        if (worldType.equals("THE_END"))
            return "world-THE_END";
        if (worldType.equals("NETHER"))
            return "world-NETHER";
        return "world-NORMAL";
    }

    public static boolean isRegionEmpty(@NotNull AirDrop airDrop, @NotNull Location location) {
        try {
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regions = container.get((BukkitAdapter.adapt(location.getWorld())));

            assert regions != null;

            Location point1 = new Location(airDrop.getWorld(), location.getX() + airDrop.getRegionRadius(), location.getY() + airDrop.getRegionRadius(), location.getZ() + airDrop.getRegionRadius());
            Location point2 = new Location(airDrop.getWorld(), location.getX() - airDrop.getRegionRadius(), location.getY() - airDrop.getRegionRadius(), location.getZ() - airDrop.getRegionRadius());

            ProtectedCuboidRegion region = new ProtectedCuboidRegion(airDrop.getId() + "_region",
                    BlockVector3.at(point1.getX(), point1.getY(), point1.getZ()),
                    BlockVector3.at(point2.getX(), point2.getY(), point2.getZ()));

            Map<String, ProtectedRegion> rg = regions.getRegions();
            List<ProtectedRegion> candidates = new ArrayList<>(rg.values());


            List<ProtectedRegion> overlapping = region.getIntersectingRegions(candidates);

            return overlapping.size() == 0;

        } catch (Exception e) {
            Message.error(BAirDrop.getConfigMessage().getMessage("overlapping-error"));
            Message.error(e.getLocalizedMessage());
            return true;
        }
    }

    public static String getBiome(@NotNull Location location) {
        Biome bom = location.getWorld().getBiome((int) location.getX(), (int) location.getY(), (int) location.getZ());
        return String.valueOf(bom);
    }

    @Nullable
    public static Block getBlock(AirDrop airDrop) {
        if (airDrop.getAnyLoc() != null)
            return airDrop.getAnyLoc().add(-getOffsets(airDrop).getX(), -getOffsets(airDrop).getY(), -getOffsets(airDrop).getZ()).getBlock();
        return null;
    }
}
