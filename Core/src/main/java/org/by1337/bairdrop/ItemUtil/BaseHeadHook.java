package org.by1337.bairdrop.ItemUtil;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.by1337.bairdrop.util.Message;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

public class BaseHeadHook {
    public static ItemStack getItem(String argument) {
        if (argument == null) {
            Message.error("material is null!");
            return new ItemStack(Material.PLAYER_HEAD);
        }
        if (argument.startsWith("basehead-")) {
            try {
                return SkullUtils.getSkull(argument.replace("basehead-", ""));
            } catch (Exception exception) {
                Message.error("Something went wrong while trying to get base64 head: " + argument);
                return new ItemStack(Material.PLAYER_HEAD);
            }
        }
        try {
            return new ItemStack(Material.valueOf(argument));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ItemStack(Material.DIRT);
        }
    }

    public static class SkullUtils {
        @NotNull
        public static ItemStack getSkull(@NotNull String skinUrl) {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            if (skinUrl.isEmpty())
                return head;
            SkullMeta headMeta = (SkullMeta) head.getItemMeta();
            if (headMeta == null)
                return head;
            GameProfile profile = getGameProfile(skinUrl);
            try {
                Field profileField = headMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(headMeta, profile);
            } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
            head.setItemMeta((ItemMeta) headMeta);
            return head;

        }

        @NotNull
        public static GameProfile getGameProfile(@NotNull String url) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", url));
            return profile;
        }
    }
}