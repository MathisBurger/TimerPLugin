package de.mathisburger.timerplugin;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {


    @Override
    public void onEnable() {
        getCommand("timer").setExecutor(new TimerCommand());
        getCommand("timer_config").setExecutor(new TimerConfigurator());

        getServer().getPluginManager().registerEvents(new PlayerJoinQuitEvent(this), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
        getServer().getPluginManager().registerEvents(new DragonDeathEvent(this), this);

    }

    @Override
    public void onDisable() {

    }
}
