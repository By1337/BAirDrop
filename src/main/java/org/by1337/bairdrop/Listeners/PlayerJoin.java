package org.by1337.bairdrop.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.util.Message;


public class PlayerJoin implements Listener {


    @EventHandler
    public void onConnect(PlayerJoinEvent e){
        if(e.getPlayer().isOp()){
            if(!BAirDrop.version.equals(BAirDrop.currentVersion)){
                Message.sendMsg(e.getPlayer(), "{PP} &fВышла новая версия плагина!");
                Message.sendMsg(e.getPlayer(), "{PP} &fТекущая версия " + BAirDrop.currentVersion + " новая версия " + BAirDrop.version);
            }
        }
    }

}
