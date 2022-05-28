package Entity.Entity;

public class Color implements DBQuery{
    private int id;
    private String name;
    private int red;
    private int green;
    private int blue;

    public Color() {
    }

    public Color(int id, String name, int red, int green, int blue) {
        this.id = id;
        this.name = name;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public String toInsertQuery() {
        return "INSERT INTO COLOR VALUES(COLORNAME, RED, GREEN, BLUE) VALUES (" +
                "N'"+this.getName()+"', " +
                "'"+this.getRed()+"', " +
                "'"+this.getGreen()+"', " +
                "'"+this.getBlue()+"'" +
                ")";
    }

    @Override
    public String toUpdateQuery() {
        return "UPDATE COLOR SET " +
                "NAME = N'"+this.getName()+"', " +
                "RED = '"+this.getRed()+"', " +
                "GREEN = '"+this.getGreen()+"', " +
                "BLUE = '"+this.getBlue()+"' " +
                "WHERE ID = '"+this.getId()+"'";
    }

    @Override
    public String toDeleteQuery() {
        return "DELETE COLOR " +
                "WHERE ID = '"+this.getId()+"'";
    }
}
