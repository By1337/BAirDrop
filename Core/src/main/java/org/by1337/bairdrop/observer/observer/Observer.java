package org.by1337.bairdrop.observer.observer;

import org.bukkit.Keyed;
import org.bukkit.entity.Player;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.observer.CustomEvent;
import org.by1337.api.command.CommandException;

import javax.annotation.Nullable;

public interface Observer extends Keyed{
    void update(@Nullable Player pl, @Nullable Airdrop airDrop, CustomEvent customEvent, boolean ignoreEvent);

    CustomEvent getEvent();

    String[] getCommands();

    String[] getDenyCommands();

    String getDescription();

    void validateCommands() throws CommandException;


}
