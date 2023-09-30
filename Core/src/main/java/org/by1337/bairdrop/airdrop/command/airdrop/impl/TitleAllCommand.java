package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TitleAllCommand  implements CommandExecutor{
   // @Override
    public String getCommandPrefix() {
        return "[TITLE_ALL]";
    }

   // @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        String subTitle = "";
        String Title;
        if (command.contains("[SUB_TITLE_ALL]")) {
            command = command.replace("[SUB_TITLE_ALL]", "%spliterator%");
            subTitle = command.split("%spliterator%")[1];
            Title = command.split("%spliterator%")[0];
            Title = Title.replace("[TITLE_ALL] ", "");
        } else {
            Title = command.replace("[TITLE_ALL] ", "");
        }
        Message.sendAllTitle(Title, subTitle);
    }

    @Override
    public String usage() {
        return "[SUB_TITLE_ALL] <message>";
    }

    @Override
    public Command createCommand() {
        return null;
    }

    @Override
    public void testCommand(@NotNull String command) throws CommandException {

    }
}
