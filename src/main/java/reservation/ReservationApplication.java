package reservation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import database.DatabaseSetup;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
@EntityScan(basePackages = {"reservation", "train", "user", "ticket"})
@ComponentScan(basePackages = {"reservation", "train", "database", "user", "ticket"})
public class ReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservationApplication.class, args);
        System.out.println("Railway Reservation Web Application started on http://localhost:8080");
    }

    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            // This will run after the Spring application has started
            DatabaseSetup.initializeDatabase();
        };
    }
}
