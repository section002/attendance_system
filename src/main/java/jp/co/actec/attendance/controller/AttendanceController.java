package jp.co.actec.attendance.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.actec.attendance.form.AttendanceForm;
import jp.co.actec.attendance.form.AttendanceSearchForm;
import jp.co.actec.attendance.model.Attendance;
import jp.co.actec.attendance.model.RouteMst;
import jp.co.actec.attendance.service.AttendanceService;
import jp.co.actec.attendance.service.RouteService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/attendances")
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;
    
    @Autowired
    RouteService routeService;

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
        Model model, HttpSession session
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
    public void downloadCsv(HttpServletResponse response) {
        response.setContentType("text/csv");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"attendance.csv\"");
        
        try (PrintWriter writer = response.getWriter()) {
            List<Attendance> attendances = attendanceService.findAllOrderByDateDesc();

            writer.println(
                "社員名," +
                "路線名," +
                "日付," +
                "遅刻理由," +
                "遅刻時間," +
                "遅延時間," +
                "備考"
            );

            for (Attendance attendance : attendances) {
                writer.println(
                    attendance.getEmployee().getEmpLname() + attendance.getEmployee().getEmpFname() + "," +
                    attendance.getRoute().getRouteName() + "," +
                    attendance.getDate() + "," +
                    attendance.getLateReasonLabel() + "," +
                    attendance.getLateTime() + "," +
                    attendance.getTrainDelayTime() + "," +
                    Objects.toString(attendance.getNote(), "")
                );
            }
        } catch (Exception e) {
            // エラー処理は時間があれば実装
        }
    }
}
