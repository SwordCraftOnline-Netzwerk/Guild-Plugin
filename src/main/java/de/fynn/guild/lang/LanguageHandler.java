package de.fynn.guild.lang;

import de.fynn.guild.database.DBManager;
import de.fynn.guild.system.FileHandler;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LanguageHandler {

    private HashMap<Player,Language> selectedLanguage = new HashMap<>();
    private DBManager dbManager;
    private LangFileLoader langFileLoader;

    public LanguageHandler(FileHandler fileHandler, DBManager dbManager) {
        this.dbManager = dbManager;
        langFileLoader = new LangFileLoader(fileHandler);
    }

    public Language getLanguage(Player player){
        return selectedLanguage.get(player);
    }

    public LangFileLoader getLangFileLoader(){
        return langFileLoader;
    }

    public void setLanguage(Player player, Language language){
        if(selectedLanguage.containsKey(player)){
            selectedLanguage.replace(player, language);
        }else {
            selectedLanguage.put(player, language);
        }
        dbManager.setLanguage(player,language.getName());
    }

    public void addPlayer(Player player){
        if(dbManager.hasLanguage(player)){
            setLanguage(player,langFileLoader.getLanguage(dbManager.getLanguage(player).equals("default")? langFileLoader.getDefaultLanguage().getName() : dbManager.getLanguage(player)));
        }else {
            setLanguage(player,langFileLoader.getDefaultLanguage());
            dbManager.setLanguage(player,"default");
        }
    }

}
