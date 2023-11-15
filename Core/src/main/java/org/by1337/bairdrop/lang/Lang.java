package org.by1337.bairdrop.lang;

import com.google.gson.Gson;
import lombok.Getter;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.util.OLDMessage;
import org.by1337.api.chat.*;
import org.by1337.api.chat.hover.HoverEvent;
import org.by1337.api.chat.hover.HoverEventContentsString;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class Lang {
    private HashMap<String, List<String>> messages = new HashMap<>();

    @Getter
    private static Lang lang;

    public String getMessage(String patch) {
        return String.join("\n", messages.getOrDefault(patch, List.of(String.format("OLDMessage along path %s not found!", patch))));
    }

    public List<String> getList(String patch) {
        return messages.getOrDefault(patch, List.of(String.format("OLDMessage along path %s not found!", patch)));
    }

    public static void init() {
        String file = BAirDrop.getInstance().getConfig().getString("lang", "en");
        InputStream resourceStream = BAirDrop.getInstance().getResource("lang/" + file + ".json");
        if (resourceStream == null) {
            OLDMessage.error("file: " + "lang/" + file + ".json" + " not found!");
            resourceStream = BAirDrop.getInstance().getResource("lang/en.json");
            if (resourceStream == null) {
                throw new IllegalStateException("OLDMessage file not found! Do you have the latest version of the plugin?");
            }
        }
        try (InputStreamReader reader = new InputStreamReader(resourceStream, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            lang = gson.fromJson(reader, Lang.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromCode() {
        lang = new Lang();
        lang.messages.put(("effect.property.max-tick.lore"),
                List.of(
                        "&r ",
                        "&r &7Сколько тиков будет работать эффект,",
                        "&r &7пока его не выключат",
                        "&r &7Текущее: {value}",
                        "&r "
                )
        );
        lang.messages.put(("effect.property.max-tick.material"), List.of("REDSTONE_TORCH"));
        lang.messages.put(("effect.property.max-tick.name"), List.of("&7Время жизни эффекта"));

        lang.messages.put(("effect.property.tick-speed.lore"),
                List.of(
                        "&r ",
                        "&r &7Раз в сколько тиков обновлять партиклы",
                        "&r &7Текущее: {value}",
                        "&r "
                )
        );
        lang.messages.put(("effect.property.tick-speed.material"), List.of("OBSERVER"));
        lang.messages.put(("effect.property.tick-speed.name"), List.of("&7Скорость обновления"));

        lang.messages.put(("effect.property.particle.lore"),
                List.of(
                        "&r ",
                        "&r &7Тип партикла",
                        "&r &7Текущее: {value}",
                        "&r "
                )
        );
        lang.messages.put(("effect.property.particle.material"), List.of("STONE"));
        lang.messages.put(("effect.property.particle.name"), List.of("&7Партикл"));

        lang.messages.put(("effect.property.radius.lore"),
                List.of(
                        "&r ",
                        "&r &7Радиус круга",
                        "&r &7Текущее: {value}",
                        "&r "
                ));
        lang.messages.put(("effect.property.radius.material"), List.of("STONE"));
        lang.messages.put(("effect.property.radius.name"), List.of("&7Радиус"));

        lang.messages.put(("effect.property.count.lore"),
                List.of(
                        "&r ",
                        "&r &7Количество партиклов",
                        "&r &7Текущее: {value}",
                        "&r "
                ));
        lang.messages.put(("effect.property.count.material"), List.of("STONE"));
        lang.messages.put(("effect.property.count.name"), List.of("&7Количество"));

        lang.messages.put(("effect.property.step.lore"),
                List.of(
                        "&r ",
                        "&r &7Расстояние между партиклами",
                        "&r &7Текущее: {value}",
                        "&r "
                ));
        lang.messages.put(("effect.property.step.material"), List.of("STONE"));
        lang.messages.put(("effect.property.step.name"), List.of("&7Промежуток"));

        lang.messages.put(("effect.property.offsets.lore"),
                List.of(
                        "&r ",
                        "&r &7Смещение относительно аирдропа",
                        "&r &7Текущее: {value}",
                        "&r "
                ));
        lang.messages.put(("effect.property.offsets.material"), List.of("STONE"));
        lang.messages.put(("effect.property.offsets.name"), List.of("&7Смещение"));

        lang.messages.put(("effect.property.size.lore"),
                List.of(
                        "&r ",
                        "&r &7Если партикл типа REDSTONE,",
                        "&r &7то можно указать размер",
                        "&r &7Текущее: {value}",
                        "&r "
                ));
        lang.messages.put(("effect.property.size.material"), List.of("STONE"));
        lang.messages.put(("effect.property.size.name"), List.of("&7Размер"));

        lang.messages.put(("effect.property.color.lore"),
                List.of(
                        "&r ",
                        "&r &7Если партикл типа REDSTONE,",
                        "&r &7то можно указать цвет",
                        "&r &7Текущее: {value}",
                        "&r "
                ));
        lang.messages.put(("effect.property.color.material"), List.of("STONE"));
        lang.messages.put(("effect.property.color.name"), List.of("&7Цвет"));

        lang.messages.put(("effect.property.direction.lore"),
                List.of(
                        "&r ",
                        "&r &7Вектор движения пертиклов",
                        "&r &7Это работает не со всеми партиклами!",
                        "&r &7Текущее: {value}",
                        "&r "
                ));
        lang.messages.put(("effect.property.direction.material"), List.of("STONE"));
        lang.messages.put(("effect.property.direction.name"), List.of("&7Направление"));

        lang.messages.put(("effect.property.speed.lore"),
                List.of(
                        "&r ",
                        "&r &7Скорость движения",
                        "&r &7Текущее: {value}",
                        "&r "
                ));
        lang.messages.put(("effect.property.speed.material"), List.of("STONE"));
        lang.messages.put(("effect.property.speed.name"), List.of("&7Скорость"));

        lang.messages.put(("command.removedItems"), List.of("%s items were removed from player %s"));
        lang.messages.put(("command.airDropDeleted"), List.of("&aAirDrop '%s' successfully deleted"));
        lang.messages.put(("command.airDropDeletedFail"), List.of("&cDeletion failed"));
        lang.messages.put(("command.paramErrItemIsNotFound"), List.of("Item '%c' not found!"));
        lang.messages.put(("command.paramErrItemIsNull"), List.of("Item must be specified!"));
        lang.messages.put(("command.gaveItems"), List.of("%s items were given to player %s"));
        lang.messages.put(("command.reloaded"), List.of("&aPlugin successfully reloaded in %s milliseconds!"));
        lang.messages.put(("command.airId"), List.of("[<AirDrop ID>]"));
        lang.messages.put(("command.airIsAlreadyExists"), List.of("AirDrop '%s' already exists!"));
        lang.messages.put(("command.airCreated"), List.of("&aAirDrop %s successfully created!"));
        lang.messages.put(("command.paramTimeToStart"), List.of("[<Time to Spawn>]"));
        lang.messages.put(("command.paramLocIsNotSelected"), List.of("Location %s must be specified!"));
        lang.messages.put(("command.airIsAlreadyStarted"), List.of("%s AirDrop is already started!"));
        lang.messages.put(("command.airStarted"), List.of("&aAirDrop %s started"));
        lang.messages.put(("command.setAirTimeToStart"), List.of("&aSet spawn time to %s for AirDrop %s"));
        lang.messages.put(("command.airIsStopped"), List.of("&aAirDrop %s stopped"));
        lang.messages.put(("command.airIsNotStarted"), List.of("&cAirDrop %s is not started!"));
        lang.messages.put(("command.newIdIsNotSelected"), List.of("New ID must be specified!"));
        lang.messages.put(("command.paramNewId"), List.of("[<New ID>]"));
        lang.messages.put(("command.airdropCreated"), List.of("&aAirDrop '%s' successfully created!"));
        lang.messages.put(("command.airDropIsNotSpawned"), List.of("&aAirDrop has not spawned yet! You are teleported to the preliminary location!"));
        lang.messages.put(("command.locIsNull"), List.of("&cLocation for spawn is not defined yet!"));
        lang.messages.put(("command.teleportedToAirdrop"), List.of("&aYou have been teleported to AirDrop '%s'"));
        lang.messages.put(("command.paramPlayer"), List.of("[<Player>]"));
        lang.messages.put(("command.playerIsNotSelected"), List.of("&cSpecify a player for this command when executing from the console!"));
        lang.messages.put(("command.generatorSpeed"), List.of("[<Generation Speed>]"));
        lang.messages.put(("command.paramCount"), List.of("[<Count>]"));
        lang.messages.put(("command.airIsNotFound"), List.of("AirDrop '%s' not found!"));
        lang.messages.put(("command.airIsNotSelected"), List.of("AirDrop must be specified!"));
        lang.messages.put(("command.rawHelp"),
                List.of(new ComponentBuilder()
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair help").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair help")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair help"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - "))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "Command Help").hoverEvent(new HoverEvent(new HoverEventContentsString("Displays a summary of all commands"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair list").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair list")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair list"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - "))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "displays a list of airdrops").hoverEvent(new HoverEvent(new HoverEventContentsString("Displays a list of airdrops, you can click on them too!"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair compass [<give/remove>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair compass")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair compass"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - "))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "give/take away compass").hoverEvent(new HoverEvent(new HoverEventContentsString("Allows you to give a compass,which will indicate the path to airdrop"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair delete [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair delete")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair delete"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - "))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "Removing an airdrop").hoverEvent(new HoverEvent(new HoverEventContentsString("You can delete an airdrop by specifying its ID"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair summoner give [<item id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair summoner give")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair summoner give"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - "))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "Gives an airdrop summon item to the player").hoverEvent(new HoverEvent(new HoverEventContentsString("/bair summoner give [<item>] [<amount>] [<player>]"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair reload").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair reload")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair reload"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - "))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "Reloads the plugin").hoverEvent(new HoverEvent(new HoverEventContentsString("What else can I say?"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair menu [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair menu")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair menu"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - "))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "Opens the airdrop editing menu").hoverEvent(new HoverEvent(new HoverEventContentsString("Can be entered without specifying an airdrop"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair create [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair create")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair create"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - ").hoverEvent(new HoverEvent(new HoverEventContentsString("/bair create"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "Creates a new airdrop with the specified id").hoverEvent(new HoverEvent(new HoverEventContentsString("Only ^[a-zA-Z0-9-_]+$ are allowed"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair start [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair start")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair start"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - ").hoverEvent(new HoverEvent(new HoverEventContentsString("/bair start"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "Launches an airdrop").hoverEvent(new HoverEvent(new HoverEventContentsString("You can specify coordinates and the world in the command!/bair start default ~ ~ ~ world"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair stop [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair stop")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair stop"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - ").hoverEvent(new HoverEvent(new HoverEventContentsString("/bair stop"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "Stops the airdrop").hoverEvent(new HoverEvent(new HoverEventContentsString("Yes indeed it stops the airdrop"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair clone [<airdrop new id>] [<old airdrop>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair clone")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair clone"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - "))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "Creates a clone of an airdrop").hoverEvent(new HoverEvent(new HoverEventContentsString("You can make it temporary using the -temp flag"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair tp [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair tp")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair tp"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - "))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "Teleports to the airdrop").hoverEvent(new HoverEvent(new HoverEventContentsString("or not?"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "\n"))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair listeners [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair listeners")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair listeners"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - ").hoverEvent(new HoverEvent(new HoverEventContentsString("/bair listeners"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "Shows a list of airdrop listeners\n").hoverEvent(new HoverEvent(new HoverEventContentsString("Listeners can unsubscribe and subscribe to the airdrop!"))))
                        .component(new Component(ChatColor.fromHex("#55ffff"), "/bair generate [<airdrop id>]").clickEvent(new ClickEvent(ClickEventType.SUGGEST_COMMAND, "/bair generate")).hoverEvent(new HoverEvent(new HoverEventContentsString("/bair generate"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), " - ").hoverEvent(new HoverEvent(new HoverEventContentsString("/bair generate"))))
                        .component(new Component(ChatColor.fromHex("#ffffff"), "Generates locations in advance!").hoverEvent(new HoverEvent(new HoverEventContentsString("Pre-generation Allows you to reduce the load on the server!")))).build()));


        lang.messages.put("particle-to-material.list",
                List.of(
                        "EXPLOSION_NORMAL->TNT",
                        "EXPLOSION_LARGE->TNT",
                        "EXPLOSION_HUGE->TNT",
                        "FIREWORKS_SPARK->FIREWORK_ROCKET",
                        "WATER_BUBBLE->WATER_BUCKET",
                        "WATER_SPLASH->WATER_BUCKET",
                        "WATER_WAKE->WATER_BUCKET",
                        "SUSPENDED->DIRT",
                        "SUSPENDED_DEPTH->DIRT",
                        "CRIT->DIRT",
                        "CRIT_MAGIC->DIRT",
                        "SMOKE_NORMAL->SMOKER",
                        "SMOKE_LARGE->SMOKER",
                        "SPELL->SPLASH_POTION",
                        "SPELL_INSTANT->SPLASH_POTION",
                        "SPELL_MOB->SPLASH_POTION",
                        "SPELL_MOB_AMBIENT->SPLASH_POTION",
                        "SPELL_WITCH->SPLASH_POTION",
                        "DRIP_WATER->WATER_BUCKET",
                        "DRIP_LAVA->LAVA_BUCKET",
                        "VILLAGER_ANGRY->VILLAGER_SPAWN_EGG",
                        "VILLAGER_HAPPY->VILLAGER_SPAWN_EGG",
                        "TOWN_AURA->DIRT",
                        "NOTE->NOTE_BLOCK",
                        "PORTAL->END_PORTAL_FRAME",
                        "ENCHANTMENT_TABLE->ENCHANTING_TABLE",
                        "FLAME->DIRT",
                        "LAVA->LAVA_BUCKET",
                        "CLOUD->DIRT",
                        "REDSTONE->REDSTONE",
                        "SNOWBALL->SNOW",
                        "SNOW_SHOVEL->SNOW",
                        "SLIME->SLIME_BLOCK",
                        "HEART->HEART_OF_THE_SEA",
                        "ITEM_CRACK->ITEM_FRAME",
                        "BLOCK_CRACK->DIRT",
                        "BLOCK_DUST->DIRT",
                        "WATER_DROP->WATER_BUCKET",
                        "MOB_APPEARANCE->DIRT",
                        "DRAGON_BREATH->DRAGON_EGG",
                        "END_ROD->END_ROD",
                        "DAMAGE_INDICATOR->DAMAGED_ANVIL",
                        "SWEEP_ATTACK->DIRT",
                        "FALLING_DUST->DIRT",
                        "TOTEM->TOTEM_OF_UNDYING",
                        "SPIT->DIRT",
                        "SQUID_INK->SQUID_SPAWN_EGG",
                        "BUBBLE_POP->BUBBLE_CORAL_BLOCK",
                        "CURRENT_DOWN->DIRT",
                        "BUBBLE_COLUMN_UP->BUBBLE_CORAL_BLOCK",
                        "NAUTILUS->NAUTILUS_SHELL",
                        "DOLPHIN->DOLPHIN_SPAWN_EGG",
                        "SNEEZE->DIRT",
                        "CAMPFIRE_COSY_SMOKE->CAMPFIRE",
                        "CAMPFIRE_SIGNAL_SMOKE->CAMPFIRE",
                        "COMPOSTER->COMPOSTER",
                        "FLASH->DIRT",
                        "FALLING_LAVA->LAVA_BUCKET",
                        "LANDING_LAVA->LAVA_BUCKET",
                        "FALLING_WATER->WATER_BUCKET",
                        "DRIPPING_HONEY->DIRT",
                        "FALLING_HONEY->DIRT",
                        "LANDING_HONEY->DIRT",
                        "FALLING_NECTAR->DIRT",
                        "SOUL_FIRE_FLAME->SOUL_SAND",
                        "ASH->SPLASH_POTION",
                        "CRIMSON_SPORE->CRIMSON_NYLIUM",
                        "WARPED_SPORE->WARPED_NYLIUM",
                        "SOUL->SOUL_SAND",
                        "DRIPPING_OBSIDIAN_TEAR->OBSIDIAN",
                        "FALLING_OBSIDIAN_TEAR->OBSIDIAN",
                        "LANDING_OBSIDIAN_TEAR->OBSIDIAN",
                        "REVERSE_PORTAL->DIRT",
                        "WHITE_ASH->WHITE_WOOL",
                        "DUST_COLOR_TRANSITION->DIRT",
                        "VIBRATION->DIRT",
                        "FALLING_SPORE_BLOSSOM->SPORE_BLOSSOM",
                        "SPORE_BLOSSOM_AIR->AIR",
                        "SMALL_FLAME->SMALL_DRIPLEAF",
                        "SNOWFLAKE->SNOW",
                        "DRIPPING_DRIPSTONE_LAVA->LAVA_BUCKET",
                        "FALLING_DRIPSTONE_LAVA->LAVA_BUCKET",
                        "DRIPPING_DRIPSTONE_WATER->WATER_BUCKET",
                        "FALLING_DRIPSTONE_WATER->WATER_BUCKET",
                        "GLOW_SQUID_INK->GLOWSTONE",
                        "GLOW->GLOWSTONE",
                        "WAX_ON->WAXED_COPPER_BLOCK",
                        "WAX_OFF->WAXED_COPPER_BLOCK",
                        "ELECTRIC_SPARK->DIRT",
                        "SCRAPE->DIRT",
                        "SONIC_BOOM->DIRT",
                        "SCULK_SOUL->SCULK",
                        "SCULK_CHARGE->SCULK",
                        "SCULK_CHARGE_POP->SCULK",
                        "SHRIEK->SCULK_SHRIEKER",
                        "CHERRY_LEAVES->CHERRY_PLANKS",
                        "EGG_CRACK->EGG",
                        "BLOCK_MARKER->DIRT",
                        "LEGACY_BLOCK_CRACK->DIRT",
                        "LEGACY_BLOCK_DUST->DIRT",
                        "LEGACY_FALLING_DUST->DIRT"
                )
        );
        lang.messages.put("airdrop.warning.item-list-empty", List.of("The list of items is empty!"));
        lang.messages.put("airdrop.error.loc-is-null", List.of("Location not found, the airdrop cannot appear!"));
        lang.messages.put("schematics.error.limit", List.of("Unable to spawn multiple structures at once!"));
        lang.messages.put("anti-steal.limit-click-rate", List.of("&cYou\"re taking items from the chest too fast!"));
        lang.messages.put("listener.error.too-many-call", List.of("Too many listeners were called!"));
        lang.messages.put("not-a-number", List.of("'%s' not a number!"));
        lang.messages.put("easy-boss-bar.error.progress.out.of.bounds", List.of("Прогресс не может быть меньше нуля или больше чем 1"));
        lang.messages.put("airdrop.error.isnt-started", List.of("&c'%s' airdrop isn't started!"));



     lang.messages.put(("airdrop.logger.lore"),
                List.of(
                        "&r ",
                        "&r &7Включён ли логгер аирдрпа",
                        "&r &7Текущее: {value}",
                        "&r "
                ));
        lang.messages.put(("airdrop.logger.material"), List.of("STONE"));
        lang.messages.put(("airdrop.logger.name"), List.of("&7Логгер"));

        lang.messages.put("command.async", List.of("Asynchronous use of the %s command"));
        lang.messages.put("command.airdrop-is-null", List.of("Airdrop is null! command: %s"));
        lang.messages.put("command.location-is-null", List.of("location is null! command: %s"));
        lang.messages.put("command.usage", List.of("Usage: %s"));

        lang.messages.put("command.player-is-null", List.of("Player is null! Command: %s"));
        lang.messages.put("command.lid.cannot-be-opened-or-closed", List.of("This block cannot be opened or closed! command: %s"));

        lang.messages.put("command.deprecated.bossbar.err1", List.of("Incorrect string format: not all quotes are closed. %s"));
        lang.messages.put("command.deprecated.bossbar.few-args", List.of("&cNot enough arguments when creating or updating the boss bar!"));
        lang.messages.put("command.deprecated.bossbar.enum-err", List.of("Invalid BarColor or BarStyle"));
        lang.messages.put("command.deprecated.bossbar.not-created", List.of("Boss bar not created"));
        lang.messages.put("command.deprecated.bossbar.unknown-boss-bar", List.of("Unknown boss bar! %s"));
        lang.messages.put("command.deprecated.bossbar.player-is-null", List.of("Player is null! Unable to send boss bar to the player!"));
        lang.messages.put("command.deprecated.bossbar.player-is-null2", List.of("Player = null! Unable to remove boss bar from the player!"));


        lang.messages.put("command.unknown-js-script", List.of("%s Unknown script!"));
        lang.messages.put("command.js-time", List.of("&7%s executed in %s"));

        lang.messages.put("command.rotate-err", List.of("This block cannot be rotated. Block %s"));
        lang.messages.put("command.is-not-block", List.of("the material must be a block! Command %s"));

    }
}
