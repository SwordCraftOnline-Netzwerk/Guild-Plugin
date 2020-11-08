package de.fynn.guild.guild.gui.guiItems;

import de.fynn.guild.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NextButton extends GUIItem {

    public NextButton(Player player) {
        super(new ItemStack(Material.GREEN_WOOL), Main.getTitle(player,"items.next"));
    }

}
