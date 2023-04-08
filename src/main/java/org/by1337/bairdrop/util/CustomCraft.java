package org.by1337.bairdrop.util;

import eu.decentsoftware.holograms.api.utils.scheduler.S;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import org.by1337.bairdrop.AirDrop;
import org.by1337.bairdrop.ConfigManager.Config;
import org.by1337.bairdrop.util.Message;
import org.by1337.bairdrop.BAirDrop;
public class CustomCraft {
    private final String id;
    private final NamespacedKey namespacedKey;
    private final List<String> call;
    private final ShapedRecipe shapedRecipe;

    public CustomCraft(String id, String item, List<String> call, HashMap<Character, Material> ingredients, String top, String middle, String bottom) throws Exception {
        this.id = id;
        this.call = call;
        ItemStack itemStack = BAirDrop.summoner.getItems().get(item).getItem();
        ItemMeta im = itemStack.getItemMeta();
        im.getPersistentDataContainer().set(NamespacedKey.fromString("custom_crafts_bairdrop"), PersistentDataType.STRING, id);
        itemStack.setItemMeta(im);
        namespacedKey = new NamespacedKey(BAirDrop.instance, id);
        shapedRecipe = new ShapedRecipe(namespacedKey, itemStack);
        shapedRecipe.shape(top, middle, bottom);
        for (char key : ingredients.keySet())
            shapedRecipe.setIngredient(key, ingredients.get(key));
        BAirDrop.instance.getServer().addRecipe(shapedRecipe);
    }

    public String getId() {
        return id;
    }

    public NamespacedKey getNamespacedKey() {
        return namespacedKey;
    }

    public ShapedRecipe getShapedRecipe() {
        return shapedRecipe;
    }

    public List<String> getCall() {
        return call;
    }

    @Nullable
    public static CustomCraft getCraftByResult(ItemStack itemStack) {
        String id = itemStack.getItemMeta().getPersistentDataContainer().get(NamespacedKey.fromString("custom_crafts_bairdrop"), PersistentDataType.STRING);
        if(id == null)
            return null;
        for (CustomCraft cc : BAirDrop.crafts.values()) {
            if (cc.id.equals(id))
                return cc;
        }
        return null;
    }

    public void callListeners(String listener, @NotNull Player pl) {
        try {
            if (!BAirDrop.internalListeners.containsKey(listener))
                return;
            BAirDrop.internalListeners.get(listener).execute(pl, null, false, Events.CRAFT_ITEM);
        } catch (StackOverflowError e) {
            Message.error(Config.getMessage("too-many-call"));
        }
    }

    public static void unloadCrafts() {
        for (CustomCraft cc : BAirDrop.crafts.values()) {
            BAirDrop.instance.getServer().removeRecipe(cc.getNamespacedKey());
        }
    }

}
