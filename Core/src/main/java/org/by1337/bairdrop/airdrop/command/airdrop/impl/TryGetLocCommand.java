package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.by1337.api.world.BLocation;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.location.generator.LocationManager;
import org.by1337.bairdrop.menu.property.property.PropertyLocation;
import org.by1337.bairdrop.util.Message;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TryGetLocCommand  implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[TRY_GEN_LOC]";
    }

    @Override
    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {

        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));

        createCommand().executor(((sender, args) -> {
            PropertyLocation loc = (PropertyLocation) airDrop.getProperty("location");

            if (loc.getValue() != null){
                return;
            }

            LocationManager locationManager = new LocationManager(airDrop.getGeneratorSetting(), Bukkit.getWorld("world"));

            Location location = locationManager.generate();

            if (location != null){
                loc.setValue(new BLocation(location));
                Message.error(loc.toString());
            }

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
