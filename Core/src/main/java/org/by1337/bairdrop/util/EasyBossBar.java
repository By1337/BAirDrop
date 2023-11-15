package org.by1337.bairdrop.util;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.lang.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasyBossBar {
    private static final Resource NOT_A_NUMBER = new Resource("not-a-number");
    private static final Resource OUT_OF_BOUNDS = new Resource("easy-boss-bar.error.progress.out.of.bounds");
    private final BossBar bossBar;
    private int minRadius = -1;
    private int radius = -1;
    private String timer = null;
    private String consTimer = null;
    private BarColor barColor = BarColor.GREEN;
    private BarStyle barStyle = BarStyle.SEGMENTED_6;
    private final List<BarFlag> barFlags = new ArrayList<>();
    private String title = "&cTitle";
    private final Airdrop airDrop;
    private final String id;

    public EasyBossBar(Airdrop airDrop, String id) {
        this.id = id;
        this.airDrop = airDrop;
        bossBar = Bukkit.createBossBar(title, barColor, barStyle);
    }

    public void update() {
        bossBar.removeAll();
        if (radius == -1) {
            for (Player player : Bukkit.getOnlinePlayers()) bossBar.addPlayer(player);
        } else {
            if (airDrop.getAnyLoc() != null) {
                for (Entity entity : airDrop.getAnyLoc().getWorld().getNearbyEntities(airDrop.getAnyLoc(), radius, radius, radius)) {
                    if (entity instanceof Player player) {
                        if (player.getLocation().distance(airDrop.getAnyLoc()) > minRadius) {
                            bossBar.addPlayer(player);
                        }
                    }
                }
            }
        }

        if (timer != null && consTimer != null) {
            String tempTimer = airDrop.replace(timer);
            String tempConsTimer = airDrop.replace(consTimer);

            if (!isNum(tempConsTimer)) {
                OLDMessage.error(NOT_A_NUMBER.getString(), tempConsTimer);
            } else {
                if (!isNum(tempTimer)) {
                    OLDMessage.error(NOT_A_NUMBER.getString(), tempTimer);
                } else {
                    double progress = (double) Integer.parseInt(tempTimer) / Integer.parseInt(tempConsTimer);
                    if (progress > 1D || progress < 0D) {
                        OLDMessage.error(OUT_OF_BOUNDS.getString());
                    } else
                        bossBar.setProgress(progress);
                }
            }
        }
        bossBar.setColor(barColor);
        bossBar.setStyle(barStyle);
        bossBar.setTitle(OLDMessage.messageBuilder(airDrop.replace(title)));
    }


    public void execCommands(String commands) {
        //    commands.replace("], ", "],");
        String[] args = commands.split(",");
        for (String cmd : args) {
            if (cmd.contains("[minRadius=")) {
                String param = getParam(cmd);
                if (!isNum(param)) {
                    OLDMessage.error(NOT_A_NUMBER.getString(), param);
                    continue;
                }
                minRadius = Integer.parseInt(param);
                continue;
            }

            if (cmd.contains("[radius=")) {
                String param = getParam(cmd);
                if (!isNum(param)) {
                    OLDMessage.error(NOT_A_NUMBER.getString(), param);
                    continue;
                }
                radius = Integer.parseInt(param);
                continue;
            }
            if (cmd.contains("[timer=")) {
                String param = getParam(cmd);
                timer = param;
                consTimer = airDrop.replace(param);
                continue;
            }
            if (cmd.contains("[barColor=")) {
                String param = getParam(cmd);
                try {
                    barColor = BarColor.valueOf(param);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (cmd.contains("[barStyle=")) {
                String param = getParam(cmd);
                try {
                    barStyle = BarStyle.valueOf(param);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (cmd.contains("[title=")) {
                title = getParam(cmd);
                continue;
            }
            if (cmd.contains("[update]")) {
                update();
                continue;
            }
            if (cmd.contains("[remove]")) {
                org.by1337.bairdrop.airdrop.command.airdrop.impl.EasyBossBar.getEasyBossBarHashMap().remove(id);
                bossBar.removeAll();
                continue;
            }
        }
    }

    public static String getParam(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (sb.indexOf("=") != -1 && sb.indexOf("]") != -1) {
            return sb.substring(sb.indexOf("=") + 1, sb.indexOf("]"));
        }
        return sb.toString();
    }

    public static boolean isNum(String num) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(num);
        if (matcher.find()) {
            return matcher.group().equals(num);
        } else
            return false;
    }
}
