package org.by1337.bairdrop.observer;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.migrator.command.CmdMigrator;
import org.by1337.bairdrop.observer.observer.Observer;
import org.by1337.bairdrop.observer.requirement.Requirement;
import org.by1337.bairdrop.observer.requirement.Requirements;
import org.by1337.bairdrop.observer.requirement.impl.RequirementNumericalCheck;
import org.by1337.bairdrop.observer.requirement.impl.RequirementStringCheck;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.CommandException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CustomListenerLoader {

    private static final Resource listenersIsEmpty = new Resource("listeners.warning.list-listeners-is-empty"); //todo добавить в файл с языком
    @Getter
    private static HashMap<NamespacedKey, Observer> customEventListeners = new HashMap<>();

    private FileConfiguration listeners;
    private File fileListeners;

    public CustomListenerLoader() {
        fileListeners = new File(BAirDrop.getInstance().getDataFolder() + File.separator + "listeners.yml");
        if (!fileListeners.exists()) {
            BAirDrop.getInstance().saveResource("listeners.yml", true);
        }
        listeners = YamlConfiguration.loadConfiguration(fileListeners);
    }

    /**
     * TODO: На данный момент невозможно провести проверку слушателей из-за наличия плейсхолдеров и отсутствия возможности удостовериться,
     * TODO: что, например, {x} действительно заменяется на число. В настоящее время отсутствуют конкретные идеи о том, как это исправить, и, возможно, это придется удалить.
     */
    private void validateListeners() {
        if(true){
            return;
        }
        for (NamespacedKey key : new ArrayList<>(customEventListeners.keySet())) {
            try {
                customEventListeners.get(key).validateCommands();
            } catch (CommandException e) {
                Message.error(e.getLocalizedMessage());
                Message.error("listener %s could not be loaded", key);
                customEventListeners.remove(key);
            }
        }
        Message.logger("loaded %s listeners!", customEventListeners.size());
    }

    private void update() {
        for (String key : listeners.getConfigurationSection("listeners").getKeys(false)) {
            List<String> oldCommands = listeners.getStringList(String.format("%s.%s.commands", "listeners", key));
            List<String> oldDenyCommands = listeners.getStringList(String.format("%s.%s.deny-commands", "listeners", key));

            List<String> commands = new ArrayList<>();
            List<String> denyCommands = new ArrayList<>();

            for (String cmd : oldCommands) {
                commands.add(CmdMigrator.adapt(cmd));
            }

            for (String cmd : oldDenyCommands) {
                denyCommands.add(CmdMigrator.adapt(cmd));
            }
            listeners.set(String.format("%s.%s.commands", "listeners", key), commands);
            listeners.set(String.format("%s.%s.deny-commands", "listeners", key), denyCommands);

        }
        listeners.set("version", 1);

        try {
            listeners.save(fileListeners);
        } catch (IOException e) {
            Message.error(e.getLocalizedMessage());
        }
    }

    public void load() {
        int version = listeners.getInt("version", 0);
        if (listeners.getConfigurationSection("listeners") == null) {
            Message.warning(listenersIsEmpty.getString());
            return;
        }
        if (version == 0) {
            update();
        }
        for (String key : listeners.getConfigurationSection("listeners").getKeys(false)) {
            try {
                String event = Objects.requireNonNull(listeners.getString(String.format("%s.%s.event", "listeners", key)), String.format("тип ивента не указан!: %s", key));
                List<String> commands = listeners.getStringList(String.format("%s.%s.commands", "listeners", key));
                List<String> denyCommands = listeners.getStringList(String.format("%s.%s.deny-commands", "listeners", key));
                String description = Objects.requireNonNull(listeners.getString(String.format("%s.%s.description", "listeners", key)), String.format("Описание слушателя не указано! %s", key));

                CustomEvent customEvent = CustomEvent.getByKey(NamespacedKey.fromString(event.toLowerCase()));
                if (customEvent == null) {
                    Message.error("Незарегистрированный ивент! %s", event);
                    continue;
                }

                List<Requirement> requirementsList = new ArrayList<>();
                if (listeners.getConfigurationSection(String.format("%s.%s.requirement", "listeners", key)) != null) {
                    for (String checkId : listeners.getConfigurationSection(String.format("%s.%s.requirement", "listeners", key)).getKeys(false)) {
                        String type = Objects.requireNonNull(
                                listeners.getString(String.format("%s.%s.requirement.%s.type", "listeners", key, checkId)),
                                String.format("Тип проверки не указан! Проверка %s в %s", checkId, key)
                        );
                        String input = Objects.requireNonNull(
                                listeners.getString(String.format("%s.%s.requirement.%s.input", "listeners", key, checkId)),
                                String.format("Условия проверки не указано! Проверка %s в %s", checkId, key)
                        );
                        requirementsList.add(
                                switch (type) {
                                    case "STRING_CHECK", "str" -> new RequirementStringCheck(input);
                                    case "NUMERICAL_CHECK", "num" -> new RequirementNumericalCheck(input);
                                    default ->
                                            throw new IllegalArgumentException(String.format("Неизвестный тип проверки! '%s' Проверка %s в %s", type, checkId, key));
                                }
                        );
                    }
                }
                Requirements requirements = new Requirements();
                requirements.set(requirementsList);

                CustomEventListener customEventListener = new CustomEventListener.CustomEventListenerBuilder()
                        .customEvent(customEvent)
                        .commands(commands.toArray(new String[0]))
                        .denyCommands(denyCommands.toArray(new String[0]))
                        .description(description)
                        .requirements(requirements)
                        .key(NamespacedKey.fromString(key.toLowerCase()))
                        .build();

                customEventListeners.put(NamespacedKey.fromString(key.toLowerCase()), customEventListener);
            } catch (NullPointerException | IllegalArgumentException e) {
                Message.error("Ошибка при загрузке слушателя! %s", e.getLocalizedMessage());
            }

        }
        validateListeners();
    }

}
