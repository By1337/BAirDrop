package org.by1337.bairdrop.command;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.CAirDrop;
import org.by1337.bairdrop.listeners.Compass;
import org.by1337.bairdrop.menu.EditAirMenu;
import org.by1337.bairdrop.menu.SelectAirMenu;
import org.by1337.bairdrop.menu.ShowAllListeners;
import org.by1337.bairdrop.scripts.JsScript;
import org.by1337.bairdrop.customListeners.CustomEvent;
import org.by1337.bairdrop.locationGenerator.GeneratorLoc;
import org.by1337.bairdrop.scripts.Script;
import org.by1337.bairdrop.util.InvalidCharacters;
import org.by1337.bairdrop.util.InvalidCharactersChecker;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static org.bukkit.Bukkit.createWorld;
import static org.bukkit.Bukkit.getServer;
import static org.by1337.bairdrop.BAirDrop.*;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player pl) {
            if (args.length == 0) {
                Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("few-arguments"));
                return true;
            }
            if (args[0].equals("help")) {
                if (!pl.hasPermission("bair.help")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                for (String str : BAirDrop.getConfigMessage().getList("help-list")) {
                    Message.sendMsg(pl, str);
                }
                return true;
            }
            if (args[0].equals("compass")) {
                if (!pl.hasPermission("bair.compass")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                Player player = pl;
                int amount = 1;
                if (args.length > 1)
                    player = Bukkit.getPlayer(args[1]);
                try {
                    if (args.length > 2)
                        amount = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (player == null)
                    player = pl;
                ItemStack compass = Compass.item.clone();
                compass.setAmount(amount);

                if (player.getInventory().firstEmpty() == -1)
                    player.getLocation().getWorld().dropItem(pl.getLocation(), compass);
                else
                    player.getInventory().addItem(compass);
                return true;
            }
            if (args[0].equals("js")) { // /bair js fire.js <player name>
                if (!pl.hasPermission("bair.js")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                if (args.length < 2) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                if (!BAirDrop.getiConfig().getScripts().containsKey(args[1])) {
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("unknown-script"), args[1]));
                }
                Player player = pl;
                if (args.length == 3) {
                    player = Bukkit.getPlayer(args[2]) == null ? pl : Bukkit.getPlayer(args[2]);
                }
                Player finalPlayer = player;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("player", finalPlayer);
                        Script manager = new JsScript();
                        manager.runScript(args[1], map);
                    }
                }.runTask(getInstance());
                return true;
            }
            if (args[0].equals("delete")) {
                if (!pl.hasPermission("bair.delete")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    airDrops.get(args[1]).notifyObservers(CustomEvent.UNLOAD, null);
                    if (!airDrops.get(args[1]).delete())
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("delete-fail"));
                    else {
                        airDrops.get(args[1]).unload();
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("delete-good"));
                    }

                    return true;
                } else {
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                }
                return true;
            }
            if (args[0].equals("get")) {
                if (args.length == 1) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                if (!pl.hasPermission("bair.get")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                if (!BAirDrop.summoner.getItems().containsKey(args[1])) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("unknown-item"));
                    return true;
                }
                if (args.length >= 3) {
                    ItemStack item = BAirDrop.summoner.getItems().get(args[1]).getItem();
                    int amount = 1;
                    if (args.length == 4) {
                        try {
                            amount = Integer.parseInt(args[3]);
                            item.setAmount(amount);
                        } catch (NumberFormatException e) {
                            item.setAmount(1);
                        }
                    }
                    Player player = Bukkit.getPlayer(args[2]);
                    if (player == null) {
                        player = pl;
                    }
                    if (player.getInventory().firstEmpty() == -1) {
                        player.getLocation().getWorld().dropItem(player.getLocation(), item);
                        Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("get-item"), player.getName(), args[1], amount));

                    } else {
                        player.getInventory().addItem(item);
                        Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("get-item"), player.getName(), args[1], amount));

                    }
                    return true;
                }
                if (pl.getInventory().firstEmpty() == -1) {
                    pl.getLocation().getWorld().dropItem(pl.getLocation(), BAirDrop.summoner.getItems().get(args[1]).getItem());
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("get-item"), pl.getName(), args[1], 1));
                    // Message.sendMsg(pl,"{PP} &fИгроку " + pl.getName() + " был выдан " + args[1] + " x1");
                } else {
                    pl.getInventory().addItem(BAirDrop.summoner.getItems().get(args[1]).getItem());
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("get-item"), pl.getName(), args[1], 1));
                    // Message.sendMsg(pl,"{PP} &fИгроку " + pl.getName() + " был выдан " + args[1] + " x1");
                }
                return true;
            }
            if (args[0].equals("reload")) {
                if (!pl.hasPermission("bair.reload")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));//message.yml
                    return true;
                }
                long x = System.currentTimeMillis();
                BAirDrop.reload();
                Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("reload"), System.currentTimeMillis() - x));
                return true;
            }

            if (args[0].equals("menu")) {
                if (!pl.hasPermission("bair.menu")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    SelectAirMenu selectAirMenu = new SelectAirMenu();
                    getServer().getPluginManager().registerEvents(selectAirMenu, BAirDrop.getInstance());
                    pl.openInventory(selectAirMenu.getInventory());
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    if (BAirDrop.airDrops.get(args[1]).getEditAirMenu() != null) {
                        BAirDrop.airDrops.get(args[1]).getEditAirMenu().unReg();
                    }
                    EditAirMenu em = new EditAirMenu(BAirDrop.airDrops.get(args[1]));
                 //   getServer().getPluginManager().registerEvents(em, BAirDrop.getInstance());
                    BAirDrop.airDrops.get(args[1]).setEditAirMenu(em);
                    pl.openInventory(em.getInventory());

                } else {
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                }
                return true;
            }
            if (args[0].equals("create")) {

                if (!pl.hasPermission("bair.create")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                InvalidCharactersChecker invalidCharactersChecker = new InvalidCharacters();
                String invalidChars = invalidCharactersChecker.getInvalidCharacters(args[1]);

                if (!invalidChars.isEmpty()) {
                    Message.sendMsg(pl, String.format("&cНедопустимые символы: %s", invalidChars));
                    return true;
                }
                BAirDrop.airDrops.put(args[1], new CAirDrop(args[1]));
                Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("air-create"), args[1]));
                return true;
            }
            if (args[0].equals("start")) {
                if (!pl.hasPermission("bair.start")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    if (BAirDrop.airDrops.get(args[1]).isAirDropStarted()) {
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("airdrop-is-started"));
                        return true;
                    }
                    Location location = null;
                    if (args.length >= 5) {//bair 0start 1air 2x 3y 4z
                        try {
                            int x = Integer.parseInt(args[2]);
                            int y = Integer.parseInt(args[3]);
                            int z = Integer.parseInt(args[4]);
                            World world;
                            if (args.length >= 6){
                                world = Bukkit.getWorld(args[5]);
                            }else {
                                world = BAirDrop.airDrops.get(args[1]).getWorld();
                            }
                            if (world == null){
                                world = BAirDrop.airDrops.get(args[1]).getWorld();
                            }
                            location = new Location(world, x, y, z);
                            BAirDrop.airDrops.get(args[1]).setAirDropLocation(location);
                            BAirDrop.airDrops.get(args[1]).setFutureLocation(location);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    BAirDrop.airDrops.get(args[1]).startCommand(pl);
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("starting"));
                } else
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                return true;
            }
            if (args[0].equals("stop")) {
                if (!pl.hasPermission("bair.stop")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    if (!BAirDrop.airDrops.get(args[1]).isAirDropStarted()) {
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("airdrop-is-not-started"));
                        return true;
                    }
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("end"));
                    BAirDrop.airDrops.get(args[1]).End();

                } else
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                return true;
            }


            if (args[0].equals("clone")) {
                if (!pl.hasPermission("bair.clone")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                if (args.length < 2) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }

                if (BAirDrop.airDrops.containsKey(args[1])) {

                    InvalidCharactersChecker invalidCharactersChecker = new InvalidCharacters();
                    String invalidChars = invalidCharactersChecker.getInvalidCharacters(args[2]);

                    if (!invalidChars.isEmpty()) {
                        Message.sendMsg(pl, String.format("&cНедопустимые символы: %s", invalidChars));
                        return true;
                    }

                    AirDrop air = BAirDrop.airDrops.get(args[1]).clone(args[2]);
                    if (args.length >= 4) {
                        if (args[3].equals("-temp")) {
                            air.setClone(true);
                            air.setKill(true);
                        }
                    }

                    if (!air.isClone()) {
                        air.createFile();
                        air.setSuperName(args[2]);
                        air.save();
                    }
                    airDrops.put(air.getId(), air);
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("airdrop-crated"));
                } else {
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                }
                return true;

            }

            if (args[0].equals("tp")) {
                if (!pl.hasPermission("bair.tp")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    if (!BAirDrop.airDrops.get(args[1]).isAirDropStarted()) {
                        Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("airdrop-is-not-started"));
                        return true;
                    }
                    pl.teleport(Objects.requireNonNull(airDrops.get(args[1]).getAirDropLocation()));
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("teleportation"));
                } else {
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                }
                return true;

            }
            if (args[0].equals("listeners")) {
                if (!pl.hasPermission("bair.listeners")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    ShowAllListeners sae = new ShowAllListeners(BAirDrop.airDrops.get(args[1]));
                    getServer().getPluginManager().registerEvents(sae, BAirDrop.getInstance());

                    pl.openInventory(sae.getInventory());
                } else {
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                }
                return true;

            }
            if (args[0].equals("generate")) {
                if (!pl.hasPermission("bair.generate")) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("no-prem"));
                    return true;
                }
                if (args.length < 3) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("generate-using"));
                    return true;
                }
                if (args[2].equals("stop")) {
                    GeneratorLoc.Stop(pl);
                    return true;
                } else if (args.length < 4) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("generate-using"));
                }
                if (!BAirDrop.airDrops.containsKey(args[1])) {
                    Message.sendMsg(pl, String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                    return true;
                }
                int timings;
                int count;
                try {
                    timings = Integer.parseInt(args[2]);
                    count = Integer.parseInt(args[3]);
                } catch (NumberFormatException e) {
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("not-a-number"));
                    Message.sendMsg(pl, BAirDrop.getConfigMessage().getMessage("generate-using"));
                    return true;
                }
                GeneratorLoc.Start(BAirDrop.airDrops.get(args[1]), timings, count, pl);
                return true;
            }
        } else {
            if (args.length == 0) {
                Message.logger(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                return true;
            }
            if (args[0].equals("delete")) {
                if (args.length == 1) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    airDrops.get(args[1]).notifyObservers(CustomEvent.UNLOAD, null);
                    if (!airDrops.get(args[1]).delete()) {
                        Message.logger(BAirDrop.getConfigMessage().getMessage("delete-fail"));
                    } else {
                        airDrops.get(args[1]).unload();
                        Message.logger(BAirDrop.getConfigMessage().getMessage("delete-good"));
                    }

                    return true;
                } else {
                    Message.logger(String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                }
                return true;
            }
            if (args[0].equals("reload")) {
                long x = System.currentTimeMillis();
                BAirDrop.reload();
                Message.logger(String.format(BAirDrop.getConfigMessage().getMessage("reload"), System.currentTimeMillis() - x));
                return true;
            }

            if (args[0].equals("create")) {
                if (args.length == 1) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                InvalidCharactersChecker invalidCharactersChecker = new InvalidCharacters();
                String invalidChars = invalidCharactersChecker.getInvalidCharacters(args[1]);

                if (!invalidChars.isEmpty()) {
                    Message.error( String.format("Недопустимые символы: %s", invalidChars));
                    return true;
                }

                BAirDrop.airDrops.put(args[1], new CAirDrop(args[1]));
                Message.logger(String.format(BAirDrop.getConfigMessage().getMessage("air-create"), args[1]));
                return true;
            }
            if (args[0].equals("start")) {
                if (args.length == 1) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    if (BAirDrop.airDrops.get(args[1]).isAirDropStarted()) {
                        Message.logger(BAirDrop.getConfigMessage().getMessage("airdrop-is-started"));
                        return true;
                    }
                    Location location = null;
                    if (args.length >= 5) {//bair 0start 1air 2x 3y 4z
                        try {
                            int x = Integer.parseInt(args[2]);
                            int y = Integer.parseInt(args[3]);
                            int z = Integer.parseInt(args[4]);
                            World world;
                            if (args.length >= 6){
                                world = Bukkit.getWorld(args[5]);
                            }else {
                                world = BAirDrop.airDrops.get(args[1]).getWorld();
                            }
                            if (world == null){
                                world = BAirDrop.airDrops.get(args[1]).getWorld();
                            }
                            location = new Location(world, x, y, z);
                            BAirDrop.airDrops.get(args[1]).setAirDropLocation(location);
                            BAirDrop.airDrops.get(args[1]).setFutureLocation(location);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    BAirDrop.airDrops.get(args[1]).startCommand(null);
                    Message.logger(BAirDrop.getConfigMessage().getMessage("starting"));
                } else
                    Message.logger(String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                return true;
            }
            if (args[0].equals("stop")) {
                if (args.length == 1) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    if (!BAirDrop.airDrops.get(args[1]).isAirDropStarted()) {
                        Message.logger(BAirDrop.getConfigMessage().getMessage("airdrop-is-not-started"));
                        return true;
                    }
                    Message.logger(BAirDrop.getConfigMessage().getMessage("end"));
                    BAirDrop.airDrops.get(args[1]).End();

                } else
                    Message.logger(String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                return true;
            }


            if (args[0].equals("clone")) {////bair clone <air> <newId> -temp
                if (args.length < 2) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }

                if (BAirDrop.airDrops.containsKey(args[1])) {

                    InvalidCharactersChecker invalidCharactersChecker = new InvalidCharacters();
                    String invalidChars = invalidCharactersChecker.getInvalidCharacters(args[2]);

                    if (!invalidChars.isEmpty()) {
                        Message.error(String.format("Недопустимые символы: %s", invalidChars));
                        return true;
                    }

                    AirDrop air = BAirDrop.airDrops.get(args[1]).clone(args[2]);
                    if (args.length == 4) {
                        if (args[3].equals("-temp")) {
                            air.setClone(true);
                            air.setKill(true);
                        }
                    }
                    if (!air.isClone())
                        air.createFile();
                    airDrops.put(air.getId(), air);
                    Message.logger(BAirDrop.getConfigMessage().getMessage("airdrop-crated"));
                    return true;
                } else {
                    Message.logger(String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                }
            }

            if (args[0].equals("generate")) {

                if (args.length < 3) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    Message.logger(BAirDrop.getConfigMessage().getMessage("generate-using"));
                }
                if (args[2].equals("stop")) {
                    GeneratorLoc.Stop(null);
                    return true;
                } else if (args.length < 4) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    Message.logger(BAirDrop.getConfigMessage().getMessage("generate-using"));
                }
                if (!BAirDrop.airDrops.containsKey(args[1])) {
                    Message.logger(String.format(BAirDrop.getConfigMessage().getMessage("unknown-airdrop"), args[1]));
                    return true;
                }
                int timings;
                int count;
                try {
                    timings = Integer.parseInt(args[2]);
                    count = Integer.parseInt(args[3]);
                } catch (NumberFormatException e) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("not-a-number"));
                    Message.logger(BAirDrop.getConfigMessage().getMessage("generate-using"));
                    return true;
                }
                GeneratorLoc.Start(BAirDrop.airDrops.get(args[1]), timings, count, null);
                return true;
            }
            if (args[0].equals("get")) {//bair get item name count
                if (args.length == 1) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                if (!BAirDrop.summoner.getItems().containsKey(args[1])) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("unknown-item"));
                    return true;
                }
                if (args.length >= 3) {
                    ItemStack item = BAirDrop.summoner.getItems().get(args[1]).getItem();
                    int amount = 1;
                    if (args.length == 4) {
                        try {
                            amount = Integer.parseInt(args[3]);
                            item.setAmount(amount);
                        } catch (NumberFormatException e) {
                            item.setAmount(1);
                        }
                    }
                    Player player = Bukkit.getPlayer(args[2]);
                    if (player == null) {
                        Message.logger(BAirDrop.getConfigMessage().getMessage("unknown-player"));
                        return true;
                    }
                    if (player.getInventory().firstEmpty() == -1) {
                        player.getLocation().getWorld().dropItem(player.getLocation(), item);
                        Message.logger(String.format(BAirDrop.getConfigMessage().getMessage("get-item"), player.getName(), args[1], amount));
                        //  Message.logger("{PP} &fИгроку " + player.getName() + " был выдан " + args[1] + " x" + amount);
                    } else {
                        player.getInventory().addItem(item);
                        Message.logger(String.format(BAirDrop.getConfigMessage().getMessage("get-item"), player.getName(), args[1], amount));
                        // Message.logger("{PP} &fИгроку " + player.getName() + " был выдан " + args[1] + " x" + amount);
                    }
                    return true;
                }
                return true;
            }
            if (args[0].equals("js")) { // /bair js fire.js <player name>
                Player player = null;
                if (args.length < 2) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("few-arguments"));
                    return true;
                }
                if (!BAirDrop.getiConfig().getScripts().containsKey(args[1])) {
                    Message.logger(String.format(BAirDrop.getConfigMessage().getMessage("unknown-script"), args[1]));
                }
                if (args.length == 3) {
                    player = Bukkit.getPlayer(args[2]);
                }
                Player finalPlayer = player;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("player", finalPlayer);
                        Script manager = new JsScript();
                        manager.runScript(args[1], map);
                    }
                }.runTask(getInstance());
                return true;
            }
            if (args[0].equals("compass")) {
                Player player = null;
                int amount = 1;
                if (args.length > 1)
                    player = Bukkit.getPlayer(args[1]);
                try {
                    if (args.length > 2)
                        amount = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (player == null) {
                    Message.logger(BAirDrop.getConfigMessage().getMessage("unknown-player"));
                    return true;
                }

                ItemStack compass = Compass.item.clone();
                compass.setAmount(amount);

                if (player.getInventory().firstEmpty() == -1)
                    player.getLocation().getWorld().dropItem(player.getLocation(), compass);
                else
                    player.getInventory().addItem(compass);
                return true;
            }
        }
        return false;
    }
}
