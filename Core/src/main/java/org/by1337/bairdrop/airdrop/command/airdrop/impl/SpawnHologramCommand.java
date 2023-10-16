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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SpawnHologramCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[SPAWN_HOLOGRAM]";
    }

    @Override
    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {

        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        Objects.requireNonNull(airDrop.getProperty("location").getValue(), String.format(LOCATION_IS_NULL.getString(), command));

        createCommand().executor(((sender, args) -> {
            String setting = (String) args.getOrThrow("setting", USAGE.getString(), usage());
            String name = (String) args.getOrThrow("name", USAGE.getString(), usage());

            BLocation location = (BLocation) airDrop.getProperty("location").getValue();

            HologramManager.createHologram(location.getLocation(), setting, name, new PlaceholderableDefault(airDrop));

        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[SPAWN_HOLOGRAM] <holo_setting> <unique name>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentSetList("setting", HologramManager.settingKeySet().stream().toList()))
                .argument(new ArgumentString("name"));
    }
}