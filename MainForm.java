package forms;

import store.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainForm extends JFrame{

    private Color light;
    private Color black;
    private Color darkPink;
    private Color pink;
    private JMenuBar menuBar;
    private JMenuItem exportItem;
    private JMenuItem saveItem;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel productGroupLabel;
    private JList productGroupsList;
    private JButton addGroupButton;
    private JPanel mainPanel;
    private JScrollPane productGroupsScrollPane;
    private JLabel productsLabel;
    private JPanel toolsPane;
    private JTextField nameField;
    private JButton searchButton;
    private JPanel searchPanel;
    private JButton showAllButton;
    private JPanel showAllPane;
    private JTable productTable;
    private JButton addProductButton;
    private JLabel totalPriceLabel;
    private DefaultTableModel productTableModel;
    private  String[] columns;

    private JPopupMenu listMenu;
    private JPopupMenu tableMenu;
    private JMenuItem listMenuEdit;
    private JMenuItem listMenuDelete;
    private JMenuItem showGroupProduct;
    private JMenuItem tableMenuEdit;
    private JMenuItem tableMenuDelete;
    private JMenuItem showProduct;


    private Store store;

    public MainForm(Store store)
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.store=store;
        darkPink=new Color(255,102,241);
        light=new Color(215, 215, 215);
        black = new Color (50,50,50);
        pink=new Color(255, 153, 255);
        setVisible(true);
        store=new Store("Склад товарів");

        setSize(830, 670);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() -getHeight()) / 2);
        setLocation(x, y);
        setContentPane(mainPanel);

        configureComponents();
        setAddProductAction();
        setAddGroupAction();
        setPopUpMenuForList();
        setPopUpMenuForTable();

    }

    public void configureComponents()
    {
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(100,15));
        exportItem=new JMenuItem("Export");
        exportItem.setPreferredSize(new Dimension(50,15));
        saveItem=new JMenuItem("Save");
        saveItem.setPreferredSize(new Dimension(50,15));
        menuBar.add(exportItem);
        menuBar.add(saveItem);
        setJMenuBar(menuBar);
        exportItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                store.exportFromFile();
                productTableModel.setDataVector(store.getTableData(store.getAllProducts()),columns);
                productGroupsList.setListData(store.getProductsGroups().toArray());
                JOptionPane.showMessageDialog(null, "Дані були оновлено успішно",
                        "", JOptionPane.INFORMATION_MESSAGE);


            }
        });
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Store.saveToFile();
                JOptionPane.showMessageDialog(null, "Дані збереглись у файл успішно",
                        "", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        productGroupLabel.setBackground(darkPink);
        leftPanel.setBackground(light);
        rightPanel.setBackground(light);
        mainPanel.setBackground(light);
        productGroupLabel.setForeground(light);
        productsLabel.setBackground(darkPink);
        productsLabel.setForeground(light);
        toolsPane.setBackground(black);
        nameField.setBackground(darkPink);
        nameField.setForeground(light);
        nameField.setCaretColor(light);
        toolsPane.setBackground(light);


        productGroupsList.setForeground(light);
        productGroupsList.setBackground(darkPink);
        addGroupButton.setForeground(light);
        addGroupButton.setBackground(pink);
        showAllPane.setBackground(light);
        searchButton.setBackground(pink);
        searchButton.setForeground(light);
        showAllButton.setForeground(light);
        showAllButton.setBackground(pink);
        addProductButton.setForeground(light);
        addProductButton.setBackground(pink);
        productGroupsList.setListData(store.getProductsGroups().toArray());

        listMenuEdit=new JMenuItem("Редагувати");
        listMenuEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditGroupForm(productGroupsList,productTableModel,store,productGroupsList.getSelectedIndex());
            }
        });
        listMenuDelete=new JMenuItem("Видалити");
        listMenuDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(store.getProductsGroups().size()<2)
                {
                    JOptionPane.showMessageDialog(null, "Ви не можете видалити останній продукт категорії",
                            "Помилка видалення", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    store.getProductsGroups().remove(productGroupsList.getSelectedIndex());
                    productGroupsList.setListData(store.getProductsGroups().toArray());
                    productTableModel.setDataVector(store.getTableData(store.getAllProducts()),columns);
                }
            }
        });
        showGroupProduct=new JMenuItem("Показати інформацію");
        showGroupProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new showGroupInfo(store.getProductsGroups().get(productGroupsList.getSelectedIndex()));
            }
        });
        listMenu=new JPopupMenu();
        listMenu.add(listMenuEdit);
        listMenu.add(listMenuDelete);

        listMenu.add(showGroupProduct);

        tableMenuEdit=new JMenuItem("Редагувати");
        tableMenuDelete=new JMenuItem("Видалити");
        tableMenuDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=productTable.getSelectedRow();
                String name=(String)productTableModel.getValueAt(row,0);
                store.removeProduct(name);
                if(productGroupsList.getSelectedIndex()>=0)
                {
                    productTableModel.setDataVector(store.getTableData(store.getProductsGroups().
                            get(productGroupsList.getSelectedIndex()).getProducts()),columns);
                }
                else
                {
                    productTableModel.setDataVector(store.getTableData(store.getAllProducts()),columns);
                }
            }
        });
        tableMenuEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=productTable.getSelectedRow();
                String name=(String)productTableModel.getValueAt(row,0);
                new EditProductForm(productTableModel,productGroupsList,store,name);
            }
        });
        showProduct=new JMenuItem("Показати інформацію");
        showProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=productTable.getSelectedRow();
                String name=(String)productTableModel.getValueAt(row,0);
                new showProductForm(store.findProduct(name));
            }
        });
        tableMenu=new JPopupMenu();
        tableMenu.add(tableMenuEdit);
        tableMenu.add(tableMenuDelete);
        tableMenu.add(showProduct);

        columns=new String[]{"Назва","Виробник","Кількість","Ціна","Категорія"};
        productTableModel=new DefaultTableModel(store.getTableData(store.getAllProducts()),columns){

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }

        };

        searchPanel.setBackground(light);
        productGroupsList.setFixedCellHeight(60);
        productGroupsList.setFixedCellWidth(230);
        productTable.setModel(productTableModel);

        productTable.setRowHeight(30);


        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productTableModel.setDataVector(store.getTableData(store.getAllProducts()),columns);
                productGroupsList.clearSelection();
                if(totalPriceLabel==null){
                    totalPriceLabel=new JLabel();
                    totalPriceLabel.setText("Загальна ціна: ");
                }
            }

        });

    }

    public void setAddProductAction()
    {
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(store.getProductsGroups().size()<1)
                {
                    JOptionPane.showMessageDialog(null, "Ви не можете додати продукт, оскільки у Вас немає жодної категорії",
                            "Помилка", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    new AddProductForm(productTableModel, store);
                }
            }
        });
    }
    public void setAddGroupAction()
    {
        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddProductGroupForm(productGroupsList,store);

            }
        });
    }
    public void setPopUpMenuForList()
    {
        productGroupsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = productGroupsList.locationToIndex(e.getPoint());
                productGroupsList.setSelectedIndex(row);
                if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1 && productGroupsList.getSelectedIndex()!=-1){
                    listMenu.show(MainForm.this,e.getX()+15, e.getY()+110);
                }
                if(SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1&& productGroupsList.getSelectedIndex()!=-1)
                {
                    productTableModel.setDataVector(store.getTableData(store.getProductsGroups().
                            get(productGroupsList.getSelectedIndex()).getProducts()),columns);
                }
            }
        });
    }
    public void setPopUpMenuForTable()
    {
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = productTable.rowAtPoint(e.getPoint());
                if (r >= 0 && r < productTable.getRowCount()) {
                    productTable.setRowSelectionInterval(r, r);
                } else {
                    productTable.clearSelection();
                }

                int rowindex = productTable.getSelectedRow();
                if (rowindex < 0)
                    return;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ){
                    tableMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

    }




}

