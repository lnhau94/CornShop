package Main;

import Entity.Entity.Employee;
import Income.Controller.MasterController;
import Manager.AccountManager.AccountManagerModel;
import Manager.Brand.BrandManagerModel;
import Manager.Category.CategoryManagerModel;
import Manager.EmployeeManager.EmployeeManagerModel;
import Manager.Helpers.Controlbar.ControlBar;
import Manager.MainScreen.ManagerControl;
import Manager.Product.ProductManagerModel;
import Manager.Storage.StorageManagerModel;
import Manager.Supplier.SupplierManagerModel;
import Store.POS.Control.PosController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

public class MainApp extends Application {

    Dimension screenSize;

    Stage toolPanel;

    public static Employee staff;

    public static ControlBar controlBar;
    Scene managerView;
    Scene POSView;


    private void createControlBar(Stage stage){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        toolPanel = new Stage(StageStyle.TRANSPARENT);
        toolPanel.initOwner(stage);
        controlBar = new ControlBar(stage);
        toolPanel.setScene(new Scene(controlBar));
        controlBar.prepareCSS();
        controlBar.showFunction(0);
    }


    @Override
    public void start(Stage stage) throws Exception {
        prepareData();
        MasterController.start();
        createControlBar(stage);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Corn Shop Clothing");
        ImageView img =  new ImageView(new Image(new FileInputStream("Icon/Cornshop.png")));
        img.setFitWidth(60);
        img.setFitHeight(60);
        stage.getIcons().add(img.getImage());

        stage.show();
        controlBar.changeLocation();
        toolPanel.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void prepareData(){
        EmployeeManagerModel.getAllData();
        CategoryManagerModel.getAllData();
        AccountManagerModel.getAllData();
        ProductManagerModel.getAllData();
        StorageManagerModel.getAllData();
        BrandManagerModel.getAllData();
        SupplierManagerModel.getAllData();
    }
}
