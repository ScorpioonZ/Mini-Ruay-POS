package RuayRuayMain.UserForm;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import RuayRuayMain.CSGUIMain;
import RuayRuayMain.Ruay_AllLogic;
import RuayRuayMain.Model.Ruay_Customer;
import RuayRuayMain.Model.Ruay_GoodsType;

public class Ruay_UserAddForm extends JDialog implements ActionListener {

	private static final int width = 500;
	private static final int height = 250;

	JTextField Username_txt;
	JTextField Password_txt;
	JComboBox CusID,Privilege;
	private int lastidx;

	private static final String addString = "Add";
	private static final String cancelString = "Cancel";
	public Ruay_UserAddForm(JFrame frame,int lastidx,Vector<Ruay_Customer> Customer) {
	    super(frame, true);
	    this.lastidx=lastidx;
	    // Create pane as container
	    Container pane = getContentPane();
	    pane.setLayout(null);
	    // Create labels
	    
	    JLabel PerfromLabel = new JLabel("Add User");
	    JLabel UsernameLabel = new JLabel("Username:");
	    JLabel PasswordLabel = new JLabel("Password:");
	    JLabel PrivilgeLabel = new JLabel("Privilege:");
	    JLabel CustomerLabel = new JLabel("Customer:");
	    
	    Privilege = new JComboBox<>();
	    Privilege.addItem("User");
	    Privilege.addItem("Admin");
	    System.out.println(Customer);
	    
	    CusID = new JComboBox<>();
		for (Ruay_Customer type : Customer) {
			CusID.addItem(type);  // Add each type to the combo box
		}
		CusID.setFont(new Font("Tahoma", Font.PLAIN, 14));

	    // Create text fields
	    Username_txt = new JTextField(20);
	    Password_txt = new JTextField(20);

	    // Create buttons
	    JButton addBtn = new JButton(addString);
	    JButton cancelBtn = new JButton(cancelString);

	    // Add action listeners to buttons
	    addBtn.addActionListener(this);
	    cancelBtn.addActionListener(this);

	    // Add components to the pane
	    pane.add(PerfromLabel);
	    pane.add(UsernameLabel);
	    pane.add(PasswordLabel);
	    pane.add(PrivilgeLabel);
	    pane.add(CustomerLabel);
	    pane.add(Username_txt);
	    pane.add(Password_txt);
	    pane.add(CusID);
	    pane.add(Privilege);
	    pane.add(addBtn);
	    pane.add(cancelBtn);

	    // Set component bounds (sizes and positions)
	    Dimension size = PerfromLabel.getPreferredSize();
	    PerfromLabel.setBounds((frame.getWidth() - size.width) / 5, 5, size.width, size.height);

	    size = UsernameLabel.getPreferredSize();
	    UsernameLabel.setBounds(30, 20, size.width, size.height);
	    
	    size = Username_txt.getPreferredSize();
	    Username_txt.setBounds(100, 20, size.width, size.height);

	    size = PasswordLabel.getPreferredSize();
	    PasswordLabel.setBounds(30, 50, size.width, size.height);

	    size = Password_txt.getPreferredSize();
	    Password_txt.setBounds(100, 50, size.width, size.height);

	    size = PrivilgeLabel.getPreferredSize();
	    PrivilgeLabel.setBounds(30, 80, size.width, size.height);

	    size = Privilege.getPreferredSize();
	    Privilege.setBounds(100, 80, size.width, size.height);
	    
	    size = CustomerLabel.getPreferredSize();
	    CustomerLabel.setBounds(30, 110, size.width, size.height);
	    
	    size = CusID.getPreferredSize();
	    CusID.setBounds(100, 110, size.width, size.height);
	    
	    size = addBtn.getPreferredSize();
	    addBtn.setBounds(30, 150, size.width, size.height);

	    size = cancelBtn.getPreferredSize();
	    cancelBtn.setBounds(150, 150, size.width, size.height);

	    // Set dialog size and location
	    setSize(width, height);
	    setLocationRelativeTo(null);
	}
	
	@Override
	 public void actionPerformed(ActionEvent evt) {
	     String actionCommand = evt.getActionCommand();
	     System.out.println("Last_idx: " + lastidx);
	     if (actionCommand.equals(addString)) {
				String priv = "";
				if (Privilege.getSelectedIndex() == 0) {
					priv = "user";
				}else {
					priv = "admin";
				}
	    	 //System.out.println(goods_types_combo.getSelectedIndex()+1);
	         try {
	        	 String Cusid = CusID.getSelectedItem().toString().substring(0,2).strip();
				//Ruay_Goods NewGoods = new Ruay_Goods(lastidx,Goodsname_txt.getText(),goods_types_combo.getSelectedIndex()+1,Type_name,price,amount,null);
				Ruay_AllLogic.InsertUser(lastidx,Username_txt.getText(),Password_txt.getText(),priv,Cusid);
				
	             setVisible(false);
	             CSGUIMain.displayTransactions();
	         } catch (Exception ex) {
	             System.err.println("Error! Invalid data: " + ex.getMessage());
	         }
	     } else if (actionCommand.equals(cancelString)) {
	         System.out.println("actionCommand: " + cancelString);
	         setVisible(false);  // Close the form
	     }
	 }

}	

