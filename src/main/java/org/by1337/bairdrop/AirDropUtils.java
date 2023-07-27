package org.by1337.bairdrop;

import org.bukkit.Bukkit;
import org.by1337.bairdrop.customListeners.observer.Observer;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Nullable
    public static AirDrop getRandomCloneAir() {
        List<AirDrop> airDrops = new ArrayList<>(BAirDrop.airDrops.values());
        Comparator<AirDrop> airDropComparator = Comparator.comparingInt(AirDrop::getSpawnChance);
        airDrops.sort(airDropComparator);
        Random random = new Random();
        for (AirDrop airDrop : airDrops) {
            if (airDrop.isUseOnlyStaticLoc() || airDrop.isClone() || airDrop.isAirDropStarted() || (Bukkit.getOnlinePlayers().size() < airDrop.getMinPlayersToStart())) {
                continue;
            }
            if (random.nextInt(100) <= airDrop.getSpawnChance()) {
                String newid = airDrop.getId() + "_" + UUID.randomUUID();
                AirDrop clone = airDrop.clone(newid);
                clone.setClone(true);
                clone.setKill(true);
                return clone;
            }
        }
        return null;
    }

    @Nullable
    public static AirDrop getRandomAir() {
        List<AirDrop> airDrops = new ArrayList<>(BAirDrop.airDrops.values());
        Comparator<AirDrop> airDropComparator = Comparator.comparingInt(AirDrop::getSpawnChance);
        airDrops.sort(airDropComparator);
        Random random = new Random();
        for (AirDrop airDrop : airDrops) {
            if (airDrop.isUseOnlyStaticLoc() || airDrop.isClone() || airDrop.isAirDropStarted() || (Bukkit.getOnlinePlayers().size() < airDrop.getMinPlayersToStart())) {
                continue;
            }
            if (random.nextInt(100) <= airDrop.getSpawnChance()) {
                return airDrop;
            }
        }
        return null;
    }

    private static String getResult(String s) {
        Pattern pattern = Pattern.compile("(-?\\d+)([-+*/])(-?\\d+)");
        Matcher matcher = pattern.matcher(s);
        String total;

        if (matcher.find()) {
            String number1 = matcher.group(1);
            String operator = matcher.group(2);
            String number2 = matcher.group(3);


            switch (operator) {
                case "+" -> total = String.valueOf(Double.parseDouble(number1) + Double.parseDouble(number2));
                case "-" -> total = String.valueOf(Double.parseDouble(number1) - Double.parseDouble(number2));
                case "*" -> total = String.valueOf(Double.parseDouble(number1) * Double.parseDouble(number2));
                case "/" -> total = String.valueOf(Double.parseDouble(number1) / Double.parseDouble(number2));
                default -> {
                    Message.error("unknown operator:  " + operator);
                    total = "0";
                }
            }

        } else {
            Message.error("This is not a mathematical operation!: " + s);
            total = "0";
        }
        return total;
    }

    public static String match(String input) {
        try {
            String pattern = "match\\((.*?)\\)";
            Pattern regexPattern = Pattern.compile(pattern);
            Matcher matcher = regexPattern.matcher(input);

            while (matcher.find()) {
                String s = matcher.group(1);
                s = s.replace(" ", "");
                input = input.replace(matcher.group(0), getResult(s).replace(".0", ""));
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return input;
    }
}
