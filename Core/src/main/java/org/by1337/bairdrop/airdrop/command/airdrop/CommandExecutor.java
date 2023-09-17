package org.by1337.bairdrop.airdrop.command.airdrop;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommandExecutor {
    String getCommandPrefix();
    void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command);
}
