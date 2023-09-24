package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ActivateCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[ACTIVATE]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        Objects.requireNonNull(airDrop, "AirDrop is null!").setActivated(Boolean.parseBoolean(command.replace("[ACTIVATE] ", "")));
    }
}