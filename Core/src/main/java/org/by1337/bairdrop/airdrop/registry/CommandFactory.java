package org.by1337.bairdrop.airdrop.registry;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.CAirDrop;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandSyntaxError;
import org.by1337.lib.command.argument.*;
import org.by1337.lib.command.requires.RequiresPermission;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.listeners.Compass;
import org.by1337.bairdrop.location.GeneratorLoc;
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
                .requires(new RequiresPermission("bair.usage"))
                .aliases("bair")
                .aliases("air")
                .addSubCommand(new Command("help")
                        .requires(new RequiresPermission("bair.usage"))
                        .executor(((sender, args) -> Message.sendRawMsg(sender, lang.rawHelp.getString())))
                )
                .addSubCommand(new Command("list")
                        .requires(new RequiresPermission("bair.list"))
                        .executor(((sender, args) -> {
                            ComponentBuilder componentBuilder = new ComponentBuilder().component(new Component("\n"));
                            int x = 0;
                            for (String airDrop : airDrops.keySet()) {
                                componentBuilder.component(new Component(new ChatColor(Color.WHITE), String.format("%s. %s\n", x, airDrop))
                                        .hoverEvent(new HoverEvent(new HoverEventContentsString(String.format("/bair menu %s", airDrop))))
                                        .clickEvent(new ClickEvent(ClickEventType.RUN_COMMAND, String.format("/bair menu %s", airDrop))));
                                x++;
                            }
                            Message.sendRawMsg(sender, componentBuilder);
                        }))
                )
                .addSubCommand(new Command("compass")
                        .requires(new RequiresPermission("bair.compass"))
                        .addSubCommand(new Command("remove")
                                .requires(new RequiresPermission("bair.compass.remove"))
                                .argument(new ArgumentPlayer("target", new ArrayList<>(lang.paramPlayer.getList())))
                                .argument(new ArgumentInteger("amount", new ArrayList<>(lang.paramCount.getList()), 1))
                                .executor(((sender, args) -> {
                                    int removed = 0;
                                    int amount = (int) args.getOrDefault("amount", 1);
                                    Player player = (Player) args.getOrDefault("target", null);
                                    if (player == null && !(sender instanceof Player)) {
                                        throw new CommandSyntaxError(lang.playerIsNotSelected.getString());
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
                                    Message.sendMsg(sender, String.format(lang.removedItems.getString(), removed, player.getName()));
                                }))
                        )
                        .addSubCommand(new Command("give")
                                .requires(new RequiresPermission("bair.compass.give"))
                                .argument(new ArgumentPlayer("target", new ArrayList<>(lang.paramPlayer.getList())))
                                .argument(new ArgumentInteger("amount", new ArrayList<>(lang.paramCount.getList()), 1, 64))
                                .executor(((sender, args) -> {
                                    int amount = (int) args.getOrDefault("amount", 1);
                                    Player player = (Player) args.getOrDefault("target", null);

                                    if (player == null && !(sender instanceof Player)) {
                                        throw new CommandSyntaxError(lang.playerIsNotSelected.getString());
                                    } else if (player == null) {
                                        player = (Player) sender;
                                    }
                                    ItemStack itemStack = Compass.item.clone();
                                    itemStack.setAmount(amount);
                                    List<ItemStack> notGave = player.getInventory().addItem(itemStack).values().stream().toList();
                                    for (ItemStack i : notGave) {
                                        player.getLocation().getWorld().dropItem(player.getLocation(), i);
                                    }

                                    Message.sendMsg(sender, String.format(lang.gaveItems.getString(), amount, player.getName()));
                                }))
                        )
                )
                .addSubCommand(new Command("delete")
                        .requires(new RequiresPermission("bair.delete"))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .executor(((sender, args) -> {

                            String airId = (String) CommandSyntaxError.ifNull(args.getOrDefault("air", null), lang.airIsNotSelected.getString());

                            AirDrop airDrop = CommandSyntaxError.ifNull(airDrops.getOrDefault(airId, null), lang.airIsNotFound.getString(), airId);

                            airDrop.unload();
                            if (airDrop.delete()) {
                                Message.sendMsg(sender, lang.airDropDeleted.getString(), airId);
                            } else {
                                Message.sendMsg(sender, lang.airDropDeletedFail.getString());
                            }
                        }))
                )
                .addSubCommand(new Command("summoner")
                        .requires(new RequiresPermission("bair.summoner"))
                        .addSubCommand(new Command("give")
                                .requires(new RequiresPermission("bair.give"))
                                .argument(new ArgumentSetList("item", BAirDrop.summoner.getItems().keySet().stream().toList()))
                                .argument(new ArgumentInteger("amount", new ArrayList<>(lang.paramCount.getList()), 1, 64))
                                .argument(new ArgumentPlayer("target", new ArrayList<>(lang.paramPlayer.getList())))
                                .executor((sender, args) -> {
                                    String item = (String) CommandSyntaxError.ifNull(args.getOrDefault("item", null), lang.paramErrItemIsNull.getString());
                                    SummonerItem summonerItem = CommandSyntaxError.ifNull(BAirDrop.summoner.getItems().getOrDefault(item, null), lang.paramErrItemIsNotFound.getString(), item);
                                    int amount = (int) args.getOrDefault("amount", 1);
                                    Player player = (Player) args.getOrDefault("target", null);

                                    ItemStack itemStack = summonerItem.getItem();
                                    itemStack.setAmount(amount);

                                    if (player == null && !(sender instanceof Player)) {
                                        throw new CommandSyntaxError(lang.playerIsNotSelected.getString());
                                    } else if (player == null) {
                                        player = (Player) sender;
                                    }
                                    List<ItemStack> notGave = player.getInventory().addItem(itemStack).values().stream().toList();
                                    for (ItemStack i : notGave) {
                                        player.getLocation().getWorld().dropItem(player.getLocation(), i);
                                    }
                                    Message.sendMsg(sender, String.format(lang.gaveItems.getString(), amount, player.getName()));
                                })
                        )

                )
                .addSubCommand(new Command("reload")
                        .requires(new RequiresPermission("bair.reload"))
                        .executor((sender, args) -> {
                            long x = System.currentTimeMillis();
                            BAirDrop.reload();
                            Message.sendMsg(sender, lang.reloaded.getString(), (System.currentTimeMillis() - x));
                        })
                )
                .addSubCommand(new Command("menu")
                        //.aliases("m")
                        .requires(new RequiresPermission("bair.menu"))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .argument(new ArgumentPlayer("target", new ArrayList<>(lang.paramPlayer.getList())))
                        .executor(((sender, args) -> {

                            String airId = (String) args.getOrDefault("air", null);

                            AirDrop airDrop = airId == null ? null : airDrops.getOrDefault(airId, null);

                            Player player = (Player) args.getOrDefault("target", null);

                            if (player == null && !(sender instanceof Player)) {
                                throw new CommandSyntaxError(lang.playerIsNotSelected.getString());
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
                        .argument(new ArgumentValidCharacters("name", lang.airId.getList()))
                        .executor(((sender, args) -> {
                            String airId = (String) args.getOrThrow("name", lang.airIsNotSelected.getString());
                            CommandSyntaxError.ifNotNull(airDrops.getOrDefault(airId, null), lang.airIsAlreadyExists.getString(), airId);

                            airDrops.put(airId, new CAirDrop(airId));
                            Message.sendMsg(sender, lang.airCreated.getString(), airId);
                        }))
                )
                .addSubCommand(new Command("start")
                        .requires(new RequiresPermission("bair.start"))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .argument(new ArgumentPosition("posX", new ArrayList<>(List.of("~", "~ ~ ~")), ArgumentPosition.ArgumentPositionType.X))
                        .argument(new ArgumentPosition("posY", new ArrayList<>(List.of("~", "~ ~")), ArgumentPosition.ArgumentPositionType.Y))
                        .argument(new ArgumentPosition("posZ", new ArrayList<>(List.of("~")), ArgumentPosition.ArgumentPositionType.Z))
                        .argument(new ArgumentWorld("world"))
                        .argument(new ArgumentInteger("time", new ArrayList<>(lang.paramTimeToStart.getList())))
                        .executor(((sender, args) -> {

                            String airId = (String) args.getOrThrow("air", lang.airIsNotSelected.getString());

                            AirDrop airDrop = CommandSyntaxError.ifNull(airDrops.getOrDefault(airId, null), lang.airIsNotFound.getString(), airId);

                            if (airDrop.isAirDropStarted())
                                throw new CommandSyntaxError(lang.airIsAlreadyStarted.getString(), airId);

                            double locX = Math.ceil((Double) args.getOrThrow("posX", lang.paramLocIsNotSelected.getString(), "x"));
                            double locY = Math.ceil((Double) args.getOrThrow("posY", lang.paramLocIsNotSelected.getString(), "y"));
                            double locZ = Math.ceil((Double) args.getOrThrow("posZ", lang.paramLocIsNotSelected.getString(), "z"));

                            World world = (World) args.getOrDefault("world", airDrop.getWorld());

                            int time = (int) args.getOrDefault("time", 0);

                            Location location = new Location(world, locX, locY, locZ);
                            airDrop.setAirDropLocation(location);
                            airDrop.setFutureLocation(location);
                            if (time == 0) {
                                airDrop.startCommand(sender);
                                Message.sendMsg(sender, lang.airStarted.getString(), airId);
                            } else {
                                airDrop.setTimeToStart(time);
                                Message.sendMsg(sender, lang.setAirTimeToStart.getString(), time, airId);
                            }
                        }))
                )
                .addSubCommand(new Command("stop")
                        .requires(new RequiresPermission("bair.stop"))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .executor(((sender, args) -> {

                            String airId = (String) args.getOrThrow("air", lang.airIsNotSelected.getString());

                            AirDrop airDrop = CommandSyntaxError.ifNull(airDrops.getOrDefault(airId, null), lang.airIsNotFound.getString(), airId);

                            if (airDrop.isAirDropStarted()) {
                                airDrop.end();
                                Message.sendMsg(sender, lang.airIsStopped.getString(), airId);
                            } else {
                                Message.sendMsg(sender, lang.airIsNotStarted.getString(), airId);
                            }

                        }))
                )
                .addSubCommand(new Command("clone")
                        .requires(new RequiresPermission("bair.clone"))
                        .argument(new ArgumentValidCharacters("newId", new ArrayList<>(lang.paramNewId.getList())))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .argument(new ArgumentSetList("isTemp", new ArrayList<>(List.of("-temp"))))
                        .executor(((sender, args) -> {
                            boolean temp = args.getOrDefault("isTemp", "no").equals("-temp");
                            String newId = (String) args.getOrThrow("newId", lang.newIdIsNotSelected.getString());
                            String airId = (String) args.getOrThrow("air", lang.airIsNotSelected.getString());

                            AirDrop airDrop = CommandSyntaxError.ifNull(airDrops.getOrDefault(airId, null), lang.airIsNotFound.getString(), airId);

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
                            Message.sendMsg(sender, lang.airdropCreated.getString(), air.getId());

                        }))
                )
                .addSubCommand(new Command("tp")
                        .requires(new RequiresPermission("bair.tp"))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .argument(new ArgumentPlayer("target", new ArrayList<>(lang.paramPlayer.getList())))
                        .executor(((sender, args) -> {

                            String airId = (String) args.getOrThrow("air", lang.airIsNotSelected.getString());

                            AirDrop airDrop = CommandSyntaxError.ifNull(airDrops.getOrDefault(airId, null), lang.airIsNotFound.getString(), airId);

                            Player player = (Player) args.getOrDefault("target", null);

                            if (player == null && !(sender instanceof Player)) {
                                throw new CommandSyntaxError(lang.playerIsNotSelected.getString());
                            } else if (player == null) {
                                player = (Player) sender;
                            }
                            if (!airDrop.isAirDropStarted()) {
                                if (airDrop.getFutureLocation() != null) {
                                    player.teleport(airDrop.getFutureLocation());
                                    Message.sendMsg(sender, lang.airDropIsNotSpawned.getString());
                                    return;
                                }
                                Message.sendMsg(sender, lang.locIsNull.getString());
                            } else {
                                player.teleport(airDrop.getAnyLoc());
                                Message.sendMsg(sender, lang.teleportedToAirdrop.getString(), airId);
                            }

                        }))
                )
                .addSubCommand(new Command("listeners")
                        .requires(new RequiresPermission("bair.listeners"))
                        .argument(new ArgumentSetList("air", airDrops.keySet().stream().toList()))
                        .argument(new ArgumentPlayer("target", new ArrayList<>(lang.paramPlayer.getList())))
                        .executor(((sender, args) -> {

                            String airId = (String) args.getOrThrow("air", lang.airIsNotSelected.getString());

                            AirDrop airDrop = CommandSyntaxError.ifNull(airDrops.getOrDefault(airId, null), lang.airIsNotFound.getString(), airId);

                            Player player = (Player) args.getOrDefault("target", null);

                            if (player == null && !(sender instanceof Player)) {
                                throw new CommandSyntaxError(lang.playerIsNotSelected.getString());
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
                                .argument(new ArgumentInteger("timings", new ArrayList<>(lang.generatorSpeed.getList()), 1, Integer.MAX_VALUE / 20))
                                .argument(new ArgumentInteger("count", new ArrayList<>(lang.paramCount.getList()), 1, Integer.MAX_VALUE / 20))
                                .executor(((sender, args) -> {

                                    String airId = (String) args.getOrThrow("air", lang.airIsNotSelected.getString());
                                    AirDrop airDrop = CommandSyntaxError.ifNull(BAirDrop.airDrops.getOrDefault(airId, null), lang.airIsNotFound.getString(), airId);

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
                .executor(((sender, args) -> Message.sendRawMsg(sender, lang.rawHelp.getString())))
                ;


    }

    public static class CommandLang {
        private final Resource removedItems = new Resource("command.removedItems");
        private final Resource airDropDeleted = new Resource("command.airDropDeleted");
        private final Resource airDropDeletedFail = new Resource("command.airDropDeletedFail");
        private final Resource paramErrItemIsNotFound = new Resource("command.paramErrItemIsNotFound");
        private final Resource paramErrItemIsNull = new Resource("command.paramErrItemIsNull");
        private final Resource gaveItems = new Resource("command.gaveItems");
        private final Resource reloaded = new Resource("command.reloaded");
        private final Resource airId = new Resource("command.airId");
        private final Resource airIsAlreadyExists = new Resource("command.airIsAlreadyExists");
        private final Resource airCreated = new Resource("command.airCreated");
        private final Resource paramTimeToStart = new Resource("command.paramTimeToStart");
        private final Resource paramLocIsNotSelected = new Resource("command.paramLocIsNotSelected");
        private final Resource airIsAlreadyStarted = new Resource("command.airIsAlreadyStarted");
        private final Resource airStarted = new Resource("command.airStarted");
        private final Resource setAirTimeToStart = new Resource("command.setAirTimeToStart");
        private final Resource airIsStopped = new Resource("command.airIsStopped");
        private final Resource airIsNotStarted = new Resource("command.airIsNotStarted");
        private final Resource newIdIsNotSelected = new Resource("command.newIdIsNotSelected");
        private final Resource paramNewId = new Resource("command.paramNewId");
        private final Resource airdropCreated = new Resource("command.airdropCreated");
        private final Resource airDropIsNotSpawned = new Resource("command.airDropIsNotSpawned");
        private final Resource locIsNull = new Resource("command.locIsNull");
        private final Resource teleportedToAirdrop = new Resource("command.teleportedToAirdrop");
        private final Resource paramPlayer = new Resource("command.paramPlayer");
        private final Resource playerIsNotSelected = new Resource("command.playerIsNotSelected");
        private final Resource generatorSpeed = new Resource("command.generatorSpeed");
        private final Resource paramCount = new Resource("command.paramCount");
        private final Resource airIsNotFound = new Resource("command.airIsNotFound");
        private final Resource airIsNotSelected = new Resource("command.airIsNotSelected");
        private final Resource rawHelp = new Resource("command.rawHelp");
    }
}
