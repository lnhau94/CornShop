package Manager.AccountManager.Controller;

import Entity.DAO;
import Entity.Entity.Account;
import Manager.AccountManager.AccountManagerModel;
import Manager.Helpers.ErrorController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AccountManager implements Initializable {


    @FXML
    private TableView<Account> accTab;
    @FXML
    private TableColumn<Account, String> noCol;

    @FXML
    private TableColumn<Account, String> accCol;

    @FXML
    private TableColumn<Account, String> passCol;

    @FXML
    private TableColumn<Account, String> staffCol;

    @FXML
    private Label header;

    AccountManagerModel model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        model = new AccountManagerModel();
        noCol.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(AccountManagerModel.accounts.indexOf(e.getValue()))));
        accCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getUsername()));
        passCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getPassword()));
        staffCol.setCellValueFactory(e -> new SimpleStringProperty(model.getEmployeeName(e.getValue().getEmployeeId())));
        accTab.setItems(FXCollections.observableList(AccountManagerModel.accounts));

    }

    public void createNewAccount(){
        FXMLLoader loader = new FXMLLoader();

        Pane pane = null;
        try {
            loader.setLocation(new File("Manager/AccountManager/View/AccountManagerAdd.fxml").toURI().toURL());
            pane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane((DialogPane) pane);
        AccountManagerAddController controller = loader.getController();
        controller.handleEvent();
        Optional<ButtonType> ClickedButton = dialog.showAndWait();
        if(ClickedButton.get()==ButtonType.APPLY) {
            if(!controller.checkPassword()){
                ErrorController errorController = new ErrorController();
                errorController.displayError("password");
            }else{
                try {
                    controller.excuteCheck();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            accTab.setItems(FXCollections.observableArrayList(AccountManagerModel.accounts));
            accTab.refresh();

        }else if(ClickedButton.get()==ButtonType.CLOSE){
            dialog.close();
        }
    }

    public void editAccount(){
        FXMLLoader loader = new FXMLLoader();

        Pane pane = null;
        try {
            loader.setLocation(new File("Manager/AccountManager/View/AccountEdit.fxml").toURI().toURL());
            pane = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane((DialogPane) pane);
        AccountEdit controller = loader.getController();
        Account a = accTab.getSelectionModel().getSelectedItem();
        if(a==null){
            new ErrorController().displayError("Nothing choosen");

        }else{
            controller.setData(a);
            Optional<ButtonType> ClickedButton = dialog.showAndWait();
            if(ClickedButton.get()==ButtonType.APPLY) {
                if(!controller.checkPassword()){
                    ErrorController errorController = new ErrorController();
                    errorController.displayError("password");
                }else{
                    controller.updateAccount(a);
                }
                accTab.setItems(FXCollections.observableArrayList(AccountManagerModel.accounts));
                accTab.refresh();

            }else if(ClickedButton.get()==ButtonType.CLOSE){
                dialog.close();
            }
        }

    }

    public void removeAccount(){
        int index = accTab.getSelectionModel().getSelectedIndex();
        if(index>=0){
            FXMLLoader loader = new FXMLLoader();
            Dialog<ButtonType> dialog = new Dialog<>();
            try {
                loader.setLocation(new File("Manager/Helpers/DeleteAlert.fxml").toURI().toURL());
                dialog.setDialogPane(loader.load());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Optional<ButtonType> ClickedButton = dialog.showAndWait();
            if(ClickedButton.get()==ButtonType.YES) {
                try {
                    DAO.execute(AccountManagerModel.accounts.remove(index).toDeleteQuery());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                accTab.setItems(FXCollections.observableArrayList(AccountManagerModel.accounts));
                accTab.refresh();

            }else if(ClickedButton.get()==ButtonType.NO){
                dialog.close();
            }
        }else{
            new ErrorController().displayError("Nothing choosen");
        }
    }
}
