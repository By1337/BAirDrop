package org.by1337.bairdrop.menu;

import org.bukkit.event.Listener;

public class ChangeWorld implements Listener {
//    @Getter
//    private final Inventory inventory;
//    private final SetValueCallBack<World> callBack;
//
//    public ChangeWorld(SetValueCallBack<World> callBack) {
//        this.callBack = callBack;
//        inventory = Bukkit.createInventory(null, 54, OLDMessage.messageBuilder("&7Смена мира"));
//        Bukkit.getServer().getPluginManager().registerEvents(this, BAirDrop.getInstance());
//        generate();
//    }
//
//    private void generate() {
//        int slot = 0;
//        for (World world : Bukkit.getWorlds()) {
//            ItemStack itemStack = switch (world.getEnvironment()) {
//                case NORMAL -> new ItemStack(Material.GRASS_BLOCK);
//                case NETHER -> new ItemStack(Material.NETHERRACK);
//                case THE_END -> new ItemStack(Material.END_STONE);
//                case CUSTOM -> new ItemStack(Material.COMMAND_BLOCK);
//            };
//
//            ItemMeta im = itemStack.getItemMeta();
//            im.setDisplayName(OLDMessage.messageBuilder("&7World №" + slot));
//            List<String> lore = new ArrayList<>(BAirDrop.getConfigMessage().getList("world-lore"));
//            lore.replaceAll(s -> s.replace("{type}", world.getEnvironment().name()).replace("{name}", world.getName()));
//            lore.replaceAll(this::locate);
//            lore.replaceAll(OLDMessage::messageBuilder);
//
//            im.getPersistentDataContainer().set(NamespacedKey.fromString("world"), PersistentDataType.STRING, world.getName());
//
//            im.setLore(lore);
//            itemStack.setItemMeta(im);
//            inventory.setItem(slot, itemStack);
//            slot++;
//        }
//    }
//
//    private String locate(String string) {
//        string = string.replace("NORMAL", "&aОбычный");
//        string = string.replace("NETHER", "&cНезер");
//        string = string.replace("THE_END", "&dЭнд");
//        string = string.replace("CUSTOM", "&6Кастомный");
//        return string;
//    }
//
//    @EventHandler
//    public void onClick(InventoryClickEvent e) {
//        if (e.getInventory().equals(inventory)) {
//            if (e.getCurrentItem() == null) return;
//            ItemMeta im = e.getCurrentItem().getItemMeta();
//            String world = im.getPersistentDataContainer().get(NamespacedKey.fromString("world"), PersistentDataType.STRING);
//            if (world == null) {
//                inventory.clear();
//                generate();
//                return;
//            }
//
//            World world1 = Bukkit.getWorld(world);
//            callBack.result(Optional.ofNullable(world1));
//        }
//
//    }
//
//    @EventHandler
//    public void onClose(InventoryCloseEvent e) {
//        if (e.getInventory().equals(inventory)) {
//            HandlerList.unregisterAll(this);
//        }
//    }
}
