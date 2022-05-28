package Manager.MainScreen.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ManagerScreenController {

    @FXML
    private Button storageBtn;

    @FXML
    private Button statisticBtn;

    @FXML
    private Button accountBtn;

    @FXML
    private Button categoryBtn;

    @FXML
    private Button productBtn;

    @FXML
    private Button employeeBtn;

    private String managerRoot = "Manager/";

    @FXML
    void showAccountView(ActionEvent event) {
        try {
            ((Stage)((Button)event.getSource()).getScene().getWindow()).setScene(
                    new Scene(FXMLLoader.load(
                            new File("Manager/AccountManager/View/AccountManager.fxml").toURI().toURL()
                    )));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showCategoryView(ActionEvent event) {
        try {
            ((Stage)((Button)event.getSource()).getScene().getWindow()).setScene(
                    new Scene(FXMLLoader.load(
                            new File("Manager/Category/View/CategoryView.fxml").toURI().toURL()
                    )));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showEmployeeView(ActionEvent event) {
        try {
            ((Stage)((Button)event.getSource()).getScene().getWindow()).setScene(
                    new Scene(FXMLLoader.load(
                            new File("Manager/EmployeeManager/View/EmployeeView.fxml").toURI().toURL()
                    )));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showProductView(ActionEvent event) {
        try {
            ((Stage)((Button)event.getSource()).getScene().getWindow()).setScene(
                    new Scene(FXMLLoader.load(
                            new File("Manager/Product/View/ProductView.fxml").toURI().toURL()
                    )));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showStatisticView(ActionEvent event) {
        try {
            Stage stage = ((Stage)((Button)event.getSource()).getScene().getWindow());
            stage.setScene(
                    new Scene(FXMLLoader.load(
                            new File("Statistic/ReportStatistic/View/RevenueStatistic.fxml")
                                    .toURI().toURL()
                    )));
            stage.getScene().getStylesheets().add(
                            new File("Statistic/ReportStatistic/View/CSS/RevenueStatistic.css")
                                    .toURI().toURL().toExternalForm());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showStorageView(ActionEvent event) {
        try {
            ((Stage)((Button)event.getSource()).getScene().getWindow()).setScene(
                    new Scene(FXMLLoader.load(
                            new File("Manager/Storage/View/StorageManager.fxml").toURI().toURL()
                    )));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
