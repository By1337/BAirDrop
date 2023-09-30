package org.by1337.bairdrop.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.by1337.bairdrop.airdrop.registry.CommandFactory;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CompleterHook implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        try {
            return CommandFactory.createMain().getTabCompleter(sender, args);
        } catch (Exception e) {
            Message.sendMsg(sender, e.getLocalizedMessage());
        }
        return null;
    }
}
