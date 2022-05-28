package Store.ReturnOrder.Model;

public class OldOrder {
    private String productID;
    private String productName;
    private String productColor;
    private String productSize;
    private int productQty;
    private int productPrice;
    public OldOrder() {
    }
    public OldOrder(String productID, String productName, String productColor, String productSize, int productQty,
            int productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productColor = productColor;
        this.productSize = productSize;
        this.productQty = productQty;
        this.productPrice = productPrice;
    }
    public String getProductID() {
        return productID;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductColor() {
        return productColor;
    }
    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }
    public String getProductSize() {
        return productSize;
    }
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
    public int getProductQty() {
        return productQty;
    }
    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }
    public int getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    @Override
    public String toString() {
        return "OldOrder [productColor=" + productColor + ", productID=" + productID + ", productName=" + productName
                + ", productPrice=" + productPrice + ", productQty=" + productQty + ", productSize=" + productSize
                + "]";
    }
}
