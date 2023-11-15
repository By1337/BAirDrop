package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentStrings;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class PlayerPerformCommand implements CommandExecutor{
    @Override
    public String getCommandPrefix() {
        return "[PLAYER]";
    }

    @Override // заглушка
    public void execute(Event event, @NotNull String command) throws CommandException {
        execute(event.getAirdrop(), event.getPlayer(), command);
    }

    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(player, PLAYER_IS_NULL.getString());
        createCommand().executor(((sender, args) -> {
            String cmd = (String) args.getOrThrow("command", USAGE.getString(), usage());
            player.performCommand(cmd);
        })).process(null, parseCommand(command));
    }


    @Override
    public String usage() {
        return "[PLAYER] <command>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentStrings("command"));
    }
}