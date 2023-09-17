package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TitleCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[TITLE]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        String subTitle = "";
        String Title;
        if (command.contains("[SUB_TITLE]")) {
            command = command.replace("[SUB_TITLE]", "%rbts%");
            subTitle = command.split("%rbts%")[1];
            Title = command.split("%rbts%")[0];
            Title = Title.replace("[TITLE] ", "");
        } else {
            Title = command.replace("[TITLE] ", "");
        }
        Message.sendTitle(Objects.requireNonNull(player, "player is null! " + command), Title, subTitle);
    }
}