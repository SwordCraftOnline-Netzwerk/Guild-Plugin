package de.fynn.guild;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Guild {

    private String guildName;
    private UUID leader;
    private List<UUID> members = new ArrayList<>();

    private File file;
    private FileConfiguration guildFile;

    public Guild(File file){
        this.file = file;
        guildFile = YamlConfiguration.loadConfiguration(file);
        guildName = file.getName().substring(0,file.getName().length()-4);
        leader = UUID.fromString(guildFile.getString("Leader"));
        List<String> member = guildFile.getStringList("Member");
        for (String s:
             member) {
            members.add(UUID.fromString(s));
        }
    }

    protected boolean containsPlayer(UUID uuid){
        return (isLeader(uuid)||members.contains(uuid));
    }

    public boolean isLeader(UUID uuid){
        return uuid.equals(leader);
    }

    protected void addMember(String UUID){
        members.add(java.util.UUID.fromString(UUID));
        List<String> members = guildFile.getStringList("Member");
        members.add(UUID);
        guildFile.set("Member",members);
        try {
            guildFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void removeMember(String UUID){
        if(isLeader(java.util.UUID.fromString(UUID))){
            if(members.size()==0){
                Main.guildManager.closeGuild(guildName);
                return;
            }
            leader = members.get(0);
        }
        members.remove(java.util.UUID.fromString(UUID));
        List<String> members = guildFile.getStringList("Member");
        members.remove(UUID);
        guildFile.set("Member",members);
        try {
            guildFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getGuildName() {
        return guildName;
    }

    public List<UUID> getMembers() {
        return members;
    }

    public UUID getLeader() {
        return leader;
    }

}
