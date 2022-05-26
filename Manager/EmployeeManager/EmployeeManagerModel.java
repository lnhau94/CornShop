package Manager.EmployeeManager;

import Entity.DAO;
import Entity.Entity.Employee;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagerModel {
    public static List<Employee> employees;
    public static Employee findById(int id) {
        for(Employee e : employees){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }

    public static void getAllData(){
        employees = new ArrayList<>();
        ResultSet rs = DAO.executeQuery("Select * from Employee");
        try{
            while(rs.next()){
                Employee e = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                employees.add(e);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
