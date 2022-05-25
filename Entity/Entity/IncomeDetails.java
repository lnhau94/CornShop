package Entity.Entity;

public class IncomeDetails {
    private int id;
    private int incomeBillId;
    private int productId;
    private int quantity;

    public IncomeDetails() {
    }

    public IncomeDetails(int id, int incomeBillId, int productId, int quantity) {
        this.id = id;
        this.incomeBillId = incomeBillId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIncomeBillId() {
        return incomeBillId;
    }

    public void setIncomeBillId(int incomeBillId) {
        this.incomeBillId = incomeBillId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
