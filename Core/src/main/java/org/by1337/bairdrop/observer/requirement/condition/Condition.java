package org.by1337.bairdrop.observer.requirement.condition;

import org.bukkit.entity.Player;
import org.by1337.api.chat.Placeholderable;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.observer.requirement.impl.RequirementNumericalCheck;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Condition {
    private String condition;

    private final List<Condition> innerConditions = new ArrayList<>();
    private Condition elseCondition = null;


    private List<String> commands = new ArrayList<>();

    @Nullable
    public String getCondition() {
        return condition;
    }

    public void setCondition(@Nullable String condition) {
        this.condition = condition;
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

    public List<String> getCommands(@NotNull Event event) {
        List<String> list;

        if (execute(event)) {
            list = new ArrayList<>(commands);
        } else {
            if (elseCondition != null) {
                return elseCondition.getCommands(event);
            } else
                return new ArrayList<>();
        }
        for (Condition innerCondition : innerConditions) {
            list.addAll(innerCondition.getCommands(event));
        }

        return list;
    }

    public boolean execute(@NotNull Event event) {
        if (condition == null) return true;
        return new RequirementNumericalCheck(condition).check(event);
    }


    @Override
    public String toString() {
        return toString(0, true, false);
    }

    public String toString(boolean compact) {
        return toString(0, true, compact);
    }

    public String toString(int lvl, boolean addOpenIf, boolean compact) {
        StringBuilder sb = new StringBuilder();

        if (condition == null && (commands == null || commands.isEmpty()) && elseCondition == null) {
            for (Condition innerCondition : innerConditions) {
                sb.append(innerCondition.toString(lvl, true, compact));
            }
            return sb.toString();
        }
        String tabLevel = compact ? "" : " ".repeat(lvl);
        String space = compact ? "" : " ";
        String lf = compact ? "" : "\n";

        sb.append(tabLevel);

        if (condition == null) {
            if (addOpenIf) {
                sb.append("if").append(space).append("()").append(space).append("{").append(lf);//if () {\n
            } else {
                sb.append("{").append(lf);
            }
        } else {
            //if ("condition") {\n
            sb.append("if").append(space).append("(\"").append(condition.replaceAll("\"", "\\\\\"")).append("\")").append(space).append("{").append(lf);
        }

        for (Condition innerCondition : innerConditions) {
            sb.append(innerCondition.toString(lvl + 4, true, compact));
        }

        if (commands != null && !commands.isEmpty()) {
            String commandLvl = compact ? "" : " ".repeat(lvl + 4);
            sb.append(commandLvl);
            sb.append("[").append(space);
            int count = 1;
            for (String command : commands) {
                sb.append('"').append(command.replaceAll("\"", "\\\\\"")).append("\",").append(space);
                if (count >= 3) {
                    sb.append(lf);
                    sb.append(commandLvl);
                    count = 0;
                }
                count++;
            }
            sb.setLength(sb.length() - (1 + space.length()));
            sb.append(space).append("]").append(lf);
        }
        sb.append(tabLevel);
        sb.append("}");

        if (elseCondition != null) {
            String str = elseCondition.toString(lvl, false, compact);
            if (str.startsWith(" ".repeat(lvl))) {
                str = str.substring(lvl);
            }
            if (str.startsWith("if")) {
                sb.append(space).append("else ").append(str);
            } else {
                sb.append(space).append("else").append(space).append(str);
            }
        } else {
            sb.append(lf);
        }
        return sb.toString();
    }
}
