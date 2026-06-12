package jp.co.actec.attendance.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.actec.attendance.form.AttendanceForm;
import jp.co.actec.attendance.form.AttendanceSearchForm;
import jp.co.actec.attendance.form.EmployeeForm;
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
     * 勤怠情報を日付の降順で全件取得する。
     *
     * @return 日付降順にソートされた勤怠情報のリスト
     */
    public List<Attendance> findAllOrderByDateDesc() {
        return attendanceRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    /**
     * 当月の勤怠情報一覧を取得する。
     *
     * @return 当月の勤怠情報リスト
     */
    public List<Attendance> findByCurrentMonth() {
        YearMonth now = YearMonth.now();
        LocalDate from = now.atDay(1);
        LocalDate to = now.atEndOfMonth();
        return attendanceRepository.findByDateBetween(from, to);
    }

    /**
     * 検索条件に一致する勤怠情報一覧を取得する。
     *
     * @param searchForm 検索条件を保持するフォーム
     * @return 条件に一致する勤怠情報リスト
     */
    public List<Attendance> search(AttendanceSearchForm searchForm) {
        boolean isEmpty =
            (searchForm.getFrom() == null) &&
            (searchForm.getTo() == null) &&
            (searchForm.getRouteId() == null) &&
            (searchForm.getUnitNo() == null || searchForm.getUnitNo().isBlank()) &&
            (searchForm.getTeamId() == null || searchForm.getTeamId().isBlank()) &&
            (searchForm.getEmpId() == null || searchForm.getEmpId().isBlank());

        if (isEmpty) {
            return findByCurrentMonth();
        }

        Specification<Attendance> spec = AttendanceSpecification.search(
            searchForm.getFrom(),
            searchForm.getTo(),
            searchForm.getRouteId(),
            searchForm.getUnitNo(),
            searchForm.getTeamId(),
            searchForm.getEmpId()
        );
        
        return attendanceRepository.findAll(spec);
    }

    /**
     * 勤怠情報を登録する。
     *
     * @param attendanceForm 登録対象の勤怠情報フォーム
     */
    @Transactional
    public void register(AttendanceForm attendanceForm, EmployeeForm employeeForm) {
        EmployeeMst employee = employeeRepository.findById(Integer.parseInt(employeeForm.getEmpId())).orElseThrow();
        RouteMst route = routeRepository.findById(attendanceForm.getRouteId()).orElseThrow();
        Attendance attendance = attendanceForm.toEntity();
        attendance.setEmployee(employee);
        attendance.setRoute(route);
        attendanceRepository.save(attendance);
    }
}
