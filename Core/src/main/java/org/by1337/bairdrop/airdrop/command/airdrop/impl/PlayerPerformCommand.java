package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentStrings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class PlayerPerformCommand implements CommandExecutor{
    @Override
    public String getCommandPrefix() {
        return "[PLAYER]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
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