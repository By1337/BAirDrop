package org.by1337.bairdrop.effect;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;

import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.effect.effectImpl.*;
import org.by1337.bairdrop.util.OLDMessage;

import java.util.Map;

public class LoadEffects {
    public static void LoadEffect(FileConfiguration file) {
        if (file.getConfigurationSection("effects") == null)
            return;
        for (String key : file.getConfigurationSection("effects").getKeys(false)) {
            try {
                @Deprecated //todo сделал так из-за поддержки старых конфигураций
                String effType = file.getString(String.format("effects.%s.type", key)).replace("-", "_").toLowerCase();

                EffectType effectType = EffectType.getByKey(NamespacedKey.fromString(effType));
                Map<String, Object> map = file.getConfigurationSection("effects." + key).getValues(false);
                if (effectType == EffectType.CIRCLE) {
             //       EffectFactory.EffectList.put(nameKey, new Circle(file, nameKey));
                    EffectFactory.EffectList.put(key,new Circle(map));
                    continue;
                }
                if (effectType == EffectType.HELIX) {
                   // EffectFactory.EffectList.put(nameKey, new Helix(file, nameKey));
                    EffectFactory.EffectList.put(key, new Helix(map));
                    continue;
                }
                if (effectType == EffectType.EXPANDING_CIRCLE) {
                  //  EffectFactory.EffectList.put(nameKey, new ExpandingCircle(file, nameKey));
                    EffectFactory.EffectList.put(key, new ExpandingCircle(map));
                    continue;
                }
                if (effectType == EffectType.RANDOM_PARTICLE) {
                   // EffectFactory.EffectList.put(nameKey, new RandomParticle(file, nameKey));
                    EffectFactory.EffectList.put(key, new RandomParticle(map));
                    continue;
                }
                if (effectType == EffectType.SPAWN_GUARD) {
                  //  EffectFactory.EffectList.put(nameKey, new Guard(file, nameKey));
                    EffectFactory.EffectList.put(key, new Guard(map));
                    continue;
                }
                if (effectType == EffectType.FIREWORK) {
                   // EffectFactory.EffectList.put(nameKey, new FireworkEffect(file, nameKey));
                    EffectFactory.EffectList.put(key, new FireworkEffect(map));
                    continue;
                }
                if (effectType == EffectType.TORUS) {
                   // EffectFactory.EffectList.put(nameKey, new Torus(file, nameKey));
                    EffectFactory.EffectList.put(key, new Torus(map));
                    continue;
                }
                if (effectType == EffectType.WRITHING_HELIX) {
                   // EffectFactory.EffectList.put(nameKey, new WrithingHelix(file, nameKey));
                    EffectFactory.EffectList.put(key, new WrithingHelix(map));
                    continue;
                }
                if (effectType == EffectType.PARTICLE_EXPLOSION) {
                   // EffectFactory.EffectList.put(nameKey, new ParticleExplosion(file, nameKey));
                    EffectFactory.EffectList.put(key, new ParticleExplosion(map));
                    continue;
                }
                throw new IllegalArgumentException();
            } catch (NullPointerException e) {
                OLDMessage.error(String.format(BAirDrop.getConfigMessage().getMessage("effect-error"), key));
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                OLDMessage.error(String.format(BAirDrop.getConfigMessage().getMessage("effect-error-unknown-type"), key));
            }
        }
    }
}
