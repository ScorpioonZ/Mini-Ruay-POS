package RuayRuayMain.Model;

public class Ruay_Supplier {
    private int SupplierID;
    private String SupplierName;
    private String SupplierAddress;
    private String SupplierPhone;
    private String SupplierEmail;

    public Ruay_Supplier(int SupplierID, String SupplierName, String SupplierAddress, String SupplierPhone, String SupplierEmail) {
        this.SupplierID = SupplierID;
        this.SupplierName = SupplierName;
        this.SupplierAddress = SupplierAddress;
        this.SupplierPhone = SupplierPhone;
        this.SupplierEmail = SupplierEmail;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public String getSupplierAddress() {
        return SupplierAddress;
    }

    public String getSupplierPhone() {
        return SupplierPhone;
    }

    public String getSupplierEmail() {
        return SupplierEmail;
    }

    @Override
    public String toString() {
        return "SupID: " + SupplierID + "SupName: " +SupplierName;  // This ensures the combo box shows SupplierName
    }
}
