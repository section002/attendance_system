package jp.co.actec.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jp.co.actec.attendance.form.EmployeeForm;

@Service
public class LoginService {

    @Autowired
    private EmployeeForm employeeForm;

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

        // TODO:データベースからの取得処理を記載する
        // TODO:こちらの処理はデータベースからの取得処理が作成完了した後削除する　ここから
        String tmpmailAdress = "example@ac-tec.co.jp";
        String tmpPassword = "1234";
        if(!mailAdress.equals(tmpmailAdress) || !password.equals(tmpPassword)) {
            return false;
        }
        // TODO:ここまで

        return true;
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
