package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import org.by1337.bairdrop.*;
import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.serializable.EffectSerializable;
import org.by1337.bairdrop.util.Message;

import java.util.HashMap;
import java.util.Map;

public class Circle implements IEffect, EffectSerializable {
    private int ticks = -1;
    private final int timeUpdate;
    private AirDrop airDrop;
    private boolean used;
    private boolean stop = false;
    private final Particle particle;
    private final double radius;
    private final int count;
    private final double step;
    private final Vector offsets;
    private final double numberOfSteps;
    private final double size;
    private final Color color;
    private Location loc;
    private final Vector direction;
    private final double speed;
    private final Map<String, Object> map;
    private boolean ser;


    public Circle(Map<String, Object> map)  {
        this.map = map;
        ticks = ((Number) map.getOrDefault("ticks", -1)).intValue();
        timeUpdate = ((Number) map.getOrDefault("timeUpdate", 0)).intValue();
        particle = Particle.valueOf((String) map.getOrDefault("particle", "FLAME"));
        radius = ((Number) map.getOrDefault("radius", 0)).doubleValue();
        count = ((Number) map.getOrDefault("count", -1)).intValue();
        step = ((Number) map.getOrDefault("step", 0)).doubleValue();
        offsets = new Vector(
                ((Number) map.getOrDefault("offset-x", 0)).doubleValue(),
                ((Number) map.getOrDefault("offset-y", 0)).doubleValue(),
                ((Number) map.getOrDefault("offset-z", 0)).doubleValue()
                );
        direction = new Vector(
                ((Number) map.getOrDefault("direction-x", 0)).doubleValue(),
                ((Number) map.getOrDefault("direction-y", 0)).doubleValue(),
                ((Number) map.getOrDefault("direction-z", 0)).doubleValue()
        );
        numberOfSteps = ((Number) map.getOrDefault("number-of-steps", 0.5D)).doubleValue();
        size = ((Number) map.getOrDefault("size", 1)).doubleValue();
        speed = ((Number) map.getOrDefault("speed", 0)).doubleValue();

        color = Color.fromBGR(
                ((Number) map.getOrDefault("color-rgb-b", 255)).intValue(),
                ((Number) map.getOrDefault("color-rgb-g", 255)).intValue(),
                ((Number) map.getOrDefault("color-rgb-r", 255)).intValue()
        );
    }
    private Circle(Map<String, Object> map, boolean ser)  {
        this.ser = ser;
        this.map = map;
        ticks = ((Number) map.getOrDefault("ticks", -1)).intValue();
        timeUpdate = ((Number) map.getOrDefault("timeUpdate", 0)).intValue();
        particle = Particle.valueOf((String) map.getOrDefault("particle", "FLAME"));
        radius = ((Number) map.getOrDefault("radius", 0)).doubleValue();
        count = ((Number) map.getOrDefault("count", -1)).intValue();
        step = ((Number) map.getOrDefault("step", 0)).doubleValue();
        offsets = (Vector) map.getOrDefault("offsets", new Vector(0, 0, 0));
        direction = (Vector) map.getOrDefault("direction", new Vector(0, 0, 0));
        numberOfSteps = ((Number) map.getOrDefault("number-of-steps", 0.5D)).doubleValue();
        size = ((Number) map.getOrDefault("size", 1)).doubleValue();
        speed = ((Number) map.getOrDefault("speed", 0)).doubleValue();
        color = (Color) map.getOrDefault("color", Color.fromRGB(255, 255, 255));
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
                //    Message.logger("spawn = " + loc);
                for (double y = 0; y <= numberOfSteps; y += step) {
                    double x = radius * Math.cos(y);
                    double z = radius * Math.sin(y);

                    if (particle.name().equals("REDSTONE"))
                        loc.getWorld().spawnParticle(particle, loc.clone().add(offsets).add(x, 0, z), count, new org.bukkit.Particle.DustOptions(color, (float) size));
                    else
                        loc.getWorld().spawnParticle(particle, loc.clone().add(offsets).add(x, 0, z), count, direction.getX(), direction.getY(), direction.getZ(), speed);
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
        return EffectType.CIRCLE;
    }

    @Override
    public IEffect clone() {
        if (ser)
            return new Circle(map, true);
        return new Circle(map);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("class", this.getClass().getName());
        map.put("ticks", ticks);
        map.put("timeUpdate", timeUpdate);
        map.put("used", used);
        map.put("stop", stop);
        map.put("particle", particle.name());
        map.put("radius", radius);
        map.put("count", count);
        map.put("step", step);
        map.put("offsets", offsets);
        map.put("number-of-steps", numberOfSteps);
        map.put("size", size);
        map.put("color", color);
        map.put("loc", loc);
        map.put("direction", direction);
        map.put("speed", speed);
        return map;
    }
    public static IEffect deserialize(Map<String, Object> map) {
        Circle circle = new Circle(map, true);
        if (!circle.stop && circle.used && circle.loc != null && circle.loc.getWorld() != null){
            circle.run();
        }
        return circle;
    }
}
