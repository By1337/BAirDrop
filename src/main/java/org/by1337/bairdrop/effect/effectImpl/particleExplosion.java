package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;

import java.util.Objects;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;
@Deprecated
public class particleExplosion implements IEffect {
    double radius; //
    double height;
    double step; //
    int viewDistance; //
    Location loc;
    Color color;
    Color color2;
    Color color3;
    Color color4;
    Color color5;
    double size;
    Particle particle;
    Particle particle2;
    Particle particle3;
    Particle particle4;
    Particle particle5;
    Vector offsets;
    int count; //
    int timeUpdate; //
    int ticks; //
    String name;
    boolean active = true;
    FileConfiguration cs;
    AirDrop airDrop;

    public particleExplosion(FileConfiguration cs, String name) throws NullPointerException, IllegalArgumentException{
        this.name = name;
        this.cs = cs;
        ticks = cs.getInt(String.format("effects.%s.ticks", name));
        timeUpdate = cs.getInt(String.format("effects.%s.timeUpdate", name));
        particle = Objects.requireNonNull(Particle.valueOf(cs.getString(String.format("effects.%s.particle", name))));
        this.name = name;
        radius = cs.getDouble(String.format("effects.%s.radius", name));
        height = cs.getDouble(String.format("effects.%s.height", name));
        count = cs.getInt(String.format("effects.%s.count", name));
        viewDistance = cs.getInt(String.format("effects.%s.viewDistance", name));
        step = cs.getDouble(String.format("effects.%s.step", name));
        offsets = new Vector(
                cs.getDouble(String.format("effects.%s.offset-x", name)),
                cs.getDouble(String.format("effects.%s.offset-y", name)),
                cs.getDouble(String.format("effects.%s.offset-z", name)));
        size = cs.getDouble(String.format("effects.%s.size", name));

        particle = Objects.requireNonNull(Particle.valueOf(cs.getString(String.format("effects.%s.particle", name))));
        particle2 = Objects.requireNonNull(Particle.valueOf(cs.getString(String.format("effects.%s.particle2", name))));
        particle3 = Objects.requireNonNull(Particle.valueOf(cs.getString(String.format("effects.%s.particle3", name))));
        particle4 = Objects.requireNonNull(Particle.valueOf(cs.getString(String.format("effects.%s.particle4", name))));
        particle5 = Objects.requireNonNull(Particle.valueOf(cs.getString(String.format("effects.%s.particle5", name))));

        color = Color.fromBGR(
                cs.getInt(String.format("effects.%s.color-rgb-b", name)),
                cs.getInt(String.format("effects.%s.color-rgb-g", name)),
                cs.getInt(String.format("effects.%s.color-rgb-r", name)));
        color2 = Color.fromBGR(
                cs.getInt(String.format("effects.%s.color-rgb-b2", name)),
                cs.getInt(String.format("effects.%s.color-rgb-g2", name)),
                cs.getInt(String.format("effects.%s.color-rgb-r2", name)));
        color3 = Color.fromBGR(
                cs.getInt(String.format("effects.%s.color-rgb-b3", name)),
                cs.getInt(String.format("effects.%s.color-rgb-g3", name)),
                cs.getInt(String.format("effects.%s.color-rgb-r3", name)));
        color4 = Color.fromBGR(
                cs.getInt(String.format("effects.%s.color-rgb-b4", name)),
                cs.getInt(String.format("effects.%s.color-rgb-g4", name)),
                cs.getInt(String.format("effects.%s.color-rgb-r4", name)));
        color5 = Color.fromBGR(
                cs.getInt(String.format("effects.%s.color-rgb-b5", name)),
                cs.getInt(String.format("effects.%s.color-rgb-g5", name)),
                cs.getInt(String.format("effects.%s.color-rgb-r5", name)));

    }
    @Override
    public void Start(AirDrop airDrop) {
        this.airDrop = airDrop;
        if (airDrop.getAirLoc() == null) {
            if (airDrop.getFutureLocation() == null) {
                Message.error(Config.getMessage("effect-error-loc-is-null"));
                Message.error(Config.getMessage("effect-error-loc-is-null2"));
                Message.error(String.format(Config.getMessage("effect-error-loc-is-null3"), airDrop.getAirId()));
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

    void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (double y = 0; y <= height; y += step) {
                    double x = radius * Math.cos(y);
                    double z = radius * Math.sin(y);
                    for (Entity entity : loc.getWorld().getNearbyEntities(loc, viewDistance, viewDistance, viewDistance)) {
                        if (entity instanceof Player p) {
                            if (particle.name().equals("REDSTONE"))
                                p.spawnParticle(particle, loc.clone().add(offsets).add(x, y, z), count, new org.bukkit.Particle.DustOptions(color, (float) size));
                            else
                                p.spawnParticle(particle, loc.clone().add(offsets).add(x, y, z), count);

                            if (particle2.name().equals("REDSTONE"))
                                p.spawnParticle(particle2, loc.clone().add(offsets).add(x, y, z), count, new org.bukkit.Particle.DustOptions(color2, (float) size));
                            else
                                p.spawnParticle(particle2, loc.clone().add(offsets).add(x, y, z), count);

                            if (particle3.name().equals("REDSTONE"))
                                p.spawnParticle(particle3, loc.clone().add(offsets).add(x, y, z), count, new org.bukkit.Particle.DustOptions(color3, (float) size));
                            else
                                p.spawnParticle(particle3, loc.clone().add(offsets).add(x, y, z), count);

                            if (particle4.name().equals("REDSTONE"))
                                p.spawnParticle(particle4, loc.clone().add(offsets).add(x, y, z), count, new org.bukkit.Particle.DustOptions(color4, (float) size));
                            else
                                p.spawnParticle(particle4, loc.clone().add(offsets).add(x, y, z), count);

                            if (particle5.name().equals("REDSTONE"))
                                p.spawnParticle(particle5, loc.clone().add(offsets).add(x, y, z), count, new org.bukkit.Particle.DustOptions(color5, (float) size));
                            else
                                p.spawnParticle(particle5, loc.clone().add(offsets).add(x, y, z), count);

                        }
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
        }.runTaskTimer(BAirDrop.getInstance(), timeUpdate,timeUpdate);
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
        try {
            IEffect clone = (IEffect) super.clone();
           // return clone;
            return new particleExplosion(cs, name);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public EffectType getType() {
        return EffectType.PARTICLE_EXPLOSION;
    }
}
