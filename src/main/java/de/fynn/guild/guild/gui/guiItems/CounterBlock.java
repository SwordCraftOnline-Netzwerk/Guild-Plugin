package de.fynn.guild.guild.gui.guiItems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CounterBlock extends GUIItem{

    private int value = 0;
    private boolean higher;

    public CounterBlock(String name, boolean higher) {
        super(new ItemStack(Material.ARROW),name+" (0)");
        this.higher = higher;
    }

    public CounterBlock(String name, boolean higher, int startValue) {
        super(new ItemStack(Material.ARROW),name+" ("+startValue+")");
        value = startValue;
        this.higher = higher;
    }

    public void changeValue(){
        if(higher){
            value++;
        }else {
            value--;
        }
        updateName();
    }

    public void setValue(int value){
        this.value = value;
        updateName();

    }

    public int getValue() {
        return value;
    }

    private void updateName(){
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(meta.getDisplayName().substring(0,meta.getDisplayName().length()-4)+"("+(value<10?"0"+value:value)+")");
        setItemMeta(meta);
    }

}
