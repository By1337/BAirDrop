package org.by1337.bairdrop.effect;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class EffectFactory {
    public static HashMap<String, IEffect> EffectList = new HashMap<>();
    @Nullable
    public static IEffect getEffect(String type){
        if(!EffectFactory.EffectList.containsKey(type))
            return null;
        return EffectFactory.EffectList.get(type).clone();
    }
}
