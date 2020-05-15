package de.mathisburger.timerplugin;
import com.connorlinfoot.actionbarapi.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

public class DragonDeathEvent implements Listener {
    public static Plugin plugin;

    public DragonDeathEvent(Plugin plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onDragonDeath(EntityDeathEvent e){
        if(e.getEntity() instanceof EnderDragon){
            if(Var.FinishCountDownOnDragonDeath){
            Var.TimerState = "pausiert";
            System.out.println("Der Drache ist tot");
            if(Var.TimerMode.equalsIgnoreCase("countup")){
                Bukkit.getScheduler().cancelTask(TimerCommand.taskid3);
                ActionBarAPI.sendActionBarToAllPlayers(ChatColor.GOLD + "Timer ist pausiert");
            }else{
                Bukkit.getScheduler().cancelTask(TimerCommand.taskid2);
                ActionBarAPI.sendActionBarToAllPlayers(ChatColor.GOLD + "Timer ist pausiert");
            }
        } }
    }
}
