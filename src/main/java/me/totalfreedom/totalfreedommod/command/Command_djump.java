package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.OP, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "", usage = "/<command>", aliases = "doublejump")
public class Command_djump extends FreedomCommand
{
    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        FPlayer fPlayer = plugin.pl.getPlayer(playerSender);
        boolean active = !fPlayer.djumpEnabled();
        msg("Double Jump toggled: " + (active ? "true" : "false"));
        fPlayer.setDoubleJump(active);
        return true;
    }
}
