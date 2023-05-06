package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;

import java.util.Objects;

public class Torus implements IEffect {
    double step;
    Location loc;
    Color color;
    double size;
    Particle particle;
    Vector offsets;
    int count;
    int timeUpdate;
    int ticks;
    String name;
    boolean active = true;
    FileConfiguration cs;
    AirDrop airDrop;
    double innerRadius;
    double outerRadius;

    public Torus(FileConfiguration cs, String name) throws NullPointerException, IllegalArgumentException {
        this.name = name;
        this.cs = cs;
        ticks = cs.getInt(String.format("effects.%s.ticks", name));
        timeUpdate = cs.getInt(String.format("effects.%s.timeUpdate", name));
        particle = Objects.requireNonNull(Particle.valueOf(cs.getString(String.format("effects.%s.particle", name))));
        this.name = name;
        count = cs.getInt(String.format("effects.%s.count", name));
        step = cs.getDouble(String.format("effects.%s.step", name));
        innerRadius = cs.getDouble(String.format("effects.%s.inner-radius", name));
        outerRadius = cs.getDouble(String.format("effects.%s.outer-radius", name));
        offsets = new Vector(
                cs.getDouble(String.format("effects.%s.offset-x", name)),
                cs.getDouble(String.format("effects.%s.offset-y", name)),
                cs.getDouble(String.format("effects.%s.offset-z", name)));
        size = cs.getDouble(String.format("effects.%s.size", name));
        color = Color.fromBGR(
                cs.getInt(String.format("effects.%s.color-rgb-b", name)),
                cs.getInt(String.format("effects.%s.color-rgb-g", name)),
                cs.getInt(String.format("effects.%s.color-rgb-r", name)));

    }

    @Override
    public void Start(AirDrop airDrop) {
        this.airDrop = airDrop;
        if (airDrop.getAnyLoc() == null) {
            Message.error(Config.getMessage("effect-error-loc-is-null"));
            Message.error(Config.getMessage("effect-error-loc-is-null2"));
            Message.error(String.format(Config.getMessage("effect-error-loc-is-null3"), airDrop.getAirId()));
            return;
        } else loc = airDrop.getAnyLoc().clone();
        run();
    }

    @Override
    public void End() {
        active = false;
    }

    void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Location center = airDrop.getAirLoc();
                double step1 = step * Math.PI / count;

                for (double theta = 0; theta < 2 * Math.PI; theta += step1) {
                    if(center == null)
                        break;
                    for (double phi = 0; phi < 2 * Math.PI; phi += step1) {
                        double v = outerRadius + innerRadius * Math.cos(theta);
                        double x = v * Math.cos(phi);
                        double y = innerRadius * Math.sin(theta);
                        double z = v * Math.sin(phi);
                        if (particle.name().equals("REDSTONE"))
                            center.getWorld().spawnParticle(particle, loc.clone().add(offsets).add(x, y, z), 0, new org.bukkit.Particle.DustOptions(color, (float) size));
                        else
                            center.getWorld().spawnParticle(particle, loc.clone().add(offsets).add(x, y, z), 0);
                    }
                }
                if (!isActive())
                    cancel();
                if (ticks != -1) {
                    if ((ticks - timeUpdate) > 0) {
                        ticks -= timeUpdate;
                    } else {
                        cancel();
                    }
                }
            }
        }.runTaskTimerAsynchronously(BAirDrop.getInstance(), timeUpdate, timeUpdate);
    }
    @Override
    public void setLifetime(int ticks) {
        this.ticks = ticks;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IEffect clone() {
        return new Torus(cs, name);
    }

    @Override
    public EffectType getType() {
        return EffectType.TORUS;
    }
}
