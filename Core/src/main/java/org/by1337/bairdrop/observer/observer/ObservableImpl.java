package org.by1337.bairdrop.observer.observer;

import org.by1337.bairdrop.AirDropUtils;
import org.by1337.bairdrop.BAirDrop;

import org.by1337.bairdrop.observer.event.Event;
import org.by1337.api.util.NameKey;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class ObservableImpl implements Observable {
    protected final Set<NameKey> listeners = new HashSet<>();
    protected final Map<NameKey, Observer> innerObservers = new HashMap<>();


    public void registerInnerObserver(@NotNull Observer observer) {
        if (innerObservers.containsKey(observer.getName())) {
            throw new IllegalArgumentException();
        }
        innerObservers.put(observer.getName(), observer);
    }

    public void unregisterInnerObserver(@NotNull NameKey nameKey) {
        if (!innerObservers.containsKey(nameKey)) {
            throw new IllegalArgumentException();
        }
        innerObservers.remove(nameKey);
    }

    public void unregisterInnerObserver(@NotNull Observer observer) {
        unregisterInnerObserver(observer.getName());
    }

    public boolean hasInnerObserver(@NotNull NameKey nameKey) {
        return innerObservers.containsKey(nameKey);
    }

    public boolean hasInnerObserver(@NotNull Observer observer) {
        return innerObservers.containsKey(observer.getName());
    }

    public void registerObserver(@NotNull NameKey nameKey) {
        if (listeners.contains(nameKey)) {
            throw new IllegalArgumentException();
        }
        listeners.add(nameKey);
    }

    public void registerObserver(@NotNull Observer observer) {
        registerObserver(observer.getName());
    }

    public void unregisterObserver(@NotNull NameKey nameKey) {
        if (!listeners.contains(nameKey)) {
            throw new IllegalArgumentException();
        }
        listeners.remove(nameKey);
    }

    public void unregisterObserver(@NotNull Observer observer) {
        unregisterObserver(observer.getName());
    }

    public boolean hasObserver(@NotNull Observer observer) {
        return hasObserver(observer.getName());
    }

    public boolean hasObserver(@NotNull NameKey nameKey) {
        return listeners.contains(nameKey);
    }

    public void callEvent(Event event) {
        notifyObservers(event);
    }


    public void notifyObservers(Event event) {
        innerObservers.values().forEach(o -> o.update(event));
     //   List<NameKey> tempObservers = new ArrayList<>(listeners);
        BAirDrop.getCfg().getListenerLoader().getListeners().forEach(file -> file.getListeners().values().stream().filter(l -> l.customEvent().equals(event.getEvent())).forEach(l -> l.update(event)));
       // tempObservers.forEach(nameKey -> BAirDrop.getCfg().getListenerLoader().update(nameKey, event));
        AirDropUtils.getStaticObservers().forEach(o -> o.update(event));
    }

    public void invokeListener(NameKey key, Event event) {
        innerObservers.values().stream().filter(o -> o.getName().equals(key)).findFirst().ifPresent(o -> o.update(event));
        BAirDrop.getCfg().getListenerLoader().update(key, event);
    }

    public Set<NameKey> getListeners() {
        return listeners;
    }

    public Map<NameKey, Observer> getInnerObservers() {
        return innerObservers;
    }
}
