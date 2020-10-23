package de.fynn.guild.guild;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIBuilder {

    private Inventory inventory;

    public GUIBuilder(){

    }

    public Inventory buildCreateGuildGUI(Player player){
        inventory = Bukkit.createInventory(player,27,"Gilde gründen");
        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = filler.getItemMeta();
        meta.setDisplayName(" ");
        filler.setItemMeta(meta);
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i,filler);
        }

        ItemStack create = new ItemStack(Material.GREEN_WOOL);
        meta = create.getItemMeta();
        meta.setDisplayName("neue Gilde gründen");
        create.setItemMeta(meta);
        inventory.setItem(13,create);

        return inventory;
    }

    public Inventory buildGuildGUI(Player player,boolean isOwner){
        inventory = Bukkit.createInventory(player,27,"Gilde verwalten");
        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = filler.getItemMeta();
        meta.setDisplayName(" ");
        filler.setItemMeta(meta);
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i,filler);
        }

        ItemStack leave = new ItemStack(Material.RED_WOOL);
        meta = leave.getItemMeta();
        meta.setDisplayName("Gilde verlassen");
        leave.setItemMeta(meta);
        if(isOwner){
            ItemStack close = new ItemStack(Material.BARRIER);
            meta = close.getItemMeta();
            meta.setDisplayName("Gilde auflösen");
            close.setItemMeta(meta);
            inventory.setItem(12,leave);
            inventory.setItem(14,close);
        }else {
            inventory.setItem(13,leave);
        }

        return inventory;
    }

}
