package jp.co.actec.attendance.controller;

import java.io.IOException;
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

import jakarta.servlet.http.HttpServletResponse;
import jp.co.actec.attendance.form.AttendanceForm;
import jp.co.actec.attendance.form.AttendanceSearchForm;
import jp.co.actec.attendance.model.Attendance;
import jp.co.actec.attendance.model.Attendance;
import jp.co.actec.attendance.model.RouteMst;
import jp.co.actec.attendance.service.AttendanceService;
import jp.co.actec.attendance.service.ExportService;
import jp.co.actec.attendance.service.RouteService;

@Controller
@RequestMapping("/attendances")
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;
    
    @Autowired
    RouteService routeService;

    @Autowired
    ExportService exportService;

    /**
     * 全路線情報を取得し、Modelに設定する
     * 
     * @return 全路線一覧
     */
    @ModelAttribute("routes")
    public List<RouteMst> getRoutes() {
        return routeService.findAllRoutes();
    }

    /**
     * 初期表示
     * 
     * @param searchForm
     * @param model
     * @return
     */
    @GetMapping
    public String index(
        @ModelAttribute("searchForm") AttendanceSearchForm searchForm,
        Model model
    ) {
        List<Attendance> attendances = attendanceService.findByCurrentMonth();

        model.addAttribute("attendances", attendances);

        return "reference";
    }

    /**
     * 勤怠情報の検索
     * 
     * @param searchForm
     * @param model
     * @return
     */
    @GetMapping("/search")
    public String search(
        @ModelAttribute("searchForm") AttendanceSearchForm searchForm,
        Model model
    ) {
        List<Attendance> attendances = attendanceService.search(searchForm);

        model.addAttribute("attendances", attendances);

        return "reference";
    }

    @GetMapping("/new")
    public String newForm(
        @ModelAttribute("attendance") AttendanceForm attendanceForm,
        Model model
    ) {
        return "registration";
    }

    /**
     * 勤怠情報の作成
     * 
     * @param attendanceForm
     * @param bindingResult
     * @param model
     * @return 勤怠情報一覧画面
     */
    @PostMapping
    public String create(
        @ModelAttribute("attendance") @Validated AttendanceForm attendanceForm,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) return "registration";

        attendanceService.register(attendanceForm);

        return "redirect:/attendances";
    }

    @GetMapping("/export/csv")
    public void exportCsv(HttpServletResponse response) throws IOException {
        List<Attendance> attendances = attendanceService.findLateAttendances();
        exportService.exportLateAttendancesCsv(attendances, response);
    }
}
