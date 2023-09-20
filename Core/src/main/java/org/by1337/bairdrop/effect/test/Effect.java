package org.by1337.bairdrop.effect.test;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitTask;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.menu.property.EditableProperties;
import org.by1337.bairdrop.menu.property.property.PropertyInteger;
import org.by1337.bairdrop.menu.property.property.PropertyLong;

import java.util.List;

public abstract class Effect extends EditableProperties implements Runnable {

    private final PropertyLong maxTick = (PropertyLong) registerProperty(
            new PropertyLong(
                    30000L,
                    "maxTick",
                    new Resource("effect.property.max-tick.lore"),
                    new Resource("effect.property.max-tick.material"),
                    new Resource("effect.property.max-tick.name"),
                    this
            )
    );


    private final PropertyInteger tickSpeed = (PropertyInteger) registerProperty(
            new PropertyInteger(
                    1,
                    "tickSpeed",
                    new Resource("effect.property.tick-speed.lore"),
                    new Resource("effect.property.tick-speed.material"),
                    new Resource("effect.property.tick-speed.name"),
                    this
            )
    );

    private transient long currentTick = 0;
    private transient BukkitTask task;

    public Effect(long maxTick, int tickSpeed) {
        this.maxTick.setValue(maxTick);
        this.tickSpeed.setValue(tickSpeed);
    }

    @Override
    public void run() {
        if (currentTick % tickSpeed.getValue() == 0) {
            tick();
        }
        currentTick++;
    }

    protected Effect getInstance() {
        return this;
    }

    public void start(Location location) {
        task = Bukkit.getScheduler().runTaskTimer(BAirDrop.getInstance(), this, 1, 1);
        startEffect(location);
    }

    public void stop() {
        if (task == null)
            throw new IllegalStateException("effect not been started");
        task.cancel();
        task = null;
        stopEffect();
    }

    protected abstract void startEffect(Location location);

    protected abstract void stopEffect();

    abstract void tick();
}

