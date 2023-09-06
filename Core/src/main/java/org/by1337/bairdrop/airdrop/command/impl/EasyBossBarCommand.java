package org.by1337.bairdrop.airdrop.command.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.CommandExecutor;
import org.by1337.bairdrop.util.EasyBossBar;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;

public class EasyBossBarCommand implements CommandExecutor {

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
}
