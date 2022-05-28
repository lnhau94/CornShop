package Store.POS.View;

import Entity.Entity.Color;
import Entity.Entity.Product;
import Entity.Entity.Size;
import Entity.Entity.Storage;
import Manager.Product.ProductManagerModel;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.awt.*;

import java.text.NumberFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.*;
import java.util.List;


public class MenuItem extends HBox {
    private List<Storage> storageList;
    private int productId;
    private Label nameLbl;
    private Label priceLbl;

    private Label materialLbl;
    private Label storageLbl;
    private VBox colorAndSize;
    private FlowPane colors;
    private FlowPane sizes;
    private ArrayList<StorageLabel> sizeLblList;
    private HashMap<Integer,ArrayList<Pair<Integer,Integer>>> details;
    private String bosCss =
            "-fx-effect: dropshadow(three-pass-box, #52c234, 5,0.0,2,2);" +
                    "-fx-padding: 5,0,0,0;" +
                    "-fx-border-color: #52c234;" +
                    "-fx-border-radius: 12;";
    public MenuItem(int productId, List storageList){
        this.storageList = storageList;
        this.productId =  productId;
        this.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-400);
        this.setSpacing(10);
        this.setStyle(bosCss);
        details = new HashMap<>();
        initGui();
    }

    private void initGui(){
        nameLbl = new Label();
        priceLbl = new Label();
        storageLbl = new Label();
        materialLbl = new Label();
        nameLbl.getStyleClass().add("menuLabel");
        priceLbl.getStyleClass().add("menuLabel");
        storageLbl.getStyleClass().add("menuLabel");
        materialLbl.getStyleClass().add("menuLabel");
        colorAndSize = new VBox();
        colors = new FlowPane();

        colors.setHgap(5);

        sizes = new FlowPane();
        sizeLblList = new ArrayList<>();
        ToggleGroup gr = new ToggleGroup();
        gr.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
        int Sum =0;
        for(Product p : ProductManagerModel.products){
            if(p.getId() == this.productId){
                nameLbl.setText(p.getProductName());

                priceLbl.setText(String.format("%,d",p.getPrice()*1000)+" VND");

                materialLbl.setText(p.getMaterial());
                break;
            }
        }
        for(Storage s : storageList){
            Sum += s.getQty();
            if(details.containsKey(s.getColorId())){
                details.get(s.getColorId()).add(new Pair<>(s.getSizeId(),s.getQty()));
            }else{
                details.put(s.getColorId(),new ArrayList<>());
                Color c = ProductManagerModel.findColorById(s.getColorId());
                System.out.println(c.getRed()+" "+c.getGreen()+" "+c.getBlue());
                System.out.println(s.getColorId());
                ToggleButton tmp = new ToggleButton();
                tmp.setId(String.valueOf(s.getColorId()));
                tmp.setStyle(String.format("-fx-background-color: rgb(%d,%d,%d)",c.getRed(),c.getGreen(),c.getBlue()));
                tmp.setOnAction(e->filterColor(Integer.parseInt(tmp.getId())));
                tmp.setToggleGroup(gr);
                colors.getChildren().add(tmp);
                details.get(s.getColorId()).add(new Pair<>(s.getSizeId(),s.getQty()));
            }
            StorageLabel tmpl = new StorageLabel(s);
            tmpl.setPrefSize(50,25);
            sizeLblList.add(tmpl);
        }

        storageLbl.setText("InStock: " + Sum);


        this.getChildren().add(nameLbl);
        this.getChildren().add(priceLbl);
        this.getChildren().add(storageLbl);
        this.getChildren().add(materialLbl);
        HBox.setHgrow(nameLbl, Priority.ALWAYS);
        HBox.setHgrow(priceLbl, Priority.ALWAYS);
        HBox.setHgrow(storageLbl, Priority.ALWAYS);
        HBox.setHgrow(materialLbl, Priority.ALWAYS);
        colorAndSize.getChildren().add(colors);
        colorAndSize.getChildren().add(sizes);
        this.getChildren().add(colorAndSize);
    }

    private void filterColor(int colorId){
        while(!sizes.getChildren().isEmpty()){
            sizes.getChildren().remove(0);
        }

        FlowPane fl = new FlowPane();
        fl.setPrefSize(320,35);
        fl.setHgap(3);
        for(StorageLabel sl : sizeLblList){
            if(sl.getStorage().getColorId() == colorId){
                System.out.println(fl.getChildren().add(sl));
            }
        }
        sizes.getChildren().add(fl);

    }

    class StorageLabel extends Label{
        Storage storage;
        public StorageLabel(Storage storage){
            this.storage = storage;
            initGUI();;
        }
        private void initGUI(){
            Size sz = ProductManagerModel.findSizebyId(storage.getSizeId());

            this.setText(String.format("%04d: %6s:%3d",
                    storage.getId(),
                    sz.getSize().toUpperCase(Locale.ROOT),
                    storage.getQty()));

            this.getStyleClass().add("storageLabel");
        }

        public Storage getStorage() {
            return storage;
        }
    }

}
