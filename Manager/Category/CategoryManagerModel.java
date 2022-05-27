package Manager.Category;

import Entity.DAO;
import Entity.Entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryManagerModel {
    public static List<Category> categories;
    public static void getAllData(){
        categories = new ArrayList<>();
        ResultSet rs = DAO.executeQuery("Select * from Category");
        try{
            while(rs.next()){
                categories.add(new Category(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
