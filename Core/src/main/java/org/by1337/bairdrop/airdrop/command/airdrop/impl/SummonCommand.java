package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.by1337.api.BLib;
import org.by1337.api.world.BLocation;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentDouble;
import org.by1337.api.command.argument.ArgumentSetList;
import org.by1337.api.command.argument.ArgumentStrings;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SummonCommand implements CommandExecutor{
    @Override
    public String getCommandPrefix() {
        return "[SUMMON]";
    }

    @Override // заглушка
    public void execute(Event event, @NotNull String command) throws CommandException {
        execute(event.getAirdrop(), event.getPlayer(), command);
    }

    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        Objects.requireNonNull(airDrop.getAnyLoc(), String.format(LOCATION_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> {
            String entityType = (String) args.getOrThrow("entity", USAGE.getString(), usage());
            double x = (double) args.getOrThrow("x", USAGE.getString(), usage());
            double y = (double) args.getOrThrow("y", USAGE.getString(), usage());
            double z = (double) args.getOrThrow("z", USAGE.getString(), usage());
            String nbt = (String) args.getOrDefault("nbt", null);

            Location loc = airDrop.getAnyLoc();
            BLocation location = new BLocation(x, y, z, loc.getWorld().getName());
            BLib.getCommandUtil().summon(entityType, location, nbt);

        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[SUMMON] <entity type> <x> <y> <z> <?nbt>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentSetList("entity", getSummonableEntities()))
                .argument(new ArgumentDouble("x"))
                .argument(new ArgumentDouble("y"))
                .argument(new ArgumentDouble("z"))
                .argument(new ArgumentStrings("nbt"));
    }

    private List<String> getSummonableEntities(){
        return Arrays.stream(EntityType.values()).filter(EntityType::isSpawnable).map(entityType -> entityType.getKey().getKey()).collect(Collectors.toList());
    }
}
