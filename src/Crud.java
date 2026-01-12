import java.sql.*;
import java.util.Scanner;

public class Crud {

    private static final String URL = "jdbc:mysql://localhost:3306/pext";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "Codex@123";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    Connection conn = null;
    Scanner sc = new Scanner(System.in);

    // ================= CONNECTION =================
    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            }
        } catch (Exception e) {
            System.err.println("Connection Failed: " + e.getMessage());
        }
        return conn;
    }

    // ================= INSERT =================
    public void addStudent() throws SQLException {
        getConnection();

        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Full Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Mobile Number: ");
        String mobile = sc.nextLine();

        System.out.print("Enter City: ");
        String city = sc.nextLine();

        System.out.print("Enter Country: ");
        String country = sc.nextLine();

        String query = "INSERT INTO users VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, userId);
        ps.setString(2, name);
        ps.setString(3, email);
        ps.setString(4, mobile);
        ps.setString(5, city);
        ps.setString(6, country);

        int r = ps.executeUpdate();
        System.out.println(r == 1 ? "✅ Registration Successful" : "❌ Failed");

        ps.close();
    }

    // ================= SEARCH BY ID =================
    public void searchStudent() throws SQLException {
        getConnection();

        System.out.print("Enter User ID to search: ");
        int id = sc.nextInt();

        String query = "SELECT * FROM users WHERE user_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("\nUser Found:");
            System.out.println("Name: " + rs.getString("full_name"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Mobile: " + rs.getString("mobile_number"));
            System.out.println("City: " + rs.getString("city"));
            System.out.println("Country: " + rs.getString("country"));
        } else {
            System.out.println("❌ User not found");
        }

        rs.close();
        ps.close();
    }

    // ================= VIEW ALL =================
    public void viewAllStudents() throws SQLException {
        getConnection();

        String query = "SELECT * FROM users";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        System.out.println("\n---- All Users ----");
        while (rs.next()) {
            System.out.println(
                    rs.getInt("user_id") + " | " +
                            rs.getString("full_name") + " | " +
                            rs.getString("email") + " | " +
                            rs.getString("mobile_number") + " | " +
                            rs.getString("city") + " | " +
                            rs.getString("country")
            );
        }

        rs.close();
        st.close();
    }

    // ================= UPDATE =================
    public void updateStudent() throws SQLException {
        getConnection();

        System.out.print("Enter User ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Email: ");
        String email = sc.nextLine();

        System.out.print("Enter New City: ");
        String city = sc.nextLine();

        String query = "UPDATE users SET email=?, city=? WHERE user_id=?";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, email);
        ps.setString(2, city);
        ps.setInt(3, id);

        int r = ps.executeUpdate();
        System.out.println(r == 1 ? "✅ Updated Successfully" : "❌ Update Failed");

        ps.close();
    }

    // ================= DELETE =================
    public void deleteStudent() throws SQLException {
        getConnection();

        System.out.print("Enter User ID to delete: ");
        int id = sc.nextInt();

        String query = "DELETE FROM users WHERE user_id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        int r = ps.executeUpdate();
        System.out.println(r == 1 ? "✅ Deleted Successfully" : "❌ Delete Failed");

        ps.close();
    }

    // ================= MENU =================
    public void menu() throws SQLException {
        while (true) {
            System.out.println("\n====== JDBC CRUD MENU ======");
            System.out.println("1. Add User");
            System.out.println("2. Search User");
            System.out.println("3. View All Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> searchStudent();
                case 3 -> viewAllStudents();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> {
                    conn.close();
                    System.out.println("🚪 Exiting...");
                    return;
                }
                default -> System.out.println("❌ Invalid Choice");
            }
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) throws SQLException {
        Crud crud = new Crud();
        crud.menu();
    }
}
