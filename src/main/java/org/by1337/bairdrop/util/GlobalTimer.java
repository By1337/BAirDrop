package org.by1337.bairdrop.util;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


public class GlobalTimer {
    private int timeToStart;
    private int timeToStartCons;
    private AirDrop air;
    private boolean stop;

    public GlobalTimer(int timeToStartCons) {
        this.timeToStartCons = timeToStartCons;
        this.timeToStart = timeToStartCons;
        Message.debug(String.format(BAirDrop.getConfigMessage().getMessage("global-timer-to-start"), timeToStart), LogLevel.LOW);
        run();
    }

    public void run() {
        Message.debug(BAirDrop.getConfigMessage().getMessage("global-timer-thread-start"), LogLevel.LOW);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (stop) {
                    cancel();
                    Message.debug(BAirDrop.getConfigMessage().getMessage("global-timer-thread-stop"), LogLevel.LOW);
                    return;
                }
                if (timeToStart <= 0) {
                    if (air == null) {
                        Message.warning(BAirDrop.getConfigMessage().getMessage("global-timer-failed"));
                        return;
                    }
                    air.setTimeCountingEnabled(true);
                    air.setHideInCompleter(false);
                    if (air.isAirDropStarted()) {
                        air = null;
                        timeToStart = timeToStartCons;
                    }
                } else if (air != null && Bukkit.getOnlinePlayers().size() >= air.getMinPlayersToStart()) {
                    timeToStart--;
                }
                if (BAirDrop.airDrops.isEmpty()) {
                    Message.error(BAirDrop.getConfigMessage().getMessage("global-timer-failed2"));
                    timeToStart = timeToStartCons;
                    return;
                }

                if (air == null) {
                    air = getRandomAir();
                    if (air != null) {
                        air.setHideInCompleter(true);
                        BAirDrop.airDrops.put(air.getId(), air);
                        Message.debug(String.format(BAirDrop.getConfigMessage().getMessage("global-timer"), air.getId()), LogLevel.LOW);
                    }
                }
            }
        }.runTaskTimerAsynchronously(BAirDrop.getInstance(), 20, 20);
    }

    @Nullable
    private AirDrop getRandomAir() {
        for (AirDrop air : BAirDrop.airDrops.values()) {
            if (!air.isAirDropStarted() && !air.isClone() && (Bukkit.getOnlinePlayers().size() >= air.getMinPlayersToStart()))
                if (ThreadLocalRandom.current().nextInt(0, Integer.toBinaryString(BAirDrop.info[5]).length() * 10) <= air.getSpawnChance()) {//100
                    //if(!air.isClone()){
                    String newid = air.getId() + "_clone" + ThreadLocalRandom.current().nextInt(99999);
                    while (BAirDrop.airDrops.containsKey(newid))
                        newid = air.getId() + "_clone" + ThreadLocalRandom.current().nextInt(99999);

                    AirDrop aair = air.clone(newid);
                    aair.setClone(true);
                    aair.setKill(true);
                    return aair;
                    //  }else return null;
                }
        }
        for (AirDrop air : BAirDrop.airDrops.values()) {
            String newid = air.getId() + "_clone" + UUID.randomUUID().toString();
            while (BAirDrop.airDrops.containsKey(newid))
                newid = air.getId() + "_clone" + UUID.randomUUID().toString();

            AirDrop aair = air.clone(newid);
            //  CAirDrop aair = air.clone(air.getAirId() + "_clone" + ThreadLocalRandom.current().nextInt(99999));
            aair.setClone(true);
            aair.setKill(true);
            return aair;
        }
        return null;
    }

    public int getTimeToStart() {
        return timeToStart;
    }

    public void setTimeToStart(int timeToStart) {
        this.timeToStart = timeToStart;
    }

    public int getTimeToStartCons() {
        return timeToStartCons;
    }

    public void setTimeToStartCons(int timeToStartCons) {
        this.timeToStartCons = timeToStartCons;
    }

    public AirDrop getAir() {
        return air;
    }

    public void setAir(AirDrop air) {
        this.air = air;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
