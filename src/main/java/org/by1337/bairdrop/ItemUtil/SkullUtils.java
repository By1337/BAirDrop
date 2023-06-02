package org.by1337.bairdrop.ItemUtil;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class SkullUtils {
    @NotNull
    public static ItemStack getSkull(@NotNull String skinUrl) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (skinUrl.isEmpty())
            return head;
        SkullMeta headMeta = (SkullMeta)head.getItemMeta();
        if (headMeta == null)
            return head;
        GameProfile profile = getGameProfile(skinUrl);
        try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException exception) {
            exception.printStackTrace();
        }
        head.setItemMeta((ItemMeta)headMeta);
        return head;

    }
    @NotNull
    public static GameProfile getGameProfile(@NotNull String url) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", url));
        return profile;
    }
}