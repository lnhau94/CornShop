package Entity.Entity;

public class Product implements DBQuery{
    private int id;
    private String productId;
    private String productName;
    private int price;
    private String material;
    private int categoryId;
    private int brandId;

    public Product() {
    }

    public Product(int id, String productId, String productName, int price, String material, int categoryId, int brandId) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.material = material;
        this.categoryId = categoryId;
        this.brandId = brandId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    @Override
    public String toInsertQuery() {
        return "INSERT INTO PRODUCT(PRODUCTNAME, PRICE, MATERIAL, CATEGORYID, BRANDID) VALUES(" +
                "'"+this.getProductName()+"', " +
                "'"+this.getPrice()+"', " +
                "'"+this.getMaterial()+"', " +
                "'"+this.getCategoryId()+"', " +
                "'"+this.getBrandId()+"' " +
                ")";
    }

    @Override
    public String toUpdateQuery() {
        return "UPDATE PRODUCT SET " +
                "PRODUCTNAME = '"+this.getProductName()+"', " +
                "PRICE = '"+this.getPrice()+"', " +
                "MATERIAL = '"+this.getMaterial()+"', " +
                "CATEGORYID = '"+this.getCategoryId()+"', " +
                "BRANDID = '"+this.getBrandId()+"' " +
                "WHERE ID = '"+this.getId()+"'";
    }

    @Override
    public String toDeleteQuery() {
        return "DELETE PRODUCT " +
                "WHERE ID = '"+this.getId()+"'";
    }
}
