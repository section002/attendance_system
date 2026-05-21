package jp.co.actec.attendance.form;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EmployeeForm {
    
    /* 社員ID */
    private String empId;

    /* 役職 */
    private String role;
    
    /* 社員姓 */
    private String empLname;

    /* 社員名 */
    private String empFname;

    /* メールアドレス */
    private String mailAdress;

    /* チームID */
    private String teamId;

    /* チーム名 */
    private String teamName;

    /* ユニットNo */
    private String unitNo;

    /* 部署ID */
    private String departmentId;
}
