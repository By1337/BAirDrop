package org.by1337.bairdrop.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.util.AirManager;
import org.by1337.bairdrop.util.Events;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class InteractListener implements Listener {
    HashMap<UUID, Long> auntyDouble = new HashMap<>();
    @EventHandler
    public void PlayerClick(PlayerInteractEvent e) {
        Player pl = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || BAirDrop.instance.getConfig().getBoolean("geyser")) {
            AirDrop airDrop = AirManager.getAirDropForLocation(Objects.requireNonNull(e.getClickedBlock()).getLocation());
            if(airDrop == null)
                return;
            if(auntyDouble.getOrDefault(e.getPlayer().getUniqueId(), 0L) > System.currentTimeMillis()){
                e.setCancelled(true);
                return;
            }else {
                auntyDouble.put(e.getPlayer().getUniqueId(), System.currentTimeMillis() + 20L);
            }
            if(!airDrop.isAirDropStarted()){
                e.setCancelled(true);
                return;
            }
            if(airDrop.isStartCountdownAfterClick() && !airDrop.isPressed()){
              //  Message.sendMsg(pl, Config.getMessage("activate"));
                airDrop.setPressed(true);
                airDrop.setTimeStop(airDrop.getTimeToStopCons() * 60);
                airDrop.event(Events.ACTIVATE, pl);
                e.setCancelled(true);
                return;
            }

            if(airDrop.isAirLocked()){
                airDrop.event(Events.CLICK_CLOSE, pl);
            }else {
                pl.openInventory(airDrop.getInv());
                airDrop.event(Events.CLICK_OPEN, pl);
                if(!airDrop.isItWasOpen())
                    airDrop.event(Events.FIRST_OPEN, pl);
                airDrop.setItWasOpen(true);
            }
            e.setCancelled(true);
        }
    }
}
