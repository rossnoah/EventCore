package net.cloakmc.eventcore.gui;

import net.cloakmc.eventcore.EventCore;
import net.cloakmc.eventcore.listener.PreventBlockItemDrops;
import net.cloakmc.eventcore.listener.DurabilityListener;
import net.cloakmc.eventcore.listener.PreventExplosionsListener;
import net.cloakmc.eventcore.listener.PlayerDeathDrops;
import net.cloakmc.eventcore.util.GamemodeUtil;
import net.cloakmc.eventcore.util.KitUtil;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class GUIListener implements Listener {


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (e.getInventory().getType() == InventoryType.CHEST && e.getInventory().getSize() == 45 && e.getInventory().getLocation() == null){
            if (e.getView().getTitle().equals("§0§lEvent Dashboard")) {
                e.setCancelled(true);
                Player p = (Player) e.getView().getPlayer();
                if(e.getSlot()==18){
                    if(EventCore.inGame){
                        EventCore.inGame=false;
                    }else{
                        EventCore.inGame= true;
                    }

                }
                if(e.getSlot()==19){
                    if(EventCore.kitOnJoin){
                        EventCore.kitOnJoin=false;
                    }else{
                        EventCore.kitOnJoin= true;
                    }

                }
                if(e.getSlot()==20){
                    PreventExplosionsListener.enabled = !PreventExplosionsListener.enabled;

                }

                if(e.getSlot()==21){
                    if(p.getWorld().getPVP()){
                        p.getWorld().setPVP(false);
                    }else{
                        p.getWorld().setPVP(true);
                    }

                }

                if(e.getSlot()==22){
                   DurabilityListener.enabled = !DurabilityListener.enabled;

                }
                if(e.getSlot()==23){
                    PlayerDeathDrops.enabled = !PlayerDeathDrops.enabled;

                }
                if(e.getSlot()==24){
                    PreventBlockItemDrops.enabled = !PreventBlockItemDrops.enabled;

                }

                if(e.getSlot()==8){
                    KitUtil.kitAll();
                    p.sendMessage("GEARED ALL PLAYERS");
                    }

                if(e.getSlot()==6) {
                    EventCore.startUp();
                    p.sendMessage("STARTUP");
                }
                if(e.getSlot()==5) {
                    GUI.openGamemode(p);
                }
                if(e.getSlot()==7) {
                    EventCore.startGame();
                    p.sendMessage("STARTING GAME");
                }
                GUI.updateDashboard(e.getInventory(),p);


                }

            }

        if (e.getInventory().getType() == InventoryType.CHEST && e.getInventory().getSize() == 9 && e.getInventory().getLocation() == null){
            if (e.getView().getTitle().equals("§0§lGamemode Dashboard")) {
                e.setCancelled(true);
                Player p = (Player) e.getView().getPlayer();
                if(e.getSlot()==0) {
                    GamemodeUtil.gamemodeAll(GameMode.SPECTATOR);
                    p.sendMessage("SPECTATOR");
                }
                    if(e.getSlot()==1) {
                        GamemodeUtil.gamemodeAll(GameMode.ADVENTURE);
                        p.sendMessage("ADVENTURE");
                    }

                        if(e.getSlot()==2) {
                            GamemodeUtil.gamemodeAll(GameMode.SURVIVAL);
                            p.sendMessage("SURVIVAL");
                        }
                }

            }

        }

        }

