package Manager.Category.Controller;

import Entity.Entity.Category;
import Manager.Brand.BrandManagerModel;
import Manager.Brand.Controller.BrandEdit;
import Manager.Category.CategoryManagerModel;
import Manager.Helpers.ErrorController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CategoryManager implements Initializable {

    @FXML
    private TableView<Category> categoryTab;

    @FXML
    private TableColumn<Category, String> noCol;

    @FXML
    private TableColumn<Category, String> idCol;

    @FXML
    private TableColumn<Category, String> nameCol;

    @FXML
    private Label header;

    @FXML
    void addNewCategory(ActionEvent event) {
        try {
            Stage s = new Stage(StageStyle.TRANSPARENT);
            s.setScene(new Scene(FXMLLoader.load(new File("Manager/Category/View/CategoryAdd.fxml")
                    .toURI().toURL())));
            s.initOwner((Stage)((Button)event.getSource()).getScene().getWindow());
            s.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void editCategory(ActionEvent event) {
        try {
            Stage s = new Stage(StageStyle.TRANSPARENT);
            FXMLLoader fx = new FXMLLoader(new File("Manager/Category/View/CategoryEdit.fxml")
                    .toURI().toURL());
            s.setScene(new Scene(fx.load()));
            CategoryEdit ce = fx.getController();
            ce.setData(categoryTab.getSelectionModel().getSelectedItem());
            s.initOwner((Stage)((Button)event.getSource()).getScene().getWindow());
            s.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e){
            new ErrorController().displayError("Nothing Choosen!!!");
        }
    }

    @FXML
    void removeCategory(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        Dialog<ButtonType> dialog = new Dialog<>();
        try {
            loader.setLocation(new File("Manager/Helpers/DeleteAlert.fxml").toURI().toURL());
            dialog.setDialogPane(loader.load());
            dialog.initStyle(StageStyle.TRANSPARENT);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Optional<ButtonType> ClickedButton = dialog.showAndWait();
        if(ClickedButton.get()==ButtonType.YES) {
            try {
                CategoryManagerModel.categories.remove(categoryTab.getSelectionModel().getSelectedItem());
            } catch (NullPointerException e){
                new ErrorController().displayError("Nothing Choosen!!!");
            }
            categoryTab.setItems(FXCollections.observableArrayList(CategoryManagerModel.categories));
            categoryTab.refresh();

        }else if(ClickedButton.get()==ButtonType.NO){
            dialog.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        noCol.setCellValueFactory(e -> new SimpleStringProperty(
                String.valueOf(CategoryManagerModel.categories.indexOf(e.getValue()))));
        idCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCategoryId()));
        nameCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCategoryName()));
        categoryTab.setItems(FXCollections.observableList(CategoryManagerModel.categories));

    }
}
