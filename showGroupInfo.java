package forms;

import store.Product;
import store.ProductsGroup;

import javax.swing.*;
import java.awt.*;

public class showGroupInfo extends JFrame {
    private JPanel mainPanel;
    private JTextArea descriptionTextArea;
    private JLabel nameLabel;
    private JLabel sumLabel;
    private JLabel amountLabel;
    private JPanel descriptionScrollPanel;
    private Color light;
    private Color dark;
    private Color green;
    private Color orange;

    public showGroupInfo(ProductsGroup group)
    {
        setSize(290,300);
        setTitle("Інформація");
        setLocationRelativeTo(null);
        dark=new Color(255,102,241);
        light=new Color(215, 215, 215);
        green=new Color(50,50,50);
        orange=new Color(255, 153, 255);
        setVisible(true);
        setContentPane(mainPanel);
        mainPanel.setBackground(dark);
        nameLabel.setText(group.getName());
        nameLabel.setForeground(light);
        descriptionTextArea.setText(group.getDescription());
        descriptionTextArea.setEditable(false);
        amountLabel.setText("Кількість: "+group.getProducts().size());
        double price=0;
        for(Product product:group.getProducts())
        {
            price+=product.getPrice()*product.getAmount();
        }
        sumLabel.setText("Ціна: "+price+"$");
        descriptionScrollPanel.setBackground(light);
    }
}
