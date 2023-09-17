package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.scripts.JsScript;
import org.by1337.bairdrop.scripts.Script;
import org.by1337.bairdrop.util.LogLevel;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class RunJsCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[RUN_JS=";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) {
        long x = System.currentTimeMillis();
        command = command.replace(" ", "");
        String jsName = command.split("RUN_JS=")[1].split("]")[0];
        if (!BAirDrop.getiConfig().getScripts().containsKey(jsName)) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-js-script"), jsName));
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("player", player);
        map.put("airdrop", airDrop);
        Script manager = new JsScript();
        manager.runScript(jsName, map);
        Message.debug(String.format(BAirDrop.getConfigMessage().getMessage("js-time"), command, (System.currentTimeMillis() - x)), LogLevel.MEDIUM);
    }
}
