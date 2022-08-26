package net.theiceninja.spawn;

import net.theiceninja.spawn.commands.SetSpawnCommand;
import net.theiceninja.spawn.commands.SpawnCommand;
import net.theiceninja.spawn.commands.SpawnReloadCommand;
import net.theiceninja.spawn.listeners.SpawnListeners;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {
    private File spawnConfigFile;
    private FileConfiguration spawnConfig;

    @Override
    public void onEnable() {
        // commands
        createSpawn();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("reloadspawnconfig").setExecutor(new SpawnReloadCommand(this));
        // listeners
        getServer().getPluginManager().registerEvents(new SpawnListeners(this), this);


    }

    @Override
    public void onDisable() {
    }
    public FileConfiguration getSpawnConfig() {
        return this.spawnConfig;
    }
    public void reloadConfiguration() {
        spawnConfig = YamlConfiguration.loadConfiguration(spawnConfigFile);
    }

    private void createSpawn() {
        spawnConfigFile = new File(getDataFolder(), "messages.yml");
        if (!spawnConfigFile.exists()) {
            spawnConfigFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
        spawnConfig = new YamlConfiguration();
        try {
            spawnConfig.load(spawnConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
