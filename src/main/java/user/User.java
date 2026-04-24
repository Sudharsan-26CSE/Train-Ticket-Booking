package user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.sql.*;
import database.DatabaseConnection;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(name = "mobile")
    private String mobileNumber;

    @Column(name = "payment_info")
    private String paymentDetails;

    private String role = "USER";

    public User() {
    }

    public User(String id, String email, String password, String name, String mobileNumber, String paymentDetails) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.mobileNumber = mobileNumber == null ? "" : mobileNumber;
        this.paymentDetails = paymentDetails == null ? "" : paymentDetails;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    
    public String getPaymentDetails() { return paymentDetails; }
    public void setPaymentDetails(String paymentDetails) { this.paymentDetails = paymentDetails; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // Static methods for legacy support (Reservation.java)
    public static User register(String email, String password, String name, String mobileNumber, String paymentDetails) throws SQLException {
        String id = generateId();
        String sql = "INSERT INTO users (id, email, password, name, mobile, payment_info) VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, name);
            pstmt.setString(5, mobileNumber);
            pstmt.setString(6, paymentDetails);
            pstmt.executeUpdate();
        }
        return new User(id, email, password, name, mobileNumber, paymentDetails);
    }

    public static User login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("mobile"),
                        rs.getString("payment_info")
                    );
                }
            }
        }
        return null;
    }

    private static String generateId() throws SQLException {
        String sql = "SELECT COUNT(*) FROM users";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return "U" + System.currentTimeMillis();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return "U" + (rs.getInt(1) + 1);
            }
        }
        return "U1";
    }
}
