package Manager.Storage;

import Entity.DAO;
import Entity.Entity.Storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StorageManagerModel {
    public static List<Storage> storages;

    public static void getAllData(){
        storages = new ArrayList<>();
        ResultSet rs = DAO.executeQuery("Select id, productId, color, size, quantity from Storage");
        try{
            while (rs.next()){
                storages.add(new Storage(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Storage findById(int id) {
        for(Storage s : storages){
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }

    public static ArrayList<String> getAllItemCode(){
        ArrayList<String> codes = new ArrayList<>();
        storages.forEach(e->codes.add(String.valueOf(e.getId())));
        return codes;
    }

}
