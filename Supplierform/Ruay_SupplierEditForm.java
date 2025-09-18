package RuayRuayMain.Supplierform;

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
import RuayRuayMain.Model.Ruay_Goods;
import RuayRuayMain.Model.Ruay_GoodsType;
import RuayRuayMain.Model.Ruay_Supplier;

public class Ruay_SupplierEditForm extends JDialog implements ActionListener {

	private static final int width = 500;
	private static final int height = 200;

	JTextField Sup_Name_txt;
	JTextField Sup_Address_txt;
	JTextField Sup_Email_txt;
	JTextField Sup_Phone_txt;
	Ruay_Supplier Supplier;

	private static final String addString = "Update";
	private static final String cancelString = "Cancel";

	public Ruay_SupplierEditForm(JFrame frame,Ruay_Supplier Supplier) {
		super(frame, true);
		this.Supplier = Supplier;
		// Create pane as container
		Container pane = getContentPane();
		// set layout manager to manual
		pane.setLayout(null);

		 JLabel PerfromLabel = new JLabel("Add Supplier");
		    JLabel SupplierNameLabel = new JLabel("Sup_Name:");
		    JLabel SupplierAddressLabel = new JLabel("Sup_Address:");
		    JLabel SupplierPhoneLabel = new JLabel("Sup_Phone:");
		    JLabel SupplierEmailLabel = new JLabel("Sup_Email:");

		    // Create text fields
		    Sup_Name_txt = new JTextField(20);
		    Sup_Address_txt = new JTextField(20);
		    Sup_Phone_txt = new JTextField(20);
		    Sup_Email_txt = new JTextField(20);
		    
		    
		    ////////////GET Text////////////////
		    Sup_Name_txt.setText(Supplier.getSupplierName());
		    Sup_Name_txt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    Sup_Address_txt.setText(Supplier.getSupplierAddress());
		    Sup_Phone_txt.setText(Supplier.getSupplierPhone());
		    Sup_Email_txt.setText(Supplier.getSupplierEmail());
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
			Ruay_Supplier NewSup = new Ruay_Supplier(Supplier.getSupplierID(),Sup_Name_txt.getText(),Sup_Address_txt.getText(),Sup_Phone_txt.getText(),Sup_Email_txt.getText());
			//System.out.println("New: "+ NewGoods);
			if (NewSup.getSupplierName().equals(Supplier.getSupplierName()) &&
					NewSup.getSupplierAddress().equals(Supplier.getSupplierAddress())  &&  // Use == for double comparison
					NewSup.getSupplierPhone().equals(Supplier.getSupplierPhone()) &&          // Use == for int comparison
					NewSup.getSupplierEmail().equals(Supplier.getSupplierEmail())) {
				    System.out.println("Nothing Change!");
				} else {
				    System.out.println("Something Change");
				    Ruay_AllLogic.UpdateSupplier(Supplier.getSupplierID(),Sup_Name_txt.getText(),Sup_Address_txt.getText(),Sup_Phone_txt.getText(),Sup_Email_txt.getText());
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
//			}
	else  if (actionCommand.equals(cancelString)) {
			System.out.println("actionCommand:" + cancelString);
			setVisible(false);
		}
	}
}

