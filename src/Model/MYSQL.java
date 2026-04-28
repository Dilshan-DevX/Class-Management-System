package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class MYSQL {
    
    private static Connection connection ;
    
    public static void createConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system","root","Dilshan@2003");
    }
    
    public static ResultSet executeSearch(String query) throws Exception{
        createConnection();
        return connection.createStatement().executeQuery(query);
    }
    
    public static Integer executeIUD(String query) throws Exception{
        createConnection();
        return connection.createStatement().executeUpdate(query);
    }
    
}
