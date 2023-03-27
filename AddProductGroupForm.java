package forms;

import store.ProductsGroup;
import store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddProductGroupForm extends JFrame {
    private JPanel mainPanel;
    private JTextField nameField;
    private JLabel addNewProductLabel;
    private JScrollPane descriptionScrollPanel;
    private JTextArea descriptionTextArea;
    private JPanel addButtonPanel;
    private JButton addButton;
    private Color light;
    private Color dark;
    private Color green;
    private Color orange;

    public AddProductGroupForm(JList list, Store store) {
        setSize(400, 300);
        setTitle("Додати категорію");
        setLocationRelativeTo(null);
        dark = new Color(255,102,241);
        light = new Color(215, 215, 215);
        green = new Color(50,50,50);
        orange = new Color(255, 153, 255);
        setVisible(true);
        setContentPane(mainPanel);
        mainPanel.setBackground(dark);
        addNewProductLabel.setForeground(light);
        addButtonPanel.setBackground(dark);
        descriptionScrollPanel.setBackground(light);
        addButton.setForeground(light);
        addButton.setBackground(green);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String) nameField.getText();
                String description = (String) descriptionTextArea.getText();
                if (name.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Якесь поле пусте\nВведіть усі значення", "Пусте поле", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean toAdd = true;
                    for (ProductsGroup pr : store.getProductsGroups()) {
                        if (pr.getName().equals(name))
                            toAdd=false;
                    }

                    if (toAdd) {
                        store.addProductGroup(new ProductsGroup(name, description));
                        list.setListData(store.getProductsGroups().toArray());
                        AddProductGroupForm.this.setVisible(false);
                        AddProductGroupForm.this.dispose();
                    }
                    else
                    {
                    JOptionPane.showMessageDialog(null, "Категорія з такою назвою вже існує\n" +
                            "Будь ласка, введіть іншу назву", "Уже існує", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });
    }
}
