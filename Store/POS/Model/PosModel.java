package Store.POS.Model;

import Entity.Entity.Product;
import Entity.Entity.Storage;
import Manager.Product.ProductManagerModel;
import Manager.Storage.StorageManagerModel;

import java.util.ArrayList;
import java.util.HashMap;

public class PosModel {

    private HashMap<Integer,ArrayList<Storage>> productDetails;
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
}
