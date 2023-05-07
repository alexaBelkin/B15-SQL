package jdbc;

import org.junit.Test;

import java.sql.*;

public class JdbcIntro {
    @Test
    public void connectionTest() throws SQLException {

        //filling up the connection
        //jdbc:oracle:thin: -->calls oracle driver to connect db
        //we have to declare it an SQLException
        Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@codefish-database-learn.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student","codefish385");
        //TYPE_SCROLL_INSENSITIVE-->searching anywhere


        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet=statement.executeQuery("select * from employees");
        resultSet.next();
        System.out.println(resultSet.getString(1));
        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+" "
                    +resultSet.getString(2)+" "
                    +resultSet.getString(3));
        }

    }
    @Test
    public void queryTest() throws SQLException {
        Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@codefish-database-learn.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student","codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet=statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()){ //iterating over rows in the table
            System.out.print("| ");
            for (int i = 1; i <=metaData.getColumnCount() ; i++) {//iterating over columns of each rows
                System.out.print(resultSet.getObject(i) +" |" );
            }
            System.out.println();
        }


    }
}
