package de.mathisburger.timerplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;


public class PlayerJoinQuitEvent implements Listener {
    public static Plugin plugin;

    public PlayerJoinQuitEvent(Plugin plugin){
        PlayerJoinQuitEvent.plugin = plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = (Player) e.getPlayer();
        e.setJoinMessage(ChatColor.GREEN + "Der Spieler " + p.getDisplayName() + " hat den Server betreten");
        Var.player.add(p);
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = (Player) e.getPlayer();
        e.setQuitMessage(ChatColor.RED + "Der Spieler " + p.getDisplayName() + " hat den Server verlassen");
        Var.player.remove(p);
    }
}
