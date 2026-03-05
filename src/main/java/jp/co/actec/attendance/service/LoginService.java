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
import jp.co.actec.attendance.model.RouteMst;
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


    // @Autowired
    // private EmployeePasswordId employeePasswordId;




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
    public boolean authenticate(String mailAdress, String password) {

        // 必須チェック
        if (!StringUtils.hasText(mailAdress) || !StringUtils.hasText(password)) {
            // メールアドレスまたはパスワードが未入力の場合、認証失敗
            return false;
        }

        // 文字数チェック
        if (mailAdress.length() > MAX_LENGTH_MAIL_ADRESS || password.length() > MAX_LENGTH_PASSWORD) {
            // メールアドレスまたはパスワードが最大文字数を超える場合、認証失敗
            return false;
        }

        // メールアドレスチェック
        if(!mailAdress.endsWith(MAIL_ADRESS_DOMAIN)) {
            // メールアドレスが指定のドメインでない場合、認証失敗
            return false;
        }
        
        // メールアドレスから社員テーブルを検索
        List<EmployeeMst> employee = employeeMstRepository.findByMailAddress(mailAdress);
        if(employee.size() != 1 ){
            // 1件でなければエラー
            return false ;
        }
        // 社員IDを抽出
        String empId = employee.get(0).getEmpId();

        // PKの項目にデータセット
        EmployeePasswordId employeePasswordId = new EmployeePasswordId();
        employeePasswordId.setEmpId(empId);
        employeePasswordId.setPassword(password);

        // パスワードテーブルを検索
        Optional<EmployeePassword> passId = employeePasswordRepository.findById(employeePasswordId);

        // 存在判定を返す
        return !passId.isEmpty();
    }



    

    /**
     * 社員情報をDBから取得する
     * @param mailAdress メールアドレス
     * @return EmployeeForm 社員情報フォーム
     */
    public EmployeeForm buildEmployeeForm(String mailAdress) {

        // TODO:データベースから社員情報を取得し、EmployeeFormにセットする処理を記載する
        // TODO:こちらの処理はデータベースからの取得処理が作成完了した後削除する　ここから
        employeeForm.setEmpId("1");
        employeeForm.setRole("1");
        employeeForm.setEmpLname("山田");
        employeeForm.setEmpFname("太郎");
        employeeForm.setEmpLnameKana("ヤマダ");
        employeeForm.setEmpFnameKana("タロウ");
        employeeForm.setMailAdress(mailAdress);
        employeeForm.setTeamId("1");
        employeeForm.setTeamName("セクション2");
        // TODO:ここまで

        return employeeForm;
    }
}
