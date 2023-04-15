package org.by1337.bairdrop.command;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.Listeners.Compass;
import org.by1337.bairdrop.menu.EditAirMenu;
import org.by1337.bairdrop.menu.ShowAllListeners;
import org.by1337.bairdrop.scripts.Manager;
import org.by1337.bairdrop.util.Events;
import org.by1337.bairdrop.util.GeneratorLoc;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static org.bukkit.Bukkit.getServer;
import static org.by1337.bairdrop.BAirDrop.airDrops;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player pl) {
            if (args.length == 0) {
                Message.sendMsg(pl, Config.getMessage("few-arguments"));
                return true;
            }
            if (args[0].equals("compass")) {
                if (!pl.hasPermission("bair.compass")) {
                    Message.sendMsg(pl, Config.getMessage("no-prem"));
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
            if (args[0].equals("delete")) {
                if (!pl.hasPermission("bair.delete")) {
                    Message.sendMsg(pl, Config.getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.sendMsg(pl, Config.getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    airDrops.get(args[1]).event(Events.UNLOAD, null);
                    if (!airDrops.get(args[1]).Delete())
                        Message.sendMsg(pl, Config.getMessage("delete-fail"));
                    else
                        Message.sendMsg(pl, Config.getMessage("delete-good"));

                    return true;
                } else {
                    Message.sendMsg(pl, String.format(Config.getMessage("unknown-airdrop"), args[1]));
                }
                return true;
            }
            if (args[0].equals("get")) {//bair get item name count
                if (args.length == 1) {
                    Message.sendMsg(pl, Config.getMessage("few-arguments"));
                    return true;
                }
                if (!pl.hasPermission("bair.get")) {
                    Message.sendMsg(pl, Config.getMessage("no-prem"));
                    return true;
                }
                if (!BAirDrop.summoner.getItems().containsKey(args[1])) {
                    Message.sendMsg(pl, Config.getMessage("unknown-item"));
                    return true;
                }
                if (args.length >= 3) {
                    //  Message.sendMsg(pl, Config.getMessage("few-arguments"));
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
                        Message.sendMsg(pl, String.format(Config.getMessage("get-item"), player.getName(), args[1], amount));
                       // Message.sendMsg(pl,"{PP} &fИгроку " + player.getName() + " был выдан " + args[1] + " x" + amount);
                    } else {
                        player.getInventory().addItem(item);
                        Message.sendMsg(pl, String.format(Config.getMessage("get-item"), player.getName(), args[1], amount));
                       // Message.sendMsg(pl,"{PP} &fИгроку " + player.getName() + " был выдан " + args[1] + " x" + amount);
                    }
                    return true;
                }
                if (pl.getInventory().firstEmpty() == -1) {
                    pl.getLocation().getWorld().dropItem(pl.getLocation(), BAirDrop.summoner.getItems().get(args[1]).getItem());
                    Message.sendMsg(pl, String.format(Config.getMessage("get-item"), pl.getName(), args[1], 1));
                   // Message.sendMsg(pl,"{PP} &fИгроку " + pl.getName() + " был выдан " + args[1] + " x1");
                } else {
                    pl.getInventory().addItem(BAirDrop.summoner.getItems().get(args[1]).getItem());
                    Message.sendMsg(pl, String.format(Config.getMessage("get-item"), pl.getName(), args[1], 1));
                   // Message.sendMsg(pl,"{PP} &fИгроку " + pl.getName() + " был выдан " + args[1] + " x1");
                }
                return true;
            }
            if (args[0].equals("reload")) {
                if (!pl.hasPermission("bair.reload")) {
                    Message.sendMsg(pl, Config.getMessage("no-prem"));//message.yml
                    return true;
                }
                long x = System.currentTimeMillis();
                BAirDrop.reload();
                Message.sendMsg(pl, String.format(Config.getMessage("reload"), System.currentTimeMillis() - x));
                return true;
            }

            if (args[0].equals("menu")) {
                if (!pl.hasPermission("bair.menu")) {
                    Message.sendMsg(pl, Config.getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.sendMsg(pl, Config.getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    if (BAirDrop.airDrops.get(args[1]).getEditAirMenu() != null) {
                        BAirDrop.airDrops.get(args[1]).getEditAirMenu().unReg();
                    }
                    EditAirMenu em = new EditAirMenu(BAirDrop.airDrops.get(args[1]));
                    getServer().getPluginManager().registerEvents(em, BAirDrop.instance);
                    BAirDrop.airDrops.get(args[1]).setEditAirMenu(em);
                    pl.openInventory(em.getInventory());

                } else {
                    Message.sendMsg(pl, String.format(Config.getMessage("unknown-airdrop"), args[1]));
                }
                return true;
            }
            if (args[0].equals("create")) {

                if (!pl.hasPermission("bair.create")) {
                    Message.sendMsg(pl, Config.getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.sendMsg(pl, Config.getMessage("few-arguments"));
                    return true;
                }
                BAirDrop.airDrops.put(args[1], new AirDrop(args[1]));
                Message.sendMsg(pl, String.format(Config.getMessage("air-create"), args[1]));
                return true;
            }
            if (args[0].equals("start")) {
                if (!pl.hasPermission("bair.start")) {
                    Message.sendMsg(pl, Config.getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.sendMsg(pl, Config.getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    if (BAirDrop.airDrops.get(args[1]).isAirDropStarted()) {
                        Message.sendMsg(pl, Config.getMessage("airdrop-is-started"));
                        return true;
                    }
                    BAirDrop.airDrops.get(args[1]).startCommand();
                    Message.sendMsg(pl, Config.getMessage("starting"));
                } else
                    Message.sendMsg(pl, String.format(Config.getMessage("unknown-airdrop"), args[1]));
                return true;
            }
            if (args[0].equals("stop")) {
                if (!pl.hasPermission("bair.stop")) {
                    Message.sendMsg(pl, Config.getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.sendMsg(pl, Config.getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    if (!BAirDrop.airDrops.get(args[1]).isAirDropStarted()) {
                        Message.sendMsg(pl, Config.getMessage("airdrop-is-not-started"));
                        return true;
                    }
                    Message.sendMsg(pl, Config.getMessage("end"));
                    BAirDrop.airDrops.get(args[1]).End();

                } else
                    Message.sendMsg(pl, String.format(Config.getMessage("unknown-airdrop"), args[1]));
                return true;
            }


            if (args[0].equals("clone")) {
                if (!pl.hasPermission("bair.clone")) {
                    Message.sendMsg(pl, Config.getMessage("no-prem"));
                    return true;
                }
                if (args.length < 2) {
                    Message.sendMsg(pl, Config.getMessage("few-arguments"));
                    return true;
                }

                if (BAirDrop.airDrops.containsKey(args[1])) {
                    AirDrop air = BAirDrop.airDrops.get(args[1]).clone(args[2]);
                    if (args.length == 4) {
                        if (args[3].equals("-temp")) {
                            air.setClone(true);
                            air.setKill(true);
                        }
                    }
                    if (!air.isClone())
                        air.createFile();
                    airDrops.put(air.getAirId(), air);
                    Message.sendMsg(pl, Config.getMessage("airdrop-crated"));
                } else {
                    Message.sendMsg(pl, String.format(Config.getMessage("unknown-airdrop"), args[1]));
                }
                return true;

            }

            if (args[0].equals("tp")) {
                if (!pl.hasPermission("bair.tp")) {
                    Message.sendMsg(pl, Config.getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.sendMsg(pl, Config.getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    if (!BAirDrop.airDrops.get(args[1]).isAirDropStarted()) {
                        Message.sendMsg(pl, Config.getMessage("airdrop-is-not-started"));
                        return true;
                    }
                    pl.teleport(Objects.requireNonNull(airDrops.get(args[1]).getAirLoc()));
                    Message.sendMsg(pl, Config.getMessage("teleportation"));
                } else {
                    Message.sendMsg(pl, String.format(Config.getMessage("unknown-airdrop"), args[1]));
                }
                return true;

            }
            if (args[0].equals("listeners")) {
                if (!pl.hasPermission("bair.listeners")) {
                    Message.sendMsg(pl, Config.getMessage("no-prem"));
                    return true;
                }
                if (args.length == 1) {
                    Message.logger(Config.getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    ShowAllListeners sae = new ShowAllListeners(BAirDrop.airDrops.get(args[1]));
                    getServer().getPluginManager().registerEvents(sae, BAirDrop.instance);

                    pl.openInventory(sae.getInventory());
                } else {
                    Message.sendMsg(pl, String.format(Config.getMessage("unknown-airdrop"), args[1]));
                }
                return true;

            }
            if (args[0].equals("generate")) {
                if (!pl.hasPermission("bair.generate")) {
                    Message.sendMsg(pl, Config.getMessage("no-prem"));
                    return true;
                }
                if (args.length < 3) {
                    Message.sendMsg(pl, Config.getMessage("few-arguments"));
                    Message.sendMsg(pl, Config.getMessage("generate-using"));
                    return true;
                }
                if (args[2].equals("stop")) {
                    GeneratorLoc.Stop(pl);
                    return true;
                } else if (args.length < 4) {
                    Message.sendMsg(pl, Config.getMessage("few-arguments"));
                    Message.sendMsg(pl, Config.getMessage("generate-using"));
                }
                if (!BAirDrop.airDrops.containsKey(args[1])) {
                    Message.sendMsg(pl, String.format(Config.getMessage("unknown-airdrop"), args[1]));
                    return true;
                }
                int timings;
                int count;
                try {
                    timings = Integer.parseInt(args[2]);
                    count = Integer.parseInt(args[3]);
                } catch (NumberFormatException e) {
                    Message.sendMsg(pl, Config.getMessage("not-a-number"));
                    Message.sendMsg(pl, Config.getMessage("generate-using"));
                    return true;
                }
                GeneratorLoc.Start(BAirDrop.airDrops.get(args[1]), timings, count, pl);
                return true;
            }
        } else {
            if (args.length == 0) {
                Message.logger(Config.getMessage("few-arguments"));
                return true;
            }
            if (args[0].equals("delete")) {
                if (args.length == 1) {
                    Message.logger(Config.getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    airDrops.get(args[1]).event(Events.UNLOAD, null);
                    if (!airDrops.get(args[1]).Delete())
                        Message.logger(Config.getMessage("delete-fail"));
                    else
                        Message.logger(Config.getMessage("delete-good"));

                    return true;
                } else {
                    Message.logger(String.format(Config.getMessage("unknown-airdrop"), args[1]));
                }
                return true;
            }
            if (args[0].equals("reload")) {
                long x = System.currentTimeMillis();
                BAirDrop.reload();
                Message.logger(String.format(Config.getMessage("reload"), System.currentTimeMillis() - x));
                return true;
            }

            if (args[0].equals("create")) {
                if (args.length == 1) {
                    Message.logger(Config.getMessage("few-arguments"));
                    return true;
                }
                BAirDrop.airDrops.put(args[1], new AirDrop(args[1]));
                Message.logger(String.format(Config.getMessage("air-create"), args[1]));
                return true;
            }
            if (args[0].equals("start")) {
                if (args.length == 1) {
                    Message.logger(Config.getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    if (BAirDrop.airDrops.get(args[1]).isAirDropStarted()) {
                        Message.logger(Config.getMessage("airdrop-is-started"));
                        return true;
                    }
                    BAirDrop.airDrops.get(args[1]).startCommand();
                    Message.logger(Config.getMessage("starting"));
                } else
                    Message.logger(String.format(Config.getMessage("unknown-airdrop"), args[1]));
                return true;
            }
            if (args[0].equals("stop")) {
                if (args.length == 1) {
                    Message.logger(Config.getMessage("few-arguments"));
                    return true;
                }
                if (BAirDrop.airDrops.containsKey(args[1])) {
                    if (!BAirDrop.airDrops.get(args[1]).isAirDropStarted()) {
                        Message.logger(Config.getMessage("airdrop-is-not-started"));
                        return true;
                    }
                    Message.logger(Config.getMessage("end"));
                    BAirDrop.airDrops.get(args[1]).End();

                } else
                    Message.logger(String.format(Config.getMessage("unknown-airdrop"), args[1]));
                return true;
            }


            if (args[0].equals("clone")) {////bair clone <air> <newId> -temp
                if (args.length < 2) {
                    Message.logger(Config.getMessage("few-arguments"));
                    return true;
                }

                if (BAirDrop.airDrops.containsKey(args[1])) {
                    AirDrop air = BAirDrop.airDrops.get(args[1]).clone(args[2]);
                    if (args.length == 4) {
                        if (args[3].equals("-temp")) {
                            air.setClone(true);
                            air.setKill(true);
                        }
                    }
                    if (!air.isClone())
                        air.createFile();
                    airDrops.put(air.getAirId(), air);
                    Message.logger(Config.getMessage("airdrop-crated"));
                    return true;
                } else {
                    Message.logger(String.format(Config.getMessage("unknown-airdrop"), args[1]));
                }
            }

            if (args[0].equals("generate")) {

                if (args.length < 3) {
                    Message.logger(Config.getMessage("few-arguments"));
                    Message.logger(Config.getMessage("generate-using"));
                }
                if (args[2].equals("stop")) {
                    GeneratorLoc.Stop(null);
                    return true;
                } else if (args.length < 4) {
                    Message.logger(Config.getMessage("few-arguments"));
                    Message.logger(Config.getMessage("generate-using"));
                }
                if (!BAirDrop.airDrops.containsKey(args[1])) {
                    Message.logger(String.format(Config.getMessage("unknown-airdrop"), args[1]));
                    return true;
                }
                int timings;
                int count;
                try {
                    timings = Integer.parseInt(args[2]);
                    count = Integer.parseInt(args[3]);
                } catch (NumberFormatException e) {
                    Message.logger(Config.getMessage("not-a-number"));
                    Message.logger(Config.getMessage("generate-using"));
                    return true;
                }
                GeneratorLoc.Start(BAirDrop.airDrops.get(args[1]), timings, count, null);
                return true;
            }
            if (args[0].equals("get")) {//bair get item name count
                if (args.length == 1) {
                    Message.logger(Config.getMessage("few-arguments"));
                    return true;
                }
                if (!BAirDrop.summoner.getItems().containsKey(args[1])) {
                    Message.logger(Config.getMessage("unknown-item"));
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
                        Message.logger(Config.getMessage("unknown-player"));
                        return true;
                    }
                    if (player.getInventory().firstEmpty() == -1) {
                        player.getLocation().getWorld().dropItem(player.getLocation(), item);
                        Message.logger(String.format(Config.getMessage("get-item"), player.getName(), args[1], amount));
                      //  Message.logger("{PP} &fИгроку " + player.getName() + " был выдан " + args[1] + " x" + amount);
                    } else {
                        player.getInventory().addItem(item);
                        Message.logger(String.format(Config.getMessage("get-item"), player.getName(), args[1], amount));
                       // Message.logger("{PP} &fИгроку " + player.getName() + " был выдан " + args[1] + " x" + amount);
                    }
                    return true;
                }
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
                    Message.logger(Config.getMessage("unknown-player"));
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
