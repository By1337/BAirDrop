package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.util.OLDMessage;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TitleCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[TITLE]";
    }

    @Override // заглушка
    public void execute(Event event, @NotNull String command) throws CommandException {
        execute(event.getAirdrop(), event.getPlayer(), command);
    }

    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) {
        String subTitle = "";
        String Title;
        if (command.contains("[SUB_TITLE]")) {
            command = command.replace("[SUB_TITLE]", "%spliterator%");
            subTitle = command.split("%spliterator%")[1];
            Title = command.split("%spliterator%")[0];
            Title = Title.replace("[TITLE] ", "");
        } else {
            Title = command.replace("[TITLE] ", "");
        }
        OLDMessage.sendTitle(Objects.requireNonNull(player, "player is null! " + command), Title, subTitle);
    }
    @Override
    public String usage() {
        return "[TITLE] <message>";
    }

    @Override
    public Command createCommand() {
        return null;
    }

    @Override
    public void testCommand(@NotNull String command) throws CommandException {

    }
}