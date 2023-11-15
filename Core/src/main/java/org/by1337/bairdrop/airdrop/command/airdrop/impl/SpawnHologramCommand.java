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

public class SpawnHologramCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[SPAWN_HOLOGRAM]";
    }

    @Override // заглушка
    public void execute(Event event, @NotNull String command) throws CommandException {
        Airdrop airdrop = event.getAirdrop();
        Objects.requireNonNull(airdrop, String.format(AIRDROP_IS_NULL.getString(), command));
        Objects.requireNonNull(airdrop.getLocation(), String.format(LOCATION_IS_NULL.getString(), command));

        createCommand().executor(((sender, args) -> {
            String setting = (String) args.getOrThrow("setting", USAGE.getString(), usage());
            String name = (String) args.getOrThrow("name", USAGE.getString(), usage());

            HologramManager.createHologram(airdrop.getLocation(), setting, name, new PlaceholderableDefault(airdrop));

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