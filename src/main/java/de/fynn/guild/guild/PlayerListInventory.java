package de.fynn.guild.guild;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class PlayerListInventory implements Inventory {

    private Inventory inventory;
    private int currentPage = 1;
    private int avaiablePages;
    private HashMap<Integer,List<Player>> pages = new HashMap<>();

    public PlayerListInventory(Inventory inventory){
        this.inventory = inventory;
        Player[] player = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        avaiablePages = player.length%9!=0?player.length/9+1:player.length/9;
        for (int i = 0; i < avaiablePages; i++) {
            List<Player> members = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                if((i+1)*(j+1)>player.length){
                    break;
                }
                members.add(Bukkit.getPlayer(player[i*j].getUniqueId()));
            }
            pages.put(i+1,members);
        }
        firstPage();
    }

    @Override
    public int getSize() {
        return inventory.getSize();
    }

    @Override
    public int getMaxStackSize() {
        return inventory.getMaxStackSize();
    }

    @Override
    public void setMaxStackSize(int size) {
        inventory.setMaxStackSize(size);
    }

    @Override
    public ItemStack getItem(int index) {
        return inventory.getItem(index);
    }

    @Override
    public void setItem(int index, ItemStack item) {
        inventory.setItem(index, item);
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
        return inventory.addItem(items);
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
        return inventory.removeItem(items);
    }

    @Override
    public ItemStack[] getContents() {
        return inventory.getContents();
    }

    @Override
    public void setContents(ItemStack[] items) throws IllegalArgumentException {
        inventory.setContents(items);
    }

    @Override
    public ItemStack[] getStorageContents() {
        return inventory.getStorageContents();
    }

    @Override
    public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
        inventory.setStorageContents(items);
    }

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        return inventory.contains(material);
    }

    @Override
    public boolean contains(ItemStack item) {
        return inventory.contains(item);
    }

    @Override
    public boolean contains(Material material, int amount) throws IllegalArgumentException {
        return inventory.contains(material, amount);
    }

    @Override
    public boolean contains(ItemStack item, int amount) {
        return inventory.contains(item, amount);
    }

    @Override
    public boolean containsAtLeast(ItemStack item, int amount) {
        return inventory.containsAtLeast(item, amount);
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        return inventory.all(material);
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
        return inventory.all(item);
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        return inventory.first(material);
    }

    @Override
    public int first(ItemStack item) {
        return inventory.first(item);
    }

    @Override
    public int firstEmpty() {
        return inventory.firstEmpty();
    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {
        inventory.remove(material);
    }

    @Override
    public void remove(ItemStack item) {
        inventory.remove(item);
    }

    @Override
    public void clear(int index) {
        inventory.clear(index);
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    public List<HumanEntity> getViewers() {
        return inventory.getViewers();
    }

    @Override
    public InventoryType getType() {
        return inventory.getType();
    }

    @Override
    public InventoryHolder getHolder() {
        return inventory.getHolder();
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        return inventory.iterator();
    }

    @Override
    public ListIterator<ItemStack> iterator(int index) {
        return inventory.iterator(index);
    }

    @Override
    public Location getLocation() {
        return inventory.getLocation();
    }

    private void firstPage(){
        List<Player> players = pages.get(currentPage);
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        for (int i = 0; i < players.size(); i++) {
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwningPlayer(Bukkit.getOfflinePlayer(players.get(i).getUniqueId()));
            meta.setDisplayName(players.get(i).getDisplayName());
            head.setItemMeta(meta);
            inventory.setItem(i,head);
        }
    }

    public void nextPage(){
        currentPage++;
        List<Player> players = pages.get(currentPage);
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        for (int i = 0; i < players.size(); i++) {
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwningPlayer(Bukkit.getOfflinePlayer(players.get(i).getUniqueId()));
            meta.setDisplayName(players.get(i).getDisplayName());
            head.setItemMeta(meta);
            inventory.setItem(i,head);
        }
    }

    public void previousPage(){
        currentPage--;
        List<Player> players = pages.get(currentPage);
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        for (int i = 0; i < players.size(); i++) {
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwningPlayer(Bukkit.getOfflinePlayer(players.get(i).getUniqueId()));
            meta.setDisplayName(players.get(i).getDisplayName());
            head.setItemMeta(meta);
            inventory.setItem(i,head);
        }
    }

    public boolean hasNextPage(){
        return currentPage+1<=avaiablePages;
    }

    public boolean hasPreviousPage(){
        return currentPage-1>=1;
    }

    public Inventory getInventory(){
        return inventory;
    }

}
