package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.*;
import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.util.Message;

import java.util.Objects;

public class circle implements IEffect {
    int ticks = -1;
    int timeUpdate;
    AirDrop airDrop;
    boolean active = true;
    Particle particle;
    double radius;
    int count;
    //int viewDistance;
    double step;
    Vector offsets;
    double numberOfSteps;
    double size;
    Color color;
    FileConfiguration cs;
    Location loc;
    String name;

    public circle(FileConfiguration cs, String name) throws NullPointerException, IllegalArgumentException {
        this.cs = cs;
        ticks = cs.getInt(String.format("effects.%s.ticks", name));
        timeUpdate = cs.getInt(String.format("effects.%s.timeUpdate", name));
        particle = Objects.requireNonNull(Particle.valueOf(cs.getString(String.format("effects.%s.particle", name))));
        this.name = name;

        radius = cs.getDouble(String.format("effects.%s.radius", name));
        count = cs.getInt(String.format("effects.%s.count", name));
        //viewDistance = cs.getInt(String.format("effects.%s.viewDistance", name));
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
        if (airDrop.getAirLoc() == null) {
            if (airDrop.getFutureLocation() == null) {
                Message.error("Локация для аирдропа ещё не сгенерирована");
                Message.error("Эффект не может появится");
                Message.error("аирдроп " + airDrop.getAirId());
                return;
            } else loc = airDrop.getFutureLocation().clone();
        } else loc = airDrop.getAirLoc().clone();
        run();

    }

    @Override
    public void End() {
        active = false;
    }

    @Override
    public void setLifetime(int ticks) {
        this.ticks = ticks;
    }

    @Override
    public boolean isActive() {
        return active;
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
                        cancel();
                    }
                }
            }
        }.runTaskTimerAsynchronously(BAirDrop.instance, timeUpdate, timeUpdate);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public EffectType getType() {
        return EffectType.CIRCLE;
    }

    @Override
    public IEffect clone() {

        return new circle(cs, name);

    }
}
