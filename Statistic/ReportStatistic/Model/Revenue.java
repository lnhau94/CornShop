package Statistic.ReportStatistic.Model;

public class Revenue {
    private String date;
    private int numOrder;
    private int numProduct;
    private int totalPrice;
    public Revenue() {
    }
    public Revenue(String date, int numOrder, int numProduct, int totalPrice) {
        this.date = date;
        this.numOrder = numOrder;
        this.numProduct = numProduct;
        this.totalPrice = totalPrice;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getNumOrder() {
        return numOrder;
    }
    public void setNumOrder(int numOrder) {
        this.numOrder = numOrder;
    }
    public int getNumProduct() {
        return numProduct;
    }
    public void setNumProduct(int numProduct) {
        this.numProduct = numProduct;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    } 
    
}
