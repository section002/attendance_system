package jp.co.actec.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/registration")
public class RegistrationController {
    
    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("message", "登録画面");
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
    
    