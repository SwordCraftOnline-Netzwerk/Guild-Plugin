package de.fynn.guild.lang;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LangFileLoader {

    private List<Language> languages = new ArrayList<>();
    private List<String> avaiableLanguages = new ArrayList<>();

    public LangFileLoader(){
        loadFiles();
    }

    private void loadFiles(){
        //Load Files, convert into Language Objects and put them in the languages and avaiableLanguages  list
    }

    public List<String> avaiableLanguages(){
        return avaiableLanguages;
    }

    public Language getLanguage(String language){
        for (Language lang:
             languages) {
            if(lang.getName().equals(language)){
                return lang;
            }
        }
        return null;
    }

    public boolean containsLanguage(String language){
        return avaiableLanguages.contains(language);
    }

}
