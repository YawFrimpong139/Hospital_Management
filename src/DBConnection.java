import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String USER = "root";
    private static final String PASSWORD = "zealow139";

    public static Connection getConnection() throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver" );
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (ClassNotFoundException e){
            throw new SQLException("My JDBC Driver cannot be found");
        }
    }

    public static void testConnection(){
        try (Connection connection = getConnection()){
            System.out.println("Database Connection made successfully");
        }catch(Exception e){
            System.err.println("Connection failed!!!");
            e.printStackTrace();
        }
    }
}
