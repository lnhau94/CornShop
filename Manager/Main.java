package Manager;

import Entity.Entity.Employee;
import Manager.AccountManager.AccountManagerModel;
import Manager.EmployeeManager.EmployeeManagerModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        prepareData();

        try {

            primaryStage.setScene(new Scene(FXMLLoader.load(
                    new File("Manager/AccountManager/View/AccountManager.fxml").toURI().toURL())));

             /*
            primaryStage.setScene(new Scene(FXMLLoader.load(
                    new File("Manager/EmployeeManager/View/EmployeeView.fxml").toURI().toURL())));
            */
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareData(){
        EmployeeManagerModel.getAllData();
        AccountManagerModel.getAllData();
    }
}
