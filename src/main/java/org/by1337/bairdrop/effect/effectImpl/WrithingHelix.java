package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;

import java.util.Objects;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;

public class WrithingHelix implements IEffect {
    double radius; //
    double height;
    double step; //
    //int viewDistance; //
    Location loc;
    Color color;
    double size;
    Particle particle;
    Vector offsets;
    int count; //
    int timeUpdate; //
    int ticks; //
    String name;
    boolean active = true;
    FileConfiguration cs;
    AirDrop airDrop;

    public WrithingHelix(FileConfiguration cs, String name) throws NullPointerException, IllegalArgumentException {
        this.name = name;
        this.cs = cs;
        ticks = cs.getInt(String.format("effects.%s.ticks", name));
        timeUpdate = cs.getInt(String.format("effects.%s.timeUpdate", name));
        particle = Objects.requireNonNull(Particle.valueOf(cs.getString(String.format("effects.%s.particle", name))));
        this.name = name;
        radius = cs.getDouble(String.format("effects.%s.radius", name));
        height = cs.getDouble(String.format("effects.%s.height", name));
        count = cs.getInt(String.format("effects.%s.count", name));
        //viewDistance = cs.getInt(String.format("effects.%s.viewDistance", name));
        step = cs.getDouble(String.format("effects.%s.step", name));
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
    }//writhing spiral

    void run() {
        new BukkitRunnable() {
            double angle = 0; // initialize angle variable

            @Override
            public void run() {
                for (double y = 0; y <= height; y += step) {
                    double x = radius * Math.cos(y);
                    double z = radius * Math.sin(y + angle); // add angle to y

                    if (particle.name().equals("REDSTONE"))
                        loc.getWorld().spawnParticle(particle, loc.clone().add(offsets).add(x, y, z), count, new org.bukkit.Particle.DustOptions(color, (float) size));
                    else
                        loc.getWorld().spawnParticle(particle, loc.clone().add(offsets).add(x, y, z), count);

                }
                angle += 0.1; // increase angle for the next iteration
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

        return new WrithingHelix(cs, name);

    }

    @Override
    public EffectType getType() {
        return EffectType.WRITHING_HELIX;
    }
}
