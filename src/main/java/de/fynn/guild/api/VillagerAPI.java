package de.fynn.guild.api;

import de.fynn.guild.Main;

import java.util.List;
import java.util.UUID;

public class VillagerAPI {

    public static boolean isGuildVillagers(UUID entityUUID){
        return Main.villagerManager.containsVillager(entityUUID);
    }

}
