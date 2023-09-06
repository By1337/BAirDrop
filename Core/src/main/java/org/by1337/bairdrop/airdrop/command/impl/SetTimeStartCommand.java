package org.by1337.bairdrop.airdrop.command.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.AsyncCatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SetTimeStartCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[SET_TIME_START-";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        Objects.requireNonNull(airDrop, "AirDrop is null!");
        String str = command.replace("[SET_TIME_START-", "").replace("]", "");
        try {
            airDrop.setTimeToStart(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            Message.error(e.getLocalizedMessage());
        }
    }
}