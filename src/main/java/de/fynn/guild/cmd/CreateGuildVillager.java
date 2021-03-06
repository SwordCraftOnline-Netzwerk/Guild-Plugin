package de.fynn.guild.cmd;

import de.fynn.guild.Main;
import de.fynn.guild.lang.MessagePlaceholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CreateGuildVillager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(sender.hasPermission("guild.guildmaster")){
                if(args.length==0){
                    return false;
                }else if(args.length == 1 && args[0].equals("create")){
                    Entity villager = ((Player) sender).getWorld().spawnEntity(((Player) sender).getLocation(), EntityType.VILLAGER);
                    villager.setInvulnerable(true);
                    villager.setCustomName(Main.getFileHandler().getGuildmasterName());
                    villager.setCustomNameVisible(true);
                    ((LivingEntity)villager).setAI(false);
                    Main.villagerManager.addVillager(villager.getUniqueId());
                    return true;
                }else if(args.length == 2 && args[0].equals("create")) {
                    Entity villager = ((Player) sender).getWorld().spawnEntity(((Player) sender).getLocation(), EntityType.VILLAGER);
                    villager.setInvulnerable(true);
                    villager.setCustomName(args[1]);
                    villager.setCustomNameVisible(true);
                    ((LivingEntity)villager).setAI(false);
                    Main.villagerManager.addVillager(villager.getUniqueId());
                    return true;
                }else {
                    sender.sendMessage(Main.getMsg((Player) sender,"commandNotFound"));
                    return false;
                }
            }else {
                sender.sendMessage(Main.getMsg((Player) sender,"noPermission"));
                return true;
            }
        }else {
            sender.sendMessage(Main.getMsg(null,"onlyForPlayer"));
            return true;
        }
    }

}
