package org.by1337.bairdrop.airdrop.command.airdrop.impl;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.util.OLDMessage;
import org.by1337.api.command.Command;
import org.by1337.api.command.CommandException;
import org.by1337.api.command.argument.ArgumentEnumValue;
import org.by1337.api.command.argument.ArgumentFloat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SoundCommand implements CommandExecutor {
    @Override
    public String getCommandPrefix() {
        return "[SOUND]";
    }

    @Override // заглушка
    public void execute(Event event, @NotNull String command) throws CommandException {
        execute(event.getAirdrop(), event.getPlayer(), command);
    }

    public void execute(@Nullable Airdrop airDrop, @Nullable Player player, @NotNull String command) throws CommandException {
        Objects.requireNonNull(player, String.format(PLAYER_IS_NULL.getString(), command));
        createCommand().executor(((sender, args) -> {
            Sound sound = (Sound) args.getOrThrow("sound", USAGE.getString(), usage());
            float volume = (float) args.getOrDefault("volume", 1);
            float pitch = (float) args.getOrDefault("pitch", 1);
            OLDMessage.sendSound(player, sound, volume, pitch);
        })).process(null, parseCommand(command));
    }

    @Override
    public String usage() {
        return "[SOUND] <sound> <?volume> <?pitch>";
    }

    @Override
    public Command createCommand() {
        return new Command(getCommandPrefix())
                .argument(new ArgumentEnumValue("sound", Sound.class))
                .argument(new ArgumentFloat("volume"))
                .argument(new ArgumentFloat("pitch"));
    }

}
