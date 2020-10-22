package de.fynn.guild.cmd;

import de.fynn.guild.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(args.length == 2){
                if(args[0].equals("invite")){
                    if(args[1].equals("accept")){
                        if(Main.getGuildManager().hasInvite((Player) sender)){
                            Main.getGuildManager().addMember(((Player)sender).getUniqueId().toString(),Main.getGuildManager().getInvite((Player) sender));
                            return true;
                        }else {
                            sender.sendMessage("%NoInvite%");
                            return true;
                        }
                    }else {
                        if(Bukkit.getPlayer(args[1])!=null){
                            if(Main.getGuildManager().hasGuild((Player) sender)){
                                if(Main.getGuildManager().isLeader((Player) sender)){
                                    if(Bukkit.getPlayer(args[1])!=sender){
                                        Main.getGuildManager().invite(Bukkit.getPlayer(args[1]),Main.getGuildManager().getPlayerGuild((Player)sender).getGuildName());
                                        return true;
                                    }else {
                                        sender.sendMessage("%NoSelfInvite%");
                                        return true;
                                    }
                                }else {
                                    sender.sendMessage("%NotLeader%");
                                    return true;
                                }
                            }else {
                                sender.sendMessage("%NoGuild%");
                                return true;
                            }
                        }else {
                            sender.sendMessage("%PlayerNotFound%");
                            return true;
                        }
                    }
                }else if(args[0].equals("kick")){
                    if(Main.getGuildManager().hasGuild((Player) sender)){
                        if(Main.getGuildManager().isLeader((Player) sender)){
                            if(Bukkit.getPlayer(args[1])!=null){
                                Main.getGuildManager().removeMember(Bukkit.getPlayer(args[1]).getUniqueId().toString(),Main.getGuildManager().getPlayerGuild((Player) sender).getGuildName());
                                return true;
                            }else {
                                sender.sendMessage("%PlayerNotFound%");
                                return true;
                            }
                        }else {
                            sender.sendMessage("%NotLeader%");
                            return true;
                        }
                    }else {
                        sender.sendMessage("%NoGuild%");
                        return true;
                    }
                }else {
                    return false;
                }
            }else {
                sender.sendMessage("%CMDNotFound%");
                return false;
            }
        }else {
            sender.sendMessage("%NoPlayer%");
            return true;
        }
    }

}
