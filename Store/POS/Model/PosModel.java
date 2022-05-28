package Store.POS.Model;

import Entity.DAO;
import Entity.Entity.Order;
import Entity.Entity.OrderDetails;
import Entity.Entity.Product;
import Entity.Entity.Storage;
import Main.MainApp;
import Manager.Product.ProductManagerModel;
import Manager.Storage.StorageManagerModel;
import Store.POS.Control.PosController;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PosModel {

    private HashMap<Integer,ArrayList<Storage>> productDetails;
    private ArrayList<OrderDetails> currentChoices;
    private Order currentOrder;
    private PosController controller;
    public PosModel(PosController controller){
        this.controller = controller;
        prepareData();
        createNewOrder();
    }

    public void createNewOrder(){
        currentOrder = new Order();
        currentChoices = new ArrayList<>();
    }

    private void prepareData(){
        productDetails = new HashMap<>();
        for(Product p : ProductManagerModel.products){
            productDetails.put(p.getId(),new ArrayList<>());
        }
        for(Storage s : StorageManagerModel.storages){
            productDetails.get(s.getProductId()).add(s);
        }
    }

    public HashMap<Integer, ArrayList<Storage>> getProductDetails() {
        return productDetails;
    }

    public void addNewItem(OrderDetails od){
        boolean done = false;
        for(OrderDetails o : currentChoices){
            if( o.getProductId() == od.getProductId() &&
                    o.getColorId() == od.getColorId() &&
                    o.getSizeId() == od.getSizeId() ){
                o.setQty(o.getQty()+od.getQty());
                done = true;
            }
        }
        if(!done){
            currentChoices.add(od);
        }
    }

    public ArrayList<OrderDetails> getCurrentChoices() {
        return currentChoices;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public int calculateTotalPrice(){
        int sum = 0;
        for(OrderDetails od : currentChoices){
            Product p = ProductManagerModel.findProductById(od.getProductId());
            sum += p.getPrice()*od.getQty();
        }
        return sum;
    }

    public void payCurrentOrder() {
        save();
        StorageManagerModel.getAllData();
        prepareData();
        createNewOrder();
        this.controller.getView().createNewOrder();
        this.controller.getView().refreshMenu();
    }
    private void save(){
        try {
            PreparedStatement pstm = DAO.getPrepareStatement(
                    "Insert into Orders (OrderDate, TotalPrice, Cashier) " +
                            "OUTPUT INSERTED.ID " +
                            "VALUES (?,?,?)");
            pstm.setDate(1,new Date(System.currentTimeMillis()));
            pstm.setInt(2,calculateTotalPrice());
            pstm.setInt(3, MainApp.staff.getId());
            ResultSet rs = pstm.executeQuery();
            rs.next();
            int orderId = rs.getInt(1);
            pstm = DAO.getPrepareStatement(
                    "Insert into OrderDetails (orderId, productId, colorId, sizeId, quantity) " +
                            "values (?,?,?,?,?)");
            PreparedStatement pstm2 = DAO.getPrepareStatement(
                    "Update Storage " +
                            "set Quantity = Quantity - ? " +
                            "where productId = ? and " +
                            "color = ? and " +
                            "size = ?"
            );
            for(OrderDetails od :currentChoices){
                pstm.setInt(1,orderId);
                pstm.setInt(2,od.getProductId());
                pstm.setInt(3,od.getColorId());
                pstm.setInt(4,od.getSizeId());
                pstm.setInt(5,od.getQty());

                pstm2.setInt(1,od.getQty());
                pstm2.setInt(2,od.getProductId());
                pstm2.setInt(3,od.getColorId());
                pstm2.setInt(4,od.getSizeId());

                pstm.execute();
                pstm2.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
