package de.fynn.guild.guild.gui.guis;

import de.fynn.guild.EventHandler.PlayerChatListener;
import de.fynn.guild.Main;
import de.fynn.guild.guild.gui.ClickAction;
import de.fynn.guild.guild.gui.GUIInventory;
import de.fynn.guild.guild.gui.guiItems.CounterBlock;
import de.fynn.guild.guild.gui.guiItems.GUIItem;
import de.fynn.guild.guild.gui.guiItems.BackButton;
import de.fynn.guild.guild.gui.guiItems.YesNoButton;
import de.fynn.guild.guild.permissions.GuildPermissions;
import de.fynn.guild.guild.permissions.GuildRoleManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CreateRole {

    public static GUIInventory getInventory(Player player){
        GUIInventory guiInventory = new GUIInventory(27, Main.getTitle(player,"inventorys.createRole"));
        GuildRoleManager roleManager = Main.guildManager.getPlayerGuild(player).getRoleManager();
        List<YesNoButton> buttons = new ArrayList<>();

        YesNoButton perm1 = new YesNoButton(Main.getTitle(player,"permissions.manageMembers"),Material.RED_WOOL,Material.GREEN_WOOL);
        buttons.add(perm1);
        perm1.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                perm1.changeState();
                if(!perm1.getState()){
                    for (int i = 2; i < 6; i++) {
                        buttons.get(i).setState(false);
                    }
                }
                player.openInventory(guiInventory.getInventory());
                return false;
            }
        });
        if(roleManager.hasPermission(player, GuildPermissions.MANAGE_MEMBER)){
            guiInventory.setItem(perm1,0);
        }

        YesNoButton perm2 = new YesNoButton(Main.getTitle(player,"permissions.manageGuild"),Material.RED_WOOL,Material.GREEN_WOOL);
        buttons.add(perm2);
        perm2.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                perm2.changeState();
                if(!perm2.getState()){
                    for (int i = 6; i < 11; i++) {
                        buttons.get(i).setState(false);
                    }
                }
                player.openInventory(guiInventory.getInventory());
                return false;
            }
        });
        if(roleManager.hasPermission(player, GuildPermissions.MANAGE_GUILD)){
            guiInventory.setItem(perm2,1);
        }

        YesNoButton perm3 = new YesNoButton(Main.getTitle(player,"permissions.invite"),Material.RED_WOOL,Material.GREEN_WOOL);
        buttons.add(perm3);
        perm3.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                perm3.changeState();
                buttons.get(0).setState(true);
                player.openInventory(guiInventory.getInventory());
                return false;
            }
        });
        if(roleManager.hasPermission(player, GuildPermissions.INVITE)){
            guiInventory.setItem(perm3,2);
        }

        YesNoButton perm4 = new YesNoButton(Main.getTitle(player,"permissions.kick"),Material.RED_WOOL,Material.GREEN_WOOL);
        buttons.add(perm4);
        perm4.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                perm4.changeState();
                buttons.get(0).setState(true);
                player.openInventory(guiInventory.getInventory());
                return false;
            }
        });
        if(roleManager.hasPermission(player, GuildPermissions.KICK)){
            guiInventory.setItem(perm4,3);
        }

        YesNoButton perm5 = new YesNoButton(Main.getTitle(player,"permissions.acceptRequest"),Material.RED_WOOL,Material.GREEN_WOOL);
        buttons.add(perm5);
        perm5.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                perm5.changeState();
                buttons.get(0).setState(true);
                player.openInventory(guiInventory.getInventory());
                return false;
            }
        });
        if(roleManager.hasPermission(player, GuildPermissions.ACCEPT_REQUEST)){
            guiInventory.setItem(perm5,4);
        }

        YesNoButton perm6 = new YesNoButton(Main.getTitle(player,"permissions.role"),Material.RED_WOOL,Material.GREEN_WOOL);
        buttons.add(perm6);
        perm6.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                perm6.changeState();
                buttons.get(0).setState(true);
                player.openInventory(guiInventory.getInventory());
                return false;
            }
        });
        if(roleManager.hasPermission(player, GuildPermissions.ROLE)){
            guiInventory.setItem(perm6,5);
        }

        YesNoButton perm7 = new YesNoButton(Main.getTitle(player,"permissions.close"),Material.RED_WOOL,Material.GREEN_WOOL);
        buttons.add(perm7);
        perm7.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                perm7.changeState();
                buttons.get(1).setState(true);
                player.openInventory(guiInventory.getInventory());
                return false;
            }
        });
        if(roleManager.hasPermission(player, GuildPermissions.CLOSE)){
            guiInventory.setItem(perm7,9);
        }

        YesNoButton perm8 = new YesNoButton(Main.getTitle(player,"permissions.manageMoney"),Material.RED_WOOL,Material.GREEN_WOOL);
        buttons.add(perm8);
        perm8.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                perm8.changeState();
                buttons.get(1).setState(true);
                player.openInventory(guiInventory.getInventory());
                return false;
            }
        });
        if(roleManager.hasPermission(player, GuildPermissions.MANAGE_MONEY)){
            guiInventory.setItem(perm8,10);
        }

        YesNoButton perm9 = new YesNoButton(Main.getTitle(player,"permissions.manageRole"),Material.RED_WOOL,Material.GREEN_WOOL);
        buttons.add(perm9);
        perm9.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                perm9.changeState();
                buttons.get(1).setState(true);
                player.openInventory(guiInventory.getInventory());
                return false;
            }
        });
        if(roleManager.hasPermission(player, GuildPermissions.MANAGE_ROLE)){
            guiInventory.setItem(perm9,11);
        }

        YesNoButton perm10 = new YesNoButton(Main.getTitle(player,"permissions.manageGuildAttributes"),Material.RED_WOOL,Material.GREEN_WOOL);
        buttons.add(perm10);
        perm10.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                perm10.changeState();
                buttons.get(1).setState(true);
                player.openInventory(guiInventory.getInventory());
                return false;
            }
        });
        if(roleManager.hasPermission(player, GuildPermissions.MANAGE_GULDATTRIBUTES)){
            guiInventory.setItem(perm10,12);
        }

        YesNoButton perm11 = new YesNoButton(Main.getTitle(player,"permissions.war"),Material.RED_WOOL,Material.GREEN_WOOL);
        buttons.add(perm11);
        perm11.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                perm11.changeState();
                buttons.get(1).setState(true);
                player.openInventory(guiInventory.getInventory());
                return false;
            }
        });
        if(roleManager.hasPermission(player, GuildPermissions.WAR)){
            guiInventory.setItem(perm11,13);
        }

        CounterBlock higher = new CounterBlock(Main.getTitle(player,"items.increasePriority"),true);
        CounterBlock lower = new CounterBlock(Main.getTitle(player,"items.decreasePriority"),false);

        higher.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                if(Integer.parseInt(roleManager.getRole(player).getPriority())<higher.getValue()){
                    if(higher.getValue()<99){
                        higher.changeValue();
                        higher.synchroValue(lower);
                    }
                }
                return false;
            }
        });
        guiInventory.setItem(higher,7);
        lower.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                if(lower.getValue()>0){
                    lower.changeValue();
                    lower.synchroValue(higher);
                }
                return false;
            }
        });
        guiInventory.setItem(lower,16);

        GUIItem back = new BackButton(player);
        back.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                player.openInventory(ManageRoles.getInventory(player).getInventory());
                return false;
            }
        });
        guiInventory.setItem(back,26);

        GUIItem finish = new GUIItem(new ItemStack(Material.GREEN_CONCRETE),Main.getTitle(player,"items.createRole"));
        finish.addClickAction(new ClickAction() {
            @Override
            public boolean execute(Player player) {
                StringBuilder builder = new StringBuilder();
                for (YesNoButton button:
                     buttons) {
                    builder.append(button.getState()?1:0);
                }
                builder.append(higher.getValue()<10?"0"+higher.getValue():higher.getValue());
                PlayerChatListener.observedPlayerRole.put(player, builder.toString());
                return true;
            }
        });
        guiInventory.setItem(finish,18);

        return guiInventory;
    }

}
