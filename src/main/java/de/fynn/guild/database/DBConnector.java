package de.fynn.guild.database;

import de.fynn.guild.Main;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.*;

public class DBConnector {

    private Connection connection;
    private Statement statement;

    private String dbIP;
    private String user;
    private String password;
    private BukkitScheduler scheduler = Bukkit.getScheduler();

    public DBConnector(String[] dbinfo){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+(dbIP = dbinfo[0])+"?useSSL=false", (user = dbinfo[1]), (password = dbinfo[2]));
            statement = connection.createStatement();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void executeSQL(String sql, boolean async) {
        if(async){
            scheduler.runTaskAsynchronously(Main.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    validateConnection();
                    try {
                        statement.executeUpdate(sql);
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }else {
            validateConnection();
            try {
                statement.executeUpdate(sql);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public ResultSet getData(String sql){
        validateConnection();
        try {
            return statement.executeQuery(sql);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private void validateConnection(){
        scheduler.runTaskAsynchronously(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                try {
                    if(connection==null||connection.isClosed()){
                        connection = DriverManager.getConnection("jdbc:mysql://"+dbIP+"?useSSL=false", user, password);
                        statement = connection.createStatement();
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

}
