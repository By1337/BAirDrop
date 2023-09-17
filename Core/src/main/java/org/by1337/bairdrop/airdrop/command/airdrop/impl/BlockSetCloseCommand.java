package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Lidded;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.AsyncCatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BlockSetCloseCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[BLOCK_SET_CLOSE]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        AsyncCatcher.catchOp("Asynchronous block set close!");
        Objects.requireNonNull(airDrop, "AirDrop is null!");
        Location location = airDrop.getAnyLoc();
        Objects.requireNonNull(location, String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null2"), command));
        Block block = location.getBlock();
        BlockState blockState = block.getState();
        if (blockState instanceof Lidded lidded) {
            lidded.close();
        } else {
            Message.error(BAirDrop.getConfigMessage().getMessage("lidded-error"));
        }
    }
}