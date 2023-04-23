package org.by1337.bairdrop.Listeners;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.effect.util.RGBHelper;
import org.by1337.bairdrop.util.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Compass implements Listener {
    public static ItemStack item = null;
    double maxDistance = 10;
    double size = 3;
    double distanceBetweenPoints = 0.2;
    Color color = Color.RED;
    String messageNotFound = "&cError";
    HashMap<UUID, Long> cd = new HashMap<>();
    @EventHandler
    public void onClick(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR){
            Player pl = e.getPlayer();
            ItemStack itemStack = e.getPlayer().getInventory().getItemInMainHand();
            if(itemStack.getItemMeta() == null) return;
            boolean isCompass = itemStack.getItemMeta().getPersistentDataContainer().has(NamespacedKey.fromString("compass_bairdrop"), PersistentDataType.STRING);
            if(!isCompass) return;
            if(cd.getOrDefault(pl.getUniqueId(), 0L) - System.currentTimeMillis() > 0)
                return;
            cd.put(pl.getUniqueId(), System.currentTimeMillis() + 1500);
            AirDrop airDrop = null;
            int dist = 0;
            for(AirDrop air : BAirDrop.airDrops.values()){
                if(!air.isAirDropStarted()) continue;
                if(!air.getAirLoc().getWorld().equals(pl.getWorld())) continue;
                if(dist > pl.getPlayer().getLocation().distance(air.getAirLoc()) || airDrop == null){
                    dist = (int) pl.getPlayer().getLocation().distance(air.getAirLoc());
                    airDrop = air;
                }
            }
            if(airDrop == null){
                Message.sendMsg(pl, messageNotFound);
                if(itemStack.getType() == Material.COMPASS){
                    CompassMeta compassMeta = (CompassMeta) itemStack.getItemMeta();
                    Location targetLocation = new Location(pl.getWorld(), 0, 0, 0);
                    compassMeta.setLodestone(targetLocation);
                    compassMeta.setLodestoneTracked(true);
                    itemStack.setItemMeta(compassMeta);
                }
            }else {
                AirDrop finalAirDrop = airDrop;
                if(itemStack.getType() == Material.COMPASS){
                    CompassMeta compassMeta = (CompassMeta) itemStack.getItemMeta();
                    compassMeta.setLodestone(finalAirDrop.getAirLoc().clone());
                    compassMeta.setLodestoneTracked(false);
                    itemStack.setItemMeta(compassMeta);
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Location playerLocation = pl.getLocation().clone().add(0, 1.5, 0);
                        Location targetLocation = finalAirDrop.getAirLoc().clone();

                        Vector direction = targetLocation.toVector().subtract(playerLocation.toVector()).normalize();
                        double distance = 0.0;

                        while (distance < maxDistance) {
                            Location pointLocation = playerLocation.clone().add(direction.clone().multiply(distance));
                            pl.spawnParticle(Particle.REDSTONE, pointLocation, 0, new Particle.DustOptions(color, (float) size));
                            distance += distanceBetweenPoints;
                        }
                        cancel();
                    }
                }.runTaskTimer(BAirDrop.instance, 1L, 1L);
            }
        }
    }
    public void loadItem(){
        ItemStack itemStack = new ItemStack(Material.DIRT);
        String mat = Objects.requireNonNull(BAirDrop.instance.getConfig().getString("compass.material"));
        String name = Objects.requireNonNull(BAirDrop.instance.getConfig().getString("compass.item-name"));
        String nbt = BAirDrop.instance.getConfig().getString("compass.nbt");
        String messageNotFound = Objects.requireNonNull(BAirDrop.instance.getConfig().getString("compass.message-not-found"));
        List<String> lore = BAirDrop.instance.getConfig().getStringList("compass.item-lore");
        double lineMaxDistance = BAirDrop.instance.getConfig().getDouble("compass.line-max-distance");
        double particleStep = BAirDrop.instance.getConfig().getDouble("compass.particle-step");
        double size = BAirDrop.instance.getConfig().getDouble("compass.particle-size");
        Color color = RGBHelper.getColorWithRgb(Objects.requireNonNull(BAirDrop.instance.getConfig().getString("compass.particle-color")));
        itemStack = new ItemStack(Material.valueOf(mat));
        ItemMeta im = itemStack.getItemMeta();
        im.setDisplayName(Message.messageBuilder(name));
        if (nbt != null) im.setCustomModelData(Integer.parseInt(nbt.replace("{CustomModelData:", "").replace("}", "")));
        this.messageNotFound = messageNotFound;
        this.color = color;
        this.size = size;
        this.maxDistance = lineMaxDistance;
        this.distanceBetweenPoints = particleStep;
        lore.replaceAll(Message::messageBuilder);
        im.setLore(lore);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        im.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
        im.getPersistentDataContainer().set(NamespacedKey.fromString("compass_bairdrop"), PersistentDataType.STRING, "true");
        itemStack.setItemMeta(im);
        item = itemStack;
    }
    @Deprecated
    public static ItemStack getItem(){
        return null;
    }
}
