package org.by1337.bairdrop.airdrop.command.argument;

import org.bukkit.command.CommandSender;
import org.by1337.bairdrop.airdrop.command.CommandSyntaxError;
import org.by1337.bairdrop.util.InvalidCharacters;

import java.util.List;

/**
 * Represents an argument that validates input strings for valid characters.
 */
public class ArgumentValidCharacters extends Argument {
    /**
     * Constructs an ArgumentValidCharacters with the specified name.
     *
     * @param name The name of the argument.
     */
    public ArgumentValidCharacters(String name) {
        super(name);
    }

    /**
     * Constructs an ArgumentValidCharacters with the specified name and custom examples.
     *
     * @param name The name of the argument.
     * @param exx  A list of example values for the argument.
     */
    public ArgumentValidCharacters(String name, List<String> exx) {
        super(name, exx);
    }

    /**
     * Processes the input string and checks if it contains any invalid characters. Throws a CommandSyntaxError if invalid characters are found.
     *
     * @param sender The sender of the command.
     * @param str    The input string to process.
     * @return The input string if it contains valid characters.
     * @throws CommandSyntaxError If the input string contains invalid characters.
     */
    @Override
    public Object process(CommandSender sender, String str) throws CommandSyntaxError {
        String invalidChars = InvalidCharacters.getInvalidCharacters(str);
        if (!invalidChars.isEmpty())
            throw new CommandSyntaxError(String.format("&cInvalid characters: %s", invalidChars));
        return str;
    }
}
