package Store.ReturnOrder.Control;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import Store.ReturnOrder.Model.OldOrder;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

public class ReturnOrder extends ScreenManager implements Initializable {
    @FXML
    private Button btnClear;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnSuccess;

    @FXML
    private TextField fieldOrderId;

    @FXML
    private TableColumn<OldOrder, String> idProduct;

    @FXML
    private TableColumn<OldOrder, String> nameProduct;

    @FXML
    private TableColumn<OldOrder, String> colorProduct;

    @FXML
    private TableColumn<OldOrder, String> sizeProduct;

    @FXML
    private TableColumn<OldOrder, Integer> qtyProduct;

    @FXML
    private TableColumn<OldOrder, Integer> priceProduct;

    @FXML
    private TableView<OldOrder> tableProduct;

    @FXML
    private Label countDel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label nameEmp;

    @FXML
    private Label dateOrder;

    @FXML
    private Label phoneCus;

    @FXML
    private Label totalQty;

    @FXML
    private Label totalPrice;

    static int countD = 0;

    public class CustomIntegerStringConverter extends IntegerStringConverter {
        private final IntegerStringConverter converter = new IntegerStringConverter();

        @Override
        public String toString(Integer object) {
            try {
                return converter.toString(object);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Số lượng bạn nhập không hợp lệ !");
                alert.showAndWait();
                tableProduct.refresh();
            }
            return null;
        }

        @Override
        public Integer fromString(String string) {
            try {
                return converter.fromString(string);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Số lượng bạn nhập không hợp lệ !");
                alert.showAndWait();
                tableProduct.refresh();
            }
            return -1;
        }
    }

    static String date;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat dateFormatData = new SimpleDateFormat("yyyy-MM-dd");
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Calendar cal = Calendar.getInstance();
            timeLabel.setText(dateFormat.format(cal.getTime()));
            date = dateFormatData.format(cal.getTime());
        }
    }));

    ObservableList<OldOrder> listTmp = FXCollections.observableArrayList();
    ObservableList<OldOrder> listAddProd = FXCollections.observableArrayList();
    ObservableList<OldOrder> listDelProd = FXCollections.observableArrayList();

    ObservableList<OldOrder> listOrder = FXCollections.observableArrayList(
        new OldOrder("PRD001", "Áo thun 3 lỗ", "Trắng", "L", 2, 72000),
        new OldOrder("PRD002", "Áo sơ mi", "Đỏ","L", 1, 90000),
        new OldOrder("PRD003", "Áo khoác", "Xanh","M", 1, 130000)
    );

    public ObservableList<String> listIdOrder() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (OldOrder order : listOrder) {
            list.add(order.getProductID());
        }
        return list;
    }

    public void successChange(ActionEvent event) throws Exception {
        if (countD == 0 && (listDelProd.size() > 0 || listAddProd.size() >= 0)) {
            for (int i = 0; i < listDelProd.size(); i++) {
                break;
            }
            for (int i = 0; i < listAddProd.size(); i++) {
                break;
            }
        }
    }

    public void loadOrder(ActionEvent event) throws Exception {
        String orderId = "";
        orderId = fieldOrderId.getText().toUpperCase();
        if (orderId.compareToIgnoreCase("") == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Mã hóa đơn bạn nhập không hợp lệ !");
            alert.showAndWait();
        }
        else {
            
        }
    }

    public void editQtyColumn(TableColumn.CellEditEvent<OldOrder, Integer> qtyEditEvent) {
        OldOrder ord = tableProduct.getSelectionModel().getSelectedItem();
        if (qtyEditEvent.getNewValue() <= 0 && qtyEditEvent.getNewValue() != -1) {
            ord.setProductQty(qtyEditEvent.getOldValue());
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Số lượng bạn nhập không hợp lệ !");
            alert.showAndWait();
            tableProduct.refresh();
        }
        if (qtyEditEvent.getOldValue() > qtyEditEvent.getNewValue()) {
            listDelProd.add(new OldOrder(ord.getProductID(), ord.getProductName(), ord.getProductColor(), ord.getProductSize(), qtyEditEvent.getOldValue() - qtyEditEvent.getNewValue(),36000));
            for (int i = 0; i < listOrder.size(); i++) {
                if (ord.getProductID().compareToIgnoreCase(listOrder.get(i).getProductID()) == 0) {
                    listOrder.get(i).setProductQty(qtyEditEvent.getNewValue());
                    countD++;
                    countDel.setText(String.valueOf(countD));
                    tableProduct.refresh();
                }
            }
            for (int i = 0; i < listDelProd.size(); i++) {
                System.out.println("Xóa: " + listDelProd.get(i).toString());
            }
        }
        if (qtyEditEvent.getOldValue() < qtyEditEvent.getNewValue()) {
            listAddProd.add(new OldOrder(ord.getProductID(), ord.getProductName(), ord.getProductColor(), ord.getProductSize(), qtyEditEvent.getNewValue() - qtyEditEvent.getOldValue(),36000));
            for (int i = 0; i < listOrder.size(); i++) {
                if (ord.getProductID().compareToIgnoreCase(listOrder.get(i).getProductID()) == 0) {
                    listOrder.get(i).setProductQty(qtyEditEvent.getNewValue());
                    countD--;
                    countDel.setText(String.valueOf(countD));
                    tableProduct.refresh();
                }
            }
            for (int i = 0; i < listAddProd.size(); i++) {
                System.out.println("Thêm: " + listAddProd.get(i).toString());
            }
        }
    }

    public void clearData(ActionEvent event) throws Exception {
        fieldOrderId.setText("");
        tableProduct.getItems().clear();
    }

    public void delCol(ActionEvent event) throws Exception {
        try {
            for (int i = 0; i < tableProduct.getSelectionModel().getSelectedItem().getProductQty(); i++) {
                countD--;
            }
            countDel.setText(String.valueOf(countD));
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn dòng bạn muốn xóa !");
            alert.showAndWait();
        }
        tableProduct.getItems().removeAll(tableProduct.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        TextFields.bindAutoCompletion(fieldOrderId, listIdOrder());
        idProduct.setCellValueFactory(new PropertyValueFactory<OldOrder, String>("productID"));
        nameProduct.setCellValueFactory(new PropertyValueFactory<OldOrder, String>("productName"));
        colorProduct.setCellValueFactory(new PropertyValueFactory<OldOrder, String>("productColor"));
        colorProduct.setCellFactory(ComboBoxTableCell.forTableColumn());
        sizeProduct.setCellValueFactory(new PropertyValueFactory<OldOrder, String>("productSize"));
        sizeProduct.setCellFactory(ComboBoxTableCell.forTableColumn());
        qtyProduct.setCellValueFactory(new PropertyValueFactory<OldOrder, Integer>("productQty"));
        qtyProduct.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));
        priceProduct.setCellValueFactory(new PropertyValueFactory<OldOrder, Integer>("productPrice"));
        nameEmp.setText("Phạm Nguyễn Đức Huy");
        dateOrder.setText("2022-05-26");
        phoneCus.setText("0909123456");
        totalQty.setText("4");
        totalPrice.setText("300000");
        countDel.setText(String.valueOf(countD));
        tableProduct.setItems(listOrder);
        tableProduct.setEditable(true);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
