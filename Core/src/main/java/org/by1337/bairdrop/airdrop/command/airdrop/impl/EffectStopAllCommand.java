package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class EffectStopAllCommand implements CommandExecutor {
    public String getCommandPrefix() {
        return "[EFFECT_STOP_ALL]";
    }

    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> airDrop.stopAllEffectsAndClear())).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[EFFECT_STOP_ALL]";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix());
    }
}
