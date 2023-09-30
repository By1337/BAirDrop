package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentInteger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SetTimeEndCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() { // old [SET_TIME_END-<time>]
        return "[SET_TIME_END]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> {
            int time = (int) args.getOrThrow("time", USAGE.getString(), usage());
            airDrop.setTimeStop(time);
        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[SET_TIME_END] <time>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentInteger("time"));
    }

}