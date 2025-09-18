package RuayRuayMain;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import RuayRuayMain.Model.Ruay_GoodsType;

public class CSTransAddForm extends JDialog implements ActionListener {

	private static final int width = 500;
	private static final int height = 400;

	JTextField Goods_type_txt;
	JTextField Goodsname_txt;
	JTextField goods_price_txt;
	JTextField goods_amount_txt;
	private int lastidx;
	private JComboBox<Ruay_GoodsType> goods_types_combo;
	private JLabel imageLabel;
	private JButton uploadBtn;
	private File selectedFile;

	private static final String addString = "Add";
	private static final String cancelString = "Cancel";

	public CSTransAddForm(JFrame frame, Vector<Ruay_GoodsType> goodsTypes, int lastidx) {
		super(frame, true);
		this.lastidx = lastidx;

		// Create pane as container
		Container pane = getContentPane();
		pane.setLayout(null);

		// Create labels
		JLabel PerfromLabel = new JLabel("Add Good");
		JLabel goodsnameLabel = new JLabel("Goods Name:");
		JLabel goodstypeLabel = new JLabel("Goods Type:");
		JLabel goodspriceLabel = new JLabel("Goods Price:");
		JLabel goodsamountLabel = new JLabel("Amount:");
		JLabel imageLabelTitle = new JLabel("Image:");

		// Initialize combo box and add types from the vector
		goods_types_combo = new JComboBox<>();
		for (Ruay_GoodsType type : goodsTypes) {
			goods_types_combo.addItem(type);  // Add each type to the combo box
		}
		goods_types_combo.setFont(new Font("Tahoma", Font.PLAIN, 14));

		// Create text fields
		Goodsname_txt = new JTextField(20);
		goods_price_txt = new JTextField(20);
		goods_amount_txt = new JTextField(20);

		// Create buttons
		JButton addBtn = new JButton(addString);
		JButton cancelBtn = new JButton(cancelString);
		uploadBtn = new JButton("Upload Image");
		uploadBtn.addActionListener(e -> {
		    chooseImageFile(); // Call the method to choose an image file
		});

		// Add action listeners to buttons
		addBtn.addActionListener(this);
		cancelBtn.addActionListener(this);

		// Create label to display selected image preview
		imageLabel = new JLabel();
		imageLabel.setPreferredSize(new Dimension(100, 100));

		// Add components to the pane
		pane.add(PerfromLabel);
		pane.add(goodsnameLabel);
		pane.add(goodstypeLabel);
		pane.add(goods_types_combo);
		pane.add(goodspriceLabel);
		pane.add(goodsamountLabel);
		pane.add(imageLabelTitle);
		pane.add(imageLabel);  // Add image preview label
		pane.add(Goodsname_txt);
		pane.add(goods_price_txt);
		pane.add(goods_amount_txt);
		pane.add(addBtn);
		pane.add(cancelBtn);
		pane.add(uploadBtn);  // Add choose image button

		// Set component bounds (sizes and positions)
		Dimension size = PerfromLabel.getPreferredSize();
		PerfromLabel.setBounds((frame.getWidth() - size.width) / 2, 5, size.width, size.height);

		size = goodsnameLabel.getPreferredSize();
		goodsnameLabel.setBounds(10, 20, size.width, size.height);
		size = Goodsname_txt.getPreferredSize();
		Goodsname_txt.setBounds(100, 20, size.width, size.height);

		size = goodstypeLabel.getPreferredSize();
		goodstypeLabel.setBounds(10, 50, size.width, size.height);
		size = goods_types_combo.getPreferredSize();
		goods_types_combo.setBounds(100, 50, size.width, size.height);

		size = goodspriceLabel.getPreferredSize();
		goodspriceLabel.setBounds(10, 80, size.width, size.height);
		size = goods_price_txt.getPreferredSize();
		goods_price_txt.setBounds(100, 80, size.width, size.height);

		size = goodsamountLabel.getPreferredSize();
		goodsamountLabel.setBounds(10, 110, size.width, size.height);
		size = goods_amount_txt.getPreferredSize();
		goods_amount_txt.setBounds(100, 110, size.width, size.height);

		size = imageLabelTitle.getPreferredSize();
		imageLabelTitle.setBounds(10, 140, size.width, size.height);
		imageLabel.setBounds(100, 140, 100, 100);  // Image preview location

		size = uploadBtn.getPreferredSize();
		uploadBtn.setBounds(300, 150, size.width, size.height);

		size = addBtn.getPreferredSize();
		addBtn.setBounds(30, 250, size.width, size.height);

		size = cancelBtn.getPreferredSize();
		cancelBtn.setBounds(150, 250, size.width, size.height);

		// Set dialog size and location
		setSize(width, height);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String actionCommand = evt.getActionCommand();
		System.out.println("Last_idx: " + lastidx);

		if (actionCommand.equals(addString)) {
			try {
		        double price = Double.parseDouble(goods_price_txt.getText());
		        int amount = Integer.parseInt(goods_amount_txt.getText());
		        String Type_name = String.valueOf(goods_types_combo.getSelectedItem());
		        String ImagePath="";
		        // Step 3: Upload the image using SFTP
		        if (selectedFile != null) {
		            String remoteDir = "/opt/lampp/htdocs/java/img"; // Adjust this to your needs
		            ImagePath = "/java/img/" + selectedFile.getName();
		            SFTPUploadImage.uploadFile(
		                selectedFile.getAbsolutePath(), 
		                remoteDir, 
		                "sczfile.online",        // Replace with actual SFTP host
		                "java_upload",        // Replace with actual SFTP user
		                "java1234"     // Replace with actual SFTP password
		            );
		        }else if(selectedFile == null) {
		        	ImagePath = "";
		        }
			  // Return the remote file path

		        // Step 4: Insert goods with the image path
		        Ruay_AllLogic.InsertGoods(lastidx, Goodsname_txt.getText(), goods_types_combo.getSelectedIndex() + 1, Type_name, price, amount, ImagePath);
		        
		        // Clear input fields and hide form
		        Goodsname_txt.setText(null);
		        goods_price_txt.setText(null);
		        goods_amount_txt.setText(null);
		        selectedFile = null;
		        setVisible(false);
		        
		        CSGUIMain.displayTransactions();
		    } catch (Exception ex) {
		        System.err.println("Error! Invalid data: " + ex.getMessage());
		    }
		} else if (actionCommand.equals(cancelString)) {
			setVisible(false);  // Close the form
		}
	}
	private void chooseImageFile() {
	    FileDialog fileDialog = new FileDialog(this, "Select an Image", FileDialog.LOAD);
	    fileDialog.setVisible(true);
	    String file = fileDialog.getDirectory() + fileDialog.getFile();
	    if (file != null) {
	        selectedFile = new File(file);
	        System.out.println("Selected file: " + selectedFile.getAbsolutePath());

	        // Preview the image
	        ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	        imageLabel.setIcon(imageIcon);
	    }
	}
}
