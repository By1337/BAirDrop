package org.by1337.bairdrop.observer;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.observer.observer.Observer;
import org.by1337.bairdrop.observer.requirement.Requirement;
import org.by1337.bairdrop.observer.requirement.Requirements;
import org.by1337.bairdrop.observer.requirement.impl.RequirementNumericalCheck;
import org.by1337.bairdrop.observer.requirement.impl.RequirementStringCheck;
import org.by1337.bairdrop.util.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CustomListenerLoader {
    private static final Resource listenersIsEmpty = new Resource("listeners.warning.list-listeners-is-empty"); //todo добавить в файл с языком
    @Getter
    private static HashMap<NamespacedKey, Observer> customEventListeners = new HashMap<>();

    public static void load(FileConfiguration file, String listenersKey) {
        if (file.getConfigurationSection(listenersKey) == null) {
            Message.warning(listenersIsEmpty.getString());
            return;
        }
        for (String key : file.getConfigurationSection(listenersKey).getKeys(false)) {
            try {
                String event = Objects.requireNonNull(file.getString(String.format("%s.%s.event", listenersKey, key)), String.format("тип ивента не указан!: %s", key));
                List<String> commands = file.getStringList(String.format("%s.%s.commands", listenersKey, key));
                List<String> denyCommands = file.getStringList(String.format("%s.%s.deny-commands", listenersKey, key));
                String description = Objects.requireNonNull(file.getString(String.format("%s.%s.description", listenersKey, key)), String.format("Описание слушателя не указано! %s", key));

                CustomEvent customEvent = CustomEvent.getByKey(NamespacedKey.fromString(event.toLowerCase()));
                if (customEvent == null) {
                    Message.error("Незарегистрированный ивент! %s", event);
                    continue;
                }

                List<Requirement> requirementsList = new ArrayList<>();
                if (file.getConfigurationSection(String.format("%s.%s.requirement", listenersKey, key)) != null) {
                    for (String checkId : file.getConfigurationSection(String.format("%s.%s.requirement", listenersKey, key)).getKeys(false)) {
                        String type = Objects.requireNonNull(
                                file.getString(String.format("%s.%s.requirement.%s.type", listenersKey, key, checkId)),
                                String.format("Тип проверки не указан! Проверка %s в %s", checkId, key)
                        );
                        String input = Objects.requireNonNull(
                                file.getString(String.format("%s.%s.requirement.%s.input", listenersKey, key, checkId)),
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
    }

}
