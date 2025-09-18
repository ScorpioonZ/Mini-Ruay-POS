package RuayRuayMain.Model;

public class Ruay_Transactions {
	private int ID;
	private int good_ID;
	private String good_name;
	private String Date;
	private int amount;
	private double total_price;
	private int userID;
	
    public Ruay_Transactions(int id, int good_ID, String good_name,String Date, int amount, double total_price, int userID) {
        this.ID = id;
        this.good_ID = good_ID;
        this.good_name = good_name;
        this.Date = Date;
        this.amount = amount;
        this.total_price = total_price;
        this.userID = userID;
    }
    public int getID() {
        return ID;
    }

    public int getGood_ID() {
        return good_ID;
    }
    public String getGoodName() {
    	return good_name;
    }
    public String getDate() {
        return Date;
    }

    public int getAmount() {
        return amount;
    }

    public double getTotal_price() {
        return total_price;
    }

    public int getUserID() {
        return userID;
    }

    // Setters
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setGood_ID(int good_ID) {
        this.good_ID = good_ID;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    // toString() method
    @Override
    public String toString() {
        return "Ruay_Transactions{" +
                "ID=" + ID +
                ", good_ID=" + good_ID + "Good_name " + good_name + 
                ", Date='" + Date + '\'' +
                ", amount=" + amount +
                ", total_price=" + total_price +
                ", userID=" + userID +
                '}';
    }
}
    

