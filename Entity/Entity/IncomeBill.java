package Entity.Entity;

import java.sql.Date;

public class IncomeBill {
    private int id;
    private String incomeBillId;
    private Date createDate;
    private int storeKeeper;
    private int supplierId;

    public IncomeBill() {
    }

    public IncomeBill(int id, String incomeBillId, Date createDate, int storeKeeper, int supplierId) {
        this.id = id;
        this.incomeBillId = incomeBillId;
        this.createDate = createDate;
        this.storeKeeper = storeKeeper;
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIncomeBillId() {
        return incomeBillId;
    }

    public void setIncomeBillId(String incomeBillId) {
        this.incomeBillId = incomeBillId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStoreKeeper() {
        return storeKeeper;
    }

    public void setStoreKeeper(int storeKeeper) {
        this.storeKeeper = storeKeeper;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
