package org.by1337.bairdrop.LocationGenerator;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.util.Message;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.*;

public class GeneratorLoc {
    //  public static HashMap<String, List<Location>> locations = new HashMap<>();
    public static HashMap<String, List<GenLoc>> locs = new HashMap<>();
    private static boolean isStarted = false;

    public static void Stop(Player pl) {
        if (!isStarted) {
            Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("generator-not-started"));
            return;
        }
        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("generator-stop"));
        isStarted = false;
    }

    public static void Start(AirDrop airDrop, int timings, int count, Player pl) {
        if (isStarted) {
            Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("generator-already-launched"));
            return;
        }
        isStarted = true;
        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("generator-start"));
        new BukkitRunnable() {
            int count1 = count;
            int fail = 0;
            AirDrop finalairDrop = airDrop;

            @Override
            public void run() {
                Generator generator = new Generator();
                Location loc = generator.getLocation(finalairDrop, true);
              //  Message.debug("loc == null =  " + (loc == null));
                fail++;
                if (loc != null) {
                    GenLoc genLoc = new CGenLoc(loc, Generator.getOffsets(finalairDrop), finalairDrop.getId());
                    String airId = finalairDrop.getId();
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
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("generator"), count1));

                    if (finalairDrop.getEditAirMenu() != null)
                        finalairDrop.getEditAirMenu().menuGenerate("usePreGeneratedLocations");
                    loc = null;
                } else if (fail % 100 == 0) {
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("generator-fail"), fail));
                }

                if (count1 == 0) {
                    isStarted = false;
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("generator-good"));
                    GeneratorLoc.save();
                    cancel();
                    return;
                }
                if (!isStarted) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("generator-stop"));
                    GeneratorLoc.save();
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(BAirDrop.getInstance(), timings, timings);
    }

    public static void save() {
        BAirDrop.getiConfig().getLocations().set("locations", null);
        for (List<GenLoc> locs1 : locs.values()) {
            for (GenLoc genLoc : locs1){
                //BConfig.locations.set(String.format("locations.%s.%s.%s.offsets-x", airDropId, world.getName(), uuid), offsets.getX());
                BAirDrop.getiConfig().getLocations().set(String.format("locations.%s.%s.%s", genLoc.getAirDropId(), genLoc.getWorld().getName(), genLoc.getUuid().toString()), genLoc);
            }
        }
        try {
            BAirDrop.getiConfig().getLocations().save(BAirDrop.getiConfig().getFileLocations());
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
                        Objects.equals(gl.getOffsets(), Generator.getOffsets(airDrop))).toList();
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
                        Objects.equals(gl.getOffsets(), Generator.getOffsets(airDrop))).toList();
        return locList.size();
    }
    public static void removeLoc(Location location, AirDrop airDrop){
        if (!locs.containsKey(airDrop.getId())) return;
        Optional<GenLoc> genLoc = locs.get(airDrop.getId()).stream().findFirst().filter(gl -> gl.getLocation().equals(location));
        genLoc.ifPresent(loc -> locs.get(airDrop.getId()).remove(loc));
    }
}
