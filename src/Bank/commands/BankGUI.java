package Bank.commands;

import Bank.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class BankGUI implements CommandExecutor, Listener {

    private Main plugin;

    public BankGUI(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("bank").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (p.hasPermission("Bank.use")) {
            if (cmd.getName().equalsIgnoreCase("bank")){
                Inventory bankgui = Bukkit.getServer().createInventory(p, 27, (ChatColor.translateAlternateColorCodes('&', "&6Bank")));

                bankgui.setItem(11, getItem(Material.CHEST, (ChatColor.translateAlternateColorCodes('&', "&6Deposit coins"))));
                bankgui.setItem(13, getItem(Material.GOLD_INGOT, (ChatColor.translateAlternateColorCodes('&', "&6Coins in your bank"))));
                bankgui.setItem(15, getItem(Material.PAPER, (ChatColor.translateAlternateColorCodes('&', "&6Withdraw coins"))));

                p.openInventory(bankgui);
            }

            return true;
        }else{
            p.sendMessage("You don't have permission");
        }

        return false;
    }

    @EventHandler
    public void OnClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();

        if (current == null) return;

        if (p.getOpenInventory().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&6Bank"))) {

            e.setCancelled(true);

            switch (e.getSlot()) {
                case 11:
                    p.setGameMode(GameMode.SURVIVAL);
                    p.closeInventory();
                    break;

                case 13:
                    p.setGameMode(GameMode.CREATIVE);
                    p.closeInventory();
                    break;

                case 15:
                    p.setGameMode(GameMode.SPECTATOR);
                    p.closeInventory();
                    break;

                default: break;
            }

        }
    }

    public ItemStack getItem(Material material, String customName) {
        ItemStack it = new ItemStack(material, 1);
        ItemMeta itM = it.getItemMeta();
        if(customName != null) itM.setDisplayName(customName);
        it.setItemMeta(itM);
        return it;
    }


}
