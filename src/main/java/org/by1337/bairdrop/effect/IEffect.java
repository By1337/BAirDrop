package org.by1337.bairdrop.effect;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.CAirDrop;
import org.by1337.bairdrop.obfuscate.DontObfuscate;

@DontObfuscate
public interface IEffect{
    void Start(AirDrop airDrop);
    void End();
    void setLifetime(int ticks);
    boolean isActive();
    String getName();
    IEffect clone();
    EffectType getType();
}
