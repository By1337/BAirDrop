package org.by1337.bairdrop.airdrop.registry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.CAirDrop;
import org.by1337.bairdrop.airdrop.command.Command;
import org.by1337.bairdrop.airdrop.command.CommandSyntaxError;
import org.by1337.bairdrop.airdrop.command.argument.*;
import org.by1337.bairdrop.airdrop.command.requires.RequiresPermission;
import org.by1337.bairdrop.listeners.Compass;
import org.by1337.bairdrop.locationGenerator.GeneratorLoc;
import org.by1337.bairdrop.menu.EditAirMenu;
import org.by1337.bairdrop.menu.SelectAirMenu;
import org.by1337.bairdrop.menu.ShowAllListeners;
import org.by1337.bairdrop.summoner.SummonerItem;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.chat.*;
import org.by1337.lib.chat.Component;
import org.by1337.lib.chat.hover.HoverEvent;
import org.by1337.lib.chat.hover.HoverEventContentsString;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.bukkit.Bukkit.getServer;
import static org.by1337.bairdrop.BAirDrop.airDrops;

public class CommandFactory {
    private static CommandLang lang = new CommandLang();

    public static Command createMain() {
        return new Command("bairdrop")
                .aliases("bair")
                .aliases("air")
                .addSubCommand(new Command("help")
                        .requires(new RequiresPermission("bair.help"))
                        .executor(((sender, args) -> Message.sendRawMsg(sender, lang.rawHelp)))
                )
                .addSubCommand(new Command("list")
                        .requires(new RequiresPermission("bair.list"))
                        .executor(((sender, args) -> {
                            ComponentBuilder componentBuilder = new ComponentBuilder();
                            int x = 0;
                            for (String airDrop : airDrops.keySet()) {
                                componentBuilder.addComponent(new Component(new ChatColor(Color.GRAY), String.format("%s. %s", x, airDrop))
                                        .hoverEvent(new HoverEvent(new HoverEventContentsString(String.format("/bair menu %s", airDrop))))
                                        .clickEvent(new ClickEvent(ClickEventType.RUN_COMMAND, String.format("/bair menu %s", airDrop))));
                            }
                            Message.sendRawMsg(sender, componentBuilder);
                        }))
                )
                .addSubCommand(new Command("compass")
                        .requires(new RequiresPermission("bair.compass"))
                        .addSubCommand(new Command("remove")
                                .requires(new RequiresPermission("bair.compass.remove"))
                                .argument(new ArgumentPlayer("target", new ArrayList<>(List.of(lang.paramPlayer))))
                                .argument(new ArgumentInteger("amount", new ArrayList<>(List.of(lang.paramCount)), 1))
                                .executor(((sender, args) -> {
                                    int removed = 0;
                                    int amount = (int) args.getOrDefault("amount", 1);
                                    Player player = (Player) args.getOrDefault("target", null);
                                    if (player == null && !(sender instanceof Player)) {
                                        throw new CommandSyntaxError(lang.playerIsNotSelected);
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
                                    Message.sendMsg(sender, String.format(lang.removedItems, removed, player.getName()));
                                }))
                        )
                        .addSubCommand(new Command("give")
                                .requires(new RequiresPermission("bair.compass.give"))
                                .argument(new ArgumentPlayer("target", new ArrayList<>(List.of(lang.paramPlayer))))
                                .argument(new ArgumentInteger("amount", new ArrayList<>(List.of(lang.paramCount)), 1, 64))
                                .executor(((sender, args) -> {
                                    int amount = (int) args.getOrDefault("amount", 1);
                                    Player player = (Player) args.getOrDefault("target", null);

                                    if (player == null && !(sender instanceof Player)) {
                                        throw new CommandSyntaxError(lang.playerIsNotSelected);
                                    } else if (player == null) {
                                        player = (Player) sender;
                                    }
                                    ItemStack itemStack = Compass.item.clone();
                                    itemStack.setAmount(amount);
                                    List<ItemStack> notGave = player.getInventory().addItem(itemStack).values().stream().toList();
                                    for (ItemStack i : notGave) {
                                        player.getLocation().getWorld().dropItem(player.getLocation(), i);
                                    }

                                    Message.sendMsg(sender, String.format(lang.gaveItems, amount, player.getName()));
                                }))
                        )
                )
                .addSubCommand(new Command("delete")
                        .requires(new RequiresPermission("bair.delete"))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .executor(((sender, args) -> {

                            String airId = (String) CommandSyntaxError.ifNull(args.getOrDefault("air", null), lang.airIsNotSelected);

                            AirDrop airDrop = CommandSyntaxError.ifNull(airDrops.getOrDefault(airId, null), lang.airIsNotFound, airId);

                            airDrop.unload();
                            if (airDrop.delete()) {
                                Message.sendMsg(sender, lang.airDropDeleted, airId);
                            } else {
                                Message.sendMsg(sender, lang.airDropDeletedFail);
                            }
                        }))
                )
                .addSubCommand(new Command("summoner")
                        .requires(new RequiresPermission("bair.summoner"))
                        .addSubCommand(new Command("give")
                                .requires(new RequiresPermission("bair.give"))
                                .argument(new ArgumentSetList("item", BAirDrop.summoner.getItems().keySet().stream().toList()))
                                .argument(new ArgumentInteger("amount", new ArrayList<>(List.of(lang.paramCount)), 1, 64))
                                .argument(new ArgumentPlayer("target", new ArrayList<>(List.of(lang.paramPlayer))))
                                .executor((sender, args) -> {
                                    String item = (String) CommandSyntaxError.ifNull(args.getOrDefault("item", null), lang.paramErrItemIsNull);
                                    SummonerItem summonerItem = CommandSyntaxError.ifNull(BAirDrop.summoner.getItems().getOrDefault(item, null), lang.paramErrItemIsNotFound, item);
                                    int amount = (int) args.getOrDefault("amount", 1);
                                    Player player = (Player) args.getOrDefault("target", null);

                                    ItemStack itemStack = summonerItem.getItem();
                                    itemStack.setAmount(amount);

                                    if (player == null && !(sender instanceof Player)) {
                                        throw new CommandSyntaxError(lang.playerIsNotSelected);
                                    } else if (player == null) {
                                        player = (Player) sender;
                                    }
                                    List<ItemStack> notGave = player.getInventory().addItem(itemStack).values().stream().toList();
                                    for (ItemStack i : notGave) {
                                        player.getLocation().getWorld().dropItem(player.getLocation(), i);
                                    }
                                    Message.sendMsg(sender, String.format(lang.gaveItems, amount, player.getName()));
                                })
                        )

                )
                .addSubCommand(new Command("reload")
                        .requires(new RequiresPermission("bair.reload"))
                        .executor((sender, args) -> {
                            long x = System.currentTimeMillis();
                            BAirDrop.reload();
                            Message.sendMsg(sender, lang.reloaded, (System.currentTimeMillis() - x));
                        })
                )
                .addSubCommand(new Command("menu")
                        //.aliases("m")
                        .requires(new RequiresPermission("bair.menu"))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .argument(new ArgumentPlayer("target", new ArrayList<>(List.of(lang.paramPlayer))))
                        .executor(((sender, args) -> {

                            String airId = (String) args.getOrDefault("air", null);

                            AirDrop airDrop = airId == null ? null : airDrops.getOrDefault(airId, null);

                            Player player = (Player) args.getOrDefault("target", null);

                            if (player == null && !(sender instanceof Player)) {
                                throw new CommandSyntaxError(lang.playerIsNotSelected);
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
                .addSubCommand(new Command("create")
                        .requires(new RequiresPermission("bair.create"))
                        .argument(new ArgumentValidCharacters("name", Collections.singletonList(lang.airId)))
                        .executor(((sender, args) -> {
                            String airId = (String) args.getOrThrow("name", lang.airIsNotSelected);
                            CommandSyntaxError.ifNotNull(airDrops.getOrDefault(airId, null), lang.airIsAlreadyExists, airId);

                            airDrops.put(airId, new CAirDrop(airId));
                            Message.sendMsg(sender, lang.airCreated, airId);
                        }))
                )
                .addSubCommand(new Command("start")
                        .requires(new RequiresPermission("bair.start"))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .argument(new ArgumentPosition("posX", new ArrayList<>(List.of("~", "~ ~ ~")), ArgumentPosition.ArgumentPositionType.X))
                        .argument(new ArgumentPosition("posY", new ArrayList<>(List.of("~", "~ ~")), ArgumentPosition.ArgumentPositionType.Y))
                        .argument(new ArgumentPosition("posZ", new ArrayList<>(List.of("~")), ArgumentPosition.ArgumentPositionType.Z))
                        .argument(new ArgumentWorld("world"))
                        .argument(new ArgumentInteger("time", new ArrayList<>(List.of(lang.paramTimeToStart))))
                        .executor(((sender, args) -> {

                            String airId = (String) args.getOrThrow("air", lang.airIsNotSelected);

                            AirDrop airDrop = CommandSyntaxError.ifNull(airDrops.getOrDefault(airId, null), lang.airIsNotFound, airId);

                            if (airDrop.isAirDropStarted())
                                throw new CommandSyntaxError(lang.airIsAlreadyStarted, airId);

                            double locX = Math.ceil((Double) args.getOrThrow("posX", lang.paramLocIsNotSelected, "x"));
                            double locY = Math.ceil((Double) args.getOrThrow("posY", lang.paramLocIsNotSelected, "y"));
                            double locZ = Math.ceil((Double) args.getOrThrow("posZ", lang.paramLocIsNotSelected, "z"));

                            World world = (World) args.getOrDefault("world", airDrop.getWorld());

                            int time = (int) args.getOrDefault("time", 0);

                            Location location = new Location(world, locX, locY, locZ);
                            airDrop.setAirDropLocation(location);
                            airDrop.setFutureLocation(location);
                            if (time == 0) {
                                airDrop.startCommand(sender);
                                Message.sendMsg(sender, lang.airStarted, airId);
                            } else {
                                airDrop.setTimeToStart(time);
                                Message.sendMsg(sender, lang.setAirTimeToStart, time, airId);
                            }
                        }))
                )
                .addSubCommand(new Command("stop")
                        .requires(new RequiresPermission("bair.stop"))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .executor(((sender, args) -> {

                            String airId = (String) args.getOrThrow("air", lang.airIsNotSelected);

                            AirDrop airDrop = CommandSyntaxError.ifNull(airDrops.getOrDefault(airId, null), lang.airIsNotFound, airId);

                            if (airDrop.isAirDropStarted()) {
                                airDrop.end();
                                Message.sendMsg(sender, lang.airIsStopped, airId);
                            } else {
                                Message.sendMsg(sender, lang.airIsNotStarted, airId);
                            }

                        }))
                )
                .addSubCommand(new Command("clone")
                        .requires(new RequiresPermission("bair.clone"))
                        .argument(new ArgumentValidCharacters("newId", new ArrayList<>(List.of(lang.paramNewId))))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .argument(new ArgumentSetList("isTemp", new ArrayList<>(List.of("-temp"))))
                        .executor(((sender, args) -> {
                            boolean temp = args.getOrDefault("isTemp", "no").equals("-temp");
                            String newId = (String) args.getOrThrow("newId", lang.newIdIsNotSelected);
                            String airId = (String) args.getOrThrow("air", lang.airIsNotSelected);

                            AirDrop airDrop = CommandSyntaxError.ifNull(airDrops.getOrDefault(airId, null), lang.airIsNotFound, airId);

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
                            airDrops.put(air.getId(), air);
                            Message.sendMsg(sender, lang.airdropCreated, air.getId());

                        }))
                )
                .addSubCommand(new Command("tp")
                        .requires(new RequiresPermission("bair.tp"))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .argument(new ArgumentPlayer("target", new ArrayList<>(List.of(lang.paramPlayer))))
                        .executor(((sender, args) -> {

                            String airId = (String) args.getOrThrow("air", lang.airIsNotSelected);

                            AirDrop airDrop = CommandSyntaxError.ifNull(airDrops.getOrDefault(airId, null), lang.airIsNotFound, airId);

                            Player player = (Player) args.getOrDefault("target", null);

                            if (player == null && !(sender instanceof Player)) {
                                throw new CommandSyntaxError(lang.playerIsNotSelected);
                            } else if (player == null) {
                                player = (Player) sender;
                            }
                            if (!airDrop.isAirDropStarted()) {
                                if (airDrop.getFutureLocation() != null) {
                                    player.teleport(airDrop.getFutureLocation());
                                    Message.sendMsg(sender, lang.airDropIsNotSpawned);
                                    return;
                                }
                                Message.sendMsg(sender, lang.locIsNull);
                            } else {
                                player.teleport(airDrop.getAnyLoc());
                                Message.sendMsg(sender, lang.teleportedToAirdrop, airId);
                            }

                        }))
                )
                .addSubCommand(new Command("listeners")
                        .requires(new RequiresPermission("bair.listeners"))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .argument(new ArgumentPlayer("target", new ArrayList<>(List.of(lang.paramPlayer))))
                        .executor(((sender, args) -> {

                            String airId = (String) args.getOrThrow("air", lang.airIsNotSelected);

                            AirDrop airDrop = CommandSyntaxError.ifNull(airDrops.getOrDefault(airId, null), lang.airIsNotFound, airId);

                            Player player = (Player) args.getOrDefault("target", null);

                            if (player == null && !(sender instanceof Player)) {
                                throw new CommandSyntaxError(lang.playerIsNotSelected);
                            } else if (player == null) {
                                player = (Player) sender;
                            }

                            ShowAllListeners sae = new ShowAllListeners(airDrop);
                            getServer().getPluginManager().registerEvents(sae, BAirDrop.getInstance());
                            player.openInventory(sae.getInventory());


                        }))
                )
                .addSubCommand(new Command("generate")
                        .requires(new RequiresPermission("bair.generate"))
                        .addSubCommand(new Command("start")
                                .requires(new RequiresPermission("bair.generate.start"))
                                .argument(new ArgumentSetList("air", BAirDrop.airDrops.keySet().stream().toList()))
                                .argument(new ArgumentInteger("timings", new ArrayList<>(List.of(lang.generatorSpeed)), 1, Integer.MAX_VALUE / 20))
                                .argument(new ArgumentInteger("count", new ArrayList<>(List.of(lang.paramCount)), 1, Integer.MAX_VALUE / 20))
                                .executor(((sender, args) -> {

                                    String airId = (String) args.getOrThrow("air", lang.airIsNotSelected);
                                    AirDrop airDrop = CommandSyntaxError.ifNull(BAirDrop.airDrops.getOrDefault(airId, null), lang.airIsNotFound, airId);

                                    int timings = (int) args.getOrDefault("timings", 1);
                                    int count = (int) args.getOrDefault("count", 1);

                                    GeneratorLoc.start(airDrop, timings, count, sender);
                                }))

                        )
                        .addSubCommand(new Command("stop")
                                .requires(new RequiresPermission("bair.generate.stop"))
                                .executor(((sender, args) -> {
                                    GeneratorLoc.stop(sender);
                                }))
                        )
                )
                ;


    }

    public static class CommandLang {
        private final String removedItems = "%s items were removed from player %s";
        private final String airDropDeleted = "&aAirDrop '%s' successfully deleted";
        private final String airDropDeletedFail = "&cDeletion failed";
        private final String paramErrItemIsNotFound = "Item '%c' not found!";
        private final String paramErrItemIsNull = "Item must be specified!";
        private final String gaveItems = "%s items were given to player %s";
        private final String reloaded = "&aPlugin successfully reloaded in %s milliseconds!";
        private final String airId = "[<AirDrop ID>]";
        private final String airIsAlreadyExists = "AirDrop '%s' already exists!";
        private final String airCreated = "&aAirDrop %s successfully created!";
        private final String paramTimeToStart = "[<Time to Spawn>]";
        private final String paramLocIsNotSelected = "Location %s must be specified!";
        private final String airIsAlreadyStarted = "%s AirDrop is already started!";
        private final String airStarted = "&aAirDrop %s started";
        private final String setAirTimeToStart = "&aSet spawn time to %s for AirDrop %s";
        private final String airIsStopped = "&aAirDrop %s stopped";
        private final String airIsNotStarted = "&cAirDrop %s is not started!";
        private final String newIdIsNotSelected = "New ID must be specified!";
        private final String paramNewId = "[<New ID>]";
        private final String airdropCreated = "&aAirDrop '%s' successfully created!";
        private final String airDropIsNotSpawned = "&aAirDrop has not spawned yet! You are teleported to the preliminary location!";
        private final String locIsNull = "&cLocation for spawn is not defined yet!";
        private final String teleportedToAirdrop = "&aYou have been teleported to AirDrop '%s'";
        private final String paramPlayer = "[<Player>]";
        private final String playerIsNotSelected = "&cSpecify a player for this command when executing from the console!";
        private final String generatorSpeed = "[<Generation Speed>]";
        private final String paramCount = "[<Count>]";
        private final String airIsNotFound = "AirDrop '%s' not found!";
        private final String airIsNotSelected = "AirDrop must be specified!";
        private final String rawHelp = new ComponentBuilder()
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair help").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair help")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair help"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - "))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "Command Help").hoverEvent(new HoverEvent(new HoverEventContentsString("Displays a summary of all commands"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair list").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair list")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair list"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - "))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "displays a list of airdrops").hoverEvent(new HoverEvent(new HoverEventContentsString("Displays a list of airdrops, you can click on them too!"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair compass [<give/remove>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair compass")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair compass"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - "))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "give/take away compass").hoverEvent(new HoverEvent(new HoverEventContentsString("Allows you to give a compass,which will indicate the path to airdrop"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair delete [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair delete")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair delete"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - "))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "Removing an airdrop").hoverEvent(new HoverEvent(new HoverEventContentsString("You can delete an airdrop by specifying its ID"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair summoner give [<item id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair summoner give")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair summoner give"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - "))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "Gives an airdrop summon item to the player").hoverEvent(new HoverEvent(new HoverEventContentsString("/bair summoner give [<item>] [<amount>] [<player>]"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair reload").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair reload")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair reload"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - "))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "Reloads the plugin").hoverEvent(new HoverEvent(new HoverEventContentsString("What else can I say?"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair menu [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair menu")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair menu"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - "))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "Opens the airdrop editing menu").hoverEvent(new HoverEvent(new HoverEventContentsString("Can be entered without specifying an airdrop"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair create [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair create")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair create"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - ").hoverEvent(new HoverEvent(new HoverEventContentsString("/bair create"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "Creates a new airdrop with the specified id").hoverEvent(new HoverEvent(new HoverEventContentsString("Only ^[a-zA-Z0-9-_]+$ are allowed"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair start [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair start")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair start"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - ").hoverEvent(new HoverEvent(new HoverEventContentsString("/bair start"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "Launches an airdrop").hoverEvent(new HoverEvent(new HoverEventContentsString("You can specify coordinates and the world in the command!/bair start default ~ ~ ~ world"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair stop [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair stop")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair stop"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - ").hoverEvent(new HoverEvent(new HoverEventContentsString("/bair stop"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "Stops the airdrop").hoverEvent(new HoverEvent(new HoverEventContentsString("Yes indeed it stops the airdrop"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair clone [<airdrop new id>] [<old airdrop>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair clone")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair clone"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - "))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "Creates a clone of an airdrop").hoverEvent(new HoverEvent(new HoverEventContentsString("You can make it temporary using the -temp flag"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair tp [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair tp")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair tp"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - "))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "Teleports to the airdrop").hoverEvent(new HoverEvent(new HoverEventContentsString("or not?"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair listeners [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair listeners")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair listeners"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - ").hoverEvent(new HoverEvent(new HoverEventContentsString("/bair listeners"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "Shows a list of airdrop listeners\n").hoverEvent(new HoverEvent(new HoverEventContentsString("Listeners can unsubscribe and subscribe to the airdrop!"))))
                .addComponent(new Component(ChatColor.fromHex("#55ffff"), "/bair generate [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair generate")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair generate"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), " - ").hoverEvent(new HoverEvent(new HoverEventContentsString("/bair generate"))))
                .addComponent(new Component(ChatColor.fromHex("#ffffff"), "Generates locations in advance!").hoverEvent(new HoverEvent(new HoverEventContentsString("Pre-generation Allows you to reduce the load on the server!")))).build();
    }
}
