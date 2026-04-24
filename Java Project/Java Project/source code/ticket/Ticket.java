package ticket;

import java.io.*;

public class Ticket {
    private String ticketId;
    private String userId;
    private String trainId;
    private String quota;
    private String bookingDate;
    private String travelClass;
    private String travelTime;
    private double price;
    private double tax;
    private String stringifiedOffers;

    public static final String TICKET_FILE = "tickets.txt";

    public Ticket(String ticketId, String userId, String trainId) {
        this(ticketId, userId, trainId, "General", "N/A", "Sleeper", "00:00", 0.0, 0.0, "None");
    }

    public Ticket(String ticketId, String userId, String trainId, String quota, String bookingDate, 
                  String travelClass, String travelTime, double price, double tax, String stringifiedOffers) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.trainId = trainId;
        this.quota = quota;
        this.bookingDate = bookingDate;
        this.travelClass = travelClass;
        this.travelTime = travelTime;
        this.price = price;
        this.tax = tax;
        this.stringifiedOffers = stringifiedOffers;
    }

    public String getTicketId() { return ticketId; }
    public String getUserId() { return userId; }
    public String getTrainId() { return trainId; }
    public String getQuota() { return quota; }
    public String getBookingDate() { return bookingDate; }
    public String getTravelClass() { return travelClass; }
    public String getTravelTime() { return travelTime; }
    public double getPrice() { return price; }
    public double getTax() { return tax; }
    public String getStringifiedOffers() { return stringifiedOffers; }

    public void save() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TICKET_FILE, true))) {
            bw.write(this.toString());
            bw.newLine();
        }
    }

    @Override
    public String toString() {
        // use || separator as before, add extra fields
        return ticketId + "||" + userId + "||" + trainId + "||" + quota + "||" + bookingDate + "||" + 
               travelClass + "||" + travelTime + "||" + price + "||" + tax + "||" + stringifiedOffers;
    }
}
