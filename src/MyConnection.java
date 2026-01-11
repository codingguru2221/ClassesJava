import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
    public static void main(String[] args) {
        try{
            String Username = "root";
            String Password = "Codex@123";
            String URL = "jdbc:mysql://localhost:3306/pext";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL,Username,Password);
            System.out.println(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
