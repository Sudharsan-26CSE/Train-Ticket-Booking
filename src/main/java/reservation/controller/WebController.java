package reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import train.Train;
import admin.Admin;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        return "index"; // Renders index.html template
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin"; // Renders admin.html template
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        return "settings"; // Renders settings.html template
    }
}


