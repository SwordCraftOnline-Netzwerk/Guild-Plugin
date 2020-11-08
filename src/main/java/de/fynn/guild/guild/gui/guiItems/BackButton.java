package de.fynn.guild.guild.gui.guiItems;

import de.fynn.guild.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BackButton extends GUIItem {

    public BackButton(Player player) {
        super(new ItemStack(Material.BARRIER),Main.getTitle(player,"items.back"));
    }

}
