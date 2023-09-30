package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.observer.CustomEvent;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentInteger;
import org.by1337.lib.command.argument.ArgumentString;
import org.by1337.lib.command.argument.ArgumentStrings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class NearPlayersCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() { //old [NEAR-PLAYERS=<radius>] {CALL-<listener>}
        return "[NEAR_PLAYERS]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(airDrop, AIRDROP_IS_NULL.getString());
        Objects.requireNonNull(airDrop.getAnyLoc(), LOCATION_IS_NULL.getString());

        createCommand().executor(((sender, args) -> {
            NamespacedKey listener = NamespacedKey.fromString((String) args.getOrThrow("listener", USAGE.getString(), usage()));
            int radius = (int) args.getOrThrow("radius", USAGE.getString());

            for (Entity entity : airDrop.getAnyLoc().getWorld().getNearbyEntities(airDrop.getAnyLoc(), radius, radius, radius)){
                if (entity instanceof Player pl) {
                    airDrop.invokeListener(listener, pl, CustomEvent.NONE);
                }
            }

        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[NEAR_PLAYERS] <radius> <listener>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentInteger("radius"))
                .argument(new ArgumentString("listener"));
    }
}