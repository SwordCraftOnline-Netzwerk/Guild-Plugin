package de.fynn.guild.guild.gui;

import de.fynn.guild.guild.gui.guiItems.GUIItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class GUIInventory implements InventoryHolder {

    private GUIItem[] items;

    private int size;
    private final String title;

    public GUIInventory(int size, String title){
        this.size = size;
        this.title = title;
        items = new GUIItem[size];
        fill();
    }

    public void setItem(GUIItem item, int pos){
        items[pos] = item;
    }

    public GUIItem getItem(int pos){
        return items[pos];
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, size, title);
        for (int i = 0; i < size; i++) {
            if(items[i]!=null){
                inventory.setItem(i,items[i].getItemStack());
            }
        }
        return inventory;
    }

    private void fill(){
        for (int i = 0; i < size; i++) {
            items[i] = new GUIItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)," ");
        }
    }

}
