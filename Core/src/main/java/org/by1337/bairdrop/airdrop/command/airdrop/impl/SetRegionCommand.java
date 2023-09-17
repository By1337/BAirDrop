package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.worldGuardHook.RegionManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SetRegionCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[SET_REGION]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        Objects.requireNonNull(airDrop, "AirDrop is null!");
        Objects.requireNonNull(airDrop.getAnyLoc(), String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null2"), "[SET_REGION]"));
        RegionManager.setRegion(airDrop);
    }
}