package net.kunmc.lab.snowfallcraft;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TabComplete implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        switch (args.length) {
            case 1:
                completions.add(Config.COMMAND_START);
                completions.add(Config.COMMAND_STOP);
                completions.add(Config.COMMAND_CONFIG);
                completions.add(Config.COMMAND_PLAYER);
                completions.add(Config.COMMAND_RADIUS);
                completions.add(Config.COMMAND_PERIOD);
                completions.removeIf(e -> !e.startsWith(args[0].toLowerCase(Locale.ROOT)));
                break;
            case 2:
                switch (args[0]) {
                    case Config.COMMAND_PLAYER:
                        completions.addAll(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList()));
                        completions.add(0, "@r");
                        completions.removeIf(e -> !e.toLowerCase(Locale.ROOT).startsWith(args[1].toLowerCase(Locale.ROOT)));
                        break;
                    case Config.COMMAND_RADIUS:
                    case Config.COMMAND_PERIOD:
                        completions.add("<number>");
                        break;
                }
        }
        return completions;
    }
}
