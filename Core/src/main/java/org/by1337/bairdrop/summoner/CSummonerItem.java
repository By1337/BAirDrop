package org.by1337.bairdrop.summoner;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.bairdrop.util.OLDMessage;


public class CSummonerItem implements SummonerItem {
    private final ItemStack item;
    private final String summonerAirDropId;
    private final boolean clone;
    private final boolean usePlayerLocation;
    private final boolean ignoreRegion;
    private final boolean flatnessCheck;
    private final boolean checkUpBlocks;
    private final List<String> call;
    private final int minY;
    private final int maxY;

    public CSummonerItem(ItemStack item, String summonerAirDropId, boolean clone, boolean usePlayerLocation, boolean ignoreRegion, boolean flatnessCheck, boolean checkUpBlocks, List<String> call, int minY, int maxY) {
        this.item = item;
        this.summonerAirDropId = summonerAirDropId;
        this.clone = clone;
        this.usePlayerLocation = usePlayerLocation;
        this.flatnessCheck = flatnessCheck;
        this.checkUpBlocks = checkUpBlocks;
        this.call = call;
        this.ignoreRegion = ignoreRegion;
        this.minY = minY;
        this.maxY = maxY;
    }


    public boolean isUsePlayerLocation() {
        return usePlayerLocation;
    }

    public ItemStack getItem() {
        return item.clone();
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

//        if (!Objects.equals(pl.getLocation().getWorld(), BAirDrop.airDrops.get(nameKey).getWorld())) {
//            OLDMessage.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("impossible-to-call"));
//            pl.setCooldown(getItem().getType(), 40);
//            return null;
//        }
        if (minY != 0) {
            if (location.getY() < minY) {
                OLDMessage.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("summoner-min-y"), minY));
                return null;
            }
        }
        if (maxY != 0) {
            if (location.getY() > maxY) {
                OLDMessage.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("summoner-max-y"), maxY));
                return null;
            }
        }

        if (isCheckUpBlocks()) {
            if (location.getY() != location.getWorld().getHighestBlockYAt(location)) {
                OLDMessage.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("check-up-blocks-fail"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }

//        AirDrop air;
//        if (nameKey.equals("RANDOM")) {
//            if (isClone()) {
//                air = AirDropUtils.getRandomCloneAir();
//            } else {
//                air = AirDropUtils.getRandomAir();
//            }
//        } else {
//            air = BAirDrop.airDrops.getOrDefault(nameKey, null);
//        }
//        if (air == null){
//            OLDMessage.error(String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), nameKey + " (air is null!)"));
//            OLDMessage.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("impossible-to-call"));
//            pl.setCooldown(getItem().getType(), 40);
//            return null;
//        }
//        if (air.isAirDropStarted()){
//            OLDMessage.error(BAirDrop.getConfigMessage().getMessage("summoner-error-it-airdrop-is-already-started") + "(airdrop: " + nameKey + ")");
//            OLDMessage.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("summoner-error-it-airdrop-is-already-started"));
//            pl.setCooldown(getItem().getType(), 40);
//            return null;
//        }
//        if (air.isClone()){
//            air.addDec(String.format(BAirDrop.getConfigMessage().getMessage("dec-info"), pl.getName()));
//        }
//
//        if (isUsePlayerLocation() && !isIgnoreRegion()) {
//            if (!GeneratorUtils.isRegionEmpty(air, location)) {
//                OLDMessage.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("region-overlapping"));
//                pl.setCooldown(getItem().getType(), 40);
//                return null;
//            }
//        }
//        if (isFlatnessCheck()) {
//            if (!new CGenerator().checkForEvenness(location.clone().add(0, 1, 0), air)) {
//                OLDMessage.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("flatness-check-fail"));
//                pl.setCooldown(getItem().getType(), 40);
//                return null;
//            }
//        }
//
//        callListeners(air, pl);
//        air.setSummoner(true);
//        return air;
        return null;
    }

    public void callListeners(AirDrop airDrop, Player pl) {
        for (String str : call)
            airDrop.invokeListener(NamespacedKey.fromString(str), pl, CustomEvent.NONE);
    }

}
