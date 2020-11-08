package de.fynn.guild.guild.gui.guis;

import de.fynn.guild.Main;
import de.fynn.guild.guild.gui.*;
import de.fynn.guild.guild.gui.guiItems.BackButton;
import de.fynn.guild.guild.gui.guiItems.GUIItem;
import de.fynn.guild.guild.gui.guiItems.NextButton;
import de.fynn.guild.guild.gui.guiItems.PreviousButton;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collection;

public class InviteMember{

    public static PageGUIInventory buildInviteGUI(Player player){
        Collection<? extends Player> players = Main.guildlessPlayer;
        PageGUIInventory guiInventory = new PageGUIInventory(18, Main.getTitle(player,"inventorys.inviteMember"), PageBuilder.buildPagesFromPlayer(players));
        for (InventoryPage page:
                guiInventory.getPages()) {
            for (GUIItem item:
                    page.getItems()) {
                if(item!=null&&item.getItemStack().getType()!=Material.BLACK_STAINED_GLASS_PANE){
                    item.addClickAction(new ClickAction() {
                        @Override
                        public boolean execute(Player player) {
                            Main.guildManager.invite(player,Bukkit.getPlayer(item.getItemMeta().getDisplayName()),Main.guildManager.getPlayerGuild(player).getGuildName());
                            return false;
                        }
                    });
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
                player.openInventory(MyGuild.getInventory(player, Main.guildManager.getPlayerGuild(player)).getInventory());
                return false;
            }
        });

        guiInventory.setItem(previous,9);
        guiInventory.setItem(next,17);
        guiInventory.setItem(back,13);

        return guiInventory;
    }

}
