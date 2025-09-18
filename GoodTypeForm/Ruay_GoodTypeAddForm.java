package RuayRuayMain.GoodTypeForm;

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
import RuayRuayMain.Model.Ruay_GoodsType;

public class Ruay_GoodTypeAddForm extends JDialog implements ActionListener {

	private static final int width = 400;
	private static final int height = 150;

	JTextField Goods_type_txt;
	JTextField Goodsname_txt;
	JTextField goods_price_txt;
	JTextField goods_amount_txt;
	private int lastidx;
	private JComboBox<Ruay_GoodsType> goods_types_combo;

	private static final String addString = "Add";
	private static final String cancelString = "Cancel";
	public Ruay_GoodTypeAddForm(JFrame frame, int lastidx) {
	    super(frame, true);
	    this.lastidx = lastidx;
	    // Create pane as container
	    Container pane = getContentPane();
	    pane.setLayout(null);
	    // Create labels
	    JLabel PerfromLabel = new JLabel("Add GoodType");
	    JLabel GoodType = new JLabel("Type Name:");

	    // Initialize combo box and add types from the vector

	    // Create text fields
	    Goodsname_txt = new JTextField(20);

	    // Create buttons
	    JButton addBtn = new JButton(addString);
	    JButton cancelBtn = new JButton(cancelString);

	    // Add action listeners to buttons
	    addBtn.addActionListener(this);
	    cancelBtn.addActionListener(this);

	    // Add components to the pane
	    pane.add(PerfromLabel);
	    pane.add(GoodType);
	    pane.add(Goodsname_txt);
	    pane.add(GoodType);
	    pane.add(addBtn);
	    pane.add(cancelBtn);

	    // Set component bounds (sizes and positions)
	    Dimension size = PerfromLabel.getPreferredSize();
	    PerfromLabel.setBounds((frame.getWidth() - size.width) / 10, 5, size.width, size.height);

	    size = Goodsname_txt.getPreferredSize();
	    Goodsname_txt.setBounds(100, 20, size.width, size.height);

	    size = GoodType.getPreferredSize();
	    GoodType.setBounds(30, 20, size.width, size.height);

	    size = addBtn.getPreferredSize();
	    addBtn.setBounds(30, 60, size.width, size.height);

	    size = cancelBtn.getPreferredSize();
	    cancelBtn.setBounds(150, 60, size.width, size.height);

	    // Set dialog size and location
	    setSize(width, height);
	    setLocationRelativeTo(null);
	}


	@Override
	 public void actionPerformed(ActionEvent evt) {
	     String actionCommand = evt.getActionCommand();
	     String Type_name = String.valueOf(Goodsname_txt.getText());
	     Ruay_AllLogic.InsertGoodType(lastidx, Type_name);
	     setVisible(false);
	     CSGUIMain.displayTransactions();
	     
//	     System.out.println("Last_idx: " + lastidx);
//	     if (actionCommand.equals(addString)) {
//	    	 //System.out.println(goods_types_combo.getSelectedIndex()+1);
//	         try {
//	 			double price = Double.parseDouble(goods_price_txt.getText());
//				int amount = Integer.parseInt(goods_amount_txt.getText());
//				String Type_name = String.valueOf(goods_types_combo.getSelectedItem());
//				//Ruay_Goods NewGoods = new Ruay_Goods(lastidx,Goodsname_txt.getText(),goods_types_combo.getSelectedIndex()+1,Type_name,price,amount,null);
//				CSLogicTrans.InsertGoods(lastidx,Goodsname_txt.getText(),goods_types_combo.getSelectedIndex()+1,Type_name,price,amount,null);
//
//	             // Insert the goods transaction into the database
//	             //CSTransacDAO.InsertGoods(NewGoods);
//
//	             // Clear the input fields
//	             Goodsname_txt.setText(null);
//	             Goodsname_txt.setText(null);
//	             goods_price_txt.setText(null);
//	             goods_amount_txt.setText(null);
//	             setVisible(false);
//	             CSGUIMain.displayTransactions();
//	         } catch (Exception ex) {
//	             System.err.println("Error! Invalid data: " + ex.getMessage());
//	         }
//	     } else if (actionCommand.equals(cancelString)) {
//	         System.out.println("actionCommand: " + cancelString);
//	         setVisible(false);  // Close the form
//	     }
	 }

}	

