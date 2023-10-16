package org.by1337.api.command.argument;

import org.bukkit.command.CommandSender;
import org.by1337.api.command.CommandSyntaxError;

import java.util.List;

public class ArgumentString extends Argument {
    public ArgumentString(String name) {
        super(name);
    }

    public ArgumentString(String name, List<String> exx) {
        super(name, exx);
    }

    @Override
    public Object process(CommandSender sender, String str) throws CommandSyntaxError {
        return str;
    }
}
