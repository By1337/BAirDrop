package org.by1337.bairdrop.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.by1337.lib.command.CommandSyntaxError;
import org.by1337.bairdrop.airdrop.registry.CommandFactory;
import org.by1337.bairdrop.effect.test.EffectCircle;
import org.by1337.bairdrop.menu.property.PropertyEditor;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;

public class CommandHook implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player pl){
            // SpawnArmorStand.spawn(new BLocation(pl.getLocation()));
//                CAirDrop airDrop = (CAirDrop) airDrops.get("default");
            EffectCircle effectCircle = new EffectCircle(999999, 1);
            PropertyEditor propertyEditor = new PropertyEditor(effectCircle);
            propertyEditor.generate();
            pl.openInventory(propertyEditor.getInventory());
            System.out.println(effectCircle);

//                PropertyEditor propertyEditor = new PropertyEditor(airDrop);
//                propertyEditor.generate();
//                pl.openInventory(propertyEditor.getInventory());
//                ItemStackSerialize itemStackSerialize = ItemStackSerializeFactory.create();
//                ItemStack itemStack = pl.getInventory().getItemInMainHand();
//                String s = itemStackSerialize.serialize(itemStack);
//                System.out.println(s);
//                pl.getLocation().getWorld().dropItem(pl.getLocation(), itemStackSerialize.deserialize(s));
        }
        try {
            CommandFactory.createMain().process(sender, args);
            return true;
        } catch (CommandSyntaxError e) {
            Message.sendMsg(sender, e.getLocalizedMessage());
        }
        return false;
    }
}
