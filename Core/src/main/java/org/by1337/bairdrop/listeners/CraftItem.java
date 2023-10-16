package org.by1337.bairdrop.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.util.CustomCraft;


public class CraftItem implements Listener {
    @EventHandler
    public void craft(CraftItemEvent e){
//        if(!BAirDrop.getInstance().getConfig().getBoolean("custom-crafts.enable")) return;
//        CustomCraft cc = CustomCraft.getCraftByResult(e.getRecipe().getResult());
//        if(cc == null) return;
//        for(String listener : cc.getCall()){
//            cc.callListeners(listener, (Player) e.getWhoClicked());
//        }
    }
}
