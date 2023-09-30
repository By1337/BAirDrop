package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.observer.CustomEvent;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentStrings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class InvokeListenerCommand implements CommandExecutor {

    public String getCommandPrefix() { // old [CALL-<listener>]
        return "[INVOKE]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> {
            String listener = (String) args.getOrThrow("listener", USAGE.getString(), usage());
            airDrop.invokeListener(NamespacedKey.fromString(listener), player, CustomEvent.NONE);
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