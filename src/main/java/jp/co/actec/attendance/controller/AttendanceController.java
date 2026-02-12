package jp.co.actec.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.actec.attendance.form.AttendanceForm;
import jp.co.actec.attendance.model.RouteMst;
import jp.co.actec.attendance.service.AttendanceService;
import jp.co.actec.attendance.service.RouteService;

@Controller
@RequestMapping("/attendances")
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;
    
    @Autowired
    RouteService routeService;

    /**
     * 全路線情報を取得し、Modelに設定する。
     * @return 全路線一覧
     */
    @ModelAttribute("routes")
    public List<RouteMst> getRoutes() {
        return routeService.findAllRoutes();
    }

    @GetMapping
    public String index() {
        return "reference";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("attendance", new AttendanceForm());
        return "registration";
    }

    @PostMapping
    public String create(
        @ModelAttribute("attendance") @Validated AttendanceForm attendanceForm,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        attendanceService.register(attendanceForm);

        return "redirect:/attendances";
    }
}
