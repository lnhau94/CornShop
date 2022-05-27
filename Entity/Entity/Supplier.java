package Entity.Entity;

public class Supplier implements DBQuery{
    private int id;
    private String supplierId;
    private String supplierName;

    public Supplier() {
    }

    public Supplier(int id, String supplierId, String supplierName) {
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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toInsertQuery() {
        return "INSERT INTO SUPPLIER (SUPPLIERNAME) VALUES ('"+this.getSupplierName()+"')";
    }

    @Override
    public String toUpdateQuery() {
        return "UPDATE SUPPLIER SET SUPPLIERNAME = '"+this.getSupplierName()+"' WHERE ID = '"+this.getId()+"'";
    }

    @Override
    public String toDeleteQuery() {
        return "DELETE SUPPLIER WHERE ID = '"+this.getId()+"'";
    }
}
