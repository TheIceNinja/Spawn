package net.theiceninja.spawn.commands;

import net.theiceninja.spawn.Main;
import net.theiceninja.spawn.utils.ColorUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    Main plugin;
    public SetSpawnCommand(Main plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtils.color(plugin.getSpawnConfig().getString("player-err")));
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("spawn.setspawn")) {
            p.sendMessage(ColorUtils.color(plugin.getSpawnConfig().getString("no-permission")));
            return true;
        }
        Location location = p.getLocation();
        plugin.getConfig().set("spawn", location);
        plugin.saveConfig();
        p.sendMessage(ColorUtils.color(plugin.getSpawnConfig().getString("location-saved")));



        return true;
    }
}
