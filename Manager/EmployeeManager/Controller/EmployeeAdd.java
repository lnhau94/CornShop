package Manager.EmployeeManager.Controller;

import Entity.DAO;
import Entity.Entity.Category;
import Entity.Entity.Employee;
import Manager.EmployeeManager.EmployeeManagerModel;
import Manager.Helpers.ErrorController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeAdd implements Initializable {

    @FXML
    private TextField phoneTxf;

    @FXML
    private PasswordField nameTxf;

    @FXML
    private ComboBox<String> posChoice;

    @FXML
    private ComboBox<String> genderChoice;

    @FXML
    void addNewEmployee(ActionEvent event) {
        if(isExistsPhone(phoneTxf.getText())){
            new ErrorController().displayError("Phone Exists");
        } else if (posChoice.getSelectionModel().getSelectedIndex()<0) {
            new ErrorController().displayError("Please Choice Position");
        } else if (genderChoice.getSelectionModel().getSelectedIndex()<0) {
            new ErrorController().displayError("Please Choice Gender");
        }else if (nameTxf.getText().trim().equals("")||phoneTxf.getText().trim().equals("")) {
            new ErrorController().displayError("Name and phone is Required");
        }  else {
            Employee c = new Employee();
            c.setEmployeeName(nameTxf.getText());
            c.setGender(genderChoice.getValue());
            c.setPhone(phoneTxf.getText());
            c.setPosition(posChoice.getValue());
            try {
                DAO.execute(c.toInsertQuery());
                ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> pos = new ArrayList<>();
        pos.add("Quản lí");
        pos.add("Thủ kho");
        pos.add("Bán Hàng");
        posChoice.setItems(FXCollections.observableList(pos));
        ArrayList<String> gds = new ArrayList<>();
        gds.add("Nam");
        gds.add("Nữ");
        genderChoice.setItems(FXCollections.observableList(gds));
    }

    private boolean isExistsPhone(String phone){
        for(Employee e : EmployeeManagerModel.employees){
            if(e.getPhone().equals(phone)){
                return true;
            }
        }
        return false;
    }

}