package de.fynn.guild.guild.permissions;

import java.util.ArrayList;
import java.util.List;

public class GuildRole {

    private List<GuildPermissions> permissions = new ArrayList<>();

    private String name;
    private String priority;

    public GuildRole(String roleName, String permissionData){
        name = roleName;
        char[] perm = permissionData.toCharArray();
        perm = validatePermissionData(perm);
        for (int i = 0; i < perm.length-2; i++) {
            if(perm[i] == '1'){
                switch (i){
                    case 0:
                        permissions.add(GuildPermissions.MANAGE_MEMBER);
                        break;
                    case 1:
                        permissions.add(GuildPermissions.MANAGE_GUILD);
                        break;
                    case 2:
                        permissions.add(GuildPermissions.INVITE);
                        break;
                    case 3:
                        permissions.add(GuildPermissions.KICK);
                        break;
                    case 4:
                        permissions.add(GuildPermissions.ACCEPT_REQUEST);
                        break;
                    case 5:
                        permissions.add(GuildPermissions.ROLE);
                        break;
                    case 6:
                        permissions.add(GuildPermissions.CLOSE);
                        break;
                    case 7:
                        permissions.add(GuildPermissions.MANAGE_MONEY);
                        break;
                    case 8:
                        permissions.add(GuildPermissions.MANAGE_ROLE);
                        break;
                    case 9:
                        permissions.add(GuildPermissions.MANAGE_GULDATTRIBUTES);
                        break;
                    case 10:
                        permissions.add(GuildPermissions.WAR);
                        break;
                }
            }
        }
        priority = ""+perm[11]+perm[12];
    }

    public boolean containsPermission(GuildPermissions permission){
        return permissions.contains(permission);
    }

    public String getName() {
        return name;
    }

    public void rename(String name){
        this.name = name;
    }

    public List<GuildPermissions> getPermissions(){
        return permissions;
    }

    public void editPermissionData(String permissionData){
        char[] perm = permissionData.toCharArray();
        perm = validatePermissionData(perm);
        for (int i = 0; i < perm.length-2; i++) {
            if(perm[i] == '1'){
                switch (i){
                    case 0:
                        permissions.add(GuildPermissions.MANAGE_MEMBER);
                        break;
                    case 1:
                        permissions.add(GuildPermissions.MANAGE_GUILD);
                        break;
                    case 2:
                        permissions.add(GuildPermissions.INVITE);
                        break;
                    case 3:
                        permissions.add(GuildPermissions.KICK);
                        break;
                    case 4:
                        permissions.add(GuildPermissions.ACCEPT_REQUEST);
                        break;
                    case 5:
                        permissions.add(GuildPermissions.ROLE);
                        break;
                    case 6:
                        permissions.add(GuildPermissions.CLOSE);
                        break;
                    case 7:
                        permissions.add(GuildPermissions.MANAGE_MONEY);
                        break;
                    case 8:
                        permissions.add(GuildPermissions.MANAGE_ROLE);
                        break;
                    case 9:
                        permissions.add(GuildPermissions.MANAGE_GULDATTRIBUTES);
                        break;
                    case 10:
                        permissions.add(GuildPermissions.WAR);
                        break;
                }
            }
        }
        priority = ""+perm[11]+perm[12];
    }

    public String getPriority() {
        return priority;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(permissions.contains(GuildPermissions.MANAGE_MEMBER)?1:0);
        builder.append(permissions.contains(GuildPermissions.MANAGE_GUILD)?1:0);
        builder.append(permissions.contains(GuildPermissions.INVITE)?1:0);
        builder.append(permissions.contains(GuildPermissions.KICK)?1:0);
        builder.append(permissions.contains(GuildPermissions.ACCEPT_REQUEST)?1:0);
        builder.append(permissions.contains(GuildPermissions.ROLE)?1:0);
        builder.append(permissions.contains(GuildPermissions.CLOSE)?1:0);
        builder.append(permissions.contains(GuildPermissions.MANAGE_MONEY)?1:0);
        builder.append(permissions.contains(GuildPermissions.MANAGE_ROLE)?1:0);
        builder.append(permissions.contains(GuildPermissions.MANAGE_GULDATTRIBUTES)?1:0);
        builder.append(permissions.contains(GuildPermissions.WAR)?1:0);
        builder.append(priority);
        return builder.toString();
    }

    public char[] validatePermissionData(char[] perm){
        if(perm.length<13){
            char[] fixedPerm = new char[13];
            for (int i = 0; i < 13; i++) {
                if(i==perm.length-1||i==perm.length-2){
                    fixedPerm[i==perm.length-1?13:12] = perm[i];
                }else {
                    if(i<perm.length){
                        fixedPerm[i] = perm[i];
                    }else {
                        fixedPerm[i] = '0';
                    }
                }
            }
            perm = fixedPerm;
        }
        return perm;
    }

}
