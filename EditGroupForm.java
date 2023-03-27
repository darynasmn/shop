package forms;

import store.ProductsGroup;
import store.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGroupForm extends JFrame {
    private JPanel mainPanel;
    private JTextField nameField;
    private JLabel addNewProductLabel;
    private JScrollPane descriptionScrollPanel;
    private JTextArea descriptionTextArea;
    private JPanel addButtonPanel;
    private JButton editButton;
    private Color light;
    private Color dark;
    private Color green;
    private Color orange;

    public EditGroupForm(JList list, DefaultTableModel table, Store store, int index)
    {
        setSize(400,300);
        setTitle("Редагувати категорію");
        setLocationRelativeTo(null);
        dark=new Color(255,102,241);
        light=new Color(215, 215, 215);
        green=new Color(50,50,50);
        orange=new Color(255, 153, 255);
        setVisible(true);
        setContentPane(mainPanel);
        mainPanel.setBackground(dark);
        addNewProductLabel.setForeground(light);
        addButtonPanel.setBackground(dark);
        descriptionScrollPanel.setBackground(light);
        editButton.setForeground(light);
        editButton.setBackground(green);
        nameField.setText(store.getProductsGroups().get(index).getName());
        descriptionTextArea.setText(store.getProductsGroups().get(index).getDescription());

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=(String)nameField.getText();
                String description=(String)descriptionTextArea.getText();
                if(name.isEmpty() || description.isEmpty())
                    JOptionPane.showMessageDialog(null, "Якесь поле пусте\nВведіть усі значення", "Пусте поле", JOptionPane.ERROR_MESSAGE);
                else {
                    boolean changable=true;
                    for(int i=0;i<store.getProductsGroups().size();i++)
                        if(i!=index && name.equals(store.getProductsGroups().get(i).getName()))
                            changable=false;

                    if(changable) {
                        ProductsGroup toChange = store.getProductsGroups().get(index);
                        toChange.setName(name);
                        toChange.setDescription(description);
                        list.setListData(store.getProductsGroups().toArray());
                        String[]columns=new String[]{"Назва","Виробник","Кількість","Ціна","Група"};
                        table.setDataVector(store.getTableData(store.getAllProducts()),columns);
                        EditGroupForm.this.setVisible(false);
                        EditGroupForm.this.dispose();
                    }else
                        JOptionPane.showMessageDialog(null, "Категорія з такою назвою вже існує\n" +
                                "Будь ласка, введіть іншу назву", "Уже існує", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
