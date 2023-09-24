package org.by1337.bairdrop.airdrop;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.by1337.bairdrop.BAirDrop;


import java.util.*;

public class Timer implements Runnable {
    private final LinkedList<Airdrop> airdrops = new LinkedList<>();
    @Setter
    @Getter
    private Airdrop current;
    @Getter
    private final BukkitTask task;
    private final Random random = new Random();

    public Timer() {
        task = Bukkit.getScheduler().runTaskTimer(BAirDrop.getInstance(), this, 0, 20);
    }

    @Override
    public void run() {
        if (current == null) {
            current = getRandomAir();
            return;
        }
        if (!current.canSpawn()) {
            if (current.isSpawned()) {
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
            if (airDrop.canSpawn()) {
                if (random.nextInt(100) <= airDrop.spawnChance()) {
                    return airDrop;
                }
            }
        }
        return null;
    }

    public void register(Airdrop airdrop) {
        airdrops.add(airdrop);
        Comparator<Airdrop> airDropComparator = Comparator.comparingInt(Airdrop::spawnChance);
        airdrops.sort(airDropComparator);
    }

    public void unregister(Airdrop airdrop) {
        airdrops.remove(airdrop);
        Comparator<Airdrop> airDropComparator = Comparator.comparingInt(Airdrop::spawnChance);
        airdrops.sort(airDropComparator);
    }

}