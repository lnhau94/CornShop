package Entity.Entity;

public class OrderDetails {
    private int id;
    private int productId;
    private int orderId;
    private int colorId;
    private int sizeId;
    private int qty;

    public OrderDetails() {
    }

    public OrderDetails(int id, int productId, int orderId, int colorId, int sizeId, int qty) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.colorId = colorId;
        this.sizeId = sizeId;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
