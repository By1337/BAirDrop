package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.scheduler.BukkitRunnable;

import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RandomParticle implements IEffect {
    private int ticks = -1;
    private final int timeUpdate;
    private AirDrop airDrop;
    private boolean used;
    private boolean stop;
    private final List<Particle> particle = new ArrayList<>();
    private final double radius;
    private final int count;
    private final FileConfiguration cs;
    private Location loc;
    private final String name;
    private final Map<String, Object> map;

    public RandomParticle(Map<String, Object> map) {
        this.map = map;
        this.cs = null;
        name = "123";
        ticks = ((Number) map.getOrDefault("ticks", -1)).intValue();
        timeUpdate = ((Number) map.getOrDefault("timeUpdate", 0)).intValue();
        radius = ((Number) map.getOrDefault("radius", 0)).doubleValue();
        count = ((Number) map.getOrDefault("count", -1)).intValue();

        List<String> particles = (List<String>) map.getOrDefault("particle", new ArrayList<String>());

        for (String particle : particles) {
            this.particle.add(Particle.valueOf(particle));
        }
    }


    @Override
    public void Start(AirDrop airDrop) {
        this.airDrop = airDrop;
        if (airDrop.getAnyLoc() == null) {
            Message.error(BAirDrop.getConfigMessage().getMessage("effect-error-loc-is-null"));
            Message.error(BAirDrop.getConfigMessage().getMessage("effect-error-loc-is-null2"));
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("effect-error-loc-is-null3"), airDrop.getId()));
            return;
        } else loc = airDrop.getAnyLoc().clone();
        used = true;
        run();
    }

    @Override
    public void End() {
        stop = true;
    }

    @Override
    public boolean isUsed() {
        return used;
    }

    void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Particle pr : particle) {
                    double x = ThreadLocalRandom.current().nextDouble(-radius, radius);
                    double y = ThreadLocalRandom.current().nextDouble(radius);
                    double z = ThreadLocalRandom.current().nextDouble(-radius, radius);
                    loc.getWorld().spawnParticle(pr, loc.clone().add(x, y, z), count);
                }
                if (stop)
                    cancel();
                if (ticks != -1) {
                    if ((ticks - timeUpdate) > 0) {
                        ticks -= timeUpdate;
                    } else {
                        End();
                        cancel();
                    }
                }
            }
        }.runTaskTimerAsynchronously(BAirDrop.getInstance(), timeUpdate, timeUpdate);
    }

    @Override
    public EffectType getType() {
        return EffectType.RANDOM_PARTICLE;
    }

    @Override
    public IEffect clone() {
        return new RandomParticle(map);
    }

}
