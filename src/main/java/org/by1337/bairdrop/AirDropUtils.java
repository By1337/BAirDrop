package org.by1337.bairdrop;

import org.by1337.bairdrop.customListeners.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class AirDropUtils {
    private static final List<Observer> staticObservers = new ArrayList<>();
    /**
     * Регистрирует статический слушатель
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
}
