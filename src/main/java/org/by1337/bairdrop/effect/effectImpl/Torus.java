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
import org.by1337.bairdrop.serializable.EffectSerializable;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;

import java.util.HashMap;
import java.util.Map;

public class Torus implements IEffect, EffectSerializable {
    private final double step;
    private Location loc;
    private final Color color;
    private final double size;
    private final Particle particle;
    private final Vector offsets;
    private final int count;
    private final int timeUpdate;
    private int ticks;
    private boolean used;
    private boolean stop;
    private AirDrop airDrop;
    private final double innerRadius;
    private final double outerRadius;
    private final Map<String, Object> map;
    private boolean ser;

    public Torus(Map<String, Object> map)  {
        this.map = map;
        ticks = ((Number) map.getOrDefault("ticks", -1)).intValue();
        timeUpdate = ((Number) map.getOrDefault("timeUpdate", 0)).intValue();
        particle = Particle.valueOf((String) map.getOrDefault("particle", "FLAME"));

        innerRadius = ((Number) map.getOrDefault("inner-radius", 0)).doubleValue();
        outerRadius = ((Number) map.getOrDefault("outer-radius", 0)).doubleValue();

        count = ((Number) map.getOrDefault("count", -1)).intValue();
        step = ((Number) map.getOrDefault("step", 0)).doubleValue();
        offsets = new Vector(
                ((Number) map.getOrDefault("offset-x", 0)).doubleValue(),
                ((Number) map.getOrDefault("offset-x", 0)).doubleValue(),
                ((Number) map.getOrDefault("offset-x", 0)).doubleValue()
        );


        size = ((Number) map.getOrDefault("size", 1)).doubleValue();

        color = Color.fromBGR(
                ((Number) map.getOrDefault("color-rgb-b", 255)).intValue(),
                ((Number) map.getOrDefault("color-rgb-g", 255)).intValue(),
                ((Number) map.getOrDefault("color-rgb-r", 255)).intValue()
        );
    }
    public Torus(Map<String, Object> map, boolean ser)  {
        this.ser = ser;
        this.map = map;
        ticks = ((Number) map.getOrDefault("ticks", -1)).intValue();
        timeUpdate = ((Number) map.getOrDefault("timeUpdate", 0)).intValue();
        particle = Particle.valueOf((String) map.getOrDefault("particle", "FLAME"));
        count = ((Number) map.getOrDefault("count", -1)).intValue();
        step = ((Number) map.getOrDefault("step", 0)).doubleValue();
        offsets = (Vector) map.getOrDefault("offsets", new Vector(0, 0, 0));
        size = ((Number) map.getOrDefault("size", 1)).doubleValue();
        color = (Color) map.getOrDefault("color", Color.fromRGB(255, 255, 255));
        loc = (Location) map.getOrDefault("loc", null);
        used = (boolean) map.getOrDefault("used", false);
        stop = (boolean) map.getOrDefault("stop", false);

        innerRadius = ((Number) map.getOrDefault("inner-radius", 0)).doubleValue();
        outerRadius = ((Number) map.getOrDefault("outer-radius", 0)).doubleValue();

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
            @Override
            public void run() {

                double step1 = step * Math.PI / count;

                for (double theta = 0; theta < 2 * Math.PI; theta += step1) {
                    if (loc == null)
                        break;
                    for (double phi = 0; phi < 2 * Math.PI; phi += step1) {
                        double v = outerRadius + innerRadius * Math.cos(theta);
                        double x = v * Math.cos(phi);
                        double y = innerRadius * Math.sin(theta);
                        double z = v * Math.sin(phi);
                        if (particle.name().equals("REDSTONE"))
                            loc.getWorld().spawnParticle(particle, loc.clone().add(offsets).add(x, y, z), 0, new org.bukkit.Particle.DustOptions(color, (float) size));
                        else
                            loc.getWorld().spawnParticle(particle, loc.clone().add(offsets).add(x, y, z), 0);
                    }
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
    public boolean isUsed() {
        return used;
    }

    @Override
    public IEffect clone() {
        if (ser)
            return new Torus(map, true);
        return new Torus(map);
    }

    @Override
    public EffectType getType() {
        return EffectType.TORUS;
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
        map.put("count", count);
        map.put("step", step);
        map.put("offsets", offsets);
        map.put("size", size);
        map.put("color", color);
        map.put("loc", loc);
        map.put("inner-radius", innerRadius);
        map.put("outer-radius", outerRadius);
        return map;
    }
    public static IEffect deserialize(Map<String, Object> map) {

        Torus torus = new Torus(map, true);
        if (!torus.stop && torus.used && torus.loc != null && torus.loc.getWorld() != null){
            torus.run();
        }
        return torus;
    }

}
