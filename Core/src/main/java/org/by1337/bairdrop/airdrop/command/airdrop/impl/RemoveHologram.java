package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.api.world.BLocation;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.PlaceholderableDefault;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.hologram.HologramManager;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentSetList;
import org.by1337.api.command.argument.ArgumentString;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class RemoveHologram implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[REMOVE_HOLOGRAM]";
    }

    @Override // заглушка
    public void execute(Event event, @NotNull String command) throws CommandException {
        execute(event.getAirdrop(), event.getPlayer(), command);
    }

    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {

        createCommand().executor(((sender, args) -> {
            String name = (String) args.getOrThrow("name", USAGE.getString(), usage());
            HologramManager.stopAndRemoveHologram(name);

        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[REMOVE_HOLOGRAM] <unique name>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentString("name"));
    }
}