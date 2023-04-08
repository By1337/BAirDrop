package org.by1337.bairdrop.util;


import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RemovalStrategy;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.World;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.ConfigManager.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionManager {
    private static HashMap<StateFlag, StateFlag.State> flags = new HashMap<>();
    public static void RemoveRegion(AirDrop airDrop) {
      //  Message.logger("RemoveRegion " + airDrop.getAirId());
        World world;
        if(airDrop.getAirLoc() != null)
            world = airDrop.getAirLoc().getWorld();
        else if(airDrop.getFutureLocation() != null)
            world = airDrop.getFutureLocation().getWorld();
        else world = airDrop.getWorld();
        if(world == null){
            Message.error("Удаление региона не удалось потому-что мир неизвестен!");
            return;
        }

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        com.sk89q.worldguard.protection.managers.RegionManager regions = container.get(BukkitAdapter.adapt(world));
        if (regions.hasRegion(airDrop.getAirId() + "_region"))
            regions.removeRegion(airDrop.getAirId() + "_region", RemovalStrategy.REMOVE_CHILDREN);
    }
    public static ProtectedCuboidRegion GetProtectedCuboidRegion(AirDrop airDrop) {
        Location point1 = new Location(airDrop.getAirLoc().getWorld(), airDrop.getAirLoc().getX() + airDrop.getAirProtect(), airDrop.getAirLoc().getY() + airDrop.getAirProtect(), airDrop.getAirLoc().getZ() + airDrop.getAirProtect());
        Location point2 = new Location(airDrop.getAirLoc().getWorld(), airDrop.getAirLoc().getX() - airDrop.getAirProtect(), airDrop.getAirLoc().getY() - airDrop.getAirProtect(), airDrop.getAirLoc().getZ() - airDrop.getAirProtect());
        return new ProtectedCuboidRegion(airDrop.getAirId() + "_region",
                BlockVector3.at(point1.getX(), point1.getY(), point1.getZ()),
                BlockVector3.at(point2.getX(), point2.getY(), point2.getZ()));
    }

    public static void SetRegion(AirDrop airDrop) {
       // Message.logger("SetRegion " + airDrop.getAirId());
        ProtectedCuboidRegion rg = GetProtectedCuboidRegion(airDrop);
        World world = airDrop.getAirLoc().getWorld();
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        com.sk89q.worldguard.protection.managers.RegionManager regions = container.get(BukkitAdapter.adapt(world));

        assert regions != null;

        try {
            for (Map.Entry<StateFlag, StateFlag.State> entry : flags.entrySet())
                rg.setFlag((Flag)entry.getKey(), entry.getValue());
        }catch (Exception e){
            Message.error(Config.getMessage("flag-error"));
        }
        regions.addRegion(rg);
    }

    public static void LoadFlags() {
        for (String flag : BAirDrop.instance.getConfig().getStringList("settings.world-guard-flags.allow-flags")) {
            if (!addFlag(flag, true))
                Message.warning("Flag " + flag + " not loaded in");
        }
        for (String flag : BAirDrop.instance.getConfig().getStringList("settings.world-guard-flags.deny-flags")) {
            if (!addFlag(flag, false))
                Message.warning("Flag " + flag + " not loaded in");
        }

    }
    private static boolean addFlag(String flagname, boolean state) {
        Flag<?> flag = WorldGuard.getInstance().getFlagRegistry().get(flagname);
        if (flag == null)
            return false;
        StateFlag stateFlag = (StateFlag) flag;
        StateFlag.State saved_state = state ? StateFlag.State.ALLOW : StateFlag.State.DENY;
        flags.put(stateFlag, saved_state);
        return true;
    }
}
