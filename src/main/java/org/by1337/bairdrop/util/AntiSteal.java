package org.by1337.bairdrop.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.customListeners.CustomEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AntiSteal implements Listener {
    private Map<UUID, ChestStealData> chestStealDataMap = new HashMap<>();
    private final AirDrop airDrop;

    public AntiSteal(AirDrop airDrop) {
        this.airDrop = airDrop;
        Bukkit.getServer().getPluginManager().registerEvents(this, BAirDrop.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(airDrop.getInventory())) {
            if (event.getCurrentItem() == null) return;
            Player player = (Player) event.getWhoClicked();
            ChestStealData chestStealData = chestStealDataMap.getOrDefault(player.getUniqueId(), new ChestStealData());
            long currentTime = System.currentTimeMillis();

            if (chestStealData.getLastTime() == 0) {
                chestStealData.setLastTime(currentTime);
            } else {
                long lastActionTime = chestStealData.getLastTime();
                long interval = currentTime - lastActionTime;
                if (interval != 0) {
                    chestStealData.addTime(interval);
                }
                if (chestStealData.getLastSteal() != -1 && currentTime - chestStealData.getLastSteal() <= BAirDrop.getInstance().getConfig().getInt("anti-steal.сooldown")){
                  //  chestStealData.setLastSteal(currentTime);
                    event.setCancelled(true);
                    Message.sendMsg(player, BAirDrop.getConfigMessage().getMessage("anti-steal-limit-speed"));
                    if (event.getCurrentItem() != null){
                        player.setCooldown(event.getCurrentItem().getType(), Math.abs( BAirDrop.getInstance().getConfig().getInt("anti-steal.сooldown") / 50));
                    }
                }else {
                    chestStealData.setLastSteal(currentTime);
                }

                if (chestStealData.getWarnings() >= BAirDrop.getInstance().getConfig().getInt("anti-steal.max-warnings")) {
                    airDrop.notifyObservers(CustomEvent.PLAYER_STEAL, player);
                  //  player.kickPlayer("Вы забираете предметы из сундука слишком быстро!");
                    chestStealData.reset();
                    event.setCancelled(true);
                } else {
                    chestStealData.setLastTime(currentTime);
                }
            }
            chestStealDataMap.put(player.getUniqueId(), chestStealData);
        }
    }
    public void unregister(){
        HandlerList.unregisterAll(this);
    }
}
