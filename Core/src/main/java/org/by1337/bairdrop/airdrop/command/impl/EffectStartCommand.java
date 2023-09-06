package org.by1337.bairdrop.airdrop.command.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class EffectStartCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[EFFECT_START-";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        Objects.requireNonNull(airDrop, "AirDrop is null!");
        String[] args = command.split("-");
        if (args.length != 3) {
            Message.warning(BAirDrop.getConfigMessage().getMessage("few-arguments"));
            Message.warning("[EFFECT_START-<NAME>-<id>]");
            return;
        }
        try {
            String id = args[2].replace("]", "");
            airDrop.loadEffect(args[1], id);
            airDrop.startEffect(id);
        } catch (IllegalArgumentException e) {
            Message.warning(e.getLocalizedMessage());
        }
    }
}
