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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;
public class Guard implements IEffect{
    int ticks = -1;
    int timeUpdate;
    AirDrop airDrop;
    boolean active = true;
    double radius;
    double heal;
    int count;
    FileConfiguration cs;
    Location loc;
    String name;
    String entityName;
    List<Zombie> zombies = new ArrayList<>();

    public Guard(FileConfiguration cs, String name) throws NullPointerException, IllegalArgumentException{
     //   getServer().getPluginManager().registerEvents(this, BAirDrop.instance);
        this.cs = cs;
        ticks = cs.getInt(String.format("effects.%s.ticks", name));
        timeUpdate = cs.getInt(String.format("effects.%s.timeUpdate", name));
        this.name = name;

        radius = cs.getDouble(String.format("effects.%s.radius", name));
        heal = cs.getDouble(String.format("effects.%s.heal", name));
        count = cs.getInt(String.format("effects.%s.count", name));

        entityName = Objects.requireNonNull(cs.getString(String.format("effects.%s.name", name)));
    }

    @Override
    public void Start(AirDrop airDrop) {
        this.airDrop = airDrop;
        if (airDrop.getAirLoc() == null) {
            if (airDrop.getFutureLocation() == null) {
                Message.error("Локация для аирдропа ещё не сгенерирована");
                Message.error("Эффект не может появится");
                Message.error("аирдроп " + airDrop.getAirId());
                return;
            } else loc = airDrop.getFutureLocation().clone();
        } else loc = airDrop.getAirLoc().clone();
        run();

    }

    @Override
    public void End() {
        active = false;
        zombies.forEach(Entity::remove);
     //   HandlerList.unregisterAll(this);
    }

    @Override
    public void setLifetime(int ticks) {
        this.ticks = ticks;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(int i = 0; i < count; i++){
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
                if (!isActive())
                    cancel();
                if (ticks != -1) {
                    if ((ticks - timeUpdate) > 0) {
                        ticks -= timeUpdate;
                    } else {
                        cancel();
                    }
                }
            }
        }.runTaskTimer(BAirDrop.instance, timeUpdate, timeUpdate);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public EffectType getType() {
        return EffectType.SPAWN_GUARD;
    }

    @Override
    public IEffect clone() {

        return new Guard(cs, name);

    }

}