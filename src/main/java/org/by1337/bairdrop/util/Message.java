package org.by1337.bairdrop.util;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.advancement.Advancement;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.by1337.bairdrop.BAirDrop;
import org.jetbrains.annotations.Nullable;

import javax.swing.plaf.synth.SynthTextAreaUI;


public class Message {
    private static final ConsoleCommandSender SENDER = Bukkit.getConsoleSender();
    private static final String AUTHOR = "&#a612cb&lB&#9a17d2&ly&#8d1bd9&l1&#8120e1&l3&#7424e8&l3&#6829ef&l7";
    private static final String prefixPlugin = "&#a600f4[&#a70bf5B&#a815f6A&#a920f7i&#aa2bf8r&#aa35f8d&#ab40f9r&#ac4bfao&#ad55fbp&#ae60fc]";
    public static final Pattern RAW_HEX_REGEX = Pattern.compile("&(#[a-f0-9]{6})", Pattern.CASE_INSENSITIVE);
    public static HashMap<String, BossBar> bossBars = new HashMap<>();


    public static void sendMsg(@Nullable Player pl, String msg) {

        //var PotionEffectType = Packages.org.bukkit.potion.PotionEffectType;



        msg = setPlaceholders(pl, msg);
        msg = msg.replace("\\n", "/n");
        if (msg.contains("/n")) {
            if (pl == null) {
                for (String str : msg.split("/n"))
                    logger(str);
            } else if (pl.isOnline())
                for (String str : msg.split("/n"))
                    pl.sendMessage(messageBuilder(str));

        } else{
            if (pl == null)
                logger(msg);
            else
                pl.sendMessage(messageBuilder(msg));
        }
    }

    public static void debug(String msg) {
        if (BAirDrop.instance.getConfig().getBoolean("debug"))
            logger("&7[DEBUG] " + msg);
    }

    public static void logger(String msg) {
        SENDER.sendMessage(messageBuilder(msg));
    }

    public static void error(String msg) {
        BAirDrop.instance.getLogger().log(Level.SEVERE, msg);
        sendAllOp(msg.replace("{PP}", prefixPlugin + " &#cb2d3e[&#d1313dE&#d7363dR&#dd3a3cR&#e33e3bO&#e9433bR&#ef473a]&c"));

    }

    public static void sendAllOp(String msg) {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (pl.isOp()) {
                sendMsg(pl, "&c" + messageBuilder(msg));
            }
        }
    }

    public static void warning(String msg) {
        BAirDrop.instance.getLogger().warning(messageBuilder(msg));
    }

    public static void sendActionBar(Player pl, String msg) {
        pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(messageBuilder(msg)));
    }

    public static void sendAllActionBar(String msg) {
        for (Player pl : Bukkit.getOnlinePlayers())
            pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(messageBuilder(msg)));
    }

    public static void sendAllOpActionBar(String msg) {
        Message.logger(msg);
        for (Player pl : Bukkit.getOnlinePlayers())
            if (pl.isOp()) {
                pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(messageBuilder(msg)));
            }


    }

    public static void sendTitle(Player pl, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        pl.sendTitle(messageBuilder(title), messageBuilder(subTitle), fadeIn, stay, fadeOut);
    }

    public static void sendTitle(Player pl, String title, String subTitle) {
        pl.sendTitle(messageBuilder(title), messageBuilder(subTitle), 10, 20, 10);
    }

    public static void sendAllTitle(String title, String subTitle) {
        for (Player pl : Bukkit.getOnlinePlayers())
            pl.sendTitle(messageBuilder(title), messageBuilder(subTitle), 20, 30, 20);
    }

    public static String messageBuilder(String msg) {
        if (msg == null)
            return "";
        String str = msg.replace("{PP}", prefixPlugin).replace("AU", AUTHOR);
        str = setPlaceholders(null, str);
        return hex(str);
    }

    public static void sendAllMsg(String msg) {
        for (Player pl : Bukkit.getOnlinePlayers())
            sendMsg(pl, msg);

    }

    public static void sendAllNear(String msg, Location loc) {
        for (Entity entity : Objects.requireNonNull(loc.getWorld()).getNearbyEntities(loc, 10, 10, 10)) {
            if (entity instanceof Player) {
                sendMsg((Player) entity, msg);
            }
        }
    }

    public static String setPlaceholders(@Nullable Player player, String string) {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            try {
                return PlaceholderAPI.setPlaceholders(player, string.replace("&", "§")).replace("§", "&");
            } catch (Exception var3) {
            }
        }

        return string;
    }

    public static void sendSound(Player pl, String sound) {
        try {
            pl.playSound(pl.getLocation(), Sound.valueOf(sound), 1, 1);
        } catch (IllegalArgumentException e) {
            Message.error("Неизвестный звук! '" + sound + "'");
        }
    }
    public static void sendSound(Player pl, Sound sound) {
            pl.playSound(pl.getLocation(), sound, 1, 1);
    }
    public static void sendAllSound(String sound) {
        try {
            Sound.valueOf(sound);
            for (Player pl : Bukkit.getOnlinePlayers())
                sendSound(pl, sound);
        } catch (IllegalArgumentException e) {
            Message.error("Неизвестный звук! '" + sound + "'");
        }
    }

    private static String hex(String message) {
        Matcher m = RAW_HEX_REGEX.matcher(message);
        while (m.find())
            message = message.replace(m.group(), ChatColor.of(m.group(1)).toString());
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}