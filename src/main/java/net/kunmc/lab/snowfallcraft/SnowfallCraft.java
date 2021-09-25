package net.kunmc.lab.snowfallcraft;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public final class SnowfallCraft extends JavaPlugin {
    public Plugin SnowfallCraft = this;
    public Timer timer;
    public TimerTask timerTask;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("SnowfallCraftプラグインが有効になりました。");

        Objects.requireNonNull(getCommand(Config.MAIN_COMMAND)).setExecutor(new CommandHandler());
        Objects.requireNonNull(getCommand(Config.MAIN_COMMAND)).setTabCompleter(new TabComplete());

        timer = new Timer();
        timerTask = new TimerTask() {
            int count;

            public void run() {
                count++;
                if (Config.period <= count) {
                    count = 0;
                    if (Config.onWorking) {
                        Integer radius = Config.radius;
                        Player player = getServer().getPlayer(Config.uuid);
                        if (player != null && player.isOnline()) {
                            Location location = player.getLocation();
                            double radian = Math.toRadians(Math.random() * 360);
                            double distance = Math.random() * radius;
                            location.add(Math.cos(radian) * distance, 0, Math.sin(radian) * distance);
                            location.set(Math.floor(location.getX()) + 0.5, Math.floor(location.getY()), Math.floor(location.getZ()) + 0.5);
                            int highest = location.getWorld().getHighestBlockYAt(location);
                            if (player.getLocation().getY() > highest) {
                                location.setY(player.getLocation().getY() + 25 + Math.random() * 25);
                            } else {
                                location.setY(location.getWorld().getHighestBlockYAt(location) + 25 + Math.random() * 25);
                            }
                            if (location.getY() > 254) location.setY(255);

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    location.getWorld().spawnFallingBlock(location, Material.SNOW_BLOCK.createBlockData());
                                }
                            }.runTask(SnowfallCraft);
                        }
                    }
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("SnowfallCraftプラグインが無効になりました。");
    }
}
