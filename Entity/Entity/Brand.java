package Entity.Entity;

public class Brand {
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
}
