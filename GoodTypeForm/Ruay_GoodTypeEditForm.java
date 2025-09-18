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

import RuayRuayMain.Model.Ruay_Goods;
import RuayRuayMain.Model.Ruay_GoodsType;

public class Ruay_GoodTypeEditForm extends JDialog implements ActionListener {

	private static final int width = 500;
	private static final int height = 150;

	JTextField Goodsid_txt;
	JTextField Goods_name_txt;
	JTextField goods_price_txt;
	JTextField goods_amount_txt;
	Ruay_GoodsType GoodType;
	private JComboBox<Ruay_GoodsType> goods_types_combo;

	private static final String addString = "Update";
	private static final String cancelString = "Cancel";

	public Ruay_GoodTypeEditForm(JFrame frame,Ruay_GoodsType GoodType) {
		super(frame, true);
		this.GoodType = GoodType;
		System.out.println(GoodType);
		// Create pane as container
		Container pane = getContentPane();
		// set layout manager to manual
		pane.setLayout(null);

		// create labels
		JLabel PerfromLabel = new JLabel("Edit GoodType");
		JLabel goodtypesidLabel = new JLabel("GoodType_id:");
		JLabel goodtypenameLabel = new JLabel("GoodsType_name:");

		// create buttons
		JButton addBtn = new JButton(addString);
		JButton cancelBtn = new JButton(cancelString);

		// create texts
		Goodsid_txt = new JTextField(10);
		Goods_name_txt= new JTextField(20);
		Goods_name_txt.setFont(new Font("Tahoma", Font.PLAIN, 14));

		// create control buttons.
		addBtn.addActionListener(this);
		cancelBtn.addActionListener(this);

		// add labels
		pane.add(PerfromLabel);
		pane.add(goodtypesidLabel);
		pane.add(goodtypenameLabel);

		// add text fields
		pane.add(Goodsid_txt);
		pane.add(Goods_name_txt);

		// add control buttons
		pane.add(addBtn);
		pane.add(cancelBtn);
		//Assign Value
		int goodtypeId = GoodType.getTypeId();  // Good ID as an integer  // Quantity as an integer
		String goodstypename = GoodType.getTypeName();
		Goodsid_txt.setText(String.valueOf(goodtypeId));  // Convert goodId (int) to String
		Goodsid_txt.setEditable(false);
		Goods_name_txt.setText(String.valueOf(goodstypename));
		
		// set sizes and positions for labels
	    Dimension size = PerfromLabel.getPreferredSize();
	    PerfromLabel.setBounds((frame.getWidth() - size.width) / 2, 5, size.width, size.height);
	    size = goodtypesidLabel.getPreferredSize();
	    goodtypesidLabel.setBounds(10, 30, size.width, size.height);
	    size = Goodsid_txt.getPreferredSize();
	    Goodsid_txt.setBounds(100, 30, size.width, size.height);
	    size = goodtypenameLabel.getPreferredSize();
	    goodtypenameLabel.setBounds(10, 55, size.width, size.height);
	    size = Goods_name_txt.getPreferredSize();
	    Goods_name_txt.setBounds(120, 55, size.width, size.height);
	    
	    
	    size = addBtn.getPreferredSize();
	    addBtn.setBounds(30, 80, size.width, size.height);

	    size = cancelBtn.getPreferredSize();
	    cancelBtn.setBounds(150, 80, size.width, size.height);

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
			double price = Double.parseDouble(goods_price_txt.getText());
			int amount = Integer.parseInt(goods_amount_txt.getText());
			String Type_name = String.valueOf(goods_types_combo.getSelectedItem());
			Ruay_Goods NewGoods = new Ruay_Goods(GoodType.getTypeId(),Goods_name_txt.getText(),goods_types_combo.getSelectedIndex()+1,"",price,amount,null);
			//System.out.println("New: "+ NewGoods);
//			if (NewGoods.getgoodsname().equals(Good.getgoodsname()) &&
//				    NewGoods.getGoodsprice() == Good.getGoodsprice() &&  // Use == for double comparison
//				    NewGoods.getAmount() == Good.getAmount() &&          // Use == for int comparison
//				    NewGoods.getGoodstpye_id() == Good.getGoodstpye_id()) {
//				    System.out.println("Nothing Change!");
//				} else {
//				    System.out.println("Something Change");
//				    CSLogicTrans.UpdateGoods(Good.getGoodId(),Goods_name_txt.getText(),goods_types_combo.getSelectedIndex()+1,"",price,amount,null);
//				    CSGUIMain.displayTransactions();
//				    setVisible(false);
//				}
		}
//			System.out.println("-- actionCommand:" + addString);
//			try {
//					int plate = Integer.parseInt(Goods_txt.getText());
//					String model = Quantity_txt.getText();
//					String make = amount_txt.getText();
//					int quantity = Integer.parseInt(model);
//					double amount = Double.parseDouble(make);
//					CSGoods trans = new CSGoods(plate, quantity, amount);
//				CSTransacDAO.insertTrans(trans);
//				Goods_txt.setText(null);
//				Quantity_txt.setText(null);
//				amount_txt.setText(null);
//			} catch (Exception ex) {
//				System.err.println("Error! Invalid data.");
//			}
//		} else if (actionCommand.equals(cancelString)) {
//			System.out.println("actionCommand:" + cancelString);
//			setVisible(false);
//		}
	}
}

