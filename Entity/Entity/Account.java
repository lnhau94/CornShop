package Entity.Entity;

public class Account implements DBQuery{
    private int id;
    private String username;
    private int employeeId;
    private String password;

    public Account() {
    }

    public Account(int id, String username, int employeeId, String password) {
        this.id = id;
        this.username = username;
        this.employeeId = employeeId;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toInsertQuery() {
        return "Insert into Account(USERNAME, USERPASS, EMPLOYEEID) " +
                "VALUES(" +
                "'"+this.getUsername()+"', " +
                "'"+this.getPassword()+"', " +
                "'"+this.getEmployeeId()+"'" +
                ")";
    }
    public String toUpdateQuery() {
        return "Update Account set " +
                "USERNAME = '"+this.getUsername()+ "', " +
                "USERPASS = '"+this.getPassword()+ "', " +
                "EMPLOYEEID = '"+this.getEmployeeId()+ "' "+
                "where id = '"+this.id+"'";
    }

    @Override
    public String toDeleteQuery() {
        return "Delete from Account where id = '"+this.id+"'";
    }

}
