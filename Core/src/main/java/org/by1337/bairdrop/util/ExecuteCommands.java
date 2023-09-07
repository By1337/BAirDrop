package org.by1337.bairdrop.util;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Lidded;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.AirDropUtils;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.command.CommandRegistry;
import org.by1337.bairdrop.listeners.SetStaticLocation;
import org.by1337.bairdrop.listeners.util.ListenChat;
import org.by1337.bairdrop.locationGenerator.GeneratorUtils;
import org.by1337.bairdrop.worldGuardHook.CSchematicsManager;
import org.by1337.bairdrop.worldGuardHook.RegionManager;
import org.by1337.bairdrop.api.event.ExecuteCommandEvent;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.customListeners.CustomEventListener;
import org.by1337.bairdrop.menu.*;
import org.by1337.bairdrop.menu.util.MenuItem;
import org.by1337.bairdrop.scripts.JsScript;
import org.by1337.bairdrop.scripts.Script;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.Bukkit.*;

import static org.by1337.bairdrop.BAirDrop.getInstance;

public class ExecuteCommands {


    public void runListenerCommands(String[] commands, @Nullable Player pl, @Nullable AirDrop airDrop, CustomEvent customEvent) {
        ExecuteCommandEvent event = new ExecuteCommandEvent(airDrop, commands, pl, customEvent);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        for (String command : commands) {
            if (airDrop != null)
                command = airDrop.replaceInternalPlaceholder(command);
            command = Message.setPlaceholders(pl, command);
            command = BMatch.match(command);


            CommandRegistry.execute(airDrop, pl, command);
        }
    }


    public static String setPlayerPlaceholder(Player pl, String command) {
        if (command.contains("{player-get-item-")) {
            try {
                int slot = Integer.parseInt(command.split("player-get-item-")[1].split("}")[0]);
                ItemStack item = pl.getInventory().getItem(slot);
                if (command.contains(".getType")) {
                    if (item == null) {
                        command = command.replace(String.format("{player-get-item-%s}.getType", slot), "AIR");
                        return command;
                    }
                    command = command.replace(String.format("{player-get-item-%s}.getType", slot), item.getType().toString());
                    return command;
                }
                if (command.contains(".isNull")) {
                    if (item == null) {
                        command = command.replace(String.format("{player-get-item-%s}.isNull", slot), "true");
                        return command;
                    }
                    command = command.replace(String.format("{player-get-item-%s}.isNull", slot), "false");
                    return command;
                }
            } catch (NumberFormatException e) {
                Message.error("{player-get-item-<slot>} <slot> должен быть числом!");
            } catch (ArrayIndexOutOfBoundsException e) {
                Message.error("{player-get-item-<slot>} Не достаточно аргументов!");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return command;
    }




    public static void execute(AirDrop airDrop, MenuItem menuItem, InventoryClickEvent e) {
        if (e.getClick() == ClickType.LEFT)
            menuCommand(menuItem.getLEFT_COMMANDS(), airDrop, (Player) e.getWhoClicked());
        if (e.getClick() == ClickType.RIGHT)
            menuCommand(menuItem.getRIGHT_COMMANDS(), airDrop, (Player) e.getWhoClicked());
        if (e.getClick() == ClickType.SHIFT_LEFT)
            menuCommand(menuItem.getSHIFT_LEFT_COMMANDS(), airDrop, (Player) e.getWhoClicked());
        if (e.getClick() == ClickType.SHIFT_RIGHT)
            menuCommand(menuItem.getSHIFT_RIGHT_COMMANDS(), airDrop, (Player) e.getWhoClicked());
        if (e.getClick() == ClickType.MIDDLE)
            menuCommand(menuItem.getMIDDLE_COMMANDS(), airDrop, (Player) e.getWhoClicked());
        if (e.getClick() == ClickType.DROP)
            menuCommand(menuItem.getDROP_COMMANDS(), airDrop, (Player) e.getWhoClicked());
    }

    private static void menuCommand(List<String> commands, AirDrop airDrop, Player pl) {
        commands.replaceAll(airDrop::replaceInternalPlaceholder);

        for (String str : commands) {
            if (str.equalsIgnoreCase("[!airdropstarted]")) {
                if (airDrop.isAirDropStarted())
                    airDrop.End();
                else airDrop.startCommand(pl);
                continue;
            }
            if (str.equalsIgnoreCase("[teleport]")) {
                if (airDrop.isAirDropStarted()) {
                    pl.teleport(airDrop.getAnyLoc());
                    pl.closeInventory();
                }
            }
            if (str.equalsIgnoreCase("[!airlocked]")) {
                if (!airDrop.isAirDropStarted()) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("airdrop-is-not-started"));
                    return;
                }
                if (airDrop.isAirDropLocked()) {
                    airDrop.unlock();
                    if (airDrop.isStartCountdownAfterClick() && !airDrop.isActivated()) {
                        airDrop.notifyObservers(CustomEvent.ACTIVATE, pl);
                    }
                } else {
                    airDrop.setTimeToOpen(airDrop.getFileConfiguration().getInt("openingTime") * 60);
                    airDrop.setAirDropLocked(true);
                    airDrop.getAirDropLocation().getBlock().setType(airDrop.getMaterialLocked());
                }
                continue;
            }
            if (str.equalsIgnoreCase("[!start-countdown-after-click]")) {
                airDrop.setStartCountdownAfterClick(!airDrop.isStartCountdownAfterClick());
                airDrop.save();
                continue;
            }

            if (str.contains("[player]")) {
                pl.performCommand(str.replace("[player] ", ""));
                continue;
            }
            if (str.contains("[PLAYER]")) {
                pl.performCommand(str.replace("[PLAYER] ", ""));
                continue;
            }

            if (str.equalsIgnoreCase("[edit]")) {
                pl.closeInventory();
                SelectInv si = new SelectInv(airDrop, "[edit]");
                getServer().getPluginManager().registerEvents(si, BAirDrop.getInstance());
                pl.openInventory(si.getInv());
            }
            if (str.equalsIgnoreCase("[event_list]")) {
                pl.closeInventory();
                ShowAllListeners sae = new ShowAllListeners(airDrop);
                getServer().getPluginManager().registerEvents(sae, BAirDrop.getInstance());
                pl.openInventory(sae.getInventory());

            }
            if (str.equalsIgnoreCase("[chance]")) {
                pl.closeInventory();
                SelectInv si = new SelectInv(airDrop, "[chance]");
                getServer().getPluginManager().registerEvents(si, BAirDrop.getInstance());
                pl.openInventory(si.getInv());
            }

            if (str.equalsIgnoreCase("[change-locked-material]")) {
                pl.closeInventory();
                ChangeMaterial cm = new ChangeMaterial(airDrop, true);
                getServer().getPluginManager().registerEvents(cm, BAirDrop.getInstance());
                pl.openInventory(cm.getInventory());
            }
            if (str.equalsIgnoreCase("[change-unlocked-material]")) {
                pl.closeInventory();
                ChangeMaterial cm = new ChangeMaterial(airDrop, false);
                getServer().getPluginManager().registerEvents(cm, BAirDrop.getInstance());
                pl.openInventory(cm.getInventory());
            }
            if (str.equalsIgnoreCase("[change-world]")) {
                pl.closeInventory();
                ChangeWorld cw = new ChangeWorld(airDrop);
                getServer().getPluginManager().registerEvents(cw, BAirDrop.getInstance());
                pl.openInventory(cw.getInventory());
            }
            if (str.equalsIgnoreCase("[!usePreGeneratedLocations]")) {
                airDrop.setUsePreGeneratedLocations(!airDrop.isUsePreGeneratedLocations());
                airDrop.save();
            }
            if (str.equalsIgnoreCase("[!use-static-loc]")) {
                airDrop.setUseStaticLoc(!airDrop.isUseStaticLoc());
                airDrop.save();
            }
            if (str.equalsIgnoreCase("[!flatnessCheck]")) {
                airDrop.setFlatnessCheck(!airDrop.isFlatnessCheck());
                airDrop.save();
            }
            if (str.equalsIgnoreCase("[!time-stop-event-must-go]")) {
                airDrop.setTimeStopEventMustGo(!airDrop.isTimeStopEventMustGo());
                airDrop.save();
            }
            if (str.equalsIgnoreCase("[change-airname]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "airname", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-invname]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "invname", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-spawnmin]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "spawnmin", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-spawnmax]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "spawnmax", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-airprotect]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "airprotect", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-timetostart]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "timetostart", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-searchbeforestart]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "searchbeforestart", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-timetoopen]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "timetoopen", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-timestop]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "timestop", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[change-minonlineplayers]")) {
                pl.closeInventory();
                if (ListenChat.ListenChat != null)
                    ListenChat.ListenChat.unReg();
                ListenChat lc = new ListenChat(airDrop, "minonlineplayers", pl);
                getServer().getPluginManager().registerEvents(lc, BAirDrop.getInstance());
                pl.closeInventory();
            }
            if (str.equalsIgnoreCase("[!time-stop-event-must-go]")) {
                airDrop.setUseStaticLoc(!airDrop.isUseStaticLoc());
                airDrop.save();
            }
            if (str.equalsIgnoreCase("[set-static-loc]")) {
                if (SetStaticLocation.SSL != null) {
                    SetStaticLocation.SSL.unReg();
                }
                SetStaticLocation ssl = new SetStaticLocation(airDrop);
                getServer().getPluginManager().registerEvents(ssl, BAirDrop.getInstance());
                pl.getInventory().setItem(0, ssl.getItem());
                pl.closeInventory();

            }
            if (str.equalsIgnoreCase("[!use-only-static-loc]")) {
                airDrop.setUseOnlyStaticLoc(!airDrop.isUseOnlyStaticLoc());
                airDrop.save();
                airDrop.getEditAirMenu().menuGenerate("setStaticLoc");
            }
        }
    }
}
