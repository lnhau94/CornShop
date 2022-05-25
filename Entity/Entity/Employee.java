package Entity.Entity;

public class Employee {
    private int id;
    private String employeeId;
    private String employeeName;
    private String phone;
    private String position;
    private String gender;

    public Employee() {
    }

    public Employee(int id, String employeeId, String employeeName, String phone, String position, String gender) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.phone = phone;
        this.position = position;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
