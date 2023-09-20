package org.by1337.bairdrop.effect.test;

import lombok.ToString;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import org.bukkit.util.Vector;

import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.menu.property.property.*;

import java.util.List;

@ToString
public class EffectCircle extends Effect {

    private final PropertyParticle particle = (PropertyParticle) registerProperty(
            new PropertyParticle(
                    Particle.REDSTONE,
                    "particle",
                    new Resource("effect.property.particle.lore"),
                    new Resource("effect.property.particle.material"),
                    new Resource("effect.property.particle.name"),
                    this
            )
    );
    private final PropertyDouble radius = (PropertyDouble) registerProperty(
            new PropertyDouble(
                    2D,
                    "radius",
                    new Resource("effect.property.radius.lore"),
                    new Resource("effect.property.radius.material"),
                    new Resource("effect.property.radius.name"),
                    this
            )
    );

    private final PropertyInteger count = (PropertyInteger) registerProperty(
            new PropertyInteger(
                    0,
                    "count",
                    new Resource("effect.property.count.lore"),
                    new Resource("effect.property.count.material"),
                    new Resource("effect.property.count.name"),
                    this
            )
    );

    private final PropertyDouble step = (PropertyDouble) registerProperty(
            new PropertyDouble(
                    0.05D,
                    "step",
                    new Resource("effect.property.step.lore"),
                    new Resource("effect.property.step.material"),
                    new Resource("effect.property.step.name"),
                    this
            )
    );

    private final PropertyVector offsets = (PropertyVector) registerProperty(
            new PropertyVector(
                    new Vector(0, 0, 0),
                    "offsets",
                    new Resource("effect.property.offsets.lore"),
                    new Resource("effect.property.offsets.material"),
                    new Resource("effect.property.offsets.name"),
                    this
            )
    );

    private final PropertyDouble size = (PropertyDouble) registerProperty(
            new PropertyDouble(
                    1D,
                    "size",
                    new Resource("effect.property.size.lore"),
                    new Resource("effect.property.size.material"),
                    new Resource("effect.property.size.name"),
                    this
            )
    );

    private final PropertyColor color = (PropertyColor) registerProperty(
            new PropertyColor(
                    Color.AQUA,
                    "color",
                    new Resource("effect.property.color.lore"),
                    new Resource("effect.property.color.material"),
                    new Resource("effect.property.color.name"),
                    this
            )
    );

    private final PropertyVector direction = (PropertyVector) registerProperty(
            new PropertyVector(
                    new Vector(0, 0, 0),
                    "direction",
                    new Resource("effect.property.direction.lore"),
                    new Resource("effect.property.direction.material"),
                    new Resource("effect.property.direction.name"),
                    this
            )
    );

    private final PropertyDouble speed = (PropertyDouble) registerProperty(
            new PropertyDouble(
                    0D,
                    "speed",
                    new Resource("effect.property.speed.lore"),
                    new Resource("effect.property.speed.material"),
                    new Resource("effect.property.speed.name"),
                    this
            )
    );


    private transient Location loc;



    public EffectCircle(long maxTick, int tickSpeed, Particle particle, double radius, int count, double step, Vector offsets, double size, Color color, Vector direction, double speed) {
        super(maxTick, tickSpeed);
    }

    public EffectCircle(long maxTick, int tickSpeed) {
        super(maxTick, tickSpeed);
    }

    @Override
    protected void startEffect(Location location) {
        loc = location;
    }

    @Override
    protected void stopEffect() {

    }

    @Override
    void tick() {
        for (double y = 0; y <= Math.PI * 2; y += step.getValue()) {
            double x = radius.getValue() * Math.cos(y);
            double z = radius.getValue() * Math.sin(y);

            if (particle.getValue() == Particle.REDSTONE)
                loc.getWorld().spawnParticle(particle.getValue(),
                        loc.clone().add(offsets.getValue()).add(x, 0, z),
                        count.getValue(),
                        direction.getValue().getX(),
                        direction.getValue().getY(),
                        direction.getValue().getZ(),
                        speed.getValue(),
                        new org.bukkit.Particle.DustOptions(color.getValue(), size.getValue().floatValue())
                        );
            else
                loc.getWorld().spawnParticle(particle.getValue(), loc.clone().add(offsets.getValue()).add(x, 0, z), count.getValue(), direction.getValue().getX(), direction.getValue().getY(), direction.getValue().getZ());
        }
    }


}
