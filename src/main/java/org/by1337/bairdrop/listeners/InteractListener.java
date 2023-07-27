package org.by1337.bairdrop.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.api.event.AirDropOpenEvent;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.util.AirManager;
import org.by1337.bairdrop.util.Message;

import java.util.HashMap;
import java.util.UUID;

public class InteractListener implements Listener {
    private final HashMap<UUID, Long> auntyDouble = new HashMap<>();
    @EventHandler
    public void PlayerClick(PlayerInteractEvent e) {
        Player pl = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || BAirDrop.getInstance().getConfig().getBoolean("geyser") && e.getClickedBlock() != null) {
            AirDrop airDrop = AirManager.getAirDropForLocation(e.getClickedBlock().getLocation());

            if(airDrop == null)
                return;
            e.setCancelled(true);
            if(auntyDouble.getOrDefault(e.getPlayer().getUniqueId(), 0L) > System.currentTimeMillis()){
                return;
            }else {
                auntyDouble.put(e.getPlayer().getUniqueId(), System.currentTimeMillis() + 20L);
            }
            if(!airDrop.isAirDropStarted()){
                return;
            }
            if(airDrop.isStartCountdownAfterClick() && !airDrop.isActivated()){
                airDrop.setActivated(true);
                airDrop.setTimeStop(airDrop.getTimeToStopCons() * 60);
                airDrop.notifyObservers(CustomEvent.ACTIVATE, pl);
                return;
            }

            if(airDrop.isAirDropLocked()){
                airDrop.notifyObservers(CustomEvent.CLICK_CLOSE, pl);
            }else {
                AirDropOpenEvent airDropOpenEvent = new AirDropOpenEvent(airDrop, pl);
                Bukkit.getServer().getPluginManager().callEvent(airDropOpenEvent);
                if(airDropOpenEvent.isCancelled())
                    return;
                pl.openInventory(airDrop.getInventory());
                airDrop.notifyObservers(CustomEvent.CLICK_OPEN, pl);
                if(!airDrop.isWasOpened()) {
                    airDrop.notifyObservers(CustomEvent.FIRST_OPEN, pl);
                }
                airDrop.setWasOpened(true);
            }
        }
    }
}
