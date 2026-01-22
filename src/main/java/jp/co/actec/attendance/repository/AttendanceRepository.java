package jp.co.actec.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jp.co.actec.attendance.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {}
