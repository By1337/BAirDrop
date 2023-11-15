package org.by1337.bairdrop.menu;

import org.bukkit.event.Listener;


public class SelectAirMenu implements Listener {
//    private final Inventory inventory;
//
//    private final int taskId;
//
//    public SelectAirMenu() {
//       inventory = Bukkit.createInventory(null, 54, OLDMessage.messageBuilder(BAirDrop.getConfigMessage().getMessage("menu-air-select")));
//        taskId = new BukkitRunnable() {
//            @Override
//            public void run() {
//                generate();
//            }
//        }.runTaskTimer(BAirDrop.getInstance(), 0, 20).getTaskId();
//
//
//    }
//    private void generate(){
//        inventory.clear();
//        int x = 0;
//        for(AirDrop airDrop : BAirDrop.airDrops.values()){
//            ItemStack itemStack = new ItemStack(airDrop.getMaterialLocked());
//            ItemMeta im = itemStack.getItemMeta();
//            im.getPersistentDataContainer().set(NamespacedKey.fromString("air_id"), PersistentDataType.STRING, airDrop.getId());
//            im.setDisplayName(OLDMessage.messageBuilder(airDrop.getDisplayName()));
//            List<String> lore = new ArrayList<>(BAirDrop.getConfigMessage().getList("select-air-menu-lore"));
//            lore.addAll(airDrop.getDec());
//            lore.replaceAll(airDrop::replaceInternalPlaceholder);
//            lore.replaceAll(OLDMessage::messageBuilder);
//            im.setLore(lore);
//            itemStack.setItemMeta(im);
//            inventory.setItem(x, itemStack);
//            x++;
//        }
//    }
//    @EventHandler
//    public void onClick(InventoryClickEvent e) {
//        if(e.getClickedInventory() != null && e.getClickedInventory().equals(inventory)){
//            if(e.getCurrentItem() == null){
//                e.setCancelled(true);
//                return;
//            }
//            ItemMeta im = e.getCurrentItem().getItemMeta();
//            if(im.getPersistentDataContainer().has(NamespacedKey.fromString("air_id"), PersistentDataType.STRING)){
//                String nameKey = im.getPersistentDataContainer().get(NamespacedKey.fromString("air_id"), PersistentDataType.STRING);
//                if(BAirDrop.airDrops.containsKey(nameKey)){
//
//                    if (BAirDrop.airDrops.get(nameKey).getEditAirMenu() != null) {
//                        BAirDrop.airDrops.get(nameKey).getEditAirMenu().unReg();
//                    }
//
//                    EditAirMenu editAirMenu = new EditAirMenu(BAirDrop.airDrops.get(nameKey));
//                    BAirDrop.airDrops.get(nameKey).setEditAirMenu(editAirMenu);
//                    e.getWhoClicked().openInventory(editAirMenu.getInventory());
//                    return;
//                }
//            }
//            e.setCancelled(true);
//        }
//    }
//
//    @EventHandler
//    public void onClose(InventoryCloseEvent e) {
//        if (e.getInventory().equals(inventory)) {
//           unReg();
//        }
//
//    }
//
//    public void unReg() {
//        HandlerList.unregisterAll(this);
//        Bukkit.getServer().getScheduler().cancelTask(taskId);
//    }
//
//    public Inventory getInventory() {
//        return inventory;
//    }
}
