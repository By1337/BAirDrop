package org.by1337.bairdrop.util;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;
public class GlobalTimer {
    int timeToStart;
    int timeToStartCons;
    AirDrop air;
    boolean stop;

    public GlobalTimer(int timeToStartCons) {
        this.timeToStartCons = timeToStartCons;
        this.timeToStart = timeToStartCons;
        Message.debug("[GlobalTimer] До старта: " + timeToStart);
        run();
    }

    public void run() {
        Message.debug("[GlobalTimer] запускаю поток");
        new BukkitRunnable() {
            @Override
            public void run() {
                if(stop){
                    cancel();
                    Message.debug("[GlobalTimer] завершаю поток");
                    return;
                }
                if (timeToStart <= 0) {
                    if (air == null) {
                        Message.warning("Не удалось подобрать аирдроп! global-time");
                        return;
                    }
                    air.setCountTheTime(true);
                    air.setHideInCompleter(false);
                    if(air.isAirDropStarted()){
                        air = null;
                        timeToStart = timeToStartCons;
                    }
                } else if(air != null && Bukkit.getOnlinePlayers().size() >= air.getMinOnlinePlayers()){
                    timeToStart--;
                }
                if (BAirDrop.airDrops.isEmpty()) {
                    Message.error("global-time Список аирдропов пуст!");
                    timeToStart = timeToStartCons;
                    return;
                }

                if (air == null) {
                    air = getRandomAir();
                    if (air != null){
                        air.setHideInCompleter(true);
                        BAirDrop.airDrops.put(air.getAirId(), air);
                        Message.debug("[GlobalTimer] Выбрал аирдроп " + air.getAirId());
                    }
                }
            }
        }.runTaskTimer(BAirDrop.instance, 20, 20);
    }

    @Nullable
    private AirDrop getRandomAir() {
        for (AirDrop air : BAirDrop.airDrops.values()) {
            if (!air.isAirDropStarted() && !air.isClone() && (Bukkit.getOnlinePlayers().size() >= air.getMinOnlinePlayers()))
                if (ThreadLocalRandom.current().nextInt(0, 100) <= air.getChance()) {
                    //if(!air.isClone()){
                    String newid = air.getAirId() + "_clone" + ThreadLocalRandom.current().nextInt(99999);
                    while (BAirDrop.airDrops.containsKey(newid))
                        newid = air.getAirId() + "_clone" + ThreadLocalRandom.current().nextInt(99999);

                    AirDrop aair = air.clone(newid);
                    aair.setClone(true);
                    aair.setKill(true);
                    return aair;
                    //  }else return null;
                }
        }
        for (AirDrop air : BAirDrop.airDrops.values()) {
            String newid = air.getAirId() + "_clone" + ThreadLocalRandom.current().nextInt(99999);
            while (BAirDrop.airDrops.containsKey(newid))
                newid = air.getAirId() + "_clone" + ThreadLocalRandom.current().nextInt(99999);

            AirDrop aair = air.clone(newid);
          //  AirDrop aair = air.clone(air.getAirId() + "_clone" + ThreadLocalRandom.current().nextInt(99999));
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
