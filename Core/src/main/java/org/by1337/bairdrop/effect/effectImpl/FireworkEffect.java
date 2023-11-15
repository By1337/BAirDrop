package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.effect.util.RGBHelper;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.serializable.EffectSerializable;
import org.by1337.bairdrop.util.OLDMessage;
import org.by1337.bairdrop.BAirDrop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FireworkEffect implements IEffect, EffectSerializable{
    private int ticks = -1;
    private final int timeUpdate;
    private AirDrop airDrop;
    private boolean used;
    private boolean stop;
    private double startHeight;
    private final double endHeight;
    private final double stepHeight;
    private Location loc;
    private final List<Color> colors;
    private final Vector offsets;
    private final Map<String, Object> map;
    private boolean ser;
    public FireworkEffect(Map<String, Object> map) {
        this.map = map;
        ticks = ((Number) map.getOrDefault("ticks", -1)).intValue();
        timeUpdate = ((Number) map.getOrDefault("timeUpdate", 0)).intValue();
        List<String> colorStrings = (List<String>) map.getOrDefault("colors", new ArrayList<String>());
        List<Color> colors = new ArrayList<>();
        for (String colorString : colorStrings) {
            colors.add(RGBHelper.getColorWithRgb(colorString));
        }
        this.colors = colors;
        offsets = new Vector(
                ((Number) map.getOrDefault("offset-x", 0)).doubleValue(),
                ((Number) map.getOrDefault("offset-y", 0)).doubleValue(),
                ((Number) map.getOrDefault("offset-z", 0)).doubleValue()
        );
        startHeight = ((Number) map.getOrDefault("start-height", 0)).doubleValue();
        endHeight = ((Number) map.getOrDefault("end-height", 0)).doubleValue();
        stepHeight = ((Number) map.getOrDefault("step-height", 0)).doubleValue();
    }
    private FireworkEffect(Map<String, Object> map, boolean ser) {
        this.ser = ser;
        this.map = map;
        ticks = (int) map.getOrDefault("ticks", -1);
        timeUpdate = (int) map.getOrDefault("timeUpdate", 1);
        used = (boolean) map.getOrDefault("used", false);
        stop = (boolean) map.getOrDefault("stop", false);
        startHeight = (double) map.getOrDefault("startHeight", 0);
        endHeight = (double) map.getOrDefault("endHeight", 0);
        stepHeight = (double) map.getOrDefault("stepHeight", 1);
        colors = (List<Color>) map.getOrDefault("colors", new ArrayList<>());
        offsets = (Vector) map.getOrDefault("offsets", new Vector(0, 0, 0));
        loc = (Location) map.getOrDefault("loc", null);

    }



    @Override
    public void Start(AirDrop airDrop) {
        this.airDrop = airDrop;
        if (airDrop.getAnyLoc() == null) {
            OLDMessage.error(BAirDrop.getConfigMessage().getMessage("effect-error-loc-is-null"));
            OLDMessage.error(BAirDrop.getConfigMessage().getMessage("effect-error-loc-is-null2"));
            OLDMessage.error(String.format(BAirDrop.getConfigMessage().getMessage("effect-error-loc-is-null3"), airDrop.getId()));
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
                Location location = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
                location.add(offsets);
                Firework firework = (Firework) location.getWorld().spawnEntity(location.add(0.0D, startHeight, 0.0D), EntityType.FIREWORK);
                FireworkMeta fireworkMeta = firework.getFireworkMeta();
                fireworkMeta.addEffect(org.bukkit.FireworkEffect.builder()
                        .withColor(colors).flicker(true)
                        .with(org.bukkit.FireworkEffect.Type.BALL_LARGE)
                        .build());
                firework.setFireworkMeta(fireworkMeta);
                fireworkMeta.setPower(20);
                firework.detonate();

                startHeight += stepHeight;
                if (startHeight >= endHeight)
                    cancel();
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
        }.runTaskTimer(BAirDrop.getInstance(), timeUpdate, timeUpdate);
    }

    @Override
    public EffectType getType() {
        return EffectType.FIREWORK;
    }

    @Override
    public IEffect clone() {
        if (ser)
            return new FireworkEffect(map, true);
        return new FireworkEffect(map);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("class", this.getClass().getName());
        map.put("ticks", ticks);
        map.put("timeUpdate", timeUpdate);
        map.put("used", used);
        map.put("stop", stop);
        map.put("startHeight", startHeight);
        map.put("endHeight", endHeight);
        map.put("stepHeight", stepHeight);
        map.put("colors", colors);
        map.put("offsets", offsets);
        map.put("loc", loc);
        return map;
    }

    public static IEffect deserialize(Map<String, Object> map) {

        FireworkEffect fireworkEffect = new FireworkEffect(map, true);
        if (!fireworkEffect.stop && fireworkEffect.used && fireworkEffect.loc != null && fireworkEffect.loc.getWorld() != null){
            fireworkEffect.run();
        }
        return null;
    }


}
