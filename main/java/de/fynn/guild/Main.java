package de.fynn.guild;

import de.fynn.guild.EventHandler.EntityDeathListener;
import de.fynn.guild.EventHandler.InventoryListener;
import de.fynn.guild.EventHandler.PlayerChatListener;
import de.fynn.guild.EventHandler.PlayerInteractEntityListener;
import de.fynn.guild.cmd.CreateGuildVillager;
import de.fynn.guild.cmd.GuildCommands;
import de.fynn.guild.database.DBManager;
import de.fynn.guild.guild.Guild;
import de.fynn.guild.guild.GuildManager;
import de.fynn.guild.system.FileHandler;
import de.fynn.guild.system.VillagerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private static Main plugin;
    private static FileHandler fileHandler;
    private static DBManager dbManager;
    public static GuildManager guildManager;
    public static VillagerManager villagerManager;

    @Override
    public void onEnable() {
        plugin = this;

        fileHandler = new FileHandler();
        dbManager = new DBManager(fileHandler.getDBInfo());
        guildManager = new GuildManager(dbManager);
        villagerManager = new VillagerManager(dbManager);

        PluginManager plManager = Bukkit.getPluginManager();
        plManager.registerEvents(new PlayerInteractEntityListener(),this);
        plManager.registerEvents(new InventoryListener(),this);
        plManager.registerEvents(new PlayerChatListener(),this);
        plManager.registerEvents(new EntityDeathListener(),this);
        getCommand("guildVillager").setExecutor(new CreateGuildVillager());
        getCommand("guild").setExecutor(new GuildCommands());
    }

    @Override
    public void onDisable() {

    }

    public static Main getPlugin(){
        return plugin;
    }

}
