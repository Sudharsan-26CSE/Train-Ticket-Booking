package user;

import java.io.*;
import java.util.*;

public class User {
    private String id;
    private String email;
    private String password;
    private String name;
    private String mobileNumber;
    private String paymentDetails;

    private static final String USER_FILE = "users.txt";

    public User(String id, String email, String password, String name) {
        this(id, email, password, name, "", "");
    }

    public User(String id, String email, String password, String name, String mobileNumber, String paymentDetails) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.mobileNumber = mobileNumber == null ? "" : mobileNumber;
        this.paymentDetails = paymentDetails == null ? "" : paymentDetails;
    }

    public String getId() { return id; }
    
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

    public static User register(String email, String password, String name, String mobileNumber, String paymentDetails) throws IOException {
        String id = generateId();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            bw.write(id + "," + email + "," + password + "," + name + "," + mobileNumber + "," + paymentDetails);
            bw.newLine();
        }
        return new User(id, email, password, name, mobileNumber, paymentDetails);
    }
    
    public static User register(String email, String password, String name) throws IOException {
        return register(email, password, name, "", "");
    }

    private static String generateId() throws IOException {
        File f = new File(USER_FILE);
        if (!f.exists()) return "U1";
        int lines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            while (br.readLine() != null) lines++;
        }
        return "U" + (lines + 1);
    }

    public static User login(String email, String password) throws IOException {
        File f = new File(USER_FILE);
        if (!f.exists()) return null;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[1].equals(email) && parts[2].equals(password)) {
                    String mobile = parts.length > 4 ? parts[4] : "";
                    String payment = parts.length > 5 ? parts[5] : "";
                    return new User(parts[0], parts[1], parts[2], parts[3], mobile, payment);
                }
            }
        }
        return null;
    }
    
    public void updateDetails() throws IOException {
        File f = new File(USER_FILE);
        if (!f.exists()) return;
        
        File tempFile = new File("users_tmp.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(f));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
             
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(this.id)) {
                    bw.write(id + "," + email + "," + password + "," + name + "," + mobileNumber + "," + paymentDetails);
                } else {
                    bw.write(line);
                }
                bw.newLine();
            }
        }
        f.delete();
        tempFile.renameTo(f);
    }
}
