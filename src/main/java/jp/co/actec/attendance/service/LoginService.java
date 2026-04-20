package jp.co.actec.attendance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import jp.co.actec.attendance.form.EmployeeForm;
import jp.co.actec.attendance.model.EmployeeMst;
import jp.co.actec.attendance.model.EmployeePassword;
import jp.co.actec.attendance.model.EmployeePasswordId;
import jp.co.actec.attendance.repository.EmployeeMstRepository;
import jp.co.actec.attendance.repository.EmployeePasswordRepository;

@Service
public class LoginService {

    @Autowired
    private EmployeeForm employeeForm;

    @Autowired
    private EmployeeMstRepository employeeMstRepository;
    
    @Autowired
    private EmployeePasswordRepository employeePasswordRepository;

    /*メールアドレス_ドメイン： */
    private final String MAIL_ADRESS_DOMAIN = "@ac-tec.co.jp";

    /*メールアドレス_最大文字数： */
    private final int MAX_LENGTH_MAIL_ADRESS = 100;  

    // TODO:パスワードの最大文字数をいくつにするかは要検討
    /*パスワード_最大文字数：255 */
    private final int MAX_LENGTH_PASSWORD = 255;

    /**
     * メールアドレスとパスワードによる認証を行う
     * @param mailAdress メールアドレス
     * @param password パスワード
     * @return 認証結果（true:認証成功、false:認証失敗）
     */
    public EmployeeMst authenticate(String mailAdress, String password) {

        EmployeeMst employee = new EmployeeMst();
        // 必須チェック
        if (!StringUtils.hasText(mailAdress) || !StringUtils.hasText(password)) {
            // メールアドレスまたはパスワードが未入力の場合、認証失敗
            return null;
        }

        // 文字数チェック
        if (mailAdress.length() > MAX_LENGTH_MAIL_ADRESS || password.length() > MAX_LENGTH_PASSWORD) {
            // メールアドレスまたはパスワードが最大文字数を超える場合、認証失敗
            return null;
        }

        // メールアドレスチェック
        if(!mailAdress.endsWith(MAIL_ADRESS_DOMAIN)) {
            // メールアドレスが指定のドメインでない場合、認証失敗
            return null;
        }
        
        // メールアドレスから社員テーブルを検索
        List<EmployeeMst> employeeList = employeeMstRepository.findByMailAddress(mailAdress);
        if(employeeList.size() != 1 ){
            // 1件でなければエラー
            return null ;
        }
        // 社員IDを抽出
        String empId = employeeList.get(0).getEmpId();

        // PKの項目にデータセット
        EmployeePasswordId employeePasswordId = new EmployeePasswordId();
        employeePasswordId.setEmpId(empId);
        employeePasswordId.setPassword(password);

        // パスワードテーブルを検索
        Optional<EmployeePassword> passId = employeePasswordRepository.findById(employeePasswordId);

        // 存在判定
        if (passId.isPresent()) {
            // 認証成功
            return employeeList.get(0);
        } else {
            // 認証失敗
            return null;
        }
    }


    /**
     * 社員情報をDBから取得する
     * @param mailAdress メールアドレス
     * @return EmployeeForm 社員情報フォーム
     */
    public EmployeeForm buildEmployeeForm(EmployeeMst employee) {

        employeeForm.setEmpId(employee.getEmpId());
        employeeForm.setRole(employee.getRole());
        employeeForm.setEmpLname(employee.getEmpLname());
        employeeForm.setEmpFname(employee.getEmpFname());
        employeeForm.setEmpLnameKana(employee.getEmpLnameKana());
        employeeForm.setEmpFnameKana(employee.getEmpFnameKana());
        employeeForm.setMailAdress(employee.getMailAddress());
        employeeForm.setTeamId(employee.getTeam().toString());
        employeeForm.setTeamName(employee.getTeam().getTeamName());

        return employeeForm;
    }
}
