package Store.POS.Model;


import Entity.Entity.Order;
import Entity.Entity.OrderDetails;

import Entity.Entity.Product;
import Entity.Entity.Storage;
import Manager.Product.ProductManagerModel;
import Manager.Storage.StorageManagerModel;

import java.util.ArrayList;
import java.util.HashMap;

public class PosModel {

    private HashMap<Integer,ArrayList<Storage>> productDetails;

    private ArrayList<OrderDetails> currentChoices;
    private Order currentOrder;
    public PosModel(){
        prepareData();
        createNewOrder();
    }

    public void createNewOrder(){
        currentOrder = new Order();
        currentChoices = new ArrayList<>();

    public PosModel(){
        prepareData();

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
        currentChoices.add(od);
    }

    public ArrayList<OrderDetails> getCurrentChoices() {
        return currentChoices;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

}
