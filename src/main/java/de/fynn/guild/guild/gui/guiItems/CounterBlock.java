package de.fynn.guild.guild.gui.guiItems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CounterBlock extends GUIItem{

    private int value = 0;
    private boolean higher;

    public CounterBlock(String name, boolean higher) {
        super(new ItemStack(Material.ARROW),name);
        this.higher = higher;
    }

    public CounterBlock(String name, boolean higher, int startValue) {
        super(new ItemStack(Material.ARROW),name);
        value = startValue;
        this.higher = higher;
    }

    public void changeValue(){
        if(higher){
            value++;
        }else {
            value--;
        }
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void synchroValue(CounterBlock counter){
        counter.setValue(value);
    }

}
