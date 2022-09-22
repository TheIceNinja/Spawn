package net.theiceninja.spawn.commands;

import net.theiceninja.spawn.Main;
import net.theiceninja.spawn.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    Main plugin;
    public SpawnCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtils.color(plugin.getSpawnConfig().getString("player-err")));
            return true;
        }
        Player p = (Player) sender;
        Location location = plugin.getConfig().getLocation("spawn");
        if (location == null) {
            p.sendMessage(ColorUtils.color(plugin.getSpawnConfig().getString("no-spawn")));
            return true;
        }
        if (args.length == 0) {
            if (!p.hasPermission("spawn.spawn")) {
                p.sendMessage(ColorUtils.color(plugin.getSpawnConfig().getString("no-permission")));
                return true;
            }
            p.teleport(location);
            p.sendMessage(ColorUtils.color(plugin.getSpawnConfig().getString("spawn-teleport")));
        } else if (args.length == 1){
            if (!p.hasPermission("spawn.spawn.other")) {
                p.sendMessage(ColorUtils.color(plugin.getSpawnConfig().getString("no-permission")));
                return true;
            }
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                p.sendMessage(ColorUtils.color(plugin.getSpawnConfig().getString("player-null")));
                return true;
            }
            t.teleport(location);
            t.sendMessage(ColorUtils.color(plugin.getSpawnConfig().getString("spawn-teleport")));
            p.sendMessage(ColorUtils.color(plugin.getSpawnConfig().getString("spawn-teleport-to").replaceAll("%target%", t.getDisplayName())));
        }
        return true;
    }
}
