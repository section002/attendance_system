package jp.co.actec.attendance.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.actec.attendance.model.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>, JpaSpecificationExecutor<Attendance> {
    List<Attendance> findByDateBetween(LocalDate from, LocalDate to);

    List<Attendance> findByDateGreaterThanEqual(LocalDate from);
    
    List<Attendance> findByDateLessThanEqual(LocalDate to);
}
