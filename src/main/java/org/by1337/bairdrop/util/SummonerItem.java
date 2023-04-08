package org.by1337.bairdrop.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.BAirDrop;
public class SummonerItem {
    final ItemStack item;
    final String airdrop;
    final boolean clone;
    final boolean usePlayerLocation;
    final boolean ignoreRegion;
    final boolean flatnessCheck;
    final boolean checkUpBlocks;
    List<String> call;

    public SummonerItem(ItemStack item, String airdrop, boolean clone, boolean usePlayerLocation, boolean flatnessCheck, boolean checkUpBlocks, List<String> call, boolean ignoreRegion) {
        this.item = item;
        this.airdrop = airdrop;
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


    public String getAirdrop() {
        return airdrop;
    }


    public boolean isClone() {
        return clone;
    }


    public AirDrop start(Location location, Player pl) {
     //   Message.debug("start(Location location, Player pl)");
        String key = airdrop;
        if (key.equals("RANDOM"))
            for (AirDrop air : BAirDrop.airDrops.values()) {
                if (!air.isAirDropStarted() && !air.isClone() || clone)
                    if (ThreadLocalRandom.current().nextInt(0, 100) <= air.getChance()) {
                            key = air.getAirId();
                            break;
                    }
            }
      //  Message.debug("key = " + key);
//        if (key.equals("RANDOM"))
//            for (AirDrop air : BAirDrop.airDrops.values()) {
//                if(!air.isClone()){
//                    key = air.getAirId();
//                    break;
//                }else return null;
//            }
     //   Message.debug("key = " + key);
        if (!BAirDrop.airDrops.containsKey(key)) {
            Message.error(String.format(Config.getMessage("unknown-airdrop"), key));
            Message.sendMsg(pl, Config.getMessage("impossible-to-call"));
            pl.setCooldown(getItem().getType(), 40);
            return null;
        }
        if(!Objects.equals(pl.getLocation().getWorld(), BAirDrop.airDrops.get(key).getWorld())){
            Message.sendMsg(pl, Config.getMessage("impossible-to-call"));
            pl.setCooldown(getItem().getType(), 40);
            return null;
        }
       // Message.debug("1");
        if (usePlayerLocation && !ignoreRegion) {
            if (!LocationGeneration.isRegionEmpty(BAirDrop.airDrops.get(key), location)) {
                Message.sendMsg(pl, Config.getMessage("region-overlapping"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }
      //  Message.debug("2");
        if(flatnessCheck){
            if(!LocationGeneration.checkForEvenness(location.clone().add(0, 1, 0), BAirDrop.airDrops.get(key))){
                Message.sendMsg(pl, Config.getMessage("flatness-check-fail"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }
      //  Message.debug("3");
        if(checkUpBlocks){
            if(location.getY() != location.getWorld().getHighestBlockYAt(location)){
                Message.sendMsg(pl, Config.getMessage("check-up-blocks-fail"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }
        AirDrop air;
        if (clone) {
          //  Message.debug("clone");
            String newid = BAirDrop.airDrops.get(key).getAirId() + "_clone" + ThreadLocalRandom.current().nextInt(99999);
            while (BAirDrop.airDrops.containsKey(newid))
                newid = BAirDrop.airDrops.get(key).getAirId() + "_clone" + ThreadLocalRandom.current().nextInt(99999);

            air = BAirDrop.airDrops.get(key).clone(newid);
            air.setClone(true);
            air.setKill(true);
        } else {
            air = BAirDrop.airDrops.get(key);
            if (air.isAirDropStarted() || air.isSUMMONER()) {
                Message.sendMsg(pl, Config.getMessage("impossible-to-call"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }
        for(String str : call)
            air.callListener(str, pl, Events.NONE);
        air.setSUMMONER(true);
        return air;
    }
}
