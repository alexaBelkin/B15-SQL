package jdbc;

import org.junit.Assert;
import org.junit.Test;
import utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcAdv {
    @Test
    public void test1() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@codefish-database-learn.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student","codefish385");
        Statement statement =connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();
        List<Map<String,Object>> resultData=new ArrayList<>();
        while(resultSet.next()){
            Map<String,Object> rowMap=new HashMap<>();
            for (int i = 1; i <metaData.getColumnCount() ; i++) { //every next column
              rowMap.put(metaData.getColumnName(i),resultSet.getObject(i));
              resultData.add(rowMap);
            }
        }
        int expected=23000;
        for (int i = 0; i <resultData.size() ; i++) {

            if(resultData.get(i).get("FIRST_NAME").equals("TJ") &&resultData.get(i).get("LAST_NAME").equals("Olson")){
                int salary=Integer.parseInt(resultData.get(i).get("SALARY").toString());
                Assert.assertEquals( expected,salary);
                break;
            }
        }
        System.out.println(resultData.get(0).get("LAST_NAME"));
        //validate that TJ OLson makes $1400 a month

    }
    @Test
    public void test2() throws SQLException {
        ResultSet resultSet= JdbcUtils.queryDB("select * from employees");
        ResultSetMetaData metaData = resultSet.getMetaData();



    }
}
