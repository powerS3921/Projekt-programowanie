import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {

    private static String URL = "jdbc:postgresql://localhost/library";
    private static String USER = "postgres";
    private static String PASSWORD = "12345";

    public static Connection connect(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("Połączenie z bazą powiodło się!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
