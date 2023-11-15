package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.util.OLDMessage;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentStrings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ActionbarAllCommand implements CommandExecutor {

    public String getCommandPrefix() {
        return "[ACTIONBAR_ALL]";
    }

    public void execute(@NotNull Event event, @NotNull String command) throws CommandException {
        createCommand().executor(((sender, args) -> BAirDrop.MESSAGE.sendAllActionBar((String) args.getOrThrow("message", USAGE.getString(), usage())))).process(null, parseCommand(command));
    }


    @Override
    public String usage() {
        return "[ACTIONBAR_ALL] <message>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentStrings("message"));
    }
}