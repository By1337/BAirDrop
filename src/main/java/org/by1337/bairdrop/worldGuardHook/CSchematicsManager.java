package org.by1337.bairdrop.worldGuardHook;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.util.LogLevel;
import org.by1337.bairdrop.util.Message;


public class CSchematicsManager implements SchematicsManager{

    /**
     * Sets the schematic
     * @param name The name of the schematic in the config
     * @param airDrop The AirDrop that spawns this schematic
     */
    public void PasteSchematics(String name, AirDrop airDrop) {
        try {
            if (airDrop.getEditSession() != null) {
                Message.error(BAirDrop.getConfigMessage().getMessage("schem-limit"));
                return;
            }
            Vector offsets = new Vector(
                    BAirDrop.getiConfig().getSchemConf().getInt(String.format("schematics.%s.offsets-x", name)),
                    BAirDrop.getiConfig().getSchemConf().getInt(String.format("schematics.%s.offsets-y", name)),
                    BAirDrop.getiConfig().getSchemConf().getInt(String.format("schematics.%s.offsets-z", name))
            );
            boolean ignoreAirBlocks = BAirDrop.getiConfig().getSchemConf().getBoolean(String.format("schematics.%s.ignore-air-blocks", name));
            String file = BAirDrop.getiConfig().getSchemConf().getString(String.format("schematics.%s.file", name));

            if (file == null || !BAirDrop.getiConfig().getSchematics().containsKey(file)) {
                throw new IllegalArgumentException("unknown schematic: " + name);
            }

            Message.debug("paste " + file, LogLevel.LOW);

            File schem = BAirDrop.getiConfig().getSchematics().get(file);

            ClipboardFormat format = ClipboardFormats.findByFile(schem);

            ClipboardReader reader = format.getReader(new FileInputStream(schem));


            Clipboard clipboard = reader.read();


            Location loc = airDrop.getAirDropLocation();
            if (loc == null)
                loc = airDrop.getFutureLocation();
            if (loc == null)
                throw new NullPointerException();
            com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(loc.getWorld());

            EditSession editSession = WorldEdit.getInstance().newEditSession(adaptedWorld);

            Operation operation = new ClipboardHolder(clipboard).createPaste(editSession)
                    .to(BlockVector3.at(loc.getX() + offsets.getBlockX(), loc.getY() + offsets.getBlockY(), loc.getZ() + +offsets.getBlockZ())).ignoreAirBlocks(ignoreAirBlocks).build();

            Operations.complete(operation);
            editSession.close();

            airDrop.setEditSession(editSession);
            editSession.getBlockBag();
        } catch (IOException | WorldEditException | IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}
