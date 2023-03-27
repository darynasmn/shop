package forms;

import store.Product;
import store.ProductsGroup;
import store.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProductForm extends JFrame {
    private JTextField nameField;
    private JTextField producerField;
    private JSpinner amountSpinner;
    private JLabel addNewProductLabel;
    private JScrollPane descriptionScrollPanel;
    private JTextArea descriptionTextArea;
    private JComboBox groupBox;
    private JPanel addButtonPanel;
    private JButton editButton;
    private JSpinner priceSpinner;
    private JPanel mainPanel;
    private Color light;
    private Color dark;
    private Color green;
    private Color orange;
    public EditProductForm(DefaultTableModel tableModel,JList list, Store store,String productName) {
        setSize(400, 600);
        setTitle("Редагувати продукт");
        setLocationRelativeTo(null);
        dark = new Color(255,102,241);
        light = new Color(215, 215, 215);
        green = new Color(50,50,50);
        orange = new Color(255, 153, 255);
        setVisible(true);
        addNewProductLabel.setForeground(light);
        addButtonPanel.setBackground(dark);
        descriptionScrollPanel.setBackground(light);
        editButton.setForeground(light);
        editButton.setBackground(green);


        for (ProductsGroup group : store.getProductsGroups()) {
            groupBox.addItem(group);
            if(group.getName().equals(store.findProduct(productName).getGroupName()))
                groupBox.setSelectedItem(group);
        }

        ProductsGroup oldGroup=(ProductsGroup) groupBox.getSelectedItem();
        amountSpinner.setModel(new SpinnerNumberModel(0,0,1000000,1));
        priceSpinner.setModel(new SpinnerNumberModel(0,0,1000000,0.1));
        mainPanel.setBackground(dark);
        setContentPane(mainPanel);
        Product toChange=store.findProduct(productName);
        nameField.setText(toChange.getName());
        producerField.setText(toChange.getProducer());
        amountSpinner.setValue((int)toChange.getAmount());
        priceSpinner.setValue((double)toChange.getPrice());
        descriptionTextArea.setText(toChange.getDescription());

        editButton.addActionListener(new ActionListener() {
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
                    JOptionPane.showMessageDialog(null, "Якесь поле не заповнено\nВведіть усі дані ", "Пусте поле", JOptionPane.ERROR_MESSAGE);
                else
                {
                    boolean changable=true;
                    for(Product product:store.getAllProducts())
                    {
                        if(product.getName().equals(name) && !name.equals(productName))
                            changable=false;
                    }
                    if(changable)
                    {
                        String[]columns=new String[]{"Назва","Виробник","Кількість","Ціна","Категорія"};
                        Product toChange =store.findProduct(productName);
                        toChange.setName(name);
                        toChange.setProducer(producer);
                        toChange.setAmount(amount);
                        toChange.setPrice(price);
                        toChange.setDescription(description);
                        if(oldGroup.getName().equals(group.getName()))
                        {
                            tableModel.setDataVector(store.getTableData(store.getAllProducts()),columns);
                        }
                        else
                        {
                            store.removeProduct(name);
                            group.addProduct(toChange);

                        }


                        if(list.getSelectedIndex()<0) {
                            tableModel.setDataVector(store.getTableData(store.getAllProducts()), columns);
                        }
                        else
                            tableModel.setDataVector(store.getTableData(store.getProductsGroups().get(list.getSelectedIndex()).getProducts()), columns);
                        EditProductForm.this.setVisible(false);
                        EditProductForm.this.dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Продукт з таким іменем уже існує\n" +
                                "Будь ласка, вибервть інше ім'я", "Уже існує", JOptionPane.ERROR_MESSAGE);
                    }
                }


            }
        });
    }
}
