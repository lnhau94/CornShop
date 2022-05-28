package Store.ReturnOrder.Control;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import Entity.DAO;
import Entity.Entity.Color;
import Entity.Entity.Product;
import Entity.Entity.Size;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddProduct extends ScreenManager implements Initializable {

    @FXML
    private Label priceProd;

    @FXML
    private TextField productId;

    @FXML
    private ComboBox<String> boxCheck;

    @FXML
    private ComboBox<String> colorBox;

    @FXML
    private ComboBox<String> sizeBox;

    String styleBox[] = { "Mã sản phẩm", "Tên sản phẩm" };
    AutoCompletionBinding<String> fieldId, fieldName;
    ObservableList<Product> prods = FXCollections.observableArrayList();
    ObservableList<Color> colors = FXCollections.observableArrayList();
    ObservableList<Size> sizes = FXCollections.observableArrayList();

    public void getDataProd(String yourQuery) {
        ResultSet rs = DAO.executeQuery(String.format(yourQuery));
        try {
            while (rs.next()) {
                try {
                    prods.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7)));
                } catch (SQLException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Không thể liên kết để lấy dữ liệu !");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Không thể liên kết để lấy dữ liệu !");
            alert.showAndWait();
        }
    }

    public void getDataColor(String yourQuery) {
        ResultSet rs = DAO.executeQuery(String.format(yourQuery));
        try {
            while (rs.next()) {
                try {
                    colors.add(new Color(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
                } catch (SQLException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Không thể liên kết để lấy dữ liệu !");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Không thể liên kết để lấy dữ liệu !");
            alert.showAndWait();
        }
    }

    public void getDataSize(String yourQuery) {
        ResultSet rs = DAO.executeQuery(String.format(yourQuery));
        try {
            while (rs.next()) {
                try {
                    sizes.add(new Size(rs.getInt(1), rs.getString(2)));
                } catch (SQLException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Không thể liên kết để lấy dữ liệu !");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Không thể liên kết để lấy dữ liệu !");
            alert.showAndWait();
        }
    }

    public ObservableList<String> listId() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Product product : prods) {
            list.add(product.getProductId());
        }
        return list;
    }

    public ObservableList<String> listName() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Product product : prods) {
            list.add(product.getProductName());
        }
        return list;
    }

    public ObservableList<String> listColor() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Color cls : colors) {
            list.add(cls.getName());
        }
        return list;
    }

    public ObservableList<String> listSize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Size sze : sizes) {
            list.add(sze.getSize());
        }
        return list;
    }

    public void checkCombo(ActionEvent event) {
        if (boxCheck.getValue() != null) {
            if (boxCheck.getValue().compareToIgnoreCase("Mã sản phẩm") == 0) {
                fieldId = TextFields.bindAutoCompletion(productId, listId());
                if (fieldName != null) {
                    fieldName.dispose();
                }
            } else if (boxCheck.getValue().compareToIgnoreCase("Tên sản phẩm") == 0) {
                fieldName = TextFields.bindAutoCompletion(productId, listName());
                if (fieldId != null) {
                    fieldId.dispose();
                }
            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        boxCheck.setItems(FXCollections.observableArrayList(styleBox));
        getDataProd("select ID, PRODUCTID, PRODUCTNAME, PRICE, MATERIAL, CATEGORYID, BRANDID from PRODUCT");
        getDataColor("select ID, COLORNAME, RED, GREEN, BLUE from COLOR");
        getDataSize("select ID, [SIZE] from [SIZE]");
        colorBox.setItems(FXCollections.observableArrayList(listColor()));
        sizeBox.setItems(FXCollections.observableArrayList(listSize()));
    }
    
}
