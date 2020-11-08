package de.fynn.guild.guild.gui.guiItems;

import de.fynn.guild.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PreviousButton extends GUIItem {

    public PreviousButton(Player player) {
        super(new ItemStack(Material.RED_WOOL), Main.getTitle(player,"items.previous"));
    }

}
