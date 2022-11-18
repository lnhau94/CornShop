package Manager.Helpers.SignIn;

import Entity.DAO;
import Entity.Entity.Account;
import Entity.Entity.Employee;
import Main.MainApp;
import Manager.AccountManager.AccountManagerModel;
import Manager.EmployeeManager.EmployeeManagerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SigninController {

    @FXML
    private TextField userTxf;

    @FXML
    private PasswordField passTxf;

    @FXML
    void checkSignIn(ActionEvent event) {
        userTxf.setText("lnhau");
        passTxf.setText("123");
        String user = userTxf.getText();
        String pass = passTxf.getText();
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.initStyle(StageStyle.TRANSPARENT);

        int flag = -1;
        try {
            a.getDialogPane().getStylesheets().add(new File("Manager/Helpers/SignIn/Alert.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        if(user.equals("") || pass.equals("")){
            a.setContentText("Please enter username and password");
            a.show();
        }
        else{
            for(Account account : AccountManagerModel.accounts){
                if(account.getUsername().equals(user)){
                    if(account.getPassword().equals(pass)){
                        MainApp.staff = EmployeeManagerModel.findById(account.getEmployeeId());
                        switch (MainApp.staff.getPosition()){
                            case "Quản lí":
                                flag = 3;
                                break;
                            case "Bán hàng":
                                flag = 1;
                                break;
                            case "Thủ kho":
                                flag = 2;
                                break;
                            default: flag = 2;
                        }
                        break;
                    }else{
                        flag = 0;
                        break;
                    }
                }
            }



            switch (flag){
                case -1:
                    a.setContentText("Account not exist");
                    a.show();
                    break;
                case 0:
                    a.setContentText("Wrong Password");
                    a.show();
                    break;
                default:
                    MainApp.controlBar.initGUI();
                    //MainApp.controlBar.getDataControl().setTxtUserName(MainApp.staff.getEmployeeName());
                    MainApp.controlBar.showFunction(flag);
            }

        }
    }
}
