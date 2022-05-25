package Entity.Entity;

public class Account {
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
}
