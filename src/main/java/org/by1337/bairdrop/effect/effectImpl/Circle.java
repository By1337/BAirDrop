package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import org.by1337.bairdrop.*;
import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.serializable.EffectSerializable;
import org.by1337.bairdrop.util.Message;

import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;

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
    private final FileConfiguration cs;
    private Location loc;
    private final String name;
    private final Vector direction;
    private final double speed;


    public Circle(FileConfiguration cs, String name) throws NullPointerException, IllegalArgumentException {
        this.cs = cs;
        ticks = cs.getInt(String.format("effects.%s.ticks", name));
        timeUpdate = cs.getInt(String.format("effects.%s.timeUpdate", name));
        particle = Objects.requireNonNull(Particle.valueOf(cs.getString(String.format("effects.%s.particle", name))));
        this.name = name;

        radius = cs.getDouble(String.format("effects.%s.radius", name));
        count = cs.getInt(String.format("effects.%s.count", name));
        step = cs.getDouble(String.format("effects.%s.step", name));
        offsets = new Vector(
                cs.getDouble(String.format("effects.%s.offset-x", name)),
                cs.getDouble(String.format("effects.%s.offset-y", name)),
                cs.getDouble(String.format("effects.%s.offset-z", name)));

        direction = new Vector(
                cs.getDouble(String.format("effects.%s.direction-x", name)),
                cs.getDouble(String.format("effects.%s.direction-y", name)),
                cs.getDouble(String.format("effects.%s.direction-z", name)));

        numberOfSteps = cs.getDouble(String.format("effects.%s.number-of-steps", name));
        size = cs.getDouble(String.format("effects.%s.size", name));
        speed = cs.getDouble(String.format("effects.%s.speed", name));
        color = Color.fromBGR(
                cs.getInt(String.format("effects.%s.color-rgb-b", name)),
                cs.getInt(String.format("effects.%s.color-rgb-g", name)),
                cs.getInt(String.format("effects.%s.color-rgb-r", name)));
    }

    public Circle(int ticks, int timeUpdate, boolean used, boolean stop, Particle particle, double radius, int count, double step, Vector offsets, double numberOfSteps, double size, Color color, Location loc, String name, Vector direction, double speed) {
        this.ticks = ticks;
        this.timeUpdate = timeUpdate;
        this.used = used;
        this.stop = stop;
        this.particle = particle;
        this.radius = radius;
        this.count = count;
        this.step = step;
        this.offsets = offsets;
        this.numberOfSteps = numberOfSteps;
        this.size = size;
        this.color = color;
        this.loc = loc;
        this.name = name;
        this.direction = direction;
        this.speed = speed;
        cs = null;
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
        if (cs == null){
            Message.error(this.getClass().getName() + " Не возможно клонировать сериализованный объект! Тем не менее он был клонирован. Но это не значит что это не ошибка!");//todo шиза в сообщении получается
            return new Circle(ticks, timeUpdate, used, stop, particle, radius, count, step, offsets, numberOfSteps, size, color, loc, name, direction, speed);
        }
        return new Circle(cs, name);
    }

    @Override
    public String getConfigName() {
        return name;
    }

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName()).append(";");//0
        sb.append(ticks).append(";");//1
        sb.append(timeUpdate).append(";");//2
        sb.append(used).append(";");//3
        sb.append(stop).append(";");//4
        sb.append(particle.name()).append(";");//5
        sb.append(radius).append(";");//6
        sb.append(count).append(";");//7
        sb.append(step).append(";");//8
        sb.append(offsets.toString()).append(";");//9
        sb.append(numberOfSteps).append(";");//10
        sb.append(size).append(";");//11
        sb.append(color == null ? "null" : color.toString()).append(";");//12
        sb.append(loc.toString()).append(";");//13
        sb.append(name).append(";");//14
        sb.append(direction.toString()).append(";");//15
        sb.append(speed);//16
        Message.devDebug(sb.toString());
        return sb.toString();
    }

    public static IEffect deserialize(String param) {
        String[] args = param.split(";");
        int ticks = Integer.parseInt(args[1]);
        int timeUpdate = Integer.parseInt(args[2]);
        boolean used = Boolean.parseBoolean(args[3]);
        boolean stop = Boolean.parseBoolean(args[4]);
        Particle particle = Particle.valueOf(args[5]);
        double radius = Double.parseDouble(args[6]);
        int count = Integer.parseInt(args[7]);
        double step = Double.parseDouble(args[8]);
        Vector offsets = new Vector(
                Double.parseDouble(args[9].split(",")[0]),
                Double.parseDouble(args[9].split(",")[1]),
                Double.parseDouble(args[9].split(",")[2])

        );
        double numberOfSteps = Double.parseDouble(args[10]);
        double size = Double.parseDouble(args[11]);
        Color color = deserializeColor(args[12]);
        Location loc = deserializeLocation(args[13]);
        String name = args[14];
        Vector direction = new Vector(
                Double.parseDouble(args[15].split(",")[0]),
                Double.parseDouble(args[15].split(",")[1]),
                Double.parseDouble(args[15].split(",")[2])

        );
        double speed = Double.parseDouble(args[16]);
        Circle circle = new Circle(ticks, timeUpdate, used, stop, particle, radius, count, step, offsets, numberOfSteps, size, color, loc, name, direction, speed);
        if (circle.isUsed() && loc != null && loc.getWorld() != null){
            circle.run();
            Message.error("run");
        }else {
            Message.error("don't run!");
            Message.error(loc.toString());
        }
        return circle;
    }

    private static Color deserializeColor(String serializedColor) {
        if (serializedColor.equals("null")) return null;
        try {
            String rgbValue = serializedColor.substring(serializedColor.indexOf("0x") + 2, serializedColor.indexOf("]"));
            int red = Integer.parseInt(rgbValue.substring(0, 2), 16);
            int green = Integer.parseInt(rgbValue.substring(2, 4), 16);
            int blue = Integer.parseInt(rgbValue.substring(4), 16);
            return Color.fromRGB(red, green, blue);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Location deserializeLocation(String serializedLocation) {
        try {
            // Разбиение строки на составляющие
            String worldString = extractValue(serializedLocation, "{world=CraftWorld{name=").replace("}", "");
            double x = Double.parseDouble(extractValue(serializedLocation, "x="));
            double y = Double.parseDouble(extractValue(serializedLocation, "y="));
            double z = Double.parseDouble(extractValue(serializedLocation, "z="));
            float pitch = Float.parseFloat(extractValue(serializedLocation, "pitch="));
            float yaw = Float.parseFloat(extractValue(serializedLocation, "yaw="));

            // Создание объекта Location
            World world = (worldString.equals("null")) ? null : Bukkit.getWorld(worldString);
            return new Location(world, x, y, z, yaw, pitch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String extractValue(String serializedLocation, String key) {
        int keyIndex = serializedLocation.indexOf(key);
        if (keyIndex == -1) {
            throw new IllegalArgumentException("Некорректный формат сериализованной строки");
        }
        int valueStartIndex = keyIndex + key.length();
        int valueEndIndex = serializedLocation.indexOf(",", valueStartIndex);
        if (valueEndIndex == -1) {
            valueEndIndex = serializedLocation.indexOf("}", valueStartIndex);
        }
        return serializedLocation.substring(valueStartIndex, valueEndIndex);
    }

}
