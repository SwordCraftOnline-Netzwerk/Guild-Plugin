package de.fynn.guild.guild;

public class Invite {

    private Guild guild;
    private String playerName;

    public Invite(String playername, Guild guild){
        this.playerName = playername;
        this.guild = guild;
    }

    public Guild getGuild() {
        return guild;
    }

    public String getPlayerName() {
        return playerName;
    }

}
