package de.fynn.guild.guild.gui.guis;

import de.fynn.guild.Main;
import de.fynn.guild.guild.Guild;
import de.fynn.guild.guild.gui.ClickAction;
import de.fynn.guild.guild.gui.GUIInventory;
import de.fynn.guild.guild.gui.guiItems.GUIItem;
import de.fynn.guild.guild.permissions.GuildPermissions;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MyGuild {

    public static GUIInventory getInventory(Player player,Guild guild) {
        GUIInventory guiInventory = new GUIInventory(27, Main.getTitle(player,"inventorys.myGuild"));

        GUIItem leave = new GUIItem(new ItemStack(Material.RED_WOOL),Main.getTitle(player,"items.leaveGuild"));
        leave.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.sendMessage(Main.getMsg(player,"leaveGuild.player"));
                Main.guildManager.sendMSGToAllGuildMembers(player,"leaveGuild.guild");
                Main.guildManager.removeMember(player.getUniqueId().toString(),
                        Main.guildManager.getPlayerGuild(player).getGuildName());
                return true;
            }
        });
        guiInventory.setItem(leave,26);

        GUIItem listMembers = new GUIItem(new ItemStack(Material.OBSERVER),Main.getTitle(player,"items.listMembers"));
        listMembers.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(ListMembers.buildMemberList(player).getInventory());
                return false;
            }
        });
        guiInventory.setItem(listMembers,14);

        if(guild.getRoleManager().hasPermission(player, GuildPermissions.CLOSE)) {
            GUIItem close = new GUIItem(new ItemStack(Material.TNT), Main.getTitle(player, "items.closeGuild"));
            close.addClickAction(new ClickAction() {
                @Override
                public boolean execute(Player player) {
                    player.sendMessage(Main.getMsg(player, "guildClosed"));
                    Main.guildManager.sendMSGToAllGuildMembers(player, "guildClosed");
                    Main.guildManager.closeGuild(Main.guildManager.getPlayerGuild(player).getGuildName());
                    return true;
                }
            });
            guiInventory.setItem(close, 8);
        }

        if(guild.getRoleManager().hasPermission(player,GuildPermissions.INVITE)){
            GUIItem invite = new GUIItem(new ItemStack(Material.FEATHER),Main.getTitle(player,"items.invite"));
            invite.addClickAction(new ClickAction() {
                @Override
                public boolean execute(Player player) {
                    player.openInventory(InviteMember.buildInviteGUI(player).getInventory());
                    return false;
                }
            });
            guiInventory.setItem(invite,22);
        }

        if(guild.getRoleManager().hasPermission(player,GuildPermissions.MANAGE_GUILD)){
            GUIItem manage = new GUIItem(new ItemStack(Material.DIAMOND_PICKAXE),Main.getTitle(player,"items.manageGuild"));
            manage.addClickAction(new ClickAction() {
                @Override
                public boolean execute(Player player) {
                    player.openInventory(ManageGuild.getInventory(player,guild).getInventory());
                    return false;
                }
            });
            guiInventory.setItem(manage,12);
        }

        return guiInventory;
    }

}
