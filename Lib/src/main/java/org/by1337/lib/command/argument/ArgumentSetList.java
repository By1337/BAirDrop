package org.by1337.lib.command.argument;

import org.bukkit.command.CommandSender;
import org.by1337.lib.command.CommandSyntaxError;
import org.by1337.lib.lang.Lang;

import java.util.List;

/**
 * Represents an argument that accepts values from a predefined set of options.
 */
public class ArgumentSetList extends Argument {
    public final List<String> items;

    /**
     * Constructs an ArgumentSetList with the specified name and a list of allowed items.
     *
     * @param name  The name of the argument.
     * @param items A list of allowed values for the argument.
     */
    public ArgumentSetList(String name, List<String> items) {
        super(name);
        this.items = items;
        super.exx.addAll(items);
    }

    /**
     * Constructs an ArgumentSetList with the specified name, custom examples, and a list of allowed items.
     *
     * @param name  The name of the argument.
     * @param exx   A list of example values for the argument.
     * @param items A list of allowed values for the argument.
     */
    public ArgumentSetList(String name, List<String> exx, List<String> items) {
        super(name, exx);
        this.items = items;
        super.exx.addAll(items);
    }

    /**
     * Processes the input string and returns the value if it's in the predefined set of options.
     *
     * @param sender The sender of the command.
     * @param str    The input string to process.
     * @return The processed argument value if it's a valid option.
     * @throws CommandSyntaxError If the input string is not a valid option.
     */
    @Override
    public Object process(CommandSender sender, String str) throws CommandSyntaxError {
        if (str.isEmpty()) return null;
        if (!items.contains(str)) {
            if (items.size() > 10)
                throw new CommandSyntaxError(Lang.getMessage("constant-not-found-more"), str, items.subList(0, 5), items.size() - 10);
            throw new CommandSyntaxError(Lang.getMessage("constant-not-found"), str, items);
        }
        return str;
    }
}
