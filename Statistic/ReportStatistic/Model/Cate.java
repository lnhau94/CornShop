package Statistic.ReportStatistic.Model;

public class Cate {
    private String categoryId;
    private String categoryName;
    private int categoryQty;
    public Cate() {
    }
    public Cate(String categoryId, String categoryName, int categoryQty) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryQty = categoryQty;
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
    public int getCategoryQty() {
        return categoryQty;
    }
    public void setCategoryQty(int categoryQty) {
        this.categoryQty = categoryQty;
    }  
}
