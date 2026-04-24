package payment;

import java.io.*;
import java.util.Scanner;

public class Payment {
    private static final String PAYMENT_FILE = "payments.txt";

    public static boolean processPayment(double amount, String userId, String ticketId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Payment Section ---");
        System.out.println("Total amount due: $" + String.format("%.2f", amount));
        System.out.println("Select Payment Method:");
        System.out.println("1. UPI");
        System.out.println("2. Card");
        System.out.print("Choice: ");
        
        String choice = sc.nextLine();
        String method = "";
        String details = "";

        if (choice.equals("1")) {
            method = "UPI";
            System.out.print("Enter UPI ID (e.g. user@bank): ");
            details = sc.nextLine();
            while (details.trim().isEmpty()) {
                System.out.print("UPI ID is required! Enter UPI ID: ");
                details = sc.nextLine();
            }
            System.out.println("Sending payment link to " + details + "...");
            System.out.println("Processing payment via UPI...");
            
        } else if (choice.equals("2")) {
            method = "Card";
            System.out.print("Enter Card Number: ");
            String cardNo = sc.nextLine();
            while (cardNo.trim().isEmpty()) {
                System.out.print("Card number is required! Enter Card Number: ");
                cardNo = sc.nextLine();
            }
            System.out.print("Enter Expiry Date (MM/YY): ");
            String exp = sc.nextLine();
            System.out.print("Enter CVV: ");
            String cvv = sc.nextLine();
            details = cardNo + " (Exp: " + exp + ", CVV: " + cvv + ")";
            System.out.println("Contacting bank for card ending in " + (cardNo.length() >= 4 ? cardNo.substring(cardNo.length() - 4) : cardNo) + "...");
            System.out.println("Processing payment via Card...");
        } else {
            System.out.println("Invalid Payment Method Selection. Payment Failed.");
            return false;
        }

        System.out.println("Payment successful!");
        String txnId = "TXN" + System.currentTimeMillis();
        
        // Save to separate text file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PAYMENT_FILE, true))) {
            bw.write(txnId + "||" + userId + "||" + ticketId + "||" + amount + "||" + method + "||" + details);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving payment details: " + e.getMessage());
        }

        return true;
    }
}
