package de.fynn.guild.guild.gui.guis;

import de.fynn.guild.Main;
import de.fynn.guild.guild.Guild;
import de.fynn.guild.guild.gui.*;
import de.fynn.guild.guild.gui.guiItems.BackButton;
import de.fynn.guild.guild.gui.guiItems.GUIItem;
import de.fynn.guild.guild.gui.guiItems.NextButton;
import de.fynn.guild.guild.gui.guiItems.PreviousButton;
import de.fynn.guild.guild.permissions.GuildRole;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MemberRole {

    public static PageGUIInventory getInventory(Player player, Player target){
        Guild guild = Main.guildManager.getPlayerGuild(player);
        List<GuildRole> roles = guild.getRoleManager().getLoader().getRoles();
        roles.removeIf(role -> guild.getRoleManager().isHigher(role, guild.getRoleManager().getRole(player)));
        PageGUIInventory guiInventory = new PageGUIInventory(18,Main.getTitle(player,"inventorys.memberRole"),PageBuilder.buildRolePages(roles,new ItemStack(Material.RED_WOOL)));

        for (InventoryPage page:
             guiInventory.getPages()) {
            for (GUIItem item:
                    page.getItems()) {
                if(item!=null){
                    if(item.getItemMeta().getDisplayName().equals(guild.getRoleManager().getRole(target).getName())){
                        item.getItemStack().setType(Material.GREEN_WOOL);
                    }else {
                        item.addClickAction(new ClickAction() {
                            @Override
                            public boolean execute(Player player) {
                                guild.getRoleManager().setRole(target,guild.getRoleManager().getLoader().getRole(item.getItemMeta().getDisplayName()));
                                player.openInventory(MemberRole.getInventory(player, target).getInventory());
                                return false;
                            }
                        });
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

        GUIItem next = new NextButton(player);
        next.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(guiInventory.next());
                return false;
            }
        });

        GUIItem back = new BackButton(player);
        back.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(ManageMember.getInventory(player, target).getInventory());
                return false;
            }
        });

        guiInventory.setItem(previous,9);
        guiInventory.setItem(next,17);
        guiInventory.setItem(back,13);
        
        return guiInventory;
    }

}
