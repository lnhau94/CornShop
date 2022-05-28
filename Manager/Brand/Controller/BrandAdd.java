package Manager.Brand.Controller;

import Entity.DAO;
import Entity.Entity.Brand;
import Entity.Entity.Supplier;
import Manager.Brand.BrandManagerModel;
import Manager.Helpers.ErrorController;
import Manager.Supplier.SupplierManagerModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BrandAdd implements Initializable {

    @FXML
    private TextField nameTxf;
    @FXML
    private ComboBox<Supplier> supplierCbb;

    @FXML
    void addNewBrand(ActionEvent event) {
        if(supplierCbb.getValue()==null){
            new ErrorController().displayError("Please Choice Supplier!!");
        }else if(isExistsName(nameTxf.getText())){
            new ErrorController().displayError("Name Exists");
        }
        else {
            Brand b = new Brand();
            b.setBrandName(nameTxf.getText());
            b.setSupplierId(supplierCbb.getValue().getId());
            try {
                DAO.execute(b.toInsertQuery());
                ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    private boolean isExistsName(String names){
        for (Brand b : BrandManagerModel.brands){
            if(b.getBrandName().equalsIgnoreCase(names)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        supplierCbb.setItems(FXCollections.observableList(SupplierManagerModel.suppliers));
        supplierCbb.setConverter(new StringConverter<Supplier>() {
            @Override
            public String toString(Supplier supplier) {
                if(supplier!=null){
                    return supplier.getSupplierName();
                }
                return null;
            }

            @Override
            public Supplier fromString(String s) {
                return null;
            }
        });
    }
}
