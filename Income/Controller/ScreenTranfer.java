package Income.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ScreenTranfer {
    private final HashMap<String, String> url = new HashMap<>() {
        {
            put("incomeCfmBtn", "Income/View/IncomeConfirmOrder.fxml");
            put("orderListBtn", "Income/View/IncomeOrderList.fxml");
            put("newOrderBtn", "Income/View/IncomeChoose.fxml");
//            put("backWarehouseBtn", "Income/View/IngredientOrder.fxml");
            put("frmIncomeOrder", "Income/View/IncomeFormOrder.fxml");
        }
    };

    private HashMap<String, Scene> sceneList;

    public ScreenTranfer() {
        this.initSceneList();
    }

    private void initSceneList() {
        this.sceneList = new HashMap<>();
        for(String btn : this.url.keySet()) {
            try {
                sceneList.put(btn, new Scene(FXMLLoader.load(new File(url.get(btn)).toURI().toURL())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private Scene getScene(Button btn) {
        return sceneList.get(btn.getId());
    }

    public void switchScene(ActionEvent e){
//        Stage owner = ((Stage) ((Node)e.getSource()).getScene().getWindow());
//        owner.setScene(getScene((Button) e.getSource()));
//        owner.centerOnScreen();
        Stage owner = ((Stage) ((Node)e.getSource()).getScene().getWindow());
        try {
            ((Stage) ((Node)e.getSource()).getScene().getWindow()).setScene(
                    new Scene(FXMLLoader.load(new File(url.get(((Button) e.getSource()).getId())).toURI().toURL())));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        owner.centerOnScreen();
    }

}
