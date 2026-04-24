package admin;

import java.sql.*;
import java.util.*;
import train.Train;
import database.DatabaseConnection;

public class Admin {
    // hardcoded admin credentials
    private static final String ADMIN_ID = "admin";
    private static final String ADMIN_PASS = "password";

    public static boolean login(String id, String pass) {
        return ADMIN_ID.equals(id) && ADMIN_PASS.equals(pass);
    }

    // --- Train Management ---
    public static void addTrain(Train t) throws SQLException {
        String sql = "INSERT INTO trains (id, name, source, destination) VALUES (?, ?, ?, ?)";
        executePreparedUpdate(sql, t.getId(), t.getName(), t.getSource(), t.getDestination());
    }

    public static void updateTrain(String id, String name, String source, String destination) throws SQLException {
        String sql = "UPDATE trains SET name = ?, source = ?, destination = ? WHERE id = ?";
        executePreparedUpdate(sql, name, source, destination, id);
    }

    public static void deleteTrain(String id) throws SQLException {
        String sql = "DELETE FROM trains WHERE id = ?";
        executePreparedUpdate(sql, id);
    }

    public static List<Train> loadTrains() throws SQLException {
        List<Train> list = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.err.println("Error: Could not establish database connection.");
            return list;
        }
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM trains")) {
            while (rs.next()) {
                list.add(new Train(rs.getString("id"), rs.getString("name"), rs.getString("source"), rs.getString("destination")));
            }
        }
        return list;
    }

    // --- Seat Management ---
    public static void updateSeats(String trainId, String classType, int total) throws SQLException {
        String sql = "INSERT INTO seats (train_id, class_type, total_seats, available_seats) VALUES (?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE total_seats = ?, available_seats = ?";
        executePreparedUpdate(sql, trainId, classType, total, total, total, total);
    }

    public static void viewSeats() throws SQLException {
        String sql = "SELECT * FROM seats";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.err.println("Error: Could not establish database connection.");
            return;
        }
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Train ID | Class | Total | Available");
            while (rs.next()) {
                System.out.println(rs.getString("train_id") + " | " + rs.getString("class_type") + " | " + 
                                   rs.getInt("total_seats") + " | " + rs.getInt("available_seats"));
            }
        }
    }

    // --- Schedule Management ---
    public static void addSchedule(String trainId, String dep, String arr, String date) throws SQLException {
        String sql = "INSERT INTO schedules (train_id, departure_time, arrival_time, travel_date) VALUES (?, ?, ?, ?)";
        executePreparedUpdate(sql, trainId, dep, arr, date);
    }

    public static void viewSchedules() throws SQLException {
        String sql = "SELECT * FROM schedules";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.err.println("Error: Could not establish database connection.");
            return;
        }
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ID | Train ID | Departure | Arrival | Date");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("train_id") + " | " + 
                                   rs.getString("departure_time") + " | " + rs.getString("arrival_time") + " | " + rs.getString("travel_date"));
            }
        }
    }

    // --- User Management ---
    public static void viewAllUsers() throws SQLException {
        String sql = "SELECT id, email, name, mobile FROM users";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.err.println("Error: Could not establish database connection.");
            return;
        }
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ID | Name | Email | Mobile");
            while (rs.next()) {
                System.out.println(rs.getString("id") + " | " + rs.getString("name") + " | " + 
                                   rs.getString("email") + " | " + rs.getString("mobile"));
            }
        }
    }

    // --- Booking Management ---
    public static void showBookingDetails() throws SQLException {
        String sql = "SELECT * FROM tickets";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.err.println("Error: Could not establish database connection.");
            return;
        }
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("ticket_id") + " | User: " + rs.getString("user_id") + " | Train: " + rs.getString("train_id") + 
                                   " | Class: " + rs.getString("travel_class") + " | Status: " + rs.getString("status"));
            }
        }
    }

    public static void cancelTicket(String ticketId) throws SQLException {
        String sql = "UPDATE tickets SET status = 'CANCELLED' WHERE ticket_id = ?";
        executePreparedUpdate(sql, ticketId);
    }

    // --- App Details Management (Edit Details) ---
    public static void updateAppDetail(String key, String value) throws SQLException {
        String sql = "INSERT INTO app_details (detail_key, detail_value) VALUES (?, ?) ON DUPLICATE KEY UPDATE detail_value = ?";
        executePreparedUpdate(sql, key, value, value);
    }

    public static String getAppDetail(String key) throws SQLException {
        String sql = "SELECT detail_value FROM app_details WHERE detail_key = ?";
        Connection conn = DatabaseConnection.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, key);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getString("detail_value");
            }
        }
        return "Not available";
    }

    // Utility
    private static void executePreparedUpdate(String sql, Object... params) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
        }
    }
}
