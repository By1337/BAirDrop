package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentBoolean;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Activate implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[ACTIVATE]";
    }

    public void execute(Event event, @NotNull String command) throws CommandException {
        Objects.requireNonNull(event.getEvent(), String.format(AIRDROP_IS_NULL.getString(), command));
        //todo меняю интерфейс аирдропа и пока нет метода setActivated
        //  createCommand().executor(((sender, args) -> airDrop.setActivated((boolean) args.getOrThrow("toggle", USAGE.getString(), usage())))).process(null, parseCommand(command));
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

