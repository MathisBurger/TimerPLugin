package de.mathisburger.timerplugin;
import com.connorlinfoot.actionbarapi.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class DeathEvent implements Listener {
    public static Plugin plugin;

    public DeathEvent(Plugin plugin){
        DeathEvent.plugin = plugin;
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Var.TimerState = "pausiert";
        if(Var.TimerMode.equalsIgnoreCase("Countdown")) {
            Bukkit.getScheduler().cancelTask(TimerCommand.taskid2);
        } else{
            Bukkit.getScheduler().cancelTask(TimerCommand.taskid3);
        }
        ActionBarAPI.sendActionBarToAllPlayers(ChatColor.GOLD + "Timer ist pausiert");
        Var.player.forEach(name -> name.setGameMode(GameMode.SPECTATOR));
        e.setDeathMessage(ChatColor.RED + "Der Spieler " + e.getEntity().getDisplayName() + " ist gestorben");
    }
}
