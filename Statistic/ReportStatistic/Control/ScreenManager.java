package Statistic.ReportStatistic.Control;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenManager {
    private Stage stage;
    private Scene scene;
    public Parent root;

    public void screenRevenueStatistic(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new File("Statistic/ReportStatistic/View/RevenueStatistic.fxml").toURI().toURL());
        root = (Parent) fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(new File("Statistic/ReportStatistic/View/CSS/RevenueStatistic.css").toURI().toURL().toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void screenProductStatistic(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new File("Statistic/ReportStatistic/View/ProductStatistic.fxml").toURI().toURL());
        root = (Parent) fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(new File("Statistic/ReportStatistic/View/CSS/ProductStatistic.css").toURI().toURL().toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void screenCategoryStatistic(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new File("Statistic/ReportStatistic/View/CategoryStatistic.fxml").toURI().toURL());
        root = (Parent) fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(new File("Statistic/ReportStatistic/View/CSS/CategoryStatistic.css").toURI().toURL().toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
