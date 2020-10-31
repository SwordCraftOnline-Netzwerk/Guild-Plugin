package de.fynn.guild.guild;

import de.fynn.guild.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIBuilder {

    private static Inventory inventory;

    public GUIBuilder(){

    }

    public static Inventory buildCreateGuildGUI(Player player){
        inventory = Bukkit.createInventory(player,27,Main.getTitle(player,"inventorys.createGuild"));
        inventory = fill(inventory);

        ItemStack create = new ItemStack(Material.GREEN_WOOL);
        ItemMeta meta = create.getItemMeta();
        meta.setDisplayName(Main.getTitle(player,"createGuild"));
        create.setItemMeta(meta);
        inventory.setItem(13,create);

        return inventory;
    }

    public static Inventory buildGuildGUI(Player player,boolean isOwner){
        inventory = Bukkit.createInventory(player,27,Main.getTitle(player,"inventorys.manageGuild"));
        inventory = fill(inventory);

        ItemStack leave = new ItemStack(Material.RED_WOOL);
        ItemMeta meta = leave.getItemMeta();
        meta.setDisplayName(Main.getTitle(player,"leaveGuild"));
        leave.setItemMeta(meta);
        if(isOwner){
            ItemStack close = new ItemStack(Material.TNT);
            meta = close.getItemMeta();
            meta.setDisplayName(Main.getTitle(player,"closeGuild"));
            close.setItemMeta(meta);
            inventory.setItem(8,close);
            ItemStack kickMembers = new ItemStack(Material.ORANGE_WOOL);
            meta = kickMembers.getItemMeta();
            meta.setDisplayName(Main.getTitle(player,"kickMembers"));
            kickMembers.setItemMeta(meta);
            inventory.setItem(12,kickMembers);
            ItemStack listMembers = new ItemStack(Material.OBSERVER);
            meta = listMembers.getItemMeta();
            meta.setDisplayName(Main.getTitle(player,"listMembers"));
            listMembers.setItemMeta(meta);
            inventory.setItem(14,listMembers);
        }
        inventory.setItem(26,leave);
        return inventory;
    }

    public static GuildMemberInventory buildMembersGUI(Player player, String title, boolean showLeader){
        int inventorys = Main.guildManager.getPlayerGuild(player).getMembers().size()/27;
        int page = 1;
        inventory = Bukkit.createInventory(player,27,Main.getTitle(player,title));
        inventory = fill(inventory);

        ItemStack previous = new ItemStack(Material.RED_WOOL);
        ItemMeta meta = previous.getItemMeta();
        meta.setDisplayName(Main.getTitle(player,"previous"));
        previous.setItemMeta(meta);

        ItemStack next = new ItemStack(Material.GREEN_WOOL);
        meta = next.getItemMeta();
        meta.setDisplayName(Main.getTitle(player,"next"));
        next.setItemMeta(meta);

        ItemStack back = new ItemStack(Material.BARRIER);
        meta = back.getItemMeta();
        meta.setDisplayName(Main.getTitle(player,"back"));
        back.setItemMeta(meta);

        inventory.setItem(18,previous);
        inventory.setItem(26,next);
        inventory.setItem(22,back);

        return new GuildMemberInventory(inventory,Main.guildManager.getPlayerGuild(player),showLeader);
    }

    private static PlayerListInventory buildInviteGUI(){
        return null;
    }

    private static Inventory fill(Inventory inventory){
        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = filler.getItemMeta();
        meta.setDisplayName(" ");
        filler.setItemMeta(meta);
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i,filler);
        }
        return inventory;
    }

}
