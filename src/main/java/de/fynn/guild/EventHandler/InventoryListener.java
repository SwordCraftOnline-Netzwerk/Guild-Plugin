package de.fynn.guild.EventHandler;

import de.fynn.guild.guild.ClickAction;
import de.fynn.guild.guild.GUIInventory;
import de.fynn.guild.guild.GUIItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){
        if(event.getView().getTopInventory().getHolder() instanceof GUIInventory){
            event.setCancelled(true);
            if(event.getWhoClicked() instanceof Player){
                Player player = (Player) event.getWhoClicked();
                ItemStack itemStack = event.getCurrentItem();
                if(itemStack == null || itemStack.getType() == Material.AIR){
                    return;
                }
                GUIInventory guiInventory = (GUIInventory) event.getView().getTopInventory().getHolder();
                GUIItem item = guiInventory.getItem(event.getRawSlot());
                if(item == null){
                    return;
                }
                boolean closed = false;
                for (ClickAction action:
                     item.getActions()) {
                    closed = action.execute(player);
                }
                if(closed) event.getView().close();
            }
        }
    }

}
