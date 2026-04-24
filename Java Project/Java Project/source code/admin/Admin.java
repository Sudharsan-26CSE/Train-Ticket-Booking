package admin;

import java.io.*;
import java.util.*;
import train.Train;

public class Admin {
    // hardcoded admin credentials
    private static final String ADMIN_ID = "admin";
    private static final String ADMIN_PASS = "password";

    public static boolean login(String id, String pass) {
        return ADMIN_ID.equals(id) && ADMIN_PASS.equals(pass);
    }

    // train management files
    private static final String TRAIN_FILE = "trains.txt";
    private static final String TICKET_FILE = "tickets.txt";

    public static void addTrain(Train t) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRAIN_FILE, true))) {
            bw.write(t.toString());
            bw.newLine();
        }
    }

    public static List<Train> loadTrains() throws IOException {
        List<Train> list = new ArrayList<>();
        File f = new File(TRAIN_FILE);
        if (!f.exists()) {
            System.out.println("\n[Warning] Train file not found at: " + f.getAbsolutePath());
            return list;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;
                String[] parts = line.split("\\|\\|");
                if (parts.length >= 4) {
                    list.add(new Train(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                } else {
                    System.out.println("[Warning] Skipping invalid line in trains.txt: " + line);
                }
            }
        }
        return list;
    }

    public static void showBookingDetails() throws IOException {
        File f = new File(TICKET_FILE);
        if (!f.exists()) {
            System.out.println("No tickets booked yet.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static void cancelTicket(String ticketId) throws IOException {
        File f = new File(TICKET_FILE);
        if (!f.exists())
            return;
        File temp = new File("tickets_tmp.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(f));
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(ticketId + "||")) {
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
        f.delete();
        temp.renameTo(f);
    }
}
