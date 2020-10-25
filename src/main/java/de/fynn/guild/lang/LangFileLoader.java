package de.fynn.guild.lang;

import de.fynn.guild.Main;
import de.fynn.guild.system.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class LangFileLoader {

    private List<Language> languages = new ArrayList<>();
    private List<String> avaiableLanguages = new ArrayList<>();
    private Language defaultLang;


    public LangFileLoader(FileHandler fileHandler){
        loadFiles(fileHandler);
    }

    private void loadFiles(FileHandler fileHandler){
        File file = new File(Main.getPlugin().getDataFolder()+"/language");
        try {
            Files.createDirectories(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        File[] langFiles = file.listFiles();
        if(langFiles==null){
            Main.getPlugin().getLogger().warning("Could not find any language file\nDisable "+Main.getPlugin().getName());
            Main.getPlugin().onDisable();
        }
        for (File f:
             langFiles) {
            avaiableLanguages.add(f.getName().substring(0,f.getName().length()-4));
            languages.add(new Language(avaiableLanguages.get(avaiableLanguages.size()-1),f));
        }
        languages.add(getLanguage(fileHandler.getDefaultLanguage()));
        defaultLang = getLanguage(fileHandler.getDefaultLanguage());
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

    public Language getDefaultLanguage(){
        return defaultLang;
    }

    public boolean containsLanguage(String language){
        return avaiableLanguages.contains(language);
    }

}
