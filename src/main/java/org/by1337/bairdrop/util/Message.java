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
     * Босс-бары созданые командой [NEW_BOSSBAR]
     */
    public static HashMap<String, BossBar> bossBars = new HashMap<>();

    /**
     * Отправляет игроку сообщение если он не равен null иначе отправляет лог в консоль
     * @param pl Игрок, которому отправить сообщение
     * @param msg Сообщение
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
     * Отправляет debug сообщение в консоль
     * @param msg debug сообщение
     * @param logLevel Уровень debug
     */
    public static void debug(String msg, LogLevel logLevel) {
        if (BAirDrop.logLevel.getLvl() >= logLevel.getLvl()) {
            if (BAirDrop.getInstance().getConfig().getBoolean("debug")) {
                logger("&7[DEBUG] " + msg);
                BAirDrop.Log("&7[DEBUG] " + msg);
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
     * Отправляет лог
     * @param msg Сообщение
     */
    public static void logger(String msg) {
        SENDER.sendMessage(messageBuilder(msg));
    }

    /**
     * Отправляет сообщение ошибки в консоль
     * Также отправляет сообщение всем op игрокам
     * @param msg Сообщение
     */
    public static void error(String msg) {
        BAirDrop.getInstance().getLogger().log(Level.SEVERE, msg);
        sendAllOp(prefixPlugin + " &c" + msg);

    }

    /**
     * Отправляет всем op игрокам сообщение
     * @param msg Сообщение
     */
    public static void sendAllOp(String msg) {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (pl.isOp()) {
                sendMsg(pl, "&c" + messageBuilder(msg));
            }
        }
    }

    /**
     * Отправляет warning сообщение
     * @param msg Сообщение
     */
    public static void warning(String msg) {
        BAirDrop.getInstance().getLogger().warning(messageBuilder(msg));
    }

    /**
     * Отправляет ActionBar сообщение игроку
     * @param pl Игрок которому нужно отправить сообщение
     * @param msg Сообщение
     */
    public static void sendActionBar(Player pl, String msg) {
        pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(messageBuilder(msg)));
    }

    /**
     * Отправляет ActionBar сообщение всем игрокам
     * @param msg Сообщение
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
     * Отправляет title игроку
     * @param pl Игрок которому нужно отправить сообщение
     * @param title Сообщение
     * @param subTitle Суб-сообщение
     * @param fadeIn Время появления title
     * @param stay Время, которое продержится этот title
     * @param fadeOut Время исчезания title
     */
    public static void sendTitle(Player pl, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        pl.sendTitle(messageBuilder(title), messageBuilder(subTitle), fadeIn, stay, fadeOut);
    }

    /**
     * Отправляет title игроку с заранее заданными fadeIn, stay и fadeOut
     * @param pl Игрок
     * @param title Сообщение
     * @param subTitle суб-сообщение
     */
    public static void sendTitle(Player pl, String title, String subTitle) {
        pl.sendTitle(messageBuilder(title), messageBuilder(subTitle), 10, 20, 10);
    }

    /**
     * Отправляет title всем игрокам
     * @param title Сообщение
     * @param subTitle суб-сообщение
     */
    public static void sendAllTitle(String title, String subTitle) {
        for (Player pl : Bukkit.getOnlinePlayers())
            pl.sendTitle(messageBuilder(title), messageBuilder(subTitle), 20, 30, 20);
    }

    /**
     * Применяет цвета к сообщению
     * @param msg Необработанное сообщение
     * @return Сообщение с применёнными цветами
     */
    public static String messageBuilder(String msg) {
        if (msg == null)
            return "";
        String str = msg.replace("{PP}", prefixPlugin).replace("AU", AUTHOR);
        str = setPlaceholders(null, str);

        return hex(str);
    }

    /**
     * Отправляет сообщение всем игрокам
     * @param msg Сообщение
     */
    public static void sendAllMsg(String msg) {
        for (Player pl : Bukkit.getOnlinePlayers())
            sendMsg(pl, msg);

    }

    /**
     * Применяет placeholders к сообщению
     * @param player Игрок
     * @param string Сообщение
     * @return Сообщение с применёнными placeholders
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
     * Отправляет звук игроку
     * @param pl Игрок
     * @param sound Звук в виде строки
     */
    public static void sendSound(Player pl, String sound) {
        try {
            pl.playSound(pl.getLocation(), Sound.valueOf(sound), 1, 1);
        } catch (IllegalArgumentException e) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-sound"), sound));
        }
    }

    /**
     * Отправляет звук игроку
     * @param pl Игрок
     * @param sound Отправляет звук из enum Sound
     * @see org.bukkit.Sound
     */
    public static void sendSound(Player pl, Sound sound) {
        pl.playSound(pl.getLocation(), sound, 1, 1);
    }

    /**
     * Отправляет звук всем игрокам
     * @param sound Звук в виде строки
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
     * Применяет hex цвет к сообщению
     * @param message raw message
     * @return обработанное сообщение
     */
    private static String hex(String message) {
        Matcher m = RAW_HEX_REGEX.matcher(message);
        while (m.find())
            message = message.replace(m.group(), ChatColor.of(m.group(1)).toString());
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}