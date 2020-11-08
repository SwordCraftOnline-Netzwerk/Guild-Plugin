package de.fynn.guild.guild.gui;

import de.fynn.guild.guild.gui.guiItems.GUIItem;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PageGUIInventory extends GUIInventory {

    private List<InventoryPage> pages = new ArrayList<>();
    private int page = 0;

    public PageGUIInventory(int size, String title, InventoryPage... pages) {
        super(size, title);
        for (InventoryPage currentPage:
             pages) {
            addPage(currentPage);
        }
        loadPage();
    }

    public void addPage(InventoryPage page){
        pages.add(page);
    }

    public Inventory next(){
        page = page+1<pages.size() ? page+1 : page;
        loadPage();
        return getInventory();
    }

    public Inventory previous(){
        page = page-1>-1 ? page-1 : page;
        loadPage();
        return getInventory();
    }

    public void setPage(InventoryPage page, int pos){
        pages.set(pos,page);
    }

    private void loadPage(){
        InventoryPage currentPage = pages.get(page);
        for (int i = 0; i < currentPage.getSize(); i++) {
            setItem(currentPage.getItem(i)==null?new GUIItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)," "):currentPage.getItem(i),i);
        }
    }

    public List<InventoryPage> getPages(){
        return pages;
    }

}
