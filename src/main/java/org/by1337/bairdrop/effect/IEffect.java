package org.by1337.bairdrop.effect;

import org.by1337.bairdrop.AirDrop;

public interface IEffect extends Cloneable{
    void Start(AirDrop airDrop);
    void End();
    void setLifetime(int ticks);
    boolean isActive();
    String getName();
    IEffect clone();
    EffectType getType();
}
