package de.fynn.guild.guild.gui.guiItems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class PlayerHead extends GUIItem {

    private UUID uuid;

    public PlayerHead(Player player) {
        super(new ItemStack(Material.PLAYER_HEAD), player.getDisplayName());
        uuid = player.getUniqueId();
        SkullMeta meta = (SkullMeta) getItemMeta();
        meta.setOwningPlayer(player);
        setItemMeta(meta);
    }

    public UUID getUuid() {
        return uuid;
    }
}
