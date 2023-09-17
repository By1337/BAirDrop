package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class EffectStopCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[EFFECT_STOP-";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        Objects.requireNonNull(airDrop, "AirDrop is null!");
        String[] args = command.split("-");
        if (args.length != 2) {
            Message.warning(BAirDrop.getConfigMessage().getMessage("few-arguments"));
            Message.warning("[EFFECT_STOP-<id>]");
            return;
        }
        try {
            airDrop.StopEffect(args[1].replace("]", ""));
        } catch (IllegalArgumentException e) {
            Message.warning(e.getLocalizedMessage());
        }
    }
}
