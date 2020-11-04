package de.fynn.guild.guild;

import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class PageGUIInventory extends GUIInventory{

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
        page = page+1<pages.size()-1 ? page+1 : page;
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
            setItem(currentPage.getItem(i),i);
        }
    }

}
