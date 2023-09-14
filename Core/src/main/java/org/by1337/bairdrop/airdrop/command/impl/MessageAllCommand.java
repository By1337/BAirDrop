package org.by1337.bairdrop.airdrop.command.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MessageAllCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[MESSAGE_ALL]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        Message.sendAllMsg(command.replace("[MESSAGE_ALL] ", ""));
    }
}