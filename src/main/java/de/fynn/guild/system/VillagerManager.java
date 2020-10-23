package de.fynn.guild.system;

import de.fynn.guild.database.DBManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VillagerManager {

    private List<UUID> villagers = new ArrayList<>();
    private DBManager dbManager;

    public VillagerManager(DBManager dbManager){
        this.dbManager = dbManager;
        villagers = dbManager.loadVillagers();
    }

    public boolean containsVillager(UUID uuid){
        return villagers.contains(uuid);
    }

    public void addVillager(UUID uuid){
        villagers.add(uuid);
        dbManager.addVillager(uuid.toString());
    }

    public void removeVillager(UUID uuid){
        villagers.remove(uuid);
        dbManager.removeVillager(uuid.toString());
    }

}
