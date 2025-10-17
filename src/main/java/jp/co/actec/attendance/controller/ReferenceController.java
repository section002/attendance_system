package jp.co.actec.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/reference")
public class ReferenceController {
    
    @GetMapping
    public String showReferenceForm(Model model) {
        model.addAttribute("message", "参照画面");
        return "reference";
    }

    @GetMapping("/register")
    public String requestMethodName(Model model) {
        return "redirect:/registration";
    }
    
}
