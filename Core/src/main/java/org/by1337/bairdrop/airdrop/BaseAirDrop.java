package org.by1337.bairdrop.airdrop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.by1337.api.chat.Placeholder;
import org.by1337.api.configuration.YamlConfig;
import org.by1337.api.property.property.Property;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.location.generator.GeneratorSetting;
import org.by1337.api.property.StorageProperties;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.observer.observer.ObservableImpl;
import org.by1337.bairdrop.observer.observer.Observer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.*;

public class BaseAirDrop extends ObservableImpl implements Airdrop {

    protected final String id;
    protected final File homeDir;

    private GeneratorSetting generatorSetting = new GeneratorSetting();
    private StorageProperties storageProperties;

    private final List<Placeholder> placeholders = new ArrayList<>();

    @Nullable
    private Location location;

    public static BaseAirDrop createNew(String id) {
        return new BaseAirDrop(id, new File(BAirDrop.getInstance().getDataFolder() + "/airdrops/" + id));
    }

    private BaseAirDrop(String id, File homeDir) {
        this.id = id;
        this.homeDir = homeDir;
        storageProperties = new StorageProperties();
        initDefault();
        initPlaceholders();
        homeDir.mkdir();
        //  String homePatch = homeDir + "/" + id;

        File mainCnd = new File(homeDir + "/main.cdn");

        try (
                InputStream in = BAirDrop.getInstance().getResource("presets/main.cdn");
                OutputStream outputStream = new FileOutputStream(mainCnd)
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            BAirDrop.MESSAGE.error(e);
        }

        File mainCfg = new File(homeDir + "/" + id + ".yml");

        try {
            mainCfg.createNewFile();
            YamlConfig config = new YamlConfig(mainCfg);

            config.getContext().set("id", id);
            config.getContext().set("home-dir", homeDir.getPath().replaceAll("\\\\", "/"));
            config.getContext().set("generator-setting", generatorSetting);
            config.getContext().set("properties", storageProperties.getProperties());

            config.save();

        } catch (IOException | InvalidConfigurationException e) {
            BAirDrop.MESSAGE.error(e);
        }


    }

    private void initPlaceholders() {
        registerPlaceholder("x", () -> location == null ? "?" : String.valueOf(location.getBlockX()));
        registerPlaceholder("y", () -> location == null ? "?" : String.valueOf(location.getBlockY()));
        registerPlaceholder("z", () -> location == null ? "?" : String.valueOf(location.getBlockZ()));
        registerPlaceholder("id", () -> id);
    }

    private void initDefault() {
        storageProperties.putInteger("time-to-start", 30);
        storageProperties.putInteger("time-to-unlock", 30);
        storageProperties.putInteger("time-to-end", 30);

        storageProperties.putInteger("time-to-start-def", 30);
        storageProperties.putInteger("time-to-unlock-def", 30);
        storageProperties.putInteger("time-to-end-def", 30);

        storageProperties.putInteger("spawn-chance", 100);
        storageProperties.putInteger("min-online-players", 0);

        storageProperties.putString("world", Bukkit.getWorld("world") != null ? "world" : Bukkit.getWorlds().get(0).getName());

        storageProperties.putBoolean("start-countdown-after-click", false);
        storageProperties.putBoolean("time-stop-event-must-go", false);
        storageProperties.putBoolean("is-spawned", false);
        storageProperties.putBoolean("activated", false);
        storageProperties.putBoolean("opened", false);

        storageProperties.putString("timer", "default");

        storageProperties.putMaterial("closed-material", Material.RESPAWN_ANCHOR);
        storageProperties.putMaterial("open-material", Material.ENDER_CHEST);

    }

    public void registerPlaceholder(@NotNull Placeholder placeholder) {
        Objects.requireNonNull(placeholder);
        if (placeholders.contains(placeholder)) {
            throw new IllegalStateException();
        }
        placeholders.add(placeholder);
    }

    public void registerPlaceholder(@NotNull String placeholder, @NotNull Supplier<String> supplier) {
        Objects.requireNonNull(placeholder);
        Objects.requireNonNull(supplier);
        placeholders.add(new Placeholder(placeholder) {
            @Override
            public @NotNull String getValue() {
                return supplier.get();
            }
        });
    }

    @Nullable
    public Location getLocation() {
        return location == null ? null : location.clone();
    }

    public void setLocation(@Nullable Location location) {
        this.location = location;
    }

    @Override
    public boolean canSpawn() {
        return false;
    }

    @Override
    public void tick() {
        callEvent(new Event(this, null, CustomEvent.TIMER));
    }


    @Override
    public int spawnChance() {
        return 0;
    }

    @Override
    public boolean isSpawned() {
        return false;
    }

    @Override
    public Logger logger() {
        return null;
    }

    @Override
    public Location getAnyLoc() {
        return null;
    }

    @Override
    public void loadEffect(String name, String id) {

    }

    @Override
    public void startEffect(String id) {

    }

    @Override
    public boolean isEffectStarted(String id) {
        return false;
    }

    @Override
    public void stopEffect(String id) {

    }

    @Override
    public void stopAllEffectsAndClear() {

    }

    @Override
    public String replace(String string) {
        StringBuilder sb = new StringBuilder(string);
        for (Map.Entry<String, Property<?>> entry : storageProperties.getProperties().entrySet()) {
            String placeholder = String.format("{%s}", entry.getKey());
            replace(sb, placeholder, () -> String.valueOf(entry.getValue().getValue()));
        }
        for (Placeholder placeholder : placeholders) {
            replace(sb, String.format("{%s}", placeholder.getPlaceholder()), placeholder::getValue);
        }
        return sb.toString();
    }

    private void replace(StringBuilder sb, String placeholder, Supplier<String> value) {
        int startPos = sb.indexOf(placeholder);
        while (startPos != -1) {
            if (startPos > 0) {
                if (sb.charAt(startPos - 1) == '\\') {
                    sb.replace(startPos - 1, startPos, "");
                    startPos = sb.indexOf(placeholder, startPos);
                    continue;
                }
            }
            sb.replace(startPos, startPos + placeholder.length(), String.valueOf(value.get()));
            startPos = sb.indexOf(placeholder, startPos);
        }
    }


    @Override
    public GeneratorSetting getGeneratorSetting() {
        return generatorSetting;
    }

    public StorageProperties getStorageProperties() {
        return storageProperties;
    }
}
