package de.fynn.guild.system;

import de.fynn.guild.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class FileHandler {

    private FileConfiguration cfg;

    public FileHandler(){
        Main.getPlugin().saveDefaultConfig();
        cfg = Main.getPlugin().getConfig();
    }

    public String[] getDBInfo(){
        return new String[]{
                cfg.getString("MySQL.IP"),
                cfg.getString("MySQL.User"),
                cfg.getString("MySQL.Password"),
                cfg.getString("MySQL.DB_Name")};
    }

    public String getDefaultLanguage(){
        return cfg.getString("Language.Default");
    }
}
