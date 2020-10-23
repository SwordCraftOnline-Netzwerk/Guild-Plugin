package de.fynn.guild.lang;

import de.fynn.guild.database.DBManager;
import de.fynn.guild.system.FileHandler;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LanguageHandler {

    private HashMap<Player,Language> selectedLanguage = new HashMap<>();
    private FileHandler fileHandler;
    private DBManager dbManager;
    private LangFileLoader langFileLoader = new LangFileLoader();

    public LanguageHandler(FileHandler fileHandler, DBManager dbManager) {
        this.fileHandler = fileHandler;
        this.dbManager = dbManager;
    }

    public Language getLanguage(Player player){
        return selectedLanguage.get(player);
    }

    private void setLanguage(Player player, Language language){
        selectedLanguage.replace(player, language);
        dbManager.setLanguage(player,language.getName());
    }

    public void addPlayer(Player player){
        if(dbManager.hasLanguage(player)){
            setLanguage(player,langFileLoader.getLanguage(dbManager.getLanguage(player)));
        }else {
            setLanguage(player,langFileLoader.getLanguage("default"));
            dbManager.setLanguage(player,"default");
        }
    }

}
