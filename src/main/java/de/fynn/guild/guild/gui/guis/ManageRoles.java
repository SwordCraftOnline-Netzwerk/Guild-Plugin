package de.fynn.guild.guild.gui.guis;

import de.fynn.guild.Main;
import de.fynn.guild.guild.gui.*;
import de.fynn.guild.guild.gui.guiItems.BackButton;
import de.fynn.guild.guild.gui.guiItems.GUIItem;
import de.fynn.guild.guild.gui.guiItems.NextButton;
import de.fynn.guild.guild.gui.guiItems.PreviousButton;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ManageRoles {

    public static PageGUIInventory getInventory(Player player) {
        PageGUIInventory guiInventory = new PageGUIInventory(18, Main.getTitle(player, "inventorys.manageRoles"),
                PageBuilder.buildRolePages(Main.guildManager.getPlayerGuild(player).getRoleManager().getLoader().getRoles(),new ItemStack(Material.STONE)));
        for (InventoryPage page:
             guiInventory.getPages()) {
            for (GUIItem item:
                    page.getItems()) {
                if(item!=null){
                    item.addClickAction(new ClickAction() {
                        @Override
                        public boolean execute(Player player) {
                            player.openInventory(ManageRole.getInventory(player,Main.guildManager.getPlayerGuild(player).getRoleManager().getLoader().getRole(
                                    item.getItemMeta().getDisplayName())).getInventory());
                            return false;
                        }
                    });
                }
            }
        }
        GUIItem create = new GUIItem(new ItemStack(Material.EGG),Main.getTitle(player,"items.createRole"));
        create.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(CreateRole.getInventory(player).getInventory());
                return false;
            }
        });
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

        guiInventory.setItem(create,12);
        GUIItem back = new BackButton(player);
        back.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(ManageGuild.getInventory(player,Main.guildManager.getPlayerGuild(player)).getInventory());
                return false;
            }
        });

        guiInventory.setItem(back,14);
        return guiInventory;
    }

}
