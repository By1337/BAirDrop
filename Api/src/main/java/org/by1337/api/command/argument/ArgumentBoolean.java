package org.by1337.api.command.argument;

import org.bukkit.command.CommandSender;
import org.by1337.api.command.CommandSyntaxError;

import java.util.List;

public class ArgumentBoolean extends ArgumentSetList {
    public ArgumentBoolean(String name) {
        super(name, List.of("true", "false"));
    }

    public ArgumentBoolean(String name, List<String> exx) {
        super(name, exx, List.of("true", "false"));
    }

    @Override
    public Object process(CommandSender sender, String str) throws CommandSyntaxError {
        if (super.process(sender, str).equals("true"))
            return true;
        return false;
    }
}
