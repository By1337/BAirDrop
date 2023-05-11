package org.by1337.bairdrop.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.*;

public class GeneratorLoc {
    //  public static HashMap<String, List<Location>> locations = new HashMap<>();
    public static HashMap<String, List<GenLoc>> locs = new HashMap<>();
    private static boolean isStarted = false;

    public static void Stop(Player pl) {
        if (!isStarted) {
            Message.sendMsg(pl, Config.getMessage("generator-not-started"));
            return;
        }
        Message.sendMsg(pl, Config.getMessage("generator-stop"));
        isStarted = false;
    }

    public static void Start(AirDrop airDrop, int timings, int count, Player pl) {
        if (isStarted) {
            Message.sendMsg(pl, Config.getMessage("generator-already-launched"));
            return;
        }
        isStarted = true;
        Message.sendMsg(pl, Config.getMessage("generator-start"));
        new BukkitRunnable() {
            int count1 = count;
            int fail = 0;
            AirDrop finalAirDrop = airDrop;

            @Override
            public void run() {
                LocationGeneration locationGeneration = new LocationGeneration();
                Location loc = locationGeneration.getLocation(finalAirDrop, true);
              //  Message.debug("loc == null =  " + (loc == null));
                fail++;
                if (loc != null) {
                    GenLoc genLoc = new GenLoc(loc, LocationGeneration.getOffsets(finalAirDrop), finalAirDrop.getId());
                    String airId = finalAirDrop.getId();
                    List<GenLoc> existingValues = locs.get(airId);
                    if (existingValues == null) {
                        // Если ключа еще нет в мапе, создаем новый список и добавляем в мапу
                        List<GenLoc> newValues = new ArrayList<>();
                        newValues.add(genLoc);
                        locs.put(airId, newValues);
                      //  System.out.println("Добавлен новый ключ " + airId + " со значением " + genLoc);
                    } else {
                            existingValues.add(genLoc);
                            locs.put(airId, existingValues);
                         //   System.out.println("Обновлен ключ " + airId + " (количество элементов: " + existingValues.size() + ")");
                    }
                    count1--;
                    Message.sendMsg(pl, String.format(Config.getMessage("generator"), count1));

                    if (finalAirDrop.getEditAirMenu() != null)
                        finalAirDrop.getEditAirMenu().menuGenerate("usePreGeneratedLocations");
                    loc = null;
                } else if (fail % 100 == 0) {
                    Message.sendMsg(pl, String.format(Config.getMessage("generator-fail"), fail));
                }

                if (count1 == 0) {
                    isStarted = false;
                    Message.sendMsg(pl, Config.getMessage("generator-good"));
                    GeneratorLoc.save();
                    cancel();
                    return;
                }
                if (!isStarted) {
                    Message.sendMsg(pl, Config.getMessage("generator-stop"));
                    GeneratorLoc.save();
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(BAirDrop.getInstance(), timings, timings);
    }

    public static void save() {
        Config.locations.set("locations", null);
        for (List<GenLoc> locs1 : locs.values()) {
            for (GenLoc genLoc : locs1){
                //Config.locations.set(String.format("locations.%s.%s.%s.offsets-x", airDropId, world.getName(), uuid), offsets.getX());
                Config.locations.set(String.format("locations.%s.%s.%s", genLoc.getAirDropId(), genLoc.getWorld().getName(), genLoc.getUuid().toString()), genLoc);
            }
        }
        try {
            Config.locations.save(Config.fileLocations);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Nullable
    public static Location getLocationForAirDrop(AirDrop airDrop) {
        if (!locs.containsKey(airDrop.getId()))
            return null;
        List<GenLoc> locList = locs.get(airDrop.getId()).stream().filter(
                gl -> gl.getWorld() == airDrop.getWorld() &&
                        Objects.equals(gl.getOffsets(), LocationGeneration.getOffsets(airDrop))).toList();
        if(locList.isEmpty())
            return null;
        Random random = new Random();
        return locList.get(random.nextInt(locList.size())).getLocation();
    }
    public static int getSizeLocForAirDrop(AirDrop airDrop){
        if (!locs.containsKey(airDrop.getId()))
            return 0;
        List<GenLoc> locList = locs.get(airDrop.getId()).stream().filter(
                gl -> gl.getWorld() == airDrop.getWorld() &&
                        Objects.equals(gl.getOffsets(), LocationGeneration.getOffsets(airDrop))).toList();
        return locList.size();
    }
    public static void removeLoc(Location location, AirDrop airDrop){
        if (!locs.containsKey(airDrop.getId())) return;
        Optional<GenLoc> genLoc = locs.get(airDrop.getId()).stream().findFirst().filter(gl -> gl.getLocation().equals(location));
        genLoc.ifPresent(loc -> locs.get(airDrop.getId()).remove(loc));
    }

    public static void LoadLocations() {
        if (Config.locations.getConfigurationSection("locations") == null)
            return;

        for (String airId : Config.locations.getConfigurationSection("locations").getKeys(false)) {
            world:
            for (String world : Config.locations.getConfigurationSection(String.format("locations.%s", airId)).getKeys(false)) {
                for (String uuid : Config.locations.getConfigurationSection(String.format("locations.%s.%s", airId, world)).getKeys(false)) {

                    if(Config.locations.getInt(String.format("locations.%s.%s.%s.v", airId, world, uuid)) == 0){//@Deprecated только для поддержки переезда с 1.0.7.1 и ниже
                        if(!loadOldLoc(airId, world, uuid)){
                            continue world;
                        }
                    }else {
                        GenLoc genLoc = Config.locations.getSerializable(String.format("locations.%s.%s.%s", airId, world, uuid), GenLoc.class);
                        locs.getOrDefault(airId, new ArrayList<>()).add(genLoc);
                    }

                }
            }
        }
    }
    @Deprecated
    private static boolean loadOldLoc(String airId, String world, String uuid){
        int offsetsX = Config.locations.getInt(String.format("locations.%s.%s.%s.offsets-x", airId, world, uuid));
        int offsetsY = Config.locations.getInt(String.format("locations.%s.%s.%s.offsets-y", airId, world, uuid));
        int offsetsZ = Config.locations.getInt(String.format("locations.%s.%s.%s.offsets-z", airId, world, uuid));
        int x = Config.locations.getInt(String.format("locations.%s.%s.%s.x", airId, world, uuid));
        int y = Config.locations.getInt(String.format("locations.%s.%s.%s.y", airId, world, uuid));
        int z = Config.locations.getInt(String.format("locations.%s.%s.%s.z", airId, world, uuid));
        World world1 = Bukkit.getWorld(world);
        if (world1 == null) {
            Message.error(String.format(Config.getMessage("gen-loc-world-is-null"), world));
            return false;
        }
        Location location = new Location(world1, x, y, z);

        if (!locs.containsKey(airId))
            locs.put(airId, new ArrayList<>());
        locs.getOrDefault(airId, new ArrayList<>()).add(new GenLoc(location, new Vector(offsetsX, offsetsY, offsetsZ), airId, UUID.fromString(uuid)));
        return true;
    }
}
