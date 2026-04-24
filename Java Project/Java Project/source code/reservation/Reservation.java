package reservation;

import java.io.*;
import java.util.*;

import admin.Admin;
import train.Train;
import user.User;
import ticket.Ticket;
import payment.Payment;

public class Reservation {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("=== Reservation System ===");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine();
            if (choice.equals("1")) {
                adminMenu();
            } else if (choice.equals("2")) {
                userMenu();
            } else if (choice.equals("0")) {
                break;
            }
        }
        System.out.println("Thank you for using the reservation system!");
    }

    private static void adminMenu() throws IOException {
        System.out.print("Admin ID: ");
        String id = sc.nextLine();
        System.out.print("Password: ");
        String pw = sc.nextLine();
        if (!Admin.login(id, pw)) {
            System.out.println("Invalid details!");
            return;
        }
        System.out.println("Welcome Admin");
        while (true) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Dashboard");
            System.out.println("2. Train Management");
            System.out.println("3. Seat Management");
            System.out.println("4. Schedule Management");
            System.out.println("5. User Management");
            System.out.println("6. Booking Management");
            System.out.println("7. Notifications");
            System.out.println("8. Edit Details");
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            String c = sc.nextLine();

            if (c.equals("1"))
                adminDashboard();
            else if (c.equals("2"))
                trainManagementMenu();
            else if (c.equals("3"))
                seatManagementMenu();
            else if (c.equals("4"))
                scheduleManagementMenu();
            else if (c.equals("5"))
                userManagementMenu();
            else if (c.equals("6"))
                bookingManagementMenu();
            else if (c.equals("7"))
                notificationsMenu();
            else if (c.equals("8"))
                editDetailsMenu();
            else if (c.equals("0")) {
                System.out.println("Logged out of Admin.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminDashboard() throws IOException {
        System.out.println("\n--- Dashboard ---");
        List<Train> trains = Admin.loadTrains();
        System.out.println("Total Trains: " + trains.size());
        System.out.println("Total Bookings: (Feature Coming Soon)");
        System.out.println("Available Seats: (Feature Coming Soon)");
        System.out.println("Revenue Summary: (Feature Coming Soon)");
    }

    private static void trainManagementMenu() throws IOException {
        while (true) {
            System.out.println("\n--- Train Management ---");
            System.out.println("1. Add train");
            System.out.println("2. Edit train details");
            System.out.println("3. Delete train");
            System.out.println("4. View all trains");
            System.out.println("0. Back to Admin Menu");
            System.out.print("Choice: ");
            String c = sc.nextLine();

            if (c.equals("1"))
                addTrainFlow();
            else if (c.equals("2"))
                System.out.println("Edit train details feature coming soon!");
            else if (c.equals("3"))
                System.out.println("Delete train feature coming soon!");
            else if (c.equals("4"))
                listTrains();
            else if (c.equals("0"))
                break;
            else
                System.out.println("Invalid choice!");
        }
    }

    private static void seatManagementMenu() {
        while (true) {
            System.out.println("\n--- Seat Management ---");
            System.out.println("1. Total seats available in each train");
            System.out.println("2. Update seat availability");
            System.out.println("3. View booked seats");
            System.out.println("4. Seat class handling (General/Sleeper/AC)");
            System.out.println("0. Back to Admin Menu");
            System.out.print("Choice: ");
            String c = sc.nextLine();
            if (c.equals("0"))
                break;
            else
                System.out.println("Feature coming soon!");
        }
    }

    private static void scheduleManagementMenu() {
        while (true) {
            System.out.println("\n--- Schedule Management ---");
            System.out.println("1. Add train schedules (date-wise)");
            System.out.println("2. Modify timings");
            System.out.println("0. Back to Admin Menu");
            System.out.print("Choice: ");
            String c = sc.nextLine();
            if (c.equals("0"))
                break;
            else
                System.out.println("Feature coming soon!");
        }
    }

    private static void userManagementMenu() {
        while (true) {
            System.out.println("\n--- User Management ---");
            System.out.println("1. View all user details");
            System.out.println("2. Block/unblock users");
            System.out.println("3. Delete user accounts");
            System.out.println("4. View booking history");
            System.out.println("0. Back to Admin Menu");
            System.out.print("Choice: ");
            String c = sc.nextLine();
            if (c.equals("0"))
                break;
            else
                System.out.println("Feature coming soon!");
        }
    }

    private static void bookingManagementMenu() throws IOException {
        while (true) {
            System.out.println("\n--- Booking Management ---");
            System.out.println("1. View all bookings");
            System.out.println("2. Cancel booking");
            System.out.println("3. Refund processing");
            System.out.println("4. Filter bookings (date/train/failed)");
            System.out.println("0. Back to Admin Menu");
            System.out.print("Choice: ");
            String c = sc.nextLine();

            if (c.equals("1"))
                Admin.showBookingDetails();
            else if (c.equals("2")) {
                System.out.print("Ticket ID to cancel: ");
                String tid = sc.nextLine();
                Admin.cancelTicket(tid);
                System.out.println("Ticket cancelled successfully (if existed).");
            } else if (c.equals("3"))
                System.out.println("Refund processing coming soon!");
            else if (c.equals("4"))
                System.out.println("Filter bookings coming soon!");
            else if (c.equals("0"))
                break;
            else
                System.out.println("Invalid choice!");
        }
    }

    private static void notificationsMenu() {
        while (true) {
            System.out.println("\n--- Notifications ---");
            System.out.println("1. Send alerts to users");
            System.out.println("2. Train delay/cancellation updates");
            System.out.println("3. Offers / announcements");
            System.out.println("0. Back to Admin Menu");
            System.out.print("Choice: ");
            String c = sc.nextLine();
            if (c.equals("0"))
                break;
            else
                System.out.println("Feature coming soon!");
        }
    }

    private static void editDetailsMenu() {
        while (true) {
            System.out.println("\n--- Edit Details ---");
            System.out.println("1. Edit Offers");
            System.out.println("2. Edit Customer Service details");
            System.out.println("3. Edit Privacy Policy");
            System.out.println("0. Back to Admin Menu");
            System.out.print("Choice: ");
            String c = sc.nextLine();
            if (c.equals("0"))
                break;
            else
                System.out.println("Feature coming soon!");
        }
    }

    private static void addTrainFlow() throws IOException {
        System.out.print("Train ID: ");
        String tid = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Source: ");
        String src = sc.nextLine();
        System.out.print("Destination: ");
        String dest = sc.nextLine();
        Admin.addTrain(new Train(tid, name, src, dest));
    }

    private static void listTrains() throws IOException {
        List<Train> trains = Admin.loadTrains();
        if (trains.isEmpty()) {
            System.out.println("No trains are Available.Sorry for the inconvenience!");
        } else {
            for (Train t : trains) {
                System.out.println(t);
            }
        }
    }

    private static void userMenu() throws IOException {
        System.out.println("-- User Section --");
        System.out.println("1. New user");
        System.out.println("2. Existing user");
        System.out.print("Choice: ");
        String c = sc.nextLine();
        User u = null;
        if (c.equals("1")) {
            u = registerFlow();
        } else if (c.equals("2")) {
            u = loginFlow();
        }
        if (u != null) {
            userActions(u);
        }
    }

    private static User registerFlow() throws IOException {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pw = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Mobile Number: ");
        String mobile = sc.nextLine();
        while (mobile.trim().isEmpty()) {
            System.out.print("Mobile Number is required! Enter Mobile Number: ");
            mobile = sc.nextLine();
        }

        System.out.print("Payment Details (e.g. UPI ID or Card No): ");
        String payment = sc.nextLine();
        while (payment.trim().isEmpty()) {
            System.out.print("Payment Details are required! Enter Payment Details: ");
            payment = sc.nextLine();
        }

        User user = User.register(email, pw, name, mobile, payment);
        System.out.println("Registered with ID: " + user.getId());
        return user;
    }

    private static User loginFlow() throws IOException {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pw = sc.nextLine();
        User user = User.login(email, pw);
        if (user == null) {
            System.out.println("Login failed! Please check your credentials.");
        }
        return user;
    }

    private static void userActions(User u) throws IOException {
        while (true) {
            System.out.println("\n-- User Main Menu --");
            System.out.println("1. Book Ticket");
            System.out.println("2. Offers");
            System.out.println("3. Ticket Details & History");
            System.out.println("4. Account Details");
            System.out.println("5. Privacy Policy");
            System.out.println("6. Terms of Use");
            System.out.println("7. Customer Service");
            System.out.println("8. Logout");
            System.out.print("Choice: ");
            String c = sc.nextLine();

            if (c.equals("1")) {
                bookTicketFlow(u);
            } else if (c.equals("2")) {
                showOffers();
            } else if (c.equals("3")) {
                showTicketDetails(u);
            } else if (c.equals("4")) {
                manageAccount(u);
            } else if (c.equals("5")) {
                showPrivacyPolicy();
            } else if (c.equals("6")) {
                showTermsOfUse();
            } else if (c.equals("7")) {
                showCustomerService();
            } else if (c.equals("8")) {
                System.out.println("Logged out successfully.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void bookTicketFlow(User u) throws IOException {
        System.out.println("\n-- Book Ticket --");
        System.out.print("Source: ");
        String src = sc.nextLine().trim();
        System.out.print("Destination: ");
        String dest = sc.nextLine().trim();

        List<Train> trains = Admin.loadTrains();
        List<Train> matching = new ArrayList<>();
        for (Train t : trains) {
            if (t.getSource().toLowerCase().contains(src.toLowerCase())
                    && t.getDestination().toLowerCase().contains(dest.toLowerCase())) {
                matching.add(t);
            }
        }
        if (matching.isEmpty()) {
            System.out.println("No trains found for given route.");
            return;
        }

        System.out.println("Available trains:");
        for (int i = 0; i < matching.size(); i++) {
            System.out.println((i + 1) + ". " + matching.get(i).getName() + " (" + matching.get(i).getId() + ")");
        }
        System.out.print("Select train number: ");
        int idx;
        try {
            idx = Integer.parseInt(sc.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
            return;
        }

        if (idx < 0 || idx >= matching.size()) {
            System.out.println("Invalid selection");
            return;
        }
        Train chosen = matching.get(idx);

        System.out.println("Select Quota:");
        System.out.println("1. General\n2. Tatkal");
        System.out.print("Choice: ");
        String qc = sc.nextLine();
        String quota = qc.equals("2") ? "Tatkal" : "General";

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        System.out.println("Select Class:");
        System.out.println("1. Sleeper\n2. 3A\n3. 2A\n4. 1A");
        System.out.print("Choice: ");
        String cc = sc.nextLine();
        String travelClass = "Sleeper";
        if (cc.equals("2"))
            travelClass = "3A";
        else if (cc.equals("3"))
            travelClass = "2A";
        else if (cc.equals("4"))
            travelClass = "1A";

        System.out.print("Select Time (e.g. 08:00 AM): ");
        String time = sc.nextLine();

        double basePrice = 100.0;
        if (travelClass.equals("3A"))
            basePrice += 50;
        if (travelClass.equals("2A"))
            basePrice += 100;
        if (travelClass.equals("1A"))
            basePrice += 200;
        if (quota.equals("Tatkal"))
            basePrice += 150;

        double tax = basePrice * 0.05; // 5% tax
        double finalPrice = basePrice + tax;

        String offers = "None"; // Here you could apply discounts from Offers menu

        String ticketId = "T" + System.currentTimeMillis();
        Ticket ticket = new Ticket(ticketId, u.getId(), chosen.getId(), quota, date, travelClass, time, finalPrice, tax,
                offers);

        System.out.println("\nTicket summary:");
        System.out.println("Train: " + chosen.getName() + " | Quota: " + quota + " | Class: " + travelClass);
        System.out.println("Date: " + date + " | Time: " + time);
        System.out.println(
                "Ticket Price: $" + String.format("%.2f", basePrice) + " | Tax: $" + String.format("%.2f", tax));
        System.out.println("Total Amount: $" + String.format("%.2f", finalPrice));

        boolean paid = Payment.processPayment(finalPrice, u.getId(), ticketId);
        if (paid) {
            ticket.save();
            System.out.println("Ticket confirmed! Your Ticket ID is: " + ticketId);
        } else {
            System.out.println("Ticket booking aborted.");
        }
    }

    private static void showOffers() {
        System.out.println("\n-- Current Offers & Promotions --");
        System.out.println("* SAVE10 : Get 10% off on your next trip (Max discount $15)");
        System.out.println("* FESTIVAL5 : Flat 5% off on all General bookings");
        System.out.println("* FREQUENT15 : 15% off for our frequent travelers");
        System.out.println("Apply these codes at the payment screen (mocked for now).");
    }

    private static void showTicketDetails(User u) throws IOException {
        System.out.println("\n-- Ticket Details & History --");
        File f = new File(Ticket.TICKET_FILE);
        if (!f.exists()) {
            System.out.println("No booking history found.");
            return;
        }

        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|\\|");
                // Format: ticketId || userId || trainId || quota || bookingDate || travelClass
                // || travelTime || price || tax || offers
                if (parts.length >= 3 && parts[1].equals(u.getId())) {
                    found = true;
                    System.out.println("------------------------------------------------");
                    System.out.println("Ticket ID: " + parts[0]);
                    System.out.println("Train ID:  " + parts[2]);
                    if (parts.length >= 10) {
                        System.out.println("Booking Date: " + parts[4] + " | Time: " + parts[6]);
                        System.out.println("Class: " + parts[5] + " | Quota: " + parts[3]);
                        System.out.println("Total Paid: $" + parts[7] + " (Tax: $" + parts[8] + ")");
                        System.out.println("Applied Offers: " + parts[9]);
                    }
                }
            }
        }
        if (!found) {
            System.out.println("You have not booked any tickets yet.");
        } else {
            System.out.println("------------------------------------------------");
        }
    }

    private static void manageAccount(User u) throws IOException {
        while (true) {
            System.out.println("\n-- Account Details --");
            System.out.println("Username (ID): " + u.getId());
            System.out.println("Full Name    : " + u.getName());
            System.out.println("Email Address: " + u.getEmail());
            System.out.println("Mobile Number: " + (u.getMobileNumber().isEmpty() ? "Not Set" : u.getMobileNumber()));
            System.out
                    .println("Payment Info : " + (u.getPaymentDetails().isEmpty() ? "Not Set" : u.getPaymentDetails()));
            System.out.println("\nOptions:");
            System.out.println("1. Edit Profile");
            System.out.println("2. Change Password");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choice: ");

            String c = sc.nextLine();
            if (c.equals("1")) {
                System.out.print("New Full Name (Press Enter to skip): ");
                String name = sc.nextLine();
                if (!name.trim().isEmpty())
                    u.setName(name);

                System.out.print("New Mobile Number (Press Enter to skip): ");
                String mobile = sc.nextLine();
                if (!mobile.trim().isEmpty())
                    u.setMobileNumber(mobile);

                System.out.print("New Payment Info (Press Enter to skip): ");
                String payment = sc.nextLine();
                if (!payment.trim().isEmpty())
                    u.setPaymentDetails(payment);

                u.updateDetails();
                System.out.println("Profile updated successfully!");

            } else if (c.equals("2")) {
                System.out.print("Enter current password: ");
                String cpw = sc.nextLine();
                if (cpw.equals(u.getPassword())) {
                    System.out.print("Enter new password: ");
                    String npw = sc.nextLine();
                    u.setPassword(npw);
                    u.updateDetails();
                    System.out.println("Password changed successfully!");
                } else {
                    System.out.println("Incorrect password!");
                }
            } else if (c.equals("0")) {
                break;
            }
        }
    }

    private static void showPrivacyPolicy() {
        System.out.println("\n--- Privacy Policy ---");
        System.out.println("Your privacy is important to us. We collect personal ");
        System.out.println("information to process your train bookings securely. ");
        System.out.println("We do not sell your data to third parties. For more ");
        System.out.println("details, please visit our website.");
        System.out.println("----------------------");
    }

    private static void showTermsOfUse() {
        System.out.println("\n--- Terms of Use ---");
        System.out.println("By using this system, you agree to abide by the rules ");
        System.out.println("of the Railway Department. Ticket cancellations must be ");
        System.out.println("done 24 hours prior to departure for a full refund.");
        System.out.println("--------------------");
    }

    private static void showCustomerService() {
        System.out.println("\n--- Customer Service ---");
        System.out.println("For help and support, please reach out to us:");
        System.out.println("Phone: 1-800-TRAIN-TIX");
        System.out.println("Email: support@reservation.com");
        System.out.println("Hours: 24/7 Available");
        System.out.println("------------------------");
    }
}
