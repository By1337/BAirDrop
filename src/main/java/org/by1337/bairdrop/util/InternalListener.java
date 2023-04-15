package org.by1337.bairdrop.util;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.scripts.Manager;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;

public class InternalListener {
    Events event;
    String description;
    String[] commands;
    String[] denyCommands;
    HashMap<String, HashMap<String, String>> requirement;

    public InternalListener(Events event, String[] commands, HashMap<String, HashMap<String, String>> requirement, String description, String[] denyCommands) {
        this.description = description;
        this.event = event;
        this.commands = commands;
        this.requirement = requirement;
        this.denyCommands = denyCommands;
    }

    public void execute(@Nullable Player pl, @Nullable AirDrop airDrop, boolean ignoredRequirement, Events event) {
        if (Arrays.stream(commands).toList().contains("[SCHEDULER]") || Arrays.stream(denyCommands).toList().contains("[SCHEDULER]")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    ExecuteCommands executeCommands = new ExecuteCommands();
                    if (ignoredRequirement)
                        executeCommands.runListenerCommands(commands, pl, airDrop, event);
                    else if (checkRequirement(airDrop, pl)) {
                        executeCommands.runListenerCommands(commands, pl, airDrop, event);
                    } else {
                        executeCommands.runListenerCommands(denyCommands, pl, airDrop, event);
                    }
                }
            }.runTaskLater(BAirDrop.instance, 0);
            return;
        }
        if (Arrays.stream(commands).toList().contains("[ASYNC]") || Arrays.stream(denyCommands).toList().contains("[ASYNC]")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    try {
                        ExecuteCommands executeCommands = new ExecuteCommands();
                        if (ignoredRequirement)
                            executeCommands.runListenerCommands(commands, pl, airDrop, event);
                        else if (checkRequirement(airDrop, pl)) {
                            executeCommands.runListenerCommands(commands, pl, airDrop, event);
                        } else {
                            executeCommands.runListenerCommands(denyCommands, pl, airDrop, event);
                        }
                    } catch (Throwable e) {
                        Message.error(e.getLocalizedMessage());
                    }
                }
            }.runTaskAsynchronously(BAirDrop.instance);
            return;
        }
        ExecuteCommands executeCommands = new ExecuteCommands();
        if (ignoredRequirement)
            executeCommands.runListenerCommands(commands, pl, airDrop, event);
        else if (checkRequirement(airDrop, pl)) {
            executeCommands.runListenerCommands(commands, pl, airDrop, event);
        } else {
            executeCommands.runListenerCommands(denyCommands, pl, airDrop, event);
        }
    }

    public Events getEvent() {
        return event;
    }

    private boolean checkRequirement(@Nullable AirDrop airDrop, @Nullable Player pl) {
        boolean requirementsMet = true;
        for (String idCheck : requirement.keySet()) {
            for (String type : requirement.get(idCheck).keySet()) {
                switch (type) {
                    case "NUMERICAL_CHECK" ->
                            requirementsMet = NUMERICAL_CHECK(idCheck, requirement.get(idCheck).get(type), airDrop, pl);
                    case "LOGICAL_CHECK" ->
                            requirementsMet = LOGICAL_CHECK(requirement.get(idCheck).get(type), airDrop, pl);
                    case "STRING_CHECK" ->
                            requirementsMet = STRING_CHECK(requirement.get(idCheck).get(type), airDrop, pl);
                    case "FIRST_OPEN" -> requirementsMet = airDrop != null && !airDrop.isItWasOpen();
                    default -> requirementsMet = true;
                }
                if (!requirementsMet) return false;
            }
        }
        return true;
    }

    private boolean LOGICAL_CHECK(String req, @Nullable AirDrop airDrop, @Nullable Player pl) {//{[RUN_JS=diamond.js] param(player=player)-scheduler}
        Pattern pattern = Pattern.compile("\\{\\[RUN_JS=.*?\\}");
        Matcher matcher = pattern.matcher(req);
        if (matcher.find()) {
            req = replaceText(req, runJs(matcher.group(0), pl, airDrop));
        }
        if (airDrop != null)
            req = (airDrop.replaceInternalPlaceholder(req));
        req = Message.setPlaceholders(pl, req);
        return Boolean.parseBoolean(req);
    }

    public static String math(String req, @Nullable AirDrop airDrop, @Nullable Player pl) {
        String reqOld = req;
        if (airDrop != null)
            req = airDrop.replaceInternalPlaceholder(req);
        req = Message.setPlaceholders(pl, req);

        String mathExp = req.substring(req.indexOf("#") + 1, req.lastIndexOf("#"));
        String[] args = mathExp.split("\\s+");

        String result = "error";
        //  Double.parseDouble()
        try {
            if (args[1].equals("%"))
                result = (Double.parseDouble(args[0]) % Double.parseDouble(args[2])) + "";
            if (args[1].equals("+"))
                result = (Double.parseDouble(args[0]) + Double.parseDouble(args[2])) + "";
            if (args[1].equals("-"))
                result = (Double.parseDouble(args[0]) - Double.parseDouble(args[2])) + "";
            if (args[1].equals("/"))
                result = (Double.parseDouble(args[0]) / Double.parseDouble(args[2])) + "";
            if (args[1].equals("*"))
                result = (Double.parseDouble(args[0]) * Double.parseDouble(args[2])) + "";
            if (args[1].equals("=="))
                result = (Double.parseDouble(args[0]) == Double.parseDouble(args[2])) + "";
            if (args[1].equals(">"))
                result = (Double.parseDouble(args[0]) > Double.parseDouble(args[2])) + "";
            if (args[1].equals("<"))
                result = (Double.parseDouble(args[0]) < Double.parseDouble(args[2])) + "";
            if (args[1].equals(">="))
                result = (Double.parseDouble(args[0]) >= Double.parseDouble(args[2])) + "";
            if (args[1].equals("<="))
                result = (Double.parseDouble(args[0]) <= Double.parseDouble(args[2])) + "";
            if (args[1].equals("!="))
                result = (Double.parseDouble(args[0]) != Double.parseDouble(args[2])) + "";
            //   Message.error(String.format(Config.getMessage("numerical-check-unknown-operator"), args[1], reqOld));
            //  result = "0.5";
        } catch (NumberFormatException e) {
            Message.error(String.format(Config.getMessage("numeric_check-error-not-a-number"), reqOld));
            Message.error(e.getLocalizedMessage()); //.replace("For input string:", "Не число:")
            result = "0.5";
        } catch (ArrayIndexOutOfBoundsException e) {
            Message.error(String.format(Config.getMessage("numerical_check-few-arguments"), reqOld));
            result = "0.5";
        } catch (Exception e) {
            Message.error(String.format(Config.getMessage("numerical-check-unknown-error"), reqOld));
            e.printStackTrace();
            result = "0.5";
        }
        return req.replace("[math#" + mathExp + "#]", result);
    }

    private boolean NUMERICAL_CHECK(String checkId, String req, @Nullable AirDrop airDrop, @Nullable Player pl) {
        Pattern pattern = Pattern.compile("\\{\\[RUN_JS=.*?\\}");
        Matcher matcher = pattern.matcher(req);
        if (matcher.find()) {
            req = replaceText(req, runJs(matcher.group(0), pl, airDrop));
        }
        if (airDrop != null)
            req = airDrop.replaceInternalPlaceholder(req);
        req = Message.setPlaceholders(pl, req);
        if (req.contains("[math#"))
            req = InternalListener.math(req, airDrop, pl);
        String[] args = req.split(" ");
        try {
            if (args[1].equals("%")) {
                if (args[3].equals("=="))
                    return Double.parseDouble(args[0]) % Double.parseDouble(args[2]) == Double.parseDouble(args[4]);
                if (args[3].equals(">"))
                    return Double.parseDouble(args[0]) % Double.parseDouble(args[2]) > Double.parseDouble(args[4]);
                if (args[3].equals("<"))
                    return Double.parseDouble(args[0]) % Double.parseDouble(args[2]) < Double.parseDouble(args[4]);
                if (args[3].equals(">="))
                    return Double.parseDouble(args[0]) % Double.parseDouble(args[2]) >= Double.parseDouble(args[4]);
                if (args[3].equals("<="))
                    return Double.parseDouble(args[0]) % Double.parseDouble(args[2]) <= Double.parseDouble(args[4]);
                if (args[3].equals("!="))
                    return Double.parseDouble(args[0]) % Double.parseDouble(args[2]) != Double.parseDouble(args[4]);
                Message.error(String.format(Config.getMessage("numerical-check-unknown-operator"), args[3], checkId));
                return false;
            }
            if (args[1].equals("=="))
                return Double.parseDouble(args[0]) == Double.parseDouble(args[2]);
            if (args[1].equals(">"))
                return Double.parseDouble(args[0]) > Double.parseDouble(args[2]);
            if (args[1].equals("<"))
                return Double.parseDouble(args[0]) < Double.parseDouble(args[2]);
            if (args[1].equals(">="))
                return Double.parseDouble(args[0]) >= Double.parseDouble(args[2]);
            if (args[1].equals("<="))
                return Double.parseDouble(args[0]) <= Double.parseDouble(args[2]);
            if (args[1].equals("!="))
                return Double.parseDouble(args[0]) != Double.parseDouble(args[2]);
            Message.error(String.format(Config.getMessage("numerical-check-unknown-operator"), args[1], checkId));
            return false;
        } catch (NumberFormatException e) {
            Message.error(String.format(Config.getMessage("numeric_check-error-not-a-number"), checkId));
            Message.error(e.getLocalizedMessage()); //.replace("For input string:", "Не число:")
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            Message.error(String.format(Config.getMessage("numerical_check-few-arguments"), checkId));
            return false;
        } catch (Exception e) {
            Message.error(String.format(Config.getMessage("numerical-check-unknown-error"), checkId));
            e.printStackTrace();
            return false;
        }

    }

    private boolean STRING_CHECK(String req, @Nullable AirDrop airDrop, @Nullable Player pl) {
        Pattern pattern = Pattern.compile("\\{\\[RUN_JS=.*?\\}");
        Matcher matcher = pattern.matcher(req);
        if (matcher.find()) {
            req = replaceText(req, runJs(matcher.group(0), pl, airDrop));
        }
        if (airDrop != null)
            req = airDrop.replaceInternalPlaceholder(req);
        req = Message.setPlaceholders(pl, req);
        if (req.contains("[math#"))
            req = InternalListener.math(req, airDrop, pl);
        if (req.contains("{player-get") && pl != null)
            req = ExecuteCommands.setPlayerPlaceholder(pl, req);
        String[] args = req.split(" ");
        if (args[1].equals("=="))
            return args[0].equals(args[2]);
        if (args[1].equals("!="))
            return !args[0].equals(args[2]);
        if (args[1].equals("contains"))
            return args[0].contains(args[2]);
        if (args[1].equals("!contains"))
            return !args[0].contains(args[2]);
        return false;
    }

    public String[] getCommands() {
        return commands;
    }

    public String[] getDenyCommands() {
        return denyCommands;
    }

    public String getDescription() {
        return description;
    }

    public String runJs(String command, @Nullable Player pl, @Nullable AirDrop airDrop) {

        try {
            if (command.contains("[RUN_JS=")) {
                command = command.replace(" ", "");
                String jsName = command.split("RUN_JS=")[1].split("]")[0];
                if (!Config.scripts.containsKey(jsName)) {
                    Message.error(String.format("%s Неизвестный скрипт!", jsName));
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
                        else if (str.split("=")[1].equals("airDrop"))
                            scriptParam = airDrop;
                        else scriptParam = str.split("=")[1];
                        map.put(str.split("=")[0], scriptParam);
                    }
                }
                org.by1337.bairdrop.scripts.Manager manager = new Manager();
                String out = (String) manager.runJsScript(jsName, map);

                return out;
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            e.printStackTrace();
        }

        return "js error";
    }

    public String replaceText(String input, String to) {
        String regex = "\\{\\[RUN_JS=.*?\\]\\s*(param\\(player=player\\))?(\\-scheduler)?\\}";
        return input.replaceAll(regex, to);
    }

}