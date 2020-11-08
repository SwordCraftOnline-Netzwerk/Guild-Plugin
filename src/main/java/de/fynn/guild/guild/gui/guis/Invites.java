package de.fynn.guild.guild.gui.guis;

import de.fynn.guild.Main;
import de.fynn.guild.guild.gui.*;
import de.fynn.guild.guild.gui.guiItems.BackButton;
import de.fynn.guild.guild.gui.guiItems.GUIItem;
import de.fynn.guild.guild.gui.guiItems.NextButton;
import de.fynn.guild.guild.gui.guiItems.PreviousButton;
import org.bukkit.entity.Player;

public class Invites {

    public static PageGUIInventory getInventory(Player player){
        PageGUIInventory guiInventory = new PageGUIInventory(18, Main.getTitle(player,"inventorys.invites"),PageBuilder.buildInvitePages(player));
        for (InventoryPage page:
                guiInventory.getPages()) {
            for (GUIItem item:
                 page.getItems()) {
                if(item!=null){
                    item.addClickAction(new ClickAction() {
                        @Override
                        public boolean execute(Player player) {

                            return true;
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
                player.openInventory(NoGuild.getInventory(player).getInventory());
                return false;
            }
        });

        guiInventory.setItem(previous,9);
        guiInventory.setItem(next,17);
        guiInventory.setItem(back,13);

        return guiInventory;
    }

}
