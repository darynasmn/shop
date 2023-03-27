package forms;

import store.Product;
import store.ProductsGroup;
import store.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddProductForm extends JFrame{
    private JPanel mainPanel;
    private JTextField nameField;
    private JLabel addNewProductLabel;
    private JButton addButton;
    private JPanel addButtonPanel;
    private JScrollPane descriptionScrollPanel;
    private JTextField producerField;
    private JSpinner amountSpinner;
    private JSpinner priceSpinner;
    private JTextArea descriptionTextArea;
    private JComboBox groupBox;
    private Color light;
    private Color dark;
    private Color green;
    private Color orange;
    public AddProductForm(DefaultTableModel tableModel, Store store)
    {
        setSize(400,600);
        setTitle("Додати товар");
        setLocationRelativeTo(null);
        dark=new Color(255,102,241);
        light=new Color(215, 215, 215);
        green=new Color(50,50,50);
        orange=new Color(255, 153, 255);
        setVisible(true);
        addNewProductLabel.setForeground(light);
        addButtonPanel.setBackground(dark);
        descriptionScrollPanel.setBackground(light);
        addButton.setForeground(light);
        addButton.setBackground(green);
        for(ProductsGroup group:store.getProductsGroups())
            groupBox.addItem(group);
        amountSpinner.setModel(new SpinnerNumberModel(0,0,1000000,1));
        priceSpinner.setModel(new SpinnerNumberModel(0,0,1000000,0.1));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double price=(double)priceSpinner.getValue();
                String name=(String)nameField.getText();
                String producer=(String)producerField.getText();
                String description=(String)descriptionTextArea.getText();
                ProductsGroup group=(ProductsGroup)groupBox.getSelectedItem();
                int amount=(int)amountSpinner.getValue();
                if(price==0)
                    JOptionPane.showMessageDialog(null, "Ціна не може бути 0", "Неправильна ціна", JOptionPane.ERROR_MESSAGE);
                else if(name.isEmpty() || producer.isEmpty() || description.isEmpty())
                    JOptionPane.showMessageDialog(null, "Якесь поле пусте\nВведіть усі значення", "Пусте поле", JOptionPane.ERROR_MESSAGE);
                else {
                    boolean toAdd=true;
                    for(Product product:store.getAllProducts())
                        if(product.getName().equals(name))
                            toAdd=false;
                    if(toAdd) {
                        String[]columns=new String[]{"Назва","Виробник","Кількість","Ціна","Категорія"};
                        group.addProduct(new Product(name, producer,amount,price,description,""));
                        tableModel.setDataVector(store.getTableData(store.getAllProducts()),columns);
                        AddProductForm.this.setVisible(false);
                        AddProductForm.this.dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Продукт з такою назвою вже існує\n" +
                                "Будь ласка, введіть іншу назву", "Уже існує", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        mainPanel.setBackground(dark);
        setContentPane(mainPanel);
    }


}
