package org.by1337.bairdrop.effect.effectImpl;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import org.by1337.bairdrop.effect.EffectType;
import org.by1337.bairdrop.effect.IEffect;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Guard implements IEffect {
    private int ticks = -1;
    private final int timeUpdate;
    private AirDrop airDrop;
    private boolean used;
    private boolean stop;
    private final double radius;
    private final double heal;
    private final int count;
    private Location loc;
    private final String entityName;
    private final List<Zombie> zombies = new ArrayList<>();
    private final Map<String, Object> map;

    public Guard(Map<String, Object> map) {
        this.map = map;
        ticks = ((Number) map.getOrDefault("ticks", -1)).intValue();
        timeUpdate = ((Number) map.getOrDefault("timeUpdate", 0)).intValue();
        radius = ((Number) map.getOrDefault("radius", 0)).doubleValue();
        heal = ((Number) map.getOrDefault("heal", 0)).doubleValue();
        count = ((Number) map.getOrDefault("count", 0)).intValue();
        entityName = Objects.requireNonNull((String) map.getOrDefault("name", ""));
    }

    @Override
    public void Start(AirDrop airDrop) {
        this.airDrop = airDrop;
        if (airDrop.getAnyLoc() == null) {
            Message.error(BAirDrop.getConfigMessage().getMessage("effect-error-loc-is-null"));
            Message.error(BAirDrop.getConfigMessage().getMessage("effect-error-loc-is-null2"));
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("effect-error-loc-is-null3"), airDrop.getId()));
            return;
        } else loc = airDrop.getAnyLoc().clone();
        used = true;
        run();
    }

    @Override
    public void End() {
        stop = true;
        zombies.forEach(Entity::remove);
    }

    @Override
    public boolean isUsed() {
        return used;
    }

    void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    double x = ThreadLocalRandom.current().nextDouble(-radius, radius);
                    double y;
                    double z = ThreadLocalRandom.current().nextDouble(-radius, radius);

                    y = loc.getWorld().getHighestBlockAt(loc.clone().add(x, 0, z)).getLocation().getY();

                    Zombie zombie = loc.getWorld().spawn(new Location(loc.getWorld(), loc.getX() + x + 0.5, y + 1, loc.getZ() + z + 0.5), Zombie.class);

                    zombie.setCustomName(Message.messageBuilder(entityName));
                    zombie.setSeed(2);
                    zombie.setMaxHealth(heal);
                    zombie.setHealth(heal);

                    ItemStack boot = new ItemStack(Material.DIAMOND_BOOTS);
                    ItemMeta im = boot.getItemMeta();
                    im.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, false);
                    im.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
                    boot.setItemMeta(im);
                    zombie.getEquipment().setBoots(boot);

                    ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
                    ItemMeta im2 = chest.getItemMeta();
                    im2.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, false);
                    im2.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
                    chest.setItemMeta(im2);
                    zombie.getEquipment().setChestplate(chest);

                    ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
                    ItemMeta im3 = leggings.getItemMeta();
                    im3.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, false);
                    im3.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
                    leggings.setItemMeta(im3);
                    zombie.getEquipment().setLeggings(leggings);

                    ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
                    ItemMeta im4 = helmet.getItemMeta();
                    im4.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, false);
                    im4.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
                    helmet.setItemMeta(im4);
                    zombie.getEquipment().setHelmet(helmet);

                    ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
                    ItemMeta im5 = axe.getItemMeta();
                    im5.addEnchant(Enchantment.KNOCKBACK, 2, false);
                    im5.addEnchant(Enchantment.DAMAGE_ALL, 5, false);
                    im5.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
                    axe.setItemMeta(im5);
                    zombie.getEquipment().setItemInMainHand(axe);
                    zombie.setLootTable(null);
                    zombie.setGlowing(true);
                    zombie.setCustomNameVisible(true);
                }
                if (stop)
                    cancel();
                if (ticks != -1) {
                    if ((ticks - timeUpdate) > 0) {
                        ticks -= timeUpdate;
                    } else {
                        End();
                        cancel();
                    }
                }
            }
        }.runTaskTimer(BAirDrop.getInstance(), timeUpdate, timeUpdate);
    }

    @Override
    public EffectType getType() {
        return EffectType.SPAWN_GUARD;
    }

    @Override
    public IEffect clone() {
        return new Guard(map);
    }

}
