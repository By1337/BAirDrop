package org.by1337.bairdrop.worldGuardHook;

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

import org.by1337.api.world.BlockPosition;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.util.OLDMessage;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class RegionManager {
    private static final HashMap<StateFlag, StateFlag.State> flags = new HashMap<>();

    // airdropId + _region
    public static void removeRegion(@NotNull World world, String regionName) {

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        com.sk89q.worldguard.protection.managers.RegionManager regions = container.get(BukkitAdapter.adapt(world));
        if (regions.hasRegion(regionName))
            regions.removeRegion(regionName, RemovalStrategy.REMOVE_CHILDREN);
    }

    public static ProtectedCuboidRegion getProtectedCuboidRegion(BlockPosition regionRadius, String regionName, Location location) {
        Location point1 = new Location(location.getWorld(), location.getX() + regionRadius.getX(), location.getY() + regionRadius.getY(), location.getZ() + regionRadius.getZ());
        Location point2 = new Location(location.getWorld(), location.getX() - regionRadius.getX(), location.getY() - regionRadius.getY(), location.getZ() - regionRadius.getZ());
        return new ProtectedCuboidRegion(regionName,
                BlockVector3.at(point1.getX(), point1.getY(), point1.getZ()),
                BlockVector3.at(point2.getX(), point2.getY(), point2.getZ()));
    }

    public static void setRegion(BlockPosition regionRadius, String regionName, Location location) {
        ProtectedCuboidRegion rg = getProtectedCuboidRegion(regionRadius, regionName, location);
        World world = location.getWorld();
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        com.sk89q.worldguard.protection.managers.RegionManager regions = container.get(BukkitAdapter.adapt(world));

        assert regions != null;

        try {
            for (Map.Entry<StateFlag, StateFlag.State> entry : flags.entrySet())
                rg.setFlag((Flag) entry.getKey(), entry.getValue());
        } catch (Exception e) {
            OLDMessage.error(BAirDrop.getConfigMessage().getMessage("flag-error"));
            e.printStackTrace();
        }
        regions.addRegion(rg);
    }

    public static void loadFlags() {
        for (String flag : BAirDrop.getInstance().getConfig().getStringList("settings.world-guard-flags.allow-flags")) {
            if (!addFlag(flag, true))
                OLDMessage.warning("Flag " + flag + " not loaded in");
        }
        for (String flag : BAirDrop.getInstance().getConfig().getStringList("settings.world-guard-flags.deny-flags")) {
            if (!addFlag(flag, false))
                OLDMessage.warning("Flag " + flag + " not loaded in");
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
