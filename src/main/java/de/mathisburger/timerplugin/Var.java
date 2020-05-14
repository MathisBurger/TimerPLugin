package de.mathisburger.timerplugin;


import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Var {
    // Basic
    static String TimerMode = "CountUp";
    static String TimerState = "pausiert";
    static boolean TimerConfig = true;
    static boolean DebugMode = false;
    static int LastSecounds = 0;
    static List<Player> player = new ArrayList<Player>();

    //For Countdown
    static int secounds = 60;
    static boolean FinishCountDownOnDragonDeath = true;
}
