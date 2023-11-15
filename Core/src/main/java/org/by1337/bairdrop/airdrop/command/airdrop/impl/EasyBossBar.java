package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.bairdrop.observer.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;
@Deprecated
public class EasyBossBar implements CommandExecutor {
    @Getter
    private static final HashMap<String, org.by1337.bairdrop.util.EasyBossBar> easyBossBarHashMap = new HashMap<>();


    @Override
    public String getCommandPrefix() {
        return "[EasyBossBar=";
    }

    @Override
    public void execute(Event event, @NotNull String command) {
        Airdrop airdrop = event.getAirdrop();
        Objects.requireNonNull(airdrop, "AirDrop is null!");
        String param = airdrop.replace(org.by1337.bairdrop.util.EasyBossBar.getParam(command));
        if (easyBossBarHashMap.containsKey(param)){
            easyBossBarHashMap.get(param).execCommands(command);
        }else {
            org.by1337.bairdrop.util.EasyBossBar easyBossBar = new org.by1337.bairdrop.util.EasyBossBar(airdrop, param);
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
