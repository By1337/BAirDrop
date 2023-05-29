package org.by1337.bairdrop;

import org.by1337.bairdrop.customListeners.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class AirDropUtils {

    public static final List<Observer> staticObservers = new ArrayList<>();

    public void registerStaticObserver(Observer observer) {
        if (staticObservers.contains(observer)) {
            throw new IllegalArgumentException("this static observer is already registered");
        }
        staticObservers.add(observer);
    }


    public void unregisterStaticObserver(Observer observer) {
        if (!staticObservers.remove(observer)) {
            throw new IllegalArgumentException("this static observer is not registered yet");
        }
    }


    public boolean hasStaticObserver(Observer observer) {
        return staticObservers.contains(observer);
    }
}
