package de.fynn.guild.guild.gui.guiItems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class YesNoButton extends GUIItem{

    private boolean state = false;
    private Material yes,no;

    public YesNoButton(String name, Material no, Material yes) {
        super(new ItemStack(no), name);
        this.yes = yes;
        this.no = no;
    }

    public YesNoButton(String name, Material no, Material yes, boolean state) {
        super(new ItemStack(state?yes:no), name);
        this.state = state;
        this.yes = yes;
        this.no = no;
    }

    public boolean getState(){
        return state;
    }

    public ItemStack changeState(){
        state=!state;
        changeItem();
        return getItemStack();
    }

    public void setState(boolean state){
        this.state = state;
        changeItem();
    }

    private void changeItem(){
        getItemStack().setType(state?yes:no);
    }

}
