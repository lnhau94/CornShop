package Manager.MainScreen;

import Manager.MainScreen.Controller.ManagerScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class ManagerControl {
    ManagerScreenController controller;
    Scene managerView;

    public ManagerControl(){
        try {
            FXMLLoader fx = new FXMLLoader(new File("Manager/MainScreen/View/ManagerScreen.fxml").toURI().toURL());
            managerView = new Scene(fx.load());
            controller = fx.getController();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ManagerScreenController getController() {
        return controller;
    }

    public Scene getManagerView() {
        return managerView;
    }
}
