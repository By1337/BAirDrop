package org.by1337.bairdrop.effect;

import org.by1337.bairdrop.obfuscate.DontObfuscate;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
@DontObfuscate
public class EffectFactory {
    public static HashMap<String, IEffect> EffectList = new HashMap<>();
    @Nullable
    public static IEffect getEffect(String type){
        if(!EffectFactory.EffectList.containsKey(type))
            return null;
        return EffectFactory.EffectList.get(type).clone();
    }
}
