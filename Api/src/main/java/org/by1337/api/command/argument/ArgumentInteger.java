package org.by1337.api.command.argument;

import org.bukkit.command.CommandSender;
import org.by1337.api.command.CommandSyntaxError;
import org.by1337.api.lang.Lang;

import java.util.List;

/**
 * Represents an integer argument for a command.
 */
public class ArgumentInteger extends Argument {
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;

    /**
     * Constructs an ArgumentInteger with the specified name and no minimum or maximum bounds.
     *
     * @param name The name of the argument.
     */
    public ArgumentInteger(String name) {
        super(name);
    }

    /**
     * Constructs an ArgumentInteger with the specified name, a list of example values, and no minimum or maximum bounds.
     *
     * @param name The name of the argument.
     * @param exx  A list of example values for the argument.
     */
    public ArgumentInteger(String name, List<String> exx) {
        super(name, exx);
    }

    /**
     * Constructs an ArgumentInteger with the specified name and a minimum bound.
     *
     * @param name The name of the argument.
     * @param min  The minimum allowed value for the argument.
     */
    public ArgumentInteger(String name, int min) {
        super(name);
        this.min = min;
    }

    /**
     * Constructs an ArgumentInteger with the specified name, a list of example values, and a minimum bound.
     *
     * @param name The name of the argument.
     * @param exx  A list of example values for the argument.
     * @param min  The minimum allowed value for the argument.
     */
    public ArgumentInteger(String name, List<String> exx, int min) {
        super(name, exx);
        this.min = min;
    }

    /**
     * Constructs an ArgumentInteger with the specified name, a minimum bound, and a maximum bound.
     *
     * @param name The name of the argument.
     * @param min  The minimum allowed value for the argument.
     * @param max  The maximum allowed value for the argument.
     */
    public ArgumentInteger(String name, int min, int max) {
        super(name);
        this.min = min;
        this.max = max;
    }

    /**
     * Constructs an ArgumentInteger with the specified name, a list of example values, a minimum bound, and a maximum bound.
     *
     * @param name The name of the argument.
     * @param exx  A list of example values for the argument.
     * @param min  The minimum allowed value for the argument.
     * @param max  The maximum allowed value for the argument.
     */
    public ArgumentInteger(String name, List<String> exx, int min, int max) {
        super(name, exx);
        this.min = min;
        this.max = max;
    }

    /**
     * Processes the input string and returns an Integer representing the argument value within specified bounds.
     *
     * @param sender The sender of the command.
     * @param str    The input string to process.
     * @return An Integer representing the processed argument value.
     * @throws CommandSyntaxError If there's a syntax error in the argument processing or the value is out of bounds.
     */
    @Override
    public Object process(CommandSender sender, String str) throws CommandSyntaxError {
        if (str.isEmpty()) return 0;
        try {
            int val = Integer.parseInt(str);

            if (val < min)
                throw new CommandSyntaxError(Lang.getMessage("number-too-big"), val, min);

            if (val > max)
                throw new CommandSyntaxError(Lang.getMessage("number-too-small"), val, max);

            return val;

        } catch (NumberFormatException e) {
            throw new CommandSyntaxError(Lang.getMessage("nan"), str);
        }
    }
}
