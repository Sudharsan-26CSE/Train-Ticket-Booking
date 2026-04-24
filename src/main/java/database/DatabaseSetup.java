package database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseSetup {
    
    public static void initializeDatabase() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            if (conn == null) {
                System.err.println("Skipping database setup because connection could not be established.");
                return;
            }
            
            stmt = conn.createStatement();
             
            // Create users table
            String createUserTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id VARCHAR(50) PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "mobile VARCHAR(20), " +
                    "payment_info TEXT, " +
                    "role VARCHAR(20) DEFAULT 'USER', " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")";
            stmt.execute(createUserTable);
            
            // Create admin table
            String createAdminTable = "CREATE TABLE IF NOT EXISTS admin (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "admin_id VARCHAR(100) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "train_management_data TEXT, " +
                    "schedule_management_data TEXT, " +
                    "customer_feedback TEXT, " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")";
            stmt.execute(createAdminTable);
            
            // Create details table
            String createDetailsTable = "CREATE TABLE IF NOT EXISTS details (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id VARCHAR(50), " +
                    "notification TEXT, " +
                    "payment_system_details TEXT, " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE" +
                    ")";
            stmt.execute(createDetailsTable);

            // Insert default admin if not exists
            String insertAdmin = "INSERT IGNORE INTO admin (admin_id, password) VALUES ('admin', 'admin123')";
            stmt.execute(insertAdmin);

            System.out.println("Database tables created and initialized successfully.");
            
        } catch (SQLException e) {
            System.err.println("Error initializing database tables: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // Ignore
                }
            }
            // We do not close conn here because DatabaseConnection manages it statically
        }
    }
}
