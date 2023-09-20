package org.by1337.bairdrop.observer.observer;

import org.bukkit.entity.Player;

import org.by1337.bairdrop.observer.CustomEvent;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Observable {
    void registerObserver(Observer observer);

    void unregisterObserver(Observer observer);

    void notifyObservers(CustomEvent customEvent, @Nullable Player pl);

    boolean hasObserver(Observer observer);

    List<Observer> getObservers();
}
