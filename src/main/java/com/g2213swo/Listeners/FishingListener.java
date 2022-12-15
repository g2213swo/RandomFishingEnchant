package com.g2213swo.Listeners;

import com.g2213swo.RandomFishingEnchant;
import net.momirealms.customfishing.api.event.FishResultEvent;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class FishingListener implements Listener {
    FileConfiguration config = RandomFishingEnchant.plugin.getConfig();
    @EventHandler
    public void PlayerCatchFish(FishResultEvent event){
        ItemStack loot = event.getLoot();
        if (loot != null){
            if (config.getBoolean("RandomEnchantBookReplace")){
                String displayName = loot.getItemMeta().getDisplayName();
                if (loot.getType().equals(Material.BOOK) && displayName.equals(config.getString("ReplaceBookName"))){
                    loot.setType(Material.ENCHANTED_BOOK);
                    ItemMeta enchantBookItemMeta = loot.getItemMeta();
                    EnchantmentStorageMeta meta = (EnchantmentStorageMeta) enchantBookItemMeta;
                    /**
                     * 随机附魔
                     */
                    Random random = new Random();
                    Enchantment[] enchantments = Enchantment.values();
                    for (int i = 1; i<=random.nextInt(config.getInt("RandomAmount"))+1;i++){
                        int randomIndex = random.nextInt(enchantments.length - 1);
                        int maxLevel = enchantments[randomIndex].getMaxLevel();
                        if (maxLevel != 1){
                            meta.addStoredEnchant(enchantments[randomIndex], random.nextInt(maxLevel-1)+1, true);
                        }else {
                            meta.addStoredEnchant(enchantments[randomIndex], random.nextInt(maxLevel)+1, true);
                        }
                    }
                    meta.setDisplayName(null);
                    loot.setItemMeta(meta);
                }
            }
        }
    }
}
