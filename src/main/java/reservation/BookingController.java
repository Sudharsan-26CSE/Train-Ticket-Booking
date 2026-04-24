package reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticket.Ticket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmBooking(@RequestBody Ticket ticket) {
        // Generate a unique ticket ID
        String ticketId = "TKT" + System.currentTimeMillis();
        ticket.setTicketId(ticketId);
        
        // Set current date if not provided
        if (ticket.getBookingDate() == null || ticket.getBookingDate().isEmpty()) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            ticket.setBookingDate(dtf.format(LocalDateTime.now()));
        }
        
        ticket.setStatus("BOOKED");
        
        Ticket savedTicket = ticketRepository.save(ticket);
        return ResponseEntity.ok(savedTicket);
    }
}
