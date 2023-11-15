package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.by1337.api.util.NameKey;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentStrings;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class InvokeListenerCommand implements CommandExecutor {

    public String getCommandPrefix() { // old [CALL-<listener>]
        return "[INVOKE]";
    }

    @Override
    public void execute(Event event, @NotNull String command) throws CommandException {
        Airdrop airdrop = event.getAirdrop();
        Objects.requireNonNull(airdrop, String.format(AIRDROP_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> {
            String listener = (String) args.getOrThrow("listener", USAGE.getString(), usage());
            airdrop.invokeListener(new NameKey(listener, true), event);
        })).process(null, parseCommand(command));
    }




    @Override
    public String usage() {
        return "[INVOKE] <listener>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentStrings("listener"));
    }
}