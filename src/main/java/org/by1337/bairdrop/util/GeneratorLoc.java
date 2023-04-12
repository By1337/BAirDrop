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
    static boolean isStarted = false;

    public static void Stop(Player pl) {
        if (!isStarted) {
            Message.sendMsg(pl, Config.getMessage("generator-not-started"));
            return;
        }
        isStarted = false;
    }

    public static void Start(AirDrop airDrop, int timings, int count, Player pl) {
        if (isStarted) {
            Message.sendMsg(pl, Config.getMessage("generator-already-launched"));
            return;
        }
        //if(!locs.containsKey(airDrop.getAirId()))
         //   locs.put(airDrop.getAirId(), new ArrayList<>());
        isStarted = true;
        Message.sendMsg(pl, Config.getMessage("generator-start"));
        Message.debug(airDrop.getAirId());
        new BukkitRunnable() {
            int count1 = count;
            int fail = 0;
            AirDrop finalAirDrop = airDrop;

            @Override
            public void run() {
                Location loc = LocationGeneration.getLocation(finalAirDrop, true);
                fail++;
                if (loc != null) {

//                    GenLoc genLoc = new GenLoc(loc, LocationGeneration.getOffsets(airDrop), airDrop.getAirId());
//                    List<GenLoc> gll =  locs.getOrDefault(airDrop.getAirId(), new ArrayList<>());
//                    gll.add(genLoc);
//                    locs.put(airDrop.getAirId(),gll);

                    GenLoc genLoc = new GenLoc(loc, LocationGeneration.getOffsets(finalAirDrop), finalAirDrop.getAirId());
                    String airId = finalAirDrop.getAirId();
                    List<GenLoc> existingValues = locs.get(airId);
                    if (existingValues == null) {
                        // Если ключа еще нет в мапе, создаем новый список и добавляем в мапу
                        List<GenLoc> newValues = new ArrayList<>();
                        newValues.add(genLoc);
                        locs.put(airId, newValues);
                        System.out.println("Добавлен новый ключ " + airId + " со значением " + genLoc);
                    } else {
                        // Если ключ уже есть в мапе, проверяем, есть ли уже такое значение в списке
                        boolean valueExists = false;
                        for (GenLoc existingValue : existingValues) {
                            if (existingValue.equals(genLoc)) {
                                valueExists = true;
                                break;
                            }
                        }
                        if (!valueExists) {
                            // Если значение еще не существует в списке, добавляем его
                            existingValues.add(genLoc);
                            locs.put(airId, existingValues);
                            System.out.println("Обновлен ключ " + airId + " (количество элементов: " + existingValues.size() + ")");
                        } else {
                            // Если значение уже есть в списке, ничего не делаем
                            System.out.println("Значение " + genLoc + " уже есть в списке для ключа " + airId);
                        }
                    }



                    count1--;
                    Message.sendMsg(pl, String.format(Config.getMessage("generator"), count1));

                    // save();
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
                }
                if (!isStarted) {
                    Message.sendMsg(pl, Config.getMessage("generator-stop"));
                    GeneratorLoc.save();
                    cancel();
                }
            }
        }.runTaskTimer(BAirDrop.instance, timings, timings);
    }

    public static void save() {
        Config.locations.set("locations", null);
        for (List<GenLoc> locs1 : locs.values()) {
            locs1.forEach(GenLoc::Save);
        }
        try {
            Config.locations.save(Config.fileLocations);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Nullable
    public static Location getLocationForAirDrop(AirDrop airDrop) {
        if (!locs.containsKey(airDrop.getAirId()))
            return null;
        List<GenLoc> locList = locs.get(airDrop.getAirId()).stream().filter(
                gl -> gl.getWorld() == airDrop.getWorld() &&
                        Objects.equals(gl.getOffsets(), LocationGeneration.getOffsets(airDrop))).toList();
        if(locList.isEmpty())
            return null;
        Random random = new Random();
        return locList.get(random.nextInt(locList.size())).getLocation();
    }
    public static int getSizeLocForAirDrop(AirDrop airDrop){
        if (!locs.containsKey(airDrop.getAirId()))
            return 0;
        List<GenLoc> locList = locs.get(airDrop.getAirId()).stream().filter(
                gl -> gl.getWorld() == airDrop.getWorld() &&
                        Objects.equals(gl.getOffsets(), LocationGeneration.getOffsets(airDrop))).toList();
        return locList.size();
    }
    public static void removeLoc(Location location, AirDrop airDrop){
        if (!locs.containsKey(airDrop.getAirId())) return;
        Optional<GenLoc> genLoc = locs.get(airDrop.getAirId()).stream().findFirst().filter(gl -> gl.getLocation().equals(location));
        genLoc.ifPresent(loc -> locs.get(airDrop.getAirId()).remove(loc));
    }

    public static void LoadLocations() {
        if (Config.locations.getConfigurationSection("locations") == null)
            return;

        for (String airId : Config.locations.getConfigurationSection("locations").getKeys(false)) {
            world:
            for (String world : Config.locations.getConfigurationSection(String.format("locations.%s", airId)).getKeys(false)) {
                for (String uuid : Config.locations.getConfigurationSection(String.format("locations.%s.%s", airId, world)).getKeys(false)) {
                    int offsetsX = Config.locations.getInt(String.format("locations.%s.%s.%s.offsets-x", airId, world, uuid));
                    int offsetsY = Config.locations.getInt(String.format("locations.%s.%s.%s.offsets-y", airId, world, uuid));
                    int offsetsZ = Config.locations.getInt(String.format("locations.%s.%s.%s.offsets-z", airId, world, uuid));
                    int x = Config.locations.getInt(String.format("locations.%s.%s.%s.x", airId, world, uuid));
                    int y = Config.locations.getInt(String.format("locations.%s.%s.%s.y", airId, world, uuid));
                    int z = Config.locations.getInt(String.format("locations.%s.%s.%s.z", airId, world, uuid));
                    World world1 = Bukkit.getWorld(world);
                    if (world1 == null) {
                        Message.error(String.format(Config.getMessage("gen-loc-world-is-null"), world));
                        continue world;
                    }
                    Location location = new Location(world1, x, y, z);

                    if (!locs.containsKey(airId))
                        locs.put(airId, new ArrayList<>());
                    locs.getOrDefault(airId, new ArrayList<>()).add(new GenLoc(location, new Vector(offsetsX, offsetsY, offsetsZ), airId, UUID.fromString(uuid)));
                }
            }
        }


//        for (String worldName : Config.locations.getConfigurationSection("loc").getKeys(false)) {
//            if (!locations.containsKey(worldName))
//                locations.put(worldName, new ArrayList<>());
//            if (Config.locations.getConfigurationSection("loc." + worldName) == null)
//                continue;
//            for (String num : Config.locations.getConfigurationSection("loc." + worldName).getKeys(false)) {
//                try {
//                    String x = Objects.requireNonNull(Config.locations.getString("loc." + worldName + "." + num + ".x"));
//                    String y = Objects.requireNonNull(Config.locations.getString("loc." + worldName + "." + num + ".y"));
//                    String z = Objects.requireNonNull(Config.locations.getString("loc." + worldName + "." + num + ".z"));
//                    Location loc = new Location(Bukkit.getWorld(worldName), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
//                    locations.get(worldName).add(loc);
//                } catch (NullPointerException | NumberFormatException e) {
//                    Message.warning(Config.getMessage("generator-loc-error"));
//                }
//
//
//            }
//        }
    }
}
