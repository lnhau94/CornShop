package Entity.Entity;

public class Category implements DBQuery{
    private int id;
    private String categoryId;
    private String categoryName;

    public Category() {
    }

    public Category(int id, String categoryId, String categoryName) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toInsertQuery() {
        return "INSERT INTO CATEGORY (CATEGORYNAME) VALUES('"+this.getCategoryName()+"')";
    }

    @Override
    public String toUpdateQuery() {
        return "UPDATE CATEGORY SET CATEGORYNAME ='"+this.getCategoryName()+"' WHERE ID = '"+this.getId()+"'";
    }

    @Override
    public String toDeleteQuery() {
        return "DELETE CATEGORY WHERE ID = '"+this.getId()+"'";
    }
}
