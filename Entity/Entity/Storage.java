package Entity.Entity;

public class Storage implements DBQuery{
    private int id;
    private int productId;
    private int colorId;
    private int sizeId;
    private int qty;

    public Storage() {
    }

    public Storage(int id, int productId, int colorId, int sizeId, int qty) {
        this.id = id;
        this.productId = productId;
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

    @Override
    public String toInsertQuery() {
        return "INSERT INTO STORAGE (PRODUCTID, COLOR, SIZE, QUANTITY) VALUES(" +
                "'"+this.getProductId()+"', " +
                "'"+this.getColorId()+"', " +
                "'"+this.getSizeId()+"', " +
                "'"+this.getQty()+"' " +
                ")";
    }

    @Override
    public String toUpdateQuery() {
        return "";
    }

    @Override
    public String toDeleteQuery() {
        return "";
    }
}
