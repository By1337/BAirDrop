package org.by1337.bairdrop.airdrop;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.by1337.api.match.BMatch;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.observer.observer.Observer;
import org.by1337.api.util.NameKey;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Timer implements Observer {

    private final LinkedList<Airdrop> airdrops = new LinkedList<>();
    private Airdrop current;
    private final BukkitTask task;
    private final Random random = new Random();
    private final NameKey name;
    private final List<Airdrop> bypassed = new ArrayList<>();

    private String spawnCondition = "{is-spawned} == false";

    public Timer(NameKey name) {
        this.name = name;
        task = Bukkit.getScheduler().runTaskTimer(BAirDrop.getInstance(), this::run, 0, 20);
    }

    void stop() {
        task.cancel();
    }

    private void run() {
        bypassed.forEach(Airdrop::tick);
        if (airdrops.isEmpty() && current == null) {
            // task.cancel();
            // TimerRegistry.unregister(name);
            return;
        }
        if (current == null) {
            current = getRandomAir();
            return;
        }
        if (!canSpawn(current)) {
            if (current.getStorageProperties().getAsBoolean("is-spawned")) {
                current.tick();
            } else {
                current = null;
            }
            return;
        }
        current.tick();
    }

    private Airdrop getRandomAir() {
        for (Airdrop airDrop : airdrops) {
            if (canSpawn(airDrop)) {
                if (random.nextInt(100) <= airDrop.getStorageProperties().getAsInteger("spawn-chance")) {
                    return airDrop;
                }
            }
        }
        return null;
    }

    private boolean canSpawn(Airdrop airdrop) {
        return BAirDrop.MESSAGE.messageBuilder(BMatch.matchSave(airdrop.replace(String.format("match[%s]", spawnCondition)))).equals("1");
    }

    public void register(Airdrop airdrop) {
        airdrops.add(airdrop);
        Comparator<Airdrop> airDropComparator = Comparator.comparingInt(air -> air.getStorageProperties().getAsInteger("spawn-chance"));
        airdrops.sort(airDropComparator);
        hookAirDrop(airdrop);
    }

    public void unregister(Airdrop airdrop) {
        airdrops.remove(airdrop);
        Comparator<Airdrop> airDropComparator = Comparator.comparingInt(air -> air.getStorageProperties().getAsInteger("spawn-chance"));
        airdrops.sort(airDropComparator);
        unhookAirDrop(airdrop);
    }

    private void unhookAirDrop(Airdrop airdrop) {
        if (airdrop.hasInnerObserver(getName())) {
            airdrop.unregisterInnerObserver(getName());
        }
    }

    private void hookAirDrop(Airdrop airdrop) {
        airdrop.registerInnerObserver(this);
    }

    @NotNull
    public NameKey getName() {
        return new NameKey("timer_root", true);
    }

    public void setCurrent(Airdrop current) {
        this.current = current;
    }

    @Override
    public void update(Event event) {
        Airdrop airdrop = event.getAirdrop();
        CustomEvent customEvent = event.getEvent();
        if (customEvent.equals(CustomEvent.END_EVENT)) {
            if (airdrop.equals(current)) {
                current = null;
            } else {
                bypassed.remove(airdrop);
            }
        } else if (customEvent.equals(CustomEvent.START_EVENT)) {
            if (!bypassed.contains(airdrop) && !airdrop.equals(current)) {
                bypassed.add(airdrop);
            }
        }
    }
}
