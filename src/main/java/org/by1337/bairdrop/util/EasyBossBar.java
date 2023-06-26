package org.by1337.bairdrop.util;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.by1337.bairdrop.AirDrop;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EasyBossBar   {
    private BossBar bossBar;
    private int minRadius = -1;
    private int radius = -1;
    private String timer = null;
    private String consTimer = null;
    private BarColor barColor = BarColor.GREEN;
    private BarStyle barStyle = BarStyle.SEGMENTED_6;
    private List<BarFlag> barFlags = new ArrayList<>();
    private String title = "&cTitle";
    private final AirDrop airDrop;
    private final String id;

    public EasyBossBar(AirDrop airDrop, String id) {
        this.id = id;
        this.airDrop = airDrop;
        bossBar = Bukkit.createBossBar(title, barColor, barStyle);
    //    Bukkit.getPluginManager().registerEvents(this, BAirDrop.getInstance());
    }
    public void update(){
        bossBar.removeAll();
        if (radius == -1){
            for (Player player : Bukkit.getOnlinePlayers()) bossBar.addPlayer(player);
        }else {
            if (airDrop.getAnyLoc() != null){
                for (Entity entity : airDrop.getAnyLoc().getWorld().getNearbyEntities(airDrop.getAnyLoc(), radius, radius, radius)){
                    if (entity instanceof Player player){
                        if (player.getLocation().distance(airDrop.getAnyLoc()) > minRadius){
                            bossBar.addPlayer(player);
                        }
                    }
                }
            }
        }

        if (timer != null && consTimer != null){
            String tempTimer = airDrop.replaceInternalPlaceholder(timer);
            String tempConsTimer = airDrop.replaceInternalPlaceholder(consTimer);

            if (!isNum(tempTimer)){
                Message.error("Не число " + tempTimer);
            }else {
                if (!isNum(tempTimer)){
                    Message.error("Не число " + tempTimer);
                }else {
                    double progress = (double) Integer.parseInt(tempTimer) / Integer.parseInt(tempConsTimer);
                    if (progress > 1D || progress < 0D){
                        Message.error("Прогресс не может быть меньше нуля или больше чем 1");
                    }else
                        bossBar.setProgress(progress);
                }
            }
        }
        bossBar.setColor(barColor);
        bossBar.setStyle(barStyle);
        bossBar.setTitle(Message.messageBuilder(airDrop.replaceInternalPlaceholder(title)));
    }


    public void execCommands(String commands){
    //    commands.replace("], ", "],");
        String[] args = commands.split(",");
        for (String cmd : args){
            if (cmd.contains("[minRadius=")){
                String param = getParam(cmd);
                if (!isNum(param)) {
                    Message.error("Не число: " + param + " в " + cmd);
                    continue;
                }
                minRadius = Integer.parseInt(param);
                continue;
            }

            if (cmd.contains("[radius=")){
                String param = getParam(cmd);
                if (!isNum(param)) {
                    Message.error("Не число: " + param + " в " + cmd);
                    continue;
                }
                radius = Integer.parseInt(param);
                continue;
            }
            if (cmd.contains("[timer=")){
                String param = getParam(cmd);
                timer = param;
                consTimer = airDrop.replaceInternalPlaceholder(param);
                continue;
            }
            if (cmd.contains("[barColor=")){
                String param = getParam(cmd);
                try {
                    barColor = BarColor.valueOf(param);
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                }
                continue;
            }
            if (cmd.contains("[barStyle=")){
                String param = getParam(cmd);
                try {
                    barStyle = BarStyle.valueOf(param);
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                }
                continue;
            }
            if (cmd.contains("[title=")){
                title = getParam(cmd);
                continue;
            }
            if (cmd.contains("[update]")){
                update();
                continue;
            }
            if (cmd.contains("[remove]")){
              //  HandlerList.unregisterAll(this);
                ExecuteCommands.easyBossBarHashMap.remove(id);
                bossBar.removeAll();
               // update();
                continue;
            }
        }
    }
    public static String getParam(String str){
        StringBuilder sb = new StringBuilder(str);
        if (sb.indexOf("=") != -1 && sb.indexOf("]") != -1){
            return sb.substring(sb.indexOf("=") +1, sb.indexOf("]"));
        }
        return sb.toString();
    }

    public static boolean isNum(String num){
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(num);
        if (matcher.find()) {
            return matcher.group().equals(num);
        }else
            return false;
    }
}
