package org.by1337.lib;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentSetList;
import org.by1337.lib.command.requires.RequiresPermission;
import org.by1337.lib.config.Config;
import org.by1337.lib.lang.Lang;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Level;

public class BLib extends JavaPlugin {
    @Getter
    private static Plugin instance;

    private Config config;

    public static final List<String> LANGUAGES = List.of(
            "en_US",
            "ru_RU"
    );

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        init();
        getCommand("blib").setExecutor(this);
        getCommand("blib").setTabCompleter(this);
    }

    private void init() {
        config = new Config();
        config.load();
        Lang.loadTranslations(config.lang);

        try {
            Version.init();
            getLogger().log(Level.INFO, String.format(Lang.getMessage("detect-version"), Version.version.getVer()));
        } catch (Version.UnsupportedVersionException e) {
            getLogger().log(Level.SEVERE, e.getLocalizedMessage());
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {
        try {
            createCommand().process(sender, args);
            return true;
        } catch (CommandException e) {
            sender.sendMessage(e.getLocalizedMessage());
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String alias, @NotNull String[] args) {
        return createCommand().getTabCompleter(sender, args);
    }

    private Command createCommand() {
        return new Command("blib")
                .requires(new RequiresPermission("blib.use"))
                .addSubCommand(new Command("reload")
                        .requires(new RequiresPermission("blib.reload"))
                        .executor(((sender, args) -> {
                            init();
                            sender.sendMessage(Lang.getMessage("reload"));
                        }))
                )
                .addSubCommand(new Command("language")
                        .aliases("lang")
                        .requires(new RequiresPermission("blib.lang"))
                        .argument(new ArgumentSetList("language", LANGUAGES))
                        .executor(((sender, args) -> {
                            String lang = (String) args.getOrThrow("language", Lang.getMessage("missing-argument"), "language");
                            Lang.loadTranslations(lang);
                            config.lang = lang;
                            config.save();
                            sender.sendMessage(Lang.getMessage("language-changed"));
                        }))
                );

    }
}
