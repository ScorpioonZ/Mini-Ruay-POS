package RuayRuayMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.modelmbean.ModelMBean;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import RuayRuayMain.Customer.Ruay_CustomerAddForm;
import RuayRuayMain.Customer.Ruay_CustomerEditForm;
import RuayRuayMain.GoodTypeForm.Ruay_GoodTypeAddForm;
import RuayRuayMain.GoodTypeForm.Ruay_GoodTypeEditForm;
import RuayRuayMain.Model.Ruay_Customer;
import RuayRuayMain.Model.Ruay_Goods;
import RuayRuayMain.Model.Ruay_GoodsType;
import RuayRuayMain.Model.Ruay_Supplier;
import RuayRuayMain.Model.Ruay_Transactions;
import RuayRuayMain.Model.Ruay_User;
import RuayRuayMain.Supplierform.Ruay_SupplierAddForm;
import RuayRuayMain.Supplierform.Ruay_SupplierEditForm;
import RuayRuayMain.UserForm.Ruay_UserAddForm;
import RuayRuayMain.UserForm.Ruay_UserEditForm;

import java.util.Vector;

public class CSGUIMain extends JPanel implements ActionListener {

    private JTable dataTable,GoodTypeDataTable,TransactionDataTable,UserDataTable,SupplierDataTable,CustomerDataTable;  // Table for displaying goods data
    private static DefaultTableModel tableModel,GoodTypeTableModel,TransactionDataModel,UserDataModel,SupplierDataModel,CustomerDataModel;
    CSTransAddForm addform;
    CSTransEditForm editform;
    Ruay_GoodTypeEditForm TypeEditForm;
    JFrame frame;

    public static void main(String[] args) {
        // Create and set up the window.
        JFrame frame = new JFrame("GUI Mini SCM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add contents to the window.
        frame.add(new CSGUIMain(frame));

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private static final String addString = "Add";
    private static final String viewString = "View";
    private static final String exitString = "Exit";
    private static final String TranviewString = "Tran_View";
    private static final int frameWidth = 800;
    private static final int frameHeight = 600;
    private int lasttypeidx;
    private Vector<Ruay_GoodsType> goodsTypes;
    private Vector<Ruay_Customer> Customer;

    public CSGUIMain(JFrame frame) {
        super(new BorderLayout());
        this.frame = frame; // Store the reference to JFrame
        Customer = CSTransacDAO.viewCustomer();

        // Create the top-level tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create the panel for "Good"
        JPanel goodPanel = new JPanel(new BorderLayout());
        JPanel TranscationsPanel = new JPanel(new BorderLayout());
        JPanel GoodTypePanel = new JPanel(new BorderLayout());
        JPanel UserPanel = new JPanel(new BorderLayout());
        JPanel SupplierPanel = new JPanel(new BorderLayout());
        JPanel CustomerPanel = new JPanel(new BorderLayout());

        // Define panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));  // Adjust alignment and gaps
        JPanel TransacbuttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));  // Adjust alignment and gaps
        JPanel GoodTypePanelButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));  // Adjust alignment and gaps
        JPanel UserPanelButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));  // Adjust alignment and gaps
        JPanel SupplierPanelButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));  // Adjust alignment and gaps
        JPanel CustomerPanelButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));  // Adjust alignment and gaps

        // Create buttons
        JButton addBtn = new JButton(addString);
        JButton viewBtn = new JButton(viewString);
        JButton TranviewBtn = new JButton(TranviewString);
        JButton GoodTypeaddBtn = new JButton("Add_type");
        JButton GoodtypeviewBtn = new JButton("Type_view");
        JButton UserAddBTN = new JButton("User_add");
        JButton UserViewBTN = new JButton("User_view");
        JButton SupplierADDBTN = new JButton("Sup_add");
        JButton SupplierViewBTN = new JButton("Sup_view");
        JButton CusAddBTN = new JButton("Cus_add");
        JButton CusViewBTN = new JButton("Cus_view");
        // Set preferred size for buttons
        addBtn.setPreferredSize(new Dimension(100, 40));  
        viewBtn.setPreferredSize(new Dimension(100, 40));  
        TranviewBtn.setPreferredSize(new Dimension(100, 40));  
        GoodTypeaddBtn.setPreferredSize(new Dimension(100, 40));  
        GoodtypeviewBtn.setPreferredSize(new Dimension(100, 40));  
        UserAddBTN.setPreferredSize(new Dimension(100, 40));  
        UserViewBTN.setPreferredSize(new Dimension(100, 40));  
        SupplierADDBTN.setPreferredSize(new Dimension(100, 40));  
        SupplierViewBTN.setPreferredSize(new Dimension(100, 40));  
        CusAddBTN.setPreferredSize(new Dimension(100, 40));  
        CusViewBTN.setPreferredSize(new Dimension(100, 40)); 
        
        // Add buttons to the button panel
        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        TransacbuttonPanel.add(TranviewBtn);
        GoodTypePanelButton.add(GoodTypeaddBtn);
        GoodTypePanelButton.add(GoodtypeviewBtn);
        UserPanelButton.add(UserAddBTN);
        UserPanelButton.add(UserViewBTN);
        SupplierPanelButton.add(SupplierADDBTN);
        SupplierPanelButton.add(SupplierViewBTN);
        CustomerPanelButton.add(CusAddBTN);
        CustomerPanelButton.add(CusViewBTN);
        
        // Add listener to buttons
        addBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        TranviewBtn.addActionListener(this);
        GoodTypeaddBtn.addActionListener(this);
        GoodtypeviewBtn.addActionListener(this);
        UserAddBTN.addActionListener(this);
        UserViewBTN.addActionListener(this);
        SupplierADDBTN.addActionListener(this);
        SupplierViewBTN.addActionListener(this);
        CusAddBTN.addActionListener(this);
        CusViewBTN.addActionListener(this);
        
        // Set up the JTable
        tableModel = new DefaultTableModel(new Object[]{"Good ID", "Goods_name","Goods_type", "Price","Stock","IMG", "Edit", "Delete"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6 || column == 7; // Only the "Edit" and "Delete" columns should be editable
            }
        };
        GoodTypeTableModel = new DefaultTableModel(new Object[]{"Type_ID", "Type_name","Edit","Delete"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 3; // Only the "Edit" and "Delete" columns should be editable
            }
        };
        TransactionDataModel = new DefaultTableModel(new Object[]{"ID", "Good_ID","Goods_Name","Date","Amount","Total_price","User","Delete"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // Only the "Edit" and "Delete" columns should be editable
            }
        };
        UserDataModel = new DefaultTableModel(new Object[]{"ID", "Username","Password","Privilege","Name","Customer_ID","Edit","Delete"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6 || column == 7; // Only the "Edit" and "Delete" columns should be editable
            }
        };
        SupplierDataModel = new DefaultTableModel(new Object[]{"ID", "Name","Address","Phone","Email","Edit","Delete"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 6; // Only the "Edit" and "Delete" columns should be editable
            }
        };
        CustomerDataModel = new DefaultTableModel(new Object[]{"ID", "Name","Address","Phone","Email","Edit","Delete"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 6; // Only the "Edit" and "Delete" columns should be editable
            }
        };
        dataTable = new JTable(tableModel);
        dataTable.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        dataTable.getColumn("Edit").setCellEditor(new ButtonEditor(new JButton("Edit"),dataTable));
        dataTable.getColumn("Delete").setCellRenderer(new ButtonRenderer("Del"));
        dataTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JButton("Del"),dataTable));
        GoodTypeDataTable = new JTable(GoodTypeTableModel);
        GoodTypeDataTable.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        GoodTypeDataTable.getColumn("Edit").setCellEditor(new ButtonEditor(new JButton("Edit"),GoodTypeDataTable));
        GoodTypeDataTable.getColumn("Delete").setCellRenderer(new ButtonRenderer("Del"));
        GoodTypeDataTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JButton("Del"),GoodTypeDataTable));
        GoodTypeDataTable.getColumnModel().getColumn(2).setPreferredWidth(20);  // "Edit" column width
        GoodTypeDataTable.getColumnModel().getColumn(3).setPreferredWidth(20);  // "Delete" column width
        GoodTypeDataTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        TransactionDataTable = new JTable(TransactionDataModel);
        TransactionDataTable.getColumn("Delete").setCellRenderer(new ButtonRenderer("Del"));
        TransactionDataTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JButton("Del"),TransactionDataTable));
        UserDataTable = new JTable(UserDataModel);
        UserDataTable.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        UserDataTable.getColumn("Edit").setCellEditor(new ButtonEditor(new JButton("Edit"),UserDataTable));
        UserDataTable.getColumn("Delete").setCellRenderer(new ButtonRenderer("Del"));
        UserDataTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JButton("Del"),UserDataTable));
        UserDataTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        SupplierDataTable = new JTable(SupplierDataModel);
        SupplierDataTable.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        SupplierDataTable.getColumn("Edit").setCellEditor(new ButtonEditor(new JButton("Edit"),SupplierDataTable));
        SupplierDataTable.getColumn("Delete").setCellRenderer(new ButtonRenderer("Del"));
        SupplierDataTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JButton("Del"),SupplierDataTable));
        SupplierDataTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        CustomerDataTable = new JTable(CustomerDataModel);
        CustomerDataTable.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        CustomerDataTable.getColumn("Edit").setCellEditor(new ButtonEditor(new JButton("Edit"),CustomerDataTable));
        CustomerDataTable.getColumn("Delete").setCellRenderer(new ButtonRenderer("Del"));
        CustomerDataTable.getColumn("Delete").setCellEditor(new ButtonEditor(new JButton("Del"),CustomerDataTable));
        CustomerDataTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
        Vector<Ruay_GoodsType> goodsTypes = CSTransacDAO.viewGoodsTypes();
        
        this.goodsTypes = goodsTypes;
        displayTransactions();
        int lastidx = getNewestIdFromColumn0(dataTable)+1;
        int lasttypeidx = getNewestIdFromColumn0(GoodTypeDataTable);
        this.lasttypeidx = lasttypeidx;
        addform = new CSTransAddForm(frame, goodsTypes,lastidx);
        
        JScrollPane dataScrollPane = new JScrollPane(dataTable);
        JScrollPane GoodTypedataScrollPane = new JScrollPane(GoodTypeDataTable);
        JScrollPane TransactionsScrollPane = new JScrollPane(TransactionDataTable);
        JScrollPane UserScrollPane = new JScrollPane(UserDataTable);
        JScrollPane SupplierScrollPane = new JScrollPane(SupplierDataTable);
        JScrollPane CustomerScrollPane = new JScrollPane(CustomerDataTable);

        goodPanel.add(buttonPanel, BorderLayout.NORTH);  // Align buttons to the top
        goodPanel.add(dataScrollPane, BorderLayout.CENTER);  // Data display area in the center
        TranscationsPanel.add(TransacbuttonPanel, BorderLayout.NORTH);
        TranscationsPanel.add(TransactionsScrollPane);
        TranscationsPanel.setBackground(Color.LIGHT_GRAY);  // Set a background color to see the panel
        GoodTypePanel.add(GoodTypedataScrollPane, BorderLayout.CENTER); // Add the table to the center
        GoodTypePanel.add(GoodTypePanelButton, BorderLayout.NORTH);     // Add the buttons to the top (north)
        UserPanel.add(UserPanelButton,BorderLayout.NORTH);
        UserPanel.add(UserScrollPane);
        SupplierPanel.add(SupplierPanelButton,BorderLayout.NORTH);
        SupplierPanel.add(SupplierScrollPane);
        CustomerPanel.add(CustomerPanelButton,BorderLayout.NORTH);
        CustomerPanel.add(CustomerScrollPane);
        
        
        tabbedPane.addTab("Good", goodPanel);
        tabbedPane.addTab("Transactions", TranscationsPanel);
        tabbedPane.addTab("Good_Type", GoodTypePanel);
        tabbedPane.addTab("User", UserPanel);
        tabbedPane.addTab("Supplier", SupplierPanel);
        tabbedPane.addTab("Customer", CustomerPanel);

        // Create a panel for the Exit button
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align Exit button to the right
        JButton exitBtn = new JButton(exitString);
        exitBtn.addActionListener(this);
        exitPanel.add(exitBtn);
        add(tabbedPane, BorderLayout.CENTER);
        add(exitPanel, BorderLayout.SOUTH); // Position the Exit panel at the bottom
        setPreferredSize(new Dimension(frameWidth, frameHeight));
    }


    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();
        System.out.println(actionCommand);
        if (actionCommand.equals(viewString)) {
            displayTransactions();
        } else if (actionCommand.equals(exitString)) {
            System.exit(0);
        } else if (actionCommand.equals(addString)) {
            addform.setVisible(true);
        }else if(actionCommand.equals(TranviewString)) {
            Vector<Ruay_Transactions> tranlist = CSTransacDAO.viewTransactions();
            TransactionDataModel.setRowCount(0);  // Clear existing data
            for (Ruay_Transactions tranlist1 : tranlist) {
            	TransactionDataModel.addRow(new Object[]{tranlist1.getID(), tranlist1.getGood_ID(),tranlist1.getGoodName(),tranlist1.getDate(),tranlist1.getAmount(),tranlist1.getTotal_price(),tranlist1.getUserID()});
            }
        }else if(actionCommand.equals("Add_type")) {
        	Ruay_GoodTypeAddForm form = new Ruay_GoodTypeAddForm(frame,lasttypeidx);
        	form.setVisible(true);
        }else if(actionCommand.equals("Type_view")) {
            Vector<Ruay_GoodsType> goodsType = CSTransacDAO.viewGoodsTypes();
            GoodTypeTableModel.setRowCount(0);  // Clear existing data
            for (Ruay_GoodsType goodstype : goodsType) {
            	GoodTypeTableModel.addRow(new Object[]{goodstype.getTypeId(), goodstype.getTypeName()});
            }
        }else if(actionCommand.equals("Sup_add")) {
        	Ruay_SupplierAddForm addsup = new Ruay_SupplierAddForm(frame, getNewestIdFromColumn0(SupplierDataTable));
        	addsup.setVisible(true);
        }else if(actionCommand.equals("User_add")) {
        	Ruay_UserAddForm adduser = new Ruay_UserAddForm(frame, getNewestIdFromColumn0(UserDataTable),Customer);
        	adduser.setVisible(true);
        }else if(actionCommand.equals("Cus_add")) {
        	Ruay_CustomerAddForm addform = new Ruay_CustomerAddForm(frame,getNewestIdFromColumn0(CustomerDataTable));
        	addform.setVisible(true);
        }
    }
    
    private int getNewestIdFromColumn0(JTable table) {
        int rowCount = table.getRowCount();
        int maxId = Integer.MIN_VALUE;
        for (int i = 0; i < rowCount; i++) {
            int currentId = (int) table.getValueAt(i, 0);  // Assuming column 0 contains the IDs
            if (currentId > maxId) {
                maxId = currentId;
            }
        }
        return maxId;
    }
    public static void displayTransactions() {
        Vector<Ruay_Goods> goods = CSTransacDAO.viewTrans();
        tableModel.setRowCount(0);  // Clear existing data
        for (Ruay_Goods goods1 : goods) {
            tableModel.addRow(new Object[]{goods1.getGoodId(), goods1.getgoodsname(), goods1.getGoodstpye_id(),goods1.getGoodsprice(),goods1.getAmount(),goods1.getImgPath()});
        }
        Vector<Ruay_GoodsType> goodsType = CSTransacDAO.viewGoodsTypes();
        GoodTypeTableModel.setRowCount(0);  // Clear existing data
        for (Ruay_GoodsType goodstype : goodsType) {
        	GoodTypeTableModel.addRow(new Object[]{goodstype.getTypeId(), goodstype.getTypeName()});
        }
        Vector<Ruay_Transactions> tranlist = CSTransacDAO.viewTransactions();
        TransactionDataModel.setRowCount(0);  // Clear existing data
        for (Ruay_Transactions tranlist1 : tranlist) {
        	TransactionDataModel.addRow(new Object[]{tranlist1.getID(), tranlist1.getGood_ID(),tranlist1.getGoodName(),tranlist1.getDate(),tranlist1.getAmount(),tranlist1.getTotal_price(),tranlist1.getUserID()});
        }
        Vector<Ruay_User> userlist = CSTransacDAO.viewUsers();
        UserDataModel.setRowCount(0);  // Clear existing data
        for (Ruay_User userlist1 : userlist) {
        	UserDataModel.addRow(new Object[]{userlist1.getId(),userlist1.getUsername(),userlist1.getPassword(),userlist1.getPrivilege(),userlist1.getName(),userlist1.getcusId()});
        }
        Vector<Ruay_Supplier> suplist = CSTransacDAO.viewSupplier();
        SupplierDataModel.setRowCount(0);  // Clear existing data
        for (Ruay_Supplier suplist1 : suplist) {
        	SupplierDataModel.addRow(new Object[]{suplist1.getSupplierID(),suplist1.getSupplierName(),suplist1.getSupplierAddress(),suplist1.getSupplierPhone(),suplist1.getSupplierEmail()});
        }
        Vector<Ruay_Customer>cuslist = CSTransacDAO.viewCustomer();
        CustomerDataModel.setRowCount(0);  // Clear existing data
        for (Ruay_Customer cuslist1 : cuslist) {
        	CustomerDataModel.addRow(new Object[]{cuslist1.getCustomerID(),cuslist1.getCustomerName(),cuslist1.getCustomerAddress(),cuslist1.getCustomerPhone(),cuslist1.getCustomerEmail()});
        }
    }

    // Custom renderer for the "Edit" and "Delete" buttons
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String text) {
            setText(text);
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                                boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Custom editor for both "Edit" and "Delete" buttons
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private boolean isPushed;
        private JTable table;  // Add a reference to the table

        public ButtonEditor(JButton btn, final JTable table) {
            super(new JTextField());
            this.button = btn;
            this.table = table;  // Store the table reference
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int selectedRow = table.getSelectedRow(); // Use the specific table
                    if (button.getText().equals("Edit")) {
                        if (table == dataTable) {  // Check if it's the goods table
                            int goodId = (int) table.getValueAt(selectedRow, 0);
                            String Good_name = (String) table.getValueAt(selectedRow, 1);
                            int Good_type = (int) table.getValueAt(selectedRow, 2);
                            double Good_price = (double) table.getValueAt(selectedRow, 3);
                            int amount = (int) table.getValueAt(selectedRow, 4);
                            String imgpath = (String) table.getValueAt(selectedRow, 5);
                            Ruay_Goods goods = new Ruay_Goods(goodId, Good_name, Good_type, "", Good_price, amount, imgpath);
                            editform = new CSTransEditForm(frame, goods, goodsTypes);
                            editform.setVisible(true);
                        } else if (table == GoodTypeDataTable) {  // Check if it's the goods type table
                            int typeId = (int) table.getValueAt(selectedRow, 0);
                            String typeName = (String) table.getValueAt(selectedRow, 1);
                            Ruay_GoodsType goodtype = new Ruay_GoodsType(typeId, typeName);
                            System.out.println(goodtype);
                            TypeEditForm = new Ruay_GoodTypeEditForm(frame, goodtype);
                            TypeEditForm.setVisible(true);
                            // You can add specific logic for editing goods type here
                        }else if (table == TransactionDataTable) {
                        	System.out.println("Edit");
                        }else if(table == SupplierDataTable) {
                            int supid = (int) table.getValueAt(selectedRow, 0);
                            String supname = (String) table.getValueAt(selectedRow, 1);
                            String supaddress = (String) table.getValueAt(selectedRow, 2);
                            String supphone = (String) table.getValueAt(selectedRow, 3);
                            String supemail = (String) table.getValueAt(selectedRow, 4);
                            Ruay_Supplier sup = new Ruay_Supplier(supid, supname, supaddress, supphone, supemail);
                        	Ruay_SupplierEditForm supedit = new Ruay_SupplierEditForm(frame, sup);
                        	supedit.setVisible(true);
                        }else if(table == UserDataTable) {
                        	int id = (int) table.getValueAt(selectedRow, 0);
                        	String username = (String) table.getValueAt(selectedRow, 1);
                        	String password = (String) table.getValueAt(selectedRow, 2);
                        	String privilege = (String) table.getValueAt(selectedRow, 3);
                        	String name = (String) table.getValueAt(selectedRow, 4);
                        	int cus_id = (int) table.getValueAt(selectedRow, 5);
                        	Ruay_User user = new Ruay_User(id, username, password, privilege, name,cus_id);
                        	Ruay_UserEditForm editform = new Ruay_UserEditForm(frame, Customer, user);
                        	editform.setVisible(true);
                        }else if(table == CustomerDataTable) {
                        	int id = (int) table.getValueAt(selectedRow, 0);
                        	String username = (String) table.getValueAt(selectedRow, 1);
                        	String password = (String) table.getValueAt(selectedRow, 2);
                        	String privilege = (String) table.getValueAt(selectedRow, 3);
                        	String name = (String) table.getValueAt(selectedRow, 4);
                        	Ruay_Customer customer = new Ruay_Customer(id, username, password, privilege, name);
                        	Ruay_CustomerEditForm editform = new Ruay_CustomerEditForm(frame, customer);
                        	editform.setVisible(true);
                        }
                        
                        
                        
                        
                    } else {
                        // Delete logic for each table
                        if (table == dataTable) {
                            int goodId = (int) table.getValueAt(selectedRow, 0);
                            System.out.println("Good with ID: " + goodId);
                            //tableModel.removeRow(selectedRow);  // Remove row from goods table
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);  // Get parent frame
                            int response = DialogUtils.showDialog(frame," Good with ID: " + goodId);
                            if (response == 0) {
                                System.out.println("User confirmed deletion.");
                                DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
                                model.removeRow(selectedRow);
                                CSTransacDAO.DeleteGoods(goodId);
                            } else {
                                System.out.println("User canceled deletion.");
                                // Do nothing or handle cancellation
                            }
                        } else if (table == GoodTypeDataTable) {
                            int typeId = (int) table.getValueAt(selectedRow, 0);
                            String TypeName = (String) table.getValueAt(selectedRow, 1);
                            System.out.println("Good Type with ID: " + typeId);
                             // Remove row from goods type table
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);  // Get parent frame
                            int response = DialogUtils.showDialog(frame," Good Type with ID: " + typeId);
                            if (response == 0) {
                                System.out.println("User confirmed deletion.");
                                GoodTypeTableModel.removeRow(selectedRow);
                                Ruay_AllLogic.DeleteGoodType(typeId, TypeName);
                            } else {
                                System.out.println("User canceled deletion.");
                                // Do nothing or handle cancellation
                            }
                        }else if(table == TransactionDataTable) {
                            int trans_ID = (int) table.getValueAt(selectedRow, 0);
                            int GoodID = (int) table.getValueAt(selectedRow, 1);
                            String GoodName = (String) table.getValueAt(selectedRow, 2);
                            String Date = (String) table.getValueAt(selectedRow, 3);
                            int amount = (int) table.getValueAt(selectedRow, 4);
                            double totalprice = (double) table.getValueAt(selectedRow, 5);
                            int userid = (int) table.getValueAt(selectedRow, 6);
                             // Remove row from goods type table
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);  // Get parent frame
                            int response = DialogUtils.showDialog(frame,"Transactions with ID: " + trans_ID);
                            if (response == 0) {
                                System.out.println("User confirmed deletion.");
                                TransactionDataModel.removeRow(selectedRow);
                                Ruay_AllLogic.DeleteTransaction(trans_ID, GoodID, GoodName, Date, amount, totalprice, userid);
                            } else {
                                System.out.println("User canceled deletion.");
                            }
                        }else if(table == SupplierDataTable) {
                            int trans_ID = (int) table.getValueAt(selectedRow, 0);
                            String GoodID = (String) table.getValueAt(selectedRow, 1);
                            String GoodName = (String) table.getValueAt(selectedRow, 2);
                            String Date = (String) table.getValueAt(selectedRow, 3);
                            String Email = (String) table.getValueAt(selectedRow, 4);
                             // Remove row from goods type table
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);  // Get parent frame
                            int response = DialogUtils.showDialog(frame,"Supplier with ID: " + trans_ID);
                            if (response == 0) {
                                System.out.println("User confirmed deletion.");
                                SupplierDataModel.removeRow(selectedRow);
                                Ruay_AllLogic.DeleteSupplier(trans_ID, GoodID, GoodName, Date, Email);
                            } else {
                                System.out.println("User canceled deletion.");
                            }
                        }else if(table == UserDataTable) {
                            int trans_ID = (int) table.getValueAt(selectedRow, 0);
                             // Remove row from goods type table
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);  // Get parent frame
                            int response = DialogUtils.showDialog(frame,"User with ID: " + trans_ID);
                            if (response == 0) {
                                System.out.println("User confirmed deletion.");
                                UserDataModel.removeRow(selectedRow);
                                Ruay_AllLogic.DeleteUser(trans_ID);
                            } else {
                                System.out.println("User canceled deletion.");
                            }
                        }else if(table == CustomerDataTable) {
                            int trans_ID = (int) table.getValueAt(selectedRow, 0);
                            String GoodID = (String) table.getValueAt(selectedRow, 1);
                            String GoodName = (String) table.getValueAt(selectedRow, 2);
                            String Date = (String) table.getValueAt(selectedRow, 3);
                            String Email = (String) table.getValueAt(selectedRow, 4);
                             // Remove row from goods type table
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);  // Get parent frame
                            int response = DialogUtils.showDialog(frame,"Customer with ID: " + trans_ID);
                            if (response == 0) {
                                System.out.println("User confirmed deletion.");
                                CustomerDataModel.removeRow(selectedRow);
                                Ruay_AllLogic.DeleteCustomer(trans_ID, GoodID, GoodName, Date, Email);
                            } else {
                                System.out.println("User canceled deletion.");
                            }
                        }
                    }
                }
            });
        }
        public class DialogUtils {
            public static int showDialog(JFrame parent, String Data) {

                // Wrapper to hold the result
                final int[] result = {-1};  // Default to "Cancel" (or any value you want for cancellation)
                
                final JDialog dialog = new JDialog(parent, "Confirm Deletion", true);
                dialog.setLayout(new FlowLayout());

                dialog.add(new JLabel("Delete :" + Data +"                               "));

                // OK button
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        result[0] = 0; // Set to "OK" response code
                        dialog.dispose();
                    }
                });
                dialog.add(okButton);

                // Cancel button
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        result[0] = -1; // Set to "Cancel" response code
                        dialog.dispose();
                    }
                });
                dialog.add(cancelButton);

                dialog.setSize(300, 100);
                dialog.setLocationRelativeTo(parent);
                dialog.setVisible(true);

                // Return the result based on user's choice
                return result[0];
            }
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                              int row, int column) {
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText().equals("Edit") ? "Edit" : "Delete";
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

}
