package reservation;

import java.sql.*;
import java.util.*;

import admin.Admin;
import train.Train;
import user.User;
import ticket.Ticket;
import payment.Payment;
import database.DatabaseConnection;

public class Reservation {
    private static Scanner sc = new Scanner(System.in);

    // The main method below was for a console-based application and conflicts with
    // the Spring Boot web server.
    // It has been removed to allow the web application to start correctly.
    // The logic from this class should be moved into Spring @Controllers and
    // @Services.

    private static void adminMenu() throws SQLException {
        System.out.print("Admin ID: ");
        String id = sc.nextLine();
        System.out.print("Password: ");
        String pw = sc.nextLine();
        if (!Admin.login(id, pw)) {
            System.out.println("Invalid details!");
            return;
        }
        while (true) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Train Management");
            System.out.println("2. Seat Management");
            System.out.println("3. Schedule Management");
            System.out.println("4. User Management");
            System.out.println("5. Booking Management");
            System.out.println("6. Edit App Details (Offers/Service/Policy)");
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            String c = sc.nextLine();

            if (c.equals("1"))
                trainManagementMenu();
            else if (c.equals("2"))
                seatManagementMenu();
            else if (c.equals("3"))
                scheduleManagementMenu();
            else if (c.equals("4"))
                Admin.viewAllUsers();
            else if (c.equals("5"))
                bookingManagementMenu();
            else if (c.equals("6"))
                editAppDetailsMenu();
            else if (c.equals("0"))
                break;
        }
    }

    private static void trainManagementMenu() throws SQLException {
        while (true) {
            System.out.println("\n--- Train Management ---");
            System.out.println("1. Add Train | 2. Update Train | 3. Delete Train | 4. View All | 0. Back");
            String c = sc.nextLine();
            if (c.equals("1")) {
                System.out.print("ID: ");
                String id = sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Src: ");
                String src = sc.nextLine();
                System.out.print("Dest: ");
                String dest = sc.nextLine();
                Admin.addTrain(new Train(id, name, src, dest));
            } else if (c.equals("2")) {
                System.out.print("ID to update: ");
                String id = sc.nextLine();
                System.out.print("New Name: ");
                String name = sc.nextLine();
                System.out.print("New Src: ");
                String src = sc.nextLine();
                System.out.print("New Dest: ");
                String dest = sc.nextLine();
                Admin.updateTrain(id, name, src, dest);
            } else if (c.equals("3")) {
                System.out.print("ID to delete: ");
                String id = sc.nextLine();
                Admin.deleteTrain(id);
            } else if (c.equals("4")) {
                Admin.loadTrains().forEach(System.out::println);
            } else if (c.equals("0"))
                break;
        }
    }

    private static void seatManagementMenu() throws SQLException {
        while (true) {
            System.out.println("\n--- Seat Management ---");
            System.out.println("1. Update Seats | 2. View All | 0. Back");
            String c = sc.nextLine();
            if (c.equals("1")) {
                System.out.print("Train ID: ");
                String tid = sc.nextLine();
                System.out.print("Class (Sleeper/3A/2A/1A): ");
                String cl = sc.nextLine();
                System.out.print("Total Seats: ");
                int total = Integer.parseInt(sc.nextLine());
                Admin.updateSeats(tid, cl, total);
            } else if (c.equals("2")) {
                Admin.viewSeats();
            } else if (c.equals("0"))
                break;
        }
    }

    private static void scheduleManagementMenu() throws SQLException {
        while (true) {
            System.out.println("\n--- Schedule Management ---");
            System.out.println("1. Add Schedule | 2. View All | 0. Back");
            String c = sc.nextLine();
            if (c.equals("1")) {
                System.out.print("Train ID: ");
                String tid = sc.nextLine();
                System.out.print("Dep Time: ");
                String dep = sc.nextLine();
                System.out.print("Arr Time: ");
                String arr = sc.nextLine();
                System.out.print("Date (YYYY-MM-DD): ");
                String date = sc.nextLine();
                Admin.addSchedule(tid, dep, arr, date);
            } else if (c.equals("2")) {
                Admin.viewSchedules();
            } else if (c.equals("0"))
                break;
        }
    }

    private static void bookingManagementMenu() throws SQLException {
        while (true) {
            System.out.println("\n--- Booking Management ---");
            System.out.println("1. View All Bookings | 2. Cancel Ticket | 0. Back");
            String c = sc.nextLine();
            if (c.equals("1"))
                Admin.showBookingDetails();
            else if (c.equals("2")) {
                System.out.print("Ticket ID: ");
                String tid = sc.nextLine();
                Admin.cancelTicket(tid);
            } else if (c.equals("0"))
                break;
        }
    }

    private static void editAppDetailsMenu() throws SQLException {
        while (true) {
            System.out.println("\n--- Edit App Details ---");
            System.out.println("1. Edit Offers | 2. Edit Customer Service | 3. Edit Privacy Policy | 0. Back");
            String c = sc.nextLine();
            if (c.equals("1")) {
                System.out.print("New Offers: ");
                Admin.updateAppDetail("offers", sc.nextLine());
            } else if (c.equals("2")) {
                System.out.print("New Service Info: ");
                Admin.updateAppDetail("customer_service", sc.nextLine());
            } else if (c.equals("3")) {
                System.out.print("New Privacy Policy: ");
                Admin.updateAppDetail("privacy_policy", sc.nextLine());
            } else if (c.equals("0"))
                break;
        }
    }

    private static void userMenu() throws SQLException {
        System.out.println("\n-- User Section -- 1. New user | 2. Existing user | 0. Back");
        String c = sc.nextLine();
        User u = null;
        if (c.equals("1"))
            u = registerFlow();
        else if (c.equals("2"))
            u = loginFlow();
        if (u != null)
            userActions(u);
    }

    private static User registerFlow() throws SQLException {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pw = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Mobile: ");
        String mobile = sc.nextLine();
        System.out.print("Payment Details: ");
        String payment = sc.nextLine();
        return User.register(email, pw, name, mobile, payment);
    }

    private static User loginFlow() throws SQLException {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pw = sc.nextLine();
        return User.login(email, pw);
    }

    private static void userActions(User u) throws SQLException {
        while (true) {
            System.out.println("\n-- Welcome " + u.getName() + " --");
            System.out.println("1. Book Ticket | 2. Ticket History | 3. Offers | 4. Support | 0. Logout");
            String c = sc.nextLine();
            if (c.equals("1"))
                bookTicketFlow(u);
            else if (c.equals("2"))
                showTicketDetails(u);
            else if (c.equals("3"))
                System.out.println("Offers: " + Admin.getAppDetail("offers"));
            else if (c.equals("4"))
                System.out.println("Support: " + Admin.getAppDetail("customer_service"));
            else if (c.equals("0"))
                break;
        }
    }

    private static void bookTicketFlow(User u) throws SQLException {
        System.out.println("\n-- Book Ticket --");
        System.out.print("Source: ");
        String src = sc.nextLine();
        System.out.print("Destination: ");
        String dest = sc.nextLine();
        List<Train> trains = Admin.loadTrains();
        trains.removeIf(t -> !t.getSource().equalsIgnoreCase(src) || !t.getDestination().equalsIgnoreCase(dest));
        if (trains.isEmpty()) {
            System.out.println("No trains found.");
            return;
        }
        for (int i = 0; i < trains.size(); i++)
            System.out.println((i + 1) + ". " + trains.get(i).getName());
        int idx = Integer.parseInt(sc.nextLine()) - 1;
        Train t = trains.get(idx);

        System.out.print("Travel Class (Sleeper/3A/2A/1A): ");
        String cl = sc.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        String ticketId = "T" + System.currentTimeMillis();
        double price = 500.0; // Mock price
        if (Payment.processPayment(price, u.getId(), ticketId)) {
            new Ticket(ticketId, u.getId(), String.valueOf(t.getId()), "General", date, cl, "10:00 AM", price, price * 0.05, "None")
                    .save();
            System.out.println("Ticket Booked! ID: " + ticketId);
        }
    }

    private static void showTicketDetails(User u) throws SQLException {
        System.out.println("\n-- Your Bookings --");
        String sql = "SELECT * FROM tickets WHERE user_id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.err.println("Error: Could not establish database connection.");
            return;
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, u.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Ticket: " + rs.getString("ticket_id") + " | Train: " + rs.getString("train_id") +
                        " | Status: " + rs.getString("status"));
            }
        }
    }
}
