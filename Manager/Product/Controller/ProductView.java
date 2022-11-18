package Manager.Product.Controller;

import Entity.Entity.Storage;
import Manager.Brand.BrandManagerModel;
import Manager.Category.CategoryManagerModel;
import Manager.Product.ProductManagerModel;
import Manager.Storage.StorageManagerModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductView implements Initializable {

    @FXML
    private TableView<Storage> proTab;

    @FXML
    private TableColumn<Storage, String> noCol;

    @FXML
    private TableColumn<Storage, String> idCol;

    @FXML
    private TableColumn<Storage, String> nameCol;

    @FXML
    private TableColumn<Storage, String> color;

    @FXML
    private TableColumn<Storage, String> size;

    @FXML
    private TableColumn<Storage, String> categoryCol;

    @FXML
    private TableColumn<Storage, String> brandCol;

    @FXML
    private Label header;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        noCol.setCellValueFactory(e->new SimpleStringProperty(String.valueOf(StorageManagerModel.storages.indexOf(e.getValue()))));
        idCol.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findProductById(e.getValue().getProductId()).getProductId()));
        nameCol.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findProductById(e.getValue().getProductId()).getProductName()));
        color.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findColorById(e.getValue().getColorId()).getName()
        ));
        size.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findSizebyId(e.getValue().getSizeId()).getSize()
        ));
        categoryCol.setCellValueFactory(e -> new SimpleStringProperty(CategoryManagerModel.findCategoryName(
                ProductManagerModel.findProductById(e.getValue().getProductId()).getCategoryId()
        )
        ));
        brandCol.setCellValueFactory(e -> new SimpleStringProperty(BrandManagerModel.findBrandName(
                ProductManagerModel.findProductById(e.getValue().getProductId()).getBrandId()
        )
        ));
        proTab.setItems(FXCollections.observableArrayList(StorageManagerModel.storages));
    }
}
