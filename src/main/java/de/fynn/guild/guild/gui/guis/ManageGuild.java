package de.fynn.guild.guild.gui.guis;

import de.fynn.guild.Main;
import de.fynn.guild.guild.Guild;
import de.fynn.guild.guild.gui.ClickAction;
import de.fynn.guild.guild.gui.GUIInventory;
import de.fynn.guild.guild.gui.guiItems.GUIItem;
import de.fynn.guild.guild.gui.guiItems.BackButton;
import de.fynn.guild.guild.permissions.GuildPermissions;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ManageGuild {

    public static GUIInventory getInventory(Player player, Guild guild){
        GUIInventory guiInventory = new GUIInventory(9, Main.getTitle(player,"inventorys.manageGuild"));

        GUIItem back = new BackButton(player);
        back.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(MyGuild.getInventory(player,guild).getInventory());
                return false;
            }
        });
        guiInventory.setItem(back,8);

        if(guild.getRoleManager().hasPermission(player, GuildPermissions.MANAGE_ROLE)){
            GUIItem manageRoles = new GUIItem(new ItemStack(Material.ORANGE_WOOL),Main.getTitle(player,"items.manageRoles"));
            manageRoles.addClickAction(new ClickAction() {
                @Override
                public boolean execute(Player player) {
                    player.openInventory(ManageRoles.getInventory(player).getInventory());
                    return false;
                }
            });
            guiInventory.setItem(manageRoles,3);
        }

        if(guild.getRoleManager().hasPermission(player,GuildPermissions.MANAGE_GULDATTRIBUTES)){
            GUIItem manageAttributes = new GUIItem(new ItemStack(Material.DIAMOND),Main.getTitle(player,"items.manageAttributes"));
            manageAttributes.addClickAction(new ClickAction() {
                @Override
                public boolean execute(Player player) {
                    player.openInventory(ManageAttributes.getInventory(player).getInventory());
                    return false;
                }
            });
            guiInventory.setItem(manageAttributes,5);
        }

        return guiInventory;
    }

}
