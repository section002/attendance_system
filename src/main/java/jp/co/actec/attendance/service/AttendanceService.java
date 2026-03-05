package jp.co.actec.attendance.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.actec.attendance.form.AttendanceForm;
import jp.co.actec.attendance.form.AttendanceSearchForm;
import jp.co.actec.attendance.model.Attendance;
import jp.co.actec.attendance.model.EmployeeMst;
import jp.co.actec.attendance.model.RouteMst;
import jp.co.actec.attendance.model.specification.AttendanceSpecification;
import jp.co.actec.attendance.repository.AttendanceRepository;
import jp.co.actec.attendance.repository.EmployeeRepository;
import jp.co.actec.attendance.repository.RouteRepository;

@Service
public class AttendanceService {
    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RouteRepository routeRepository;

    /**
     * 今月の勤怠情報一覧を返す
     * 
     * @return 勤怠情報一覧
     */
    public List<Attendance> findByCurrentMonth() {
        YearMonth now = YearMonth.now();
        LocalDate from = now.atDay(1);
        LocalDate to = now.atEndOfMonth();
        return attendanceRepository.findByDateBetween(from, to);
    }

    /**
     * 検索した勤怠情報一覧を返す
     * 
     * @param searchForm 検索フォーム入力値
     * @return 勤怠情報一覧
     */
    public List<Attendance> search(AttendanceSearchForm searchForm) {
        Specification<Attendance> spec = AttendanceSpecification.search(
            searchForm.getFrom(),
            searchForm.getTo()
        );
        
        return attendanceRepository.findAll(spec);
    }

    /**
     * 勤怠情報を登録する
     * 
     * @param attendanceForm フォーム入力値
     */
    @Transactional
    public void register(AttendanceForm attendanceForm) {
        // 社員IDは認証情報をセッションから取得予定
        EmployeeMst employee = employeeRepository.findById(1).orElseThrow();
        RouteMst route = routeRepository.findById(attendanceForm.getRouteId()).orElseThrow();
        Attendance attendance = attendanceForm.toEntity();
        attendance.setEmployee(employee);
        attendance.setRoute(route);
        attendanceRepository.save(attendance);
    }
}
