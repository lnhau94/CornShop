package Manager.Brand.Controller;

import Entity.DAO;
import Entity.Entity.Brand;
import Manager.Brand.BrandManagerModel;
import Manager.Helpers.ErrorController;
import Manager.Supplier.SupplierManagerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BrandEdit {

    @FXML
    private TextField nameTxf;

    @FXML
    private TextField supplierTxf;

    private Brand brand;

    @FXML
    void cancel(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void editBrand(ActionEvent event) {
        if(isExistsName(nameTxf.getText())){
            new ErrorController().displayError("Name Exists");
        }
        else {
            brand.setBrandName(nameTxf.getText());
            try {
                DAO.execute(brand.toUpdateQuery());
                ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setData(Brand b){
        this.brand = b;
        supplierTxf.setText(SupplierManagerModel.findById(brand.getSupplierId()).getSupplierName());
        nameTxf.setText(brand.getBrandName());
    }

    private boolean isExistsName(String names){
        for (Brand b : BrandManagerModel.brands){
            if(b.getBrandName().equalsIgnoreCase(names)){
                return true;
            }
        }
        return false;
    }
}
