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

import org.by1337.bairdrop.BAirDrop;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.api.chat.util.LogLevel;
import org.by1337.bairdrop.util.OLDMessage;


public class CSchematicsManager implements SchematicsManager {
    private static Resource schematicsLimit = new Resource("schematics.error.limit");


    public EditSession pasteSchematics(String name, Location location) {
        try {
//            if (airDrop.getEditSession() != null) {
//                OLDMessage.error(schematicsLimit.getString());
//                return;
//            }
            Vector offsets = new Vector(
                    BAirDrop.getCfg().getSchemConf().getInt(String.format("schematics.%s.offsets-x", name)),
                    BAirDrop.getCfg().getSchemConf().getInt(String.format("schematics.%s.offsets-y", name)),
                    BAirDrop.getCfg().getSchemConf().getInt(String.format("schematics.%s.offsets-z", name))
            );
            boolean ignoreAirBlocks = BAirDrop.getCfg().getSchemConf().getBoolean(String.format("schematics.%s.ignore-air-blocks", name));
            String file = BAirDrop.getCfg().getSchemConf().getString(String.format("schematics.%s.file", name));

            if (file == null || !BAirDrop.getCfg().getSchematics().containsKey(file)) {
                throw new IllegalArgumentException("unknown schematic: " + name);
            }

            OLDMessage.debug("paste " + file, LogLevel.LEVEL_0);

            File schem = BAirDrop.getCfg().getSchematics().get(file);

            ClipboardFormat format = ClipboardFormats.findByFile(schem);

            ClipboardReader reader = format.getReader(new FileInputStream(schem));


            Clipboard clipboard = reader.read();


            com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(location.getWorld());

            EditSession editSession = WorldEdit.getInstance().newEditSession(adaptedWorld);

            Operation operation = new ClipboardHolder(clipboard).createPaste(editSession)
                    .to(BlockVector3.at(location.getX() + offsets.getBlockX(), location.getY() + offsets.getBlockY(), location.getZ() + offsets.getBlockZ())).ignoreAirBlocks(ignoreAirBlocks).build();

            Operations.complete(operation);
            editSession.close();

//            airDrop.setEditSession(editSession);
            editSession.getBlockBag();
            return editSession;
        } catch (IOException | WorldEditException | IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

}
