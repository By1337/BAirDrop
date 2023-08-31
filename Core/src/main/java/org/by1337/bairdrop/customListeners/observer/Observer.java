package org.by1337.bairdrop.customListeners.observer;

import org.bukkit.Keyed;
import org.bukkit.entity.Player;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.customListeners.CustomEvent;

import javax.annotation.Nullable;

public interface Observer extends Keyed {
    void update(@Nullable Player pl, @Nullable AirDrop airDrop, CustomEvent customEvent, boolean ignoreEvent);

    CustomEvent getEvent();

    String[] getCommands();

    String[] getDenyCommands();

    String getDescription();

}
