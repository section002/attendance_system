package jp.co.actec.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jp.co.actec.attendance.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @GetMapping("/")
    public String showLoginForm(Model model) {
        return "login";
    }

    @PostMapping("/login")
   public String login(@RequestParam("mailAdress") String mailAdress,
                       @RequestParam("password") String password,
                       Model model,
                       HttpSession session) {

       if(!loginService.authenticate(mailAdress, password)) { 
           // 認証失敗：エラーメッセージと入力されたメールアドレスを入力欄に戻す
           model.addAttribute("error", "メールアドレスまたはパスワードが正しくありません。");
           // 入力値を保持して再表示
           model.addAttribute("email", mailAdress);
           return "login";
       } else {
           // 認証成功：セッションにユーザー情報を保存して、登録画面へリダイレクト
           session.setAttribute("employeeInfo", loginService.buildEmployeeForm(mailAdress));
           return "redirect:/registration";
       }
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {

        // セッションを無効化してログアウト
        session.invalidate();
        return "redirect:/";
    }
}