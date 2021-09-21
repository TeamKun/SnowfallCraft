package net.kunmc.lab.snowfallcraft;

import com.sun.javafx.scene.control.skin.IntegerFieldSkin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Config {
    public final static String MAIN_COMMAND = "snowfall";

    //第1引数
    public final static String COMMAND_START = "start";
    public final static String COMMAND_STOP = "stop";
    public final static String COMMAND_PLAYER = "player";
    public final static String COMMAND_RADIUS = "radius";
    public final static String COMMAND_AMOUNT = "amount";

    //変数
    public static boolean onWorking = false;
    public static Integer radius = 50;
    public static Integer amount = 100;
    public static @Nullable Player player = null;
}
