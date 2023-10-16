package org.by1337.bairdrop.airdrop;

import com.google.gson.Gson;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDropUtils;
import org.by1337.bairdrop.location.generator.GeneratorSetting;
import org.by1337.bairdrop.menu.property.StorageProperties;
import org.by1337.bairdrop.menu.property.property.*;
import org.by1337.bairdrop.observer.Condition;
import org.by1337.bairdrop.observer.CustomEvent;
import org.by1337.bairdrop.observer.CustomEventListener;
import org.by1337.bairdrop.observer.observer.Observer;
import org.by1337.bairdrop.serializable.GsonFactory;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

public class BaseAirDrop extends StorageProperties implements Airdrop {

    protected final String id;
    protected final transient File homeDir;
    private final String homePatch;
    private transient Logger logger;
    private transient FileHandler fileHandler;

    private transient final List<Observer> observers = new ArrayList<>();

    private GeneratorSetting generatorSetting = new GeneratorSetting();

    private List<CustomEventListener> listeners = new ArrayList<>();

    public BaseAirDrop(String id, File homeDir) {
        this.id = id;
        this.homeDir = homeDir;
        homePatch = homeDir + "/" + id + ".json";
        initProperty();
        try {
            if ((Boolean) getProperty("logger-is-enabled").getValue()) {
                loadLogger();
            } else {
                loadEmptyLogger();
            }
        } catch (IOException e) {
            Message.error(e.getLocalizedMessage());
        }
        File file = new File(homePatch);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = GsonFactory.create();
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initProperty() {
        putProperty(new PropertyBoolean(true, "logger-is-enabled"));
        putProperty(new PropertyInteger(30, "time-to-start", 30));
        putProperty(new PropertyInteger(30, "time-to-unlock", 30));
        putProperty(new PropertyInteger(30, "time-to-end", 30));


        putProperty(new PropertyListString(List.of("time-to-start", "time-to-unlock", "time-to-end"), "timer-queue"));

        putProperty(new PropertyLocation(null, "location", null));

        putProperty(new PropertyString("world", "world"));

        putProperty(new PropertyBoolean(false, "start-countdown-after-click"));
        putProperty(new PropertyBoolean(false, "time-stop-event-must-go"));

        putProperty(new PropertyBoolean(false, "is-spawned", false));
        putProperty(new PropertyBoolean(false, "activated", false));

        putProperty(new PropertyBoolean(false, "opened", false));

        putProperty(new PropertyString("default", "timer"));


        putProperty(new PropertyString("?", "x"));
        putProperty(new PropertyString("?", "y"));
        putProperty(new PropertyString("?", "z"));

        putProperty(new PropertyMaterial(Material.RESPAWN_ANCHOR, "closed-material"));
        putProperty(new PropertyMaterial(Material.ENDER_CHEST, "open-material"));

        listeners.add(new CustomEventListener.CustomEventListenerBuilder()
                .key(NamespacedKey.fromString("start_listener"))
                .customEvent(CustomEvent.START_EVENT)
                .description("test")
                .commands(new String[]{"[SET_MATERIAL] {closed-material}", "[PROPERTY] set is-spawned true", "[SPAWN_HOLOGRAM] default test"})
                .build()
        );
        listeners.add(new CustomEventListener.CustomEventListenerBuilder()
                .key(NamespacedKey.fromString("unlock_listener"))
                .customEvent(CustomEvent.UNLOCK_EVENT)
                .description("test")
                .commands(new String[]{
                        "[PROPERTY] set opened true",
                        "[SET_MATERIAL] {open-material}"
                })
                .build()
        );

        listeners.add(new CustomEventListener.CustomEventListenerBuilder()
                .key(NamespacedKey.fromString("end_listener"))
                .customEvent(CustomEvent.END_EVENT)
                .description("test")
                .commands(new String[]{
                        "[SET_MATERIAL] AIR",
                        "[PROPERTY] reset time-to-start",
                        "[PROPERTY] reset location",
                        "[PROPERTY] reset is-spawned",
                        "[PROPERTY] reset time-to-unlock",
                        "[PROPERTY] reset activated",
                        "[PROPERTY] reset opened",
                        "[PROPERTY] reset time-to-end",
                        "[REMOVE_HOLOGRAM] test"
                })
                .build()
        );

        listeners.add(new CustomEventListener.CustomEventListenerBuilder()
                .key(NamespacedKey.fromString("timer_listener"))
                .customEvent(CustomEvent.TIMER)
                .description("test")
                .condition(new Condition()
                        .when(new Condition().commands(
                                "[MESSAGE_ALL] time-to-start={time-to-start}",
                                "[MESSAGE_ALL] is-spawned={is-spawned}",
                                "[MESSAGE_ALL] time-to-unlock={time-to-unlock}",
                                "[MESSAGE_ALL] activated={activated}",
                                "[MESSAGE_ALL] opened={opened}",
                                "[MESSAGE_ALL] time-to-end={time-to-end}",
                                "[MESSAGE_ALL] location={location}"
                        ))
                        .when(new Condition("{time-to-start} < 600 && {x} == ? && {y} == ? && {z} == ?")
                                .commands("[TRY_GEN_LOC]")
                        )
                        .when(new Condition("{time-to-start} > 0")// && {min-players-to-start} <= 5
                                .commands("[PROPERTY] set time-to-start match[{time-to-start} - 1]")
                        )
                        .when(new Condition("{time-to-start} == 0")
                                .when(new Condition("{is-spawned} == false")
                                        .commands("[CALL_EVENT] START_EVENT")
                                        .else_(new Condition("{time-to-unlock} > 0")
                                                .when(new Condition("{start-countdown-after-click} == true")
                                                        .when(new Condition("{activated} == true")
                                                                .commands("[PROPERTY] set time-to-unlock match[{time-to-unlock} - 1]")
                                                        )
                                                        .else_(new Condition()
                                                                .commands("[PROPERTY] set time-to-unlock match[{time-to-unlock} - 1]")
                                                        )
                                                )
                                                .else_(new Condition("{opened} == false")
                                                        .commands("[CALL_EVENT] UNLOCK_EVENT")
                                                        .else_(new Condition("{time-to-end} > 0")
                                                                .when(new Condition("{time-stop-event-must-go} == true || {opened} == true")
                                                                        .commands("[PROPERTY] set time-to-end match[{time-to-end} - 1]")
                                                                )
                                                                .else_(new Condition()
                                                                        .commands("[CALL_EVENT] END_EVENT")
                                                                )
                                                        )
                                                )
                                        )
                                      //  .else_()
                                )


                        ))
                .build()
        );

    }

    @Override
    public boolean canSpawn() {
        return false;
    }

    @Override
    public void tick() {
        callEvent(null, CustomEvent.TIMER);
      //  Message.logger(getProperties().values().toString());
    }

    @Override
    public void callEvent(@Nullable Player player, CustomEvent customEvent) {
        for (CustomEventListener listener : listeners) {
            listener.update(null, this, customEvent, false);
        }
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
    public void registerObserver(Observer observer) {

    }

    @Override
    public void unregisterObserver(Observer observer) {

    }

    @Override
    public void notifyObservers(CustomEvent customEvent, @Nullable Player pl) {
        long x = System.currentTimeMillis();

        List<Observer> tempObservers = new ArrayList<>(observers);

        tempObservers.forEach(o -> o.update(pl, this, customEvent, false));

        AirDropUtils.getStaticObservers().forEach(o -> o.update(pl, this, customEvent, false));

    }

    @Override
    public boolean hasObserver(Observer observer) {
        return false;
    }

    @Override
    public List<Observer> getObservers() {
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
    public void invokeListener(NamespacedKey listener, @Nullable Player player, CustomEvent customEvent) {

    }

    private void loadEmptyLogger() {
        logger = Logger.getLogger("airdrop-" + id + "-logger");
        logger.setLevel(Level.OFF);
        Handler emptyHandler = new ConsoleHandler();
        emptyHandler.setLevel(Level.OFF);
        logger.addHandler(emptyHandler);
    }

    private void loadLogger() throws IOException {
        File logFile = getLogFile();

        logger = Logger.getLogger("airdrop-" + id + "-logger");
        fileHandler = new FileHandler(logFile.getPath());

        fileHandler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord record) {
                if (record.getLevel() == Level.SEVERE) {
                    return String.format("[%s]: %s in %s: %s\n", record.getLevel().getName(), record.getSourceClassName(), record.getSourceMethodName(), record.getMessage());
                }
                return String.format("[%s]: %s\n", record.getLevel().getName(), record.getMessage());
            }
        });
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);
        logger.info("Start logger");
    }

    @NotNull
    private File getLogFile() {
        File folder = new File(homeDir + "/logs");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String baseName = formatter.format(date) + "-log";
        int suffix = 0;

        String logName = baseName;
        File logFile = new File(folder, logName + ".log");

        while (logFile.exists()) {
            suffix++;
            logName = baseName + "(" + suffix + ")";
            logFile = new File(folder, logName + ".log");
        }

        return logFile;
    }


    @Override
    public String replace(String string) {
        StringBuilder sb = new StringBuilder(string);
        for (Map.Entry<String, Property<?>> entry : getProperties().entrySet()) {
            String placeholder = String.format("{%s}", entry.getKey());
            int startPos = sb.indexOf(placeholder);
            while (startPos != -1) {
                if (startPos > 0) {
                    if (sb.charAt(startPos - 1) == '\\') {
                        sb.replace(startPos - 1, startPos, "");
                        startPos = sb.indexOf(placeholder, startPos);
                        continue;
                    }
                }
                sb.replace(startPos, startPos + placeholder.length(), String.valueOf(entry.getValue().getValue()));
                startPos = sb.indexOf(placeholder, startPos);
            }
        }
        return sb.toString();
    }

    @Override
    public GeneratorSetting getGeneratorSetting() {
        return generatorSetting;
    }

    @Override
    public Logger logger() {
        return logger;
    }

}
