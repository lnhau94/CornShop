package Manager.Category.Controller;

import Entity.DAO;
import Entity.Entity.Brand;
import Entity.Entity.Category;
import Manager.Category.CategoryManagerModel;
import Manager.Helpers.ErrorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CategoryEdit {

    @FXML
    private TextField nameTxf;

    @FXML
    private TextField idTxf;
    private Category category;

    @FXML
    void cancel(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void editCategory(ActionEvent event) {
        if(isExistsName(nameTxf.getText())){
            new ErrorController().displayError("Name Exists");
        }
        else {
            category.setCategoryName(nameTxf.getText());
            try {
                DAO.execute(category.toUpdateQuery());
                ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setData(Category cat){
        this.category = cat;
        idTxf.setText(category.getCategoryId());
        nameTxf.setText(category.getCategoryName());
    }
    private boolean isExistsName(String name){
        for(Category c : CategoryManagerModel.categories){
            if(c.getCategoryName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
}
