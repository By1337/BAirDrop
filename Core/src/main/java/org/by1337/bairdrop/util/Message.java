package org.by1337.bairdrop.util;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.plugin.java.JavaPlugin;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.lib.chat.ComponentBuilder;
import org.by1337.lib.chat.TellRaw;
import org.jetbrains.annotations.Nullable;


public class Message {
    private static final Pattern RAW_HEX_REGEX = Pattern.compile("&(#[a-f0-9]{6})", Pattern.CASE_INSENSITIVE);

    private static final Logger LOGGER = JavaPlugin.getPlugin(BAirDrop.class).getLogger();

    /**
     * Boss bars created with the [NEW_BOSSBAR] command
     */
    public static HashMap<String, BossBar> bossBars = new HashMap<>();

    /**
     * Sends a message to the player if they are not null, otherwise logs it to the console
     *
     * @param sender The CommandSender to send the message to
     * @param msg    The message
     */
    public static void sendMsg(CommandSender sender, String msg) {
        msg = setPlaceholders(sender instanceof OfflinePlayer ? (OfflinePlayer) sender : null, msg);
        msg = msg.replace("\\n", "/n");
        if (msg.contains("/n")) {
            for (String str : msg.split("/n"))
                sender.sendMessage(messageBuilder(str));
        } else {
            sender.sendMessage(messageBuilder(msg));
        }
    }

    public static void sendRawMsg(CommandSender sender, ComponentBuilder msg) {
        if (sender instanceof Player player)
            TellRaw.tell(msg.build(), player);
        else
            logger(msg.build());
    }

    public static void sendRawMsg(CommandSender sender, String msg) {
        if (sender instanceof Player player)
            TellRaw.tell(msg, player);
        else
            logger(msg);
    }


    public static void sendMsg(CommandSender sender, String msg, Object... format) {
        msg = setPlaceholders(sender instanceof OfflinePlayer ? (OfflinePlayer) sender : null, msg);
        msg = msg.replace("\\n", "/n");
        if (msg.contains("/n")) {
            for (String str : msg.split("/n"))
                sender.sendMessage(messageBuilder(String.format(str, format)));
        } else {
            sender.sendMessage(messageBuilder(String.format(msg, format)));
        }
    }

    public static void sendMsg(CommandSender sender, Resource res, Object... format) {
        String msg = setPlaceholders(sender instanceof OfflinePlayer ? (OfflinePlayer) sender : null, res.getString());
        msg = msg.replace("\\n", "/n");
        if (msg.contains("/n")) {
            for (String str : msg.split("/n"))
                sender.sendMessage(messageBuilder(String.format(str, format)));
        } else {
            sender.sendMessage(messageBuilder(String.format(msg, format)));
        }
    }

    /**
     * Sends a debug message to the console
     *
     * @param msg      The debug message
     * @param logLevel The debug level
     */
    public static void debug(String msg, LogLevel logLevel) {
        if (BAirDrop.logLevel.getLvl() >= logLevel.getLvl()) {
            if (BAirDrop.getInstance().getConfig().getBoolean("debug")) {
                logger("&7[DEBUG] " + msg);
            }
        }
    }

    /**
     * use debug(String msg, LogLevel logLevel)
     * or logger(String msg)
     */
    @Deprecated
    public static void debug(String msg) {
        System.out.println(msg);
    }

    /**
     * This method should only be used for debugging
     *
     * @param msg The message
     */
    public static void devDebug(String msg) {
        logger(msg);
    }

    /**
     * Sends a log
     *
     * @param msg The message
     */
    public static void logger(String msg) {
        LOGGER.log(Level.INFO, messageBuilder(msg));
    }

    /**
     * Sends an error message to the console
     * Also sends the message to all op players
     *
     * @param msg The message
     */
    public static void error(String msg) {
        LOGGER.log(Level.SEVERE, messageBuilder(msg));
    }
    public static void error(String msg, Object... objects) {
        LOGGER.log(Level.SEVERE, messageBuilder(String.format(msg, objects)));
    }

    /**
     * Sends a message to all op players
     *
     * @param msg The message
     */
    public static void sendAllOp(String msg) {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (pl.isOp()) {
                sendMsg(pl, "&c" + messageBuilder(msg));
            }
        }
    }

    /**
     * Sends a warning message
     *
     * @param msg The message
     */
    public static void warning(String msg) {
        LOGGER.log(Level.WARNING, messageBuilder(msg));
    }

    /**
     * Sends an ActionBar message to the player
     *
     * @param pl  The player to send the message to
     * @param msg The message
     */
    public static void sendActionBar(Player pl, String msg) {
        pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(messageBuilder(msg)));
    }

    /**
     * Sends an ActionBar message to all players
     *
     * @param msg The message
     */
    public static void sendAllActionBar(String msg) {
        for (Player pl : Bukkit.getOnlinePlayers())
            pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(messageBuilder(msg)));
    }

    /**
     * Отправляет ActionBar сообщение всем op игрокам
     *
     * @param msg Сообщение
     * @deprecated Устарел из-за без полезности
     */
    @Deprecated
    public static void sendAllOpActionBar(String msg) {
        Message.logger(msg);
        for (Player pl : Bukkit.getOnlinePlayers())
            if (pl.isOp()) {
                pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(messageBuilder(msg)));
            }
    }

    /**
     * Sends a title message to the player
     *
     * @param pl       The player to send the message to
     * @param title    The title message
     * @param subTitle The subtitle message
     * @param fadeIn   The fade-in time for the title
     * @param stay     The duration the title will stay on screen
     * @param fadeOut  The fade-out time for the title
     */
    public static void sendTitle(Player pl, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        pl.sendTitle(messageBuilder(title), messageBuilder(subTitle), fadeIn, stay, fadeOut);
    }

    /**
     * Sends a title message to the player with pre-defined fadeIn, stay, and fadeOut values
     *
     * @param pl       The player
     * @param title    The title message
     * @param subTitle The subtitle message
     */
    public static void sendTitle(Player pl, String title, String subTitle) {
        pl.sendTitle(messageBuilder(title), messageBuilder(subTitle), 10, 20, 10);
    }

    /**
     * Sends a title message to all players
     *
     * @param title    The title message
     * @param subTitle The subtitle message
     */
    public static void sendAllTitle(String title, String subTitle) {
        for (Player pl : Bukkit.getOnlinePlayers())
            pl.sendTitle(messageBuilder(title), messageBuilder(subTitle), 20, 30, 20);
    }

    /**
     * Applies colors to the message
     *
     * @param msg The raw message
     * @return The message with applied colors
     */
    public static String messageBuilder(String msg) {
        if (msg == null)
            return "";
        msg = setPlaceholders(null, msg);

        return hex(msg);
    }

    /**
     * Sends a message to all players
     *
     * @param msg The message
     */
    public static void sendAllMsg(String msg) {
        for (Player pl : Bukkit.getOnlinePlayers())
            sendMsg(pl, msg);

    }

    /**
     * Applies placeholders to the message
     *
     * @param player The player
     * @param string The message
     * @return The message with applied placeholders
     */
    public static String setPlaceholders(@Nullable Player player, String string) {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            try {
                return PlaceholderAPI.setPlaceholders(player, string.replace("&", "§")).replace("§", "&");
            } catch (Exception var3) {
            }
        }
        return string;
    }

    /**
     * Applies placeholders to the message
     *
     * @param player The player
     * @param string The message
     * @return The message with applied placeholders
     */
    public static String setPlaceholders(@Nullable OfflinePlayer player, String string) {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            try {
                return PlaceholderAPI.setPlaceholders(player, string.replace("&", "§")).replace("§", "&");
            } catch (Exception var3) {
            }
        }
        return string;
    }

    /**
     * Sends a sound to the player
     *
     * @param pl    The player
     * @param sound The sound as a string
     */
    public static void sendSound(Player pl, String sound) {
        try {
            pl.playSound(pl.getLocation(), Sound.valueOf(sound), 1, 1);
        } catch (IllegalArgumentException e) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-sound"), sound));
        }
    }

    /**
     * Sends a sound to the player
     *
     * @param pl    The player
     * @param sound The sound from the Sound enum
     * @see org.bukkit.Sound
     */
    public static void sendSound(Player pl, Sound sound) {
        pl.playSound(pl.getLocation(), sound, 1, 1);
    }

    /**
     * Sends a sound to all players
     *
     * @param sound The sound as a string
     */
    public static void sendAllSound(String sound) {
        try {
            for (Player pl : Bukkit.getOnlinePlayers())
                pl.playSound(pl.getLocation(), Sound.valueOf(sound), 1, 1);
        } catch (IllegalArgumentException e) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-sound"), sound));
        }
    }

    /**
     * Applies a hex color to the message
     *
     * @param message The raw message
     * @return The processed message
     */
    private static String hex(String message) {
        Matcher m = RAW_HEX_REGEX.matcher(message);
        while (m.find())
            message = message.replace(m.group(), ChatColor.of(m.group(1)).toString());
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}