package org.by1337.bairdrop.effect;

import org.by1337.bairdrop.AirDrop;


public interface IEffect {
    /**
     * Запускает эффект
     *
     * @param airDrop аирдрпо который запускает эффект
     */
    void Start(AirDrop airDrop);

    /**
     * Останавливает эффект
     */
    void End();

    /**
     * @return был ли этот эффект использован
     */
    boolean isUsed();

    /**
     * @return клон этого эффекта
     */
    IEffect clone();

    EffectType getType();

}
