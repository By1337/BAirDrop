package org.by1337.bairdrop.util;

import org.by1337.api.chat.util.LogLevel;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;

@Deprecated
public class GlobalTimer {
    private int timeToStart;
    private int timeToStartCons;
    private AirDrop air;
    private boolean stop;

    public GlobalTimer(int timeToStartCons) {
        this.timeToStartCons = timeToStartCons;
        this.timeToStart = timeToStartCons;
        OLDMessage.debug(String.format(BAirDrop.getConfigMessage().getMessage("global-timer-to-start"), timeToStart), LogLevel.LEVEL_0);
        run();
    }

    public void run() {
//        OLDMessage.debug(BAirDrop.getConfigMessage().getMessage("global-timer-thread-start"), LogLevel.LOW);
//        new BukkitRunnable() {
//            @Override
//            public void run() {
//                if (stop) {
//                    cancel();
//                    OLDMessage.debug(BAirDrop.getConfigMessage().getMessage("global-timer-thread-stop"), LogLevel.LOW);
//                    return;
//                }
//                if (air != null && !BAirDrop.airDrops.containsKey(air.getId())){
//                    air = null;
//                    timeToStart = timeToStartCons;
//                }
//                if (timeToStart <= 0) {
//                    if (air == null) {
//                        OLDMessage.warning(BAirDrop.getConfigMessage().getMessage("global-timer-failed"));
//                        return;
//                    }
//                    air.setTimeCountingEnabled(true);
//                    air.setHideInCompleter(false);
//                    if (air.isAirDropStarted()) {
//                        air = null;
//                        timeToStart = timeToStartCons;
//                    }
//                } else if (air != null && Bukkit.getOnlinePlayers().size() >= air.getMinPlayersToStart()) {
//                    timeToStart--;
//                }
//                if (BAirDrop.airDrops.isEmpty()) {
//                    OLDMessage.error(BAirDrop.getConfigMessage().getMessage("global-timer-failed2"));
//                    timeToStart = timeToStartCons;
//                    return;
//                }
//
//                if (air == null) {
//                    air = AirDropUtils.getRandomCloneAir();
//                    if (air != null) {
//                        air.setHideInCompleter(true);
//                        air.addDec(String.format(BAirDrop.getConfigMessage().getMessage("dec-info"), "global timer"));
//                        BAirDrop.airDrops.put(air.getId(), air);
//                        OLDMessage.debug(String.format(BAirDrop.getConfigMessage().getMessage("global-timer"), air.getId()), LogLevel.LOW);
//                    }
//                }
//            }
//        }.runTaskTimerAsynchronously(BAirDrop.getInstance(), 20, 20);
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
