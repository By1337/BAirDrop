package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.EasyBossBar;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;
@Deprecated
public class EasyBossBarCommand  implements CommandExecutor {
    @Getter
    private static final HashMap<String, EasyBossBar> easyBossBarHashMap = new HashMap<>();


    @Override
    public String getCommandPrefix() {
        return "[EasyBossBar=";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        Objects.requireNonNull(airDrop, "AirDrop is null!");
        String param = airDrop.replaceInternalPlaceholder(EasyBossBar.getParam(command));
        if (easyBossBarHashMap.containsKey(param)){
            easyBossBarHashMap.get(param).execCommands(command);
        }else {
            EasyBossBar easyBossBar = new EasyBossBar(airDrop, param);
            easyBossBarHashMap.put(param,easyBossBar);
            easyBossBar.execCommands(command);
        }
    }

    @Override
    public String usage() {
        return "[EasyBossBar=<Bossbar>] <args>";
    }

    @Override
    public Command createCommand() {
        return null;
    }


    @Override
    public void testCommand(@NotNull String command) throws CommandException {

    }
}
