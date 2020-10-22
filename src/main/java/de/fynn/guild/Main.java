package de.fynn.guild;

import de.fynn.guild.EventHandler.EntityDeathListener;
import de.fynn.guild.EventHandler.InventoryListener;
import de.fynn.guild.EventHandler.PlayerChatListener;
import de.fynn.guild.EventHandler.PlayerInteractEntityListener;
import de.fynn.guild.cmd.CreateGuildVillager;
import de.fynn.guild.cmd.GuildCommands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class Main extends JavaPlugin {

    protected static HashMap<String,Guild> guilds = new HashMap<>();
    protected static List<UUID> villagerIDs = new ArrayList<>();

    private static Main plugin;
    protected static FileHandler fileHandler;
    protected static GuildManager guildManager = new GuildManager();

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        PluginManager plManager = Bukkit.getPluginManager();
        plManager.registerEvents(new PlayerInteractEntityListener(),this);
        plManager.registerEvents(new InventoryListener(),this);
        plManager.registerEvents(new PlayerChatListener(),this);
        plManager.registerEvents(new EntityDeathListener(),this);
        getCommand("guildVillager").setExecutor(new CreateGuildVillager());
        getCommand("guild").setExecutor(new GuildCommands());
        fileHandler = new FileHandler();
        villagerIDs = fileHandler.getVillager();
        guilds = fileHandler.loadAllGuilds();
    }

    @Override
    public void onDisable() {

    }

    public static Main getPlugin(){
        return plugin;
    }

    public static GuildManager getGuildManager() {
        return guildManager;
    }

    public static FileHandler getFileHandler() {
        return fileHandler;
    }

    public static void addVillagerID(UUID uuid){
        villagerIDs.add(uuid);
    }

    public static boolean villagerIDContains(UUID uuid){
        return villagerIDs.contains(uuid);
    }

     public static void removeVillager(UUID uuid){
        villagerIDs.remove(uuid);
     }

}
