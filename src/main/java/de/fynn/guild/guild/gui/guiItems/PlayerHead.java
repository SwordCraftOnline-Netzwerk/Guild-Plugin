package de.fynn.guild.guild.gui.guiItems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHead extends GUIItem {

    public PlayerHead(Player player) {
        super(new ItemStack(Material.PLAYER_HEAD), player.getDisplayName());
        SkullMeta meta = (SkullMeta) getItemMeta();
        meta.setOwningPlayer(player);
        setItemMeta(meta);
    }

}
