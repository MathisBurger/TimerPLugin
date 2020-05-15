package de.mathisburger.timerplugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimerConfigurator implements CommandExecutor {
    private int taskid2;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equalsIgnoreCase("CountUp")){
            Var.TimerMode = "CountUp";
            Var.TimerConfig = true;
            return true;
        } else if(args[0].equalsIgnoreCase("CountDown")){
            Var.TimerConfig = true;
            Var.TimerMode = "CountDown";
            Var.TimerState = "paused";
            if(Var.DebugMode){
                sender.sendMessage("Timermode: " + Var.TimerMode);
                sender.sendMessage("Timerconfig: " + Var.TimerConfig);
                sender.sendMessage("Timerstate: " + Var.TimerState);
            }
            int time = Integer.parseInt(args[1]);
            String unit = args[2];
            int time_in_sec = 0;
            if(unit.equalsIgnoreCase("s")){
                time_in_sec = time;
            }
            if(unit.equalsIgnoreCase("m")){
                time_in_sec = time * 60;
            }
            if(unit.equalsIgnoreCase("h")){
                time_in_sec = time * 3600;
            }
            int finalTime_in_sec = time_in_sec;
            int modulo_hours = finalTime_in_sec % 3600;
            int hours = (finalTime_in_sec - modulo_hours) / 3600;
            int modulo_minutes = modulo_hours % 60;
            int minutes = (modulo_hours - modulo_minutes) / 60;
            String message = ChatColor.GREEN + "Timer auf " + ChatColor.BOLD + hours + ":" + minutes + ":" + modulo_minutes + ChatColor.GREEN + " gesetzt";
            sender.sendMessage(message);
            Var.secounds = finalTime_in_sec;
            return true;
        } else if(args[0].equalsIgnoreCase("debug")){
            String value = args[1];
            if(value.equalsIgnoreCase("true")){
                sender.sendMessage(ChatColor.BOLD + "Debug is now " + ChatColor.GREEN + "TRUE");
                Var.DebugMode = true;
            } else{
                sender.sendMessage(ChatColor.BOLD + "Debug is now " + ChatColor.RED + "FALSE");
                Var.DebugMode = false;
            }
            return true;
        } else if(args[0].equalsIgnoreCase("stopcountdownondragondeath")){
            String value = args[1];
            if(value.equalsIgnoreCase("true")){
                Var.FinishCountDownOnDragonDeath = true;
                sender.sendMessage(ChatColor.GREEN + "Der Timer wird gestoppt, sobald der Ender Drache stirbt");
            } else{
                Var.FinishCountDownOnDragonDeath = false;
                sender.sendMessage("Bestätigt");
            }
            return true;
        }

        return false;
    }
}
