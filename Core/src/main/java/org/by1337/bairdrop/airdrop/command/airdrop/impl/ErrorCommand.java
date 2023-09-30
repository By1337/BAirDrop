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

public class ErrorCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[ERROR]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        createCommand().executor(((sender, args) -> {
            String message = (String) args.getOrThrow("message", USAGE.getString(), usage());
            Message.error(message);
        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[ERROR] <message>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentStrings("message"));
    }
}
