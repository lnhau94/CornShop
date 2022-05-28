package Income.Controller;


import Entity.Entity.IncomeDetails;
import Income.IncomeModel;
import Manager.Brand.BrandManagerModel;
import Manager.Category.CategoryManagerModel;
import Manager.Product.ProductManagerModel;
import Manager.Storage.StorageManagerModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class IncomeFormOrder extends MasterController implements Initializable {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldID;
    @FXML
    private TextField dateTxt;
    @FXML
    private TextField textFieldSupplier;


    @FXML
    private TableView<IncomeDetails> table;
    @FXML
    private TableColumn<IncomeDetails, String> idCol;
    @FXML
    private TableColumn<IncomeDetails, String> nameCol;
    @FXML
    private TableColumn<IncomeDetails, String> typeCol;
    @FXML
    private TableColumn<IncomeDetails, String> brandCol;
    @FXML
    private TableColumn<IncomeDetails, String> priceCol;
    @FXML
    private TableColumn<IncomeDetails, String> sizeCol;
    @FXML
    private TableColumn<IncomeDetails, String> colorCol;
    @FXML
    private TableColumn<IncomeDetails, Integer> qtyCol;


    private Date now;
    private IncomeModel model;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.model = MasterController.model;
        idCol.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findProductById(StorageManagerModel.findById(
                        e.getValue().getStorageId()).getProductId()).getProductId()
        ));
        nameCol.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findProductById(StorageManagerModel.findById(
                        e.getValue().getStorageId()).getProductId()).getProductName()
        ));
        brandCol.setCellValueFactory(e -> new SimpleStringProperty(
                BrandManagerModel.findBrandName(
                        ProductManagerModel.findProductById(
                                StorageManagerModel.findById(
                        e.getValue().getStorageId()).getProductId()).getBrandId())
        ));
        typeCol.setCellValueFactory(e -> new SimpleStringProperty(
                CategoryManagerModel.findCategoryName(
                        ProductManagerModel.findProductById(
                                StorageManagerModel.findById(
                        e.getValue().getStorageId()).getProductId()).getCategoryId())
        ));
        priceCol.setCellValueFactory(e -> new SimpleStringProperty(
                        String.valueOf(ProductManagerModel.findProductById(
                                StorageManagerModel.findById(
                                        e.getValue().getStorageId()).getProductId()).getPrice())
        ));
        colorCol.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findColorById(
                        StorageManagerModel.findById(
                                e.getValue().getStorageId()).getProductId()).getName()
        ));
        sizeCol.setCellValueFactory((e -> new SimpleStringProperty(
                ProductManagerModel.findSizebyId(
                        StorageManagerModel.findById(
                                e.getValue().getStorageId()).getProductId()).getSize()
        )));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table.setItems(this.model.currentChoice);

        now = new Date();
        dateTxt.setText(new SimpleDateFormat("dd-MM-yyyy").format(now));
    }

    public void sendOrder(ActionEvent e) {
//        this.model.getCurrentOrder().setStatus("Waiting");
//        System.out.println(java.sql.Date.valueOf(
//                new SimpleDateFormat("yyyy-MM-dd").format(now)
//        ).getClass().getName());
        this.model.getCurrentOrder().setCreateDate(java.sql.Date.valueOf(
                new SimpleDateFormat("yyyy-MM-dd").format(now)
        ));
//        this.model.getCurrentOrder().setStoreKeeper(this.textFieldID.getText());
//        this.model.getCurrentOrder().setSupplierId(this.textFieldSupplier.getText());

//        this.model.saveIncomeReport();
//        this.model.saveIncomeDetails();

//        this.model.getWaitingInReport().add(this.model.getIncomeReport());
//        this.modelExtra.getIncomeReports().add(this.model.getIncomeReport());


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Send Successfully");
        alert.showAndWait();
        screenTranfer.switchScene(e);
    }
}
