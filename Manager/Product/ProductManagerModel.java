package Manager.Product;

import Entity.DAO;
import Entity.Entity.Color;
import Entity.Entity.Product;
import Entity.Entity.Size;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductManagerModel {
    public static List<Product> products;
    public static List<Color> colors;
    public static List<Size> sizes;

    public static void getAllData(){
        products = new ArrayList<>();
        colors = new ArrayList<>();
        sizes = new ArrayList<>();
        getAllProduct();
        getAllColor();
        getAllSize();
    }
    private static void getAllProduct(){
        ResultSet rs = DAO.executeQuery("Select * from Product");
        try{
            while(rs.next()){
                products.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void getAllColor(){
        ResultSet rs = DAO.executeQuery("Select * from Color");
        try{
            while(rs.next()){
                colors.add(new Color(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void getAllSize(){
        ResultSet rs = DAO.executeQuery("Select * from Size");
        try{
            while(rs.next()){
                sizes.add(new Size(
                        rs.getInt(1),
                        rs.getString(2)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Color findColorById(int id){
        for(Color c : colors){
            if(c.getId() == id){
                return c;
            }
        }
        return new Color();
    }

    public static Size findSizebyId(int id){
        for(Size s : sizes){
            if(s.getId() == id){
                return s;
            }
        }
        return new Size();
    }
}
