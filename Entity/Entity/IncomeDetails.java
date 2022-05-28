package Entity.Entity;

public class IncomeDetails {
    private int id;
    private int incomeBillId;
    private int storageId;
    private int quantity;
    private int recevie;

    public IncomeDetails() {
    }

    public IncomeDetails(int id, int incomeBillId, int productId, int quantity, int recevie) {
        this.id = id;
        this.incomeBillId = incomeBillId;
        this.storageId = productId;
        this.quantity = quantity;
        this.recevie = recevie;
    }

    public IncomeDetails(Storage i, int quantity) {
        storageId = i.getId();
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

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRecevie() {
        return recevie;
    }

    public void setRecevie(int recevie) {
        this.recevie = recevie;
    }
}
