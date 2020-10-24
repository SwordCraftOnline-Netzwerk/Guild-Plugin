package de.fynn.guild.lang;

import de.fynn.guild.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

public class Language {

    private String name;
    private HashMap<String,String> messages = new HashMap<>();

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
        messages.put("invite.noSelfInvite",langFile.getString("Messages.guild.invite.noSelfInvite"));
        messages.put("invite.noInvite",langFile.getString("Messages.guild.invite.noInvite"));
        messages.put("guildClosed",langFile.getString("Messages.guild.guildClosed"));
        messages.put("guildCreated.askGuildName",langFile.getString("Messages.guild.guildCreated.askGuildName"));
    }

    public String getName(){
        return name;
    }

    public String getMessage(String message){
        return messages.get(message);
    }

}