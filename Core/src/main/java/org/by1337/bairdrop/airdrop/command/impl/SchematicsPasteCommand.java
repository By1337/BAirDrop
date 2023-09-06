package org.by1337.bairdrop.airdrop.command.impl;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.AsyncCatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchematicsPasteCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[SCHEMATICS_PASTE-";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        AsyncCatcher.catchOp("Asynchronous schematics paste!");
        Objects.requireNonNull(airDrop, "AirDrop is null!");
        Location location = airDrop.getAnyLoc();
        Objects.requireNonNull(location, String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null2"), command));

        Pattern pattern = Pattern.compile("\\[SET_BLOCK_FACE_(.*?)\\]");
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) {
            String output = matcher.group(1);
            try {
                BlockFace blockFace = BlockFace.valueOf(output);
                Block block = location.getBlock();
                if (block.getBlockData() instanceof Directional directional) {
                    BlockState state = block.getState();
                    directional.setFacing(blockFace);
                    state.setBlockData(directional);
                    state.update(true);
                } else {
                    Message.error(BAirDrop.getConfigMessage().getMessage("rotate-error"));
                }
            } catch (IllegalArgumentException e) {
                Message.error(e.getLocalizedMessage());
            }
        }
    }
}