package de.fynn.guild;

import de.fynn.guild.EventHandler.*;
import de.fynn.guild.cmd.CreateGuildVillager;
import de.fynn.guild.cmd.LanguageCMD;
import de.fynn.guild.database.DBManager;
import de.fynn.guild.guild.GuildManager;
import de.fynn.guild.guild.permissions.GuildRole;
import de.fynn.guild.lang.LanguageHandler;
import de.fynn.guild.lang.MessagePlaceholder;
import de.fynn.guild.system.FileHandler;
import de.fynn.guild.system.VillagerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private static Main plugin;
    private static FileHandler fileHandler;
    public static LanguageHandler languageHandler;
    private static DBManager dbManager;
    public static GuildManager guildManager;
    public static VillagerManager villagerManager;
    public static List<Player> guildlessPlayer = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;
        fileHandler = new FileHandler();
        dbManager = new DBManager(fileHandler.getDBInfo());
        languageHandler = new LanguageHandler(fileHandler,dbManager);
        guildManager = new GuildManager(dbManager);
        villagerManager = new VillagerManager(dbManager);
        PluginManager plManager = Bukkit.getPluginManager();
        plManager.registerEvents(new PlayerInteractEntityListener(),this);
        plManager.registerEvents(new InventoryListener(),this);
        plManager.registerEvents(new PlayerChatListener(),this);
        plManager.registerEvents(new EntityDeathListener(),this);
        plManager.registerEvents(new JoinListener(languageHandler),this);
        getCommand("guildManager").setExecutor(new CreateGuildVillager());
        getCommand("guildLanguage").setExecutor(new LanguageCMD());
    }

    @Override
    public void onDisable() {

    }

    public static Main getPlugin(){
        return plugin;
    }

    public static String getMsg(Player player, String msg){
        return "§9§l[§bSwordCraftOnline§9] §r"+MessagePlaceholder.getPlaceholder(player,Main.languageHandler.getLanguage(player).getMessage(msg));
    }

    public static String getMsg(Player player, Player target, String msg){
        return "§9§l[§bSwordCraftOnline§9] §r"+MessagePlaceholder.getPlaceholder(target,Main.languageHandler.getLanguage(player).getMessage(msg));
    }

    public static String getTitle(Player player, String title){
        return  MessagePlaceholder.getPlaceholder(player,Main.languageHandler.getLanguage(player).getTitle(title));
    }

    public static String getTitle(Player player,Player target, String title){
        return  MessagePlaceholder.getPlaceholder(target,Main.languageHandler.getLanguage(player).getTitle(title));
    }

    public static DBManager getDbManager(){
        return dbManager;
    }

    public static FileHandler getFileHandler() {
        return fileHandler;
    }

}
