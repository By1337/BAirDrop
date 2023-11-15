package org.by1337.bairdrop.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.util.OLDMessage;

import java.util.Optional;
/**
 * A class for listening to player chat input for property editing.
 */
public class ListenChat implements Listener {

//    private final SetValueCallBack<String> callBack;
//    private final Player pl;
//
//    public ListenChat(SetValueCallBack<String> callBack, Player pl) {
//        this.callBack = callBack;
//        this.pl = pl;
//        Bukkit.getPluginManager().registerEvents(this, BAirDrop.getInstance());
//        OLDMessage.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("edit-chat"));
//    }
//    @EventHandler
//    public void onChat(AsyncPlayerChatEvent e) {
//        if (e.getPlayer().equals(pl)) {
//            e.setCancelled(true);
//            HandlerList.unregisterAll(this);
//            new BukkitRunnable() {
//                @Override
//                public void run() {
//                    if (e.getMessage().equalsIgnoreCase("отмена") || e.getMessage().equalsIgnoreCase("cancel")) {
//                        OLDMessage.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("edit-canceled"));
//                        callBack.result(Optional.empty());
//                        return;
//                    }
//                    callBack.result(Optional.of(e.getMessage()));
//                }
//            }.runTaskLater(BAirDrop.getInstance(), 0);
//        }
//    }
}

