package RuayRuayMain;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

public class CSTransEditForm extends JDialog implements ActionListener {

    private static final int width = 700;
    private static final int height = 350;

    JTextField Goodsid_txt;
    JTextField Goods_name_txt;
    JTextField goods_price_txt;
    JTextField goods_amount_txt;
    Ruay_Goods Good;
    private JComboBox<Ruay_GoodsType> goods_types_combo;
    private JLabel imageLabel; // New JLabel for displaying image
    private JButton uploadImageButton; // Button for uploading a new image
    private File selectedFile; // Store the selected file for uploading
    private static final String addString = "Update";
    private static final String cancelString = "Cancel";

    public CSTransEditForm(JFrame frame, Ruay_Goods Good, Vector<Ruay_GoodsType> goodsTypes) {
        super(frame, true);
        this.Good = Good;

        // Create pane as container
        Container pane = getContentPane();
        // Set layout manager to manual
        pane.setLayout(null);

        // Create labels
        JLabel PerfromLabel = new JLabel("Edit Good");
        JLabel goodsidLabel = new JLabel("Good_id:");
        JLabel goodsnameLabel = new JLabel("Goods_name:");
        JLabel goodstypeLabel = new JLabel("Goods_type:");
        JLabel goodsprice = new JLabel("Goods_price:");
        JLabel goodsamountLabel = new JLabel("Amount_left:");
        // Create buttons
        JButton addBtn = new JButton(addString);
        JButton cancelBtn = new JButton(cancelString);
        uploadImageButton = new JButton("Upload Image");
        uploadImageButton.addActionListener(e -> chooseImageFile());
        // Create texts
        Goodsid_txt = new JTextField(10);
        Goods_name_txt = new JTextField(20);
        goods_types_combo = new JComboBox<>();
        for (Ruay_GoodsType type : goodsTypes) {
            goods_types_combo.addItem(type); // Add each type to the combo box
        }
        goods_types_combo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        goods_price_txt = new JTextField(20);
        goods_amount_txt = new JTextField(20);

        // Create control buttons.
        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        // Add labels
        pane.add(PerfromLabel);
        pane.add(goodsidLabel);
        pane.add(goodsnameLabel);
        pane.add(goodstypeLabel);
        pane.add(goodsprice);
        pane.add(goodsamountLabel);

        // Add text fields
        pane.add(Goodsid_txt);
        pane.add(Goods_name_txt);
        pane.add(goods_types_combo);
        pane.add(goods_price_txt);
        pane.add(goods_amount_txt);

        // Add control buttons
        pane.add(addBtn);
        pane.add(cancelBtn);
        pane.add(uploadImageButton);
        uploadImageButton.setBounds(30, 200, 150, 30); // Set position and size for the button
        // Assign values
        int goodId = Good.getGoodId(); 
        String goods_name = Good.getgoodsname();
        int goods_Type_id = Good.getGoodstpye_id();
        double price = Good.getGoodsprice();
        int amount = (int) Good.getAmount();
        
        // Set values in text fields
        Goodsid_txt.setText(String.valueOf(goodId));  
        Goodsid_txt.setEditable(false);
        Goods_name_txt.setText(goods_name);
        goods_types_combo.setSelectedIndex(goods_Type_id - 1);
        goods_price_txt.setText(Double.toString(price));
        goods_amount_txt.setText(String.valueOf(amount));


        imageLabel = new JLabel();
        String imgpath = Good.getImgPath(); // Assuming this returns the relative path
        String fullImgPath = "http://sczfile.online" + imgpath; // Construct the full URL
        try {
            URL url = new URL(fullImgPath);
            BufferedImage bufferedImage = ImageIO.read(url);
            if (bufferedImage != null) {
                Image image = bufferedImage.getScaledInstance(400, 400, Image.SCALE_REPLICATE);
                imageLabel.setIcon(new ImageIcon(image));
                System.out.println("Image loaded successfully.");
            } else {
                System.out.println("Buffered image is null.");
            }
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }

        imageLabel.setBounds(300, 100, 300, 150); // Set position and size for image
        pane.add(imageLabel);
        

        // Set sizes and positions for labels
        Dimension size = PerfromLabel.getPreferredSize();
        PerfromLabel.setBounds((frame.getWidth() - size.width) / 2, 5, size.width, size.height);
        size = goodsidLabel.getPreferredSize();
        goodsidLabel.setBounds(370, 30, size.width, size.height);
        size = Goodsid_txt.getPreferredSize();
        Goodsid_txt.setBounds(430, 30, size.width, size.height);
        size = goodsnameLabel.getPreferredSize();
        goodsnameLabel.setBounds(10, 20, size.width, size.height);
        size = Goods_name_txt.getPreferredSize();
        Goods_name_txt.setBounds(100, 20, size.width, size.height);
        size = goodstypeLabel.getPreferredSize();
        goodstypeLabel.setBounds(10, 50, size.width, size.height);
        size = goods_types_combo.getPreferredSize();
        goods_types_combo.setBounds(100, 50, size.width, size.height);
        size = goodsprice.getPreferredSize();
        goodsprice.setBounds(10, 80, size.width, size.height);
        size = goods_price_txt.getPreferredSize();
        goods_price_txt.setBounds(100, 80, size.width, size.height);
        size = goodsamountLabel.getPreferredSize();
        goodsamountLabel.setBounds(10, 110, size.width, size.height);
        size = goods_amount_txt.getPreferredSize();
        goods_amount_txt.setBounds(100, 110, size.width, size.height);
        size = addBtn.getPreferredSize();
        addBtn.setBounds(30, 150, size.width, size.height);
        size = cancelBtn.getPreferredSize();
        cancelBtn.setBounds(150, 150, size.width, size.height);

        // Set size and position for container
        pane.setPreferredSize(new Dimension(width, height));

        pack();
        setVisible(false);
    }
    private void chooseImageFile() {
        FileDialog fileDialog = new FileDialog(this, "Select an Image", FileDialog.LOAD);
        fileDialog.setVisible(true);
        String file = fileDialog.getDirectory() + fileDialog.getFile();
        if (file != null) {
            selectedFile = new File(file);
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();
        if (actionCommand.equals(addString)) {
            try {
                double price = Double.parseDouble(goods_price_txt.getText());
                int amount = Integer.parseInt(goods_amount_txt.getText());
                Ruay_Goods NewGoods = new Ruay_Goods(Good.getGoodId(), Goods_name_txt.getText(),
                        goods_types_combo.getSelectedIndex() + 1, "", price, amount, null);
                
                boolean isImageChanged = selectedFile != null; // Check if a new image is selected
                String imagePath = Good.getImgPath(); // Default to the existing image path

                // If a new image is selected, upload it and update the image path
                if (isImageChanged) {
                    String remoteDir = "/opt/lampp/htdocs/java/img"; // Adjust as needed
                    SFTPUploadImage.uploadFile(
                        selectedFile.getAbsolutePath(),
                        remoteDir,
                        "sczfile.online",        // Replace with actual SFTP host
                        "java_upload",                  // Replace with actual SFTP user
                        "java1234"         // Replace with actual SFTP password
                    );
                    imagePath = "/java/img/" + selectedFile.getName();  // Update the image path
                }

                // Check if any goods data has changed
                if (NewGoods.getgoodsname().equals(Good.getgoodsname()) &&
                    NewGoods.getGoodsprice() == Good.getGoodsprice() &&  
                    NewGoods.getAmount() == Good.getAmount() &&          
                    NewGoods.getGoodstpye_id() == Good.getGoodstpye_id() &&
                    !isImageChanged) { // Also check if the image has not changed
                    System.out.println("Nothing Changed!");
                } else {
                    System.out.println("Something Changed");
                    // Update goods in the database, including the new image path if it changed
                    Ruay_AllLogic.UpdateGoods(Good.getGoodId(), Goods_name_txt.getText(),
                            goods_types_combo.getSelectedIndex() + 1, "", price, amount, imagePath);
                    CSGUIMain.displayTransactions();
                    setVisible(false);
                }
            } catch (Exception ex) {
                System.err.println("Error! Invalid data: " + ex.getMessage());
            }
        } else if (actionCommand.equals(cancelString)) {
            setVisible(false);
        }
    }

}
