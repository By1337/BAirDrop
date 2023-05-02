package org.by1337.bairdrop.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.by1337.bairdrop.util.LocationGeneration.getSettings;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.api.event.AirDropSummonerEvent;

public class Summoner implements Listener {
    HashMap<String, SummonerItem> items = new HashMap<>();
    HashMap<UUID, Long> auntyDouble = new HashMap<>();
    HashMap<UUID, Long> cooldownPlayers = new HashMap<>();
    int cooldown;

    public void Load() {
        items.clear();
        cooldown = BAirDrop.getInstance().getConfig().getInt("summoner-сooldown");
        if (BAirDrop.getInstance().getConfig().getConfigurationSection("summoner") == null)
            return;
        for (String key : BAirDrop.getInstance().getConfig().getConfigurationSection("summoner").getKeys(false)) {
            ItemStack item = new ItemStack(Material.DIRT);

            String material = Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("summoner.%s.material", key)));
            String nbt = BAirDrop.getInstance().getConfig().getString(String.format("summoner.%s.nbt", key));
            String name = Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("summoner.%s.name", key)));
            String airdrop = Objects.requireNonNull(BAirDrop.getInstance().getConfig().getString(String.format("summoner.%s.airdrop", key)));
            List<String> lore = BAirDrop.getInstance().getConfig().getStringList(String.format("summoner.%s.lore", key));
            List<String> call = BAirDrop.getInstance().getConfig().getStringList(String.format("summoner.%s.call", key));
            boolean clone = BAirDrop.getInstance().getConfig().getBoolean(String.format("summoner.%s.clone", key));
            boolean usePlayerLocation = BAirDrop.getInstance().getConfig().getBoolean(String.format("summoner.%s.use-player-location", key));
            boolean flatnessCheck = BAirDrop.getInstance().getConfig().getBoolean(String.format("summoner.%s.flatness-check", key));
            boolean checkUpBlocks = BAirDrop.getInstance().getConfig().getBoolean(String.format("summoner.%s.check-up-blocks", key));
            boolean ignoreRegion = BAirDrop.getInstance().getConfig().getBoolean(String.format("summoner.%s.ignore-region", key));

            try {
                if (material.contains("basehead-"))
                    item = BaseHeadHook.getItem(material);
                else item.setType(Material.valueOf(material));
            }catch (IllegalArgumentException e){
                Message.error(String.format("Ошибка загрузки %s предмета призыва", key));
                continue;
            }


            if (nbt != null) {
                ItemMeta im = item.getItemMeta();
                im.setCustomModelData(Integer.parseInt(nbt.replace("{CustomModelData:", "").replace("}", "")));
                item.setItemMeta(im);
            }
            ItemMeta im = item.getItemMeta();
            im.setDisplayName(Message.messageBuilder(name));
            lore.replaceAll(Message::messageBuilder);
            im.setLore(lore);
            im.getPersistentDataContainer().set(NamespacedKey.fromString("summoner"), PersistentDataType.STRING, key);
            im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
          //  im.addEnchant(Enchantment.ARROW_DAMAGE)
            item.setItemMeta(im);
            items.put(key, new SummonerItem(item, airdrop, clone, usePlayerLocation, flatnessCheck, checkUpBlocks, call, ignoreRegion));
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || BAirDrop.getInstance().getConfig().getBoolean("geyser")) {
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            ItemMeta im = item.getItemMeta();
            if (im == null) return;
            String key = im.getPersistentDataContainer().get(NamespacedKey.fromString("summoner"), PersistentDataType.STRING);
            if (key == null) return;
            if (auntyDouble.getOrDefault(e.getPlayer().getUniqueId(), 0L) > System.currentTimeMillis()) {
                e.setCancelled(true);
                return;
            } else {
                auntyDouble.put(e.getPlayer().getUniqueId(), System.currentTimeMillis() + 20L);
            }
            if (items.containsKey(key)) {
                if(!e.getPlayer().isOp()){
                    if(!e.getPlayer().hasCooldown(items.get(key).item.getType()) && cooldownPlayers.getOrDefault(e.getPlayer().getUniqueId(),  System.currentTimeMillis() / 50) - (System.currentTimeMillis() / 50) > 0){
                        int cooldown = (int) (cooldownPlayers.get(e.getPlayer().getUniqueId()) - (int) (System.currentTimeMillis() / 50));
                        Message.sendMsg(e.getPlayer(), String.format(Config.getMessage("summoner-limit"), cooldown / 20));
                        e.getPlayer().setCooldown(items.get(key).item.getType(), cooldown);
                        return;
                    }else if(e.getPlayer().hasCooldown(items.get(key).item.getType())){
                        int cooldown = e.getPlayer().getCooldown(items.get(key).item.getType());
                        Message.sendMsg(e.getPlayer(), String.format(Config.getMessage("summoner-limit"), cooldown / 20));
                        return;
                    }
                }
                AirDrop airDrop = items.get(key).start(e.getClickedBlock().getLocation(), e.getPlayer());
                if (airDrop != null) {
                    if (items.get(key).usePlayerLocation) {
                        airDrop.setAirLoc(e.getClickedBlock().getLocation().add(0, 0, 0).add(
                                getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.x", LocationGeneration.getWorldKeyByWorld(e.getClickedBlock().getWorld()))),
                                getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.y", LocationGeneration.getWorldKeyByWorld(e.getClickedBlock().getWorld()))),
                                getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.z", LocationGeneration.getWorldKeyByWorld(e.getClickedBlock().getWorld())))));

                        airDrop.setFutureLocation(e.getClickedBlock().getLocation().add(0, 0, 0).add(
                                getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.x", LocationGeneration.getWorldKeyByWorld(e.getClickedBlock().getWorld()))),
                                getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.y", LocationGeneration.getWorldKeyByWorld(e.getClickedBlock().getWorld()))),
                                getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.z", LocationGeneration.getWorldKeyByWorld(e.getClickedBlock().getWorld())))));
                        airDrop.setUsePlayerLocation(true);
                    }
                    BAirDrop.airDrops.put(airDrop.getAirId(), airDrop);
                    e.getPlayer().setCooldown(items.get(key).item.getType(), cooldown);
                    cooldownPlayers.put(e.getPlayer().getUniqueId(), (System.currentTimeMillis() / 50 + cooldown));
                    AirDropSummonerEvent airDropSummonerEvent = new AirDropSummonerEvent(airDrop, e.getPlayer());
                    Bukkit.getServer().getPluginManager().callEvent(airDropSummonerEvent);
                    if(airDropSummonerEvent.isCancelled())
                        return;
                    airDrop.event(Event.SUMMONER, e.getPlayer());
                    if (item.getAmount() > 1) {
                        item.setAmount(item.getAmount() - 1);

                        e.getPlayer().getInventory().setItemInMainHand(item);
                    } else
                        e.getPlayer().getInventory().removeItem(item);
                }
                e.setCancelled(true);


            }
        }
    }

    public HashMap<String, SummonerItem> getItems() {
        return items;
    }
}
