package org.by1337.bairdrop.summoner;

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

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.locationGenerator.GeneratorUtils;
import org.by1337.bairdrop.api.event.AirDropSummonerEvent;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.ItemUtil.BaseHeadHook;
import org.by1337.bairdrop.util.Message;

public class Summoner implements Listener {
    public final HashMap<String, SummonerItem> items = new HashMap<>();
    private final HashMap<UUID, Long> auntyDouble = new HashMap<>();
    private final HashMap<UUID, Long> cooldownPlayers = new HashMap<>();
    public int cooldown;

    /**
     * Loading items from the configuration
     */

    public void LoadSummoner() {
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

            int minY = BAirDrop.getInstance().getConfig().getInt(String.format("summoner.%s.min-y", key));
            int maxY = BAirDrop.getInstance().getConfig().getInt(String.format("summoner.%s.max-y", key));

            try {
                if (material.contains("basehead-"))
                    item = BaseHeadHook.getItem(material);
                else item.setType(Material.valueOf(material));
            }catch (IllegalArgumentException e){
                Message.error(String.format("Ошибка загрузки %s предмета призыва", key));
                continue;
            }

            ItemMeta im = item.getItemMeta();

            if (nbt != null) {
                im.setCustomModelData(Integer.parseInt(nbt.replace("{CustomModelData:", "").replace("}", "")));
            }

            im.setDisplayName(Message.messageBuilder(name));
            lore.replaceAll(Message::messageBuilder);
            im.setLore(lore);
            im.getPersistentDataContainer().set(NamespacedKey.fromString("summoner"), PersistentDataType.STRING, key);
            im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

            item.setItemMeta(im);
            items.put(key, new CSummonerItem(item, airdrop, clone, usePlayerLocation, ignoreRegion, flatnessCheck, checkUpBlocks, call, minY, maxY));
        }
    }
    /**
     * Listening for Summoner settings
     */

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
                    if(!e.getPlayer().hasCooldown(items.get(key).getItem().getType()) && cooldownPlayers.getOrDefault(e.getPlayer().getUniqueId(),  System.currentTimeMillis() / 50) - (System.currentTimeMillis() / 50) > 0){
                        int cooldown = (int) (cooldownPlayers.get(e.getPlayer().getUniqueId()) - (int) (System.currentTimeMillis() / 50));
                        Message.sendMsg(e.getPlayer(), String.format(BAirDrop.getConfigMessage().getMessage("summoner-limit"), cooldown / 20));
                        e.getPlayer().setCooldown(items.get(key).getItem().getType(), cooldown);
                        return;
                    }else if(e.getPlayer().hasCooldown(items.get(key).getItem().getType())){
                        int cooldown = e.getPlayer().getCooldown(items.get(key).getItem().getType());
                        Message.sendMsg(e.getPlayer(), String.format(BAirDrop.getConfigMessage().getMessage("summoner-limit"), cooldown / 20));
                        return;
                    }
                }
                AirDrop airDrop = items.get(key).getAirDrop(e.getClickedBlock().getLocation(), e.getPlayer());
                if (airDrop != null) {
                    if (items.get(key).isUsePlayerLocation()) {
                        airDrop.setAirDropLocation(e.getClickedBlock().getLocation().add(0, 0, 0).add(
                                GeneratorUtils.getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.x", GeneratorUtils.getWorldKeyByWorld(e.getClickedBlock().getWorld()))),
                                GeneratorUtils.getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.y", GeneratorUtils.getWorldKeyByWorld(e.getClickedBlock().getWorld()))),
                                GeneratorUtils.getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.z", GeneratorUtils.getWorldKeyByWorld(e.getClickedBlock().getWorld())))));

                        airDrop.setFutureLocation(e.getClickedBlock().getLocation().add(0, 0, 0).add(
                                GeneratorUtils.getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.x", GeneratorUtils.getWorldKeyByWorld(e.getClickedBlock().getWorld()))),
                                GeneratorUtils.getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.y", GeneratorUtils.getWorldKeyByWorld(e.getClickedBlock().getWorld()))),
                                GeneratorUtils.getSettings(airDrop.getGeneratorSettings(), String.format("%s.offsets.z", GeneratorUtils.getWorldKeyByWorld(e.getClickedBlock().getWorld())))));
                        airDrop.setUsePlayerLocation(true);
                    }


                    AirDropSummonerEvent airDropSummonerEvent = new AirDropSummonerEvent(airDrop, e.getPlayer());
                    Bukkit.getServer().getPluginManager().callEvent(airDropSummonerEvent);
                    if(airDropSummonerEvent.isCancelled()) {
                        airDrop.unload();
                        return;
                    }

                    BAirDrop.airDrops.put(airDrop.getId(), airDrop);
                    e.getPlayer().setCooldown(items.get(key).getItem().getType(), cooldown);
                    cooldownPlayers.put(e.getPlayer().getUniqueId(), (System.currentTimeMillis() / 50 + cooldown));

                    airDrop.notifyObservers(CustomEvent.SUMMONER, e.getPlayer());
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
