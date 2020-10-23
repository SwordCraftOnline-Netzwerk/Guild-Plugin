package de.fynn.guild.guild;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Guild {

    private String guildName;
    private UUID leader;
    private List<UUID> members = new ArrayList<>();

    public Guild(String name, UUID leader, List<UUID> members){
        guildName = name;
        this.leader = leader;
        this.members = members;
    }

    protected boolean containsPlayer(UUID uuid){
        return (isLeader(uuid)||members.contains(uuid));
    }

    public boolean isLeader(UUID uuid){
        return uuid.equals(leader);
    }

    protected void addMember(String UUID){
        members.add(java.util.UUID.fromString(UUID));
    }

    protected boolean removeMember(String UUID){
        if(isLeader(java.util.UUID.fromString(UUID))){
            if(members.size()==0){
                return false;
            }
            leader = members.get(0);
        }
        members.remove(java.util.UUID.fromString(UUID));
        return true;
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
