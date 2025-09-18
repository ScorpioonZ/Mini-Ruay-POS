package RuayRuayMain.Model;

public class Ruay_Customer {
    private int CustomerID;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerPhone;
    private String CustomerEmail;

    public Ruay_Customer(int CustomerID, String CustomerName, String CustomerAddress, String CustomerPhone, String CustomerEmail) {
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.CustomerAddress = CustomerAddress;
        this.CustomerPhone = CustomerPhone;
        this.CustomerEmail = CustomerEmail;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public String getCustomerPhone() {
        return CustomerPhone;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    @Override
    public String toString() {
        return ""+CustomerID + " "+CustomerName ;
    }
}
