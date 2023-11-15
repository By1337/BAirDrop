package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.by1337.api.util.NameKey;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentInteger;
import org.by1337.api.command.argument.ArgumentString;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class NearPlayersCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() { //old [NEAR-PLAYERS=<radius>] {CALL-<listener>}
        return "[NEAR_PLAYERS]";
    }

    @Override
    public void execute(Event event, @NotNull String command) throws CommandException {
        Airdrop airdrop = event.getAirdrop();
        Objects.requireNonNull(airdrop, AIRDROP_IS_NULL.getString());
        Objects.requireNonNull(airdrop.getAnyLoc(), LOCATION_IS_NULL.getString());

        createCommand().executor(((sender, args) -> {
            NameKey listener = new NameKey((String) args.getOrThrow("listener", USAGE.getString(), usage()), true);
            int radius = (int) args.getOrThrow("radius", USAGE.getString());

            for (Entity entity : airdrop.getAnyLoc().getWorld().getNearbyEntities(airdrop.getAnyLoc(), radius, radius, radius)) {
                if (entity instanceof Player pl) {
                    airdrop.invokeListener(listener, new Event(airdrop, pl, CustomEvent.NONE));
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