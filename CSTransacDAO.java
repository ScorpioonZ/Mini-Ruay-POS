package RuayRuayMain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JComboBox;


import RuayRuayMain.Model.Ruay_Customer;
import RuayRuayMain.Model.Ruay_Goods;
import RuayRuayMain.Model.Ruay_GoodsType;
import RuayRuayMain.Model.Ruay_Supplier;
import RuayRuayMain.Model.Ruay_Transactions;
import RuayRuayMain.Model.Ruay_User;


public class CSTransacDAO {
	
	public static void InsertGoods(Ruay_Goods goods) {
		String insertSql = "INSERT INTO goods(goods_name,goods_type,price,stock,Image) VALUES" + 
				"('" + goods.getgoodsname() + "', '" + goods.getGoodstpye_id() + "', '" + goods.getGoodsprice() + "','"+goods.getAmount() + "','"+goods.getImgPath()+ "')";
		System.out.println("insertSql:" + insertSql);
		CSDB.execute(insertSql);
	}
	static Vector<Ruay_Goods> viewTrans() {
        String selectSql = "SELECT * FROM view_all_goods";
        Vector<Ruay_Goods> transList = new Vector<>();

        CSMariaDBConn connDB = new CSMariaDBConn();
        Connection con = connDB.getConnection();
        Statement stmnt = null;
        if (con != null) {
            try {
                stmnt = con.createStatement();
                ResultSet rs = stmnt.executeQuery(selectSql);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String goods_name = rs.getString("goods_name");
                    String type_name = rs.getString("type_name");
                    int type_id = rs.getInt("type_id");
                    double goods_price = rs.getDouble("price");
                    int amount = rs.getInt("amount");
                    String imgpath = rs.getString("img");
                    Ruay_Goods Goods = new Ruay_Goods(id, goods_name,type_id, type_name, goods_price, amount, imgpath);
                    transList.add(Goods);
                }
                stmnt.close();
                con.close();
                System.out.println("Goods Retrieved successfully.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return transList; // Return the data
    }
	
	public static Vector<Ruay_GoodsType> viewGoodsTypes() {
	    String selectSql = "SELECT type_id, type_name FROM goods_type";  // Assuming this is your table name
	    Vector<Ruay_GoodsType> goodsTypeList = new Vector<>();  // Create a Vector to store goods types

	    CSMariaDBConn connDB = new CSMariaDBConn();  // Your custom DB connection class
	    Connection con = connDB.getConnection();
	    Statement stmnt = null;

	    if (con != null) {
	        try {
	            stmnt = con.createStatement();
	            ResultSet rs = stmnt.executeQuery(selectSql);
	            while (rs.next()) {
	                int typeId = rs.getInt("type_id");
	                String typeName = rs.getString("type_name");
	                Ruay_GoodsType goodsType = new Ruay_GoodsType(typeId, typeName);
	                goodsTypeList.add(goodsType); 
	            }
	            stmnt.close();
	            con.close();
	            System.out.println("Goods types retrieved successfully.");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    return goodsTypeList; 
	}
	public static Vector<Ruay_Transactions> viewTransactions() {
	    String selectSql = "SELECT * FROM view_transactions";  // Assuming this is your table name
	    Vector<Ruay_Transactions> Translist = new Vector<>();  // Create a Vector to store goods types

	    CSMariaDBConn connDB = new CSMariaDBConn();  // Your custom DB connection class
	    Connection con = connDB.getConnection();
	    Statement stmnt = null;

	    if (con != null) {
	        try {
	            stmnt = con.createStatement();
	            ResultSet rs = stmnt.executeQuery(selectSql);
	            while (rs.next()) {
	                int tranID = rs.getInt("id");
	                int trangood_id = rs.getInt("good_id");
	                String trangood_name = rs.getString("goods_name");
	                String trandate = rs.getString("date");
	                int amount = rs.getInt("amount");
	                double Total_price = rs.getDouble("Total_price");
	                int UserID = rs.getInt("userid");
	                Ruay_Transactions transaction = new Ruay_Transactions(tranID, trangood_id,trangood_name,trandate,amount,Total_price,UserID);
	                Translist.add(transaction); 
	            }
	            stmnt.close();
	            con.close();
	            System.out.println("Transactions Retrieved");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	    return Translist; 
	}
	public static void UpdateGoods(Ruay_Goods goods) {
	    String updateSql = "UPDATE goods SET goods_name = '" + goods.getgoodsname() + 
	                       "', goods_type = '" + goods.getGoodstpye_id() + 
	                       "', price = '" + goods.getGoodsprice() + 
	                       "', stock = '" + goods.getAmount() + 
	                       "', Image = '" + goods.getImgPath() + 
	                       "' WHERE good_id = '" + goods.getGoodId() + "'";
	    CSDB.execute(updateSql);
	}

	

	
	public static void DeleteGoods(int GoodsID) {
		String DeleteSql = "Delete from goods where good_id = '"+GoodsID+"' ";
		//System.out.println("DeleteSQL: " + DeleteSql);
		CSDB.execute(DeleteSql);
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public static void InsertGoodType(Ruay_GoodsType goodtype) {
		String insertsql = "insert into goods_type(type_name) value('"+goodtype.getTypeName()+"')";
		CSDB.execute(insertsql);
	}
	
	public static void UpdateGoodType(Ruay_GoodsType goodtype) {
		String Updatesql = "update goods_type set type_id = '" + goodtype.getTypeId()+ 
				"', type_name = '"+goodtype.getTypeName() +  
				"' where type_id = '"+goodtype.getTypeId()+"' ";
		CSDB.execute(Updatesql);
	}
	public static void DeleteGoodType(Ruay_GoodsType goodtype) {
		String DeleteSql = "Delete from goods_type where type_id = '"+goodtype.getTypeId()+"' ";
		//System.out.println("DeleteSQL: " + DeleteSql);
		CSDB.execute(DeleteSql);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void UpdateTransactions (Ruay_Transactions trans) {
	    String updateSql = "update transactions set good_id = '"+ trans.getGood_ID() + 
	    		"', id = '"+trans.getID()+
	    		"', date = '"+trans.getDate()+
	    		"', amount = '"+trans.getAmount()+
	    		"', userID = '"+trans.getUserID()+
	    		"' where id = '"+trans.getID()+"' ";
	    CSDB.execute(updateSql);
	}
	
	public static void DeleteTransactions(Ruay_Transactions trans) {
		String DeleteSql = "delete from transactions where id = '"+trans.getID()+"'";
		CSDB.execute(DeleteSql);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static Vector<Ruay_User> viewUsers() {
	    String selectSql = "SELECT * FROM view_users";  // Assuming this is your table name
	    Vector<Ruay_User> UserList = new Vector<>();  // Create a Vector to store goods types

	    CSMariaDBConn connDB = new CSMariaDBConn();  // Your custom DB connection class
	    Connection con = connDB.getConnection();
	    Statement stmnt = null;

	    if (con != null) {
	        try {
	            stmnt = con.createStatement();
	            ResultSet rs = stmnt.executeQuery(selectSql);
	            while (rs.next()) {
	                int userID = rs.getInt("id");
	                String username = rs.getString("username");
	                String password = rs.getString("password");
	                String privilege = rs.getString("privilege");
	                String name = rs.getString("name");
	                int cus_id = rs.getInt("cus_id");
	                Ruay_User users = new Ruay_User(userID, username,password,privilege,name,cus_id);
	                UserList.add(users); 
	            }
	            stmnt.close();
	            con.close();
	            System.out.println("Goods types retrieved successfully.");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    return UserList; 
	}
	public static void AddUser(Ruay_User User,String cusid) {
		String AddSql = "insert into users(username,password,privilege,cus_id) values('"+User.getUsername()+"','"+User.getPassword()+"','"+User.getPrivilege()+"' , '"+cusid+"')";
		CSDB.execute(AddSql);
	}
	public static void UpdateUser(Ruay_User User) {
		String UpdateUser = "update users set username = '"+User.getUsername()+"' , password = '"+User.getPassword()+"' , privilege = '"+User.getPrivilege()+"' ,cus_id = '"+User.getcusId()+"'  ";
		CSDB.execute(UpdateUser);
	}
	public static void DeleteUser(int idx) {
		String DeleteUser = "delete from users where id = '"+idx+"' ";
		CSDB.execute(DeleteUser);
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static Vector<Ruay_Supplier> viewSupplier() {
	    String selectSql = "SELECT * FROM suppliers";  // Assuming this is your table name
	    Vector<Ruay_Supplier> Suplist = new Vector<>();  // Create a Vector to store goods types

	    CSMariaDBConn connDB = new CSMariaDBConn();  // Your custom DB connection class
	    Connection con = connDB.getConnection();
	    Statement stmnt = null;

	    if (con != null) {
	        try {
	            stmnt = con.createStatement();
	            ResultSet rs = stmnt.executeQuery(selectSql);
	            while (rs.next()) {
	                int userID = rs.getInt("id");
	                String username = rs.getString("Name");
	                String password = rs.getString("Address");
	                String privilege = rs.getString("Phone");
	                String name = rs.getString("Email");
	                Ruay_Supplier users = new Ruay_Supplier(userID, username,password,privilege,name);
	                Suplist.add(users); 
	            }
	            stmnt.close();
	            con.close();
	            System.out.println("Goods types retrieved successfully.");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    return Suplist; 
	}
	
	public static void InsertSupplier(Ruay_Supplier Supplier) {
		String AddSql ="INSERT INTO suppliers(Name, Address, Phone, Email) VALUES ('"
			    + Supplier.getSupplierName() + "', '"
			    + Supplier.getSupplierAddress() + "', '"
			    + Supplier.getSupplierPhone() + "', '"
			    + Supplier.getSupplierEmail() + "')";

		CSDB.execute(AddSql);
	}
	public static void UpdateSupplier(Ruay_Supplier Supplier) {
		String UpdateSql = "update suppliers set name = '"+Supplier.getSupplierName()+"' , address = '"+Supplier.getSupplierAddress()+"' , phone = '"+Supplier.getSupplierPhone()+"' , email = '"+Supplier.getSupplierEmail()+"' ";
		CSDB.execute(UpdateSql);
	}
	public static void DeleteSupplier(Ruay_Supplier Supplier) {
		String DeleteSupSql = "delete from suppliers where id = '"+Supplier.getSupplierID()+"' ";
		CSDB.execute(DeleteSupSql);
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static Vector<Ruay_Customer> viewCustomer() {
	    String selectSql = "SELECT * FROM customer";  // Assuming this is your table name
	    Vector<Ruay_Customer> Suplist = new Vector<>();  // Create a Vector to store goods types

	    CSMariaDBConn connDB = new CSMariaDBConn();  // Your custom DB connection class
	    Connection con = connDB.getConnection();
	    Statement stmnt = null;

	    if (con != null) {
	        try {
	            stmnt = con.createStatement();
	            ResultSet rs = stmnt.executeQuery(selectSql);
	            while (rs.next()) {
	                int userID = rs.getInt("id");
	                String username = rs.getString("Name");
	                String password = rs.getString("Address");
	                String privilege = rs.getString("Phone");
	                String name = rs.getString("Email");
	                Ruay_Customer users = new Ruay_Customer(userID, username,password,privilege,name);
	                Suplist.add(users); 
	            }
	            stmnt.close();
	            con.close();
	            System.out.println("Goods types retrieved successfully.");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    return Suplist; 
	}
	public static void InsertCustomer(Ruay_Customer Customer) {
		String AddSql ="INSERT INTO customer(Name, Address, Phone, Email) VALUES ('"
			    + Customer.getCustomerName() + "', '"
			    + Customer.getCustomerAddress() + "', '"
			    + Customer.getCustomerPhone() + "', '"
			    + Customer.getCustomerEmail() + "')";

		CSDB.execute(AddSql);
	}
	public static void UpdateCustomer(Ruay_Customer Customer) {
		String UpdateSql = "update customer set name = '"+Customer.getCustomerName()+"' , address = '"+Customer.getCustomerAddress()+"' , phone = '"+Customer.getCustomerPhone()+"' , email = '"+Customer.getCustomerEmail()+"' where id = '"+Customer.getCustomerID()+"'";
		CSDB.execute(UpdateSql);
	}
	public static void DeleteCustomer(Ruay_Customer Customer) {
		String DeleteSupSql = "delete from customer where id = '"+Customer.getCustomerID()+"' ";
		CSDB.execute(DeleteSupSql);
		
	}
	
	
	
	
	
	
	
	
	


}
