package Income.Controller;

import Income.IncomeModel;
import javafx.event.ActionEvent;

public class MasterController {
    public static ScreenTranfer screenTranfer;
    public static IncomeModel model;

    public void changeScene(ActionEvent e){
        screenTranfer.switchScene(e);
    }

    public static void start() {
        model = new IncomeModel();
        model.createNewOrder();
        MasterController.screenTranfer = new ScreenTranfer();
    }
}
