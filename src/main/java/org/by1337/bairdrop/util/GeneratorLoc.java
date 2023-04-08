package org.by1337.bairdrop.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.ConfigManager.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GeneratorLoc {
    public static HashMap<String, List<Location>> locations = new HashMap<>();
    static boolean isStarted = false;
    public static void Stop(Player pl){
        if(!isStarted){
            Message.sendMsg(pl, Config.getMessage("generator-not-started")); return;
        }
        isStarted = false;
    }
    public static void Start(AirDrop airDrop, int timings, int count, Player pl) {
        if(isStarted){
            Message.sendMsg(pl, Config.getMessage("generator-already-launched"));
            return;
        }
        isStarted = true;
        if (!locations.containsKey(airDrop.getWorld().getName()))
            locations.put(airDrop.getWorld().getName(), new ArrayList<>());
        Message.sendMsg(pl, Config.getMessage("generator-start"));
        new BukkitRunnable() {
            int x = count;
            int xxx = 0;

            @Override
            public void run() {
                Location loc = LocationGeneration.getLocation(airDrop, true);
                xxx++;
                if (loc != null) {
                    locations.get(loc.getWorld().getName()).add(loc);
                    x--;
                    Message.sendMsg(pl, String.format(Config.getMessage("generator"), x));
                    save();
                    if (airDrop.getEditAirMenu() != null)
                        airDrop.getEditAirMenu().menuGenerate("usePreGeneratedLocations");
                    loc = null;
                } else if (xxx % 100 == 0) {
                    Message.sendMsg(pl, String.format(Config.getMessage("generator-fail"), xxx));
                }

                if (x == 0) {
                    isStarted = false;
                    Message.sendMsg(pl, Config.getMessage("generator-good"));
                    cancel();
                }
                if (!isStarted) {
                    Message.sendMsg(pl, Config.getMessage("generator-stop"));
                    cancel();
                }
            }
        }.runTaskTimer(BAirDrop.instance, timings, timings);
    }

    public static void save() {
        Config.locations.set("loc", null);
        for (String str : locations.keySet()) {
            int x1 = 0;
            for (Location location : locations.get(str)) {
                if (location.getWorld() == null) continue;
                String world = location.getWorld().getName();
                double x = location.getX();
                double y = location.getY();
                double z = location.getZ();
                Config.locations.set("loc." + str + "." + x1 + ".x", x);
                Config.locations.set("loc." + str + "." + x1 + ".y", y);
                Config.locations.set("loc." + str + "." + x1 + ".z", z);
                x1++;
            }
        }
        try {
            Config.locations.save(Config.fileLocations);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void LoadLocations() {
        if (Config.locations.getConfigurationSection("loc") == null)
            return;
        for (String worldName : Config.locations.getConfigurationSection("loc").getKeys(false)) {
            if (!locations.containsKey(worldName))
                locations.put(worldName, new ArrayList<>());
            if (Config.locations.getConfigurationSection("loc." + worldName) == null)
                continue;
            for (String num : Config.locations.getConfigurationSection("loc." + worldName).getKeys(false)) {
                try {
                    String x = Objects.requireNonNull(Config.locations.getString("loc." + worldName + "." + num + ".x"));
                    String y = Objects.requireNonNull(Config.locations.getString("loc." + worldName + "." + num + ".y"));
                    String z = Objects.requireNonNull(Config.locations.getString("loc." + worldName + "." + num + ".z"));
                    Location loc = new Location(Bukkit.getWorld(worldName), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
                    locations.get(worldName).add(loc);
                } catch (NullPointerException | NumberFormatException e) {
                    Message.warning(Config.getMessage("generator-loc-error"));
                }


            }
        }
    }
}
