package Entity.Entity;

public class Brand implements DBQuery{
    private int id;
    private String brandId;
    private String brandName;
    private int supplierId;

    public Brand() {
    }

    public Brand(int id, String brandId, String brandName, int supplierId) {
        this.id = id;
        this.brandId = brandId;
        this.brandName = brandName;
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toInsertQuery() {
        return "INSERT INTO BRAND(BRANDNAME,SUPPLIERID) VALUES" +
                "(N'"+this.getBrandName()+"', " +
                "'"+this.getSupplierId()+"')";
    }

    @Override
    public String toUpdateQuery() {
        return "UPDATE BRAND SET " +
                "BRANDNAME = N'"+this.getBrandName()+"', " +
                "SUPPLIERID = '"+this.getSupplierId()+"' " +
                "WHERE ID = '"+this.getId()+"'";
    }

    @Override
    public String toDeleteQuery() {
        return "DELETE BRAND WHERE ID = '"+this.getId()+"'";
    }
}
