package net.kunmc.lab.snowfallcraft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;


public final class SnowfallCraft extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        BukkitRunnable mainTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (Config.onWorking) {
                    Integer radius = Config.radius;
                    Integer amount = Config.amount;
                    Player player = Config.player;
                    if (player != null) {
                        Location location = player.getLocation();
                        for (int i = 0; i < amount; i++){
                            Double radian = Math.toRadians(Math.random() * 360);
                            Double distance = Math.random() * radius;
                            location.add(Math.cos(radian) * distance, 0, Math.sin(radian) * distance);
                            Integer highest = location.getWorld().getHighestBlockYAt(location);
                            if (player.getLocation().getY() > highest){
                                location.setY(player.getLocation().getY() + 50D);
                            } else{
                                location.setY(location.getWorld().getHighestBlockYAt(location) + 50D);
                            }
                            byte blockData = 0x0;
                            location.getWorld().spawnFallingBlock(location, Material.SNOW_BLOCK, blockData);
                        }
                    }
                }
            }
        };
        mainTask.runTaskTimer(this, 1L, 10L);

        Objects.requireNonNull(getCommand(Config.MAIN_COMMAND)).setExecutor(new CommandHandler());
        Objects.requireNonNull(getCommand(Config.MAIN_COMMAND)).setTabCompleter(new TabComplete());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
