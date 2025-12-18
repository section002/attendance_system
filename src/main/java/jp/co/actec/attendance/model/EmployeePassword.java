package jp.co.actec.attendance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "EMPLOYEE_PASSWORD", schema = "ATTENDANCE_SYSTEM")
@IdClass(EmployeePasswordId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePassword implements Serializable {
    @Id
    @Column(name = "EMP_ID", length = 10, nullable = false)
    private String empId;

    @Id
    @Column(name = "PASSWORD", length = 255, nullable = false)
    private String password;

    @Column(name = "CREATED_AT", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "EMP_ID", insertable = false, updatable = false)
    private EmployeeMst employee;
}