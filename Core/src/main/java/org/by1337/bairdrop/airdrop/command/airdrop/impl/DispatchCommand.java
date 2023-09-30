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

public class DispatchCommand implements CommandExecutor {
    public String getCommandPrefix() {
        return "[CONSOLE]";
    }


    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        createCommand().executor(((sender, args) -> {
            String cmd = (String) args.getOrThrow("command", USAGE.getString(), usage());
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd);
        })).process(null, parseCommand(command));
    }


    @Override
    public String usage() {
        return "[CONSOLE] <command>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentStrings("command"));
    }
}