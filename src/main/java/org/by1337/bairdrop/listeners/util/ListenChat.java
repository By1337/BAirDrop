package org.by1337.bairdrop.listeners.util;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.menu.EditAirMenu;
import org.by1337.bairdrop.util.Message;

public class ListenChat implements Listener {
    public static ListenChat ListenChat = null;
    private final AirDrop airDrop;
    private final String changeNameString;

    private final Player pl;

    public void unReg(){
        HandlerList.unregisterAll(this);
        ListenChat = null;
    }
    public ListenChat(AirDrop airDrop, String changeNameString, Player pl) {
        this.airDrop = airDrop;
        this.changeNameString = changeNameString;
        this.pl = pl;
        ListenChat = this;
        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("edit-chat"));
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (e.getPlayer().equals(pl)) {
            if (e.getMessage().equalsIgnoreCase("отмена") || e.getMessage().equalsIgnoreCase("cancel")) {
                Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("edit-canceled"));
                HandlerList.unregisterAll(this);
                e.setCancelled(true);
                return;
            }
            if (changeNameString.equalsIgnoreCase("invname")) {
                if (airDrop.isAirDropStarted()){
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("stop-event-for-edit"));
                    e.setCancelled(true);
                    return;
                }
                airDrop.setInventoryTitle(e.getMessage());
                airDrop.save();
                Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("named-changed"), e.getMessage()));
            }
            if (changeNameString.equalsIgnoreCase("airname")) {
                if (airDrop.isAirDropStarted()){
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("stop-event-for-edit"));
                    e.setCancelled(true);
                    return;
                }
                airDrop.setDisplayName(e.getMessage());
                airDrop.save();
                Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("named-changed"), e.getMessage()));
            }
            try {
                if (changeNameString.equalsIgnoreCase("spawnmin")) {
                    if (airDrop.isAirDropStarted()){
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("stop-event-for-edit"));
                        e.setCancelled(true);
                        return;
                    }
                    int x = Integer.parseInt(e.getMessage());
                    airDrop.setSpawnRadiusMin(x);
                    airDrop.save();
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("min-spawn-changed"), e.getMessage()));
                }
                if (changeNameString.equalsIgnoreCase("spawnmax")) {
                    if (airDrop.isAirDropStarted()){
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("stop-event-for-edit"));
                        e.setCancelled(true);
                        return;
                    }
                    int x = Integer.parseInt(e.getMessage());
                    if(airDrop.getSpawnRadiusMin() >= x){
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("max-limit"));
                        e.setCancelled(true);
                        return;
                    }
                    airDrop.setSpawnRadiusMax(x);
                    airDrop.save();
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("max-spawn-changed"), e.getMessage()));
                }
                if (changeNameString.equalsIgnoreCase("airprotect")) {
                    if (airDrop.isAirDropStarted()){
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("stop-event-for-edit"));
                        e.setCancelled(true);
                        return;
                    }
                    int x = Integer.parseInt(e.getMessage());
                    airDrop.setRegionRadius(x);
                    airDrop.save();
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("protect-changed"), e.getMessage()));
                }
                if (changeNameString.equalsIgnoreCase("timetostart") || changeNameString.equalsIgnoreCase("timetostartcons")) {
                    if (airDrop.isAirDropStarted()){
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("stop-event-for-edit"));
                        e.setCancelled(true);
                        return;
                    }
                    int x = Integer.parseInt(e.getMessage());
                    airDrop.setTimeToStartCons(x);
                    airDrop.setTimeToStart(x * 60);
                    airDrop.save();
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("time-to-start-changed"), e.getMessage()));
                }
                if (changeNameString.equalsIgnoreCase("searchbeforestart") || changeNameString.equalsIgnoreCase("searchbeforestartcons")) {
                    if (airDrop.isAirDropStarted()){
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("stop-event-for-edit"));
                        e.setCancelled(true);
                        return;
                    }
                    int x = Integer.parseInt(e.getMessage());
                    airDrop.setSearchBeforeStartCons(x);
                    airDrop.setSearchBeforeStart(x * 60);
                    airDrop.save();
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("search-before-start-changed"), e.getMessage()));
                }
                if (changeNameString.equalsIgnoreCase("timetoopen") || changeNameString.equalsIgnoreCase("timetounlockcons")) {
                    if (airDrop.isAirDropStarted()){
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("stop-event-for-edit"));
                        e.setCancelled(true);
                        return;
                    }
                    int x = Integer.parseInt(e.getMessage());
                    airDrop.setTimeToUnlockCons(x);
                    airDrop.setTimeToOpen(x * 60);
                    airDrop.save();
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("time-to-open-changed"), e.getMessage()));
                }
                if (changeNameString.equalsIgnoreCase("timestop") || changeNameString.equalsIgnoreCase("timetostopcons")) {
                    if (airDrop.isAirDropStarted()){
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("stop-event-for-edit"));
                        e.setCancelled(true);
                        return;
                    }
                    int x = Integer.parseInt(e.getMessage());
                    airDrop.setTimeToStopCons(x);
                    airDrop.setTimeStop(x * 60);
                    airDrop.save();
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("time-to-stop-changed"), e.getMessage()));
                }
                if (changeNameString.equalsIgnoreCase("minonlineplayers")) {
                    if (airDrop.isAirDropStarted()){
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("stop-event-for-edit"));
                        e.setCancelled(true);
                        return;
                    }
                    int x = Integer.parseInt(e.getMessage());
                    airDrop.setMinPlayersToStart(x);
                    airDrop.save();
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("min-online-players-changed"), e.getMessage()));
                }
            } catch (NumberFormatException var3) {
                Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("isn-t-number"));
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);
            HandlerList.unregisterAll(this);
            new BukkitRunnable() {
                @Override
                public void run() {
                    EditAirMenu em = new EditAirMenu(airDrop);
                    airDrop.setEditAirMenu(em);
                    pl.openInventory(em.getInventory());
                    cancel();
                }
            }.runTaskTimer(BAirDrop.getInstance(), 1, 1);
        }
    }

}

