package Store.POS.View;

import Store.POS.Model.PosModel;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;

public class PosView extends BorderPane {

    private HBox posControlBar;
    private VBox menuList;
    private ScrollPane menuView;
    private BorderPane orderView;

    private PosModel model;
    public PosView(PosModel model){
        this.model = model;
        initGUI();

        prepareMenuItem();
    }

    private void initGUI(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPrefSize(d.getWidth()-100,d.getHeight()-100);
        posControlBar = new HBox();
        posControlBar.setPrefHeight(100);
        menuList = new VBox();
        menuList.setSpacing(5);
        menuView = new ScrollPane(menuList);
        orderView = new BorderPane();
        orderView.setPrefWidth(300);
        this.setTop(posControlBar);
        this.setCenter(menuView);
        this.setRight(orderView);
    }

    private void prepareMenuItem(){
        this.model.getProductDetails().forEach((k,v)->{
            MenuItem i = new MenuItem(k,v);
            i.getStyleClass().add("choicebutton");
            i.getChildren().add(new Button("hello"));
            menuList.getChildren().add(i);
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
            menuList.getChildren().add(new MenuItem(k,v));
        });
    }

}
