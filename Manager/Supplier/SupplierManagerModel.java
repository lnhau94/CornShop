package Manager.Supplier;

import Entity.DAO;
import Entity.Entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierManagerModel {
    public static List<Supplier> suppliers;
    public static void getAllData(){
        suppliers = new ArrayList<>();
        ResultSet rs = DAO.executeQuery("Select id, supplierId, supplierName from Supplier");
        try{
            while (rs.next()){
                suppliers.add(new Supplier(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Supplier findById(int id){
        for(Supplier s : suppliers){
            if(s.getId()==id){
                return s;
            }
        }
        return null;
    }
}
