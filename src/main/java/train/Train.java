package train;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "train_name")
    private String name;

    private String source;
    private String destination;

    @Column(name = "total_seats")
    private Integer totalSeats;

    public Train() {
    }

    public Train(String name, String source, String destination, Integer totalSeats) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
    }

    // Constructor with ID (for legacy support if needed)
    public Train(String id, String name, String source, String destination) {
        try {
            this.id = Long.parseLong(id.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            // fallback
        }
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.totalSeats = 100; // default
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public Integer getTotalSeats() { return totalSeats; }
    public void setTotalSeats(Integer totalSeats) { this.totalSeats = totalSeats; }

    @Override
    public String toString() {
        return "Train [id=" + id + ", name=" + name + ", source=" + source + ", destination=" + destination + "]";
    }
}