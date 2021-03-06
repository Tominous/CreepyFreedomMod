package me.totalfreedom.totalfreedommod.commands;

import me.totalfreedom.totalfreedommod.rank.PlayerRank;
import me.totalfreedom.totalfreedommod.banning.Ban;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = PlayerRank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Temporarily bans a player for six hours.", usage = "/<command> <partialname>", aliases = "rekt")
public class Command_buster extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length != 1)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            playerMsg(FreedomCommand.PLAYER_NOT_FOUND, ChatColor.RED);
            return true;
        }

        // strike with lightning effect:
        final Location targetPos = player.getLocation();
        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                final Location strike_pos = new Location(targetPos.getWorld(), targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                targetPos.getWorld().strikeLightning(strike_pos);
            }
        }

        FUtil.adminAction(sender.getName(), "Tempbanning: " + player.getName() + " for 6 hours.", true);
        plugin.bm.addBan(Ban.forPlayer(player, sender, FUtil.parseDateOffset("6h"), ChatColor.RED + "You have been temporarily banned for 6 hours."));

        player.kickPlayer(ChatColor.RED + "You have been temporarily banned for six hours. Please read totalfreedom.me for more info.");

        return true;
    }
}
