package de.fynn.guild.guild;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class PageBuilder {

    public static InventoryPage[] buildPagesFromPlayer(Collection<? extends Player> playersCollection){
        Queue<? extends Player> players = new ArrayBlockingQueue<>(playersCollection.size(),false,playersCollection);
        List<InventoryPage> pages = new ArrayList<>();
        while(!players.isEmpty()){
            GUIItem[] items = new GUIItem[9];
            for (int i = 0; i < 9; i++) {
                if(!players.isEmpty()){
                    GUIItem item = new GUIItem(new ItemStack(Material.PLAYER_HEAD));
                    SkullMeta meta = (SkullMeta) item.getItemMeta();
                    Player player = players.remove();
                    meta.setDisplayName(player.getDisplayName());
                    meta.setOwningPlayer(player);
                    item.setItemMeta(meta);
                    items[i] = item;
                }
            }
            pages.add(new InventoryPage(items));
        }
        return pages.toArray(new InventoryPage[0]);
    }

}
