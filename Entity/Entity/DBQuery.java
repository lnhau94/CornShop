package Entity.Entity;

public interface DBQuery {
    public String toInsertQuery();
    public String toUpdateQuery();
    public String toDeleteQuery();
}
