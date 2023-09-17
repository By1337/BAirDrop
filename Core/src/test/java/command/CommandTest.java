package command;

import org.by1337.bairdrop.airdrop.command.CommandSyntaxError;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class CommandTest {

    @Test
    public void testCommandExecution() throws CommandSyntaxError {
        // Create a mock CommandSender
     //   CommandSender sender = Mockito.mock(CommandSender.class);

      //  Mockito.doAnswer(invocationOnMock -> true).when(sender).hasPermission(Mockito.anyString());

//        // Create a command structure
//        Command command = new Command("bair")
//                .subcommand(new Command("compass")
//                        .subcommand(new Command("give")
//                                .argument(new ArgumentInteger("amount", 1, 64))
//                                .executor(((commandSender, args) -> {
//                                            int amount = (int) args.getOrDefault("amount", 0);
//                                            System.out.println("выдал игроку " + amount);
//                                        })
//                                )
//                        )
//                        .subcommand(new Command("remove")
//                                .argument(new ArgumentInteger("amount", 1, 64))
//                                .executor(((commandSender, args) -> {
//                                            int amount = (int) args.getOrDefault("amount", 0);
//                                            System.out.println("забрал у игрока " + amount);
//                                        })
//                                )
//                        )
//                );
//        SetServer.setServer();
//
//        Command command = CommandFactory.commands.get("bairdrop");
//        System.out.println(command);
//        // Test command execution
//     //   command.process(sender, new String[]{"compass", "give", ""});
//
//        Assert.assertEquals(command.getTabCompleter(sender, new String[]{"compass", "give", ""}), new ArrayList<>(List.of("[<target>]", "@p", "@r", "@s")));
//        Assert.assertEquals(command.getTabCompleter(sender, new String[]{""}), new ArrayList<>(List.of("help", "compass")));
//        System.out.println("1 " + command.getTabCompleter(sender, new String[]{"compass", "give", ""}));
//        System.out.println("1 " + command.getTabCompleter(sender, new String[]{"compass", ""}));
     //   command.process(sender, new String[]{"compass", "remove", "64"});

        // You can assert the output here if needed.
    }

    @Test
    public void testTabCompletion() {
        // Create a mock CommandSender
//        CommandSender sender = Mockito.mock(CommandSender.class);
//
//        // Create a command structure
//        Command command = new Command("bair")
//                .subcommand(new Command("compass")
//                        .subcommand(new Command("give")
//                                .argument(new ArgumentInteger("amount", 1, 64))
//                        )
//                );

        // Test tab completion
//        assertEquals(command.getTabCompleter(sender, new String[]{"compass"}), List.of("give"));
//        assertEquals(command.getTabCompleter(sender, new String[]{"compass", "give", ""}), List.of("[<amount>]"));
//        assertEquals(command.getTabCompleter(sender, new String[]{"compass", "give", "q"}), List.of("[<amount>]"));
//        assertEquals(command.getTabCompleter(sender, new String[]{"compass", "give", "1"}), List.of("[<test 1>]"));
//        assertEquals(command.getTabCompleter(sender, new String[]{"compass", "give", "1", ""}), List.of("[<test 1>]"));
//        assertEquals(command.getTabCompleter(sender, new String[]{"compass", "give", "2343"}), List.of("[<test 1>]"));
    }
}
