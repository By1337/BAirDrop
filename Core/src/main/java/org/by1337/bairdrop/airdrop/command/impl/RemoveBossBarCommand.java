package org.by1337.bairdrop.airdrop.command.impl;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class RemoveBossBarCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[REMOVE_BOSSBAR]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        command = command.replace("[REMOVE_BOSSBAR]", "");
        int quoteCount = command.replaceAll("[^\"]", "").length();
        if (quoteCount % 2 != 0) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("boss-bar-error-command"), command));
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
            Message.error(BAirDrop.getConfigMessage().getMessage("few-arg-for-del-boss-bar"));
            return;
        }
        String name = null;
        for (String key : args)
            if (key.contains("name")) {
                name = key.replace("name=", "").replace("\"", "");
                continue;
            }
        if (name == null) {
            Message.error(BAirDrop.getConfigMessage().getMessage("fail-del-boss-bar"));
            Message.error(BAirDrop.getConfigMessage().getMessage("fail-del-boss-bar2"));
            return;
        }
        BossBar bossBar = Message.bossBars.getOrDefault(name, null);
        if (bossBar == null) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-boss-bar"), name));
            return;
        }
        Message.bossBars.remove(name);
    }
}