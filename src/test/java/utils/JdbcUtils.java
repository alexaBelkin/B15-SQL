package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    static Connection connection;
    static ResultSet resultSet;
    static Statement statement;

    private static Statement establishConnection(){
       try {
           connection = DriverManager.getConnection(getProp("connection_string"), getProp("username"), getProp("password"));
           statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

       }catch (SQLException exception){
          throw new RuntimeException("Could not connect DB");
       }
       return statement;
    }

    public static ResultSet queryDB(String query){
        Statement statement1 = establishConnection();
        try {
            resultSet = statement1.executeQuery(query);
            return resultSet;
        }catch (SQLException exp){
            throw new RuntimeException("Failed to execute query");
        }finally {
            closeConnection();
        }


    }
    private static String getProp(String key)  {
        Properties properties=new Properties();
        try {
            properties.load(new FileInputStream(new File("src/test/resources/database.properties")));
        }catch (IOException exception){
            throw new RuntimeException("Could not find new properties file");
        }
        return properties.getProperty(key);
    }
    private static void closeConnection(){
        try {

            if (connection != null) {
                connection.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

}
