package dev.noah.eventcore.gui;

import dev.noah.eventcore.listener.PreventBlockItemDrops;
import dev.noah.eventcore.util.InvUtil;
import dev.noah.eventcore.EventCore;
import dev.noah.eventcore.listener.DurabilityListener;
import dev.noah.eventcore.listener.PlayerDeathDrops;
import dev.noah.eventcore.listener.PreventExplosionsListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GUI {

    public static void openDashboard(Player p){

                Inventory inv = Bukkit.createInventory(null, 45, "§0§lEvent Dashboard");
                updateDashboard(inv,p);
                p.openInventory(inv);
    }


    public static void updateDashboard(Inventory inv, Player p){
        if(EventCore.inGame){
            InvUtil.addItem(inv,18, Material.LIME_CONCRETE_POWDER,1,"Currently INGAME",false);
        }else{
            InvUtil.addItem(inv,18, Material.RED_CONCRETE_POWDER,1,"Currently NOT INGAME",false);
        }
        if(EventCore.kitOnJoin){
            InvUtil.addItem(inv,19, Material.LIME_CONCRETE_POWDER,1,"Auto Kit is ON",false);
        }else{
            InvUtil.addItem(inv,19, Material.RED_CONCRETE_POWDER,1,"Auto Kit is OFF",false);
        }


        if(!PreventExplosionsListener.enabled){
            InvUtil.addItem(inv,20, Material.LIME_CONCRETE_POWDER,1,"Explosions are ON",false);
        }else{
            InvUtil.addItem(inv,20, Material.RED_CONCRETE_POWDER,1,"Explosions are OFF",false);
        }

        if(p.getWorld().getPVP()){
            InvUtil.addItem(inv,21, Material.LIME_CONCRETE_POWDER,1,"PvP is ON",false);
        }else{
            InvUtil.addItem(inv,21, Material.RED_CONCRETE_POWDER,1,"PvP is OFF",false);
        }

        if(DurabilityListener.enabled){
            InvUtil.addItem(inv,22, Material.LIME_CONCRETE_POWDER,1,"Durability is ON",false);
        }else{
            InvUtil.addItem(inv,22, Material.RED_CONCRETE_POWDER,1,"Durability is OFF",false);
        }

        if(PlayerDeathDrops.enabled){
            InvUtil.addItem(inv,23, Material.LIME_CONCRETE_POWDER,1,"Player items drops are ON",false);
        }else{
            InvUtil.addItem(inv,23, Material.RED_CONCRETE_POWDER,1,"Player items drops are OFF",false);
        }

        if(PreventBlockItemDrops.enabled){
            InvUtil.addItem(inv,24, Material.LIME_CONCRETE_POWDER,1,"Prevent block item drops is ON",false);
        }else{
            InvUtil.addItem(inv,24, Material.RED_CONCRETE_POWDER,1,"Prevent block item drops is OFF",false);
        }


        InvUtil.addItem(inv,27, Material.BLACK_CONCRETE_POWDER,1,"Build Limit: "+EventCore.buildLimit,true);



        InvUtil.addItem(inv,5,Material.DIAMOND,1,"GAMEMODE DASHBOARD",true);

        InvUtil.addItem(inv,6,Material.EMERALD_BLOCK,1,"STARTUP",true);

        InvUtil.addItem(inv,8,Material.NETHERITE_BLOCK,1,"GEAR ALL",true);
        InvUtil.addItem(inv,7,Material.BARRIER,1,"START",true);



    }


    public static void openGamemode(Player p){

        Inventory inv = Bukkit.createInventory(null, 9, "§0§lGamemode Dashboard");
        InvUtil.addItem(inv,0,Material.STRUCTURE_VOID,1,"SPECTATOR",false);
        InvUtil.addItem(inv,1,Material.BARRIER,1,"ADVENTURE",false);
        InvUtil.addItem(inv,2,Material.DIRT,1,"SURVIVAL",false);


        p.openInventory(inv);
    }

}
