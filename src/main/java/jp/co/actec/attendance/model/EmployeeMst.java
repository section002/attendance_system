package jp.co.actec.attendance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "EMPLOYEE_MST", schema = "ATTENDANCE_SYSTEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMst implements Serializable {
    @Id
    @Column(name = "EMP_ID", length = 10, nullable = false)
    private String empId;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", nullable = false)
    private DepartmentMst department;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private TeamMst team;

    @Column(name = "EMP_NO", length = 4)
    private String empNo;

    @Column(name = "ROLE", length = 1, columnDefinition = "char(1) default '0'")
    private String role = "0";

    @Column(name = "EMP_LNAME", length = 10, nullable = false)
    private String empLname;

    @Column(name = "EMP_FNAME", length = 10, nullable = false)
    private String empFname;

    @Column(name = "EMP_LNAME_KANA", length = 20, nullable = false)
    private String empLnameKana;

    @Column(name = "EMP_FNAME_KANA", length = 20, nullable = false)
    private String empFnameKana;

    @Column(name = "GENDER", length = 1, nullable = false)
    private String gender;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "BELONG", length = 1, nullable = false)
    private String belong;

    @Column(name = "EMP_STATUS", length = 1, nullable = false, columnDefinition = "char(1) default '0'")
    private String empStatus = "0";

    @Column(name = "CHANGE_DATE")
    private LocalDate changeDate;

    @Column(name = "MAIL_ADDRESS", length = 100)
    private String mailAddress;
}