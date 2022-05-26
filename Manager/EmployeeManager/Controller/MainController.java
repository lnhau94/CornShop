package Manager.EmployeeManager.Controller;

import Entity.Entity.Employee;
import Manager.AccountManager.AccountManagerModel;
import Manager.EmployeeManager.EmployeeManagerModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<Employee> empTab;

    @FXML
    private TableColumn<Employee, String> noCol;

    @FXML
    private TableColumn<Employee, String> idCol;

    @FXML
    private TableColumn<Employee, String> nameCol;

    @FXML
    private TableColumn<Employee, String> phoneCol;

    @FXML
    private TableColumn<Employee, String> posCol;

    @FXML
    private TableColumn<Employee, String> genderCol;

    @FXML
    private Label header;

    @FXML
    void createNewEmployee(ActionEvent event) {

    }

    @FXML
    void editEmployee(ActionEvent event) {

    }

    @FXML
    void removeEmloyee(ActionEvent event) {

    }

    EmployeeManagerModel model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new EmployeeManagerModel();
        noCol.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(EmployeeManagerModel.employees.indexOf(e.getValue()))));
        idCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getEmployeeId()));
        nameCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getEmployeeName()));
        phoneCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getPhone()));
        posCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getPosition()));
        genderCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getGender()));
        empTab.setItems(FXCollections.observableList(EmployeeManagerModel.employees));

    }
}
