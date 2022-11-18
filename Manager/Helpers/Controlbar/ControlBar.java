package Manager.Helpers.Controlbar;

import Manager.MainScreen.ManagerControl;
import Store.POS.Control.PosController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class ControlBar extends VBox {

    ToggleButton saleBtn, EoDsBtn, dataBtn, returnOrderBtn, ingredientBtn;
    Scene salesScreen;
    Scene eodScreen;
    Scene dataManagerScreen;
    Scene ingredientManagerScreen;
    Scene returnOrderScreen;
    Scene loginScreen;
    Stage owner;
    Button logoutBtn,exitBtn;


    public ControlBar(Stage stage){
        this.owner = stage;
        start();
    }


    public void showFunction(int lvl){
        this.getChildren().clear();
        switch (lvl) {
            case 0 -> {
                owner.setScene(loginScreen);
                this.getChildren().add(exitBtn);
            }
            case 1 -> {
                this.getChildren().add(saleBtn);
                this.getChildren().add(returnOrderBtn);
                this.getChildren().add(EoDsBtn);
                saleBtn.setSelected(true);
                owner.setScene(salesScreen);
                this.getChildren().add(logoutBtn);
            }
            case 2 -> {
                this.getChildren().add(ingredientBtn);
                ingredientBtn.setSelected(true);
                owner.setScene(ingredientManagerScreen);
                this.getChildren().add(logoutBtn);
            }
            case 3 -> {
                this.getChildren().add(saleBtn);
                this.getChildren().add(returnOrderBtn);
                this.getChildren().add(EoDsBtn);
                this.getChildren().add(dataBtn);
                this.getChildren().add(ingredientBtn);
                dataBtn.setSelected(true);
                owner.setScene(dataManagerScreen);
                this.getChildren().add(logoutBtn);
            }
        }
        this.getScene().getWindow().sizeToScene();
    }

    public void initGUI(){
        saleBtn = new ToggleButton("Sale");
        EoDsBtn = new ToggleButton("CloseStore");
        dataBtn = new ToggleButton("Data");
        returnOrderBtn = new ToggleButton("Returns");
        ingredientBtn = new ToggleButton("Ingredients");
        salesScreen = new Scene(new PosController().getView());
        try {
            salesScreen.getStylesheets().add(new File("Manager/Application.css").toURI().toURL().toExternalForm());
            ingredientManagerScreen = new Scene(FXMLLoader.load(
                    new File("Manager/Storage/View/StorageManager.fxml").toURI().toURL()
            ));
            eodScreen = new Scene(FXMLLoader.load(
                    new File("Statistic/ReportEndDay/View/ReportEndDay.fxml")
                            .toURI().toURL()));
            eodScreen.getStylesheets().add(new File("Statistic/ReportEndDay/View/CSS/ReportEndDay.css")
                    .toURI().toURL().toExternalForm());
            returnOrderScreen = new Scene(FXMLLoader.load(
                    new File("Store/ReturnOrder/View/ReturnOrder.fxml")
                            .toURI().toURL()));
            returnOrderScreen.getStylesheets().add(new File("Store/ReturnOrder/View/CSS/ReturnOrder.css")
                    .toURI().toURL().toExternalForm());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataManagerScreen = new ManagerControl().getManagerView();



        ToggleGroup gr = new ToggleGroup();
        gr.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
        saleBtn.setToggleGroup(gr);
        EoDsBtn.setToggleGroup(gr);
        dataBtn.setToggleGroup(gr);
        ingredientBtn.setToggleGroup(gr);
        returnOrderBtn.setToggleGroup(gr);

        logoutBtn = new Button("Logout");

        setBtnImgandTooltip();


        createHandleEvent();

    }

    public void start(){
        try {
            loginScreen = new Scene(FXMLLoader.load(new File("Manager/Helpers/SignIn/Signinv2.fxml")
                    .toURI().toURL()));
            } catch (IOException e) {
            throw new RuntimeException(e);
        }

        exitBtn = new Button("Exit");
        exitBtn.setOnAction(e->owner.close());
        owner.sceneProperty().addListener((w,o,n)->{
            w.getValue().getWindow().centerOnScreen();
            changeLocation();
        });
    }

    private void setBtnImgandTooltip(){
        try {
            ImageView img = new ImageView(new Image(new FileInputStream("Icon/coffee-cup.png")));
            img.setFitHeight(25);
            img.setFitWidth(25);
            saleBtn.setGraphic(img);
            saleBtn.setTooltip(new Tooltip("Go to Sales Screen"));
            saleBtn.setText("");
            img = new ImageView(new Image(new FileInputStream("Icon/seo-report.png")));
            img.setFitHeight(25);
            img.setFitWidth(25);
            EoDsBtn.setGraphic(img);
            EoDsBtn.setTooltip(new Tooltip("Close store session and start report Daily Sales"));
            EoDsBtn.setText("");
            img = new ImageView(new Image(new FileInputStream("Icon/manager.png")));
            img.setFitHeight(25);
            img.setFitWidth(25);
            dataBtn.setGraphic(img);
            dataBtn.setTooltip(new Tooltip("Go to Manager Screen to manage store data"));
            dataBtn.setText("");
            img = new ImageView(new Image(new FileInputStream("Icon/ingredients.png")));
            img.setFitHeight(25);
            img.setFitWidth(25);
            ingredientBtn.setGraphic(img);
            ingredientBtn.setTooltip(new Tooltip("Go to Workshop Management screen"));
            ingredientBtn.setText("");
            img = new ImageView(new Image(new FileInputStream("Icon/salary2.png")));
            img.setFitHeight(25);
            img.setFitWidth(25);
            returnOrderBtn.setGraphic(img);
            returnOrderBtn.setTooltip(new Tooltip("Go to Salary Management Screen"));
            returnOrderBtn.setText("");
        } catch (FileNotFoundException e) {
            //throw new RuntimeException(e);
        }
    }

    public void prepareCSS(){
        try {
            this.getScene().getStylesheets().add(new File("Manager/Helpers/Controlbar/Controlbar.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeLocation(){

        if(owner.getX()<100){
            owner.setX(100);
            owner.setY(0);
        }

        this.getScene().getWindow().setX(owner.getX()-100);
        this.getScene().getWindow().setY(owner.getY());


    }

    private void createHandleEvent(){

        saleBtn.setOnAction(e->{
            owner.setScene(salesScreen);
            //changeLocation();
        });
        EoDsBtn.setOnAction(e->{

            try {
                eodScreen = new Scene(FXMLLoader.load(
                        new File("Statistic/ReportEndDay/View/ReportEndDay.fxml")
                                .toURI().toURL()));
                eodScreen.getStylesheets().add(new File("Statistic/ReportEndDay/View/CSS/ReportEndDay.css")
                        .toURI().toURL().toExternalForm());
                owner.setScene(eodScreen);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //changeLocation();
        });
        dataBtn.setOnAction(e->{
            owner.setScene(dataManagerScreen);
            //changeLocation();

        });
        returnOrderBtn.setOnAction(e->{
            owner.setScene(returnOrderScreen);
        });
        ingredientBtn.setOnAction(e->{
            owner.setScene(ingredientManagerScreen);
            //changeLocation();
        });
        logoutBtn.setOnAction(e->showFunction(0));

    }

}
