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
                            sender.sendMessage(Main.getMsg((Player) sender,"invite.inviteAccept"));
                            sender.sendMessage(Main.getMsg((Player) sender,"joinGuild.player"));
                            for (UUID uuid:
                                 Main.guildManager.getPlayerGuild((Player) sender).getMembers()) {
                                Bukkit.getPlayer(uuid).sendMessage(Main.getMsg((Player) sender,Bukkit.getPlayer(uuid),"joinGuild.guild"));
                            }
                            return true;
                        }else {
                            sender.sendMessage(Main.getMsg((Player) sender,"invite.noInvite"));
                            return true;
                        }
                    }else {
                        if(Bukkit.getPlayer(args[1])!=null){
                            if(Main.guildManager.hasGuild((Player) sender)){
                                if(Main.guildManager.isLeader((Player) sender)){
                                    if(Bukkit.getPlayer(args[1])!=sender){
                                        Main.guildManager.invite(Bukkit.getPlayer(args[1]),Main.guildManager.getPlayerGuild((Player)sender).getGuildName());
                                        Bukkit.getPlayer(args[1]).sendMessage(Main.getMsg((Player) sender,Bukkit.getPlayer(args[1]),"invite.guildInvite"));
                                        return true;
                                    }else {
                                        sender.sendMessage(Main.getMsg((Player) sender,"invite.noSelfInvite"));
                                        return true;
                                    }
                                }else {
                                    sender.sendMessage(Main.getMsg((Player) sender,"noPermission"));
                                    return true;
                                }
                            }else {
                                sender.sendMessage(Main.getMsg((Player) sender,"noGuild"));
                                return true;
                            }
                        }else {
                            sender.sendMessage(Main.getMsg(Bukkit.getPlayer(args[1]),(Player) sender,"playerNotFound"));
                            return true;
                        }
                    }
                }else if(args[0].equals("kick")){
                    if(Main.guildManager.hasGuild((Player) sender)){
                        if(Main.guildManager.isLeader((Player) sender)){
                            if(Bukkit.getPlayer(args[1])!=null){
                                Bukkit.getPlayer(args[1]).sendMessage(Main.getMsg((Player) sender,Bukkit.getPlayer(args[1]),"kickedFromGuild.player"));
                                for (UUID uuid:
                                        Main.guildManager.getPlayerGuild((Player) sender).getMembers()) {
                                    Bukkit.getPlayer(uuid).sendMessage(Main.getMsg(Bukkit.getPlayer(args[1]),Bukkit.getPlayer(uuid),"kickedFromGuild.guild"));
                                }
                                Main.guildManager.removeMember(Bukkit.getPlayer(args[1]).getUniqueId().toString(),Main.guildManager.getPlayerGuild((Player) sender).getGuildName());
                                return true;
                            }else {
                                sender.sendMessage(Main.getMsg(Bukkit.getPlayer(args[1]),(Player) sender,"playerNotFound"));
                                return true;
                            }
                        }else {
                            sender.sendMessage(Main.getMsg((Player) sender,"noPermission"));
                            return true;
                        }
                    }else {
                        sender.sendMessage(Main.getMsg((Player) sender,"noGuild"));
                        return true;
                    }
                }else {
                    return false;
                }
            }else {
                sender.sendMessage(Main.getMsg((Player) sender,"commandNotFound"));
                return false;
            }
        }else {
            sender.sendMessage(Main.getMsg(null,"onlyForPlayers"));
            return true;
        }
    }

}
