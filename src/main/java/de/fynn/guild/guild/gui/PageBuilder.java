package de.fynn.guild.guild.gui;

import de.fynn.guild.Main;
import de.fynn.guild.guild.Invite;
import de.fynn.guild.guild.gui.guiItems.GUIItem;
import de.fynn.guild.guild.gui.guiItems.PlayerHead;
import de.fynn.guild.guild.permissions.GuildRole;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class PageBuilder {

    public static InventoryPage[] buildPagesFromPlayer(Collection<? extends Player> playersCollection){
        if(playersCollection.isEmpty()){
            return new InventoryPage[]{new InventoryPage()};
        }
        Queue<? extends Player> players = new ArrayBlockingQueue<>(playersCollection.size(),false,playersCollection);
        List<InventoryPage> pages = new ArrayList<>();
        while(!players.isEmpty()){
            GUIItem[] items = new GUIItem[9];
            for (int i = 0; i < 9; i++) {
                if(!players.isEmpty()){
                    GUIItem item = new PlayerHead(players.remove());
                    items[i] = item;
                }
            }
            pages.add(new InventoryPage(items));
        }
        return pages.toArray(new InventoryPage[0]);
    }

    public static InventoryPage[] buildInvitePages(Player player){
        if(Main.guildManager.hasInvite(player)){
            List<Invite> invites = Main.guildManager.getInvite(player);
            List<InventoryPage> pages = new ArrayList<>();
            while (!invites.isEmpty()){
                GUIItem[] items = new GUIItem[9];
                for (int i = 0; i < 9; i++) {
                    if(!invites.isEmpty()){
                        Invite invite = invites.remove(0);
                        GUIItem item = new GUIItem(new ItemStack(Material.BOOK),invite.getGuild().getGuildName());
                        items[i] = item;
                    }
                }
                pages.add(new InventoryPage(items));
            }
            return pages.toArray(new InventoryPage[0]);
        }else {
            return new InventoryPage[]{new InventoryPage()};
        }
    }

    public static InventoryPage[] buildRolePages(List<GuildRole> roles, ItemStack itemStack){
        List<InventoryPage> pages = new ArrayList<>();
        while (!roles.isEmpty()){
            GUIItem[] items = new GUIItem[9];
            for (int i = 0; i < 9; i++) {
                if(!roles.isEmpty()){
                    GuildRole role = roles.remove(0);
                    items[i] = new GUIItem(new ItemStack(itemStack), role.getName());
                }
            }
            pages.add(new InventoryPage(items));
        }
        return pages.toArray(new InventoryPage[0]);
    }

}
