package dev.noah.eventcore.commands;

import dev.noah.eventcore.util.KitUtil;
import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Savekit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p= (Player) sender;
            KitUtil.saveKit(p.getInventory().getContents().clone());
            //EventCore.kit = p.getInventory().getContents();
            p.sendMessage(MiniMessage.miniMessage().deserialize(EventCore.green+"Kit Saved!"));
         }
        return true;
    }
}
