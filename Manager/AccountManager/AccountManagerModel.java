package Manager.AccountManager;

import Entity.DAO;
import Entity.Entity.Account;
import Entity.Entity.Employee;
import Manager.EmployeeManager.EmployeeManagerModel;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountManagerModel {
    public static List<Account> accounts;

    public static void getAllData(){
        //DAO.executeQuery()
        accounts = new ArrayList<>();
        ResultSet rs = DAO.executeQuery("Select * from Account");
        try{
            while(rs.next()){
                accounts.add(new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmployeeName(int id){
        Employee e = EmployeeManagerModel.findById(id);
        return e != null ? e.getEmployeeName() : "Not set yet";
    }
}
