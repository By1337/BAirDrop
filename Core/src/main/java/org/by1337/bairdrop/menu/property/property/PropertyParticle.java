package org.by1337.bairdrop.menu.property.property;

import org.bukkit.Particle;

public class PropertyParticle  extends Property<Particle>{
    /**
     * Creates a new property with the specified initial value, name
     *
     * @param value The initial value of the property.
     * @param name  The unique name of the property.
     */
    public PropertyParticle(Particle value, String name) {
        super(value, name);
    }

    @Override
    public Particle parse(String str) {
        return Particle.valueOf(str);
    }
//    public PropertyParticle(Particle value, String name, Resource lore, Resource material, Resource displayName, StorageProperties owner) {
//        super(value, name, lore, material, displayName, owner);
//    }

    public PropertyParticle(Particle value, String name, Particle defValue) {
        super(value, name, defValue);
    }


/*    @Override
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
    }*/

    @Override
    public PropertyType getType() {
        return PropertyType.PARTICLE;
    }
}
