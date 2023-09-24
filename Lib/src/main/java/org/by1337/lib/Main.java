package org.by1337.lib;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        try {
            Version.init();
            getLogger().log(Level.INFO,"Version detected: " + Version.version);
        } catch (Version.UnsupportedVersionException e) {
            getLogger().log(Level.SEVERE,e.getLocalizedMessage());
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }
}
