package org.by1337.bairdrop.util;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.extent.inventory.BlockBag;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;
public class SchematicsManager {
    public static void PasteSchematics(String name, AirDrop airDrop) {
        //   new BukkitRunnable() {
        //      @Override
        //   public void run() {
        try {
            if (airDrop.getEditSession() != null) {
                Message.error(Config.getMessage("schem-limit"));
                return;
            }

            Vector offsets = new Vector(
                    Config.getSchemConf().getInt(String.format("schematics.%s.offsets-x", name)),
                    Config.getSchemConf().getInt(String.format("schematics.%s.offsets-y", name)),
                    Config.getSchemConf().getInt(String.format("schematics.%s.offsets-z", name))
            );
            boolean ignoreAirBlocks = Config.getSchemConf().getBoolean(String.format("schematics.%s.ignore-air-blocks", name));
            String file = Config.getSchemConf().getString(String.format("schematics.%s.file", name));
            Message.debug("paste " + file, LogLevel.LOW);

            if (!Config.Schematics.containsKey(file)) throw new IllegalArgumentException();
            File schem = Config.Schematics.get(file);

            ClipboardFormat format = ClipboardFormats.findByFile(schem);

            ClipboardReader reader = format.getReader(new FileInputStream(schem));

            Clipboard clipboard = reader.read();

            Location loc = airDrop.getAirLoc();
            if (loc == null)
                loc = airDrop.getFutureLocation();
            if (loc == null)
                throw new NullPointerException();
            //   Block block = loc.getBlock();
            //   loc.getBlock().setType(Material.AIR);
            com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(loc.getWorld());

            EditSession editSession = WorldEdit.getInstance().newEditSession(adaptedWorld);

            Operation operation = new ClipboardHolder(clipboard).createPaste(editSession)
                    .to(BlockVector3.at(loc.getX() + offsets.getBlockX(), loc.getY() + offsets.getBlockY(), loc.getZ() + +offsets.getBlockZ())).ignoreAirBlocks(ignoreAirBlocks).build();

            Operations.complete(operation);
            editSession.flushSession();

            airDrop.setEditSession(editSession);
            editSession.getBlockBag();
            //  loc.getBlock().setType(block.getType());
            //   loc.getBlock().setBlockData(block.getBlockData());
        } catch (IOException | WorldEditException | IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
        }
        //   }
        //  }.runTaskLater(BAirDrop.instance, 0);
    }

}
