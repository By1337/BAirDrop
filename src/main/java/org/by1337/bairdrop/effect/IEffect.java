package org.by1337.bairdrop.effect;

import org.by1337.bairdrop.AirDrop;


public interface IEffect{
    void Start(AirDrop airDrop);
    void End();
    boolean isActive();
    IEffect clone();
    EffectType getType();
}
