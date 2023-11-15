package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.location.generator.LocationManager;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TryGetLocCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[TRY_GEN_LOC]";
    }

    @Override // заглушка
    public void execute(Event event, @NotNull String command) throws CommandException {
        execute(event.getAirdrop(), event.getPlayer(), command);
    }

    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {

        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));

        createCommand().executor(((sender, args) -> {
            LocationManager locationManager = new LocationManager(airDrop.getGeneratorSetting(), Bukkit.getWorld("world"));
            Location location = locationManager.generate();
            airDrop.setLocation(location);
        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[TRY_GEN_LOC]";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix());
    }
}
