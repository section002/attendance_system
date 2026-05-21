package jp.co.actec.attendance.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.actec.attendance.model.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>, JpaSpecificationExecutor<Attendance> {
    List<Attendance> findByDateBetween(LocalDate from, LocalDate to);

    List<Attendance> findByDateGreaterThanEqual(LocalDate from);
    
    List<Attendance> findByDateLessThanEqual(LocalDate to);

    @Query(value = """
        select
            ad.*
        from
            attendance ad
        inner join employee_mst em
            on em.emp_id = ad.emp_id
        inner join team_mst tm
            on tm.team_id = em.team_id
        where
            (
                (
                    :departmentId in ('4', '5')
                    and tm.unit_no = :unitNo
                )
                or
                (
                    :departmentId in ('2', '3')
                    and tm.team_id = :teamId
                )
                or
                (
                    :departmentId not in ('2', '3', '4', '5')
                    and em.emp_id = :empId
                )
            )
            and ad.date between :fromDate and :toDate
        order by
            em.emp_id
        """, nativeQuery = true)
    List<Attendance> findAttendanceByCondition(
        @Param("departmentId") String departmentId,
        @Param("unitNo") String unitNo,
        @Param("teamId") String teamId,
        @Param("empId") String empId,
        @Param("fromDate") LocalDate fromDate,
        @Param("toDate") LocalDate toDate
    );

}
