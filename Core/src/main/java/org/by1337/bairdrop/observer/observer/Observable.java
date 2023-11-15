package org.by1337.bairdrop.observer.observer;

import org.by1337.api.util.NameKey;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public interface Observable {
    void invokeListener(NameKey key, Event event);
    void notifyObservers(Event event);
    void callEvent(Event event);
    boolean hasObserver(@NotNull NameKey nameKey);
    boolean hasObserver(@NotNull Observer observer);
    void unregisterObserver(@NotNull Observer observer);
    void unregisterObserver(@NotNull NameKey nameKey);
    void registerObserver(@NotNull Observer observer);
    void registerObserver(@NotNull NameKey nameKey);
    boolean hasInnerObserver(@NotNull Observer observer);
    boolean hasInnerObserver(@NotNull NameKey nameKey);
    void unregisterInnerObserver(@NotNull Observer observer);
    void unregisterInnerObserver(@NotNull NameKey nameKey);
    void registerInnerObserver(@NotNull Observer observer);
    Set<NameKey> getListeners();
    Map<NameKey, Observer> getInnerObservers();
}
