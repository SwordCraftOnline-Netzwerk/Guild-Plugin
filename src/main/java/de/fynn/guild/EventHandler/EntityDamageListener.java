package de.fynn.guild.EventHandler;

import de.fynn.guild.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event){
        if(Main.villagerManager.containsVillager(event.getEntity().getUniqueId())){
            if(event.isCancelled()){
                event.setCancelled(false);
            }
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

}