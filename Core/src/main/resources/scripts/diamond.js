var Material = Packages.org.bukkit.Material;
var Enchantment = Packages.org.bukkit.enchantments.Enchantment;
var ItemStack = Packages.org.bukkit.inventory.ItemStack;

var itemStack = new ItemStack(Material.DIAMOND); //Create an ItemStack
var itemMeta = itemStack.getItemMeta(); //Get ItemMeta from it
itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true); //add enchantment to ItemMeta
itemStack.setItemMeta(itemMeta); //Setting the item's ItemMeta
player.getInventory().addItem(itemStack); //give him an item

/*
* Usage
  jsTest:
    description: '&cJs test'
    event: 'CLICK_CLOSE'
    commands:
      - '[RUN_JS=diamond.js] param(player=player)-scheduler'

* player=player means <variable name>=<what it stores> since this event was called by the player, this listener can pass the player (player) to the script
*/