package dev.noah.eventcore.util;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {

    public static ItemStack addEnchantLook(ItemStack i){
        ItemMeta meta = i.getItemMeta();
        if (meta != null) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (meta != null) {
            meta.addEnchant(Enchantment.MENDING,1,true);
        }
        i.setItemMeta(meta);
       return i;
    }

    public static ItemStack setName(ItemStack i,String name){
        ItemMeta meta = i.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));
        }
        i.setItemMeta(meta);
        return i;
    }


}
