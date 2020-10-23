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
                cfg.getString("Database.IP"),
                cfg.getString("Database.User"),
                cfg.getString("Database.Password")};
    }
}
