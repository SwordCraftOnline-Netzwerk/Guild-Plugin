package de.fynn.guild.guild.gui.guiItems;

import de.fynn.guild.guild.gui.ClickAction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUIItem extends ItemStack {

    private final ItemStack itemStack;
    private final List<ClickAction> actions = new ArrayList<>();

    public GUIItem(ItemStack itemStack){
        this.itemStack = itemStack;
    }

    public GUIItem(ItemStack itemStack, String name){
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        this.itemStack = itemStack;
    }

    public GUIItem(ItemStack itemStack, String name, String... lore){
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(meta);
        this.itemStack = itemStack;
    }

    public GUIItem addClickAction(ClickAction action){
        actions.add(action);
        return this;
    }

    public void removeClickAction(ClickAction action){
        actions.remove(action);
    }

    public List<ClickAction> getActions(){
        return actions;
    }

    public ItemStack getItemStack(){
        return itemStack;
    }

    public ItemMeta getItemMeta(){
        return itemStack.getItemMeta();
    }

    public boolean setItemMeta(ItemMeta meta){
        return itemStack.setItemMeta(meta);
    }

}
