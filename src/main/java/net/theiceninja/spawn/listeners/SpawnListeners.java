package net.theiceninja.spawn.listeners;

import net.theiceninja.spawn.SpawnPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SpawnListeners implements Listener {
    private SpawnPlugin plugin;

    public SpawnListeners(SpawnPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Location location = plugin.getConfig().getLocation("spawn");
        if (location != null) {
            p.teleport(location);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Location location = plugin.getConfig().getLocation("spawn");
        if (location != null) {
            e.setRespawnLocation(location);
        }
    }
}
