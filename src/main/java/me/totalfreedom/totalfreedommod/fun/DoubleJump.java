package me.totalfreedom.totalfreedommod.fun;

import me.totalfreedom.totalfreedommod.FreedomService;
import me.totalfreedom.totalfreedommod.TotalFreedomMod;
import me.totalfreedom.totalfreedommod.player.FPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJump extends FreedomService
{
    public DoubleJump(TotalFreedomMod plugin)
    {
        super(plugin);
    }
    
    @Override
    protected void onStart()
    {
    }
    
    @Override
    protected void onStop()
    {
    }
    
    @EventHandler
    public void onPlayerDJump(PlayerToggleFlightEvent event)
    {
        final Player player = event.getPlayer();
        final FPlayer fPlayer = plugin.pl.getPlayer(player);
        
        if (fPlayer.djumpEnabled()) {
            player.setFlying(false);
            Vector jump = player.getLocation().getDirection().multiply(2).setY(1.1);
            player.setVelocity(player.getVelocity().add(jump));
            event.setCancelled(true);
        }
    }
}
