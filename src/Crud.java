import java.awt.desktop.SystemEventListener;
import java.sql.*;
import java.util.Scanner;

public class Crud {
    private static final String URL = "jdbc:mysql://localhost:3306/pext";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "Codex@123";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    Connection conn = null;

    public Connection getConnection() {

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
            System.err.println("Connection Faild" + e);
        }
        return conn;
    }

    public void addStudent() throws SQLException {
        if (conn == null) {
            conn = getConnection();
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the user Id :");
        String UI = sc.next();
        System.out.print("Enter your name : ");
        String Name = sc.next();
        System.out.println("enter Email : ");
        String Email = sc.next();
        System.out.println("Enter mobile number  : ");
        String MOBNumber = sc.next();
        System.out.println("enter city : ");
        String City = sc.next();
        System.out.println("Enter Country : ");
        String Country = sc.next();

        String query = "insert into users(user_id,full_name,email,mobile_number,city,country) value(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, UI);
        ps.setString(2, Name);
        ps.setString(3, Email);
        ps.setString(4, MOBNumber);
        ps.setString(5, City);
        ps.setString(6, Country);

        int r = ps.executeUpdate();
        System.out.println(r == 1 ? "Registration Success" : "Registration failed ");
        ps.close();
        conn.close();
    }

    public void scarchStudent() throws SQLException {
        if (conn == null){
            conn = getConnection();
        }
        Scanner sc =  new Scanner(System.in);
        int user_id = sc.nextInt();
        String query = "select * from users where user_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,user_id);
        ResultSet set = ps.executeQuery();
        if(set.next()){
            System.out.println(set.getString("full_name"));
        }else {
            System.out.println("not found");
        }
        set.close();
        ps.close();
        conn.close();
    }

    public static void main(String[] args) throws SQLException {
        Crud crud = new Crud();
//        crud.addStudent();
        crud.scarchStudent();
    }
}
