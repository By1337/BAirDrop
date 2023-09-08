package org.by1337.bairdrop.airdrop.command.impl;

import lombok.Getter;
import lombok.ToString;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.by1337.api.world.BLocation;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SummonCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[SUMMON]";
    }

    /**
     * syntax [SUMMON] <entity type> x y z nbt
     * @param airDrop
     * @param player
     * @param command
     */
    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        Objects.requireNonNull(airDrop, "AirDrop is null!");
        SummonCommandArgs args = new SummonCommandArgs();
        try {
            args.parse(command);
        }catch (IllegalArgumentException e){
            Message.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        Location loc = airDrop.getAnyLoc();
        Objects.requireNonNull(loc, String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null2"), command));
        BLocation location = new BLocation(args.x, args.y, args.z, loc.getWorld().getName());
        org.by1337.lib.SummonCommand.execute(args.entityType, location, args.nbt);
    }

    @ToString
    @Getter
    public static class SummonCommandArgs {
        private String entityType;
        private double x;
        private double y;
        private double z;
        private String nbt = null;

        public void parse(String s) {
            Pattern pattern = Pattern.compile("\\[SUMMON] (\\S+) (-?\\d+\\.?\\d+) (-?\\d+\\.?\\d+) (-?\\d+\\.?\\d+) ?(.*)");
            Matcher matcher = pattern.matcher(s);

            if (matcher.matches()) {
                entityType = matcher.group(1);
                x = Double.parseDouble(matcher.group(2));
                y = Double.parseDouble(matcher.group(3));
                z = Double.parseDouble(matcher.group(4));
                nbt = matcher.group(5);
                if (nbt.length() == 0){
                    nbt = null;
                }
            } else {
                throw new IllegalArgumentException("Error in command syntax!");
            }
        }

    }

}
