package de.fynn.guild.api;

import de.fynn.guild.Main;
import de.fynn.guild.lang.Language;
import org.bukkit.entity.Player;

public class LanguageAPI {

    public String getLanguage(Player player){
        return Main.languageHandler.getLanguage(player).toString();
    }

    public Language getLanguageObject(Player player){
        return Main.languageHandler.getLanguage(player);
    }

}
