package org.by1337.bairdrop.util;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.customListeners.CustomEvent;

import static org.by1337.bairdrop.BAirDrop.len;

public class SummonerItem {
    private final ItemStack item;
    private final String airdrop;
    private final boolean clone;
    private final boolean usePlayerLocation;
    private final boolean ignoreRegion;
    private final boolean flatnessCheck;
    private final boolean checkUpBlocks;
    private final List<String> call;

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


    public AirDrop start(Location location, Player pl) {//todo тут обф
        int var0 = Integer.parseInt("110011100", 2);
        int Vvar619 = 94^160;//254
        switch (Vvar619){
            case 22:
                throw null;
            case 77:
                throw null;
            case 67:
                throw null;
            case -2:
                throw null;
            case 254://254
                var0 = (94 ^ 84) >> 1;
                break;
            case 63:
                var0 = (94 ^ 790) >> 2;
                break;
            case 9:
                var0 = (94 ^ 188) >> 9;
                break;
        }

        int x = Integer.toBinaryString(len).length() << var0; //320
        int var1 = Integer.parseInt("110110100", 2);
        int Vvar19 = 41061^41;//41036
        switch (Vvar19){
            case 36925:
                throw null;
            case 8872:
                var1 = (41061 ^ 754) >> 5;
                break;
            case 41036://41036
                var1 = (41061 ^ 101) >> 7;
                break;
            case 6687:
                throw null;
            case 7027:
                throw null;
            case 5654:
                throw null;
        }
        if(x != var1){
            return null;
        }
            String key = airdrop;
        if (key.equals("RANDOM"))
            for (AirDrop air : BAirDrop.airDrops.values()) {
                if (!air.isAirDropStarted() && !air.isClone() || clone)
                    if (ThreadLocalRandom.current().nextInt(0, 100) <= air.getSpawnChance()) {
                            key = air.getId();
                            break;
                    }
            }
        if (!BAirDrop.airDrops.containsKey(key)) {
            Message.error(String.format(Config.getMessage("unknown-airdrop"), key));
            Message.sendMsg(pl, Config.getMessage("impossible-to-call"));
            pl.setCooldown(getItem().getType(), 40);
            //Integer.toBinaryString(len).length()
            return null;
        }
        if(!Objects.equals(pl.getLocation().getWorld(), BAirDrop.airDrops.get(key).getWorld())){
            Message.sendMsg(pl, Config.getMessage("impossible-to-call"));
            pl.setCooldown(getItem().getType(), 40);
            return null;
        }
        if (usePlayerLocation && !ignoreRegion) {
            if (!LocationGeneration.isRegionEmpty(BAirDrop.airDrops.get(key), location)) {
                Message.sendMsg(pl, Config.getMessage("region-overlapping"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }
        if(flatnessCheck){

            if(!new LocationGeneration().checkForEvenness(location.clone().add(0, 1, 0), BAirDrop.airDrops.get(key))){
                Message.sendMsg(pl, Config.getMessage("flatness-check-fail"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }
        if(checkUpBlocks){
            if(location.getY() != location.getWorld().getHighestBlockYAt(location)){
                Message.sendMsg(pl, Config.getMessage("check-up-blocks-fail"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }
        AirDrop air;
        if (clone) {
            String newid = BAirDrop.airDrops.get(key).getId() + "_clone" + UUID.randomUUID();
          //  while (BAirDrop.airDrops.containsKey(newid))
              //  newid = BAirDrop.airDrops.get(key).getAirId() + "_clone" + UUID.randomUUID();

            air = BAirDrop.airDrops.get(key).clone(newid);
            air.setClone(true);
            air.setKill(true);
        } else {
            air = BAirDrop.airDrops.get(key);
            if (air.isAirDropStarted() || air.isSummoner()) {
                Message.sendMsg(pl, Config.getMessage("impossible-to-call"));
                pl.setCooldown(getItem().getType(), 40);
                return null;
            }
        }
        for(String str : call)
            air.callListener(NamespacedKey.fromString(str), pl, CustomEvent.NONE);
        air.setSummoner(true);
        return air;
    }

}
