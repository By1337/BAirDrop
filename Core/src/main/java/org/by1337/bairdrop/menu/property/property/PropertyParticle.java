package org.by1337.bairdrop.menu.property.property;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.by1337.bairdrop.ItemUtil.BaseHeadHook;
import org.by1337.bairdrop.lang.Resource;
import org.by1337.bairdrop.menu.property.EditableProperties;
import org.by1337.bairdrop.menu.property.PropertyEditor;
import org.by1337.bairdrop.menu.property.enums.EnumChooser;
import org.by1337.bairdrop.menu.property.enums.EnumToItemStackConverter;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PropertyParticle  extends Property<Particle>{
    public PropertyParticle(Particle value, String name, Resource lore, Resource material, Resource displayName, EditableProperties owner) {
        super(value, name, lore, material, displayName, owner);
    }

    @Override
    public void editValue(Player player) {
        EnumChooser<Particle> enumChooser = new EnumChooser<>(
                Particle.class,
                new EnumToItemStackConverter<Particle>() {
                    @Override
                    public @NotNull ItemStack convertToMaterial(Particle enumValue) {
                        List<String> list = new Resource("particle-to-material.list").getList();
                        for (String s : list){
                            if (s.split("->")[0].equals(enumValue.name())){
                                return addName(new ItemStack(Material.valueOf(s.split("->")[1])), enumValue.name());
                            }
                        }
                        return addName(new ItemStack(Material.DIRT), enumValue.name());
                    }

                    public ItemStack addName(ItemStack itemStack, String name) {
                        ItemMeta im = itemStack.getItemMeta();
                        im.setDisplayName(Message.messageBuilder("&7" + name));
                        itemStack.setItemMeta(im);
                        return itemStack;
                    }
                },
                result -> {
                    result.ifPresent(this::setValue);
                    PropertyEditor propertyEditor = new PropertyEditor(getOwner());
                    propertyEditor.generate();
                    player.openInventory(propertyEditor.getInventory());
                }
        );
        enumChooser.registerFilter(value -> value == Particle.REDSTONE || value.getDataType() != Void.class);
        enumChooser.generate();
        player.openInventory(enumChooser.getInventory());
    }

    @Override
    public PropertyType getType() {
        return PropertyType.PARTICLE;
    }
}
