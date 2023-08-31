package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.serializable.EffectSerializable;
import org.by1337.bairdrop.util.Message;

import java.util.Map;

public class ParticleExplosion implements IEffect {
    private int ticks = -1;
    private AirDrop airDrop;
    private boolean used;
    private boolean stop = false;
    private final Particle particle;
    private final double radius;
    private final int count;
    private final Vector offsets;
    private Location loc;
    private final double speed;
    private final int timeUpdate;
    private final Map<String, Object> map;
    public ParticleExplosion(Map<String, Object> map) {
        this.map = map;
        ticks = ((Number) map.getOrDefault("ticks", -1)).intValue();
        particle = Particle.valueOf((String) map.getOrDefault("particle", "FLAME"));
        radius = ((Number) map.getOrDefault("radius", 0)).doubleValue();
        count = ((Number) map.getOrDefault("count", 0)).intValue();
        offsets = new Vector(
                ((Number) map.getOrDefault("offset-x", 0)).doubleValue(),
                ((Number) map.getOrDefault("offset-y", 0)).doubleValue(),
                ((Number) map.getOrDefault("offset-z", 0)).doubleValue()
        );
        speed = ((Number) map.getOrDefault("speed", 0)).doubleValue();
        timeUpdate = ((Number) map.getOrDefault("timeUpdate", 0)).intValue();
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
              loc.getWorld().spawnParticle(particle, loc.clone().add(offsets), count, radius, radius, radius, speed);
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
        return EffectType.PARTICLE_EXPLOSION;
    }

    @Override
    public IEffect clone() {
        return new ParticleExplosion(map);
    }

}
