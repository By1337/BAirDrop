package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import com.ibm.icu.impl.ICUResourceBundleReader;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.util.Message;
import org.by1337.lib.command.Command;
import org.by1337.lib.command.CommandException;
import org.by1337.lib.command.argument.ArgumentEnumValue;
import org.by1337.lib.command.argument.ArgumentInteger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SoundAllCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[SOUND_ALL]";
    }

    @Override
    public void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        createCommand().executor(((sender, args) -> {
            Sound sound = (Sound) args.getOrThrow("sound", USAGE.getString(), usage());
            int volume = (int) args.getOrDefault("volume", 1);
            int pitch = (int) args.getOrDefault("pitch", 1);
            Message.sendAllSound(sound, volume, pitch);
        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[SOUND_ALL] <sound> <?volume> <?pitch>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentEnumValue("sound", Sound.class))
                .argument(new ArgumentInteger("volume"))
                .argument(new ArgumentInteger("pitch"));
    }
}