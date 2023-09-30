package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import lombok.Getter;
import lombok.ToString;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.by1337.api.world.BLocation;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentInteger;
import org.by1337.lib.command.argument.ArgumentSetList;
import org.by1337.lib.command.argument.ArgumentStrings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SummonCommand implements CommandExecutor{
    @Override
    public String getCommandPrefix() {
        return "[SUMMON]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(airDrop, String.format(AIRDROP_IS_NULL.getString(), command));
        Objects.requireNonNull(airDrop.getAnyLoc(), String.format(LOCATION_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> {
            String entityType = (String) args.getOrThrow("entity", USAGE.getString(), usage());
            int x = (int) args.getOrThrow("x", USAGE.getString(), usage());
            int y = (int) args.getOrThrow("y", USAGE.getString(), usage());
            int z = (int) args.getOrThrow("z", USAGE.getString(), usage());
            String nbt = (String) args.getOrDefault("nbt", null);

            Location loc = airDrop.getAnyLoc();
            BLocation location = new BLocation(x, y, z, loc.getWorld().getName());
            org.by1337.lib.command.airdrop.SummonCommand.execute(entityType, location, nbt);

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
                .argument(new ArgumentInteger("x"))
                .argument(new ArgumentInteger("y"))
                .argument(new ArgumentInteger("z"))
                .argument(new ArgumentStrings("nbt"));
    }

    private List<String> getSummonableEntities(){
        return Arrays.stream(EntityType.values()).filter(EntityType::isSpawnable).map(entityType -> entityType.getKey().getKey()).collect(Collectors.toList());
    }
}
