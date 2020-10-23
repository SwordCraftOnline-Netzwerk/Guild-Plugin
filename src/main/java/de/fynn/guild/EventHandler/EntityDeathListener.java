package de.fynn.guild.EventHandler;

import de.fynn.guild.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if(Main.villagerManager.containsVillager(event.getEntity().getUniqueId())){
            Main.villagerManager.removeVillager(event.getEntity().getUniqueId());
        }
    }

}
