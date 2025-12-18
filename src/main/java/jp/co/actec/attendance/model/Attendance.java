package jp.co.actec.attendance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "ATTENDANCE", schema = "ATTENDANCE_SYSTEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance implements Serializable {
    @Id
    @Column(name = "ATTENDANCE_ID", nullable = false)
    private Integer attendanceId;

    @Column(name = "EMP_ID", length = 10, nullable = false, insertable = false, updatable = false)
    private String empId;

    @Column(name = "ROUTE_ID", nullable = false, insertable = false, updatable = false)
    private Integer routeId;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "LATE_REASON", nullable = false)
    private Integer lateReason;

    @Column(name = "LATE_TIME", nullable = false)
    private Integer lateTime;

    @Column(name = "TRAIN_DELAY_TIME", nullable = false)
    private Integer trainDelayTime;

    @Column(name = "NOTE", columnDefinition = "TEXT")
    private String note;

    @Column(name = "CREATED_AT", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
    private EmployeeMst employee;

    @ManyToOne
    @JoinColumn(name = "ROUTE_ID", referencedColumnName = "ROUTE_ID")
    private RouteMst route;
}