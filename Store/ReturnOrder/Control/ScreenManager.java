package Store.ReturnOrder.Control;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ScreenManager {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public ReturnOrder parentScene;

    public void screenReturnOrder(ActionEvent event) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader();
        // fxmlLoader.setLocation(new File("src/Store/ReturnOrder/View/ReturnOrder.fxml").toURI().toURL());
        // root = (Parent) fxmlLoader.load();
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        // scene = new Scene(root);
        // scene.getStylesheets().add(new File("src/Store/ReturnOrder/View/CSS/ReturnOrder.css").toURI().toURL().toExternalForm());
        // stage.setScene(scene);
        // stage.show();
    }

    public void screenChangeOrder(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new File("src/Store/ReturnOrder/View/AddProduct.fxml").toURI().toURL());
        root = (Parent) fxmlLoader.load();
        Stage stage1 = new Stage();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(new File("src/Store/ReturnOrder/View/CSS/AddProduct.css").toURI().toURL().toExternalForm());
        stage1.setScene(scene);
        stage1.initOwner(stage);
        stage1.showAndWait();
    }
}
