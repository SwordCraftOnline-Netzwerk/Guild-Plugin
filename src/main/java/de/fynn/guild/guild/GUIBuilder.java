package de.fynn.guild.guild;

import de.fynn.guild.EventHandler.PlayerChatListener;
import de.fynn.guild.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.UUID;

public class GUIBuilder {

    public static GUIInventory buildCreateGuildGUI(Player player){
        GUIInventory guiInventory = new GUIInventory(9,Main.getTitle(player,"inventorys.createGuild"));
        GUIItem create = new GUIItem(new ItemStack(Material.GREEN_WOOL),Main.getTitle(player,"createGuild"));
        create.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.sendMessage(Main.getMsg(player,"guildCreated.askGuildName"));
                PlayerChatListener.observedPlayerCreate.add(player);
                return true;
            }
        });
        guiInventory.setItem(create,4);
        return guiInventory;
    }

    public static GUIInventory buildGuildGUI(Player player,boolean isOwner){
        GUIInventory guiInventory = new GUIInventory(27,Main.getTitle(player,"inventorys.manageGuild"));
        GUIItem leave = new GUIItem(new ItemStack(Material.RED_WOOL),Main.getTitle(player,"leaveGuild"));
        leave.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.sendMessage(Main.getMsg(player,"leaveGuild.player"));
                sendMsgToGuildMembers(player,"leaveGuild.guild");
                Main.guildManager.removeMember(player.getUniqueId().toString(),
                        Main.guildManager.getPlayerGuild(player).getGuildName());
                return true;
            }
        });
        if(isOwner){
            GUIItem close = new GUIItem(new ItemStack(Material.TNT),Main.getTitle(player,"closeGuild"));
            close.addClickAction(new ClickAction() {
                @Override
                public boolean execute(Player player) {
                    player.sendMessage(Main.getMsg(player,"guildClosed"));
                    sendMsgToGuildMembers(player,"guildClosed");
                    Main.guildManager.closeGuild(Main.guildManager.getPlayerGuild(player).getGuildName());
                    return true;
                }
            });
            guiInventory.setItem(close,8);
            
            GUIItem kick = new GUIItem(new ItemStack(Material.ORANGE_WOOL),Main.getTitle(player,"kickMembers"));
            kick.addClickAction(new ClickAction() {
                @Override
                public boolean execute(Player player) {
                    return true;
                }
            });
            guiInventory.setItem(kick,12);

            GUIItem listMembers = new GUIItem(new ItemStack(Material.OBSERVER),Main.getTitle(player,"listMembers"));
            listMembers.addClickAction(new ClickAction() {
                @Override
                public boolean execute(Player player) {
                    player.openInventory(GUIBuilder.buildMemberListGUI(player, Bukkit.getOnlinePlayers()).getInventory());
                    return false;
                }
            });
            guiInventory.setItem(listMembers,14);
        }
        guiInventory.setItem(leave,26);
        return guiInventory;
    }

    public static PageGUIInventory buildMemberListGUI(Player player,Collection<? extends Player> players){

        PageGUIInventory guiInventory = new PageGUIInventory(18,Main.getTitle(player,"inventorys.listMembers"), PageBuilder.buildPagesFromPlayer(players));

        GUIItem previous = new GUIItem(new ItemStack(Material.RED_WOOL),Main.getTitle(player,"previous"));
        previous.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                guiInventory.previous();
                return false;
            }
        });

        GUIItem next = new GUIItem(new ItemStack(Material.GREEN_WOOL),Main.getTitle(player,"next"));
        next.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                guiInventory.next();
                return false;
            }
        });

        GUIItem back = new GUIItem(new ItemStack(Material.BARRIER),Main.getTitle(player,"back"));
        back.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(GUIBuilder.buildGuildGUI(player,Main.guildManager.isLeader(player)).getInventory());
                return false;
            }
        });

        guiInventory.setItem(previous,9);
        guiInventory.setItem(next,17);
        guiInventory.setItem(back,13);

        return guiInventory;
    }

    private static void sendMsgToGuildMembers(Player player, String msg){
        for (UUID uuid:
                Main.guildManager.getPlayerGuild(player).getMembers()) {
            Bukkit.getPlayer(uuid).sendMessage(Main.getMsg(Bukkit.getPlayer(uuid),msg));
        }
    }

}
