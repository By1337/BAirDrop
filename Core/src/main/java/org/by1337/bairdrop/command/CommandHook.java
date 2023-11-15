package org.by1337.bairdrop.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.by1337.api.command.CommandException;
import org.by1337.bairdrop.airdrop.registry.CommandFactory;
import org.by1337.bairdrop.util.OLDMessage;
import org.jetbrains.annotations.NotNull;

public class CommandHook implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            CommandFactory.createMain().process(sender, args);
            return true;
        } catch (CommandException e) {
            OLDMessage.sendMsg(sender, e.getLocalizedMessage());
        }
        return false;
    }
}
