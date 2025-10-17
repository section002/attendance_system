package jp.co.actec.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
    @GetMapping("/")
    public String showLoginForm(Model model) {
        model.addAttribute("message", "ログイン画面");
        return "login";
    }

    @PostMapping("/login")
   public String login(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       Model model) {
       return "redirect:/registration";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/";
    }
    

}