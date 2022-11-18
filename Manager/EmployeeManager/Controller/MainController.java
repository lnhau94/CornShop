package Manager.EmployeeManager.Controller;

import Entity.Entity.Employee;
import Manager.AccountManager.AccountManagerModel;
import Manager.Brand.Controller.BrandEdit;
import Manager.Category.CategoryManagerModel;
import Manager.EmployeeManager.EmployeeManagerModel;
import Manager.Helpers.ErrorController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
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
        try {
            Stage s = new Stage(StageStyle.TRANSPARENT);
            s.setScene(new Scene(FXMLLoader.load(new File("Manager/EmployeeManager/View/EmployeeAdd.fxml")
                    .toURI().toURL())));
            s.initOwner((Stage)((Button)event.getSource()).getScene().getWindow());
            s.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void editEmployee(ActionEvent event) {
        try {
            Stage s = new Stage(StageStyle.TRANSPARENT);
            FXMLLoader fx = new FXMLLoader(new File("Manager/EmployeeManager/View/EmployeeEdit.fxml")
                    .toURI().toURL());
            s.setScene(new Scene(fx.load()));
            EmployeeEdit ee = fx.getController();
            ee.setData(empTab.getSelectionModel().getSelectedItem());
            s.initOwner((Stage)((Button)event.getSource()).getScene().getWindow());
            s.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e){
            new ErrorController().displayError("Nothing Choosen!!!");
        }
    }

    @FXML
    void removeEmloyee(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        Dialog<ButtonType> dialog = new Dialog<>();
        try {
            loader.setLocation(new File("Manager/Helpers/DeleteAlert.fxml").toURI().toURL());
            dialog.setDialogPane(loader.load());
            dialog.initStyle(StageStyle.TRANSPARENT);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Optional<ButtonType> ClickedButton = dialog.showAndWait();
        if(ClickedButton.get()==ButtonType.YES) {
            try {
                EmployeeManagerModel.employees.remove(empTab.getSelectionModel().getSelectedItem());
            } catch (NullPointerException e){
                new ErrorController().displayError("Nothing Choosen!!!");
            }
            empTab.setItems(FXCollections.observableArrayList(EmployeeManagerModel.employees));
            empTab.refresh();

        }else if(ClickedButton.get()==ButtonType.NO){
            dialog.close();
        }
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
