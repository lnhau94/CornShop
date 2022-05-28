package Store.POS.View;

import Entity.Entity.Category;
import Entity.Entity.OrderDetails;
import Entity.Entity.Storage;
import Manager.Category.CategoryManagerModel;
import Manager.Helpers.ErrorController;
import Manager.Product.ProductManagerModel;
import Manager.Storage.StorageManagerModel;
import Store.POS.Model.PosModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.textfield.TextFields;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PosView extends BorderPane {

    private HBox posControlBar;
    private VBox menuList;
    private ScrollPane menuView;
    private BorderPane orderView;
    private TableView<OrderDetails> orderBody;
    private VBox orderFooter;
    private Label priceLbl;
    private TextField itemCodeTxf;
    private TextField itemQtyTxf;

    private PosModel model;
    public PosView(PosModel model){
        this.model = model;
        initGUI();
    }

    private void initGUI(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPrefSize(d.getWidth()-100,d.getHeight()-100);

        createControlBar();
        createOderView();
        prepareMenuItem();

        this.setTop(posControlBar);
        this.setCenter(menuView);
        this.setRight(orderView);
    }

    private void prepareMenuItem(){
        menuList = new VBox();
        menuList.setSpacing(5);
        menuView = new ScrollPane(menuList);
        this.model.getProductDetails().forEach((k,v)->{
            menuList.getChildren().add(new MenuItem(k,v,this));
        });
        this.setCenter(menuView);
    }
    private void createControlBar(){
        posControlBar = new HBox();
        posControlBar.setPrefHeight(100);
        VBox left = new VBox();
        left.setPrefWidth(800);
        FlowPane right = new FlowPane();
        right.setHgap(10);
        FlowPane searchBar = new FlowPane();
        FlowPane filterBar = new FlowPane();
        TextField searchTxf = new TextField();
        searchTxf.setPrefSize(150,30);
        searchTxf.setPromptText("Search item!!!");
        TextFields.bindAutoCompletion(searchTxf, ProductManagerModel.getAllProductName());
        searchBar.getChildren().add(searchTxf);
        try {
            ImageView img = new ImageView(new Image(new FileInputStream("Icon/search.png")));
            img.setFitHeight(35);
            img.setFitWidth(35);
            searchBar.getChildren().add(img);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        left.getChildren().add(searchBar);
        for(Category c : CategoryManagerModel.categories){
            Button tmp = new Button(c.getCategoryName());
            filterBar.getChildren().add(tmp);
        }

        itemCodeTxf = new TextField();
        itemQtyTxf = new TextField("1");
        itemQtyTxf.setMaxSize(50,50);
        itemCodeTxf.setPromptText("Enter Item Code!!!");
        TextFields.bindAutoCompletion(itemCodeTxf, StorageManagerModel.getAllItemCode());
        Button checkBtn = new Button("OK");
        checkBtn.setOnAction(e->choiceItem());


        checkBtn.getStyleClass().add("checkBtn");
        right.getChildren().addAll(
                new Label("ItemCode:"), itemCodeTxf,
                new Label("Qty:"), itemQtyTxf,
                checkBtn);

        left.getChildren().add(filterBar);
        posControlBar.getChildren().addAll(left,right);
    }

    private void createOderView(){
        orderView = new BorderPane();
        orderView.setPrefWidth(300);
        createNewOrder();
    }
    public void createNewOrder(){

        createOrderBody();
        createOrderFooter();
    }
    private void createOrderBody(){
        orderBody = new TableView<>();
        TableColumn<OrderDetails,String> indexCol = new TableColumn<>("No");
        TableColumn<OrderDetails,String> productCol = new TableColumn<>("Product");
        TableColumn<OrderDetails,String> colorCol = new TableColumn<>("Color");
        TableColumn<OrderDetails,String> sizeCol = new TableColumn<>("Size");
        TableColumn<OrderDetails,String> qtyCol = new TableColumn<>("Qty");

        indexCol.setPrefWidth(35);
        productCol.setPrefWidth(130);
        colorCol.setPrefWidth(50);
        sizeCol.setPrefWidth(50);
        qtyCol.setPrefWidth(35);

        indexCol.setCellValueFactory(e->
                new SimpleStringProperty(String.valueOf(model.getCurrentChoices().indexOf(e.getValue())+1)));
        productCol.setCellValueFactory(e->
                new SimpleStringProperty(ProductManagerModel.findProductById(e.getValue().getProductId()).getProductName()));
        colorCol.setCellValueFactory(e->
                new SimpleStringProperty(ProductManagerModel.findColorById(e.getValue().getColorId()).getName()));
        sizeCol.setCellValueFactory(e->
                new SimpleStringProperty(ProductManagerModel.findSizebyId(e.getValue().getSizeId()).getSize()));
        qtyCol.setCellValueFactory(e->new SimpleStringProperty(String.valueOf(e.getValue().getQty())));

        orderBody.getColumns().addAll(indexCol,productCol,colorCol,sizeCol,qtyCol);
        orderView.setCenter(orderBody);
        orderBody.setItems(FXCollections.observableList(this.model.getCurrentChoices()));
    }

    private void createOrderFooter(){
        orderFooter = new VBox();
        orderFooter.setPrefHeight(120);
        orderFooter.setAlignment(Pos.BASELINE_RIGHT);
        orderFooter.setSpacing(5);

        priceLbl = new Label("VND");
        priceLbl.getStyleClass().add("priceLbl");
        priceLbl.setAlignment(Pos.CENTER_RIGHT);
        Button cashBtn = new Button("Cash");
        cashBtn.setOnAction(e->model.payCurrentOrder());
        orderFooter.getChildren().addAll(priceLbl,cashBtn);
        orderView.setBottom(orderFooter);

    }

    private void choiceItem(){
        int id;
        int choiceQty = 0;
        try {
            choiceQty = Integer.parseInt(itemQtyTxf.getText());
        }catch (NumberFormatException e){
            new ErrorController().displayError("Item Qty is not legal!!");
        }
        try{
            id = Integer.parseInt(itemCodeTxf.getText());
            Storage tmp = StorageManagerModel.findById(id);
            OrderDetails od = new OrderDetails();
            od.setProductId(tmp.getProductId());
            od.setColorId(tmp.getColorId());
            od.setSizeId(tmp.getSizeId());
            od.setQty(choiceQty);
            this.model.addNewItem(od);
            refreshTable();
        }catch (NumberFormatException e){
            new ErrorController().displayError("Item code is not legal!!");
        }catch (NullPointerException e){
            new ErrorController().displayError("Item code is not found!!");
        }
        priceLbl.setText(String.format("%,d VND",model.calculateTotalPrice()));
    }

    public void refreshTable(){
        orderBody.setItems(FXCollections.observableList(this.model.getCurrentChoices()));
        orderBody.refresh();
    }

    public void refreshMenu() {
        prepareMenuItem();
    }

    public void setItemCode(String itemCode){
        itemCodeTxf.setText(itemCode);
    }
}
