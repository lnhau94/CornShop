package Manager.Brand.Controller;

import Entity.DAO;
import Entity.Entity.Brand;
import Manager.AccountManager.AccountManagerModel;
import Manager.Brand.BrandManagerModel;
import Manager.Helpers.ErrorController;
import Manager.Supplier.SupplierManagerModel;
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
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class BrandManager implements Initializable {

    @FXML
    private TableView<Brand> brandTab;

    @FXML
    private TableColumn<Brand, String> noCol;

    @FXML
    private TableColumn<Brand, String> idCol;

    @FXML
    private TableColumn<Brand, String> nameCol;

    @FXML
    private Label header;

    @FXML
    void addNewBrand(ActionEvent event) {
        try {
            Stage s = new Stage(StageStyle.TRANSPARENT);
            s.setScene(new Scene(FXMLLoader.load(new File("Manager/Brand/View/BrandAdd.fxml")
                    .toURI().toURL())));
            s.initOwner((Stage)((Button)event.getSource()).getScene().getWindow());
            s.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void editBrand(ActionEvent event) {
        try {
            Stage s = new Stage(StageStyle.TRANSPARENT);
            FXMLLoader fx = new FXMLLoader(new File("Manager/Brand/View/BrandEdit.fxml")
                    .toURI().toURL());
            s.setScene(new Scene(fx.load()));
            BrandEdit be = fx.getController();
            be.setData(brandTab.getSelectionModel().getSelectedItem());
            s.initOwner((Stage)((Button)event.getSource()).getScene().getWindow());
            s.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e){
            new ErrorController().displayError("Nothing Choosen!!!");
        }
    }

    @FXML
    void removeBrand(ActionEvent event) {
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
                BrandManagerModel.brands.remove(brandTab.getSelectionModel().getSelectedItem());
            } catch (NullPointerException e){
                new ErrorController().displayError("Nothing Choosen!!!");
            }
            brandTab.setItems(FXCollections.observableArrayList(BrandManagerModel.brands));
            brandTab.refresh();

        }else if(ClickedButton.get()==ButtonType.NO){
            dialog.close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        noCol.setCellValueFactory(e ->
                new SimpleStringProperty(
                        String.valueOf(BrandManagerModel.brands.indexOf(e.getValue()))));
        idCol.setCellValueFactory(e ->
                new SimpleStringProperty(e.getValue().getBrandId()));
        nameCol.setCellValueFactory(e ->
                new SimpleStringProperty(e.getValue().getBrandName()));
        brandTab.setItems(FXCollections.observableList(BrandManagerModel.brands));
    }
}
