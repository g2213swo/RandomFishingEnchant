package com.g2213swo.Commands;

import com.g2213swo.RandomFishingEnchant;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ReloadCommand implements CommandExecutor {
    final Plugin plugin = RandomFishingEnchant.plugin;
    final FileConfiguration config = RandomFishingEnchant.plugin.getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player || sender instanceof ConsoleCommandSender){
            if (args.length == 1 && args[0].equalsIgnoreCase("enchantfishingreload")){
                plugin.reloadConfig();
                sender.sendMessage("插件重载完成！");
            }else {
                sender.sendMessage("输入指令有误");
            }
        }
        return true;
    }
}
