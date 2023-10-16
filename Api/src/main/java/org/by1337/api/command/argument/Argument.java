package org.by1337.api.command.argument;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.by1337.api.command.requires.Requires;
import org.by1337.api.command.CommandSyntaxError;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a command argument.
 */
@Getter
public abstract class Argument {
    protected final String name;
    protected final List<String> exx;
    protected Requires requires;

    /**
     * Constructs an Argument with the specified name and no examples.
     *
     * @param name The name of the argument.
     */
    public Argument(String name) {
        this.name = name;
        this.exx = new ArrayList<>();
    }

    /**
     * Constructs an Argument with the specified name and a list of example values.
     *
     * @param name     The name of the argument.
     * @param exx A list of example values for the argument.
     */
    public Argument(String name, List<String> exx) {
        this.name = name;
        this.exx = exx;
    }

    /**
     * Processes the input string and returns an object representing the argument value.
     *
     * @param sender The sender of the command.
     * @param str    The input string to process.
     * @return An object representing the processed argument value.
     * @throws CommandSyntaxError If there's a syntax error in the argument processing.
     */
    public abstract Object process(CommandSender sender, String str) throws CommandSyntaxError;

    public Argument requires(Requires requires){
        this.requires = requires;
        return this;
    }
}
