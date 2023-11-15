package org.by1337.bairdrop.migrator.observer;

import org.bukkit.configuration.file.YamlConfiguration;
import org.by1337.api.chat.util.LogLevel;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.api.configuration.YamlConfig;
import org.by1337.api.configuration.YamlContext;
import org.by1337.bairdrop.migrator.command.CmdMigrator;
import org.by1337.bairdrop.observer.requirement.condition.Condition;
import org.by1337.bairdrop.observer.requirement.condition.ConditionParse;
import org.by1337.bairdrop.observer.CustomEventListener;
import org.by1337.bairdrop.observer.CustomEventListenerBuilder;
import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.bairdrop.observer.requirement.Requirement;
import org.by1337.bairdrop.observer.requirement.Requirements;
import org.by1337.bairdrop.observer.requirement.impl.RequirementNumericalCheck;
import org.by1337.bairdrop.observer.requirement.impl.RequirementStringCheck;
import org.by1337.api.util.NameKey;

import java.util.*;

public class ListenerMigrator {
    public static final int LASTED_VERSION = 1;
    private final YamlConfig yaml;
    private int currentVersion;

    private HashMap<NameKey, CustomEventListener> customEventListeners = new HashMap<>();

    public ListenerMigrator(YamlConfig yaml, int currentVersion) {
        this.yaml = yaml;
        this.currentVersion = currentVersion;
        if (currentVersion > LASTED_VERSION){
            throw new IllegalStateException(yaml.getFile().getPath() + " bad version! Lasted: " + LASTED_VERSION);
        }
    }

    public void adapt() {
        if (currentVersion == 0){
            BAirDrop.MESSAGE.debug("update listeners '%s' from %s to %s version", LogLevel.LEVEL_3, yaml.getFile().getPath(), 0, 1);
            readLegacyListeners();
            updateCommandSyntax();
            currentVersion = 1;
            resave();
            adapt();
        }
    }

    private void resave(){
        YamlContext context = new YamlContext(new YamlConfiguration());
        context.set("listeners", customEventListeners);
        context.set("version", currentVersion);
        yaml.setYamlConfiguration((YamlConfiguration) context.getHandle());
        yaml.trySave();
    }
    private void updateCommandSyntax() {
        for (CustomEventListener observer : customEventListeners.values()) {
            List<String> commands = Arrays.asList(observer.commands());
            List<String> denyCommands = Arrays.asList(observer.denyCommands());

            commands.replaceAll(CmdMigrator::adapt);
            denyCommands.replaceAll(CmdMigrator::adapt);
        }
    }

    private void readLegacyListeners(){
        for (String key : yaml.getContext().getHandle().getConfigurationSection("listeners").getKeys(false)) {
            try {
                String event = Objects.requireNonNull(yaml.getContext().getAsString(String.format("%s.%s.event", "listeners", key), null), String.format("тип ивента не указан!: %s", key));
                List<String> commands = yaml.getContext().getList(String.format("%s.%s.commands", "listeners", key), String.class, new ArrayList<>());
                List<String> denyCommands = yaml.getContext().getList(String.format("%s.%s.deny-commands", "listeners", key), String.class, new ArrayList<>());
                String description = Objects.requireNonNull(yaml.getContext().getAsString(String.format("%s.%s.description", "listeners", key), null), String.format("Описание слушателя не указано! %s", key));

                CustomEvent customEvent = CustomEvent.getByKey(new NameKey(event.toLowerCase()));
                if (customEvent == null) {
                    BAirDrop.MESSAGE.error("Неизвестный ивент! %s", event);
                    continue;
                }

                List<Requirement> requirementsList = new ArrayList<>();
                if (yaml.getContext().getHandle().getConfigurationSection(String.format("%s.%s.requirement", "listeners", key)) != null) {
                    for (String checkId : yaml.getContext().getHandle().getConfigurationSection(String.format("%s.%s.requirement", "listeners", key)).getKeys(false)) {
                        String type = Objects.requireNonNull(
                                yaml.getContext().getAsString(String.format("%s.%s.requirement.%s.type", "listeners", key, checkId), null),
                                String.format("Тип проверки не указан! Проверка %s в %s", checkId, key)
                        );
                        String input = Objects.requireNonNull(
                                yaml.getContext().getAsString(String.format("%s.%s.requirement.%s.input", "listeners", key, checkId), null),
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
                Condition condition;
                if (yaml.getContext().getAsString(String.format("listeners.%s.condition", key)) != null) {
                    condition = ConditionParse.parse(yaml.getContext().getAsString(String.format("listeners.%s.condition", key)));
                } else {
                    condition = null;
                }
                Requirements requirements = new Requirements();
                requirements.set(requirementsList);

                CustomEventListener customEventListener = new CustomEventListenerBuilder()
                        .customEvent(customEvent)
                        .commands(commands.toArray(new String[0]))
                        .denyCommands(denyCommands.toArray(new String[0]))
                        .description(description)
                        .requirements(requirements)
                        .nameKey(new NameKey(key))
                        .condition(condition)
                        .build();

                customEventListeners.put(customEventListener.getName(), customEventListener);
            } catch (NullPointerException | IllegalArgumentException e) {
                BAirDrop.MESSAGE.error("Ошибка при загрузке слушателя!", e);
            }
        }
    }

}
