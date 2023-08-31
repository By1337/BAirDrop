var Material = Packages.org.bukkit.Material;
var Enchantment = Packages.org.bukkit.enchantments.Enchantment;
var ItemStack = Packages.org.bukkit.inventory.ItemStack;

var itemStack = new ItemStack(Material.DIAMOND); //Создаём ItemStack
var itemMeta = itemStack.getItemMeta(); //Получаем ItemMeta из него
itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true); //добавляем зачарование в ItemMeta
itemStack.setItemMeta(itemMeta); //Устанавливаем предмету ItemMeta
player.getInventory().addItem(itemStack); //выдаём ему предмет

/*
* Использование
  jsTest:
    description: '&cJs test'
    event: 'CLICK_CLOSE'
    commands:
      - '[RUN_JS=diamond.js] param(player=player)-scheduler'

* player=player значит <название переменой>=<что она хранит> так как этот ивент вызвал игрок то этот слушатель может передать игрока (player) скрипту
*/