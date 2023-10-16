package org.by1337.bairdrop.worldGuardHook;

import com.sk89q.worldedit.EditSession;
import org.bukkit.Location;
import org.by1337.bairdrop.AirDrop;

public interface SchematicsManager {
    EditSession pasteSchematics(String name, Location location);
}
