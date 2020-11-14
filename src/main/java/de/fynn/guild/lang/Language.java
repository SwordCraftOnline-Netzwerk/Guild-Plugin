package de.fynn.guild.lang;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

public class Language {

    private String name;
    private HashMap<String,String> messages = new HashMap<>();
    private HashMap<String,String> titles = new HashMap<>();

    public Language(String name, File file){
        this.name = name;
        init(file);
    }

    private void init(File file){
        FileConfiguration langFile = YamlConfiguration.loadConfiguration(file);

        //Command Errors
        messages.put("commandNotFound", langFile.getString("Messages.commandErrors.commandNotFound"));
        messages.put("onlyForPlayers",langFile.getString("Messages.commandErrors.onlyForPlayers"));
        messages.put("playerNotFound",langFile.getString("Messages.commandErrors.playerNotFound"));
        messages.put("noPermission",langFile.getString("Messages.commandErrors.noPermission"));
        messages.put("noGuild", langFile.getString("Messages.commandErrors.noGuild"));
        messages.put("languageDoNotExist", langFile.getString("Messages.commandErrors.languageDoNotExist"));

        //Guild
        messages.put("guildCreated",langFile.getString("Messages.guild.guildCreated"));
        messages.put("joinGuild.player",langFile.getString("Messages.guild.joinGuild.player"));
        messages.put("joinGuild.guild",langFile.getString("Messages.guild.joinGuild.guild"));
        messages.put("leaveGuild.player",langFile.getString("Messages.guild.leaveGuild.player"));
        messages.put("leaveGuild.guild",langFile.getString("Messages.guild.leaveGuild.guild"));
        messages.put("kickedFromGuild.player",langFile.getString("Messages.guild.kickedFromGuild.player"));
        messages.put("kickedFromGuild.guild",langFile.getString("Messages.guild.kickedFromGuild.guild"));
        messages.put("invite.guildInvite",langFile.getString("Messages.guild.invite.guildInvite"));
        messages.put("invite.inviteAccept",langFile.getString("Messages.guild.invite.inviteAccept"));
        messages.put("invite.noInvite",langFile.getString("Messages.guild.invite.noInvite"));
        messages.put("guildClosed",langFile.getString("Messages.guild.guildClosed"));
        messages.put("guildCreated.askGuildName",langFile.getString("Messages.guild.askGuildName"));
        messages.put("noDoubleGuildCreate",langFile.getString("Messages.guild.noDoubleGuildCreate"));
        messages.put("askRoleName",langFile.getString("Messages.guild.askRoleName"));
        messages.put("roleCreated",langFile.getString("Messages.guild.roleCreated"));
        messages.put("roleDelete",langFile.getString("Messages.guild.roleDelete"));
        messages.put("renameRole",langFile.getString("Messages.guild.renameRole"));
        messages.put("renameRoleFinish",langFile.getString("Messages.guild.renameRoleFinish"));
        messages.put("renameGuild",langFile.getString("Messages.guild.renameGuild"));
        messages.put("renameGuildFinish",langFile.getString("Messages.guild.renameGuildFinish"));

        //titles
        titles.put("inventorys.noGuild", langFile.getString("Titles.inventorys.noGuild"));
        titles.put("inventorys.myGuild", langFile.getString("Titles.inventorys.myGuild"));
        titles.put("inventorys.inviteMember", langFile.getString("Titles.inventorys.inviteMember"));
        titles.put("inventorys.manageGuild", langFile.getString("Titles.inventorys.manageGuild"));
        titles.put("inventorys.listMembers", langFile.getString("Titles.inventorys.listMembers"));
        titles.put("inventorys.invites", langFile.getString("Titles.inventorys.invites"));
        titles.put("inventorys.manageAttributes", langFile.getString("Titles.inventorys.manageAttributes"));
        titles.put("inventorys.manageMember", langFile.getString("Titles.inventorys.manageMember"));
        titles.put("inventorys.manageRole", langFile.getString("Titles.inventorys.manageRole"));
        titles.put("inventorys.manageRoles", langFile.getString("Titles.inventorys.manageRoles"));
        titles.put("inventorys.memberRole", langFile.getString("Titles.inventorys.memberRole"));
        titles.put("inventorys.createRole", langFile.getString("Titles.inventorys.createRole"));
        titles.put("items.createGuild", langFile.getString("Titles.items.createGuild"));
        titles.put("items.previous", langFile.getString("Titles.items.previous"));
        titles.put("items.next", langFile.getString("Titles.items.next"));
        titles.put("items.getInvites", langFile.getString("Titles.items.getInvites"));
        titles.put("items.leaveGuild", langFile.getString("Titles.items.leaveGuild"));
        titles.put("items.closeGuild", langFile.getString("Titles.items.closeGuild"));
        titles.put("items.back", langFile.getString("Titles.items.back"));
        titles.put("items.kick", langFile.getString("Titles.items.kick"));
        titles.put("items.manageRole", langFile.getString("Titles.items.manageRole"));
        titles.put("items.invites", langFile.getString("Titles.items.invites"));
        titles.put("items.invite", langFile.getString("Titles.items.invite"));
        titles.put("items.manageGuild", langFile.getString("Titles.items.manageGuild"));
        titles.put("items.manageRoles", langFile.getString("Titles.items.manageRoles"));
        titles.put("items.manageAttributes", langFile.getString("Titles.items.manageAttributes"));
        titles.put("items.listMembers", langFile.getString("Titles.items.listMembers"));
        titles.put("items.increasePriority", langFile.getString("Titles.items.increasePriority"));
        titles.put("items.decreasePriority", langFile.getString("Titles.items.decreasePriority"));
        titles.put("items.saveRole", langFile.getString("Titles.items.saveRole"));
        titles.put("items.createRole", langFile.getString("Titles.items.createRole"));
        titles.put("items.deleteRole", langFile.getString("Titles.items.deleteRole"));
        titles.put("items.renameRole", langFile.getString("Titles.items.renameRole"));
        titles.put("items.renameGuild", langFile.getString("Titles.items.renameGuild"));
        titles.put("permissions.manageMembers", langFile.getString("Titles.permissions.manageMembers"));
        titles.put("permissions.manageGuild", langFile.getString("Titles.permissions.manageGuild"));
        titles.put("permissions.invite", langFile.getString("Titles.permissions.invite"));
        titles.put("permissions.kick", langFile.getString("Titles.permissions.kick"));
        titles.put("permissions.acceptRequest", langFile.getString("Titles.permissions.acceptRequest"));
        titles.put("permissions.role", langFile.getString("Titles.permissions.role"));
        titles.put("permissions.close", langFile.getString("Titles.permissions.close"));
        titles.put("permissions.manageMoney", langFile.getString("Titles.permissions.manageMoney"));
        titles.put("permissions.manageRole", langFile.getString("Titles.permissions.manageRole"));
        titles.put("permissions.manageGuildAttributes", langFile.getString("Titles.permissions.manageGuildAttributes"));
        titles.put("permissions.war", langFile.getString("Titles.permissions.war"));
    }

    public String getName(){
        return name;
    }

    public String getMessage(String message){
        return messages.get(message);
    }

    public String getTitle(String placeholder){
        return titles.get(placeholder);
    }

    public void addMessageValue(String key, String message){
        messages.put(key, message);
    }

    public void addTitleValue(String key, String title){
        titles.put(key, title);
    }

}
