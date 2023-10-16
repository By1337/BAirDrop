package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.scripts.JsScript;
import org.by1337.bairdrop.scripts.Script;
import org.by1337.bairdrop.util.LogLevel;
import org.by1337.bairdrop.util.Message;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class RunJsCommand implements CommandExecutor {
    private final Resource UNKNOWN_JS = new Resource("command.unknown-js-script");// %s Unknown script!
    private final Resource JS_TIME = new Resource("command.js-time");// &7%s executed in %s
    @Override
    public String getCommandPrefix() { // [RUN_JS=script.js]
        return "[RUN_JS]";
    }

    @Override
    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        long x = System.currentTimeMillis();
        createCommand().executor(((sender, args) -> {
            String jsName = (String) args.getOrThrow("js", usage());
            if (!BAirDrop.getiConfig().getScripts().containsKey(jsName)) {
                Message.error(UNKNOWN_JS.getString(), jsName);
                return;
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("player", player);
            map.put("airdrop", airDrop);
            Script manager = new JsScript();
            manager.runScript(jsName, map);
            Message.debug(String.format(JS_TIME.getString(), jsName, (System.currentTimeMillis() - x)), LogLevel.MEDIUM);
        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[RUN_JS] <js name>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentString("js"));
    }
}
