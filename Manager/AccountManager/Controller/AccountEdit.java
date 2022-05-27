package Manager.AccountManager.Controller;

import Entity.DAO;
import Entity.Entity.Account;
import Manager.AccountManager.AccountManagerModel;
import Manager.EmployeeManager.EmployeeManagerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AccountEdit {

    @FXML
    private TextField txtUserName;

    @FXML
    private Label EmployeeName;

    @FXML
    private PasswordField textPassword;

    @FXML
    private PasswordField txtPasswordAgain;

    @FXML
    private Label EmployeeId;

    public void setData(Account a){
        EmployeeName.setText(
                EmployeeManagerModel.findById(a.getEmployeeId())!=null
                        ?EmployeeManagerModel.findById(a.getEmployeeId()).getEmployeeName()
                        :"Not Set Yet");
        EmployeeId.setText(
                EmployeeManagerModel.findById(a.getEmployeeId())!=null
                        ?EmployeeManagerModel.findById(a.getEmployeeId()).getEmployeeId()
                        :"Not Set Yet");
        textPassword.setText(a.getPassword());
        txtPasswordAgain.setText(a.getPassword());
        txtUserName.setText(a.getUsername());
    }

    public boolean checkPassword() {
        return textPassword.getText().equals(txtPasswordAgain.getText());
    }

    public void updateAccount(Account a) {
        a.setUsername(txtUserName.getText());
        a.setPassword(textPassword.getText());
        AccountManagerModel.accounts.set(AccountManagerModel.accounts.indexOf(a),a);
        try {
            DAO.execute(a.toUpdateQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
