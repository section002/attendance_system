package jp.co.actec.attendance.form;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EmployeeForm {
    
    // TODO:フィールド追加・削除・変更検討

    /* 社員ID */
    private String empId;

    /* 役職 */
    private String role;
    
    /* 社員姓 */
    private String empLname;

    /* 社員名 */
    private String empFname;

    /* 社員セイ */
    private String empLnameKana;

    /* 社員メイ */
    private String empFnameKana;

    /* メールアドレス */
    private String mailAdress;

    /* チームID */
    private String teamId;

    /* チーム名 */
    private String teamName;

}
