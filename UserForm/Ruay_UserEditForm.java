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
import RuayRuayMain.Model.Ruay_Goods;
import RuayRuayMain.Model.Ruay_GoodsType;
import RuayRuayMain.Model.Ruay_Supplier;
import RuayRuayMain.Model.Ruay_User;

public class Ruay_UserEditForm extends JDialog implements ActionListener {

	private static final int width = 500;
	private static final int height = 200;

	JTextField Username_txt;
	JTextField Password_txt;
	JComboBox CusID,Privilege;
	Ruay_User user;

	private static final String addString = "Update";
	private static final String cancelString = "Cancel";
	public Ruay_UserEditForm(JFrame frame,Vector<Ruay_Customer> Customer,Ruay_User User) {
	    super(frame, true);
	    this.user = User;
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

	    
	    ////////////////////Assign Value/////////////////////////
	    
	    Username_txt.setText(User.getUsername());
	    Password_txt.setText(User.getPassword());
	    if(User.getPrivilege().equals("admin")) {
	    	Privilege.setSelectedIndex(1);
	    }else {
	    	Privilege.setSelectedIndex(0);
	    }
		CusID.setSelectedIndex(User.getcusId()-1);
	    
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

		// set size and position for container
		pane.setPreferredSize(new Dimension(width, height));

		pack();
		setVisible(false);
	}


	public void actionPerformed(ActionEvent evt) {
		String actionCommand = evt.getActionCommand();
		if (actionCommand.equals(addString)) {
			//System.out.println(goods_types_combo.getSelectedIndex()+1+"Type_Name" + goods_types_combo.getSelectedItem());
			//System.out.println("OLD: "+ Good);
			String priv = "";
			if (Privilege.getSelectedIndex() == 0) {
				priv = "user";
			}else {
				priv = "admin";
			}
			Ruay_User NewSup = new Ruay_User(user.getId(),Username_txt.getText(),Password_txt.getText(),priv,user.getName(),CusID.getSelectedIndex()-1);
			//System.out.println("New: "+ NewGoods);
			if (NewSup.getName().equals(user.getName()) &&
					NewSup.getUsername().equals(user.getUsername())  &&  // Use == for double comparison
					NewSup.getPassword().equals(user.getPassword()) &&          // Use == for int comparison
					NewSup.getPrivilege().equals(user.getPrivilege()) && 
					NewSup.getcusId() == user.getcusId()) {
				    System.out.println("Nothing Change!");
				} else {
				    System.out.println("Something Change");
				    //Ruay_AllLogic.UpdateSupplier();
				    CSGUIMain.displayTransactions();
				    setVisible(false);
				}
		}
//			System.out.println("-- actionCommand:" + addString);
//			try {
//					int plate = Integer.parseInt(Goods_txt.getText());
//					String model = Quantity_txt.getText();
//					String make = amount_txt.getText();
//					int quantity = Integer.parseInt(model);
//					double amount = Double.parseDouble(make);
//			CSGoods trans = new CSGoods(plate, quantity, amount);
//			CSTransacDAO.insertTrans(trans);
//			Goods_txt.setText(null);
//			Quantity_txt.setText(null);
//			amount_txt.setText(null);
//			} catch (Exception ex) {
//				System.err.println("Error! Invalid data.");
			else  if (actionCommand.equals(cancelString)) {
			System.out.println("actionCommand:" + cancelString);
			setVisible(false);
		}
	}
}

