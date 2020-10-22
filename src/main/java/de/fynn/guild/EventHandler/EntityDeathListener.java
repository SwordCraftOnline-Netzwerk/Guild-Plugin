package de.fynn.guild.EventHandler;

import de.fynn.guild.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if(Main.villagerIDContains(event.getEntity().getUniqueId())){
            Main.removeVillager(event.getEntity().getUniqueId());
            Main.getFileHandler().removeVillager(event.getEntity().getUniqueId());
        }
    }

}
