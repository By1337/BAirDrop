package org.by1337.bairdrop.airdrop.command;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.airdrop.command.impl.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class CommandRegistry {
    private static final HashMap<String, CommandExecutor> registeredCommands;

    public static void registerCommandExecutor(CommandExecutor executor){
        if (registeredCommands.containsKey(executor.getCommandPrefix())){
            throw new IllegalStateException("the command executor already registered!");
        }
        registeredCommands.put(executor.getCommandPrefix(), executor);
    }

    public static void execute(@Nullable AirDrop airDrop, @Nullable Player player, @NotNull String command){
        for (CommandExecutor commandExecutor : registeredCommands.values()){
            if (command.startsWith(commandExecutor.getCommandPrefix())){
                commandExecutor.execute(airDrop, player, command);
                return;
            }
        }
    }
    static {
        registeredCommands = new HashMap<>();
        registerCommandExecutor(new ActivateCommand());
        registerCommandExecutor(new BlockSetCloseCommand());
        registerCommandExecutor(new BlockSetOpenCommand());
        registerCommandExecutor(new EasyBossBarCommand());
        registerCommandExecutor(new EffectStartCommand());
        registerCommandExecutor(new EffectStopCommand());
        registerCommandExecutor(new EffectStopAllCommand());
        registerCommandExecutor(new SchematicsPasteCommand());
        registerCommandExecutor(new SchematicsRemoveCommand());
        registerCommandExecutor(new SetBlockFaceCommand());
        registerCommandExecutor(new SetHoloTimeToStartCommand());
        registerCommandExecutor(new SetMaterialCommand());
        registerCommandExecutor(new SetRegionCommand());
        registerCommandExecutor(new SetTimeEndCommand());
        registerCommandExecutor(new SetTimeStartCommand());
        registerCommandExecutor(new SetTimeUnlockCommand());
        registerCommandExecutor(new RunJsCommand());
        registerCommandExecutor(new ErrorCommand());
        registerCommandExecutor(new LoggerCommand());
        registerCommandExecutor(new MessageAllCommand());
        registerCommandExecutor(new SubTitleAllCommand());
        registerCommandExecutor(new TitleAllCommand());
        registerCommandExecutor(new ActionbarAllCommand());
        registerCommandExecutor(new ConsoleCommand());
        registerCommandExecutor(new SoundAllCommand());
        registerCommandExecutor(new PlayerSetItemCommand());
        registerCommandExecutor(new PlayerCloseInventoryCommand());
        registerCommandExecutor(new MessageCommand());
        registerCommandExecutor(new TitleCommand());
        registerCommandExecutor(new SubTitleCommand());
        registerCommandExecutor(new ActionbarCommand());
        registerCommandExecutor(new SoundCommand());
        registerCommandExecutor(new PlayerPerformCommand());
        registerCommandExecutor(new NewBossBarCommand());
        registerCommandExecutor(new BossBarCommand());
        registerCommandExecutor(new RemoveBossBarCommand());
        registerCommandExecutor(new CallListenerCommand());
        registerCommandExecutor(new NearPlayersCommand());
        registerCommandExecutor(new SummonCommand());
    }

}