package de.fynn.guild.EventHandler;

import de.fynn.guild.lang.LanguageHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private LanguageHandler languageHandler;

    public JoinListener(LanguageHandler languageHandler){
        this.languageHandler = languageHandler;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        languageHandler.addPlayer(event.getPlayer());
    }

}
