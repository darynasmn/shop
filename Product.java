

import java.io.Serializable;

public class Product implements Serializable {
    //okayyy let`s goooooo
    private String name;
    private String producer;
    private int amount;
    private double price;
    private String description;
    private String groupName;

    /**
     * the main constructor for product
     * @param name
     * @param producer
     * @param amount
     * @param price
     * @param description
     * @param groupName
     */
    public Product(String name, String producer, int amount, double price, String description, String groupName) {
        this.name = name;
        this.producer = producer;
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.groupName = groupName;
    }

    /**
     * the method allows to edit a product which already exists
     * @param changedProduct
     * @return
     */
    //if success return 1;
    public int edit(Product changedProduct){
        this.name = changedProduct.getName();
        this.producer = changedProduct.getProducer();
        this.amount = changedProduct.getAmount();
        this.price = getPrice();
        this.description = changedProduct.getDescription();
        this.groupName = changedProduct.getGroupName();
        return 1;
    }

    /**
     * the method increases amount of the products which already exists
     */
    public void increaseAmount(){
        amount++;
    }
    //setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
