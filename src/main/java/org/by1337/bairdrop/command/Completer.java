package org.by1337.bairdrop.command;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.scripts.Manager;
import org.by1337.bairdrop.util.LogLevel;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Completer implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
      //  Message.debug(Arrays.toString(args) + " = " + args.length, LogLevel.HARD);
        if(sender instanceof Player pl){
            if(!pl.hasPermission("bair.*")) return null;
            List<String> list = new ArrayList<>();
            for(AirDrop airDrop : BAirDrop.airDrops.values()){
                if(!airDrop.isHideInCompleter())
                    list.add(airDrop.getAirId());
            }
            if (args.length == 2 && args[0].equals("js")) return Config.scripts.values().stream().map(File::getName).collect(Collectors.toList());
            if (args.length == 2 && !args[0].equals("get") && !args[0].equals("compass")) return list;
            if (args.length == 1 ) {
                return List.of("menu", "start", "stop", "tp", "listeners", "generate", "reload", "clone", "get", "delete", "create", "compass", "js", "help");
            }
            if (args.length == 2) return BAirDrop.summoner.getItems().keySet().stream().toList();
        }else {
            List<String> list = new ArrayList<>(BAirDrop.airDrops.keySet().stream().toList());
            if (args.length == 2 && args[0].equals("js")) return Config.scripts.values().stream().map(File::getName).collect(Collectors.toList());
            if (args.length == 2 && !args[0].equals("get")) return list;
            if (args.length == 1 ) {
                return List.of("start", "stop", "generate", "reload", "clone", "delete", "create", "get", "compass", "js");
            }
            if (args[0].equals("get")) return BAirDrop.summoner.getItems().keySet().stream().toList();
        }
        if (args.length == 3 && args[0].equals("generate")) return List.of("stop", "1");
        if (args.length == 4 && args[0].equals("generate")) return List.of("50");
        if (args.length == 4 && args[0].equals("clone")) return List.of("-temp");
        if (args.length == 3 && args[0].equals("clone")) return List.of("<new id>");
        return null;
    }
}
