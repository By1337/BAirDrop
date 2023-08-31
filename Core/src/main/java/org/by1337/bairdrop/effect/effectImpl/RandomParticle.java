package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.scheduler.BukkitRunnable;

import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.serializable.EffectSerializable;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RandomParticle implements IEffect, EffectSerializable {
    private int ticks = -1;
    private final int timeUpdate;
    private AirDrop airDrop;
    private boolean used;
    private boolean stop;
    private final List<Particle> particle;
    private final double radius;
    private final int count;
    private Location loc;
    private final Map<String, Object> map;
    private boolean ser;

    public RandomParticle(Map<String, Object> map) {
        this.map = map;
        ticks = ((Number) map.getOrDefault("ticks", -1)).intValue();
        timeUpdate = ((Number) map.getOrDefault("timeUpdate", 0)).intValue();
        radius = ((Number) map.getOrDefault("radius", 0)).doubleValue();
        count = ((Number) map.getOrDefault("count", -1)).intValue();
        List<String> particles = (List<String>) map.getOrDefault("particle", new ArrayList<String>());
        particle = new ArrayList<>();
        for (String particle : particles) {
            this.particle.add(Particle.valueOf(particle));
        }
    }

    private RandomParticle(Map<String, Object> map, boolean ser) {
        this.ser = ser;
        this.map = map;
        ticks = ((Number) map.getOrDefault("ticks", -1)).intValue();
        timeUpdate = ((Number) map.getOrDefault("timeUpdate", 0)).intValue();
        radius = ((Number) map.getOrDefault("radius", 0)).doubleValue();
        count = ((Number) map.getOrDefault("count", -1)).intValue();
        List<String> particles = (List<String>) map.getOrDefault("particle", new ArrayList<String>());

        particle = new ArrayList<>();
        for (String particle : particles) {
            this.particle.add(Particle.valueOf(particle));
        }

        loc = (Location) map.getOrDefault("loc", null);
        used = (boolean) map.getOrDefault("used", false);
        stop = (boolean) map.getOrDefault("stop", false);

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
        if (ser)
            return new RandomParticle(map, true);
        return new RandomParticle(map);
    }
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("class", this.getClass().getName());
        map.put("ticks", ticks);
        map.put("timeUpdate", timeUpdate);
        map.put("used", used);
        map.put("stop", stop);
        map.put("radius", radius);
        map.put("count", count);
        List<String> part = new ArrayList<>();
        particle.forEach(p -> part.add(p.name()));
        map.put("particle", part);
        map.put("loc", loc);
        return map;
    }
    public static IEffect deserialize(Map<String, Object> map) {
        RandomParticle randomParticle = new RandomParticle(map, true);
        if (!randomParticle.stop && randomParticle.used && randomParticle.loc != null && randomParticle.loc.getWorld() != null){
            randomParticle.run();
        }
        return randomParticle;
    }
}
