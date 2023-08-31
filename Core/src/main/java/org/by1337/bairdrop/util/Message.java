package org.by1337.bairdrop.util;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BossBar;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.by1337.bairdrop.BAirDrop;
import org.jetbrains.annotations.Nullable;


public class Message {
    private static final ConsoleCommandSender SENDER = Bukkit.getConsoleSender();
    private static final String AUTHOR = "&#a612cb&lB&#9a17d2&ly&#8d1bd9&l1&#8120e1&l3&#7424e8&l3&#6829ef&l7";
    private static final String prefixPlugin = "&#a600f4[&#a70bf5B&#a815f6A&#a920f7i&#aa2bf8r&#aa35f8d&#ab40f9r&#ac4bfao&#ad55fbp&#ae60fc]";
    private static final Pattern RAW_HEX_REGEX = Pattern.compile("&(#[a-f0-9]{6})", Pattern.CASE_INSENSITIVE);

    /**
     * Boss bars created with the [NEW_BOSSBAR] command
     */
    public static HashMap<String, BossBar> bossBars = new HashMap<>();

    /**
     * Sends a message to the player if they are not null, otherwise logs it to the console
     * @param pl The player to send the message to
     * @param msg The message
     */
    public static void sendMsg(@Nullable Player pl, String msg) {
        msg = setPlaceholders(pl, msg);
        msg = msg.replace("\\n", "/n");
        if (msg.contains("/n")) {
            if (pl == null) {
                for (String str : msg.split("/n"))
                    logger(str);
            } else if (pl.isOnline())
                for (String str : msg.split("/n"))
                    pl.sendMessage(messageBuilder(str));

        } else {
            if (pl == null)
                logger(msg);
            else
                pl.sendMessage(messageBuilder(msg));
        }
    }

    /**
     * Sends a debug message to the console
     * @param msg The debug message
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
        if (BAirDrop.getInstance().getConfig().getBoolean("debug"))
            logger("&7[&eDeprecated &7DEBUG] " + msg);
    }

    /**
     * This method should only be used for debugging
     * @param msg The message
     */
    public static void devDebug(String msg){
        logger(msg);
    }

    /**
     * Sends a log
     * @param msg The message
     */
    public static void logger(String msg) {
        SENDER.sendMessage(messageBuilder(msg));
    }

    /**
     * Sends an error message to the console
     * Also sends the message to all op players
     * @param msg The message
     */
    public static void error(String msg) {
        BAirDrop.getInstance().getLogger().log(Level.SEVERE, msg);
        sendAllOp(prefixPlugin + " &c" + msg);

    }

    /**
     * Sends a message to all op players
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
     * @param msg The message
     */
    public static void warning(String msg) {
        BAirDrop.getInstance().getLogger().warning(messageBuilder(msg));
    }

    /**
     * Sends an ActionBar message to the player
     * @param pl The player to send the message to
     * @param msg The message
     */
    public static void sendActionBar(Player pl, String msg) {
        pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(messageBuilder(msg)));
    }

    /**
     * Sends an ActionBar message to all players
     * @param msg The message
     */
    public static void sendAllActionBar(String msg) {
        for (Player pl : Bukkit.getOnlinePlayers())
            pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(messageBuilder(msg)));
    }

    /**
     * Отправляет ActionBar сообщение всем op игрокам
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
     * @param pl The player to send the message to
     * @param title The title message
     * @param subTitle The subtitle message
     * @param fadeIn The fade-in time for the title
     * @param stay The duration the title will stay on screen
     * @param fadeOut The fade-out time for the title
     */
    public static void sendTitle(Player pl, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        pl.sendTitle(messageBuilder(title), messageBuilder(subTitle), fadeIn, stay, fadeOut);
    }

    /**
     * Sends a title message to the player with pre-defined fadeIn, stay, and fadeOut values
     * @param pl The player
     * @param title The title message
     * @param subTitle The subtitle message
     */
    public static void sendTitle(Player pl, String title, String subTitle) {
        pl.sendTitle(messageBuilder(title), messageBuilder(subTitle), 10, 20, 10);
    }

    /**
     * Sends a title message to all players
     * @param title The title message
     * @param subTitle The subtitle message
     */
    public static void sendAllTitle(String title, String subTitle) {
        for (Player pl : Bukkit.getOnlinePlayers())
            pl.sendTitle(messageBuilder(title), messageBuilder(subTitle), 20, 30, 20);
    }

    /**
     * Applies colors to the message
     * @param msg The raw message
     * @return The message with applied colors
     */
    public static String messageBuilder(String msg) {
        if (msg == null)
            return "";
        String str = msg.replace("{PP}", prefixPlugin).replace("AU", AUTHOR);
        str = setPlaceholders(null, str);

        return hex(str);
    }

    /**
     * Sends a message to all players
     * @param msg The message
     */
    public static void sendAllMsg(String msg) {
        for (Player pl : Bukkit.getOnlinePlayers())
            sendMsg(pl, msg);

    }

    /**
     * Applies placeholders to the message
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
     * Sends a sound to the player
     * @param pl The player
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
     * @param pl The player
     * @param sound The sound from the Sound enum
     * @see org.bukkit.Sound
     */
    public static void sendSound(Player pl, Sound sound) {
        pl.playSound(pl.getLocation(), sound, 1, 1);
    }

    /**
     * Sends a sound to all players
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