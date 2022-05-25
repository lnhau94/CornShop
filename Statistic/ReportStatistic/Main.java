package Statistic.ReportStatistic;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(new File("src/Statistic/ReportStatistic/View/RevenueStatistic.fxml").toURI().toURL());
            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(new File("src/Statistic/ReportStatistic/View/CSS/RevenueStatistic.css").toURI().toURL().toExternalForm());
            primaryStage.setTitle("REVENUE STATISTIC");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
