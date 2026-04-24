package reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticket.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
}
