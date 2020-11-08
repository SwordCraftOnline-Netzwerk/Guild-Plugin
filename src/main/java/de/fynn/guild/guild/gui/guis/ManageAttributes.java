package de.fynn.guild.guild.gui.guis;

import de.fynn.guild.EventHandler.PlayerChatListener;
import de.fynn.guild.Main;
import de.fynn.guild.guild.gui.ClickAction;
import de.fynn.guild.guild.gui.GUIInventory;
import de.fynn.guild.guild.gui.guiItems.GUIItem;
import de.fynn.guild.guild.gui.guiItems.BackButton;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ManageAttributes {

    public static GUIInventory getInventory(Player player){
        GUIInventory guiInventory = new GUIInventory(9, Main.getTitle(player,"inventorys.manageAttributes"));
        GUIItem rename = new GUIItem(new ItemStack(Material.FEATHER),Main.getTitle(player,"items.renameGuild"));
        rename.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                PlayerChatListener.observedPlayerRename.add(player);
                return true;
            }
        });
        guiInventory.setItem(rename,4);
        GUIItem back = new BackButton(player);
        back.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(ManageGuild.getInventory(player,Main.guildManager.getPlayerGuild(player)).getInventory());
                return false;
            }
        });
        guiInventory.setItem(back,8);
        return guiInventory;
    }

}
