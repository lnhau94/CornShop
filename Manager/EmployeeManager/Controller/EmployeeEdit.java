package Manager.EmployeeManager.Controller;

import Entity.DAO;
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

public class EmployeeEdit implements Initializable {

    @FXML
    private TextField nameTxf;

    @FXML
    private TextField phoneTxf;
    @FXML
    private ComboBox<String> posChoice;

    @FXML
    private ComboBox<String> genderChoice;

    private Employee employee;

    @FXML
    void cancel(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void editEmployee(ActionEvent event) {
        if(isExistsPhone(phoneTxf.getText())){
            new ErrorController().displayError("Phone Exists");
        }
        else {
            try {
                employee.setEmployeeName(nameTxf.getText());
                employee.setPosition(posChoice.getValue());
                employee.setPhone(phoneTxf.getText());
                employee.setGender(genderChoice.getValue());
                DAO.execute(employee.toUpdateQuery());
                ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isExistsPhone(String phone){
        for(Employee e : EmployeeManagerModel.employees){
            if(e.getPhone().equals(phone)){
                return true;
            }
        }
        return false;
    }

    public void setData(Employee e){
        this.employee = e;
        posChoice.setValue(e.getPosition());
        genderChoice.setValue(e.getGender());
        nameTxf.setText(e.getEmployeeName());
        phoneTxf.setText(e.getPhone());
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
}
