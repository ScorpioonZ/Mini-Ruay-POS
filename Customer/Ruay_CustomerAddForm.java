package RuayRuayMain.Customer;

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

public class Ruay_CustomerAddForm extends JDialog implements ActionListener {

	private static final int width = 500;
	private static final int height = 250;

	JTextField Sup_Name_txt;
	JTextField Sup_Address_txt;
	JTextField Sup_Email_txt;
	JTextField Sup_Phone_txt;
	private int lastidx;

	private static final String addString = "Add";
	private static final String cancelString = "Cancel";
	public Ruay_CustomerAddForm(JFrame frame,int lastidx) {
	    super(frame, true);
	    this.lastidx=lastidx;
	    // Create pane as container
	    Container pane = getContentPane();
	    pane.setLayout(null);
	    // Create labels
	    JLabel PerfromLabel = new JLabel("Add Customer");
	    JLabel SupplierNameLabel = new JLabel("Cus_name:");
	    JLabel SupplierAddressLabel = new JLabel("Cus_Address:");
	    JLabel SupplierPhoneLabel = new JLabel("Cus_Phone:");
	    JLabel SupplierEmailLabel = new JLabel("Cus_Email:");

	    // Create text fields
	    Sup_Name_txt = new JTextField(20);
	    Sup_Address_txt = new JTextField(20);
	    Sup_Phone_txt = new JTextField(20);
	    Sup_Email_txt = new JTextField(20);

	    // Create buttons
	    JButton addBtn = new JButton(addString);
	    JButton cancelBtn = new JButton(cancelString);

	    // Add action listeners to buttons
	    addBtn.addActionListener(this);
	    cancelBtn.addActionListener(this);

	    // Add components to the pane
	    pane.add(PerfromLabel);
	    pane.add(SupplierNameLabel);
	    pane.add(SupplierAddressLabel);
	    pane.add(SupplierPhoneLabel);
	    pane.add(SupplierEmailLabel);
	    pane.add(Sup_Name_txt);
	    pane.add(Sup_Address_txt);
	    pane.add(Sup_Phone_txt);
	    pane.add(Sup_Email_txt);
	    pane.add(addBtn);
	    pane.add(cancelBtn);

	    // Set component bounds (sizes and positions)
	    Dimension size = PerfromLabel.getPreferredSize();
	    PerfromLabel.setBounds((frame.getWidth() - size.width) / 2, 5, size.width, size.height);

	    size = SupplierNameLabel.getPreferredSize();
	    SupplierNameLabel.setBounds(30, 20, size.width, size.height);
	    
	    size = Sup_Name_txt.getPreferredSize();
	    Sup_Name_txt.setBounds(100, 20, size.width, size.height);

	    size = SupplierAddressLabel.getPreferredSize();
	    SupplierAddressLabel.setBounds(30, 50, size.width, size.height);

	    size = Sup_Address_txt.getPreferredSize();
	    Sup_Address_txt.setBounds(100, 50, size.width, size.height);

	    size = SupplierPhoneLabel.getPreferredSize();
	    SupplierPhoneLabel.setBounds(30, 80, size.width, size.height);

	    size = Sup_Phone_txt.getPreferredSize();
	    Sup_Phone_txt.setBounds(100, 80, size.width, size.height);
	    
	    size = SupplierEmailLabel.getPreferredSize();
	    SupplierEmailLabel.setBounds(30, 110, size.width, size.height);
	    
	    size = Sup_Email_txt.getPreferredSize();
	    Sup_Email_txt.setBounds(100, 110, size.width, size.height);
	    
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
	     if (actionCommand.equals(addString)) {
	    	 //System.out.println(goods_types_combo.getSelectedIndex()+1);
	         try {
				//Ruay_Customer NewGoods = new Ruay_Customer(lastidx,Sup_Name_txt.getText(),Sup_Address_txt.getText(),Sup_Phone_txt.getText(),Sup_Email_txt.getText());
				Ruay_AllLogic.InsertCustomer(lastidx,Sup_Name_txt.getText(),Sup_Address_txt.getText(),Sup_Phone_txt.getText(),Sup_Email_txt.getText());
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

