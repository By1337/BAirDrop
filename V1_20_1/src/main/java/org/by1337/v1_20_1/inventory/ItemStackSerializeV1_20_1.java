package org.by1337.v1_20_1.inventory;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.by1337.api.inventory.ItemStackSerialize;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ItemStackSerializeV1_20_1 implements ItemStackSerialize {
    @Override
    public String serialize(ItemStack itemStack) throws IllegalArgumentException {
        try {
            net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(itemStack);
            CompoundTag tag = new CompoundTag();
            item.save(tag);
            String serialize = tag.toString();
            return new String(Base64.getEncoder().encode(serialize.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to serialize ItemStack", e);
        }
    }

    @Override
    public ItemStack deserialize(String data) throws IllegalArgumentException {
        try {
            return CraftItemStack.asBukkitCopy(
                    net.minecraft.world.item.ItemStack.of(TagParser.parseTag(new String(Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8)))
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to deserialize ItemStack", e);
        }
    }

}