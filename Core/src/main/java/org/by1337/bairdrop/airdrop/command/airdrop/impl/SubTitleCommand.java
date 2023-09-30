package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SubTitleCommand implements CommandExecutor{
 //   @Override
    public String getCommandPrefix() {
        return "[SUB_TITLE]";
    }

   // @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        Message.sendTitle(Objects.requireNonNull(player, "player is null! " + command), " ", command.replace("[SUB_TITLE] ", ""));
    }
    @Override
    public String usage() {
        return "[SUB_TITLE] <message>";
    }

    @Override
    public Command createCommand() {
        return null;
    }

    @Override
    public void testCommand(@NotNull String command) throws CommandException {

    }
}