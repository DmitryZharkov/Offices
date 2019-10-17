package main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        Connection conn2 = null;
        Statement stmt = null;
         try {
            // connect method #2 - network client driver
            String dbURL2 = "jdbc:derby://localhost:1527/offices;create=true";
            String user = "root";
            String password = "5yH37ij@6hw1";
            conn2 = DriverManager.getConnection(dbURL2, user, password);
            if (conn2 != null) {
                System.out.println("Connected to database #2");
            }
 
            // connect method #3 - network client driver
            String dbURL3 = "jdbc:derby://localhost:1527/offices";
            Properties properties = new Properties();
            properties.put("create", "true");
            properties.put("user", "root");
            properties.put("password", "5yH37ij@6hw1");
             
            Connection conn3 = DriverManager.getConnection(dbURL3, properties);
            if (conn3 != null) {
                System.out.println("Connected to database #3");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try
        {
        DatabaseMetaData dbm = conn2.getMetaData();
        
    ResultSet rs = dbm.getTables(null, null, "OFFICES", null);
    if (rs.next()) {
        System.out.println("Table exists");
    }
    else
    {
        System.out.println("Table does not exist");
      stmt = conn2.createStatement();
      
      String sql = "CREATE TABLE employees " +
                   "(name VARCHAR(255), " + 
                   " birth DATE, " + 
                   " mail VARCHAR(255), " +
                   " office VARCHAR(255), " +
                   " tel VARCHAR(255), " +
                   " PRIMARY KEY ( name ))"; 

      stmt.executeUpdate(sql);
      sql = "CREATE TABLE subs " +
                   "(name VARCHAR(255), " + 
                   " fio VARCHAR(255), " + 
                   " hc INTEGER not NULL, " +
                   " parent VARCHAR(255), " + 
                   " id INTEGER not NULL, " +
                   " PRIMARY KEY ( id ))"; 
      
      stmt.executeUpdate(sql);
      
      sql = "CREATE TABLE offices " +
                   "(name VARCHAR(255), " + 
                   " addr VARCHAR(255), " + 
                   " hc INTEGER not NULL, " +
                   " id INTEGER not NULL, " +
                   " PRIMARY KEY ( id ))";
      
      stmt.executeUpdate(sql);
    }
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
      
        SpringApplication.run(Application.class, args);
    }
}
