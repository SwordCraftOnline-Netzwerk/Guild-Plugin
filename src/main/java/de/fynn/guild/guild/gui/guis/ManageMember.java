package de.fynn.guild.guild.gui.guis;

import de.fynn.guild.Main;
import de.fynn.guild.guild.gui.ClickAction;
import de.fynn.guild.guild.gui.GUIInventory;
import de.fynn.guild.guild.gui.guiItems.GUIItem;
import de.fynn.guild.guild.gui.guiItems.BackButton;
import de.fynn.guild.guild.permissions.GuildPermissions;
import de.fynn.guild.guild.permissions.GuildRoleManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ManageMember {

    public static GUIInventory getInventory(Player player, Player target){
        GUIInventory guiInventory = new GUIInventory(9, Main.getTitle(target,player,"inventorys.manageMember"));
        GuildRoleManager roleManager = Main.guildManager.getPlayerGuild(player).getRoleManager();
        if(roleManager.hasPermission(player, GuildPermissions.KICK)){
            GUIItem kick = new GUIItem(new ItemStack(Material.RED_WOOL),Main.getTitle(player,"items.kick"));
            kick.addClickAction(new ClickAction() {
                @Override
                public boolean execute(Player player) {
                    Main.guildManager.removeMember(target,Main.guildManager.getPlayerGuild(player).getGuildName());
                    return false;
                }
            });
            guiInventory.setItem(kick,3);
        }

        if(roleManager.hasPermission(player,GuildPermissions.ROLE)){
            GUIItem manageRole = new GUIItem(new ItemStack(Material.ORANGE_WOOL),Main.getTitle(player,"items.manageRole"));
            manageRole.addClickAction(new ClickAction() {
                @Override
                public boolean execute(Player player) {
                    player.openInventory(MemberRole.getInventory(player, target).getInventory());
                    return false;
                }
            });
            guiInventory.setItem(manageRole,5);
        }

        GUIItem back = new BackButton(player);
        back.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(ListMembers.buildMemberList(player).getInventory());
                return false;
            }
        });
        guiInventory.setItem(back,8);

        return guiInventory;
    }

}
