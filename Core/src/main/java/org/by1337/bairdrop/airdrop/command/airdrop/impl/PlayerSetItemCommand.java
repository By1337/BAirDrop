package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
@Deprecated
public class PlayerSetItemCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[PLAYER-SET-ITEM-";
    }

    @Override
    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) {
        Objects.requireNonNull(player, "player is null! " + command);
        try {
            int slot = Integer.parseInt(command.split("ITEM-")[1].split("=")[0]);
            Material material = Material.valueOf(command.split("=")[1].replace("]", ""));
            player.getInventory().setItem(slot, new ItemStack(material));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            Message.error("[PLAYER-SET-ITEM-<slot>=<material>]" + command);
        }

    }

    @Override
    public String usage() {
        return "[PLAYER-SET-ITEM-<slot>=<material>]";
    }

    @Override
    public Command createCommand() {
        return null;
    }

    @Override
    public void testCommand(@NotNull String command) throws CommandException {

    }
}