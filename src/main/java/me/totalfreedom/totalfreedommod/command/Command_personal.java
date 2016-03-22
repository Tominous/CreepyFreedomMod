package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Supers +'s own personal commands", usage = "/<command>")
public class Command_personal extends FreedomCommand
{
    
    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        String name = sender.getName();
        
        switch(name)
        {
            case "IllX":
                FUtil.adminAction(name, "Giving everyone a useless stick", true);
                for (Player player : server.getOnlinePlayers())
                {
                    ItemStack stick = new ItemStack(Material.STICK, 1);
                    stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 100);
                    ItemMeta meta = stick.getItemMeta();
                    meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Granny's stick");
                    stick.setItemMeta(meta);
                    player.getInventory().addItem(stick);
                }
            break;
            
            default:
                msg(ChatColor.RED + "Unlucky, you don't have anything defined under your name! Please contact a developer if this is a bug.");
                msg(ChatColor.RED + "or check in admin section on fourm!");
                break;
        }
        return true;
    }
}
