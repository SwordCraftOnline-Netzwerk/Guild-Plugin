package de.fynn.guild.guild.gui.guis;

import de.fynn.guild.EventHandler.PlayerChatListener;
import de.fynn.guild.Main;
import de.fynn.guild.guild.gui.ClickAction;
import de.fynn.guild.guild.gui.GUIInventory;
import de.fynn.guild.guild.gui.guiItems.GUIItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NoGuild {

    public static GUIInventory getInventory(Player player){
        GUIInventory guiInventory = new GUIInventory(9, Main.getTitle(player,"inventorys.noGuild"));
        GUIItem create = new GUIItem(new ItemStack(Material.GREEN_WOOL),Main.getTitle(player,"items.createGuild"));
        create.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.sendMessage(Main.getMsg(player,"guildCreated.askGuildName"));
                PlayerChatListener.observedPlayerCreate.add(player);
                return true;
            }
        });
        guiInventory.setItem(create,3);

        GUIItem invites = new GUIItem(new ItemStack(Material.BOOK),Main.getTitle(player,"items.invites"));
        invites.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(Invites.getInventory(player).getInventory());
                return false;
            }
        });
        guiInventory.setItem(invites,5);
        return guiInventory;
    }

}
