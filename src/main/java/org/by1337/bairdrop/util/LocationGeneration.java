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
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;
public class LocationGeneration {
    private static final HashMap<String, Long> cd = new HashMap<>();

    @Nullable
    public static synchronized Location getPreLocation(@NotNull AirDrop airDrop) {
        if (GeneratorLoc.locations.getOrDefault(airDrop.getWorld().getName(), new ArrayList<>()).isEmpty()) {
            if (cd.getOrDefault(airDrop.getAirId() + "001", 0L) < System.currentTimeMillis()) {
                Message.warning(String.format(Config.getMessage("locations-are-absent"), airDrop.getWorld().getName()));
                Message.warning(Config.getMessage("attempt-use-static-loc"));
                cd.put(airDrop.getAirId() + "001", System.currentTimeMillis() + 150000L);//скажем что это error 001
            }
            if (airDrop.isUseStaticLoc())
                return airDrop.getStaticLocation();
            return null;
        }
        return PreGeneratedLocations(airDrop);
    }

    @Nullable
    public static synchronized Location getLocation(@NotNull AirDrop airDrop, boolean isGenerator) {
        double x = ThreadLocalRandom.current().nextLong(airDrop.getSpawnMin(), airDrop.getSpawnMax());
        double y = 100;
        double z = ThreadLocalRandom.current().nextLong(airDrop.getSpawnMin(), airDrop.getSpawnMax());
        Location loc1 = new Location(airDrop.getWorld(), x, y, z);
        String worldType = String.valueOf(airDrop.getWorld().getEnvironment());
        if (worldType.equals("NORMAL"))
            return getLocationNORMAL(loc1, airDrop);
        if (worldType.equals("THE_END"))
            return getLocationTHE_END(loc1, airDrop);
        if (worldType.equals("NETHER")) {
            if (!isGenerator && cd.getOrDefault(airDrop.getAirId() + "002", 0L) < System.currentTimeMillis()) {
                Message.warning(String.format(Config.getMessage("generation-nether"), airDrop.getAirId()));
                cd.put(airDrop.getAirId() + "002", System.currentTimeMillis() + 150000L);//а это 002
            }
            return getLocationNETHER(loc1, airDrop);
        }
        if (worldType.equals("CUSTOM")) {
            if (cd.getOrDefault(airDrop.getAirId() + "003", 0L) < System.currentTimeMillis()) {
                Message.warning(String.format(Config.getMessage("unknown-world-type"), airDrop.getWorld().getName()));
                cd.put(airDrop.getAirId() + "003", System.currentTimeMillis() + 150000L); //а это 003
            }
            return getLocationNORMAL(loc1, airDrop);

        }


        return null;
    }
    public static String getWorldKeyByWorld(World world){
        String worldType = String.valueOf(world.getEnvironment());
        if (worldType.equals("THE_END"))
            return "world-THE_END";
        if (worldType.equals("NETHER"))
            return "world-NETHER";
        return "world-NORMAL";
    }

    @Nullable
    private static Location getLocationNORMAL(@NotNull Location location, AirDrop airDrop) {
        double y2 = location.getWorld().getHighestBlockAt(location).getLocation().getY();
        location.setY(y2);
        if (Config.getGeneratorSettings().getStringList("black-List").contains(String.valueOf(location.getBlock().getType()))) {
            return null;
        }
        location.add(getSettings(airDrop.getGeneratorSettings(), "world-NORMAL.offsets.x"),
                getSettings(airDrop.getGeneratorSettings(), "world-NORMAL.offsets.y"),
                getSettings(airDrop.getGeneratorSettings(), "world-NORMAL.offsets.z"));
        if (isBiomeInBlackList(location)) return null;
        if (isRegionEmpty(airDrop, location))
            if (checkMaxY(location, airDrop, "world-NORMAL.max-y"))
                if (airDrop.isFlatnessCheck()) {
                    if (checkForEvenness(location, airDrop))
                        return location;
                    return null;
                } else return location;

        return null;
    }

    @Nullable
    private static Location getLocationNETHER(@NotNull Location location, AirDrop airDrop) {
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
                if (!Config.getGeneratorSettings().getStringList("black-List").contains(String.valueOf(loc.getBlock().getType()))) {
                    if (upBlockIsAir) {
                        if (isRegionEmpty(airDrop, location)) {
                            if (checkMaxY(location, airDrop, "world-NETHER.max-y")) {
                                if (airDrop.isFlatnessCheck()) {
                                    if (checkForEvenness(loc, airDrop)) {
                                        return loc.add(getSettings(airDrop.getGeneratorSettings(), "world-NETHER.offsets.x"),
                                                getSettings(airDrop.getGeneratorSettings(), "world-NETHER.offsets.y"),
                                                getSettings(airDrop.getGeneratorSettings(), "world-NETHER.offsets.z"));
                                    } else {
                                        return null;
                                    }

                                } else
                                    return loc.add(getSettings(airDrop.getGeneratorSettings(), "world-NETHER.offsets.x"),
                                            getSettings(airDrop.getGeneratorSettings(), "world-NETHER.offsets.y"),
                                            getSettings(airDrop.getGeneratorSettings(), "world-NETHER.offsets.z"));
                            }
                        }
                    }
                }
            upBlockIsAir = loc.getBlock().getType().isAir() || Config.getGeneratorSettings().getStringList("ignored-blocks").contains(String.valueOf(location.getBlock().getType()));
        }
        return null;
    }

    @Nullable
    private static Location getLocationTHE_END(@NotNull Location location, AirDrop airDrop) {

        location.setY(getSettings(airDrop.getGeneratorSettings(), "world-THE_END.start-y"));
        if (!location.getBlock().isEmpty()) {
            location.setY(location.getWorld().getHighestBlockYAt(location));
            if (Config.getGeneratorSettings().getStringList("black-List").contains(String.valueOf(location.getBlock().getType()))) {
                return null;
            }

            location.add(getSettings(airDrop.getGeneratorSettings(), "world-THE_END.offsets.x"),
                    getSettings(airDrop.getGeneratorSettings(), "world-THE_END.offsets.y"),
                    getSettings(airDrop.getGeneratorSettings(), "world-THE_END.offsets.z"));
            if (isRegionEmpty(airDrop, location)) {
                if (!isBiomeInBlackList(location))
                    if (checkMaxY(location, airDrop, "world-THE_END.max-y"))
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

    private static boolean checkMaxY(@NotNull Location location, AirDrop airDrop, String path) {
        return getSettings(airDrop.getGeneratorSettings(), path) >= location.getY();
    }

    public static boolean checkForEvenness(@NotNull Location location, AirDrop airDrop) {
        for (int y = getSettings(airDrop.getGeneratorSettings(), "check-for-evenness.poz.start-y"); y < getSettings(airDrop.getGeneratorSettings(), "check-for-evenness.poz.end-y"); y++) {
            for (int x = getSettings(airDrop.getGeneratorSettings(), "check-for-evenness.poz.start-x"); x < getSettings(airDrop.getGeneratorSettings(), "check-for-evenness.poz.end-x"); x++) {
                for (int z = getSettings(airDrop.getGeneratorSettings(), "check-for-evenness.poz.start-z"); z < getSettings(airDrop.getGeneratorSettings(), "check-for-evenness.poz.end-z"); z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    if (!location.clone().add(x, y, z).getBlock().isEmpty() && !Config.getGeneratorSettings().getStringList("ignored-blocks").contains(String.valueOf(location.clone().add(x, y, z).getBlock().getType())))
                        return false;
                }
            }
        }
        return true;
    }

    @Nullable
    public static synchronized Location PreGeneratedLocations(AirDrop airDrop) {
        if (airDrop.getAttemptsToPick() >= BAirDrop.instance.getConfig().getInt("max-experience-pre-generated-location")) {
            if (cd.getOrDefault(airDrop.getAirId() + "004", 0L) < System.currentTimeMillis()) {
                Message.error(Config.getMessage("search-location-limit"));
                Message.error(String.format(Config.getMessage("search-location-limit-2"), airDrop.getAirId()));
                cd.put(airDrop.getAirId() + "004", System.currentTimeMillis() + 75000L);//004
            }
            if (airDrop.isUseStaticLoc())
                return airDrop.getStaticLocation();
            return null;
        }
        airDrop.setAttemptsToPick(airDrop.getAttemptsToPick() + 1);
        int locX = ThreadLocalRandom.current().nextInt(GeneratorLoc.locations.getOrDefault(airDrop.getWorld().getName(), new ArrayList<>()).size());

        Location loc = GeneratorLoc.locations.get(airDrop.getWorld().getName()).get(locX);

        if (loc.getWorld() == null) {
            GeneratorLoc.locations.get(airDrop.getWorld().getName()).remove(locX);
            Message.logger(String.format(Config.getMessage("location-isn-t-relevant"), loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()));
            if (airDrop.getEditAirMenu() != null)
                airDrop.getEditAirMenu().menuGenerate("usePreGeneratedLocations");
            return null;
        }
        if (isBiomeInBlackList(loc)) {
            GeneratorLoc.locations.get(airDrop.getWorld().getName()).remove(locX);
            Message.logger(String.format(Config.getMessage("location-isn-t-relevant"), loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()));
            if (airDrop.getEditAirMenu() != null)
                airDrop.getEditAirMenu().menuGenerate("usePreGeneratedLocations");
            return null;
        }
        if (!isRegionEmpty(airDrop, loc)) {
            GeneratorLoc.locations.get(airDrop.getWorld().getName()).remove(locX);
            Message.logger(String.format(Config.getMessage("location-isn-t-relevant"), loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()));
            if (airDrop.getEditAirMenu() != null)
                airDrop.getEditAirMenu().menuGenerate("usePreGeneratedLocations");
            return null;
        }
        if(getBlock(loc, airDrop).isEmpty())
            return null;
        return loc;
    }
    public static Block getBlock(Location loc, AirDrop airDrop){
        if (loc.getWorld().getEnvironment().toString().equals("NORMAL"))
           return loc.clone().add(0, 0, 0).add(-getSettings(airDrop.getGeneratorSettings(), "world-NORMAL.offsets.x"),
                    -getSettings(airDrop.getGeneratorSettings(), "world-NORMAL.offsets.y"),
                    -getSettings(airDrop.getGeneratorSettings(), "world-NORMAL.offsets.z")).getBlock();


        if (loc.getWorld().getEnvironment().toString().equals("NETHER"))
            return loc.clone().add(0, 0, 0).add(-getSettings(airDrop.getGeneratorSettings(), "world-NETHER.offsets.x"),
                    -getSettings(airDrop.getGeneratorSettings(), "world-NETHER.offsets.y"),
                    -getSettings(airDrop.getGeneratorSettings(), "world-NETHER.offsets.z")).getBlock();

        if (loc.getWorld().getEnvironment().toString().equals("THE_END"))
            return loc.clone().add(0, 0, 0).add(-getSettings(airDrop.getGeneratorSettings(), "world-THE_END.offsets.x"),
                    -getSettings(airDrop.getGeneratorSettings(), "world-THE_END.offsets.y"),
                    -getSettings(airDrop.getGeneratorSettings(), "world-THE_END.offsets.z")).getBlock();
        return loc.getBlock();
    }
    public static boolean isBiomeInBlackList(@NotNull Location location) {
        Biome bom = location.getWorld().getBiome((int) location.getX(), (int) location.getY(), (int) location.getZ());
        return Config.getGeneratorSettings().getStringList("black-List-biome").contains(String.valueOf(bom));
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

            Location point1 = new Location(airDrop.getWorld(), location.getX() + airDrop.getAirProtect(), location.getY() + airDrop.getAirProtect(), location.getZ() + airDrop.getAirProtect());
            Location point2 = new Location(airDrop.getWorld(), location.getX() - airDrop.getAirProtect(), location.getY() - airDrop.getAirProtect(), location.getZ() - airDrop.getAirProtect());

            ProtectedCuboidRegion region = new ProtectedCuboidRegion(airDrop.getAirId() + "_region",
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

    public static int getSettings(String genS, String patch) {
        return Config.getGeneratorSettings().getInt(String.format("settings.%s.%s", genS, patch));
    }
}
