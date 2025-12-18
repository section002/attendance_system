package jp.co.actec.attendance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "DEPARTMENT_MST")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentMst implements Serializable {
    @Id
    @Column(name = "DEPARTMENT_ID", length = 1, nullable = false)
    private String departmentId;

    @Column(name = "DEPARTMENT_NAME", length = 20, nullable = false)
    private String departmentName;
}
