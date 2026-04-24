package reservation;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import train.Train;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private TrainRepository trainRepository;

    @PostConstruct
    public void init() {
        long count = trainRepository.count();
        System.out.println(">>> DataInitializer: Current train count is " + count);
        if (count == 0) {
            System.out.println(">>> Seeding database with 20 trains...");
            List<Train> trains = Arrays.asList(
                new Train("kangai","kovilpatti","chennai",100),
                new Train("cholai","Thanjavur","Tenkasi",100),
                new Train("Nagercoil Coimbatore Super Fast Express","Nagercoil","Coimbatore",120),
                new Train("Mettupalayam Udagamandalam MG Passenger","Kanyakumari","Chennai Beach",90),
                new Train("Madurai Punalur Express","Madurai","Punalur",110),
                new Train("Tuticorin Mettupalayam Express","Tuticorin","Mettupalayam",100),
                new Train("Tuticorin Okha Vivek Express","Tuticorin","Okha",150),
                new Train("Chennai Egmore Madurai Tejas Express","Chennai Egmore","Madurai",200),
                new Train("Vaigai Superfast Express","Madurai","Chennai",180),
                new Train("Pallavan Superfast Express","Karaikudi","Chennai",170),
                new Train("Pandian Superfast Express","Madurai","Chennai",160),
                new Train("Pearl City Superfast Express","Tuticorin","Chennai",150),
                new Train("Nellai Superfast Express","Tirunelveli","Chennai",140),
                new Train("Kanyakumari Superfast Express","Kanyakumari","Chennai",130),
                new Train("Cheran Superfast Express","Coimbatore","Chennai",160),
                new Train("Kovai Superfast Express","Coimbatore","Chennai",150),
                new Train("Nilgiri Superfast Express","Mettupalayam","Chennai",120),
                new Train("Yercaud Express","Erode","Chennai",110),
                new Train("Rockfort Superfast Express","Tiruchirappalli","Chennai",140),
                new Train("Cholan Express","Tiruchirappalli","Chennai",130)
            );
            trainRepository.saveAll(trains);
            System.out.println(">>> 20 Trains initialized in database.");
        }
    }
}
