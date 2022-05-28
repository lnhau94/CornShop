package Store.ReturnOrder.Model;

import java.sql.Date;

public class Inform {
    private String phoneCus;
    private String nameEmp;
    private Date orderDate;
    private int productQty;
    private int totalPrice;
    public Inform() {
    }
    public Inform(String phoneCus, String nameEmp, Date orderDate, int productQty, int totalPrice) {
        this.phoneCus = phoneCus;
        this.nameEmp = nameEmp;
        this.orderDate = orderDate;
        this.productQty = productQty;
        this.totalPrice = totalPrice;
    }
    public String getPhoneCus() {
        return phoneCus;
    }
    public void setPhoneCus(String phoneCus) {
        this.phoneCus = phoneCus;
    }
    public String getNameEmp() {
        return nameEmp;
    }
    public void setNameEmp(String nameEmp) {
        this.nameEmp = nameEmp;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public int getProductQty() {
        return productQty;
    }
    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
