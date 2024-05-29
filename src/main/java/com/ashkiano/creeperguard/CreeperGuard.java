package com.ashkiano.creeperguard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CreeperGuard extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Metrics metrics = new Metrics(this, 22003);
        this.getLogger().info("Thank you for using the CreeperGuard plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://donate.ashkiano.com");
    }

    @EventHandler
    public void onCreeperExplosion(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.CREEPER) {
            for (Player player : entity.getWorld().getPlayers()) {
                if (player.getLocation().distance(entity.getLocation()) <= 10 && player.hasPermission("creeper.explode.prevent")) {
                    event.blockList().clear(); // Clear the list of blocks to be destroyed
                    break;
                }
            }
        }
    }
}
