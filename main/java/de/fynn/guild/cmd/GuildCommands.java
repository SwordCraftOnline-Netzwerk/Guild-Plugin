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
                        if(Main.guildManager.hasInvite((Player) sender)){
                            Main.guildManager.addMember(((Player)sender).getUniqueId().toString(),Main.guildManager.getInvite((Player) sender));
                            return true;
                        }else {
                            sender.sendMessage("Du hast keine ausstehende Einladung!");
                            return true;
                        }
                    }else {
                        if(Bukkit.getPlayer(args[1])!=null){
                            if(Main.guildManager.hasGuild((Player) sender)){
                                if(Main.guildManager.isLeader((Player) sender)){
                                    if(Bukkit.getPlayer(args[1])!=sender){
                                        Main.guildManager.invite(Bukkit.getPlayer(args[1]),Main.guildManager.getPlayerGuild((Player)sender).getGuildName());
                                        return true;
                                    }else {
                                        sender.sendMessage("Du kanst dich nicht selbst einladen!");
                                        return true;
                                    }
                                }else {
                                    sender.sendMessage("Du musst Gildenleiter sein, um einen Spieler einladen zu können!");
                                    return true;
                                }
                            }else {
                                sender.sendMessage("Du musst in einer Gilde sein!");
                                return true;
                            }
                        }else {
                            sender.sendMessage("Der Spieler "+args[1]+" wurde nicht gefunden!");
                            return true;
                        }
                    }
                }else if(args[0].equals("kick")){
                    if(Main.guildManager.hasGuild((Player) sender)){
                        if(Main.guildManager.isLeader((Player) sender)){
                            if(Bukkit.getPlayer(args[1])!=null){
                                Main.guildManager.removeMember(Bukkit.getPlayer(args[1]).getUniqueId().toString(),Main.guildManager.getPlayerGuild((Player) sender).getGuildName());
                                return true;
                            }else {
                                sender.sendMessage("Der Spieler "+args[1]+" wurde nicht gefunden!");
                                return true;
                            }
                        }else {
                            sender.sendMessage("Du musst Gildenleiter sein, um einen Spieler einladen zu können!");
                            return true;
                        }
                    }else {
                        sender.sendMessage("Du musst in einer Gilde sein!");
                        return true;
                    }
                }else {
                    return false;
                }
            }else {
                sender.sendMessage("Der Befehl wurde nicht gefunden, bitte versuche folgendes:");
                return false;
            }
        }else {
            sender.sendMessage("Du musst ein Spieler sein um diesen Befehl zu benutzen!");
            return true;
        }
    }

}
