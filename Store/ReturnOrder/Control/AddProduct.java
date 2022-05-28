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
import Store.ReturnOrder.Model.OldOrder;
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

    @FXML
    private TextField qtyProd;

    String styleBox[] = { "Mã sản phẩm", "Tên sản phẩm" };
    AutoCompletionBinding<String> fieldId, fieldName;
    ObservableList<Product> prods = FXCollections.observableArrayList();
    ObservableList<Product> prodTmp = FXCollections.observableArrayList();
    ObservableList<Color> colors = FXCollections.observableArrayList();
    ObservableList<Size> sizes = FXCollections.observableArrayList();
    ObservableList<String> tmp = FXCollections.observableArrayList();
    String oldValue;
    String newValue;
    ReturnOrder parent;

    public void setParent(ReturnOrder parent) {
        this.parent = parent;
    }

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
        for (Product pd : prods) {
            list.add(pd.getProductId());
        }
        return list;
    }

    public ObservableList<String> listName() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Product pd : prods) {
            list.add(pd.getProductName());
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
                ObservableList<String> list = listId();
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

    public void addNew(ActionEvent event) throws Exception{
        boolean checkId = false;
        boolean checkName = false;
        if (ReturnOrder.listOrder.size() <= 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Chưa chọn hóa đơn để thêm sản phẩm khác !");
            alert.showAndWait();
        }
        else {
            if (productId.getText().compareToIgnoreCase("") == 0 || colorBox.getValue().compareToIgnoreCase("") == 0 || sizeBox.getValue().compareToIgnoreCase("") == 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Không thể thêm sản phẩm vì thiếu dữ liệu !");
                alert.showAndWait();
            }
            else {
                if (boxCheck.getValue().compareToIgnoreCase("Mã sản phẩm") == 0) {
                    for (Product pd : prods) {
                        if (productId.getText().compareToIgnoreCase(pd.getProductId()) == 0) {
                            checkId = true;
                            try {
                                if (Integer.parseInt(qtyProd.getText()) > 0) {
                                    parent.listOrder.add(new OldOrder(pd.getProductId(), pd.getProductName(), colorBox.getValue(), sizeBox.getValue(), Integer.parseInt(qtyProd.getText()), Integer.parseInt(priceProd.getText())));
                                }
                                else {
                                    Alert alert = new Alert(AlertType.ERROR);
                                    alert.setHeaderText(null);
                                    alert.setContentText("Số lượng bị sai !");
                                    alert.showAndWait();
                                    qtyProd.setText("");
                                }
                            } catch (Exception e) {
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setHeaderText(null);
                                alert.setContentText("Số lượng bị sai !");
                                alert.showAndWait();
                                qtyProd.setText("");
                            }
                            parent.refreshTable();
                        }
                    }
                    if (checkId == false) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Mã không tồn tại !");
                        alert.showAndWait();
                    }
                }
                if (boxCheck.getValue().compareToIgnoreCase("Tên sản phẩm") == 0) {
                    for (Product pd : prods) {
                        if (productId.getText().compareToIgnoreCase(pd.getProductName()) == 0) {
                            checkName = true;
                            try {
                                if (Integer.parseInt(qtyProd.getText()) > 0) {
                                    parent.listOrder.add(new OldOrder(pd.getProductId(), pd.getProductName(), colorBox.getValue(), sizeBox.getValue(), Integer.parseInt(qtyProd.getText()), Integer.parseInt(priceProd.getText())));
                                }
                                else {
                                    Alert alert = new Alert(AlertType.ERROR);
                                    alert.setHeaderText(null);
                                    alert.setContentText("Số lượng bị sai !");
                                    alert.showAndWait();
                                    qtyProd.setText("");
                                }
                            } catch (Exception e) {
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setHeaderText(null);
                                alert.setContentText("Số lượng bị sai !");
                                alert.showAndWait();
                                qtyProd.setText("");
                            }
                            parent.refreshTable();
                        }
                    }
                    if (checkName == false) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Tên không tồn tại !");
                        alert.showAndWait();
                    }
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
        qtyProd.textProperty().addListener((prodTmp, oldValue, newValue) -> {
            for (Product pd : prods) {
                if (productId.getText().compareToIgnoreCase(pd.getProductId()) == 0 || productId.getText().compareToIgnoreCase(pd.getProductName()) == 0 ) {
                    try {
                        priceProd.setText(String.valueOf(pd.getPrice() * Integer.parseInt(newValue)));
                    } catch (Exception e) {
                    }
                }
            }
        });
        productId.textProperty().addListener((prodTmp, oldValue, newValue) -> {
            priceProd.setText("");
            for (Product pd : prods) {
                if (newValue.compareToIgnoreCase(pd.getProductId()) == 0 || newValue.compareToIgnoreCase(pd.getProductName()) == 0) {
                    if (qtyProd.getText().compareToIgnoreCase("") == 0) {
                        priceProd.setText(String.valueOf(pd.getPrice()));
                    }
                    else {
                        priceProd.setText(String.valueOf(pd.getPrice() * Integer.parseInt(qtyProd.getText())));
                    }
                }
            }
        });
    }
    
}
