package de.fynn.guild.database;

import java.sql.*;

public class DBConnector {

    private Connection connection;
    private Statement statement;

    private String dbIP;
    private String user;
    private String password;

    public DBConnector(String[] dbinfo){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+(this.dbIP = dbinfo[0]+"?useSSL=false"), (this.user = dbinfo[1]), (this.password = dbinfo[2]));
            statement = connection.createStatement();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void executeSQL(String sql) {
        validateConnection();
        try {
            statement.executeUpdate(sql);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public ResultSet getData(String sql){
        validateConnection();
        try {
            return statement.executeQuery(sql);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private void validateConnection(){
        try {
            if(connection==null||connection.isClosed()){
                connection = DriverManager.getConnection("jdbc:mysql://"+dbIP+"?useSSL=false", user, password);
                statement = connection.createStatement();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
