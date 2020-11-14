package de.fynn.guild.guild.gui.guis;

import de.fynn.guild.Main;
import de.fynn.guild.guild.Guild;
import de.fynn.guild.guild.gui.*;
import de.fynn.guild.guild.gui.guiItems.*;
import de.fynn.guild.guild.permissions.GuildPermissions;
import de.fynn.guild.guild.permissions.GuildRoleManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListMembers {

    public static PageGUIInventory buildMemberList(Player player){
        Guild guild = Main.guildManager.getPlayerGuild(player);
        GuildRoleManager roleManager = guild.getRoleManager();
        List<Player> members = new ArrayList<>();
        for (UUID uuid:
                guild.getMembers()) {
            members.add((Player) Bukkit.getOfflinePlayer(uuid));
        }
        PageGUIInventory guiInventory = new PageGUIInventory(18, Main.getTitle(player,"inventorys.listMembers"), PageBuilder.buildPagesFromPlayer(members));

        if(roleManager.hasPermission(player, GuildPermissions.MANAGE_MEMBER)){
            for (InventoryPage page:
                    guiInventory.getPages()) {
                for (GUIItem item:
                        page.getItems()) {
                    if(item!=null){
                        if(!roleManager.hasHigherOrEqualsPriority((Player) Bukkit.getOfflinePlayer(((PlayerHead)item).getUuid()),player)){
                            item.addClickAction(new ClickAction() {
                                @Override
                                public boolean execute(Player player) {
                                    player.openInventory(ManageMember.getInventory(player,(Player) Bukkit.getOfflinePlayer(((PlayerHead)item).getUuid())).getInventory());
                                    return false;
                                }
                            });
                        }
                    }
                }
            }
        }

        GUIItem previous = new PreviousButton(player);
        previous.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(guiInventory.previous());
                return false;
            }
        });
        if(guiInventory.getPageNumber()>0){
            guiInventory.setItem(previous,9);
        }else{
            guiInventory.setItem(new GUIItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)," "),9);
        }

        GUIItem next = new NextButton(player);
        next.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(guiInventory.next());
                return false;
            }
        });
        if(guiInventory.getPageNumber()<guiInventory.getPages().size()-1){
            guiInventory.setItem(next,17);
        }else {
            guiInventory.setItem(new GUIItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)," "),17);
        }

        GUIItem back = new BackButton(player);
        back.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(MyGuild.getInventory(player, guild).getInventory());
                return false;
            }
        });

        guiInventory.setItem(back,13);

        return guiInventory;
    }

}
