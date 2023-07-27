package org.by1337.bairdrop.util;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Lidded;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.AirDropUtils;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.listeners.SetStaticLocation;
import org.by1337.bairdrop.listeners.util.ListenChat;
import org.by1337.bairdrop.locationGenerator.GeneratorUtils;
import org.by1337.bairdrop.worldGuardHook.CSchematicsManager;
import org.by1337.bairdrop.worldGuardHook.RegionManager;
import org.by1337.bairdrop.api.event.ExecuteCommandEvent;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.customListeners.CustomEventListener;
import org.by1337.bairdrop.menu.*;
import org.by1337.bairdrop.menu.util.MenuItem;
import org.by1337.bairdrop.scripts.JsScript;
import org.by1337.bairdrop.scripts.Script;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.Bukkit.*;

import static org.by1337.bairdrop.BAirDrop.getInstance;

public class ExecuteCommands {
    private static List<String> ignoreCommands = new ArrayList<>();
    public static HashMap<String, EasyBossBar> easyBossBarHashMap = new HashMap<>();

    public static boolean hasIgnoreCommand(String command) {
        return ignoreCommands.contains(command);
    }

    public static void registerIgnoreCommand(String command) {
        if (hasIgnoreCommand(command)) {
            throw new IllegalArgumentException("This command is already being ignored");
        }
        ignoreCommands.add(command);
    }

    public static void unregisterIgnoreCommand(String command) {
        ignoreCommands.remove(command);
    }

    private static boolean isIgnore(String command) {
        for (String str : ignoreCommands) {
            if (command.contains(str)) return true;
        }
        return false;
    }

    public void runListenerCommands(String[] commands, @Nullable Player pl, @Nullable AirDrop airDrop, CustomEvent customEvent) {
        ExecuteCommandEvent event = new ExecuteCommandEvent(airDrop, commands, pl, customEvent);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        for (String command : commands) {
            if (isIgnore(command)) {
                Message.debug("ignore " + command, LogLevel.HARD);
                continue;
            }
            if (airDrop != null)
                command = airDrop.replaceInternalPlaceholder(command);
            command = Message.setPlaceholders(pl, command);
            if (command.contains("[math#"))
                command = CustomEventListener.math(command, airDrop, pl);
            if (command.contains("{player-get-item-") && pl != null)
                command = setPlayerPlaceholder(pl, command);

            command = AirDropUtils.match(command);

            if (runJsCommand(pl, airDrop, command))
                continue;
            if (pl != null)
                if (execPlayerCommands(pl, command))
                    continue;
            if (execPlayerNullCommand(command))
                continue;
            if (airDrop != null)
                if (executeAirdropCommand(airDrop, command))
                    continue;
            if (bossBar(command, pl))
                continue;
            if (airDrop != null) {
                if (command.contains("[CALL-")) {
                    String str = command.replace("[CALL-", "").replace("]", "");
                    airDrop.invokeListener(NamespacedKey.fromString(str), pl, customEvent);
                    continue;
                }
                if (command.contains("[NEAR-PLAYERS=")) {
                    try {
                        int range = Integer.parseInt(command.split("=")[1].split("]")[0]);

                        for (Entity entity : airDrop.getAnyLoc().getWorld().getNearbyEntities(airDrop.getAnyLoc(), range, range, range)) {
                            if (entity instanceof Player player) {
                                airDrop.invokeListener(NamespacedKey.fromString(command
                                        .replace(String.format("[NEAR-PLAYERS=%s] {CALL-", range), "")
                                        .replace("}", "")), player, customEvent);
                            }
                        }
                    } catch (NumberFormatException e) {
                        Message.error(String.format(BAirDrop.getConfigMessage().getMessage("near-error-1"), command));
                    } catch (NullPointerException e) {
                        Message.error(String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null-command"), command));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Message.error(String.format(BAirDrop.getConfigMessage().getMessage("few-args-command"), command));
                    }
                    continue;
                }
            }

            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-command"), command));
        }
    }

    public boolean runJsCommand(@Nullable Player pl, @Nullable AirDrop airDrop, String command) {
        long x = System.currentTimeMillis();
        if (command.contains("[RUN_JS")) {
            if (command.contains("-scheduler")) {
                command = command.replace("-scheduler", "");
                String preCmd = command;
                new BukkitRunnable() {
                    String finalCommand = preCmd;

                    @Override
                    public void run() {
                        try {
                            if (finalCommand.contains("[RUN_JS=")) {
                                finalCommand = finalCommand.replace(" ", "");
                                String jsName = finalCommand.split("RUN_JS=")[1].split("]")[0];
                                if (!BAirDrop.getiConfig().getScripts().containsKey(jsName)) {
                                    Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-js-script"), jsName));
                                    return;
                                }
                                HashMap<String, Object> map = new HashMap<>();
                                if (finalCommand.contains("param(")) {
                                    String param = finalCommand.split("param")[1];
                                    param = param.replace("(", "").replace(")", "");
                                    String[] args = param.split(",");
                                    for (String str : args) {
                                        Object scriptParam = null;
                                        if (str.split("=")[1].equals("player"))
                                            scriptParam = pl;
                                        else if (str.split("=")[1].equals("airdrop"))
                                            scriptParam = airDrop;
                                        else scriptParam = str.split("=")[1];
                                        map.put(str.split("=")[0], scriptParam);
                                    }
                                }
                                Script manager = new JsScript();
                                manager.runScript(jsName, map);
                            }
                        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                            e.printStackTrace();
                        }
                        cancel();
                    }
                }.runTaskLater(getInstance(), 0);
                Message.debug(String.format(BAirDrop.getConfigMessage().getMessage("js-time"), command, (System.currentTimeMillis() - x)), LogLevel.MEDIUM);
                return true;
            } else {
                try {
                    if (command.contains("[RUN_JS=")) {
                        command = command.replace(" ", "");
                        String jsName = command.split("RUN_JS=")[1].split("]")[0];
                        if (!BAirDrop.getiConfig().getScripts().containsKey(jsName)) {
                            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-js-script"), jsName));
                            return true;
                        }
                        HashMap<String, Object> map = new HashMap<>();
                        if (command.contains("param(")) {
                            String param = command.split("param")[1];
                            param = param.replace("(", "").replace(")", "");
                            String[] args = param.split(",");
                            for (String str : args) {
                                Object scriptParam = null;
                                if (str.split("=")[1].equals("player"))
                                    scriptParam = pl;
                                else if (str.split("=")[1].equals("airdrop"))
                                    scriptParam = airDrop;
                                else scriptParam = str.split("=")[1];
                                map.put(str.split("=")[0], scriptParam);
                            }
                        }
                        Script manager = new JsScript();
                        manager.runScript(jsName, map);
                        Message.debug(String.format(BAirDrop.getConfigMessage().getMessage("js-time"), command, (System.currentTimeMillis() - x)), LogLevel.MEDIUM);
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static String setPlayerPlaceholder(Player pl, String command) {
        if (command.contains("{player-get-item-")) {
            try {
                int slot = Integer.parseInt(command.split("player-get-item-")[1].split("}")[0]);
                ItemStack item = pl.getInventory().getItem(slot);
                if (command.contains(".getType")) {
                    if (item == null) {
                        command = command.replace(String.format("{player-get-item-%s}.getType", slot), "AIR");
                        return command;
                    }
                    command = command.replace(String.format("{player-get-item-%s}.getType", slot), item.getType().toString());
                    return command;
                }
                if (command.contains(".isNull")) {
                    if (item == null) {
                        command = command.replace(String.format("{player-get-item-%s}.isNull", slot), "true");
                        return command;
                    }
                    command = command.replace(String.format("{player-get-item-%s}.isNull", slot), "false");
                    return command;
                }
            } catch (NumberFormatException e) {
                Message.error("{player-get-item-<slot>} <slot> должен быть числом!");
            } catch (ArrayIndexOutOfBoundsException e) {
                Message.error("{player-get-item-<slot>} Не достаточно аргументов!");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return command;
    }

    public boolean bossBar(String command, @Nullable Player pl) {
        if (command.contains("[NEW_BOSSBAR]")) {
            command = command.replace("[NEW_BOSSBAR]", "");
            int quoteCount = command.replaceAll("[^\"]", "").length();
            if (quoteCount % 2 != 0) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("boss-bar-format-error"), command));
                return true;
            } else {
                StringBuilder result = new StringBuilder();
                boolean insideQuotes = false;
                for (char c : command.toCharArray()) {
                    if (c == '\"') {
                        insideQuotes = !insideQuotes;
                    }
                    if (!insideQuotes && c == ' ') {
                        continue;
                    }
                    result.append(c);
                }
                command = result.toString();
            }
            String[] args = command.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            if (args.length < 4) {
                Message.error(BAirDrop.getConfigMessage().getMessage("boss-bar-few-args"));
                Message.error(Arrays.toString(args));
                return true;
            }
            String title = null;
            String name = null;
            BarColor barColor = null;
            BarStyle barStyle = null;
            try {
                for (String key : args) {
                    if (key.contains("name")) {
                        name = key.replace("name=", "").replace("\"", "");
                        continue;
                    }
                    if (key.contains("setTitle")) {
                        title = key.replace("setTitle=", "").replace("\"", "");
                        continue;
                    }
                    if (key.contains("BarColor")) {
                        barColor = BarColor.valueOf(key.replace("BarColor=", ""));
                        continue;
                    }
                    if (key.contains("BarStyle")) {
                        barStyle = BarStyle.valueOf(key.replace("BarStyle=", ""));
                        continue;
                    }
                }
            } catch (IllegalArgumentException e) {
                Message.error(BAirDrop.getConfigMessage().getMessage("bar-error"));
                Message.error(BAirDrop.getConfigMessage().getMessage("boss-bar-not-created"));
                Message.error(Arrays.toString(args));
                Message.error(e.getLocalizedMessage());
                return true;
            }
            if (title == null || name == null || barColor == null || barStyle == null) {
                Message.error(BAirDrop.getConfigMessage().getMessage("boss-bar-not-created"));
                Message.error(BAirDrop.getConfigMessage().getMessage("boss-bar-few-args"));
                return true;
            }
            BossBar bossBar = Bukkit.createBossBar(Message.messageBuilder(title), barColor, barStyle);
            bossBarAddParam(bossBar, args, pl);
            if (Message.bossBars.containsKey(name)) {
                Message.warning(String.format(BAirDrop.getConfigMessage().getMessage("boss-bar-already-created"), name));
                Message.bossBars.get(name).removeAll();
            }
            Message.bossBars.put(name, bossBar);
            return true;

        }
        if (command.contains("[BOSSBAR]")) {
            command = command.replace("[BOSSBAR]", "");
            int quoteCount = command.replaceAll("[^\"]", "").length();
            if (quoteCount % 2 != 0) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("boss-bar-error-command"), command));
                return true;
            } else {
                StringBuilder result = new StringBuilder();
                boolean insideQuotes = false;
                for (char c : command.toCharArray()) {
                    if (c == '\"') {
                        insideQuotes = !insideQuotes;
                    }
                    if (!insideQuotes && c == ' ') {
                        continue;
                    }
                    result.append(c);
                }
                command = result.toString();
            }
            String[] args = command.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            if (args.length < 1) {
                Message.error(BAirDrop.getConfigMessage().getMessage("boss-bar-few-args"));
                return true;
            }
            String name = null;
            for (String key : args)
                if (key.contains("name")) {
                    name = key.replace("name=", "").replace("\"", "");
                    continue;
                }
            if (name == null) {
                Message.error(BAirDrop.getConfigMessage().getMessage("bar-error"));
                Message.error(BAirDrop.getConfigMessage().getMessage("boss-bar-not-created"));
                Message.error(Arrays.toString(args));
                return true;

            }
            BossBar bossBar = Message.bossBars.getOrDefault(name, null);
            if (bossBar == null) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-boss-bar"), name));
                return true;
            }
            bossBarAddParam(bossBar, args, pl);
            return true;
        }

        if (command.contains("[REMOVE_BOSSBAR]")) {
            command = command.replace("[REMOVE_BOSSBAR]", "");
            int quoteCount = command.replaceAll("[^\"]", "").length();
            if (quoteCount % 2 != 0) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("boss-bar-error-command"), command));
                return true;
            } else {
                StringBuilder result = new StringBuilder();
                boolean insideQuotes = false;
                for (char c : command.toCharArray()) {
                    if (c == '\"') {
                        insideQuotes = !insideQuotes;
                    }
                    if (!insideQuotes && c == ' ') {
                        continue;
                    }
                    result.append(c);
                }
                command = result.toString();
            }
            String[] args = command.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            if (args.length < 1) {
                Message.error(BAirDrop.getConfigMessage().getMessage("few-arg-for-del-boss-bar"));
                return true;
            }
            String name = null;
            for (String key : args)
                if (key.contains("name")) {
                    name = key.replace("name=", "").replace("\"", "");
                    continue;
                }
            if (name == null) {
                Message.error(BAirDrop.getConfigMessage().getMessage("fail-del-boss-bar"));
                Message.error(BAirDrop.getConfigMessage().getMessage("fail-del-boss-bar2"));
                return true;
            }
            BossBar bossBar = Message.bossBars.getOrDefault(name, null);
            if (bossBar == null) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-boss-bar"), name));
                return true;
            }
            Message.bossBars.remove(name);
            return true;
        }
        return false;
    }

    public void bossBarAddParam(BossBar bossBar, String[] args, @Nullable Player pl) {
        try {
            for (String key : args) {
                if (key.contains("setTitle")) {
                    bossBar.setTitle(Message.messageBuilder(key.replace("setTitle=", "").replace("\"", "")));
                    continue;
                }
                if (key.contains("BarColor")) {
                    bossBar.setColor(BarColor.valueOf(key.replace("BarColor=", "")));
                    continue;
                }
                if (key.contains("BarStyle")) {
                    bossBar.setStyle(BarStyle.valueOf(key.replace("BarStyle=", "")));
                    continue;
                }
                if (key.contains("setProgress")) {
                    bossBar.setProgress(Double.parseDouble(key.replace("setProgress=", "")));
                    continue;
                }
                if (key.contains("addAll")) {
                    for (Player player : Bukkit.getOnlinePlayers())
                        bossBar.addPlayer(player);
                    continue;
                }
                if (key.contains("addPlayer")) {
                    if (pl != null)
                        bossBar.addPlayer(pl);
                    else
                        Message.error(BAirDrop.getConfigMessage().getMessage("boss-bar-fail"));
                    continue;
                }
                if (key.contains("removePlayer")) {
                    if (pl != null)
                        bossBar.removePlayer(pl);
                    else
                        Message.error(BAirDrop.getConfigMessage().getMessage("boss-bar-fail2"));
                    continue;
                }
                if (key.contains("removeAll")) {
                    bossBar.removeAll();
                    continue;
                }
                if (key.contains("setVisible")) {
                    bossBar.setVisible(Boolean.parseBoolean(key.replace("setVisible=", "")));
                    continue;
                }
                if (key.contains("BarFlag")) {
                    bossBar.addFlag(BarFlag.valueOf(key.replace("BarFlag=", "")));
                    continue;
                }
                if (key.contains("removeFlag")) {
                    bossBar.removeFlag(BarFlag.valueOf(key.replace("removeFlag=", "")));
                    continue;
                }
                if (!key.contains("name="))
                    Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-cmd-boss-bar"), key));
            }
        } catch (IllegalArgumentException e) {
            Message.error(BAirDrop.getConfigMessage().getMessage("IllegalArgumentException-boss-bar"));
            Message.error(BAirDrop.getConfigMessage().getMessage("IllegalArgumentException-boss-bar2"));
            Message.error(e.getLocalizedMessage());
        }
    }

    public boolean executeAirdropCommand(@NotNull AirDrop airDrop, String command) {
        if (command.equals("[ACTIVATE]")) {
            airDrop.setActivated(true);
            return true;
        }
        if (command.contains("[EasyBossBar=")) {
            String param = airDrop.replaceInternalPlaceholder(EasyBossBar.getParam(command));
            if (easyBossBarHashMap.containsKey(param)){
                easyBossBarHashMap.get(param).execCommands(command);
            }else {
                EasyBossBar easyBossBar = new EasyBossBar(airDrop, param);
                easyBossBarHashMap.put(param,easyBossBar);
                easyBossBar.execCommands(command);
            }
            return true;
        }
        if (command.contains("[EFFECT_START-")) {
            String[] args = command.split("-");
            if (args.length != 3) {
                Message.warning(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                Message.warning("[EFFECT_START-<NAME>-<id>]");
                return true;
            }
            try {
                String id = args[2].replace("]", "");
                airDrop.loadEffect(args[1], id);
                airDrop.startEffect(id);
            } catch (IllegalArgumentException e) {
                Message.warning(e.getLocalizedMessage());
            }
            return true;
        }
        if (command.contains("[EFFECT_STOP-")) {
            String[] args = command.split("-");
            if (args.length != 2) {
                Message.warning(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                Message.warning("[EFFECT_STOP-<id>]");
                return true;
            }
            try {
                airDrop.StopEffect(args[1].replace("]", ""));
            } catch (IllegalArgumentException e) {
                Message.warning(e.getLocalizedMessage());
            }

            return true;
        }
        if (command.equalsIgnoreCase("[EFFECT_STOP_ALL]")) {
            airDrop.StopAllEffects();
            airDrop.setLoadedEffect(new HashMap<>());
            return true;
        }
        if (command.equalsIgnoreCase("[BLOCK_SET_OPEN]")) {
            Location location = airDrop.getAnyLoc();
            if (location == null) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null2"), command));
                return true;
            }
            Block block = location.getBlock();
            BlockState blockState = block.getState();
            if (blockState instanceof Lidded lidded) {
                lidded.open();
            } else {
                Message.error(BAirDrop.getConfigMessage().getMessage("lidded-error"));
            }
            return true;
        }
        if (command.equalsIgnoreCase("[BLOCK_SET_CLOSE]")) {
            Location location = airDrop.getAnyLoc();
            if (location == null) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null2"), command));
                return true;
            }
            Block block = location.getBlock();
            BlockState blockState = block.getState();
            if (blockState instanceof Lidded lidded) {
                lidded.close();
            } else {
                Message.error(BAirDrop.getConfigMessage().getMessage("lidded-error"));
            }
            return true;
        }
        if (command.contains("[SET_BLOCK_FACE_")) { //[SET_BLOCK_FACE_UP]
            Location location = airDrop.getAnyLoc();
            if (location == null) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null2"), command));
                return true;
            }
            Pattern pattern = Pattern.compile("\\[SET_BLOCK_FACE_(.*?)\\]");
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                String output = matcher.group(1);
                try {
                    BlockFace blockFace = BlockFace.valueOf(output);
                    Block block = location.getBlock();
                    if (block.getBlockData() instanceof Directional directional) {
                        BlockState state = block.getState();
                        directional.setFacing(blockFace);
                        state.setBlockData(directional);
                        state.update(true);
                    } else {
                        Message.error(BAirDrop.getConfigMessage().getMessage("rotate-error"));
                    }
                } catch (IllegalArgumentException e) {
                    Message.error(e.getLocalizedMessage());
                }
            }
            return true;
        }
        if (command.contains("[SET_MATERIAL_")) {
            Location location = airDrop.getAnyLoc();
            if (location == null) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null2"), command));
                return true;
            }
            boolean subtractOffsets = false;
            if (command.contains("-offsets")) {
                subtractOffsets = true;
                command = command.replace("-offsets", "");
            }
            location = location.clone();
            try {
                Material mat = Material.valueOf(command.replace("]", "").replace("[SET_MATERIAL_", "").replace(" ", ""));
                if (subtractOffsets)
                    location.add(
                            -GeneratorUtils.getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.x", GeneratorUtils.getWorldKeyByWorld(location.getWorld()))),
                            -GeneratorUtils.getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.y", GeneratorUtils.getWorldKeyByWorld(location.getWorld()))),
                            -GeneratorUtils.getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.z", GeneratorUtils.getWorldKeyByWorld(location.getWorld())))).add(0,
                            1
                            , 0);
                location.getBlock().setType(mat);
                if (mat == Material.RESPAWN_ANCHOR) {
                    RespawnAnchor ra = (RespawnAnchor) location.getBlock().getBlockData();
                    ra.setCharges(4);
                    location.getBlock().setBlockData(ra);
                } else if (mat == Material.BARREL) {
                    BlockState barrelState = location.getBlock().getState();
                    barrelState.setType(Material.BARREL);
                    Directional directionalData = (Directional) Material.BARREL.createBlockData();
                    directionalData.setFacing(BlockFace.UP);
                    barrelState.setBlockData(directionalData);
                    barrelState.update(true);
                }
            } catch (IllegalArgumentException e) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-material"), "?", command));
                Message.warning(e.getLocalizedMessage());
                return true;
            }
            return true;
        }
        if (command.contains("[SCHEMATICS_PASTE-")) {
            String schem = command.replace("[SCHEMATICS_PASTE-", "").replace("]", "");
            airDrop.schematicsPaste(new CSchematicsManager(), schem);
            return true;
        }
        if (command.equalsIgnoreCase("[SCHEMATICS_REMOVE]")) {
            airDrop.schematicsUndo();
            return true;
        }
        if (command.contains("[SET_TIME_START-")) {
            String str = command.replace("[SET_TIME_START-", "").replace("]", "");
            try {
                airDrop.setTimeToStart(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                Message.error(e.getLocalizedMessage());
            }
            return true;
        }
        if (command.contains("[SET_TIME_UNLOCK-")) {
            String str = command.replace("[SET_TIME_UNLOCK-", "").replace("]", "");
            try {
                airDrop.setTimeToOpen(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                Message.error(e.getLocalizedMessage());
            }
            return true;
        }


        if (command.contains("[SET_TIME_END-")) {
            String str = command.replace("[SET_TIME_END-", "").replace("]", "");
            try {
                airDrop.setTimeStop(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                Message.error(e.getLocalizedMessage());
            }
            return true;
        }
        if (command.equalsIgnoreCase("[SET_REGION]")) {
            if (airDrop.getAirDropLocation() == null) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null2:"), "[SET_REGION]"));
                return true;
            }
            RegionManager.SetRegion(airDrop);
            return true;
        }
        if (command.equalsIgnoreCase("[SET_HOLO_TIME_TO_START]")) {
            if (airDrop.getAirDropLocation() == null) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null2:"), "[SET_HOLO_TIME_TO_START]"));
                return true;
            }
            airDrop.setHoloTimeToStartEnabled(true);
            airDrop.setHoloTimeToStartMinusOffsets(false);
            return true;
        }
        if (command.equalsIgnoreCase("[SET_HOLO_TIME_TO_START]-offsets")) {
            if (airDrop.getAirDropLocation() == null) {
                Message.error(String.format(BAirDrop.getConfigMessage().getMessage("loc-is-null2:"), "[[SET_HOLO_TIME_TO_START]-offsets"));
                return true;
            }
            airDrop.setHoloTimeToStartEnabled(true);
            airDrop.setHoloTimeToStartMinusOffsets(true);
            return true;
        }
        return false;
    }

    public boolean execPlayerNullCommand(String command) {
        if (command.contains("[ERROR]")) {
            String str = command.replace("[ERROR]", "");
            Message.error(str);
            return true;
        }
        if (command.contains("[LOGGER]")) {
            String str = command.replace("[LOGGER]", "");
            Message.logger(str);
            return true;
        }
        if (command.contains("[MESSAGE_ALL]")) {
            Message.sendAllMsg(command.replace("[MESSAGE_ALL] ", ""));
            return true;
        }
        if (command.contains("[TITLE_ALL]")) {
            String subTitle = "";
            String Title;
            if (command.contains("[SUB_TITLE_ALL]")) {
                command = command.replace("[SUB_TITLE_ALL]", "%rbts%");
                subTitle = command.split("%rbts%")[1];
                Title = command.split("%rbts%")[0];
                Title = Title.replace("[TITLE_ALL] ", "");
            } else {
                Title = command.replace("[TITLE_ALL] ", "");
            }
            Message.sendAllTitle(Title, subTitle);
            return true;
        }
        if (command.contains("[SUB_TITLE_ALL]")) {
            Message.sendAllTitle(" ", command.replace("[SUB_TITLE_ALL] ", ""));
            return true;
        }
        if (command.contains("[ACTIONBAR_ALL]")) {
            Message.sendAllActionBar(command.replace("[ACTIONBAR_ALL] ", ""));
            return true;
        }
        if (command.contains("[CONSOLE]")) {
            command = command.replace("[CONSOLE] ", "");
            //  command = Message.setPlaceholders(pl, command);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
            return true;
        }
        if (command.contains("[SOUND_ALL]")) {
            Message.sendAllSound(command.replace("[SOUND_ALL] ", ""));
            return true;
        }
        return false;
    }

    public boolean execPlayerCommands(@NotNull Player pl, String command) {//(
        if (command.equalsIgnoreCase("[PLAYER-CLOSE-INVENTORY]")) {
            pl.closeInventory();
            return true;
        }
        if (command.contains("[PLAYER-SET-ITEM-")) {
            try {
                int slot = Integer.parseInt(command.split("ITEM-")[1].split("=")[0]);
                Material material = Material.valueOf(command.split("=")[1].replace("]", ""));
                pl.getInventory().setItem(slot, new ItemStack(material));
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                Message.error("[PLAYER-SET-ITEM-<slot>=<material>]" + command);
            }
            return true;
        }
        if (command.contains("[MESSAGE]")) {
            Message.sendMsg(pl, command.replace("[MESSAGE] ", ""));
            return true;
        }
        if (command.contains("[TITLE]")) {
            String subTitle = "";
            String Title;
            if (command.contains("[SUB_TITLE]")) {
                command = command.replace("[SUB_TITLE]", "%rbts%");
                subTitle = command.split("%rbts%")[1];
                Title = command.split("%rbts%")[0];
                Title = Title.replace("[TITLE] ", "");
            } else {
                Title = command.replace("[TITLE] ", "");
            }
            Message.sendTitle(pl, Title, subTitle);
            return true;
        }
        if (command.contains("[SUB_TITLE]")) {
            Message.sendTitle(pl, " ", command.replace("[SUB_TITLE] ", ""));
            return true;
        }
        if (command.contains("[ACTIONBAR]")) {
            Message.sendActionBar(pl, command.replace("[ACTIONBAR] ", ""));
            return true;
        }
        if (command.contains("[SOUND]")) {
            Message.sendSound(pl, command.replace("[SOUND] ", ""));
            return true;
        }
        if (command.contains("[PLAYER]")) {
            pl.performCommand(command.replace("[PLAYER] ", ""));
            return true;
        }
        return false;
    }

    public static void execute(AirDrop airDrop, MenuItem menuItem, InventoryClickEvent e) {
        if (e.getClick() == ClickType.LEFT)
            menuCommand(menuItem.getLEFT_COMMANDS(), airDrop, (Player) e.getWhoClicked());
        if (e.getClick() == ClickType.RIGHT)
            menuCommand(menuItem.getRIGHT_COMMANDS(), airDrop, (Player) e.getWhoClicked());
        if (e.getClick() == ClickType.SHIFT_LEFT)
            menuCommand(menuItem.getSHIFT_LEFT_COMMANDS(), airDrop, (Player) e.getWhoClicked());
        if (e.getClick() == ClickType.SHIFT_RIGHT)
            menuCommand(menuItem.getSHIFT_RIGHT_COMMANDS(), airDrop, (Player) e.getWhoClicked());
        if (e.getClick() == ClickType.MIDDLE)
            menuCommand(menuItem.getMIDDLE_COMMANDS(), airDrop, (Player) e.getWhoClicked());
        if (e.getClick() == ClickType.DROP)
            menuCommand(menuItem.getDROP_COMMANDS(), airDrop, (Player) e.getWhoClicked());
    }

    private static void menuCommand(List<String> commands, AirDrop airDrop, Player pl) {
        commands.replaceAll(airDrop::replaceInternalPlaceholder);

        for (String str : commands) {
            if (str.equalsIgnoreCase("[!airdropstarted]")) {
                if (airDrop.isAirDropStarted())
                    airDrop.End();
                else airDrop.startCommand(pl);
                continue;
            }
            if (str.equalsIgnoreCase("[teleport]")) {
                if (airDrop.isAirDropStarted()) {
                    pl.teleport(airDrop.getAnyLoc());
                    pl.closeInventory();
                }
            }
            if (str.equalsIgnoreCase("[!airlocked]")) {
                if (!airDrop.isAirDropStarted()) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("airdrop-is-not-started"));
                    return;
                }
                if (airDrop.isAirDropLocked()) {
                    airDrop.unlock();
                    if (airDrop.isStartCountdownAfterClick() && !airDrop.isActivated()) {
                        airDrop.notifyObservers(CustomEvent.ACTIVATE, pl);
                    }
                } else {
                    airDrop.setTimeToOpen(airDrop.getFileConfiguration().getInt("openingTime") * 60);
                    airDrop.setAirDropLocked(true);
                    airDrop.getAirDropLocation().getBlock().setType(airDrop.getMaterialLocked());
                }
                continue;
            }
//            if(CAirDrop.getAirId().equals("default2")){
//                Message.sendMsg(pl, "&cЭто защищёный аирдроп и его редактировать нельзя");
//                return;
//            }

            if (str.equalsIgnoreCase("[!start-countdown-after-click]")) {
                airDrop.setStartCountdownAfterClick(!airDrop.isStartCountdownAfterClick());
                airDrop.save();
                continue;
            }

            if (str.contains("[player]")) {
                pl.performCommand(str.replace("[player] ", ""));
                continue;
            }
            if (str.contains("[PLAYER]")) {
                pl.performCommand(str.replace("[PLAYER] ", ""));
                continue;
            }

            if (str.equalsIgnoreCase("[edit]")) {
                pl.closeInventory();
                SelectInv si = new SelectInv(airDrop, "[edit]");
                getServer().getPluginManager().registerEvents(si, BAirDrop.getInstance());
                pl.openInventory(si.getInv());
            }
            if (str.equalsIgnoreCase("[event_list]")) {
                pl.closeInventory();
                ShowAllListeners sae = new ShowAllListeners(airDrop);
                getServer().getPluginManager().registerEvents(sae, BAirDrop.getInstance());
                pl.openInventory(sae.getInventory());

            }
            if (str.equalsIgnoreCase("[chance]")) {
                pl.closeInventory();
                SelectInv si = new SelectInv(airDrop, "[chance]");
                getServer().getPluginManager().registerEvents(si, BAirDrop.getInstance());
                pl.openInventory(si.getInv());
            }

            if (str.equalsIgnoreCase("[change-locked-material]")) {
                pl.closeInventory();
                ChangeMaterial cm = new ChangeMaterial(airDrop, true);
                getServer().getPluginManager().registerEvents(cm, BAirDrop.getInstance());
                pl.openInventory(cm.getInventory());
            }
            if (str.equalsIgnoreCase("[change-unlocked-material]")) {
                pl.closeInventory();
                ChangeMaterial cm = new ChangeMaterial(airDrop, false);
                getServer().getPluginManager().registerEvents(cm, BAirDrop.getInstance());
                pl.openInventory(cm.getInventory());
            }
            if (str.equalsIgnoreCase("[change-world]")) {
//                if(true){//todo
//                    Message.sendMsg(pl, "&cВ демо версии изменение этой настройки запрещены!");
//                    return;
//                }
                pl.closeInventory();
                ChangeWorld cw = new ChangeWorld(airDrop);
                getServer().getPluginManager().registerEvents(cw, BAirDrop.getInstance());
                pl.openInventory(cw.getInventory());
            }
            if (str.equalsIgnoreCase("[!usePreGeneratedLocations]")) {
                airDrop.setUsePreGeneratedLocations(!airDrop.isUsePreGeneratedLocations());
                airDrop.save();
            }
            if (str.equalsIgnoreCase("[!use-static-loc]")) {
                airDrop.setUseStaticLoc(!airDrop.isUseStaticLoc());
                airDrop.save();
            }
            if (str.equalsIgnoreCase("[!flatnessCheck]")) {
                airDrop.setFlatnessCheck(!airDrop.isFlatnessCheck());
                airDrop.save();
            }
            if (str.equalsIgnoreCase("[!time-stop-event-must-go]")) {
                airDrop.setTimeStopEventMustGo(!airDrop.isTimeStopEventMustGo());
                airDrop.save();
            }
            if (str.equalsIgnoreCase("[change-airname]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "airname", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-invname]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "invname", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
//            if(true){//todo
//                Message.sendMsg(pl, "&cВ демо версии изменение этой настройки запрещены!");
//                return;
//            }
            if (str.equalsIgnoreCase("[change-spawnmin]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "spawnmin", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-spawnmax]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "spawnmax", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-airprotect]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "airprotect", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-timetostart]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "timetostart", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-searchbeforestart]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "searchbeforestart", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-timetoopen]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "timetoopen", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-timestop]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "timestop", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-minonlineplayers]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "minonlineplayers", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[!time-stop-event-must-go]")) {
                airDrop.setUseStaticLoc(!airDrop.isUseStaticLoc());
                airDrop.save();
            }
            if (str.equalsIgnoreCase("[set-static-loc]")) {
                if (SetStaticLocation.SSL != null) {
                    SetStaticLocation.SSL.unReg();
                }
                SetStaticLocation ssl = new SetStaticLocation(airDrop);
                getServer().getPluginManager().registerEvents(ssl, BAirDrop.getInstance());
                pl.getInventory().setItem(0, ssl.getItem());
                pl.closeInventory();

            }
            if (str.equalsIgnoreCase("[!use-only-static-loc]")) {
                airDrop.setUseOnlyStaticLoc(!airDrop.isUseOnlyStaticLoc());
                airDrop.save();
                airDrop.getEditAirMenu().menuGenerate("setStaticLoc");
            }
        }
    }
}
