package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.util.Message;

import java.util.Objects;

public class ExpandingCircle implements IEffect {
    private int ticks = -1;
    private final int timeUpdate;
    private  AirDrop airDrop;
    private boolean active = true;
    private final Particle particle;
    private double radius;
    private final double endRadius;
    private final double stepRadius;
    private final int count;
    private final double step;
    private final Vector offsets;
    private final double numberOfSteps;
    private final double size;
    private final Color color;
    private final FileConfiguration cs;
    private Location loc;
    private final String name;

    public ExpandingCircle(FileConfiguration cs, String name) throws NullPointerException, IllegalArgumentException {
        this.cs = cs;
        ticks = cs.getInt(String.format("effects.%s.ticks", name));
        timeUpdate = cs.getInt(String.format("effects.%s.timeUpdate", name));
        particle = Objects.requireNonNull(Particle.valueOf(cs.getString(String.format("effects.%s.particle", name))));
        this.name = name;

        radius = cs.getDouble(String.format("effects.%s.radius", name));
        endRadius = cs.getDouble(String.format("effects.%s.end-radius", name));
        stepRadius = cs.getDouble(String.format("effects.%s.step-radius", name));

        count = cs.getInt(String.format("effects.%s.count", name));
        step = cs.getDouble(String.format("effects.%s.step", name));
        offsets = new Vector(
                cs.getDouble(String.format("effects.%s.offset-x", name)),
                cs.getDouble(String.format("effects.%s.offset-y", name)),
                cs.getDouble(String.format("effects.%s.offset-z", name)));

        numberOfSteps = cs.getDouble(String.format("effects.%s.number-of-steps", name));
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
            Message.error(BAirDrop.getConfigMessage().getMessage("effect-error-loc-is-null"));
            Message.error(BAirDrop.getConfigMessage().getMessage("effect-error-loc-is-null2"));
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("effect-error-loc-is-null3"), airDrop.getId()));
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
                for (double y = 0; y <= numberOfSteps; y += step) {
                    double x = radius * Math.cos(y);
                    double z = radius * Math.sin(y);
                    if (particle.name().equals("REDSTONE"))
                        loc.getWorld().spawnParticle(particle, loc.clone().add(offsets).add(x, 0, z), count, new org.bukkit.Particle.DustOptions(color, (float) size));
                    else
                        loc.getWorld().spawnParticle(particle, loc.clone().add(offsets).add(x, 0, z), count);
                }
                if (!isActive())
                    cancel();
                if (ticks != -1) {
                    if ((ticks - timeUpdate) > 0) {
                        ticks -= timeUpdate;
                    } else {
                        End();
                        cancel();
                    }
                }
                radius += stepRadius;
                if (radius >= endRadius){
                    End();
                    cancel();
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
        return new ExpandingCircle(cs, name);
    }

    @Override
    public EffectType getType() {
        return EffectType.EXPANDING_CIRCLE;
    }
}
