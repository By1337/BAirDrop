package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;

import java.util.Map;

public class WrithingHelix implements IEffect {
    private final double radius;
    private final double height;
    private final double step;
    private Location loc;
    private final Color color;
    private final double size;
    private final Particle particle;
    private final Vector offsets;
    private final int count;
    private final int timeUpdate;
    private int ticks;
    private String name;
    private boolean used;
    private boolean stop;
    private final FileConfiguration cs;
    private AirDrop airDrop;
    private final Map<String, Object> map;
    public WrithingHelix(Map<String, Object> map) {
        this.map = map;
        this.cs = null;
        name = "123";
        ticks = ((Number) map.getOrDefault("ticks", -1)).intValue();
        timeUpdate = ((Number) map.getOrDefault("timeUpdate", 0)).intValue();
        particle = Particle.valueOf((String) map.getOrDefault("particle", "FLAME"));
        radius = ((Number) map.getOrDefault("radius", 0)).doubleValue();
        height = ((Number) map.getOrDefault("height", 0)).doubleValue();
        count = ((Number) map.getOrDefault("count", 0)).intValue();
        step = ((Number) map.getOrDefault("step", 0)).doubleValue();
        offsets = new Vector(
                ((Number) map.getOrDefault("offset-x", 0)).doubleValue(),
                ((Number) map.getOrDefault("offset-y", 0)).doubleValue(),
                ((Number) map.getOrDefault("offset-z", 0)).doubleValue()
        );
        size = ((Number) map.getOrDefault("size", 1)).doubleValue();
        color = Color.fromBGR(
                ((Number) map.getOrDefault("color-rgb-b", 255)).intValue(),
                ((Number) map.getOrDefault("color-rgb-g", 255)).intValue(),
                ((Number) map.getOrDefault("color-rgb-r", 255)).intValue()
        );
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

    void run() {
        new BukkitRunnable() {
            double angle = 0;

            @Override
            public void run() {
                for (double y = 0; y <= height; y += step) {
                    double x = radius * Math.cos(y);
                    double z = radius * Math.sin(y + angle);

                    if (particle.name().equals("REDSTONE"))
                        loc.getWorld().spawnParticle(particle, loc.clone().add(offsets).add(x, y, z), count, new org.bukkit.Particle.DustOptions(color, (float) size));
                    else
                        loc.getWorld().spawnParticle(particle, loc.clone().add(offsets).add(x, y, z), count);

                }
                angle += 0.1;
                if (stop){
                    cancel();
                }
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
    public boolean isUsed() {
        return used;
    }

    @Override
    public IEffect clone() {
        return new WrithingHelix(map);
    }

    @Override
    public EffectType getType() {
        return EffectType.WRITHING_HELIX;
    }

}
