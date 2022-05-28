package Store.ReturnOrder.Control;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import Entity.DAO;
import Entity.Entity.Order;
import Entity.Entity.OrderDetails;
import Store.ReturnOrder.Model.Inform;
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

    ObservableList<String> listColor = FXCollections.observableArrayList();
    ObservableList<Inform> listInfo = FXCollections.observableArrayList();
    ObservableList<OldOrder> listAddProd = FXCollections.observableArrayList();
    ObservableList<OldOrder> listDelProd = FXCollections.observableArrayList();

    ObservableList<Order> orders = FXCollections.observableArrayList();
    ObservableList<OrderDetails> orderDetails = FXCollections.observableArrayList();

    ObservableList<OldOrder> listOrder = FXCollections.observableArrayList();

    public void getDataOrder(String yourQuery) {
        ResultSet rs = DAO.executeQuery(String.format(yourQuery));
        try {
            while (rs.next()) {
                try {
                    orders.add(new Order(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getString(5), rs.getInt(6)));
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

    public void getData(String yourQuery, String orderId) {
        ResultSet rs = DAO.executeQuery(String.format(yourQuery, orderId));
        try {
            while (rs.next()) {
                try {
                    listOrder.add(new OldOrder(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));
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

    public void getDataDetails(String yourQuery) {
        ResultSet rs = DAO.executeQuery(String.format(yourQuery));
        try {
            while (rs.next()) {
                try {
                    orderDetails.add(new OrderDetails(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
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

    public void getDataInform(String yourQuery, String orderId) {
        ResultSet rs = DAO.executeQuery(String.format(yourQuery, orderId));
        try {
            while (rs.next()) {
                try {
                    listInfo.add(new Inform(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getInt(5)));
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

    public void getDataColor(String yourQuery, int productId) {
        ResultSet rs = DAO.executeQuery(String.format(yourQuery, productId));
        try {
            while (rs.next()) {
                try {
                    listColor.add(rs.getString(1));
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

    public ObservableList<String> listIdOrder() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Order order : orders) {
            list.add(order.getOrderId());
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
        listOrder.clear();
        listInfo.clear();
        nameEmp.setText("");
        dateOrder.setText("");
        phoneCus.setText("");
        totalQty.setText("");
        totalPrice.setText("");
        if (orderId.compareToIgnoreCase("") == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Mã hóa đơn bạn nhập không hợp lệ !");
            alert.showAndWait();
        }
        else {
            boolean checkOrder = false;
            for (Order order : orders) {
                if (orderId.compareToIgnoreCase(order.getOrderId()) == 0) {
                    checkOrder = true;
                    getData("select p.PRODUCTID, p.PRODUCTNAME, c.COLORNAME, s.[SIZE], o.QUANTITY, o.QUANTITY*p.PRICE from ORDERDETAILS o join PRODUCT p on p.ID = o.PRODUCTID join COLOR c on c.ID = o.COLORID join [SIZE] s on s.ID = o.SIZEID join ORDERS od ON od.ID = o.ORDERID where od.ORDERID like ('%s')", orderId);
                    getDataInform("select c.PHONE, e.EMPLOYEENAME, od.ORDERDATE, sum(odt.QUANTITY), od.TOTALPRICE, od.ORDERID from ORDERDETAILS odt join ORDERS od on od.ID = odt.ORDERID join EMPLOYEE e on e.ID = od.CASHIER join CUSTOMER c on c.PHONE like od.CUSTOMER	where od.ORDERID like ('%s') group by od.ORDERID, c.PHONE, od.ORDERDATE, e.EMPLOYEENAME, od.TOTALPRICE", orderId);
                    for (Inform info : listInfo) {
                        phoneCus.setText(info.getPhoneCus());
                        nameEmp.setText(info.getNameEmp());
                        dateOrder.setText(String.valueOf(info.getOrderDate()));
                        totalQty.setText(String.valueOf(info.getProductQty()));
                        totalPrice.setText(String.valueOf(info.getTotalPrice()));
                        break;
                    }
                    tableProduct.refresh();
                    break; 
                }
            }
            if (checkOrder == false) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Mã hóa đơn bạn nhập không hợp lệ !");
                alert.showAndWait();
                fieldOrderId.setText("");
            }
        }
    }

    public void editQtyColumn(TableColumn.CellEditEvent<OldOrder, Integer> qtyEditEvent) {
        OldOrder ord = tableProduct.getSelectionModel().getSelectedItem();
        int tmp = 0;
        if (qtyEditEvent.getNewValue() <= 0 && qtyEditEvent.getNewValue() != -1) {
            ord.setProductQty(qtyEditEvent.getOldValue());
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Số lượng bạn nhập không hợp lệ !");
            alert.showAndWait();
            tableProduct.refresh();
        }
        else {
            if (qtyEditEvent.getOldValue() > qtyEditEvent.getNewValue()) {
                countD+=(qtyEditEvent.getOldValue()-qtyEditEvent.getNewValue());
                tmp = (ord.getProductPrice()/qtyEditEvent.getOldValue()) * qtyEditEvent.getNewValue();
                listDelProd.add(new OldOrder(ord.getProductID(), ord.getProductName(), ord.getProductColor(), ord.getProductSize(), qtyEditEvent.getOldValue() - qtyEditEvent.getNewValue(), (ord.getProductPrice()/qtyEditEvent.getOldValue()) * qtyEditEvent.getNewValue()));
                for (int i = 0; i < listOrder.size(); i++) {
                    if (ord.getProductID().compareToIgnoreCase(listOrder.get(i).getProductID()) == 0) {
                        listOrder.get(i).setProductQty(qtyEditEvent.getNewValue());
                        listOrder.get(i).setProductPrice(tmp);
                        tmp=0;
                        countDel.setText(String.valueOf(countD));
                        tableProduct.refresh();
                        break;
                    }
                }
                for (int i = 0; i < listDelProd.size(); i++) {
                    System.out.println("Xóa: " + listDelProd.get(i).toString());
                }
            }
            if (qtyEditEvent.getOldValue() < qtyEditEvent.getNewValue()) {
                countD-=(qtyEditEvent.getNewValue()-qtyEditEvent.getOldValue());
                tmp = (ord.getProductPrice()/qtyEditEvent.getOldValue()) * qtyEditEvent.getNewValue();
                listAddProd.add(new OldOrder(ord.getProductID(), ord.getProductName(), ord.getProductColor(), ord.getProductSize(), qtyEditEvent.getNewValue() - qtyEditEvent.getOldValue(),(ord.getProductPrice()/qtyEditEvent.getOldValue()) * qtyEditEvent.getNewValue()));
                for (int i = 0; i < listOrder.size(); i++) {
                    if (ord.getProductID().compareToIgnoreCase(listOrder.get(i).getProductID()) == 0) {
                        listOrder.get(i).setProductQty(qtyEditEvent.getNewValue());
                        listOrder.get(i).setProductPrice(tmp);
                        tmp=0;
                        System.out.println(qtyEditEvent.getNewValue());
                        System.out.println(qtyEditEvent.getOldValue());
                        System.out.println(countD);
                        countDel.setText(String.valueOf(countD));
                        tableProduct.refresh();
                        break;
                    }
                }
                for (int i = 0; i < listAddProd.size(); i++) {
                    System.out.println("Thêm: " + listAddProd.get(i).toString());
                }
            }
        }
    }

    public void clearData(ActionEvent event) throws Exception {
        countD=0;
        countDel.setText(String.valueOf(countD));
        fieldOrderId.setText("");
        nameEmp.setText("");
        dateOrder.setText("");
        phoneCus.setText("");
        totalQty.setText("");
        totalPrice.setText("");
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
        orders.clear();
        orderDetails.clear();
        getDataOrder("select ID, ORDERID, ORDERDATE, TOTALPRICE, CUSTOMER, CASHIER from ORDERS where datediff(day, ORDERDATE, getdate()) <=7");
        getDataDetails("select ID, ORDERID, PRODUCTID, COLORID, SIZEID, QUANTITY from ORDERDETAILS");
        TextFields.bindAutoCompletion(fieldOrderId, listIdOrder());
        idProduct.setCellValueFactory(new PropertyValueFactory<OldOrder, String>("productID"));
        nameProduct.setCellValueFactory(new PropertyValueFactory<OldOrder, String>("productName"));
        colorProduct.setCellValueFactory(new PropertyValueFactory<OldOrder, String>("productColor"));
        colorProduct.setCellFactory(ComboBoxTableCell.forTableColumn(listColor));
        sizeProduct.setCellValueFactory(new PropertyValueFactory<OldOrder, String>("productSize"));
        sizeProduct.setCellFactory(ComboBoxTableCell.forTableColumn());
        qtyProduct.setCellValueFactory(new PropertyValueFactory<OldOrder, Integer>("productQty"));
        qtyProduct.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));
        priceProduct.setCellValueFactory(new PropertyValueFactory<OldOrder, Integer>("productPrice"));
        nameEmp.setText("");
        dateOrder.setText("");
        phoneCus.setText("");
        totalQty.setText("");
        totalPrice.setText("");
        countDel.setText(String.valueOf(countD));
        tableProduct.setItems(listOrder);
        tableProduct.setEditable(true);
        // tableProduct.getSelectionModel().getSelectedItem()
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}