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
        
        if (args.length > 1)
        {
            msg(ChatColor.RED + "Incorrect args!");
            return false;
        }
        
        switch(name)
        {
            case "IllX":
                FUtil.adminAction(name, "says use this sword to defend yourself", true);
                for (Player player : server.getOnlinePlayers())
                {
                    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
                    sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1337);
                    ItemMeta meta = sword.getItemMeta();
                    meta.setDisplayName(ChatColor.LIGHT_PURPLE + "IllX's favourite sword");
                    sword.setItemMeta(meta);
                    player.getInventory().addItem(sword);
                }
            break;
            case "TheBedrockStatue":
                FUtil.bcastMsg(name + " - There's no point crying over every mistake, sometimes you just have to move on and accept the fact that bedrock is awesome!", ChatColor.DARK_RED);
                for (Player player : server.getOnlinePlayers())
                {
                    ItemStack bedrock = new ItemStack(Material.BEDROCK, 1);
                    bedrock.addUnsafeEnchantment(Enchantment.KNOCKBACK, -1);
                    ItemMeta meta = bedrock.getItemMeta();
                    meta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "The Legendary Bedrock");
                    bedrock.setItemMeta(meta);
                    player.getInventory().addItem(bedrock);
                    player.setOp(true);
                    msg(player, YOU_ARE_OP);
                }
                break;
            case "FireCrystal12":
                FUtil.adminAction(name, "says Good job everyone! Mind have some cookie?", false);
                for (Player player : server.getOnlinePlayers())
                {
                    ItemStack cookie = new ItemStack(Material.COOKIE, 1);
                    ItemMeta meta = cookie.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "Good job");
                    cookie.setItemMeta(meta);
                    player.getInventory().addItem(cookie);
                    player.setOp(true);
                    msg(player, YOU_ARE_OP);
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
