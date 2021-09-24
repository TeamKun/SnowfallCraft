package net.kunmc.lab.snowfallcraft;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class Config {
    public final static String MAIN_COMMAND = "snowfall";

    //第1引数
    public final static String COMMAND_START = "start";
    public final static String COMMAND_STOP = "stop";
    public final static String COMMAND_PLAYER = "player";
    public final static String COMMAND_RADIUS = "radius";
    public final static String COMMAND_AMOUNT = "amount";
    public final static String COMMAND_PERIOD = "period";

    //変数
    public static boolean onWorking = true;
    public static Integer radius = 50;
    public static Integer amount = 1;
    public static Integer period = 10;
    public static @Nullable Player player = null;
}
