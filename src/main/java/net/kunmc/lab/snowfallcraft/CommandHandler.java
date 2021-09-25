package net.kunmc.lab.snowfallcraft;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage("引数が不足しています。");
            return false;
        }

        try {
            switch (args[0]) {
                case Config.COMMAND_START:
                    Config.onWorking = true;
                    sender.sendMessage("降雪開始");
                    if (Config.uuid == null) sender.sendMessage("降雪対象プレイヤーが設定されるまで降雪しません。");
                    break;
                case Config.COMMAND_STOP:
                    Config.onWorking = false;
                    sender.sendMessage("降雪終了");
                    break;
                case Config.COMMAND_CONFIG:
                    sender.sendMessage("***SnowfallCraft 設定値一覧***");
                    sender.sendMessage(Config.onWorking ? "降雪状態： 降雪中" : "降雪状態： 停止中");
                    String message = Config.uuid != null ? getServer().getPlayer(Config.uuid).getName() : "未設定";
                    sender.sendMessage("降雪対象プレイヤー： " + message);
                    sender.sendMessage("降雪半径： " + Config.radius);
                    sender.sendMessage("降雪間隔： " + Config.period);
                    break;
                case Config.COMMAND_PLAYER:
                case Config.COMMAND_RADIUS:
                case Config.COMMAND_PERIOD:
                    if (args.length < 2) {
                        sender.sendMessage("引数が不足しています。");
                        return false;
                    } else {
                        switch (args[0]) {
                            case Config.COMMAND_PLAYER:
                                if (args[1].equals("@r")) {
                                    ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
                                    Random random = new Random();
                                    Config.uuid = players.get(random.nextInt(players.size())).getUniqueId();
                                    sender.sendMessage("降雪対象プレイヤー： " + getServer().getPlayer(Config.uuid).getName());
                                    break;
                                } else {
                                    Player player = Bukkit.getPlayer(args[1]);
                                    if (player != null) {
                                        Config.uuid = player.getUniqueId();
                                        sender.sendMessage("降雪対象プレイヤー： " + getServer().getPlayer(Config.uuid).getName());
                                    } else {
                                        sender.sendMessage("存在しないプレイヤーです。");
                                        return false;
                                    }
                                }
                                break;
                            case Config.COMMAND_RADIUS:
                                if (Integer.parseInt(args[1]) < 1) {
                                    sender.sendMessage("降雪半径は1以上の整数である必要があります。");
                                    return false;
                                }
                                Config.radius = Integer.parseInt(args[1]);
                                sender.sendMessage("降雪半径： " + Config.radius);
                                break;
                            case Config.COMMAND_PERIOD:
                                if (Integer.parseInt(args[1]) < 1) {
                                    sender.sendMessage("降雪間隔は1以上の整数である必要があります。");
                                    return false;
                                }
                                Config.period = Integer.parseInt(args[1]);
                                sender.sendMessage("降雪間隔： " + Config.period);
                                break;
                        }
                    }
                    break;
                default:
                    sender.sendMessage("存在しない引数です。");
                    return false;
            }
        } catch (Exception e) {
            sender.sendMessage("引数が不正です。");
            return false;
        }
        return true;
    }
}
