package net.cloakmc.eventcore.util;

import net.cloakmc.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitUtil {

    public static void givekit (Player p){

        p.getInventory().setContents(EventCore.kit);
    }

    public static void saveKit(ItemStack[] i){

        EventCore.kit = i;
        EventCore.saveData();

    }

    public static void kitAll() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            givekit(p);
            p.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green + "Kit loaded!"));
        }
    }

}
