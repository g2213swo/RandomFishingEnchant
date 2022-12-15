package com.g2213swo;

import com.g2213swo.commands.ReloadCommand;
import com.g2213swo.listeners.FishingListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RandomFishingEnchant extends JavaPlugin {
    FileConfiguration config = getConfig();

    public static RandomFishingEnchant plugin;

    public RandomFishingEnchant(){
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        config.addDefault("RandomEnchantBookReplace", true);
        config.addDefault("ReplaceBookName", "ReplaceBook");
        config.addDefault("RandomAmount", 5);
        config.options().copyDefaults(true);
        saveConfig();
        Objects.requireNonNull(Bukkit.getPluginCommand("enchantfishingreload")).setExecutor(new ReloadCommand());
        Bukkit.getPluginManager().registerEvents(new FishingListener(), this);
        getLogger().warning("插件已启动！");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().warning("插件已关闭！");
        HandlerList.unregisterAll();
    }
}
