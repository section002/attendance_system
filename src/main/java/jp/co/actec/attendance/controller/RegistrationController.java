package jp.co.actec.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.actec.attendance.service.RouteService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    RouteService routeService;

    @GetMapping
    public String registerAttendanceForm(Model model) {
        System.out.println(routeService.findAllRoutes());
        return "registration";
    }

    @PostMapping("/register")
    public String registerAttendance(Model model) {
        return "redirect:/reference";
    }

    @GetMapping("/reference")
    public String referenceAttendance(Model model) {
        return "redirect:/reference";
    }
}
