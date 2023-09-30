package org.by1337.bairdrop.airdrop;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.menu.property.EditableProperties;
import org.by1337.bairdrop.menu.property.property.*;
import org.by1337.bairdrop.observer.CustomEventListener;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;
import java.util.stream.Collectors;

public abstract class BaseAirDrop extends EditableProperties implements Airdrop {

    protected final String id;

    protected final File homeDir;
    private Logger logger;
    private FileHandler fileHandler;

    protected List<CustomEventListener> rootListeners = new ArrayList<>();
    protected LinkedList<String> countingQueue = new LinkedList<>(List.of(
            "time-to-start",
            "time-to-open",
            "time-to-end"
    ));


    protected BaseAirDrop(String id, File homeDir) {
        this.id = id;
        this.homeDir = homeDir;
        initDefaultProperties();
        initTemps();

        try {
            if ((Boolean) getPropertyByName("logger-is-enabled").getValue()) {
                loadLogger();
            } else {
                loadEmptyLogger();
            }
        } catch (IOException e) {
            Message.error(e.getLocalizedMessage());
        }

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

    protected void initTemps() {
        registerProperty(new PropertyInteger(
                120,
                "time-to-start",
                null,
                null,
                null,
                this
        )).editable(false).saveable(false);
        registerProperty(new PropertyInteger(
                120,
                "time-to-open",
                null,
                null,
                null,
                this
        )).editable(false).saveable(false);
        registerProperty(new PropertyInteger(
                120,
                "time-to-end",
                null,
                null,
                null,
                this
        )).editable(false).saveable(false);
    }

    private void initDefaultProperties() {
        registerProperty(new PropertyString(
                id,
                "id",
                null,
                null,
                null,
                this
        )).editable(false);

        registerProperty(new PropertyBoolean(
                true,
                "logger-is-enabled",
                new Resource("airdrop.logger.lore"),
                new Resource("airdrop.logger.material"),
                new Resource("airdrop.logger.name"),
                this
        ));
    }

    @Override
    public Logger logger() {
        return logger;
    }

}
