package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class EffectStopCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() { //old [EFFECT_STOP-<id>]
        return "[EFFECT_STOP]";
    }


    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> {
            String id = (String) args.getOrThrow("id", USAGE.getString(), usage());
            airDrop.stopEffect(id);
        })).process(null, parseCommand(command));
    }


    @Override
    public String usage() {
        return "[EFFECT_STOP] <id>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentString("id"));
    }
}
