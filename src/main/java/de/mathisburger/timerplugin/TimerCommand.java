package de.mathisburger.timerplugin;
import com.connorlinfoot.actionbarapi.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimerCommand implements CommandExecutor {

    private static int taskid1;
    public static int taskid2;
    public static int taskid3;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("timer")){
            if(sender instanceof Player){
                if(args[0].equalsIgnoreCase("state")){
                    if(Var.TimerState.equalsIgnoreCase("pausiert")){
                        ActionBarAPI.sendActionBarToAllPlayers(ChatColor.GOLD + "Timer ist pausiert");
                    }else if(Var.TimerState.equalsIgnoreCase("not_config")){
                        sender.sendMessage(ChatColor.RED + "Der Timer ist noch nicht konfiguriert");
                        sender.sendMessage(ChatColor.GREEN + "Nutze /timer config um den Timer einzustellen");
                    } else if(Var.TimerState.equalsIgnoreCase("resumed")){
                        sender.sendMessage(ChatColor.GREEN + "Der Timer läuft gerade");
                    }
                    return true;
                }
                if(args[0].equalsIgnoreCase("resume")){
                    if(Var.TimerState.equalsIgnoreCase("not_config")){

                          taskid1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(PlayerJoinQuitEvent.plugin, new Runnable() {
                            @Override
                            public void run() {
                                ActionBarAPI.sendActionBarToAllPlayers(ChatColor.RED + "Timer ist nicht konfiguriert");
                                if(Var.TimerConfig){
                                    Bukkit.getScheduler().cancelTask(taskid1);
                                }
                            }
                        }, 0L, 20L);
                    } else {
                        Var.TimerState = "resumed";
                        if(Var.TimerMode.equalsIgnoreCase("Countdown")){
                            int SECs = 0;
                            if(Var.LastSecounds != 0){
                                SECs = Var.LastSecounds;
                            } else {
                                SECs = Var.secounds;
                            }
                            final int finalTime_in_sec;
                            finalTime_in_sec = SECs;
                            final int[] counter = {finalTime_in_sec};
                            taskid2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(PlayerJoinQuitEvent.plugin, new Runnable() {
                                @Override
                                public void run() {
                                    if(counter[0] == 0){
                                        Var.player.forEach(name -> name.setGameMode(GameMode.SPECTATOR));
                                        ActionBarAPI.sendActionBarToAllPlayers(ChatColor.GOLD + "Timer wurde pausiert");
                                        Bukkit.getScheduler().cancelTask(taskid2);
                                    }
                                    int modulo_hours = counter[0] % 3600;
                                    int hours = (finalTime_in_sec - modulo_hours) / 3600;
                                    int modulo_minutes = modulo_hours % 60;
                                    int minutes = (modulo_hours - modulo_minutes) / 60;
                                    String message = ChatColor.GOLD + "Verbleibend: " + ChatColor.BOLD + ChatColor.DARK_GREEN + hours + ":" + minutes + ":" + modulo_minutes;
                                    ActionBarAPI.sendActionBarToAllPlayers(message);
                                    Var.LastSecounds = counter[0];
                                    counter[0]--;
                                }

                            }, 0L, 20L);
                        } else{
                            if(Var.LastSecounds == 0){
                            final int[] counter = {0};
                            taskid3 = Bukkit.getScheduler().scheduleSyncRepeatingTask(PlayerJoinQuitEvent.plugin, new Runnable() {
                                @Override
                                public void run() {
                                    int modulo_hours = counter[0] % 3600;
                                    int hours = (counter[0] - modulo_hours) / 3600;
                                    int modulo_minutes = modulo_hours % 60;
                                    int minutes = (modulo_hours - modulo_minutes) / 60;
                                    String message = ChatColor.GOLD + "Timer: " + ChatColor.BOLD + ChatColor.DARK_GREEN + hours + ":" + minutes + ":" + modulo_minutes;
                                    ActionBarAPI.sendActionBarToAllPlayers(message);
                                    Var.LastSecounds = counter[0];
                                    counter[0]++;
                                }
                            }, 0L, 20L);
                        } else{
                                final int[] counter = {Var.LastSecounds};
                                taskid3 = Bukkit.getScheduler().scheduleSyncRepeatingTask(PlayerJoinQuitEvent.plugin, new Runnable() {
                                    @Override
                                    public void run() {
                                        int modulo_hours = counter[0] % 3600;
                                        int hours = (counter[0] - modulo_hours) / 3600;
                                        int modulo_minutes = modulo_hours % 60;
                                        int minutes = (modulo_hours - modulo_minutes) / 60;
                                        String message = ChatColor.GOLD + "Timer: " + ChatColor.BOLD + ChatColor.DARK_GREEN + hours + ":" + minutes + ":" + modulo_minutes;
                                        ActionBarAPI.sendActionBarToAllPlayers(message);
                                        Var.LastSecounds = counter[0];
                                        counter[0]++;
                                    }
                                }, 0L, 20L);
                            }
                        }
                    }
                    return true;
                } else if(args[0].equalsIgnoreCase("pause")){
                        Bukkit.getScheduler().cancelTask(taskid3);
                        ActionBarAPI.sendActionBarToAllPlayers(ChatColor.GOLD + "Timer ist pausiert");
                } else if(args[0].equalsIgnoreCase("reset")){
                    Bukkit.getScheduler().cancelTask(taskid3);
                    ActionBarAPI.sendActionBarToAllPlayers(ChatColor.GOLD + "Timer ist pausiert");
                    Var.LastSecounds = 0;
                }


            }else{
                sender.sendMessage("Du kannst diesen Command nur als Spieler ausführen");
            }
        }

        return false;
    }

}
