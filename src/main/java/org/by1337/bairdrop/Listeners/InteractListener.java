package org.by1337.bairdrop.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.api.event.AirDropOpenEvent;
import org.by1337.bairdrop.util.AirManager;
import org.by1337.bairdrop.util.Event;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class InteractListener implements Listener {
    HashMap<UUID, Long> auntyDouble = new HashMap<>();
    @EventHandler
    public void PlayerClick(PlayerInteractEvent e) {
        Player pl = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || BAirDrop.getInstance().getConfig().getBoolean("geyser")) {
            AirDrop airDrop = AirManager.getAirDropForLocation(Objects.requireNonNull(e.getClickedBlock()).getLocation());
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
            if(airDrop.isStartCountdownAfterClick() && !airDrop.isPressed()){
                airDrop.setPressed(true);
                airDrop.setTimeStop(airDrop.getTimeToStopCons() * 60);
                airDrop.event(Event.ACTIVATE, pl);
                return;
            }

            if(airDrop.isAirLocked()){
                airDrop.event(Event.CLICK_CLOSE, pl);
            }else {
                AirDropOpenEvent airDropOpenEvent = new AirDropOpenEvent(airDrop, pl);
                Bukkit.getServer().getPluginManager().callEvent(airDropOpenEvent);
                if(airDropOpenEvent.isCancelled())
                    return;
                pl.openInventory(airDrop.getInv());
                airDrop.event(Event.CLICK_OPEN, pl);
                if(!airDrop.isItWasOpen()) {
                    airDrop.event(Event.FIRST_OPEN, pl);
                }
                airDrop.setItWasOpen(true);
            }
        }
    }
}
