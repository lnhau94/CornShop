package Statistic.ReportEndDay.Control;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import Entity.DAO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

public class ReportEndDay extends ScreenManager implements Initializable{
    @FXML
    private Button btnConfirm;

    @FXML
    private Label dateLabel;

    @FXML
    private Label nameEmp;

    @FXML
    private Label totalClothes;

    @FXML
    private Label totalOrder;

    @FXML
    private Label totalRevenue;

    public String getData(String yourQuery, String date) {
        String value = "";
        ResultSet rs =null;
        try {
            rs = DAO.executeQuery(String.format(yourQuery, date));
            while (rs.next()) {
                try {
                    value = rs.getString(1);
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
        return value;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String time = "";
        String valueTotalRevenue = "";
        String valueTotalOrder = "";
        String valueTotalClothes = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = new Date(); 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Calendar cal = Calendar.getInstance();
                dateLabel.setText(dateFormat.format(cal.getTime()));
            }
        }));
        time = formatter.format(date); 
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        valueTotalRevenue = getData("select sum(TotalPrice) from Orders where OrderDate = ('%s') group by OrderDate", time);
        if (valueTotalRevenue.compareToIgnoreCase("") == 0) {
            valueTotalRevenue = "0";
        }
        totalRevenue.setText(valueTotalRevenue);
        valueTotalOrder = getData("select count(OrderID) from Orders where OrderDate = ('%s') group by OrderDate", time);
        if (valueTotalOrder.compareToIgnoreCase("") == 0) {
            valueTotalOrder = "0";
        }
        totalOrder.setText(valueTotalOrder);
        valueTotalClothes = getData("select sum(odt.Quantity) from Orders od join OrderDetails odt on odt.OrderID = od.ID where od.OrderDate = ('%s') group by OrderDate", time);
        if (valueTotalClothes.compareToIgnoreCase("") == 0) {
            valueTotalClothes = "0";
        }
        totalClothes.setText(valueTotalClothes);
        nameEmp.setText("Phạm Nguyễn Đức Huy");
    }
}
