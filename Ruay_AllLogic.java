package RuayRuayMain;

import RuayRuayMain.Model.Ruay_Customer;
import RuayRuayMain.Model.Ruay_Goods;
import RuayRuayMain.Model.Ruay_GoodsType;
import RuayRuayMain.Model.Ruay_Supplier;
import RuayRuayMain.Model.Ruay_Transactions;
import RuayRuayMain.Model.Ruay_User;

public class Ruay_AllLogic {

	public static void InsertGoods(int good_id, String goods_name, int good_type_id, String goods_type, double price, int amount, String img_path) {
	    Ruay_Goods CSTransac = new Ruay_Goods(good_id, goods_name, good_type_id, goods_type, price, amount, img_path);
	    CSTransacDAO.InsertGoods(CSTransac);
	}

	public static void UpdateGoods(int good_id, String goods_name,int good_type_id, String goods_type,double price,int amount,String img) {
		Ruay_Goods CSTransac = new Ruay_Goods(good_id,goods_name ,good_type_id, goods_type, price, amount, img);
		CSTransacDAO.UpdateGoods(CSTransac);
	}
	public static void InsertGoodType(int Goodtype_ID ,String Goodtype_Name) {
	    Ruay_GoodsType GoodType = new Ruay_GoodsType(Goodtype_ID,Goodtype_Name);
	    CSTransacDAO.InsertGoodType(GoodType);
	}
	public static void DeleteGoodType(int Goodtype_ID ,String Goodtype_Name) {
	    Ruay_GoodsType GoodType = new Ruay_GoodsType(Goodtype_ID,Goodtype_Name);
		CSTransacDAO.DeleteGoodType(GoodType);
	}
	////////////////////////////////////////////////////////////////////
	
	public static void DeleteTransaction(int id, int good_ID, String good_name,String Date, int amount, double total_price, int userID) {
		Ruay_Transactions trans = new Ruay_Transactions(id, good_ID, good_name, Date, amount, total_price, userID);
		CSTransacDAO.DeleteTransactions(trans);
	}
	
	////////////////////////////////////////////////////////////////////
	public static void InsertSupplier(int SupID,String SupName,String SupAddress,String SupPhone, String SupEmail) {
		Ruay_Supplier Supplier = new Ruay_Supplier(SupID, SupName, SupAddress, SupPhone, SupEmail);
		CSTransacDAO.InsertSupplier(Supplier);
	}
	
	public static void UpdateSupplier(int SupID,String SupName, String SupAddress , String SupPhone , String SupEmail) {
		Ruay_Supplier newsup = new Ruay_Supplier(SupID, SupName, SupAddress, SupPhone, SupEmail);
		CSTransacDAO.UpdateSupplier(newsup);
	}
	public static void DeleteSupplier(int SupID,String SupName, String SupAddress , String SupPhone , String SupEmail) {
		Ruay_Supplier newsup = new Ruay_Supplier(SupID, SupName, SupAddress, SupPhone, SupEmail);
		CSTransacDAO.DeleteSupplier(newsup);
	}
	////////////////////////////////////////////////////////////////////
	public static void InsertCustomer(int SupID,String SupName,String SupAddress,String SupPhone, String SupEmail) {
		Ruay_Customer Customer = new Ruay_Customer(SupID, SupName, SupAddress, SupPhone, SupEmail);
		CSTransacDAO.InsertCustomer(Customer);
	}
	
	public static void UpdateCustomer(int SupID,String SupName,String SupAddress,String SupPhone, String SupEmail) {
		Ruay_Customer Customer = new Ruay_Customer(SupID, SupName, SupAddress, SupPhone, SupEmail);
		CSTransacDAO.UpdateCustomer(Customer);
	}
	public static void DeleteCustomer(int SupID,String SupName,String SupAddress,String SupPhone, String SupEmail) {
		Ruay_Customer Customer = new Ruay_Customer(SupID, SupName, SupAddress, SupPhone, SupEmail);
		CSTransacDAO.DeleteCustomer(Customer);
		
	}
	////////////////////////////////////////////////////////////////////
	public static void InsertUser(int SupID,String SupName,String SupAddress,String SupPhone, String cusid) {
		Ruay_User Supplier = new Ruay_User(SupID, SupName, SupAddress, SupPhone, cusid, SupID);
		CSTransacDAO.AddUser(Supplier,cusid);
	}
	
	public static void UpdateUser(int SupID,String SupName,String SupAddress,String SupPhone, int cusid) {
		Ruay_User User = new Ruay_User(SupID, SupPhone, SupAddress, SupPhone, SupName, cusid);
		CSTransacDAO.UpdateUser(User);
	}
	public static void DeleteUser(int SupID) {
		CSTransacDAO.DeleteUser(SupID);
	}
}
