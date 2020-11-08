package de.fynn.guild.guild.gui;

import de.fynn.guild.guild.gui.guiItems.GUIItem;

public class InventoryPage {

    private int size;

    private GUIItem[] items;

    public InventoryPage(GUIItem... items){
        size = items.length;
        this.items = items;
    }

    public GUIItem[] getItems(){
        return items;
    }

    public GUIItem getItem(int pos){
        return items[pos];
    }

    public void setItem(GUIItem item, int pos){
        items[pos] = item;
    }

    public int getSize() {
        return size;
    }

}
