package org.by1337.bairdrop.util;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.BAirDrop;

import static org.by1337.bairdrop.ConfigManager.Config.getGeneratorSettings;

public class LocationGeneration {
    private static final HashMap<String, Long> cd = new HashMap<>();
    private World world;

    @Nullable
    public Location getPreLocation(@NotNull AirDrop airDrop) {
        world = airDrop.getWorld();
        if (GeneratorLoc.locs.getOrDefault(airDrop.getId(), new ArrayList<>()).isEmpty()) {
            if (cd.getOrDefault(airDrop.getId() + "001", 0L) < System.currentTimeMillis()) {
                Message.warning(String.format(Config.getMessage("locations-are-absent"), world.getName()));
                Message.warning(Config.getMessage("attempt-use-static-loc"));
                cd.put(airDrop.getId() + "001", System.currentTimeMillis() + 150000L);//скажем что это error 001
            }
            if (airDrop.isUseStaticLoc())
                return airDrop.getStaticLocation();
            return null;
        }
        return PreGeneratedLocations(airDrop);
    }

    @Nullable
    public Location getLocation(@NotNull AirDrop airDrop, boolean isGenerator) {
        world = airDrop.getWorld();
        double x = ThreadLocalRandom.current().nextLong(airDrop.getSpawnRadiusMin(), airDrop.getSpawnRadiusMax());
        double y = Integer.toBinaryString(BAirDrop.info[5]).length() * 100;//100
        double z = ThreadLocalRandom.current().nextLong(airDrop.getSpawnRadiusMin(), airDrop.getSpawnRadiusMax());

        Location loc1 = new Location(world, x, y, z);
        String worldType = String.valueOf(world.getEnvironment());
        if (worldType.equals("NORMAL"))
            return getLocationNORMAL(loc1, airDrop);
        if (worldType.equals("THE_END"))
            return getLocationTHE_END(loc1, airDrop);
        if (worldType.equals("NETHER")) {
            if (!isGenerator && cd.getOrDefault(airDrop.getId() + "002", 0L) < System.currentTimeMillis()) {
                Message.warning(String.format(Config.getMessage("generation-nether"), airDrop.getId()));
                cd.put(airDrop.getId() + "002", System.currentTimeMillis() + 150000L);//а это 002
            }
            return getLocationNETHER(loc1, airDrop);
        }
        if (worldType.equals("CUSTOM")) {
            if (cd.getOrDefault(airDrop.getId() + "003", 0L) < System.currentTimeMillis()) {
                Message.warning(String.format(Config.getMessage("unknown-world-type"), world.getName()));
                cd.put(airDrop.getId() + "003", System.currentTimeMillis() + 150000L); //а это 003
            }
            return getLocationNORMAL(loc1, airDrop);

        }


        return null;
    }

    public static String getWorldKeyByWorld(World world) {
        String worldType = String.valueOf(world.getEnvironment());
        if (worldType.equals("THE_END"))
            return "world-THE_END";
        if (worldType.equals("NETHER"))
            return "world-NETHER";
        return "world-NORMAL";
    }

    @Nullable
    private Location getLocationNORMAL(@NotNull Location location, AirDrop airDrop) {
        double y2 = location.getWorld().getHighestBlockAt(location).getLocation().getY();
        location.setY(y2);
        if (!checkMaxY(location, airDrop, "world-NORMAL.max-y"))
            return null;
        if (isBiomeInBlackList(location)) return null;
        if (getGeneratorSettings().getStringList("black-List").contains(String.valueOf(location.getBlock().getType()))) {
            return null;
        }
        location.add(getOffsets(airDrop));
        if (isRegionEmpty(airDrop, location))

            if (airDrop.isFlatnessCheck()) {
                if (checkForEvenness(location, airDrop))
                    return location;
                return null;
            } else return location;

        return null;
    }


    @Nullable
    private Location getLocationNETHER(@NotNull Location location, AirDrop airDrop) {
        Location loc = location.clone();
        loc.setY(getSettings(airDrop.getGeneratorSettings(), "world-NETHER.check-lava"));
        if (loc.getBlock().getType() == Material.LAVA)
            return null;
        loc.setY(getSettings(airDrop.getGeneratorSettings(), "world-NETHER.start-y") + 1);
        boolean upBlockIsAir = loc.getBlock().isEmpty();
        if (isBiomeInBlackList(location)) {
            return null;
        }
        for (int y = getSettings(airDrop.getGeneratorSettings(), "world-NETHER.start-y"); y > getSettings(airDrop.getGeneratorSettings(), "world-NETHER.end-y"); y--) {
            loc.setY(y);
            if (!loc.getBlock().getType().isAir())
                if (!getGeneratorSettings().getStringList("black-List").contains(String.valueOf(loc.getBlock().getType()))) {
                    if (upBlockIsAir) {
                        if (isRegionEmpty(airDrop, location)) {
                            if (checkMaxY(location, airDrop, "world-NETHER.max-y")) {
                                if (airDrop.isFlatnessCheck()) {
                                    if (checkForEvenness(loc, airDrop)) {
                                        return loc.add(getOffsets(airDrop));
                                    } else {
                                        return null;
                                    }

                                } else
                                    return loc.add(getOffsets(airDrop));
                            }
                        }
                    }
                }
            upBlockIsAir = loc.getBlock().getType().isAir() || getGeneratorSettings().getStringList("ignored-blocks").contains(String.valueOf(location.getBlock().getType()));
        }
        return null;
    }

    @Nullable
    private Location getLocationTHE_END(@NotNull Location location, AirDrop airDrop) {

        location.setY(getSettings(airDrop.getGeneratorSettings(), "world-THE_END.start-y"));
        if (!location.getBlock().isEmpty()) {
            location.setY(location.getWorld().getHighestBlockYAt(location));
            if (getGeneratorSettings().getStringList("black-List").contains(String.valueOf(location.getBlock().getType()))) {
                return null;
            }
            if (!checkMaxY(location, airDrop, "world-THE_END.max-y"))
                return null;
            location.add(getOffsets(airDrop));
            if (isRegionEmpty(airDrop, location)) {
                if (!isBiomeInBlackList(location))

                    if (airDrop.isFlatnessCheck()) {
                        if (checkForEvenness(location, airDrop)) {

                            return location;
                        } else {

                            return null;
                        }
                    } else {

                        return location;
                    }
            }
        }

        return null;
    }

    private boolean checkMaxY(@NotNull Location location, AirDrop airDrop, String path) {
        return getSettings(airDrop.getGeneratorSettings(), path) >= location.getY();
    }

    public boolean checkForEvenness(@NotNull Location location, AirDrop airDrop) {
        for (int y = getSettings(airDrop.getGeneratorSettings(), "check-for-evenness.poz.start-y"); y < getSettings(airDrop.getGeneratorSettings(), "check-for-evenness.poz.end-y"); y++) {
            for (int x = getSettings(airDrop.getGeneratorSettings(), "check-for-evenness.poz.start-x"); x < getSettings(airDrop.getGeneratorSettings(), "check-for-evenness.poz.end-x"); x++) {
                for (int z = getSettings(airDrop.getGeneratorSettings(), "check-for-evenness.poz.start-z"); z < getSettings(airDrop.getGeneratorSettings(), "check-for-evenness.poz.end-z"); z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    if (!location.clone().add(x, y, z).getBlock().isEmpty() && !getGeneratorSettings().getStringList("ignored-blocks").contains(String.valueOf(location.clone().add(x, y, z).getBlock().getType())))
                        return false;
                }
            }
        }
        return true;
    }

    @Nullable
    public Location PreGeneratedLocations(AirDrop airDrop) {
        if (airDrop.getPickPreGenLocs() >= BAirDrop.getInstance().getConfig().getInt("max-experience-pre-generated-location")) {
            if (cd.getOrDefault(airDrop.getId() + "004", 0L) < System.currentTimeMillis()) {
                Message.error(Config.getMessage("search-location-limit"));
                Message.error(String.format(Config.getMessage("search-location-limit-2"), airDrop.getId()));
                cd.put(airDrop.getId() + "004", System.currentTimeMillis() + 75000L);//004
            }
            if (airDrop.isUseStaticLoc())
                return airDrop.getStaticLocation();
            return null;
        }
        airDrop.setPickPreGenLocs(airDrop.getPickPreGenLocs() + 1);

        Location loc = GeneratorLoc.getLocationForAirDrop(airDrop);
        if (loc == null) {
            Message.error(String.format(Config.getMessage("gen-loc-is-null"), airDrop.getId()));
            return null;
        }
        if (loc.clone().add(-getOffsets(airDrop).getX(), -getOffsets(airDrop).getY(), -getOffsets(airDrop).getZ()).getBlock().isEmpty()) {
            GeneratorLoc.removeLoc(loc, airDrop);
            Message.logger(String.format(Config.getMessage("location-isn-t-relevant"), loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()));
            if (airDrop.getEditAirMenu() != null)
                airDrop.getEditAirMenu().menuGenerate("usePreGeneratedLocations");
            return null;
        }
        if (isBiomeInBlackList(loc)) {
            GeneratorLoc.removeLoc(loc, airDrop);
            Message.logger(String.format(Config.getMessage("location-isn-t-relevant"), loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()));
            if (airDrop.getEditAirMenu() != null)
                airDrop.getEditAirMenu().menuGenerate("usePreGeneratedLocations");
            return null;
        }
        if (!isRegionEmpty(airDrop, loc)) {
            GeneratorLoc.removeLoc(loc, airDrop);
            Message.logger(String.format(Config.getMessage("location-isn-t-relevant"), loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()));
            if (airDrop.getEditAirMenu() != null)
                airDrop.getEditAirMenu().menuGenerate("usePreGeneratedLocations");
            return null;
        }
        return loc;
    }

    @Nullable
    public static Block getBlock(AirDrop airDrop) {
        if (airDrop.getAnyLoc() != null)
            return airDrop.getAnyLoc().add(-getOffsets(airDrop).getX(), -getOffsets(airDrop).getY(), -getOffsets(airDrop).getZ()).getBlock();
        return null;
    }

    public boolean isBiomeInBlackList(@NotNull Location location) {
        Biome bom = location.getWorld().getBiome((int) location.getX(), (int) location.getY(), (int) location.getZ());
        return getGeneratorSettings().getStringList("black-List-biome").contains(String.valueOf(bom));
    }

    public static String getBiome(@NotNull Location location) {
        Biome bom = location.getWorld().getBiome((int) location.getX(), (int) location.getY(), (int) location.getZ());
        return String.valueOf(bom);
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
            Message.error(Config.getMessage("overlapping-error"));
            Message.error(e.getLocalizedMessage());
            return true;
        }
    }

    @NotNull
    public static Vector getOffsets(AirDrop airDrop) {
        return new Vector(
                getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.x", getWorldKeyByWorld(airDrop.getWorld()))),
                getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.y", getWorldKeyByWorld(airDrop.getWorld()))),
                getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.z", getWorldKeyByWorld(airDrop.getWorld()))));
    }

    public static int getSettings(String genS, String patch) {
        return getGeneratorSettings().getInt(String.format("settings.%s.%s", genS, patch));
    }
}
