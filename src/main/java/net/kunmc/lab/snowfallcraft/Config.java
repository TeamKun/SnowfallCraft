package net.kunmc.lab.snowfallcraft;

import java.util.UUID;

public class Config {
    public final static String MAIN_COMMAND = "snowfall";

    //第1引数
    public final static String COMMAND_START = "start";
    public final static String COMMAND_STOP = "stop";
    public final static String COMMAND_CONFIG = "config";
    public final static String COMMAND_PLAYER = "player";
    public final static String COMMAND_RADIUS = "radius";
    public final static String COMMAND_PERIOD = "period";

    //変数
    public static boolean onWorking = false;
    public static Integer radius = 50;
    public static Integer period = 10;
    public static UUID uuid = null;
}
