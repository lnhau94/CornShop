package Income.Controller;

import Entity.Entity.IncomeDetails;
import Entity.Entity.Storage;
import Income.IncomeModel;
import Manager.Brand.BrandManagerModel;
import Manager.Category.CategoryManagerModel;
import Manager.Product.ProductManagerModel;
import Manager.Storage.StorageManagerModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;


import java.net.URL;
import java.util.ResourceBundle;

public class IncomeChoose extends MasterController implements Initializable{

    @FXML
    private TableView<Storage> tableChoose;
    @FXML
    private TableView<IncomeDetails> tableOrder;
    @FXML
    private TableColumn<Storage, String> idCol;
    @FXML
    private TableColumn<Storage, String> nameCol;
    @FXML
    private TableColumn<Storage, String> priceCol;
    @FXML
    private TableColumn<Storage, String> colorCol;
    @FXML
    private TableColumn<Storage, String> sizeCol;
    @FXML
    private TableColumn<Storage, String> typeCol;
    @FXML
    private TableColumn<Storage, String> brandCol;

    @FXML
    private TableColumn<IncomeDetails, String> idColOrd;
    @FXML
    private TableColumn<IncomeDetails, String> nameColOrd;
    @FXML
    private TableColumn<IncomeDetails, Integer> qtyCol;

    @FXML
    private TableColumn<IncomeDetails, String> colorColOrd;
    @FXML
    private TableColumn<IncomeDetails, String> sizeColOrd;

    private IncomeModel model;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.model = MasterController.model;
        idCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
        nameCol.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findProductById(e.getValue().getProductId()).getProductName()
        ));
        brandCol.setCellValueFactory(e -> new SimpleStringProperty(
                BrandManagerModel.findBrandName(
                        ProductManagerModel.findProductById(e.getValue().getProductId()).getBrandId())
        ));
        typeCol.setCellValueFactory(e -> new SimpleStringProperty(
                CategoryManagerModel.findCategoryName(
                        ProductManagerModel.findProductById(e.getValue().getProductId()).getCategoryId())
        ));
        priceCol.setCellValueFactory(e -> new SimpleStringProperty(
                String.valueOf(ProductManagerModel.findProductById(e.getValue().getProductId()).getPrice())
        ));
        colorCol.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findColorById(e.getValue().getColorId()).getName()
        ));
        sizeCol.setCellValueFactory((e -> new SimpleStringProperty(
                ProductManagerModel.findSizebyId(e.getValue().getSizeId()).getSize()
        )));

//        this.model.createNewOrder();
        tableChoose.setItems(FXCollections.observableList(StorageManagerModel.storages));


        idColOrd.setCellValueFactory(new PropertyValueFactory<>("storageId"));
        nameColOrd.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findProductById(StorageManagerModel.findById(
                        e.getValue().getStorageId()).getProductId()).getProductName()
        ));
        colorColOrd.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findColorById(StorageManagerModel.findById(
                        e.getValue().getStorageId()).getColorId()).getName()
        ));
        sizeColOrd.setCellValueFactory(e -> new SimpleStringProperty(
                ProductManagerModel.findSizebyId(StorageManagerModel.findById(
                        e.getValue().getStorageId()).getSizeId()).getSize()
        ));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tableOrder.setItems(this.model.currentChoice);
        editableCols();

    }

    private void editableCols() {
        qtyCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer integer) {
                return String.valueOf(integer);
            }
            @Override
            public Integer fromString(String s) {
                int value = model.getCurrentChoice().get(tableOrder.getSelectionModel().getSelectedIndex()).getQuantity();
                try {
                   if(Integer.parseInt(s) > 0) {
                        value = Integer.parseInt(s);
                   }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Enter number please");
                    alert.show();
                }
                return value;
            }
        }));
        qtyCol.setOnEditCommit(e -> e.getTableView().getItems().get(
                e.getTablePosition().getRow()).setQuantity(e.getNewValue()));

        this.tableOrder.setEditable(true);
    }

    public void addItem(){
        Storage i = tableChoose.getSelectionModel().getSelectedItem();
        if(i != null) {
            IncomeDetails incomeDetail = new IncomeDetails(i, 1);
            this.model.addChosenItem(incomeDetail);
        }
    }


    public void deleteItem(ActionEvent e) {
        IncomeDetails inD = tableOrder.getSelectionModel().getSelectedItem();
        if(inD != null) {
            this.model.removeChosenItem(inD);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Choose a row please");
            alert.show();
        }
//        System.out.println(model.getCurrentChoices().get(tableOrder.getSelectionModel().getSelectedIndex()).getOrderQty());
    }

    public void checkChoose(ActionEvent e) {
        if(this.model.getCurrentChoice().size() <= 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Fail");
            alert.setContentText("You have not chosen any items to order");
            alert.showAndWait();
        }
        else {
            MasterController.screenTranfer.switchScene(e);
        }

    }
}
