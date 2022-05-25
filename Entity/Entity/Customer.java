package Entity.Entity;

public class Customer {
    private String phone;
    private String name;
    private String gender;
    private int lastpurchase;
    private int currentpurchase;

    public Customer() {
    }

    public Customer(String phone, String name, String gender, int lastpurchase, int currentpurchase) {
        this.phone = phone;
        this.name = name;
        this.gender = gender;
        this.lastpurchase = lastpurchase;
        this.currentpurchase = currentpurchase;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getLastpurchase() {
        return lastpurchase;
    }

    public void setLastpurchase(int lastpurchase) {
        this.lastpurchase = lastpurchase;
    }

    public int getCurrentpurchase() {
        return currentpurchase;
    }

    public void setCurrentpurchase(int currentpurchase) {
        this.currentpurchase = currentpurchase;
    }
}
