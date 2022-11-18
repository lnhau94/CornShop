package Manager.Storage.Controller;

import Entity.Entity.Storage;
import Manager.Product.ProductManagerModel;
import Manager.Storage.StorageManagerModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StorageManager implements Initializable {

    @FXML
    private Label header;

    @FXML
    private TableView<Storage> table;

    @FXML
    private TableColumn<Storage, String> noCol;

    @FXML
    private TableColumn<Storage, String> productIdCol;

    @FXML
    private TableColumn<Storage, String> productNameCol;

    @FXML
    private TableColumn<Storage, String> colorCol;

    @FXML
    private TableColumn<Storage, String> sizeCol;

    @FXML
    private TableColumn<Storage, String> qtyCol;

    @FXML
    private Button backWarehouseBtn;

    @FXML
    void createIncomeOrder(ActionEvent event) {
        FXMLLoader fx = new FXMLLoader();
        Parent root = null;
        try {
            fx.setLocation(new File("Income/View/IncomeChoose.fxml").toURI().toURL());
            root = fx.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((Stage)((Button)event.getSource()).getScene().getWindow()).setScene(new Scene(root));
    }

    @FXML
    void showOrderList(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        noCol.setCellValueFactory(e->new SimpleStringProperty(String.valueOf(StorageManagerModel.storages.indexOf(e.getValue()))));
        productIdCol.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findProductById(e.getValue().getProductId()).getProductId()));
        productNameCol.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findProductById(e.getValue().getProductId()).getProductName()));
        colorCol.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findColorById(e.getValue().getColorId()).getName()
        ));
        sizeCol.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findSizebyId(e.getValue().getSizeId()).getSize()
        ));
        qtyCol.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getQty())));
        table.setItems(FXCollections.observableArrayList(StorageManagerModel.storages));
    }
}
