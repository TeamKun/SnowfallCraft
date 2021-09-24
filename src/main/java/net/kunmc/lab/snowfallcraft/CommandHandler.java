package net.kunmc.lab.snowfallcraft;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

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
                    if (Config.player == null) sender.sendMessage("降雪対象プレイヤーが設定されるまで降雪しません。");
                    break;
                case Config.COMMAND_STOP:
                    Config.onWorking = false;
                    sender.sendMessage("降雪終了");
                    break;
                case Config.COMMAND_PLAYER:
                case Config.COMMAND_RADIUS:
                    /*case Config.COMMAND_AMOUNT:*/
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
                                    Config.player = players.get(random.nextInt(players.size()));
                                    sender.sendMessage("降雪対象プレイヤー： " + Config.player.getName());
                                    break;
                                } else {
                                    Player player = Bukkit.getPlayer(args[1]);
                                    if (player != null) {
                                        Config.player = player;
                                        sender.sendMessage("降雪対象プレイヤー： " + Config.player.getName());
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
                            /*case Config.COMMAND_AMOUNT:
                                if (Integer.parseInt(args[1]) < 1) {
                                    sender.sendMessage("降雪量は1以上の整数である必要があります。");
                                    return false;
                                }
                                Config.amount = Integer.parseInt(args[1]);
                                sender.sendMessage("降雪量： " + Config.amount);
                                break;*/
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
