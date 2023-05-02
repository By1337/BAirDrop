package org.by1337.bairdrop.Hologram;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.Holograms.CMIHologram;
import net.Zrips.CMILib.Container.CMILocation;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CMIHolo implements IHologram{
    @Override
    public void createOrUpdateHologram(@NotNull List<String> lines, @NotNull Location location, @NotNull String name) {
        if(!CMI.getInstance().getHologramManager().getHolograms().containsKey(name)){
            CMIHologram holo = new CMIHologram(name, new CMILocation(location));
            holo.setLines(lines);
            CMI.getInstance().getHologramManager().addHologram(holo);
            holo.update();
        }else {
            CMIHologram holo = CMI.getInstance().getHologramManager().getHolograms().get(name);
            int size = holo.getLines().size();
            holo.setLines(lines);
            holo.setLoc(location);
            holo.update();
            if(size != lines.size())//проблемы с api у cmi, или я додик
                holo.refresh();
        }
    }

    @Override
    public void remove(@NotNull String name) {
        if(CMI.getInstance().getHologramManager().getHolograms().containsKey(name)){
            CMI.getInstance().getHologramManager().getHolograms().get(name).remove();
        }
    }
}
