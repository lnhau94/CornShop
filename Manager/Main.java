package Manager;

import Entity.Entity.Brand;
import Entity.Entity.Employee;
import Manager.AccountManager.AccountManagerModel;
import Manager.Brand.BrandManagerModel;
import Manager.Category.CategoryManagerModel;
import Manager.EmployeeManager.EmployeeManagerModel;
import Manager.Product.ProductManagerModel;
import Manager.Storage.StorageManagerModel;
import Manager.Supplier.SupplierManagerModel;
import Store.POS.Control.PosController;
import Store.POS.View.MenuItem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        prepareData();
        primaryStage.setScene(new Scene(new PosController().getView()));
        try {
            primaryStage.getScene().getStylesheets().add(new File("Manager/Application.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        primaryStage.centerOnScreen();
        primaryStage.show();
        /*
        try {

            primaryStage.setScene(new Scene(FXMLLoader.load(
                    new File("Manager/AccountManager/View/AccountManager.fxml").toURI().toURL())));


            primaryStage.setScene(new Scene(FXMLLoader.load(
                    new File("Manager/EmployeeManager/View/EmployeeView.fxml").toURI().toURL())));

            primaryStage.setScene(new Scene(new MenuItem()));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         */
    }

    private void prepareData(){
        EmployeeManagerModel.getAllData();
        CategoryManagerModel.getAllData();
        AccountManagerModel.getAllData();
        ProductManagerModel.getAllData();
        StorageManagerModel.getAllData();
        BrandManagerModel.getAllData();
        SupplierManagerModel.getAllData();
        System.out.println(EmployeeManagerModel.employees.size());
        System.out.println(CategoryManagerModel.categories.size());
        System.out.println(AccountManagerModel.accounts.size());
        System.out.println(ProductManagerModel.products.size());
        System.out.println(StorageManagerModel.storages.size());
        System.out.println(BrandManagerModel.brands.size());
        System.out.println(SupplierManagerModel.suppliers.size());
    }
}
