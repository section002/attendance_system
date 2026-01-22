package jp.co.actec.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.actec.attendance.model.Attendance;
import jp.co.actec.attendance.repository.AttendanceRepository;
import jp.co.actec.attendance.service.RouteService;

@Controller
@RequestMapping("/attendances")
public class AttendanceController {
    @Autowired
    AttendanceRepository attendanceRepository;
    
    @Autowired
    RouteService routeService;

    @GetMapping
    public String index() {
        return "reference";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        model.addAttribute("routes", routeService.findAllRoutes());

        return "registration";
    }

    @PostMapping
    public String create(@ModelAttribute Attendance attendance) {
        System.out.println(attendance);
        // attendanceRepository.save(attendance);

        return "redirect:/attendances";
    }
}
