package Income;

import Manager.AccountManager.AccountManagerModel;
import Manager.Brand.BrandManagerModel;
import Manager.Category.CategoryManagerModel;
import Manager.EmployeeManager.EmployeeManagerModel;
import Manager.Product.ProductManagerModel;
import Manager.Storage.StorageManagerModel;
import Manager.Supplier.SupplierManagerModel;
import Store.POS.Control.PosController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class MainIncome extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        prepareData();

        FXMLLoader fx = new FXMLLoader();
        Parent root = null;
        try {
            fx.setLocation(new File("Income/View/IncomeChoose.fxml").toURI().toURL());
            root = fx.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setScene(new Scene(root));
        primaryStage.centerOnScreen();
        primaryStage.show();
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
