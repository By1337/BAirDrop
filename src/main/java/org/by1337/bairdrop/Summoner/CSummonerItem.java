package org.by1337.bairdrop.Summoner;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.LocationGenerator.GeneratorUtils;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.LocationGenerator.CGenerator;
import org.by1337.bairdrop.util.Message;


public class CSummonerItem implements SummonerItem {
    private final ItemStack item;
    private final String summonerAirDropId;
    private final boolean clone;
    private final boolean usePlayerLocation;
    private final boolean ignoreRegion;
    private final boolean flatnessCheck;
    private final boolean checkUpBlocks;
    private final List<String> call;

    public CSummonerItem(ItemStack item, String summonerAirDropId, boolean clone, boolean usePlayerLocation, boolean flatnessCheck, boolean checkUpBlocks, List<String> call, boolean ignoreRegion) {
        this.item = item;
        this.summonerAirDropId = summonerAirDropId;
        this.clone = clone;
        this.usePlayerLocation = usePlayerLocation;
        this.flatnessCheck = flatnessCheck;
        this.checkUpBlocks = checkUpBlocks;
        this.call = call;
        this.ignoreRegion = ignoreRegion;
    }

    public boolean isUsePlayerLocation() {
        return usePlayerLocation;
    }

    public ItemStack getItem() {
        return item;
    }


    public String getSummonerAirDropId() {
        return summonerAirDropId;
    }


    public boolean isClone() {
        return clone;
    }

    public boolean isIgnoreRegion() {
        return ignoreRegion;
    }

    public boolean isFlatnessCheck() {
        return flatnessCheck;
    }

    public boolean isCheckUpBlocks() {
        return checkUpBlocks;
    }

    public AirDrop getAirDrop(Location location, Player pl) {
        String key = summonerAirDropId;
        if (key.equals("RANDOM"))

            for (AirDrop air : BAirDrop.airDrops.values()) {
                if (!air.isAirDropStarted() && !air.isClone() || clone)
                    if (ThreadLocalRandom.current().nextInt(0, 100) <= air.getSpawnChance()) {
                        key = air.getId();
                        break;
                    }
            }
        if (!BAirDrop.airDrops.containsKey(key)) {
            Message.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), key));
            Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("impossible-to-call"));
            pl.setCooldown(getItem().getType(), 40);
            return null;
        }
        if (!Objects.equals(pl.getLocation().getWorld(), BAirDrop.airDrops.get(key).getWorld())) {
            Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("impossible-to-call"));
            pl.setCooldown(getItem().getType(), 40);
            return null;
        }
        if (isUsePlayerLocation() && !isIgnoreRegion()) {
            if (!GeneratorUtils.isRegionEmpty(BAirDrop.airDrops.get(key), location)) {
                Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("region-overlapping"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }
        if (isFlatnessCheck()) {
            if (!new CGenerator().checkForEvenness(location.clone().add(0, 1, 0), BAirDrop.airDrops.get(key))) {
                Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("flatness-check-fail"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }
        if (isCheckUpBlocks()) {
            if (location.getY() != location.getWorld().getHighestBlockYAt(location)) {
                Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("check-up-blocks-fail"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }
        AirDrop air;
        if (isClone()) {
            String newid = BAirDrop.airDrops.get(key).getId() + "_clone" + UUID.randomUUID();
            air = BAirDrop.airDrops.get(key).clone(newid);
            air.setClone(true);
            air.setKill(true);
        } else {
            air = BAirDrop.airDrops.get(key);
            if (air.isAirDropStarted() || air.isSummoner()) {
                Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("impossible-to-call"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }
        callListeners(air, pl);
        air.setSummoner(true);
        return air;
    }

    public void callListeners(AirDrop airDrop, Player pl) {
        for (String str : call)
            airDrop.InvokeListener(NamespacedKey.fromString(str), pl, CustomEvent.NONE);
    }

}
