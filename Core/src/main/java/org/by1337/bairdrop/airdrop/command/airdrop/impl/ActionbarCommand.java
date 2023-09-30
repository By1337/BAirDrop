package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentStrings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ActionbarCommand implements CommandExecutor {
    public String getCommandPrefix() {
        return "[ACTIONBAR]";
    }


    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(player, String.format(PLAYER_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> Message.sendActionBar(player, (String) args.getOrThrow("message", USAGE.getString(), usage())))).process(null, parseCommand(command));
    }


    @Override
    public String usage() {
        return "[ACTIONBAR] <message>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentStrings("message"));
    }
}