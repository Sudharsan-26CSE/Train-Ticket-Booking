import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckDB {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://127.0.0.1:3306/train_reservation_system";
        String USER = "root";
        String PASS = "Newpassword@123";
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = conn.createStatement();

            System.out.println("==== ADMIN TABLE ====");
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin");
            while (rs.next()) {
                System.out
                        .println("Admin ID: " + rs.getString("admin_id") + " | Password: " + rs.getString("password"));
            }

            System.out.println("\n==== USER TABLE ====");
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM user");
            if (!rs2.isBeforeFirst()) {
                System.out.println("(No users found)");
            }
            while (rs2.next()) {
                System.out.println("ID: " + rs2.getInt("id") + " | Username: " + rs2.getString("username")
                        + " | Email: " + rs2.getString("email"));
            }

            System.out.println("\n==== DETAILS TABLE ====");
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM details");
            if (!rs3.isBeforeFirst()) {
                System.out.println("(No details found)");
            }
            while (rs3.next()) {
                System.out.println("ID: " + rs3.getInt("id") + " | User ID: " + rs3.getInt("user_id")
                        + " | Notification: " + rs3.getString("notification"));
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Could not connect to database: " + e.getMessage());
        }
    }
}
