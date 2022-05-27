package Entity.Entity;

public class Size implements DBQuery{
    private int id;
    private String size;

    public Size() {
    }

    public Size(int id, String size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toInsertQuery() {
        return "INSERT INTO SIZE (SIZE) VALUES ('"+this.getSize()+"')";
    }

    @Override
    public String toUpdateQuery() {
        return "UPDATE SIZE SET SIZE = '"+this.size+"' WHERE ID = '"+this.getId()+"'";
    }

    @Override
    public String toDeleteQuery() {
        return "DELETE SIZE WHERE ID = '"+this.getId()+"'";
    }
}
