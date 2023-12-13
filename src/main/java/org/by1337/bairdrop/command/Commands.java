package org.by1337.bairdrop.command;

import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.NumberConversions;
import org.by1337.api.chat.ChatColor;
import org.by1337.api.chat.ClickEvent;
import org.by1337.api.chat.ClickEventType;
import org.by1337.api.chat.Component;
import org.by1337.api.chat.ComponentBuilder;
import org.by1337.api.chat.hover.HoverEvent;
import org.by1337.api.chat.hover.HoverEventContentsString;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.CommandSyntaxError;
import org.by1337.api.command.argument.*;
import org.by1337.api.command.requires.RequiresPermission;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.CAirDrop;
import org.by1337.bairdrop.listeners.Compass;
import org.by1337.bairdrop.menu.EditAirMenu;
import org.by1337.bairdrop.menu.SelectAirMenu;
import org.by1337.bairdrop.menu.ShowAllListeners;
import org.by1337.bairdrop.scripts.JsScript;
import org.by1337.bairdrop.locationGenerator.GeneratorLoc;
import org.by1337.bairdrop.scripts.Script;
import org.by1337.bairdrop.summoner.SummonerItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.util.*;

import static org.bukkit.Bukkit.getServer;

public class Commands implements CommandExecutor, TabCompleter {

    private final Command<CommandSender> command;

    public Commands() {
        this.command = new Command<>("main");
        initCommands();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command cmd, @NotNull String label, @NotNull String[] args) {
        try {
            command.process(sender, args);
            return true;
        } catch (CommandException e) {
            sender.sendMessage(e.getLocalizedMessage());
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command cmd, @NotNull String alias, @NotNull String[] args) {
        return command.getTabCompleter(sender, args);
    }

    private void initCommands() {
        command.addSubCommand(new Command<CommandSender>("help")
                        .requires(new RequiresPermission<>("bair.help"))
                        .executor(((sender, args) -> BAirDrop.getMessage().sendMsg(sender, String.join("\n", BAirDrop.getConfigMessage().getList("help-list")))))

                )
                .addSubCommand(new Command<CommandSender>("list")
                        .requires(new RequiresPermission<>("bair.list"))
                        .executor(((sender, args) -> {
                            ComponentBuilder componentBuilder = new ComponentBuilder().component(new Component("\n"));
                            int x = 0;
                            for (String airDrop : BAirDrop.airDrops.keySet()) {
                                componentBuilder.component(new Component(new ChatColor(Color.WHITE), String.format("%s. %s\n", x, airDrop))
                                        .hoverEvent(new HoverEvent(new HoverEventContentsString(String.format("/bair menu %s", airDrop))))
                                        .clickEvent(new ClickEvent(ClickEventType.RUN_COMMAND, String.format("/bair menu %s", airDrop))));
                                x++;
                            }
                            BAirDrop.getMessage().sendRawMsg(sender, componentBuilder);
                        }))
                )
                .addSubCommand(new Command<CommandSender>("compass")
                        .requires(new RequiresPermission<>("bair.compass"))
                        .addSubCommand(new Command<CommandSender>("remove")
                                .requires(new RequiresPermission<>("bair.compass.remove"))
                                .argument(new ArgumentPlayer<>("target", List.of("[player]"))))
                        .argument(new ArgumentInteger<>("amount", List.of("[count]"), 1))
                        .executor(((sender, args) -> {
                            int removed = 0;
                            int amount = (int) args.getOrDefault("amount", 1);
                            Player player = (Player) args.get("target");
                            if (player == null && !(sender instanceof Player)) {
                                throw new CommandSyntaxError("&cSpecify a player for this command when executing from the console!");
                            } else if (player == null) {
                                player = (Player) sender;
                            }
                            for (int slot = 0; slot < player.getInventory().getSize(); slot++) {
                                ItemStack itemStack = player.getInventory().getItem(slot);
                                if (itemStack == null || itemStack.getItemMeta() == null) continue;
                                if (itemStack.getItemMeta().getPersistentDataContainer().has(Compass.COMPASS_TAG, PersistentDataType.STRING)) {
                                    if (amount < itemStack.getAmount()) {
                                        itemStack.setAmount(amount);
                                        break;
                                    }
                                    removed += itemStack.getAmount();
                                    amount -= itemStack.getAmount();
                                    player.getInventory().setItem(slot, null);
                                }
                            }
                            BAirDrop.getMessage().sendMsg(sender, String.format("%s items were removed from player %s", removed, player.getName()));
                        }))
                        .addSubCommand(new Command<CommandSender>("give")
                                .requires(new RequiresPermission<>("bair.compass.give"))
                                .argument(new ArgumentPlayer<>("target", List.of("[player]")))
                                .argument(new ArgumentInteger<>("amount", List.of("[count]"), 1, 64))
                                .executor(((sender, args) -> {
                                    int amount = (int) args.getOrDefault("amount", 1);
                                    Player player = (Player) args.getOrDefault("target", null);

                                    if (player == null && !(sender instanceof Player)) {
                                        throw new CommandSyntaxError("&cSpecify a player for this command when executing from the console!");
                                    } else if (player == null) {
                                        player = (Player) sender;
                                    }
                                    ItemStack itemStack = Compass.item.clone();
                                    itemStack.setAmount(amount);
                                    List<ItemStack> notGave = player.getInventory().addItem(itemStack).values().stream().toList();
                                    for (ItemStack i : notGave) {
                                        player.getLocation().getWorld().dropItem(player.getLocation(), i);
                                    }
                                    BAirDrop.getMessage().sendMsg(sender, String.format("%s items were given to player %s", amount, player.getName()));
                                }))
                        )
                )
                .addSubCommand(new Command<CommandSender>("delete")
                        .requires(new RequiresPermission<>("bair.delete"))
                        .argument(new ArgumentSetList<>("air", () -> BAirDrop.airDrops.keySet().stream().toList()))
                        .executor(((sender, args) -> {
                            String airId = (String) CommandSyntaxError.ifNull(args.get("air"), "AirDrop must be specified!");
                            AirDrop airDrop = CommandSyntaxError.ifNull(BAirDrop.airDrops.get(airId), "AirDrop '%s' not found!", airId);
                            airDrop.unload();
                            if (airDrop.delete()) {
                                BAirDrop.getMessage().sendMsg(sender, "&aAirDrop '%s' successfully deleted", airId);
                            } else {
                                BAirDrop.getMessage().sendMsg(sender, "&cDeletion failed");
                            }
                        }))
                )
                .addSubCommand(new Command<CommandSender>("summoner")
                        .requires(new RequiresPermission<>("bair.summoner"))
                        .addSubCommand(new Command<CommandSender>("give")
                                .requires(new RequiresPermission<>("bair.summoner.give"))
                                .argument(new ArgumentSetList<>("item", () -> BAirDrop.summoner.getItems().keySet().stream().toList()))
                                .argument(new ArgumentInteger<>("amount", List.of("[amount]"), 1, 64))
                                .argument(new ArgumentPlayer<>("target", List.of("[player]")))
                                .executor((sender, args) -> {
                                    String item = (String) CommandSyntaxError.ifNull(args.get("item"), "Item must be specified!");
                                    SummonerItem summonerItem = CommandSyntaxError.ifNull(BAirDrop.summoner.getItems().get(item), "Item '%c' not found!", item);
                                    int amount = (int) args.getOrDefault("amount", 1);
                                    Player player = (Player) args.getOrDefault("target", null);

                                    ItemStack itemStack = summonerItem.getItem();
                                    itemStack.setAmount(amount);

                                    if (player == null && !(sender instanceof Player)) {
                                        throw new CommandSyntaxError("&cSpecify a player for this command when executing from the console!");
                                    } else if (player == null) {
                                        player = (Player) sender;
                                    }
                                    List<ItemStack> notGave = player.getInventory().addItem(itemStack).values().stream().toList();
                                    for (ItemStack i : notGave) {
                                        player.getLocation().getWorld().dropItem(player.getLocation(), i);
                                    }
                                    BAirDrop.getMessage().sendMsg(sender, String.format("%s items were given to player %s", amount, player.getName()));
                                })
                        )
                )
                .addSubCommand(new Command<CommandSender>("reload")
                        .requires(new RequiresPermission<>("bair.reload"))
                        .executor((sender, args) -> {
                            long x = System.currentTimeMillis();
                            BAirDrop.reload();
                            BAirDrop.getMessage().sendMsg(sender, "&aPlugin successfully reloaded in %s milliseconds!", (System.currentTimeMillis() - x));
                        })
                )
                .addSubCommand(new Command<CommandSender>("menu")
                        .requires(new RequiresPermission<>("bair.menu"))
                        .argument(new ArgumentSetList<>("air", () -> BAirDrop.airDrops.keySet().stream().toList()))
                        .argument(new ArgumentPlayer<>("target", List.of("[player]")))
                        .executor(((sender, args) -> {

                            String airId = (String) args.get("air");

                            AirDrop airDrop = BAirDrop.airDrops.get(airId);

                            Player player = (Player) args.getOrDefault("target", null);

                            if (player == null && !(sender instanceof Player)) {
                                throw new CommandSyntaxError("&cSpecify a player for this command when executing from the console!");
                            } else if (player == null) {
                                player = (Player) sender;
                            }
                            if (airDrop == null) {
                                SelectAirMenu selectAirMenu = new SelectAirMenu();
                                getServer().getPluginManager().registerEvents(selectAirMenu, BAirDrop.getInstance());
                                player.openInventory(selectAirMenu.getInventory());
                            } else {
                                if (airDrop.getEditAirMenu() != null)
                                    airDrop.getEditAirMenu().unReg();
                                EditAirMenu em = new EditAirMenu(airDrop);
                                airDrop.setEditAirMenu(em);
                                player.openInventory(em.getInventory());
                            }
                        }))
                )
                .addSubCommand(new Command<CommandSender>("create")
                        .requires(new RequiresPermission<>("bair.create"))
                        .argument(new ArgumentValidCharacters<>("name", List.of("[id]")))
                        .executor(((sender, args) -> {
                            String airId = (String) args.getOrThrow("name", "&cMissing id");
                            CommandSyntaxError.ifNotNull(BAirDrop.airDrops.get(airId), "AirDrop '%s' already exists!", airId);

                            BAirDrop.airDrops.put(airId, new CAirDrop(airId));
                            BAirDrop.getMessage().sendMsg(sender, "AirDrop '%s' already exists!", airId);
                        }))
                )
                .addSubCommand(new Command<CommandSender>("start")
                        .requires(new RequiresPermission<>("bair.start"))
                        .argument(new ArgumentSetList<>("air", () -> BAirDrop.airDrops.keySet().stream().toList()))
                        .argument(new ArgumentPosition<>("posX", new ArrayList<>(List.of("~", "~ ~ ~")), ArgumentPosition.ArgumentPositionType.X))
                        .argument(new ArgumentPosition<>("posY", new ArrayList<>(List.of("~", "~ ~")), ArgumentPosition.ArgumentPositionType.Y))
                        .argument(new ArgumentPosition<>("posZ", new ArrayList<>(List.of("~")), ArgumentPosition.ArgumentPositionType.Z))
                        .argument(new ArgumentWorld<>("world"))
                        .argument(new ArgumentInteger<>("time", List.of("[time to start]")))
                        .executor(((sender, args) -> {

                            String airId = (String) CommandSyntaxError.ifNull(args.get("air"), "AirDrop must be specified!");
                            AirDrop airDrop = CommandSyntaxError.ifNull(BAirDrop.airDrops.get(airId), "AirDrop '%s' not found!", airId);

                            if (airDrop.isAirDropStarted())
                                throw new CommandSyntaxError("%s AirDrop is already started!", airId);

                            int locX = NumberConversions.floor(((Double) args.getOrThrow("posX", "Location %s must be specified!", "x")));
                            int locY = NumberConversions.floor(((Double) args.getOrThrow("posY", "Location %s must be specified!", "y")));
                            int locZ = NumberConversions.floor(((Double) args.getOrThrow("posZ", "Location %s must be specified!", "z")));


                            World world = (World) args.getOrDefault("world", airDrop.getWorld());

                            int time = (int) args.getOrDefault("time", 0);

                            Location location = new Location(world, locX, locY, locZ);
                            airDrop.setAirDropLocation(location);
                            airDrop.setFutureLocation(location);
                            if (time == 0) {
                                airDrop.startCommand(sender);
                                BAirDrop.getMessage().sendMsg(sender, "&aAirDrop %s started", airId);
                            } else {
                                airDrop.setTimeToStart(time);
                                BAirDrop.getMessage().sendMsg(sender, "&aSet spawn time to %s for AirDrop %s", time, airId);
                            }
                        }))
                )
                .addSubCommand(new Command<CommandSender>("stop")
                        .requires(new RequiresPermission<>("bair.stop"))
                        .argument(new ArgumentSetList<>("air", () -> BAirDrop.airDrops.keySet().stream().toList()))
                        .executor(((sender, args) -> {
                            String airId = (String) CommandSyntaxError.ifNull(args.get("air"), "AirDrop must be specified!");
                            AirDrop airDrop = CommandSyntaxError.ifNull(BAirDrop.airDrops.get(airId), "AirDrop '%s' not found!", airId);

                            if (airDrop.isAirDropStarted()) {
                                airDrop.end();
                                BAirDrop.getMessage().sendMsg(sender, "&aAirDrop %s stopped", airId);
                            } else {
                                BAirDrop.getMessage().sendMsg(sender, "&cAirDrop %s is not started!", airId);
                            }
                        }))
                )
                .addSubCommand(new Command<CommandSender>("clone")
                        .requires(new RequiresPermission<>("bair.clone"))
                        .argument(new ArgumentValidCharacters<>("newId", List.of("[id]")))
                        .argument(new ArgumentSetList<>("air", () -> BAirDrop.airDrops.keySet().stream().toList()))
                        .argument(new ArgumentSetList<>("isTemp", List.of("-temp")))
                        .executor(((sender, args) -> {
                            boolean temp = args.getOrDefault("isTemp", "no").equals("-temp");
                            String newId = (String) args.getOrThrow("newId", "New ID must be specified!");
                            String airId = (String) CommandSyntaxError.ifNull(args.get("air"), "AirDrop must be specified!");
                            AirDrop airDrop = CommandSyntaxError.ifNull(BAirDrop.airDrops.get(airId), "AirDrop '%s' not found!", airId);

                            AirDrop air = airDrop.clone(newId);
                            if (temp) {
                                air.setClone(true);
                                air.setKill(true);
                            }
                            if (!air.isClone()) {
                                air.createFile();
                                air.setSuperName(newId);
                                air.save();
                            }
                            BAirDrop.airDrops.put(air.getId(), air);
                            BAirDrop.getMessage().sendMsg(sender, "&aAirDrop '%s' successfully created!", air.getId());

                        }))
                )
                .addSubCommand(new Command<CommandSender>("tp")
                        .requires(new RequiresPermission<>("bair.tp"))
                        .argument(new ArgumentSetList<>("air", () -> BAirDrop.airDrops.keySet().stream().toList()))
                        .argument(new ArgumentPlayer<>("target", List.of("[player]")))
                        .executor(((sender, args) -> {
                            String airId = (String) CommandSyntaxError.ifNull(args.get("air"), "AirDrop must be specified!");
                            AirDrop airDrop = CommandSyntaxError.ifNull(BAirDrop.airDrops.get(airId), "AirDrop '%s' not found!", airId);

                            Player player = (Player) args.getOrDefault("target", null);

                            if (player == null && !(sender instanceof Player)) {
                                throw new CommandSyntaxError("&cSpecify a player for this command when executing from the console!");
                            } else if (player == null) {
                                player = (Player) sender;
                            }
                            if (!airDrop.isAirDropStarted()) {
                                if (airDrop.getFutureLocation() != null) {
                                    player.teleport(airDrop.getFutureLocation());
                                    BAirDrop.getMessage().sendMsg(sender, "&aAirDrop has not spawned yet! You are teleported to the preliminary location!");
                                    return;
                                }
                                BAirDrop.getMessage().sendMsg(sender, "&cLocation for spawn is not defined yet!");
                            } else {
                                player.teleport(airDrop.getAnyLoc());
                                BAirDrop.getMessage().sendMsg(sender, "&aYou have been teleported to AirDrop '%s'", airId);
                            }
                        }))
                )
                .addSubCommand(new Command<CommandSender>("listeners")
                        .requires(new RequiresPermission<>("bair.listeners"))
                        .argument(new ArgumentSetList<>("air", () -> BAirDrop.airDrops.keySet().stream().toList()))
                        .argument(new ArgumentPlayer<>("target", List.of("[player]")))
                        .executor(((sender, args) -> {
                            String airId = (String) CommandSyntaxError.ifNull(args.get("air"), "AirDrop must be specified!");
                            AirDrop airDrop = CommandSyntaxError.ifNull(BAirDrop.airDrops.get(airId), "AirDrop '%s' not found!", airId);

                            Player player = (Player) args.getOrDefault("target", null);

                            if (player == null && !(sender instanceof Player)) {
                                throw new CommandSyntaxError("&cSpecify a player for this command when executing from the console!");
                            } else if (player == null) {
                                player = (Player) sender;
                            }
                            ShowAllListeners sae = new ShowAllListeners(airDrop);
                            getServer().getPluginManager().registerEvents(sae, BAirDrop.getInstance());
                            player.openInventory(sae.getInventory());
                        }))
                )
                .addSubCommand(new Command<CommandSender>("generate")
                        .requires(new RequiresPermission<>("bair.generate"))
                        .addSubCommand(new Command<CommandSender>("start")
                                .requires(new RequiresPermission<>("bair.generate.start"))
                                .argument(new ArgumentSetList<>("air", () -> BAirDrop.airDrops.keySet().stream().toList()))
                                .argument(new ArgumentInteger<>("timings", List.of("[speed]"), 1, Integer.MAX_VALUE / 20))
                                .argument(new ArgumentInteger<>("count", List.of("[count]"), 1, Integer.MAX_VALUE / 20))
                                .executor(((sender, args) -> {
                                    String airId = (String) CommandSyntaxError.ifNull(args.get("air"), "AirDrop must be specified!");
                                    AirDrop airDrop = CommandSyntaxError.ifNull(BAirDrop.airDrops.get(airId), "AirDrop '%s' not found!", airId);
                                    int timings = (int) args.getOrDefault("timings", 1);
                                    int count = (int) args.getOrDefault("count", 1);
                                    GeneratorLoc.start(airDrop, timings, count, sender);
                                }))
                        )
                        .addSubCommand(new Command<CommandSender>("stop")
                                .requires(new RequiresPermission<>("bair.generate.stop"))
                                .executor(((sender, args) -> {
                                    GeneratorLoc.stop(sender);
                                }))
                        )
                )
                .addSubCommand(new Command<CommandSender>("js")
                        .requires(new RequiresPermission<>("bair.js"))
                        .argument(new ArgumentSetList<>("js", () -> BAirDrop.getiConfig().getScripts().keySet().stream().toList()))
                        .argument(new ArgumentPlayer<>("target", List.of("[player]")))
                        .executor((sender, args) -> {
                            Player player = (Player) args.get("target");
                            if (player == null && sender instanceof Player player1) {
                                player = player1;
                            }
                            String js = (String) args.get("js");
                            Player finalPlayer = player;
                            new BukkitRunnable() {
                                public void run() {
                                    HashMap<String, Object> map = new HashMap<>();
                                    if (finalPlayer != null)
                                        map.put("player", finalPlayer);
                                    Script manager = new JsScript();
                                    manager.runScript(js, map);
                                }
                            }.runTask(BAirDrop.getInstance());
                        })
                );
    }

}
