package de.fynn.guild.cmd;

import de.fynn.guild.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class LanguageCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(args.length == 1){
                System.out.println(Main.languageHandler.getLangFileLoader().containsLanguage(args[0]));
                if(Main.languageHandler.getLangFileLoader().containsLanguage(args[0])){
                    Main.languageHandler.setLanguage((Player) sender,Main.languageHandler.getLangFileLoader().getLanguage(args[0]));
                    return true;
                }else {
                    sender.sendMessage(Main.getMsg((Player) sender,"languageDoNotExist"));
                    return true;
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
