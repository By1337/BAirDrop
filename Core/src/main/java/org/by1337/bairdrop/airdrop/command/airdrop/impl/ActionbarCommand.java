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

import java.util.Objects;

public class ActionbarCommand implements CommandExecutor {
    public String getCommandPrefix() {
        return "[ACTIONBAR]";
    }


    public void execute(Event event, @NotNull String command) throws CommandException {
        Objects.requireNonNull(event.getPlayer(), String.format(PLAYER_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> BAirDrop.MESSAGE.sendActionBar(event.getPlayer(), (String) args.getOrThrow("message", USAGE.getString(), usage())))).process(null, parseCommand(command));
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