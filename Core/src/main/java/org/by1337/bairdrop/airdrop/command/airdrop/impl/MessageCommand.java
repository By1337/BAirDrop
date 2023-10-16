package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentStrings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class MessageCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[MESSAGE]";
    }

    @Override
    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(player, PLAYER_IS_NULL.getString());
        createCommand().executor(((sender, args) -> {
            String message = (String) args.getOrThrow("message", USAGE.getString(), usage());
            Message.sendMsg(player, message);
        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[MESSAGE] <message>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentStrings("message"));
    }
}