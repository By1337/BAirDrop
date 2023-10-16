package command;

import org.bukkit.util.Vector;
import org.by1337.api.chat.Placeholderable;
import org.by1337.bairdrop.observer.Condition;
import org.by1337.api.hologaram.Hologram;
import org.by1337.api.lang.Lang;
import org.by1337.api.match.BMatch;
import org.by1337.api.util.CyclicList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ConditionTest {
    @Test
    public void run() {
        /*
         * if({time-to-start} == 0 && {start-countdown-after-click} == false){
         *   cmd {time-to-unlock} -= 1
         * }else if({time-to-start} == 0 && {activated} == true){
         *  cmd  {time-to-unlock} -= 1
         * }
         *
         *
         *
         * */
        Lang.loadIfNull();
        SetServer.setServer();

        int timeToStart = 0;
        int timeToUnlock = 120;
        int timeToEnd = 120;
        int minPlayersToStart = 3;
        String posX = "12";
        String posY = "?";
        String posZ = "?";
        boolean isSpawned = true;
        boolean startCountdownAfterClick = false;
        boolean activated = false;
        boolean timeStopEventMustGo = false;
        boolean opened = false;


        // if(true){/*if 1*/if(true){/*inner if 2*/}else{/*else inner if 2*/}}else if (true){/*else if 1*/}

        Placeholderable placeholderable = string -> {
            StringBuilder sb = new StringBuilder(string);

            while (true) {
                if (sb.indexOf("{time-to-start}") != -1) {
                    sb.replace(sb.indexOf("{time-to-start}"), sb.indexOf("{time-to-start}") + "{time-to-start}".length(), String.valueOf(timeToStart));
                    continue;
                }
                if (sb.indexOf("{is-spawned}") != -1) {
                    sb.replace(sb.indexOf("{is-spawned}"), sb.indexOf("{is-spawned}") + "{is-spawned}".length(), String.valueOf(isSpawned));
                    continue;
                }
                if (sb.indexOf("{start-countdown-after-click}") != -1) {
                    sb.replace(sb.indexOf("{start-countdown-after-click}"), sb.indexOf("{start-countdown-after-click}") + "{start-countdown-after-click}".length(), String.valueOf(startCountdownAfterClick));
                    continue;
                }
                if (sb.indexOf("{time-to-unlock}") != -1) {
                    sb.replace(sb.indexOf("{time-to-unlock}"), sb.indexOf("{time-to-unlock}") + "{time-to-unlock}".length(), String.valueOf(timeToUnlock));
                    continue;
                }
                if (sb.indexOf("{activated}") != -1) {
                    sb.replace(sb.indexOf("{activated}"), sb.indexOf("{activated}") + "{activated}".length(), String.valueOf(activated));
                    continue;
                }
                if (sb.indexOf("{time-stop-event-must-go}") != -1) {
                    sb.replace(sb.indexOf("{time-stop-event-must-go}"), sb.indexOf("{time-stop-event-must-go}") + "{time-stop-event-must-go}".length(), String.valueOf(timeStopEventMustGo));
                    continue;
                }
                if (sb.indexOf("{opened}") != -1) {
                    sb.replace(sb.indexOf("{opened}"), sb.indexOf("{opened}") + "{opened}".length(), String.valueOf(opened));
                    continue;
                }
                if (sb.indexOf("{time-to-end}") != -1) {
                    sb.replace(sb.indexOf("{time-to-end}"), sb.indexOf("{time-to-end}") + "{time-to-end}".length(), String.valueOf(timeToEnd));
                    continue;
                }
                if (sb.indexOf("{min-players-to-start}") != -1) {
                    sb.replace(sb.indexOf("{min-players-to-start}"), sb.indexOf("{min-players-to-start}") + "{min-players-to-start}".length(), String.valueOf(minPlayersToStart));
                    continue;
                }
//                if (sb.indexOf("spawned") != -1){
//                    sb.replace(sb.indexOf("spawned"), sb.indexOf("spawned") + "spawned".length(), String.valueOf(isSpawned));
//                    continue;
//                }
                if (sb.indexOf("{x}") != -1) {
                    sb.replace(sb.indexOf("{x}"), sb.indexOf("{x}") + 3, posX);
                    continue;
                }
                if (sb.indexOf("{y}") != -1) {
                    sb.replace(sb.indexOf("{y}"), sb.indexOf("{y}") + 3, posY);
                    continue;
                }
                if (sb.indexOf("{z}") != -1) {
                    sb.replace(sb.indexOf("{z}"), sb.indexOf("{z}") + 3, posZ);
                    continue;
                }
                break;
            }
            return sb.toString();
        };

        Condition condition = new Condition()
                .when(new Condition("{time-to-start} < 600 && {x} == ? && {y} == ? && {z} == ?")
                        .commands("[TRY_GEN_LOC]")
                )
                .when(new Condition("{time-to-start} > 0")
                        .commands("{time-to-start} -= 1")
                )
                .when(new Condition("{time-to-start} == 0")
                        .when(new Condition("{is-spawned} == false")
                                .commands("[CALL_EVENT] START_EVENT")
                                .else_(new Condition("{time-to-unlock} > 0")
                                        .when(new Condition("{start-countdown-after-click} == true")
                                                .when(new Condition("{activated} == true")
                                                        .commands("{time-to-unlock} -= 1")
                                                )
                                                .else_(new Condition()
                                                        .commands("{time-to-unlock} -= 1")
                                                )

                                        )
                                        .else_(new Condition("{opened} == false")
                                                .commands("[CALL_EVENT] UNLOCK_EVENT")
                                                .else_(new Condition("{time-to-end} > 0")
                                                        .when(new Condition("{time-stop-event-must-go} == true || {opened} == true")
                                                                .commands("{time-to-end} -= 1")
                                                        )
                                                        .else_(new Condition()
                                                                .commands("[CALL_EVENT] END_EVENT")
                                                        )
                                                )
                                        )
                                )
                        )


                );
        System.out.println(
                condition.getCommands(placeholderable, null)
        );


        CyclicList<List<String>> list = new CyclicList<>();
        list.add(List.of("&d-&3--------------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3-&d-&3-------------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3--&d-&3------------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3---&d-&3-----------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3----&d-&3----------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3-----&d-&3---------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3------&d-&3--------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3-------&d-&3-------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3--------&d-&3------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3---------&d-&3-----","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3----------&d-&3----","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3-----------&d-&3---","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3------------&d-&3--","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3-------------&d-&3-","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3--------------&d-","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3-------------&d-&3-","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3------------&d-&3--","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3-----------&d-&3---","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3----------&d-&3----","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3---------&d-&3-----","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3--------&d-&3------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3-------&d-&3-------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3------&d-&3--------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3-----&d-&3---------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3----&d-&3----------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3---&d-&3-----------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3--&d-&3------------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&3-&d-&3-------------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        list.add(List.of("&d-&3--------------","&7Test", "&7time-to-start={time-to-start}, time-to-unlock={time-to-unlock}, time-to-end={time-to-end}"));
        Hologram.Setting setting = new Hologram.Setting(5, 0.3, list, 20, new Vector(0.5, 0.7, 0.5));
         // System.out.println(condition.toString());

    //    System.out.println(setting.save().saveToString());

      //  Hologram.Setting setting1 = Hologram.Setting.load(setting.save());

      //  System.out.println(setting1.save().saveToString());

        // System.out.println(GsonFactory.create().toJson(setting));
    }

    @Test
    public void run1() {
        System.out.println(BMatch.match("match[63 == 63 && 63 == 63 && 63 == 63]"));

        Assert.assertEquals(BMatch.match("match[9999 < 60 * 10 || true == true]"), "1");

    }
}
