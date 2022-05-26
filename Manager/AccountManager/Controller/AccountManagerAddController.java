package Manager.AccountManager.Controller;

import Entity.DAO;
import Entity.Entity.Account;
import Entity.Entity.Employee;
import Manager.AccountManager.AccountManagerModel;
import Manager.EmployeeManager.EmployeeManagerModel;
import Manager.Helpers.ErrorController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccountManagerAddController implements Initializable {
     @FXML
    ComboBox<Employee> EmployeeId;
     @FXML
    private Label EmployeeName;
     @FXML
    private TextField txtUserName;
     @FXML
    private PasswordField textPassword;
     @FXML
     private PasswordField txtPasswordAgain;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmployeeId.setConverter(new StringConverter<Employee>() {
            @Override
            public String toString(Employee employee) {
                return employee.getEmployeeId()+" "+employee.getEmployeeName();
            }

            @Override
            public Employee fromString(String s) {
                return EmployeeId.getValue();
            }
        });
        refresh();

    }
    private void refresh(){
        EmployeeId.setItems(FXCollections.observableList(EmployeeManagerModel.employees));
    }
     public void handleEvent(){
         EmployeeId.valueProperty().addListener((observableValue, s, t1) -> {
             EmployeeName.setText(t1.getEmployeeName());
         });
     }

    public boolean checkPassword(){
         String password = textPassword.getText();
         String passwordAgain =txtPasswordAgain.getText();
         if(password.equalsIgnoreCase(passwordAgain)){
             return true;
         }
         return false;
    }
    public void AddAccount() throws SQLException {
         Account a = new Account();
         a.setUsername(txtUserName.getText());
         a.setPassword(textPassword.getText());
         a.setEmployeeId(EmployeeId.getValue().getId());
         DAO.execute(a.toInsertQuery());
         AccountManagerModel.accounts.add(a);
    }
    public boolean checkName(String Name) throws SQLException {
        for(Account e : AccountManagerModel.accounts){
            if(e.getUsername().equalsIgnoreCase(Name)) return false;
        }
        return true;
    }
    public void excuteCheck() throws SQLException {
        ErrorController error = new ErrorController();
        if(!checkName(txtUserName.getText())){
            error.displayError("name");
        }else if (EmployeeId.getSelectionModel().getSelectedIndex()<0){
                error.displayError("Please Choice Onwer");
        }else{
            AddAccount();
        }
    }
}
