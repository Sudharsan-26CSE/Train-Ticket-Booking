package reservation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import train.Train;
import train.Schedule;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping
    public List<Train> getAllTrains() {
        List<Train> trains = trainService.getAllTrains();
        System.out.println(">>> TrainController: Found " + trains.size() + " trains.");
        return trains;
    }

    @PutMapping("/{id}")
    public Train updateTrain(@PathVariable Long id, @RequestBody Train train) {
        train.setId(id);
        return trainService.saveTrain(train);
    }

    @GetMapping("/schedules")
    public List<Schedule> getAllSchedules() {
        return trainService.getAllSchedules();
    }

    @PutMapping("/schedules/{id}")
    public Schedule updateSchedule(@PathVariable Integer id, @RequestBody Schedule schedule) {
        schedule.setId(id);
        return trainService.saveSchedule(schedule);
    }
}