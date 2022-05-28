package Manager.Brand;

import Entity.DAO;
import Entity.Entity.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandManagerModel {
    public static List<Brand> brands;
    public static void getAllData(){
        brands = new ArrayList<>();
        ResultSet rs = DAO.executeQuery("Select id, brandid, brandName, supplierId from Brand");
        try{
            while (rs.next()){
                brands.add(new Brand(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
