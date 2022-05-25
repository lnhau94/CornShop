package Entity.Entity;

public class Supplier {
    private int id;
    private int supplierId;
    private String supplierName;

    public Supplier() {
    }

    public Supplier(int id, int supplierId, String supplierName) {
        this.id = id;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
