package dev.noah.eventcore.listener;

import dev.noah.eventcore.EventCore;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.util.Vector;

public class BorderListener implements Listener {

    @EventHandler
    public void onPearl(PlayerTeleportEvent e){
        if(e.getCause()!= PlayerTeleportEvent.TeleportCause.ENDER_PEARL){
            return;
        }

        World w = e.getFrom().getWorld();
        WorldBorder wb = w.getWorldBorder();

        if(wb.isInside(e.getFrom())){

            if(!wb.isInside(e.getTo())){
                e.setCancelled(true);
                e.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(EventCore.red+"<bold>Please don't leave the playing area!"));
            }

        }
    }

    @EventHandler
    public void onBorderDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player) {
            if (e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
                Player p = (Player) e.getEntity();
                    World w = p.getWorld();
                    WorldBorder worldBorder = w.getWorldBorder();
                    Location center = worldBorder.getCenter();
                    if(!worldBorder.isInside(p.getLocation())){






                        double maxX = worldBorder.getCenter().getBlockX()+worldBorder.getSize()/2;
                        double minX = worldBorder.getCenter().getBlockX()-worldBorder.getSize()/2;
                        double maxZ = worldBorder.getCenter().getBlockZ()+worldBorder.getSize()/2;
                        double minZ = worldBorder.getCenter().getBlockZ()-worldBorder.getSize()/2;

                        /*
                        if(e.getDamage()>10){
                            if(p.getLocation().getBlockX()>maxX){
                                Location l = p.getLocation();
                                l.setX(maxX);
                                l = l.getWorld().getHighestBlockAt(l).getLocation().add(0,2,0);
                                p.teleport(l);
                            }else if(p.getLocation().getBlockX()<minX) {
                                Location l = p.getLocation();
                                l.setX(minX);
                                l = l.getWorld().getHighestBlockAt(l).getLocation().add(0,2,0);
                                p.teleport(l);                            }

                            if(p.getLocation().getBlockZ()>maxZ){
                                Location l = p.getLocation();
                                l.setZ(maxZ);
                                l = l.getWorld().getHighestBlockAt(l).getLocation().add(0,2,0);
                                p.teleport(l);                            }
                            else if(p.getLocation().getBlockZ()<minZ) {
                                Location l = p.getLocation();
                                l.setZ(minZ);
                                l = l.getWorld().getHighestBlockAt(l).getLocation().add(0,2,0);
                                p.teleport(l);                              }
                        }

                         */




                        if(p.getLocation().getBlockX()>maxX){
                            p.setVelocity(p.getVelocity().add(new Vector(-1.3,0.1,0)));
                        }else if(p.getLocation().getBlockX()<minX) {
                            p.setVelocity(p.getVelocity().add(new Vector(1.3,0.1,0)));
                        }

                        if(p.getLocation().getBlockZ()>maxZ){
                            p.setVelocity(p.getVelocity().add(new Vector(0,0.1,-1.3)));
                        }else if(p.getLocation().getBlockZ()<minZ) {
                            p.setVelocity(p.getVelocity().add(new Vector(0,0.1,1.3)));
                        }




                        }
            }
        }
    }



}
