package de.fynn.guild.cmd;

import de.fynn.guild.Main;
import de.fynn.guild.lang.MessagePlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GuildCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(args.length == 2){
                if(args[0].equals("invite")){
                    if(args[1].equals("accept")){
                        if(Main.guildManager.hasInvite((Player) sender)){
                            Main.guildManager.addMember(((Player)sender).getUniqueId().toString(),Main.guildManager.getInvite((Player) sender));
                            sender.sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("invite.inviteAccept")));
                            sender.sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("joinGuild")));
                            for (UUID uuid:
                                 Main.guildManager.getPlayerGuild((Player) sender).getMembers()) {
                                Bukkit.getPlayer(uuid).sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage(Bukkit.getPlayer(uuid)).getMessage("joinGuild")));
                            }
                            return true;
                        }else {
                            sender.sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("invite.noInvite")));
                            return true;
                        }
                    }else {
                        if(Bukkit.getPlayer(args[1])!=null){
                            if(Main.guildManager.hasGuild((Player) sender)){
                                if(Main.guildManager.isLeader((Player) sender)){
                                    if(Bukkit.getPlayer(args[1])!=sender){
                                        Main.guildManager.invite(Bukkit.getPlayer(args[1]),Main.guildManager.getPlayerGuild((Player)sender).getGuildName());
                                        Bukkit.getPlayer(args[1]).sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage(Bukkit.getPlayer(args[1])).getMessage("invite.guildInvite")));
                                        return true;
                                    }else {
                                        sender.sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("invite.noSelfInvite")));
                                        return true;
                                    }
                                }else {
                                    sender.sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("noPermission")));
                                    return true;
                                }
                            }else {
                                sender.sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("noGuild")));
                                return true;
                            }
                        }else {
                            sender.sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("playerNotFound")));
                            return true;
                        }
                    }
                }else if(args[0].equals("kick")){
                    if(Main.guildManager.hasGuild((Player) sender)){
                        if(Main.guildManager.isLeader((Player) sender)){
                            if(Bukkit.getPlayer(args[1])!=null){
                                Main.guildManager.removeMember(Bukkit.getPlayer(args[1]).getUniqueId().toString(),Main.guildManager.getPlayerGuild((Player) sender).getGuildName());
                                Bukkit.getPlayer(args[1]).sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("kickedFromGuild.player")));;
                                return true;
                            }else {
                                sender.sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("playerNotFound")));
                                return true;
                            }
                        }else {
                            sender.sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("noPermission")));
                            return true;
                        }
                    }else {
                        sender.sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("noGuild")));
                        return true;
                    }
                }else {
                    return false;
                }
            }else {
                sender.sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("commandNotFound")));
                return false;
            }
        }else {
            sender.sendMessage(MessagePlaceholder.getPlaceholder((Player) sender,Main.languageHandler.getLanguage((Player) sender).getMessage("onlyForPlayers")));
            return true;
        }
    }

}
