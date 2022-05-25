package Statistic.ReportStatistic.Model;

public class Prod {
    private String productId;
    private String productName;
    private String productCategory;
    private String productSize;
    private String productColor;
    private int productPrice;
    private int productQty;
    public Prod() {
    }
    public Prod(String productId, String productName, String productCategory, String productSize, String productColor,
            int productPrice, int productQty) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productSize = productSize;
        this.productColor = productColor;
        this.productPrice = productPrice;
        this.productQty = productQty;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductCategory() {
        return productCategory;
    }
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    public String getProductSize() {
        return productSize;
    }
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
    public String getProductColor() {
        return productColor;
    }
    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }
    public int getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    public int getProductQty() {
        return productQty;
    }
    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }
}
