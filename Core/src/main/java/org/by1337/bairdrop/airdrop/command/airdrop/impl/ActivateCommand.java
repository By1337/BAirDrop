package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentBoolean;
import org.by1337.lib.command.argument.ArgumentStrings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ActivateCommand implements CommandExecutor {
    // @Override
    public String getCommandPrefix() {
        return "[ACTIVATE]";
    }

    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> airDrop.setActivated((boolean) args.getOrThrow("toggle", USAGE.getString(), usage())))).process(null, parseCommand(command));
    }


    @Override
    public String usage() {
        return "[ACTIVATE] <true/false>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentBoolean("toggle"));
    }
}

