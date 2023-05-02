package org.by1337.bairdrop.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.util.Message;


public class PlayerJoin implements Listener {


    @EventHandler
    public void onConnect(PlayerJoinEvent e){
        if(e.getPlayer().isOp()){
            if(!BAirDrop.version.equals(BAirDrop.currentVersion)){
                Message.logger(Config.getMessage("update"));
                Message.logger(String.format(Config.getMessage("update-2"), BAirDrop.currentVersion, BAirDrop.version));
            }
        }
    }

}
