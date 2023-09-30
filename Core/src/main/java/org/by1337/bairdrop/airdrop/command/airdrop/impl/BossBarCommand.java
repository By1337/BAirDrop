package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

@Deprecated
public class BossBarCommand implements CommandExecutor {
    private final Resource ERR1 = new Resource("command.deprecated.bossbar.err1");
    private final Resource FEW_ARGS = new Resource("command.deprecated.bossbar.few-args");
    private final Resource ENUM_ERR = new Resource("command.deprecated.bossbar.enum-err");
    private final Resource NOT_CREATED = new Resource("command.deprecated.bossbar.not-created");
    private final Resource UNKNOWN_BOSS_BAR = new Resource("command.deprecated.bossbar.unknown-boss-bar");
    private final Resource PLAYER_IS_NULL = new Resource("command.deprecated.bossbar.player-is-null");
    private final Resource PLAYER_IS_NULL2 = new Resource("command.deprecated.bossbar.player-is-null2");

    @Override
    public String getCommandPrefix() {
        return "[BOSSBAR]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        command = command.replace("[BOSSBAR]", "");
        int quoteCount = command.replaceAll("[^\"]", "").length();
        if (quoteCount % 2 != 0) {
            Message.error(ERR1.getString(), command);
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
            Message.error(FEW_ARGS.getString());
            return;
        }
        String name = null;
        for (String key : args)
            if (key.contains("name")) {
                name = key.replace("name=", "").replace("\"", "");
                continue;
            }
        if (name == null) {
            Message.error(ENUM_ERR.getString());
            Message.error(NOT_CREATED.getString());
            Message.error(Arrays.toString(args));
            return;

        }
        BossBar bossBar = Message.bossBars.getOrDefault(name, null);
        if (bossBar == null) {
            Message.error(UNKNOWN_BOSS_BAR.getString(), name);
            return;
        }
        bossBarAddParam(bossBar, args, Objects.requireNonNull(player, "player is null! " + command));
    }

    @Override
    public String usage() {
        return "[BOSSBAR]";
    }

    @Override
    public Command createCommand() {
        return null;
    }

    @Override
    public void testCommand(@NotNull String command) throws CommandException {

    }

    public void bossBarAddParam(BossBar bossBar, String[] args, @Nullable Player pl) {
        try {
            for (String key : args) {
                if (key.contains("setTitle")) {
                    bossBar.setTitle(Message.messageBuilder(key.replace("setTitle=", "").replace("\"", "")));
                    continue;
                }
                if (key.contains("BarColor")) {
                    bossBar.setColor(BarColor.valueOf(key.replace("BarColor=", "")));
                    continue;
                }
                if (key.contains("BarStyle")) {
                    bossBar.setStyle(BarStyle.valueOf(key.replace("BarStyle=", "")));
                    continue;
                }
                if (key.contains("setProgress")) {
                    bossBar.setProgress(Double.parseDouble(key.replace("setProgress=", "")));
                    continue;
                }
                if (key.contains("addAll")) {
                    for (Player player : Bukkit.getOnlinePlayers())
                        bossBar.addPlayer(player);
                    continue;
                }
                if (key.contains("addPlayer")) {
                    if (pl != null)
                        bossBar.addPlayer(pl);
                    else
                        Message.error(PLAYER_IS_NULL.getString());
                    continue;
                }
                if (key.contains("removePlayer")) {
                    if (pl != null)
                        bossBar.removePlayer(pl);
                    else
                        Message.error(PLAYER_IS_NULL2.getString());
                    continue;
                }
                if (key.contains("removeAll")) {
                    bossBar.removeAll();
                    continue;
                }
                if (key.contains("setVisible")) {
                    bossBar.setVisible(Boolean.parseBoolean(key.replace("setVisible=", "")));
                    continue;
                }
                if (key.contains("BarFlag")) {
                    bossBar.addFlag(BarFlag.valueOf(key.replace("BarFlag=", "")));
                    continue;
                }
                if (key.contains("removeFlag")) {
                    bossBar.removeFlag(BarFlag.valueOf(key.replace("removeFlag=", "")));
                    continue;
                }
                if (!key.contains("name="))
                    Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-cmd-boss-bar"), key));
            }
        } catch (IllegalArgumentException e) {
            Message.error(e.getLocalizedMessage());
        }
    }
}