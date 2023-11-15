package org.by1337.bairdrop.airdrop.registry;

import org.bukkit.entity.Player;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.airdrop.Airdrop;
import org.by1337.bairdrop.airdrop.command.airdrop.CommandExecutor;
import org.by1337.bairdrop.airdrop.command.airdrop.impl.*;
import org.by1337.bairdrop.observer.event.Event;
import org.by1337.bairdrop.util.OLDMessage;
import org.by1337.api.command.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class AirDropCommandRegistry {
    private static final HashMap<String, CommandExecutor> registeredCommands;

    public static void registerCommandExecutor(CommandExecutor executor) {
        if (registeredCommands.containsKey(executor.getCommandPrefix())) {
            throw new IllegalStateException("the command executor already registered!");
        }
        registeredCommands.put(executor.getCommandPrefix(), executor);
    }

    public static void execute(@NotNull Event event, @NotNull String command) {
        try {
            for (CommandExecutor commandExecutor : registeredCommands.values()) {
                if (command.startsWith(commandExecutor.getCommandPrefix())) {
                    commandExecutor.execute(event, command);
                    return;
                }
            }
            BAirDrop.MESSAGE.warning("no executed: %s", command);
        } catch (CommandException e) {
            BAirDrop.MESSAGE.error(e);
        }
    }

    public static void validate(String cmd) throws CommandException {
        for (CommandExecutor commandExecutor : registeredCommands.values()) {
            if (cmd.startsWith(commandExecutor.getCommandPrefix())) {
                commandExecutor.testCommand(cmd);
                return;
            }
        }
        throw new CommandException("unknown command %s", cmd);
    }

    static {
        registeredCommands = new HashMap<>();
        registerCommandExecutor(new Activate());
        registerCommandExecutor(new BlockLidToggle());
        // registerCommandExecutor(new BlockSetOpenCommand());
        registerCommandExecutor(new EasyBossBar());
        registerCommandExecutor(new EffectStart());
        registerCommandExecutor(new EffectStop());
        registerCommandExecutor(new EffectStopAll());
        registerCommandExecutor(new SchematicsPaste());
        registerCommandExecutor(new SchematicsRemove());
        registerCommandExecutor(new SetBlockFace());
        //   registerCommandExecutor(new SetH oloTimeToStartCommand()); //deleted
        registerCommandExecutor(new SetMaterial());
        registerCommandExecutor(new SetRegion());
        registerCommandExecutor(new SetTimeEnd());
        registerCommandExecutor(new SetTimeStart());
        registerCommandExecutor(new SetTimeUnlock());
        registerCommandExecutor(new RunJs());
        registerCommandExecutor(new ErrorCommand());
        registerCommandExecutor(new LoggerCommand());
        registerCommandExecutor(new MessageAllCommand());
        registerCommandExecutor(new SubTitleAllCommand());
        registerCommandExecutor(new TitleAllCommand());
        registerCommandExecutor(new ActionbarAllCommand());
        registerCommandExecutor(new DispatchCommand());
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
        registerCommandExecutor(new InvokeListenerCommand());
        registerCommandExecutor(new NearPlayersCommand());
        registerCommandExecutor(new SummonCommand());

        registerCommandExecutor(new PropertyEditCommand());
        registerCommandExecutor(new CallEvent());
        registerCommandExecutor(new TryGetLocCommand());

        registerCommandExecutor(new RemoveHologram());
        registerCommandExecutor(new SpawnHologramCommand());
    }

}
