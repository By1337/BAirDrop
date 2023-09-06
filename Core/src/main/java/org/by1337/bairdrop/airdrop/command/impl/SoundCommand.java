package org.by1337.bairdrop.airdrop.command.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SoundCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[SOUND]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        Message.sendSound(Objects.requireNonNull(player, "player is null! " + command), command.replace("[SOUND] ", ""));
    }
}