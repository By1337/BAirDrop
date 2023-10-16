package command;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.mockito.Mockito;

import java.util.Collections;

/**
 * The command.SetServer class is responsible for initializing a mock Bukkit Server
 * for testing purposes. It ensures that only one instance of the mock server
 * is created.
 */
public class SetServer {

    private static SetServer instance = null;

    /**
     * Initializes the mock Bukkit Server if it has not been initialized before.
     * This method should be called to ensure that the server is set up for testing.
     */
    public static void setServer() {
        if (instance == null)
            instance = new SetServer();
    }

    private SetServer() {
        System.out.println("Created new server");

        // Create a mocked Bukkit Server instance for testing
        Server mockedServer = Mockito.mock(Server.class);

        PluginManager mockedPluginManager = Mockito.mock(PluginManager.class);

        Mockito.doAnswer(invocation -> {
            return false;
        }).when(mockedPluginManager).isPluginEnabled(Mockito.anyString());

        // Configure mock server behavior for various methods
        Mockito.when(mockedServer.getLogger()).thenReturn(java.util.logging.Logger.getLogger("Minecraft"));
        Mockito.when(mockedServer.getName()).thenReturn("Mock Server");
        Mockito.when(mockedServer.getVersion()).thenReturn("1.16.5" + " (MC: 1.16.5)");
        Mockito.when(mockedServer.getBukkitVersion()).thenReturn("1.16.5");
        //Mockito.when(mockedServer.getItemFactory()).thenReturn(CraftItemFactory.instance());
        Mockito.when(mockedServer.getOnlinePlayers()).thenReturn(Collections.emptySet());
        //Mockito.when(mockedServer.getOfflinePlayer()).thenReturn(null);
        Mockito.when(mockedServer.isPrimaryThread()).thenReturn(true);
        Mockito.when(mockedServer.getPluginManager()).thenReturn(mockedPluginManager);

        // Set the mock server as the active Bukkit server
        Bukkit.setServer(mockedServer);
    }
}
