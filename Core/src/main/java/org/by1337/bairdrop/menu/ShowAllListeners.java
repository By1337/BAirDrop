package org.by1337.bairdrop.menu;

import org.bukkit.event.Listener;

public class ShowAllListeners implements Listener {
//    private final Inventory inventory;
//    private final AirDrop airDrop;
//    private int page = 0;
//
//    public ShowAllListeners(AirDrop airDrop) {
//        this.airDrop = airDrop;
//        page = 0;
//        inventory = Bukkit.createInventory(null, 54, BAirDrop.getConfigMessage().getMessage("show-all-listeners-inv"));
//        generate();
//    }
//
//    private void generate() {
//        int slot = 0;
//        for (NamespacedKey nameKey : CustomListenerLoader.getCustomEventListeners().keySet()) {
//            if (CustomListenerLoader.getCustomEventListeners().get(nameKey).getEvent() == CustomEvent.NONE) continue;
//            if (page > 0) {
//                if (slot < (53 * page)) {
//                    slot++;
//                    continue;
//                }
//            }
//            if (slot - (53 * page) >= 53) continue;
//            inventory.setItem(slot - (53 * page), getItem(nameKey));
//            slot++;
//        }
//        if (slot >= 53) {
//            ItemStack itemStack = new ItemStack(Material.ARROW);
//            ItemMeta im = itemStack.getItemMeta();
//            im.setDisplayName(BAirDrop.getConfigMessage().getMessage("mat-change-arrow-name"));
//            List<String> lore = new ArrayList<>(BAirDrop.getConfigMessage().getList("mat-change-arrow-lore"));
//            lore.replaceAll(OLDMessage::messageBuilder);
//            im.setLore(lore);
//            im.getPersistentDataContainer().set(NamespacedKey.fromString("event"), PersistentDataType.STRING, "page swipe");//page
//            itemStack.setItemMeta(im);
//            inventory.setItem(53, itemStack);
//        }
//    }
//
//    public ItemStack getItem(NamespacedKey nameKey) {
//        Observer observer = CustomListenerLoader.getCustomEventListeners().get(nameKey);
//        if (observer == null) {
//            return getErrorItem();
//        }
//        ItemStack itemStack = new ItemStack(airDrop.hasObserver(observer) ? Material.DISPENSER : Material.OBSERVER);
//        ItemMeta im = itemStack.getItemMeta();
//
//        im.setDisplayName(OLDMessage.messageBuilder("&f" + nameKey));
//
//        im.getPersistentDataContainer().set(NamespacedKey.fromString("event"), PersistentDataType.STRING, nameKey.getKey());
//        List<String> lore = new ArrayList<>(BAirDrop.getConfigMessage().getList("event-lore"));
//        lore.replaceAll(s -> s
//                .replace("{description}", observer.getDescription())
//                .replace("{flag}", airDrop.hasObserver(observer) + "")
//                .replace("{event}", observer.getEvent().getKey().getKey())
//        );
//
//        int max = 0;
//        for (String cmd : observer.getCommands()) {
//            lore.add("&7" + cmd);
//            max++;
//            if (max == 3) {
//                if (observer.getCommands().length > 3)
//                    lore.add(String.format(BAirDrop.getConfigMessage().getMessage("event-lore-max"), (observer.getCommands().length - 3)));
//                break;
//            }
//        }
//        if (observer.getDenyCommands().length != 0)
//            lore.add("&adeny-commands:");
//        max = 0;
//        for (String cmd : observer.getDenyCommands()) {
//            lore.add("&7" + cmd);
//            max++;
//            if (max == 3) {
//                if (observer.getCommands().length > 3)
//                    lore.add(String.format(BAirDrop.getConfigMessage().getMessage("event-lore-max"), (observer.getCommands().length - 3)));
//                break;
//            }
//        }
//
//        List<String> formattedLore = new ArrayList<>();
//        for (String s : lore) {
//            if (s.length() > 50) {
//                StringBuilder sb = new StringBuilder();
//                String[] args = s.split(" ");
//                int len = 0;
//                for (String word : args) {
//                    len += word.length() + 1;
//                    if (len >= 50) {
//                        sb.append(word);
//                        formattedLore.add(sb.toString());
//                        sb = new StringBuilder();
//                        sb.append("&7");
//                        len = 0;
//                    } else {
//                        sb.append(word).append(" ");
//                    }
//                }
//                if (!sb.toString().equals("&7")) {
//                    formattedLore.add(sb.toString());
//                }
//            } else {
//                formattedLore.add(s);
//            }
//        }
//        lore = formattedLore;
//
//
//        List<String> nweLore = new ArrayList<>();
//        lore.replaceAll(s -> s.replace("\\n", "%split%"));
//        for (String str : lore) {
//            nweLore.addAll(Arrays.stream(str
//                    .replace("[", "&d[")
//                    .replace("]", "]&7")
//                    .replace("-offsets", "&d-offsets")
//                    .split("%split%")).toList());
//        }
//        nweLore.replaceAll(airDrop::replaceInternalPlaceholder);
//        nweLore.replaceAll(AirDropUtils::color);
//        nweLore.replaceAll(OLDMessage::messageBuilder);
//
//        im.setLore(nweLore);
//        itemStack.setItemMeta(im);
//        return itemStack;
//    }
//
//    public Inventory getInventory() {
//        return inventory;
//    }
//
//    @EventHandler
//    public void onClick(InventoryClickEvent e) {
//        if (e.getInventory().equals(inventory)) {
//            if (e.getCurrentItem() == null) {
//                e.setCancelled(true);
//                return;
//            }
//            ItemMeta im = e.getCurrentItem().getItemMeta();
//            String nameKey = im.getPersistentDataContainer().get(NamespacedKey.fromString("event"), PersistentDataType.STRING);
//            if (nameKey == null) {
//                e.setCancelled(true);
//                OLDMessage.sendMsg((Player) e.getWhoClicked(), BAirDrop.getConfigMessage().getMessage("item-error2"));
//                inventory.clear();
//                generate();
//                return;
//            }
//            if (nameKey.equals("page swipe")) {//page
//                if (e.getClick() == ClickType.LEFT) {
//                    page++;
//                    e.setCancelled(true);
//                    inventory.clear();
//                    generate();
//                    return;
//                }
//                if (e.getClick() == ClickType.RIGHT) {
//                    page = page == 0 ? 0 : page - 1;
//                    e.setCancelled(true);
//                    inventory.clear();
//                    generate();
//                    return;
//                }
//            }
//            Observer observer = CustomListenerLoader.getCustomEventListeners().getOrDefault(nameKey, null);
//
//            if (observer == null) {
//                e.setCancelled(true);
//                OLDMessage.sendMsg(e.getWhoClicked(), BAirDrop.getConfigMessage().getMessage("item-error2"));
//                inventory.clear();
//                generate();
//                return;
//            }
//
//            if (airDrop.hasObserver(observer)) {
//                airDrop.unregisterObserver(observer);
//                OLDMessage.sendMsg(e.getWhoClicked(), BAirDrop.getConfigMessage().getMessage("unsubscribed"));
//                if (airDrop.hasSavedObserver(observer.getKey().getKey())) {
//                    airDrop.removeSaveObserver(observer.getKey().getKey());
//                }
//            } else {
//                airDrop.registerObserver(observer);
//                OLDMessage.sendMsg(e.getWhoClicked(), BAirDrop.getConfigMessage().getMessage("signed"));
//                if (!airDrop.hasSavedObserver(observer.getKey().getKey())) {
//                    airDrop.saveObserver(observer.getKey().getKey());
//                }
//            }
//
//            airDrop.save();
//            e.setCancelled(true);
//            inventory.setItem(e.getSlot(), getItem(NamespacedKey.fromString(nameKey)));
//        }
//
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
