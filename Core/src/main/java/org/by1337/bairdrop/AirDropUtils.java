package org.by1337.bairdrop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.api.world.BlockPosition;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.observer.observer.Observer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AirDropUtils {
    private static final List<Observer> staticObservers = new ArrayList<>();

    /**
     * Регистрирует статический слушатель
     *
     * @param observer слушатель
     * @throws IllegalArgumentException если этот уже зарегистрирован
     */
    public static void registerStaticObserver(Observer observer) {
        if (staticObservers.contains(observer)) {
            throw new IllegalArgumentException("this static observer is already registered");
        }
        staticObservers.add(observer);
    }

    /**
     * unregister статическиого слушателя
     *
     * @param observer слушатель
     * @throws IllegalArgumentException если этот слушетель не зарегистрирован
     */
    public static void unregisterStaticObserver(Observer observer) {
        if (!staticObservers.remove(observer)) {
            throw new IllegalArgumentException("this static observer is not registered yet");
        }
    }

    /**
     * @param observer слушатель
     * @return зарегистрирован ли этот слушатель
     */
    public static boolean hasStaticObserver(Observer observer) {
        return staticObservers.contains(observer);
    }

    /**
     * @return список зарегистрированных слушателей
     */
    public static List<Observer> getStaticObservers() {
        return new ArrayList<>(staticObservers);
    }


    private static int count = 0;
    @Nullable
    public static Airdrop getAirDropForLocation(@NotNull Location location) {
        for (Airdrop airDrop : BAirDrop.airDrops.values()) {
            if (location.equals(airDrop.getAnyLoc())) return airDrop;
        }
        return null;
    }
    public static String getFormat(int Sec) {
        int hour = Sec / 3600;
        int min = Sec % 3600 / 60;
        int sec = Sec % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
    public static int getTimeToNextAirdrop() {
        int time = -1;
        for (Airdrop airDrop : BAirDrop.airDrops.values()) {
//            if (!airDrop.isAirDropStarted())
//                if (time > airDrop.getTimeToStart() || time == -1) {
//                    time = airDrop.getTimeToStart();
//                }
        }
        return time;
    }
    public static String color(String str) {
        str = str.replace("true", "&atrue");
        str = str.replace("false", "&cfalse");
        str = str.replace(" 0 ", " &c0 ");
        return str;
    }
    public static int getAndIncrease(){
        return count++;
    }
}
