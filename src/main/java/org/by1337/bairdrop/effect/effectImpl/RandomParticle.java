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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;
public class RandomParticle implements IEffect {
    int ticks = -1;
    int timeUpdate;
    AirDrop airDrop;
    boolean active = true;
    List<Particle> particle = new ArrayList<>();
    double radius;
    int count;
    //int viewDistance;
    FileConfiguration cs;
    Location loc;
    String name;

    public RandomParticle(FileConfiguration cs, String name) throws NullPointerException, IllegalArgumentException {
        this.cs = cs;
        ticks = cs.getInt(String.format("effects.%s.ticks", name));
        timeUpdate = cs.getInt(String.format("effects.%s.timeUpdate", name));
        for(String pr : cs.getStringList(String.format("effects.%s.particle", name))){
            particle.add(Particle.valueOf(pr));
        }
        this.name = name;

        radius = cs.getDouble(String.format("effects.%s.radius", name));
        count = cs.getInt(String.format("effects.%s.count", name));
        //viewDistance = cs.getInt(String.format("effects.%s.viewDistance", name));

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

    @Override
    public boolean isActive() {
        return active;
    }

    void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Particle pr : particle){
                    double x = ThreadLocalRandom.current().nextDouble(-radius, radius);
                    double y = ThreadLocalRandom.current().nextDouble(radius);
                    double z = ThreadLocalRandom.current().nextDouble(-radius, radius);

                    loc.getWorld().spawnParticle(pr, loc.clone().add(x, y, z), count);

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
    public String getName() {
        return name;
    }

    @Override
    public EffectType getType() {
        return EffectType.RANDOM_PARTICLE;
    }

    @Override
    public IEffect clone() {
        return new RandomParticle(cs, name);

    }
}
