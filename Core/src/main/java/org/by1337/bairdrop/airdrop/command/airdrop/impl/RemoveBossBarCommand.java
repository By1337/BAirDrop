package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.util.OLDMessage;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class RemoveBossBarCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[REMOVE_BOSSBAR]";
    }

    @Override // заглушка
    public void execute(Event event, @NotNull String command) throws CommandException {
        execute(event.getAirdrop(), event.getPlayer(), command);
    }

    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) {
        command = command.replace("[REMOVE_BOSSBAR]", "");
        int quoteCount = command.replaceAll("[^\"]", "").length();
        if (quoteCount % 2 != 0) {
            OLDMessage.error(String.format(BAirDrop.getConfigMessage().getMessage("boss-bar-error-command"), command));
            return;
        } else {
            StringBuilder result = new StringBuilder();
            boolean insideQuotes = false;
            for (char c : command.toCharArray()) {
                if (c == '\"') {
                    insideQuotes = !insideQuotes;
                }
                if (!insideQuotes && c == ' ') {
                    continue;
                }
                result.append(c);
            }
            command = result.toString();
        }
        String[] args = command.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        if (args.length < 1) {
            OLDMessage.error(BAirDrop.getConfigMessage().getMessage("few-arg-for-del-boss-bar"));
            return;
        }
        String name = null;
        for (String key : args)
            if (key.contains("name")) {
                name = key.replace("name=", "").replace("\"", "");
                continue;
            }
        if (name == null) {
            OLDMessage.error(BAirDrop.getConfigMessage().getMessage("fail-del-boss-bar"));
            OLDMessage.error(BAirDrop.getConfigMessage().getMessage("fail-del-boss-bar2"));
            return;
        }
        BossBar bossBar = OLDMessage.bossBars.getOrDefault(name, null);
        if (bossBar == null) {
            OLDMessage.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-boss-bar"), name));
            return;
        }
        OLDMessage.bossBars.remove(name);
    }

    @Override
    public String usage() {
        return "[REMOVE_BOSSBAR] <args>";
    }

    @Override
    public Command createCommand() {
        return null;
    }

    @Override
    public void testCommand(@NotNull String command) throws CommandException {

    }
}