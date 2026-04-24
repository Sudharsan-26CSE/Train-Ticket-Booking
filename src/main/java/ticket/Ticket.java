package ticket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    private String ticketId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "train_id")
    private String trainId;

    @Column(name = "booking_date")
    private String bookingDate;

    @Column(name = "travel_class")
    private String travelClass;

    private double price;

    private String status = "BOOKED";

    public Ticket() {
    }

    public Ticket(String ticketId, String userId, String trainId, String bookingDate, String travelClass, double price) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.trainId = trainId;
        this.bookingDate = bookingDate;
        this.travelClass = travelClass;
        this.price = price;
    }

    // Legacy constructor for Reservation.java compatibility
    public Ticket(String ticketId, String userId, String trainId, String quota, String bookingDate, 
                  String travelClass, String travelTime, double price, double tax, String stringifiedOffers) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.trainId = trainId;
        this.bookingDate = bookingDate;
        this.travelClass = travelClass;
        this.price = price;
        this.status = "BOOKED";
    }

    // Getters and Setters
    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTrainId() { return trainId; }
    public void setTrainId(String trainId) { this.trainId = trainId; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    public String getTravelClass() { return travelClass; }
    public void setTravelClass(String travelClass) { this.travelClass = travelClass; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void save() {
        // This is a dummy method to satisfy legacy code calling .save()
        // In the new system, we use TicketRepository.save()
    }
}
