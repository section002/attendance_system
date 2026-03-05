package jp.co.actec.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.actec.attendance.form.AttendanceForm;
import jp.co.actec.attendance.model.Attendance;
import jp.co.actec.attendance.model.EmployeeMst;
import jp.co.actec.attendance.model.RouteMst;
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
