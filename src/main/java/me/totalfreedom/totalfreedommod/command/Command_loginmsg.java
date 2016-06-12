package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.admin.Admin;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Set your own login message", usage = "/<command> [-o <admin>] <set <message>>")
public class Command_loginmsg extends FreedomCommand
{
    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length < 2)
        {
            return false;
            
    
        Player init = null;
        Admin target = getAdmin(playerSender);
        Player targetPlayer = playerSender;
        String targetIp = Ips.getIp(targetPlayer);
        
                // -o switch
        if (args[0].equals("-o"))
        {
            checkRank(Rank.SENIOR_ADMIN);
            init = playerSender;
            targetPlayer = getPlayer(args[1]);
            if (targetPlayer == null)
            {
                msg(FreedomCommand.PLAYER_NOT_FOUND);
                return true;
            }
            target = getAdmin(targetPlayer);
            if (target == null)
            {
                msg("That player is not admin", ChatColor.RED);
                return true;
            }

            // Shift 2
            args = Arrays.copyOfRange(args, 2, args.length);
            if (args.length < 1)
            {
                return false;
            }
        }

        String message = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        
        if (args[0].equalsIgnoreCase("delete"))
        {
            msg("If you want to delete, please do /loginmsg set null");
            return true;
        }
        
        if (args[0].equalsIgnoreCase("set"))
        {
            Admin admin = plugin.al.getAdmin(sender);
            
            if (message.equalsIgnoreCase(admin.getLoginMessage()))
            {
                msg(ChatColor.RED + "Your new login message can't be the same as your current login message");
                return true;
            }
            
            if (args[1].equalsIgnoreCase("null"))
            {
                admin.setLoginMessage(null);
                msg("Login message removed");
                return true;
            }
            
            admin.setLoginMessage(message);
            msg(FUtil.colorize("Your new login message is now " + message), ChatColor.GREEN);
            return true;
        }
        return false;
    }
}
