package org.by1337.bairdrop.observer;

import org.bukkit.entity.Player;
import org.by1337.api.chat.Placeholderable;
import org.by1337.bairdrop.observer.requirement.impl.RequirementNumericalCheck;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Condition {
    private final String condition;

    private final List<Condition> innerConditions = new ArrayList<>();
    private Condition elseCondition = null;


    private List<String> commands = new ArrayList<>();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ".repeat(4));
        if (condition == null) {
            sb.append("if (){\n");
        } else {
            sb.append("if (").append('"').append(condition).append('"').append("){\n");
        }


        for (Condition innerCondition : innerConditions) {
            sb.append(" ".repeat(4));
            sb.append(innerCondition.toString());
        }
        if (!commands.isEmpty()) {
            sb.append(" ".repeat(4));
            sb.append("[ ");
            for (int i = 0; i < commands.size(); i++) {
                if (commands.size() - 1 == i) {
                    sb.append('"').append(commands.get(i)).append('"');
                } else {
                    sb.append('"').append(commands.get(i)).append("\", ");
                }
            }
            sb.append(" ]\n");
        }

        sb.append(" ".repeat(4));
        sb.append("}");

        if (elseCondition != null) {
            StringBuilder elseSb = new StringBuilder(elseCondition.toString());
            elseSb.replace(4, 6, "else");
            sb.append(elseSb);
        } else {
            sb.append("\n");
        }
        return sb.toString();

    }

    public String toString(int currentLevel, String name) {

        StringBuilder sb = new StringBuilder();

        if (condition == null) {
            sb.append(" ".repeat(currentLevel)).append(name).append(": ").append("'true'").append(" #").append(currentLevel).append("\n");
            currentLevel += 2;
        } else {
            sb.append(" ".repeat(currentLevel)).append(name).append(": ").append("'").append(condition).append("'").append(" #").append(currentLevel).append("\n");
            currentLevel += 2;
        }
        for (Condition innerCondition : innerConditions) {
            sb.append(innerCondition.toString(currentLevel, "if"));
        }
        if (elseCondition != null) {
            sb.append(elseCondition.toString(currentLevel, "else"));
        }
        if (!commands.isEmpty()) {
            sb.append(" ".repeat(currentLevel)).append("commands:").append(" #").append(currentLevel).append("\n");
            for (String command : commands) {
                sb.append(" ".repeat(currentLevel + 2)).append("- ").append("'").append(command).append("'").append(" #").append(currentLevel + 2).append("\n");
            }
        }

        return sb.toString();
    }


    public Condition(String condition) {
        this.condition = condition;
    }

    public Condition() {
        condition = null;
    }

    public Condition commands(List<String> commands) {
        this.commands = commands;
        return this;
    }


    public Condition commands(String... commands) {
        this.commands = List.of(commands);
        return this;
    }


    public Condition when(Condition condition) {
        innerConditions.add(condition);
        return this;
    }

    public Condition else_(Condition condition) {
        if (elseCondition != null)
            throw new IllegalStateException("elseCondition already exist!");
        elseCondition = condition;
        return this;
    }

    public List<String> getCommands(@Nullable Placeholderable placeholderable, @Nullable Player player) {
        List<String> list;

        if (execute(placeholderable, player)) {
            list = new ArrayList<>(commands);
        } else {
            if (elseCondition != null) {
                return elseCondition.getCommands(placeholderable, player);
            }
            else
                return new ArrayList<>();
        }
        for (Condition innerCondition : innerConditions) {
            list.addAll(innerCondition.getCommands(placeholderable, player));
        }

        return list;
    }

    public boolean execute(@Nullable Placeholderable placeholderable, @Nullable Player player) {
        if (condition == null) return true;
        return new RequirementNumericalCheck(condition).check(placeholderable, player);
    }
}
